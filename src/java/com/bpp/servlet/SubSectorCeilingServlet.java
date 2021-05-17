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
import com.bpp.hibernate.MtssSubSectorCeiling;
import com.bpp.hibernate.MtssSubSectorCeilingHibernateHelper;
import com.bpp.hibernate.SubSectorHibernateHelper;
import com.bpp.hibernate.SubSectors;
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
public class SubSectorCeilingServlet extends HttpServlet {

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

            MtssSubSectorCeilingHibernateHelper helper = new MtssSubSectorCeilingHibernateHelper();
            SubSectorHibernateHelper subsectorhelper = new SubSectorHibernateHelper();
            BudgetTypeComponentsHibernateHelper budgettypecomponenthelper = new BudgetTypeComponentsHibernateHelper();
            BudgetYearHibernateHelper budgetyearhelper = new BudgetYearHibernateHelper();
            MtssSubSectorCeiling mtsssubsectorceiling = new MtssSubSectorCeiling();
            SubSectors subsectors = new SubSectors();
            BudgetYears budgetyears = new BudgetYears();
            BudgetTypeComponents bdgettypecomponents = new BudgetTypeComponents();
            Utility utility = new Utility();

            String option;
            String id;
            String sub_sector_id;
            String sub_sector_weight;
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

            sub_sector_id = request.getParameter("sub_sector_id");
            if (sub_sector_id == null) {
                sub_sector_id = "";
            }

            sub_sector_weight = request.getParameter("sub_sector_weight");
            if (sub_sector_weight == null) {
                sub_sector_weight = "";
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
                    //subsectors = subsectorhelper.fetchObj(Integer.parseInt(sub_sector_id));
                    bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                    budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                    mtsssubsectorceiling = helper.fetchObjById(Integer.parseInt(sub_sector_id), budgetyears.getId(), bdgettypecomponents.getId());
                    if(mtsssubsectorceiling == null){
                        mtsssubsectorceiling = new MtssSubSectorCeiling();
                        mtsssubsectorceiling.setId(Integer.parseInt(helper.getMaxSerialNo("Mtss_SubSector_Ceiling")) + 1);
                        mtsssubsectorceiling.setSubSectorWeight(Double.parseDouble(sub_sector_weight));
                        mtsssubsectorceiling.setBudgetTypeComponents(bdgettypecomponents);
                        mtsssubsectorceiling.setSubSectorId(Integer.parseInt(sub_sector_id));
                        mtsssubsectorceiling.setBudgetYears(budgetyears);
                        mtsssubsectorceiling.setDateCreated(utility.dbDateNow());   
                        mtsssubsectorceiling.setTotalAmount(Double.parseDouble(total_amount));
                        mtsssubsectorceiling.setOrgId(1);
                        subsectors.setOrgId(1);
                        resp = helper.insert(mtsssubsectorceiling);
                        //Gson gson = new Gson();
                        //resp = gson.toJson(resp);
                    }else{
                        option = Utility.OPTION_UPDATE;
                    }

                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    subsectors = subsectorhelper.fetchObj(Integer.parseInt(sub_sector_id));
                    bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                    budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                    mtsssubsectorceiling = helper.fetchObjById(Integer.parseInt(sub_sector_id), budgetyears.getId(), bdgettypecomponents.getId());
                    if(mtsssubsectorceiling != null){
                        subsectors = subsectorhelper.fetchObj(Integer.parseInt(sub_sector_id));
                        bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                        budgetyears = budgetyearhelper.fetchObj(Integer.parseInt(budget_year_id));
                        mtsssubsectorceiling.setSubSectorWeight(Double.parseDouble(sub_sector_weight));
                        mtsssubsectorceiling.setBudgetTypeComponents(bdgettypecomponents);
                        mtsssubsectorceiling.setSubSectorId(Integer.parseInt(sub_sector_id));
                        mtsssubsectorceiling.setTotalAmount(Double.parseDouble(total_amount));
                        resp = helper.update(mtsssubsectorceiling);
                        //Gson gson = new Gson();
                        //resp = gson.toJson(resp);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    mtsssubsectorceiling = helper.fetchObjById(Integer.parseInt(sub_sector_id), Integer.parseInt(budget_year_id), Integer.parseInt(budget_type_component_id));
                    resp = helper.delete(mtsssubsectorceiling);
                }
            }
            
