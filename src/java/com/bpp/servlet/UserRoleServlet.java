/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.RolesHibernateHelper;
import com.bpp.hibernate.Roles;
import com.bpp.hibernate.UserRole;
import com.bpp.hibernate.UserRoleHibernateHelper;
import com.bpp.hibernate.Users;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserRoleServlet extends HttpServlet {

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

            UserRoleHibernateHelper helper = new UserRoleHibernateHelper();
            UserRole userRole = new UserRole();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    userRole.setId(Integer.parseInt(helper.getMaxSerialNo("User_Role")) + 1);
                    userRole.setDateCreated(Utility.dbDateNow());
                    
                    Users user = new UsersHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("userID")));
                    userRole.setUsers(user);
                    
                    Roles role = new RolesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("roleID")));
                    userRole.setRoles(role);
                    
                    userRole.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(userRole);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    userRole = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    
                    Users user = new UsersHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("userID")));
                    userRole.setUsers(user);
                    
                    Roles role = new RolesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("roleID")));
                    userRole.setRoles(role);
                    
                    resp = helper.update(userRole);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	userRole = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(userRole);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT)){
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
            	if(request.getParameter("roleID") != null && request.getParameter("mdaID") != null)
            		resp = helper.fetchAllById(Integer.parseInt(request.getParameter("roleID")), Integer.parseInt(request.getParameter("mdaID")));
            	else
            		resp = helper.fetchAll();
            }
            
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserRoleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserRoleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
