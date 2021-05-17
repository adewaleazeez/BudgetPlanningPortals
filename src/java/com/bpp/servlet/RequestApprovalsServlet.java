/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetStatusHibernateHelper;
import com.bpp.hibernate.BudgetVersionStatus;
import com.bpp.hibernate.BudgetVersionStatusHibernateHelper;
import com.bpp.hibernate.Notifications;
import com.bpp.hibernate.NotificationsHibernateHelper;
import com.bpp.hibernate.RequestAgents;
import com.bpp.hibernate.RequestAgentsHibernateHelper;
import com.bpp.hibernate.RequestApprovals;
import com.bpp.hibernate.RequestApprovalsHibernateHelper;
import com.bpp.hibernate.RequestDetails;
import com.bpp.hibernate.RequestDetailsHibernateHelper;
import com.bpp.hibernate.RequestDocuments;
import com.bpp.hibernate.RequestDocumentsHibernateHelper;
import com.bpp.hibernate.UserRole;
import com.bpp.hibernate.UserRoleHibernateHelper;
import com.bpp.hibernate.Users;
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
 * Request Approvals Servlet class
 *
 * @author Lekan
 * @since 28/6/2017
 */
public class RequestApprovalsServlet extends HttpServlet {

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

            RequestApprovalsHibernateHelper helper = new RequestApprovalsHibernateHelper();
            RequestApprovals requestApproval = new RequestApprovals();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    requestApproval.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    requestApproval.setDateCreated(Utility.dbDateNow());
                    requestApproval.setApprovalStatus(Integer.parseInt(request.getParameter("approvalStatus")));

                    Users user = new UsersHibernateHelper().exists(Integer.parseInt(request.getParameter("userID")));
                    requestApproval.setUsers(user);

                    RequestDetails requestDetails = new RequestDetailsHibernateHelper().exists(Integer.parseInt(request.getParameter("requestDetailID")));
                    requestApproval.setRequestDetails(requestDetails);

