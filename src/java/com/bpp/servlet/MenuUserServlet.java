/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MenuUser;
import com.bpp.hibernate.MenuUserHibernateHelper;
import com.bpp.hibernate.SystemMenu;
import com.bpp.hibernate.SystemMenuHibernateHelper;
import com.bpp.hibernate.Users;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;

/**
 * Budget Phases Servlet class
 *
 * @author Lekan
 * @since 18/6/2017
 */
public class MenuUserServlet extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            MenuUserHibernateHelper helper = new MenuUserHibernateHelper();
            UsersHibernateHelper usersHelper = new UsersHibernateHelper();
            SystemMenuHibernateHelper systemMenusHelper = new SystemMenuHibernateHelper();
            MenuUser menuUser = new MenuUser();
            SystemMenu systemMenu = new SystemMenu();
            Users users = new Users();
            
            Utility utility = new Utility();

            String option;
            String id;
            String user_id;
            String menu_id;
            String dateCreated;
            String orgId;
            String accessible;
            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            user_id = request.getParameter("user_id");
            if (user_id == null) {
                user_id = "";
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

            accessible = request.getParameter("accessible");
            if (accessible == null) {
                accessible = "false";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    menuUser = new MenuUser();
                    users = usersHelper.exists(Integer.parseInt(user_id));
                    systemMenu = systemMenusHelper.exists(Integer.parseInt(menu_id));
                    menuUser.setId(Integer.parseInt(helper.getMaxSerialNo("Menu_User")) + 1);
                    systemMenu.setDateCreated(utility.dbDateNow());
                    menuUser.setUsers(users);
                    menuUser.setSystemMenu(systemMenu);
                    menuUser.setOrgId(1);
                    menuUser.setAccessible(accessible);
                    resp = helper.insert(menuUser);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    users = usersHelper.exists(Integer.parseInt(user_id));
                    menuUser = helper.getObj(users.getId()+"", systemMenu.getId()+"");
                    menuUser.setUsers(users);
                    menuUser.setSystemMenu(systemMenu);
                    menuUser.setAccessible(accessible);
                    resp = helper.update(menuUser);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    //systemMenu = helper.exists(Integer.parseInt(id));
                    menuUser = helper.getObj(users.getId()+"", systemMenu.getId()+"");
                    resp = helper.delete(menuUser);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchUser(user_id);
                createCookie(response, "myrolecookie", resp);
                resp = helper.fetchAll(user_id);
            }
            
            if (option.equals(Utility.OPTION_UPDATE_ALL_MENUS_STATUS)) {
                resp = helper.updateAllMenuStatus(user_id, accessible);
            }
            
            if (option.equals(Utility.OPTION_UPDATE_MENU_STATUS)) {
                resp = helper.updateMenuStatus(id, accessible);
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
                resp = helper.updateMenuRoleStatus(user_id, menu_id, accessible);
            }
            
            if (option.equals(Utility.OPTION_UPDATE_USER_MENUS)) {
                resp = helper.insertMenuUser(user_id);
                Gson gson = new Gson();
                resp = gson.toJson(resp);
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchOne(id);
            }
            out.println(resp);
        } finally {
            out.close();
        }
    }

    public synchronized String readCookie(HttpServletRequest request, String cookiename) {
        Cookie[] cookies = request.getCookies();
        String resp = "";
        for (Cookie c : cookies) {
            if (c.getName().equals(cookiename)) {
                resp = c.getValue();
            }
        }
        return resp;
    }

//    public synchronized String readCookieValues(HttpServletRequest request, String cookiename) {
//        Cookie[] cookies = request.getCookies();
//        String resp = "";
//        for (Cookie c : cookies) {
//            if (c.getName().equals(cookiename)) {
//                resp = "value: " + c.getValue() + "   name: " + c.getName() + "   domain: " + c.getDomain() + "   path: " + c.getPath();
//            }
//        }
//        return resp;
//    }
    public synchronized void createCookie(HttpServletResponse response, String cookiename, String cookievalue) {
        Cookie c = new Cookie(cookiename, cookievalue);
        c.setMaxAge(24 * 60 * 60);
        response.addCookie(c);
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
        } catch (JSONException ex) {
            Logger.getLogger(MenuUserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
