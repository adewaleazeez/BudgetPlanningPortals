/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MenuRole;
import com.bpp.hibernate.MenuRoleHibernateHelper;
import com.bpp.hibernate.MenuUserHibernateHelper;
import com.bpp.hibernate.Roles;
import com.bpp.hibernate.RolesHibernateHelper;
import com.bpp.hibernate.SystemMenu;
import com.bpp.hibernate.SystemMenuHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Budget Phases Servlet class
 *
 * @author Lekan
 * @since 18/6/2017
 */
public class MenuRoleServlet extends HttpServlet {

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

            MenuRoleHibernateHelper helper = new MenuRoleHibernateHelper();
            RolesHibernateHelper rolesHelper = new RolesHibernateHelper();
            SystemMenuHibernateHelper systemMenusHelper = new SystemMenuHibernateHelper();
            MenuUserHibernateHelper menuUserHelper = new MenuUserHibernateHelper();
            MenuRole menuRole = new MenuRole();
            SystemMenu systemMenu = new SystemMenu();
            Roles roles = new Roles();
            Utility utility = new Utility();

            String option;
            String id;
            String role_id;
            String menu_id;
            String dateCreated;
            String orgId;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            role_id = request.getParameter("role_id");
            if (role_id == null) {
                role_id = "";
            }

            menu_id = request.getParameter("menu_id");
            if (menu_id == null) {
                menu_id = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    menuRole = helper.exists(role_id, menu_id);
                    if(menuRole==null){
                        menuRole = new MenuRole();
                        roles = rolesHelper.exists(role_id);
                        systemMenu = systemMenusHelper.exists(Integer.parseInt(menu_id));
                        //menuRole.setId(Integer.parseInt(helper.getMaxSerialNo("Menu_Role")) + 1);
                        //menuRole.setDateCreated(utility.dbDateNow());
                        menuRole.setRoles(roles);
                        menuRole.setSystemMenu(systemMenu);
                        menuRole.setOrgId(1);
                        resp = helper.insert(menuRole);
                    }else{
                        resp = Utility.ActionResponse.RECORD_EXISTS.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    menuRole = helper.getObj(id);
                    menuRole.setRoles(roles);
                    menuRole.setSystemMenu(systemMenu);
                    resp = helper.update(menuRole);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    menuRole = helper.getObj(id);
                    menuUserHelper.deleteById(menuRole.getRoles().getId(), menuRole.getSystemMenu().getId(), 0);
                    resp = helper.delete(menuRole);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAll(role_id);
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchOne(id);
            }
            out.println(resp);
        } finally {
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
        return "Budget Timetable Activities";
    }// </editor-fold>

}
