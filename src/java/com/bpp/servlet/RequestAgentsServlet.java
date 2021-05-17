/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
import com.bpp.hibernate.Notifications;
import com.bpp.hibernate.NotificationsHibernateHelper;
import com.bpp.hibernate.RequestAgentTypes;
import com.bpp.hibernate.RequestAgentTypesHibernateHelper;
import com.bpp.hibernate.RequestAgents;
import com.bpp.hibernate.RequestAgentsHibernateHelper;
import com.bpp.hibernate.RequestTypes;
import com.bpp.hibernate.RequestTypesHibernateHelper;
import com.bpp.hibernate.RolesHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.Roles;
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
 * Request Agents Servlet class
 * 
 * @author Lekan
 * @since 20/7/2017
 */
public class RequestAgentsServlet extends HttpServlet {

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
            
        	RequestAgentsHibernateHelper helper = new RequestAgentsHibernateHelper();
            RequestAgents requestAgents = new RequestAgents();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    requestAgents.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    requestAgents.setDateCreated(Utility.dbDateNow());
                    
                    Roles role = new RolesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("roleID")));
                    requestAgents.setRoles(role);
                    
                    RequestTypes requestType = new RequestTypesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("requestTypeID")));
                    requestAgents.setRequestTypes(requestType);
                    
                    RequestAgentTypes agentType = new RequestAgentTypesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("requestAgentTypeID")));
                    requestAgents.setRequestAgentTypes(agentType);
                    
                    Mdas mda = new MdaHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("mdaID")));
                    requestAgents.setMdas(mda);
                    
                    requestAgents.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(requestAgents);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    requestAgents = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    
                    RequestAgentTypes agentTypes = new RequestAgentTypesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("requestAgentTypeID")));
                    requestAgents.setRequestAgentTypes(agentTypes);
                    
                    resp = helper.update(requestAgents);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	requestAgents = helper.fetchObj(Integer.parseInt(request.getParameter("id")));                    
                    
                    //delete notifications for agent role
                    NotificationsHibernateHelper helper2 = new NotificationsHibernateHelper();
                    List<Notifications> notifications = helper2.fetchAll2(requestAgents.getRoles().getId(),
                    											  		  requestAgents.getRequestTypes().getId());
                    
                    for (Iterator iterator = notifications.iterator(); iterator.hasNext();) {
	            		Notifications notification = (Notifications) iterator.next();
	            		helper2.delete(notification);
                    }
                    
                    resp = helper.delete(requestAgents);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT))
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            
            if (option.equals(Utility.OPTION_SELECT_ALL))
                resp = helper.fetchAll(Integer.parseInt(request.getParameter("requestTypeID")));
            
            //System.out.println(resp);
            
            out.println(resp);
        }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        return "Request Agents";
    }// </editor-fold>

}