                    requestApproval.setComment(request.getParameter("comment"));
                    requestApproval.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);

                    resp = helper.insert(requestApproval);

                    int requestTypeID = Integer.parseInt(request.getParameter("requestTypeID"));
                    int nextRoleID = -1,
                            nextMdaID = -1;

                    String recipients = "";
                    String mailBody = "";
                    String SMS_Message = "";
                    String SMS_Recipients = "";
                    String requestTypeName = "";
                    String comment = request.getParameter("comment");
                    String submissionAgentRole = "",
                            submissionAgentMda = "";

                    if (requestTypeID == Utility.MYBF_REQUEST_TYPE) {
                        requestTypeName = "MYBF Forecast Estimates";
                    }

                    Notifications notification = null;

                    boolean isFullyApproved = false;

                    int mdaID = Integer.parseInt(request.getParameter("mdaID"));
                    int relevantRole = -1;

                    String[] allRoles = request.getParameter("roles").split(",");
                    for (String role : allRoles) {
                        RequestAgents obj = new RequestAgentsHibernateHelper().fetchObj(Integer.parseInt(role), requestTypeID, mdaID);

                        if (obj != null) {
                            relevantRole = Integer.parseInt(role);
                        }
                    }

                    List<RequestAgents> requestAgents = new RequestAgentsHibernateHelper().fetchAll2(requestTypeID);
                    if (requestApproval.getApprovalStatus() == Utility.APPROVAL_STATUS) {
                        for (Iterator iterator = requestAgents.iterator(); iterator.hasNext();) {
                            RequestAgents agent = (RequestAgents) iterator.next();

                            for (Iterator iterator2 = user.getUserRoles().iterator(); iterator2.hasNext();) {
                                UserRole userRole = (UserRole) iterator2.next();

                                if (agent.getRoles().getId() == userRole.getRoles().getId()) {
                                    if (iterator.hasNext()) {
                                        submissionAgentRole = agent.getRoles().getName();
                                        submissionAgentMda = agent.getMdas().getName();

                                        agent = (RequestAgents) iterator.next();
                                        nextRoleID = agent.getRoles().getId();
                                        nextMdaID = agent.getMdas().getId();
                                    } else {
                                        //CHECK IF OTHER USERS WITHIN THE ROLE HAVE ALSO APPROVED
                                        List<UserRole> usersInRole = new UserRoleHibernateHelper().fetchAll2(agent.getRoles().getId(), agent.getMdas().getId());

                                        for (Iterator iterator3 = usersInRole.iterator(); iterator3.hasNext();) {
                                            UserRole userRole2 = (UserRole) iterator3.next();

                                            requestApproval = new RequestApprovalsHibernateHelper().fetchByUser(userRole2.getUsers().getId(), requestTypeID);

                                            if (requestApproval.getApprovalStatus() == Utility.REJECTION_STATUS) {
                                                isFullyApproved = false;
                                                break;
                                            } else {
                                                isFullyApproved = true;
                                            }
                                        }
                                    }

                                    break;
                                }
                            }

                            if (nextRoleID != -1 || isFullyApproved) {
                                break;
                            }
                        }

                        //FULLY APPROVED
                        if (isFullyApproved) {
                            //Update request details
                            requestDetails.setFullyApproved(true);
                            new RequestDetailsHibernateHelper().update(requestDetails);

                            if (requestTypeID == Utility.MYBF_REQUEST_TYPE) {
                                BudgetVersionStatusHibernateHelper helper3 = new BudgetVersionStatusHibernateHelper();
                                BudgetVersionStatus budgetVersion = helper3.fetchObj(requestDetails.getVersionId());
                                budgetVersion.setBudgetStatus(new BudgetStatusHibernateHelper().fetchObj(Utility.APPROVED_BUDGET_VERSION));
                                helper3.update(budgetVersion);

                                //resp = "[[" + Utility.ActionResponse.FULLY_APPROVED.toString() + "]]";
                            }

                            //Send Mails to All
                            int roleID = -1;
                            for (Iterator iterator = requestAgents.iterator(); iterator.hasNext();) {
                                RequestAgents agent = (RequestAgents) iterator.next();

                                if (roleID == -1) {
                                    roleID = agent.getRoles().getId();
                                }

                                List<UserRole> users = new UserRoleHibernateHelper().fetchAll2(agent.getRoles().getId(), agent.getMdas().getId());
                                for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                                    UserRole userRole = (UserRole) iterator2.next();

                                    recipients += userRole.getUsers().getEmail() + (iterator2.hasNext() ? "," : "");
                                    SMS_Recipients += userRole.getUsers().getPhoneno() + (iterator2.hasNext() ? "," : "");
                                }
                            }

                            //Create notification
                            notification = new NotificationsHibernateHelper().fetchObj(roleID, requestTypeID, Utility.FULL_APPROVAL_NOTIFICATION_TYPE);
                            mailBody = notification.getNotificationText().replaceAll("&lt;Link&gt;", Utility.SERVER_PROTOCOL + Utility.SERVER_NAME + ":"
                                    + Utility.SERVER_PORT + Utility.SITE_URL + requestDetails.getItemUrl());

                            //Compose SMS Message                        	
                            SMS_Message = "Hello, the " + requestTypeName + " has just been fully approved by all agents on the platform, thank you.";
                        } else if (!isFullyApproved && nextRoleID != -1) {
                            //PICK THE NEXT GUYS
                            List<UserRole> userRoles = new UserRoleHibernateHelper().fetchAll2(nextRoleID, nextMdaID);
                            for (Iterator iterator2 = userRoles.iterator(); iterator2.hasNext();) {
                                UserRole userRole = (UserRole) iterator2.next();

                                recipients += userRole.getUsers().getEmail() + (iterator2.hasNext() ? "," : "");
                                SMS_Recipients += userRole.getUsers().getPhoneno() + (iterator2.hasNext() ? "," : "");

                                RequestApprovalsHibernateHelper helper2 = new RequestApprovalsHibernateHelper();
                                requestApproval = new RequestApprovals();
                                requestApproval.setId(Integer.parseInt(helper2.getMaxserialNo()) + 1);
                                requestApproval.setDateCreated(Utility.dbDateNow());
                                requestApproval.setRequestDetails(requestDetails);
                                requestApproval.setUsers(userRole.getUsers());
                                requestApproval.setComment("<Auto-Generated-Workflow>");
                                requestApproval.setApprovalStatus(Utility.OPEN_FOR_APPROVAL_STATUS);
                                requestApproval.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);

                                helper2.insert(requestApproval);
                            }

                            //Create notification
                            notification = new NotificationsHibernateHelper().fetchObj(relevantRole, requestTypeID, Utility.APPROVAL_NOTIFICATION_TYPE);
                            mailBody = notification.getNotificationText().replaceAll("&lt;Role&gt;", submissionAgentRole)
                                    .replaceAll("&lt;MDA&gt;", submissionAgentMda)
                                    .replaceAll("&lt;FullName&gt;", submissionAgentMda)
                                    .replaceAll("&lt;Comment&gt;", comment)
                                    .replaceAll("&lt;Link&gt;", Utility.SERVER_PROTOCOL + Utility.SERVER_NAME + ":"
                                            + Utility.SERVER_PORT + Utility.SITE_URL + requestDetails.getItemUrl());

                            //Compose SMS Message                        	
                            SMS_Message = "Hello, please open the SIFMIS Budget portal to review/approve the " + requestTypeName + ", thank you.";
                        }
                    } else if (requestApproval.getApprovalStatus() == Utility.REJECTION_STATUS) {
                        if (requestTypeID == Utility.MYBF_REQUEST_TYPE) {
                            //Re-Open Budget for editing
                            BudgetVersionStatusHibernateHelper helper3 = new BudgetVersionStatusHibernateHelper();
                            BudgetVersionStatus budgetVersion = helper3.fetchObj(requestDetails.getVersionId());
                            budgetVersion.setBudgetStatus(new BudgetStatusHibernateHelper().fetchObj(Utility.UNLOCKED_BUDGET_VERSION));
                            helper3.update(budgetVersion);
                        }

                        //Get all recipients below current rejecter   	
                        for (Iterator iterator = requestAgents.iterator(); iterator.hasNext();) {
                            RequestAgents agent = (RequestAgents) iterator.next();

                            if (relevantRole == agent.getRoles().getId() && mdaID == agent.getMdas().getId()) {
                                submissionAgentRole = agent.getRoles().getName();
                                submissionAgentMda = agent.getMdas().getName();
                                break;
                            }

                            List<UserRole> users = new UserRoleHibernateHelper().fetchAll2(agent.getRoles().getId(), agent.getMdas().getId());
                            for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                                UserRole userRole = (UserRole) iterator2.next();
                                recipients += userRole.getUsers().getEmail() + (iterator2.hasNext() ? "," : "");
                                SMS_Recipients += userRole.getUsers().getPhoneno() + (iterator2.hasNext() ? "," : "");
                            }
                        }

                        //Create notification
                        notification = new NotificationsHibernateHelper().fetchObj(relevantRole, requestTypeID, Utility.REJECTION_NOTIFICATION_TYPE);
                        mailBody = notification.getNotificationText().replaceAll("&lt;Role&gt;", submissionAgentRole)
                                .replaceAll("&lt;MDA&gt;", submissionAgentMda)
                                .replaceAll("&lt;FullName&gt;", submissionAgentMda)
                                .replaceAll("&lt;Comment&gt;", comment)
                                .replaceAll("&lt;Link&gt;", Utility.SERVER_PROTOCOL + Utility.SERVER_NAME + ":"
                                        + Utility.SERVER_PORT + Utility.SITE_URL + requestDetails.getItemUrl());

                        //Compose SMS Message                        	
                        SMS_Message = "Hello, please open the SIFMIS Budget portal to review and amend the " + requestTypeName + ", thank you.";
                    }

                    //SEND EMAIL NOTIFICATION
                    if (notification != null) {
                        try {
                            //Send Notification to Affected Users
                            Utility utility = new Utility();
                            utility.setMailServerProperties();
                            utility.createEmailMessage(notification.getNotificationSubject(), mailBody, recipients);
                            utility.sendEmail();

                            //Send SMS as well
                            if (!SMS_Message.equals("") && !SMS_Recipients.equals("")) {
                                //split up phone numbers
                                String[] allSMSRecipients = SMS_Recipients.split(",");

                                Utility.sendSMS(allSMSRecipients, SMS_Message);
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }

            /**
             * Returns integer (workflowStatus): -1 not allowed to view 0 for
             * user not being in line to approve nor a requester 1 for user
             * being able to approve 2 for user being an originator/requester 3
             * for user that has already approved 4 for user that is a requester
             * and fully approved 5 for user that is a requester and fully
             * approved and document generated 6 for user that has already
             * approved and document generated
             */
            if (option.equals(Utility.OPTION_CHECK_WORKFLOW_STATUS)) {
                int requestTypeID = Integer.parseInt(request.getParameter("requestTypeID"));
                int mdaID = Integer.parseInt(request.getParameter("mdaID"));
                int userID = Integer.parseInt(request.getParameter("userID"));

                int workflowStatus = -1;
                int relevantRole = -1;

                String[] allRoles = request.getParameter("roles").split(",");
                for (String role : allRoles) {
                    RequestAgents obj = new RequestAgentsHibernateHelper().fetchObj(Integer.parseInt(role), requestTypeID, mdaID);

                    if (obj != null) {
                        relevantRole = Integer.parseInt(role);
                    }
                }

                if (relevantRole != -1) {
                    workflowStatus = 0;
                    int requestDetailID = 0;

                    List<RequestAgents> requestAgents = new RequestAgentsHibernateHelper().fetchAll2(requestTypeID);
                    for (Iterator iterator = requestAgents.iterator(); iterator.hasNext();) {
                        RequestAgents agent = (RequestAgents) iterator.next();

                        if (agent.getRoles().getId() == relevantRole && agent.getMdas().getId() == mdaID) {
                            requestApproval = new RequestApprovalsHibernateHelper().fetchByUser(userID, requestTypeID);

                            if (requestApproval != null) {
                                if (requestApproval.getUsers().getId() == userID) {
                                    workflowStatus = 1;
                                }

                                if (requestApproval.getApprovalStatus() == Utility.APPROVAL_STATUS
                                        || requestApproval.getApprovalStatus() == Utility.REJECTION_STATUS) {
                                    workflowStatus = 3;

                                    RequestDocuments document = new RequestDocumentsHibernateHelper().fetchLatestDocument(requestApproval.getRequestDetails().getId());
                                    if (document != null) {
                                        if (document.getDocumentText() != null) {
                                            workflowStatus = 6;
                                        }
                                    }
                                }

                                requestDetailID = requestApproval.getRequestDetails().getId();
                            } else if (agent.getRequestAgentTypes().getId() == 1) {
                                workflowStatus = 2;

                                requestApproval = new RequestApprovalsHibernateHelper().fetchByRequestType(requestTypeID);

                                if (requestApproval.getRequestDetails().isFullyApproved()) {
                                    workflowStatus = 4;
                                }

                                RequestDocuments document = new RequestDocumentsHibernateHelper().fetchLatestDocument(requestApproval.getRequestDetails().getId());
                                if (document != null) {
                                    if (document.getDocumentText() != null) {
                                        workflowStatus = 5;
                                    }
                                }

                                requestDetailID = requestApproval.getRequestDetails().getId();
                            }

                            break;
                        }
                    }

                    resp = "[[" + workflowStatus + "," + requestDetailID + "]]";
                } else {
                    resp = "[[" + workflowStatus + "]]";
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    requestApproval = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    requestApproval.setApprovalStatus(Integer.parseInt(request.getParameter("approvalStatus")));

                    resp = helper.update(requestApproval);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    requestApproval = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(requestApproval);
                }
            }

            if (option.equals(Utility.OPTION_SELECT)) {
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll(Integer.parseInt(request.getParameter("requestDetailID")));
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
            //ex.printStackTrace();
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
        return "Request Approval";
    }
}
