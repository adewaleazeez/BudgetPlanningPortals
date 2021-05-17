/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.BudgetYears;
import com.bpp.hibernate.Notifications;
import com.bpp.hibernate.NotificationsHibernateHelper;
import com.bpp.hibernate.RequestAgents;
import com.bpp.hibernate.RequestAgentsHibernateHelper;
import com.bpp.hibernate.RequestApprovals;
import com.bpp.hibernate.RequestApprovalsHibernateHelper;
import com.bpp.hibernate.RequestDetails;
import com.bpp.hibernate.RequestDetailsHibernateHelper;
import com.bpp.hibernate.RequestTypes;
import com.bpp.hibernate.RequestTypesHibernateHelper;
import com.bpp.hibernate.UserRole;
import com.bpp.hibernate.UserRoleHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Request Document Servlet class
 *
 * @author Lekan
 * @since 28/6/2017
 */
public class RequestDetailsServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            RequestDetailsHibernateHelper helper = new RequestDetailsHibernateHelper();
            RequestDetails requestDetail = new RequestDetails();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    requestDetail.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    requestDetail.setDateCreated(Utility.dbDateNow());
                    requestDetail.setItemUrl(request.getParameter("itemUrl"));
                    requestDetail.setVersionId(Integer.parseInt(request.getParameter("versionID")));
                    requestDetail.setRequiresApproval(Boolean.parseBoolean(request.getParameter("requiresApproval")));
                    requestDetail.setFullyApproved(true);

                    RequestTypes requestType = new RequestTypesHibernateHelper().exists(Integer.parseInt(request.getParameter("requestTypeID")));
                    requestDetail.setRequestTypes(requestType);

                    BudgetYears budgetYears = new BudgetYearHibernateHelper().exists(Integer.parseInt(request.getParameter("year")));
                    requestDetail.setBudgetYears(budgetYears);

                    requestDetail.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
//System.out.println("requestDetail getVersionId  "+requestDetail.getVersionId());

                    resp = helper.insert(requestDetail);

                    if (resp.equals(Utility.ActionResponse.INSERTED.toString())) {
                        //Check for user role relevant to request type
                        int requestTypeID = Integer.parseInt(request.getParameter("requestTypeID"));
                        int mdaID = Integer.parseInt(request.getParameter("mdaID"));
                        int relevantRole = -1;

                        String[] allRoles = request.getParameter("roles").split(",");
                        for (String role : allRoles) {
                            RequestAgents obj = new RequestAgentsHibernateHelper().fetchObj(Integer.parseInt(role), requestTypeID, mdaID);

                            if (obj != null) {
                                relevantRole = Integer.parseInt(role);
                            }
                        }

                        if (relevantRole != -1) {
                            //Alert immediate next agents in workflow mapped to this request type
                            List<RequestAgents> requestAgents = new RequestAgentsHibernateHelper().fetchAll2(requestType.getId());

                            String recipients = "";
                            String submissionAgentRole = "",
                                    submissionAgentMda = "";

                            for (Iterator iterator = requestAgents.iterator(); iterator.hasNext();) {
                                RequestAgents agent = (RequestAgents) iterator.next();

                                if (agent.getRoles().getId() == relevantRole && agent.getMdas().getId() == mdaID) {
                                    submissionAgentRole = agent.getRoles().getName();
                                    submissionAgentMda = agent.getMdas().getName();

                                    continue;
                                }

                                List<UserRole> users = new UserRoleHibernateHelper().fetchAll2(agent.getRoles().getId(), agent.getMdas().getId());
                                for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                                    UserRole user = (UserRole) iterator2.next();

                                    recipients += user.getUsers().getEmail() + (iterator2.hasNext() ? "," : "");

                                    RequestApprovalsHibernateHelper helper2 = new RequestApprovalsHibernateHelper();
                                    RequestApprovals requestApproval = new RequestApprovals();
                                    requestApproval.setId(Integer.parseInt(helper2.getMaxserialNo()) + 1);
                                    requestApproval.setDateCreated(Utility.dbDateNow());
                                    requestApproval.setRequestDetails(requestDetail);
                                    requestApproval.setUsers(user.getUsers());
                                    requestApproval.setComment("<Auto-Generated-Workflow>");
                                    requestApproval.setApprovalStatus(Utility.OPEN_FOR_APPROVAL_STATUS);
                                    requestApproval.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);

                                    helper2.insert(requestApproval);
                                }

                                break;
                            }

                            //Create notification
                            Notifications notification = new NotificationsHibernateHelper().fetchObj(relevantRole, requestType.getId(), Utility.SUBMISSION_NOTIFICATION_TYPE);
                            String mailBody = notification.getNotificationText().replaceAll("&lt;Role&gt;", submissionAgentRole)
                                    .replaceAll("&lt;MDA&gt;", submissionAgentMda)
                                    .replaceAll("&lt;FullName&gt;", submissionAgentMda)
                                    .replaceAll("&lt;Link&gt;", Utility.SERVER_PROTOCOL + Utility.SERVER_NAME + ":"
                                            + Utility.SERVER_PORT + Utility.SITE_URL + requestDetail.getItemUrl());

                            try {
                                //Send Notification to Users in this Role
                                Utility utility = new Utility();
                                utility.setMailServerProperties();
                                utility.createEmailMessage(notification.getNotificationSubject(), mailBody, recipients);
                                utility.sendEmail();
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    requestDetail = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    requestDetail.setFullyApproved(Boolean.parseBoolean(request.getParameter("fullyApproved")));

                    resp = helper.update(requestDetail);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    requestDetail = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(requestDetail);
                }
            }

            if (option.equals(Utility.OPTION_SELECT)) {
                if (request.getParameter("requestTypeID") != null) {
                    resp = helper.fetch2(Integer.parseInt(request.getParameter("requestTypeID")));
                } else {
                    resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
                }
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }

            out.println(resp);
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            //Logger.getLogger(BudgetTimetableServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            // Logger.getLogger(BudgetTimetableServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Request Details";
    }
}
