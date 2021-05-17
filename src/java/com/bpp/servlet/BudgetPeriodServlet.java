/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetPeriod;
import com.bpp.hibernate.BudgetPeriodHibernateHelper;
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
public class BudgetPeriodServlet extends HttpServlet {

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

            BudgetPeriodHibernateHelper helper = new BudgetPeriodHibernateHelper();
            BudgetPeriod budgetPeriod = new BudgetPeriod();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
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
                    budgetPeriod = new BudgetPeriod();
                    budgetPeriod.setId(Integer.parseInt(helper.getMaxSerialNo("Budget_Period")) + 1);
                    budgetPeriod.setDateCreated(utility.dbDateNow());
                    budgetPeriod.setName(name);
                    budgetPeriod.setOrgId(1);
                    resp = helper.insert(budgetPeriod);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    budgetPeriod = helper.fetchOne(id);
                    if (budgetPeriod != null) {
                        budgetPeriod.setName(name);
                        resp = helper.update(budgetPeriod);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    budgetPeriod = helper.fetchOne(id);
                    if (budgetPeriod != null) {
                        resp = helper.delete(budgetPeriod);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                synchronized (this) {
                    budgetPeriod = helper.fetchOne(id);
                    Gson gson = new Gson();
                    resp = gson.toJson(budgetPeriod);
                }
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
            Logger.getLogger(BudgetPeriodServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BudgetPeriodServlet.class.getName()).log(Level.SEVERE, null, ex);
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
