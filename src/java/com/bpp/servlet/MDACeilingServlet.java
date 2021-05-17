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
import com.bpp.hibernate.MtssMdaCeiling;
import com.bpp.hibernate.MtssMDACeilingHibernateHelper;
import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
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
public class MDACeilingServlet extends HttpServlet {

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
            
            MtssMDACeilingHibernateHelper helper = new MtssMDACeilingHibernateHelper();
            MdaHibernateHelper mdahelper = new MdaHibernateHelper();
            BudgetTypeComponentsHibernateHelper budgettypecomponenthelper = new BudgetTypeComponentsHibernateHelper();
            BudgetYearHibernateHelper budgetyearhelper = new BudgetYearHibernateHelper();
            MtssMdaCeiling mtssmdaceiling = new MtssMdaCeiling();
            Mdas mdas = new Mdas();
            BudgetYears budgetyears = new BudgetYears();
            BudgetTypeComponents bdgettypecomponents = new BudgetTypeComponents();
            Utility utility = new Utility();
            
            String option;
            String id;
            String mda_id;
            String sub_sector_id;
            String mda_weight;
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

            mda_id = request.getParameter("mda_id");
            if (mda_id == null) {
                mda_id = "";
            }

            sub_sector_id = request.getParameter("sub_sector_id");
            if (sub_sector_id == null) {
                sub_sector_id = "";
            }

            mda_weight = request.getParameter("mda_weight");
            if (mda_weight == null) {
                mda_weight = "";
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
                    //mdas = mdahelper.fetchObj(Integer.parseInt(mda_id));
                    bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                    budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                    mtssmdaceiling = helper.fetchObjById(Integer.parseInt(sub_sector_id), Integer.parseInt(mda_id), budgetyears.getId(), bdgettypecomponents.getId());
//System.out.println(mtssmdaceiling+"  I resp::: "+resp+"  I option::: "+option);
                    if(mtssmdaceiling == null){
                        mtssmdaceiling = new MtssMdaCeiling();
                        mtssmdaceiling.setId(Integer.parseInt(helper.getMaxSerialNo("MTSS_MDA_Ceiling")) + 1);
                        mtssmdaceiling.setMdaWeight(Double.parseDouble(mda_weight));
                        mtssmdaceiling.setBudgetTypeComponents(bdgettypecomponents);
                        mtssmdaceiling.setMdaId(Integer.parseInt(mda_id));
                        mtssmdaceiling.setBudgetYears(budgetyears);
                        mtssmdaceiling.setDateCreated(utility.dbDateNow());   
                        mtssmdaceiling.setTotalAmount(Double.parseDouble(total_amount));
                        mtssmdaceiling.setOrgId(1);
                        mdas.setOrgId(1);
                        resp = helper.insert(mtssmdaceiling);
                        //Gson gson = new Gson();
                        //resp = gson.toJson(resp);
//System.out.println(total_amount+"  U resp::: "+mtssmdaceiling.getTotalAmount());
                    }else{
                        option = Utility.OPTION_UPDATE;
                    }

                }
           }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    //mdas = mdahelper.fetchObj(Integer.parseInt(mda_id));
                    bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                    budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                    mtssmdaceiling = helper.fetchObjById(Integer.parseInt(sub_sector_id), Integer.parseInt(mda_id), budgetyears.getId(), bdgettypecomponents.getId());
