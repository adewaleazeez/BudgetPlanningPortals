/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetTypeComponents;
import com.bpp.hibernate.BudgetTypeComponentsHibernateHelper;
import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.BudgetYears;
import com.bpp.hibernate.MtssSectorCeiling;
import com.bpp.hibernate.MtssSectorCeilingHibernateHelper;
import com.bpp.hibernate.SectorHibernateHelper;
import com.bpp.hibernate.Sectors;
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

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class SectorCeilingServlet extends HttpServlet {

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

            MtssSectorCeilingHibernateHelper helper = new MtssSectorCeilingHibernateHelper();
            SectorHibernateHelper sectorhelper = new SectorHibernateHelper();
            BudgetTypeComponentsHibernateHelper budgettypecomponenthelper = new BudgetTypeComponentsHibernateHelper();
            BudgetYearHibernateHelper budgetyearhelper = new BudgetYearHibernateHelper();
            MtssSectorCeiling mtsssectorceiling = new MtssSectorCeiling();
            Sectors sectors = new Sectors();
            BudgetYears budgetyears = new BudgetYears();
            BudgetTypeComponents bdgettypecomponents = new BudgetTypeComponents();
            Utility utility = new Utility();

            String option;
            String id;
            String sector_id;
            String sector_weight;
            String budget_type_component_id;
            String total_amount;
            String budget_year_id;
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

            sector_id = request.getParameter("sector_id");
            if (sector_id == null) {
                sector_id = "";
            }

            sector_weight = request.getParameter("sector_weight");
            if (sector_weight == null) {
                sector_weight = "";
            }

            budget_type_component_id = request.getParameter("budget_type_component_id");
            if (budget_type_component_id == null) {
                budget_type_component_id = "";
            }

            total_amount = request.getParameter("total_amount");
            if (total_amount == null) {
                total_amount = "";
            }

            budget_year_id = request.getParameter("budget_year_id");
            if (budget_year_id == null) {
                budget_year_id = "";
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
                    sectors = sectorhelper.fetchObj(Integer.parseInt(sector_id));
                    bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                    budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                    mtsssectorceiling = helper.fetchObjById(budgetyears.getId(), bdgettypecomponents.getId());
                    if(mtsssectorceiling == null){
                        mtsssectorceiling = new MtssSectorCeiling();
                        mtsssectorceiling.setId(Integer.parseInt(helper.getMaxSerialNo("Mtss_Sector_Ceiling")) + 1);
                        mtsssectorceiling.setSectorWeight(sectors.getSectorWeight());
                        mtsssectorceiling.setBudgetTypeComponents(bdgettypecomponents);
                        mtsssectorceiling.setSectorId(Integer.parseInt(sector_id));
                        mtsssectorceiling.setBudgetYears(budgetyears);
                        mtsssectorceiling.setDateCreated(utility.dbDateNow());
                        mtsssectorceiling.setTotalAmount(Double.parseDouble(total_amount));
                        mtsssectorceiling.setOrgId(1);
                        sectors.setOrgId(1);
                        resp = helper.insert(mtsssectorceiling);
                        Gson gson = new Gson();
                        resp = gson.toJson(resp);
                    }else{
                        option = Utility.OPTION_UPDATE;
                    }

                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    sectors = sectorhelper.fetchObj(Integer.parseInt(sector_id));
                    bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                    budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                    mtsssectorceiling = helper.fetchObjById(budgetyears.getId(), bdgettypecomponents.getId());
                    if(mtsssectorceiling != null){
                        sectors = sectorhelper.fetchObj(Integer.parseInt(sector_id));
                        bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                        budgetyears = budgetyearhelper.fetchObj(Integer.parseInt(budget_year_id));
                        mtsssectorceiling.setSectorWeight(sectors.getSectorWeight());
                        mtsssectorceiling.setTotalAmount(Double.parseDouble(total_amount));
                        resp = helper.update(mtsssectorceiling);
                        Gson gson = new Gson();
                        resp = gson.toJson(resp);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    mtsssectorceiling = helper.fetchObjById(Integer.parseInt(budget_year_id), Integer.parseInt(budget_type_component_id));
                    resp = helper.delete(mtsssectorceiling);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_NAME)) {
                resp = sectorhelper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchHeadrs();
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchDatalist(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
            }
            
            if (option.equals(Utility.OPTION_RESET_MENU)) {
                resp = helper.fetchYearMenu(Integer.parseInt(Utility.MYBF_REQUEST_ID));
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
                resp = budgetyearhelper.fetchCurrentYear();
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAllEnvelope(budget_year_id);
            }
            
            if (option.equals(Utility.OPTION_DISTRIBUTE_ENVELOPES)) {
                resp = helper.distributeEnvelopes(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
            }
            
            if (option.equals(Utility.OPTION_DELETE_BY_ID)) {
                resp = helper.deleteAllForms(Integer.parseInt(budget_year_id));
            }

            out.println(resp);

        } finally {
            out.close();
        }
    }

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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SectorCeilingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SectorCeilingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