            if (option.equals(Utility.OPTION_INSERT_SUPPLEMENTARY_RECORD)) {
                budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                String date_created = utility.convertToDBDate(utility.dbDateNow());
                String org_id = "1";
                id = helper.exists_Supplementary(sub_sector_id, budget_type_component_id, budgetyears.getId()+"");
                //System.out.println("id   ::::::::::"+id);
                if(id.equals("0")){
                    resp = helper.insertSupplementaryRecord(id, sub_sector_id, budget_type_component_id, total_amount, budgetyears.getId()+"", date_created, org_id, sub_sector_weight);
                }else{
                    option = Utility.OPTION_UPDATE_SUPPLEMENTARY_RECORD;
                }
            }
            if (option.equals(Utility.OPTION_UPDATE_SUPPLEMENTARY_RECORD)) {
                resp = helper.updateSupplementaryRecord(id, total_amount);
            }
            
            if (option.equals(Utility.OPTION_INSERT_CONTINGENCY_RECORD)) {
                budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                String date_created = utility.convertToDBDate(utility.dbDateNow());
                String org_id = "1";
                id = helper.exists_Contingency(sub_sector_id, budget_type_component_id, budgetyears.getId()+"");
                //System.out.println("id   ::::::::::"+id);
                if(id.equals("0")){
                    resp = helper.insertContingencyRecord(id, sub_sector_id, budget_type_component_id, total_amount, budgetyears.getId()+"", date_created, org_id, sub_sector_weight);
                }else{
                    option = Utility.OPTION_UPDATE_CONTINGENCY_RECORD;
                }
            }
            if (option.equals(Utility.OPTION_UPDATE_CONTINGENCY_RECORD)) {
                resp = helper.updateContingencyRecord(id, total_amount);
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_NAME)) {
                resp = subsectorhelper.fetchByCode();
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchHeadrs();
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                helper.createSubSectorInitialValues(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                resp = helper.fetchDatalist(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
            }

            if (option.equals(Utility.OPTION_SELECT_ALL_SUPPLEMENTARY_RECORD)) {
                helper.createSubSectorSupplementaryInitialValues(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                resp = helper.fetchSupplementaryDatalist(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                //System.out.println("******OPTION_SELECT_ALL_SUPPLEMENTARY_RECORD: "+resp);
            }
            if (option.equals(Utility.OPTION_SELECT_ALL_CONTINGENCY_RECORD)) {
                helper.createSubSectorContingencyInitialValues(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                resp = helper.fetchContingencyDatalist(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                //System.out.println("******OPTION_SELECT_ALL_CONTINGENCY_RECORD: "+resp);
            }
            
            if (option.equals(Utility.OPTION_RESET_MENU)) {
                resp = helper.fetchYearMenu(Integer.parseInt(Utility.MYBF_REQUEST_ID), budgetyearhelper.fetchObjByYear(budget_year_id).getId()); 
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
                resp = budgetyearhelper.fetchCurrentYear();
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAllEnvelope(Integer.parseInt(budget_year_id));
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID_SUPPLEMENTARY_RECORD)) {
                resp = helper.fetchAllSupplementaryEnvelope(Integer.parseInt(budget_year_id));
                //System.out.println("******OPTION_SELECT_BY_ID_SUPPLEMENTARY_RECORD: "+resp);
            }
            if (option.equals(Utility.OPTION_SELECT_BY_ID_CONTINGENCY_RECORD)) {
                resp = helper.fetchAllContingencyEnvelope(Integer.parseInt(budget_year_id));
            }
            
            if (option.equals(Utility.OPTION_DISTRIBUTE_ENVELOPES)) {
                resp = helper.distributeEnvelopes(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
            }
            
            if (option.equals(Utility.OPTION_DISTRIBUTE_ENVELOPES_SUPPLEMENTARY_RECORD)) {
                resp = helper.distributeSubSectorSupplementaryEnvelopes(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                //System.out.println("******OPTION_DISTRIBUTE_ENVELOPES_SUPPLEMENTARY_RECORD: "+resp);
            }
            if (option.equals(Utility.OPTION_DISTRIBUTE_ENVELOPES_CONTINGENCY_RECORD)) {
                resp = helper.distributeSubSectorContingencyEnvelopes(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
            }
            
            if (option.equals(Utility.OPTION_DELETE_BY_ID)) {
                resp = helper.deleteAllForms(Integer.parseInt(budget_year_id));
            }

            if (option.equals(Utility.OPTION_DELETE_BY_ID_SUPPLEMENTARY_RECORD)) {
                resp = helper.deleteAllSupplementaryForms(Integer.parseInt(budget_year_id));
                //System.out.println("******OPTION_DELETE_BY_ID_SUPPLEMENTARY_RECORD: "+resp);
            }
            if (option.equals(Utility.OPTION_DELETE_BY_ID_CONTINGENCY_RECORD)) {
                resp = helper.deleteAllContingencyForms(Integer.parseInt(budget_year_id));
            }
//System.out.println("******option: "+option);
//System.out.println("******resp: "+resp);
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
            Logger.getLogger(SubSectorCeilingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SubSectorCeilingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
