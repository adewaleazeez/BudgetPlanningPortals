/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MenuCategory;
import com.bpp.hibernate.MenuCategoryHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.google.gson.Gson;
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
public class MenuCategoryServlet extends HttpServlet {

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
            
            MenuCategoryHibernateHelper helper = new MenuCategoryHibernateHelper();
            MenuCategory menuCategory = new MenuCategory();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String rank;
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

            name = request.getParameter("name");
            if (name == null) {
                name = "";
            }

            rank = request.getParameter("rank");
            if (rank == null) {
                rank = "";
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
                    menuCategory = new MenuCategory();
                    menuCategory.setId(Integer.parseInt(helper.getMaxSerialNo("Menu_Category")) + 1);
                    menuCategory.setDateCreated(utility.dbDateNow());
                    menuCategory.setName(name);
                    menuCategory.setOrgId(1);
                    resp = helper.insert(menuCategory);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    menuCategory = helper.fetchOneById(id);
                    if (menuCategory != null) {
                        menuCategory.setName(name);
                        resp = helper.update(menuCategory);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    menuCategory = helper.fetchOneById(id);
                    if (menuCategory != null) {
                        resp = helper.delete(menuCategory);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);

                }
            }
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                synchronized (this) {
                    resp = helper.fetchOne(id);
                    //Gson gson = new Gson();
                    //resp = gson.toJson(menuCategory);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE_USER_MENUS)) {
                resp = helper.updateRank(id, rank);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MenuCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MenuCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
