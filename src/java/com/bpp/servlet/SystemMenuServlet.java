/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MenuCategory;
import com.bpp.hibernate.MenuCategoryHibernateHelper;
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
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Budget Phases Servlet class
 *
 * @author Adewale
 * @since 18/6/2017
 */
public class SystemMenuServlet extends HttpServlet {

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

            SystemMenuHibernateHelper helper = new SystemMenuHibernateHelper();
            MenuRoleHibernateHelper menuRoleHelper = new MenuRoleHibernateHelper();
            MenuUserHibernateHelper menuUserHelper = new MenuUserHibernateHelper();
            RolesHibernateHelper rolesHelper = new RolesHibernateHelper();
            MenuCategoryHibernateHelper menuCategoryHelper = new MenuCategoryHibernateHelper();
            SystemMenu systemMenu = new SystemMenu();
            MenuRole menuRole = new MenuRole();
            MenuCategory menuCategory = new MenuCategory();
            Roles roles = new Roles();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String dateCreated;
            String orgId;
            String menu_url;
            String rank;
            String menu_category_id;
            String roleId;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            name = request.getParameter("name");
            if (name == null) {
                name = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "1";
            }

            menu_url = request.getParameter("menu_url");
            if (menu_url == null) {
                menu_url = "";
            }

            rank = request.getParameter("rank");
            if (rank == null) {
                rank = "";
            }

            menu_category_id = request.getParameter("menu_category_id");
            if (menu_category_id == null) {
                menu_category_id = "";
            }

            roleId = request.getParameter("roleId");
            if (roleId == null) {
                roleId = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    menuCategory = menuCategoryHelper.fetchOneById(menu_category_id);
                    systemMenu = new SystemMenu();
                    systemMenu.setId(Integer.parseInt(helper.getMaxSerialNo("System_Menu")) + 1);
                    systemMenu.setDateCreated(utility.dbDateNow());
                    systemMenu.setName(name);
                    systemMenu.setOrgId(1);
                    systemMenu.setMenuCategory(menuCategory);
                    systemMenu.setMenuUrl(menu_url);
                    resp = helper.insert(systemMenu);
                    if (resp.equals(Utility.ActionResponse.INSERTED.toString())) {
                        String[] splited_roles = roleId.split(",");
                        for (String splited_role : splited_roles) {
                            roles = rolesHelper.exists(Integer.parseInt(splited_role));
                            menuRole.setId(Integer.parseInt(menuRoleHelper.getMaxSerialNo("Menu_Role")) + 1);
                            menuRole.setRoles(roles);
                            menuRole.setSystemMenu(systemMenu);
                            menuRole.setDateCreated(utility.dbDateNow());
                            menuRole.setOrgId(Integer.parseInt(orgId));
                            menuRoleHelper.insert(menuRole);
                        }
                    }
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    menuCategory = menuCategoryHelper.fetchOneById(menu_category_id);
                    systemMenu = helper.exists(Integer.parseInt(id));
                    systemMenu.setName(name);
                    systemMenu.setMenuCategory(menuCategory);
                    systemMenu.setMenuUrl(menu_url);
                    resp = helper.update(systemMenu);
                    if (resp.equals(Utility.ActionResponse.UPDATED.toString())) {
                        //System.out.println("roleId "+roleId);
                        //System.out.println("users.getId() "+users.getId());
                        List elements = menuRoleHelper.fetchByMenuId(systemMenu.getId());
                        //System.out.println("elements.size "+elements.size());
                        if (elements.size() > 0) {
                            Iterator i = elements.iterator();
                            while (i.hasNext()) {
                                MenuRole element = (MenuRole) i.next();
                                //System.out.println("roleId-element.getRoles().getId()  "+element.getRoles().getId());
                                if (!roleId.contains(element.getRoles().getId() + "")) {
                                    //System.out.println("roleId userId  "+element.getRoles().getId()+"    "+users.getId());
                                    menuUserHelper.deleteById(element.getRoles().getId(), systemMenu.getId(), 0);
                                }
                            }
                        }
                        menuRoleHelper.deleteAll(systemMenu.getId() + "");
                        String[] splited_roles = roleId.split(",");
                        for (String splited_role : splited_roles) {
                            roles = rolesHelper.exists(Integer.parseInt(splited_role));
                            menuRole.setId(Integer.parseInt(menuRoleHelper.getMaxSerialNo("Menu_Role")) + 1);
                            menuRole.setRoles(roles);
                            menuRole.setSystemMenu(systemMenu);
                            menuRole.setDateCreated(utility.dbDateNow());
                            menuRole.setOrgId(Integer.parseInt(orgId));
                            menuRoleHelper.insert(menuRole);
                        }
                    }
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    //systemMenu = helper.exists(Integer.parseInt(id));
                    systemMenu = helper.exists(Integer.parseInt(id));
                    menuRoleHelper.deleteAll(systemMenu.getId()+"");
                    menuUserHelper.deleteByMenuId(systemMenu.getId());
                    resp = helper.delete(systemMenu);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE_USER_MENUS)) {
                resp = helper.updateRank(id, rank);
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAll(menu_category_id);
            }

            if (option.equals(Utility.OPTION_SELECT_BY_NAME)) {
                synchronized (this) {
                    resp = helper.getObj(name);
                    //Gson gson = new Gson();
                    //resp = gson.toJson(systemMenu);
                }
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