//System.out.println(mtssmdaceiling+"  U resp::: "+resp+"  U option::: "+option);
                    if(mtssmdaceiling != null){
                        //mdas = mdahelper.fetchObj(Integer.parseInt(mda_id));
                        bdgettypecomponents = budgettypecomponenthelper.fetchObj(Integer.parseInt(budget_type_component_id));
                        budgetyears = budgetyearhelper.fetchObj(Integer.parseInt(budget_year_id));
                        mtssmdaceiling.setMdaWeight(Double.parseDouble(mda_weight));
                        mtssmdaceiling.setBudgetTypeComponents(bdgettypecomponents);
                        mtssmdaceiling.setMdaId(Integer.parseInt(mda_id));
                        mtssmdaceiling.setTotalAmount(Double.parseDouble(total_amount));
                        resp = helper.update(mtssmdaceiling);
                        //Gson gson = new Gson();
                        //resp = gson.toJson(resp);
//System.out.println(total_amount+"  Update::: "+mtssmdaceiling.getTotalAmount()+"  U resp::: "+resp);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    mtssmdaceiling = helper.fetchObjById(Integer.parseInt(sub_sector_id), Integer.parseInt(mda_id), budgetyears.getId(), bdgettypecomponents.getId());
                    resp = helper.delete(mtssmdaceiling);
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_INSERT_SUPPLEMENTARY_RECORD)) {
                budgetyears = budgetyearhelper.fetchObjByYear(budget_year_id);
                String date_created = utility.convertToDBDate(utility.dbDateNow());
                String org_id = "1";
                id = helper.exists_Supplementary(mda_id, sub_sector_id, budget_type_component_id, budgetyears.getId()+"");
                //System.out.println("id   ::::::::::"+id);
                if(id.equals("0")){
                    resp = helper.insertSupplementaryRecord(id, mda_id, sub_sector_id, budget_type_component_id, total_amount, budgetyears.getId()+"", date_created, org_id, mda_weight);
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
                id = helper.exists_Contingency(mda_id, sub_sector_id, budget_type_component_id, budgetyears.getId()+"");
                //System.out.println("id   ::::::::::"+id);
                if(id.equals("0")){
                    resp = helper.insertContingencyRecord(id, mda_id, sub_sector_id, budget_type_component_id, total_amount, budgetyears.getId()+"", date_created, org_id, mda_weight);
                }else{
                    option = Utility.OPTION_UPDATE_CONTINGENCY_RECORD;
                }
            }
            if (option.equals(Utility.OPTION_UPDATE_CONTINGENCY_RECORD)) {
                resp = helper.updateContingencyRecord(id, total_amount);
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_NAME)) {
                if(id.equals("")){
                    String userid = session.getAttribute("userid").toString();
                    String userrole = session.getAttribute("userrole").toString();
                    resp = mdahelper.fetchByUserRole(userid, userrole);
                }else{
                    resp = mdahelper.fetchBySubSectorCode(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchHeaders();
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                helper.createMDAInitialValues(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                resp = helper.fetchDatalist(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL_SUPPLEMENTARY_RECORD)) {
                helper.createMDASupplementaryInitialValues(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                resp = helper.fetchSupplementaryDatalist(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL_CONTINGENCY_RECORD)) {
                helper.createMDAContingencyInitialValues(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id));
                resp = helper.fetchContingencyDatalist(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }
            
            if (option.equals(Utility.OPTION_RESET_MENU)) {
                resp = helper.fetchYearMenu(Integer.parseInt(Utility.MYBF_REQUEST_ID), budgetyearhelper.fetchObjByYear(budget_year_id).getId());
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
                resp = budgetyearhelper.fetchCurrentYear();
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAllEnvelope(budget_year_id, sub_sector_id);
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID_SUPPLEMENTARY_RECORD)) {
                resp = helper.fetchAllSupplementaryEnvelope(budget_year_id, sub_sector_id);
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID_CONTINGENCY_RECORD)) {
                resp = helper.fetchAllContingencyEnvelope(budget_year_id, sub_sector_id);
            }
            
            if (option.equals(Utility.OPTION_DISTRIBUTE_ENVELOPES)) {
                resp = helper.distributeEnvelopes(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }
            
            if (option.equals(Utility.OPTION_DISTRIBUTE_ENVELOPES_SUPPLEMENTARY_RECORD)) {
                resp = helper.distributeMDASupplementaryEnvelopes(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }
            
            if (option.equals(Utility.OPTION_DISTRIBUTE_ENVELOPES_CONTINGENCY_RECORD)) {
                resp = helper.distributeMDAContingencyEnvelopes(Integer.parseInt(Utility.MYBF_REQUEST_ID), Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }
            
            if (option.equals(Utility.OPTION_DELETE_BY_ID)) {
                resp = helper.deleteAllForms(Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }

            if (option.equals(Utility.OPTION_DELETE_BY_ID_SUPPLEMENTARY_RECORD)) {
                resp = helper.deleteAllSupplementaryForms(Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
            }

            if (option.equals(Utility.OPTION_DELETE_BY_ID_CONTINGENCY_RECORD)) {
                resp = helper.deleteAllContingencyForms(Integer.parseInt(budget_year_id), Integer.parseInt(sub_sector_id));
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
            Logger.getLogger(MDACeilingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MDACeilingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
