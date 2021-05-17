/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.NotificationTypes;
import com.bpp.hibernate.NotificationTypesHibernateHelper;
import com.bpp.hibernate.Notifications;
import com.bpp.hibernate.NotificationsHibernateHelper;
import com.bpp.hibernate.RequestTypes;
import com.bpp.hibernate.RequestTypesHibernateHelper;
import com.bpp.hibernate.RolesHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.Roles;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Notification Type Servlet class
 * 
 * @author Lekan
 * @since 31/7/2017
 */
public class NotificationsServlet extends HttpServlet {

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
            
        	NotificationsHibernateHelper helper = new NotificationsHibernateHelper();
            Notifications notification = new Notifications();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    notification.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    notification.setDateCreated(Utility.dbDateNow());
                    notification.setNotificationSubject(request.getParameter("subject"));
                    notification.setNotificationText(request.getParameter("text"));
                    
                    NotificationTypes notificationType = new NotificationTypesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("notificationTypeID")));
                    notification.setNotificationTypes(notificationType);
                    
                    RequestTypes requestType = new RequestTypesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("requestTypeID")));
                    notification.setRequestTypes(requestType);
                    
                    Roles role = new RolesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("roleID")));
                    notification.setRoleId(role.getId());
                                        
                    notification.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(notification);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    notification = helper.fetchObj(Integer.parseInt(request.getParameter("roleID")),
						                    	   Integer.parseInt(request.getParameter("requestTypeID")),
						                    	   Integer.parseInt(request.getParameter("notificationTypeID")));
                    
                    notification.setDateCreated(Utility.dbDateNow());
                    notification.setNotificationSubject(request.getParameter("subject"));
                    notification.setNotificationText(request.getParameter("text"));
                    
                    resp = helper.update(notification);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	notification = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(notification);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
            	if(request.getParameter("roleID") != null)
            		resp = helper.fetch(Integer.parseInt(request.getParameter("roleID")), 
            							Integer.parseInt(request.getParameter("requestTypeID")),
            							Integer.parseInt(request.getParameter("notificationTypeID")));
            	else
            		resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL))
                resp = helper.fetchAll(Integer.parseInt(request.getParameter("roleID")), Integer.parseInt(request.getParameter("requestTypeID")));
            
            out.println(resp);
        }
        finally {
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
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Notification Types";
    }
}
