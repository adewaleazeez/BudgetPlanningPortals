/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MtssCosting;
import com.bpp.hibernate.MtssCostingHibernateHelper;
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
//import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class MtssCostingServlet extends HttpServlet {

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
            

            MtssCostingHibernateHelper helper = new MtssCostingHibernateHelper();
            MtssCosting mtssCosting = new MtssCosting();
            Utility utility = new Utility();

            String option;
            String id;
            String admin_segment;
            String programme_segment;
            String economic_segment;
            String functional_segment;
            String fund_segment;
            String geo_segment;
            String dept_id;
            String budget_year_id;
            String budget_amount;
            String dateCreated;
            String orgId;
            String cost;
            String quantity;
            
            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            admin_segment = request.getParameter("admin_segment");
            if (admin_segment == null) {
                admin_segment = "";
            }

            programme_segment = request.getParameter("programme_segment");
            if (programme_segment == null) {
                programme_segment = "";
            }

            economic_segment = request.getParameter("economic_segment");
            if (economic_segment == null) {
                economic_segment = "";
            }

            functional_segment = request.getParameter("functional_segment");
            if (functional_segment == null) {
                functional_segment = "";
            }

            fund_segment = request.getParameter("fund_segment");
            if (fund_segment == null) {
                fund_segment = "";
            }

            geo_segment = request.getParameter("geo_segment");
            if (geo_segment == null) {
                geo_segment = "";
            }

            dept_id = request.getParameter("dept_id");
            if (dept_id == null) {
                dept_id = "";
            }
 
            budget_year_id = request.getParameter("budget_year_id");
            if (budget_year_id == null) {
                budget_year_id = "";
            }

            budget_amount = request.getParameter("budget_amount");
            if (budget_amount == null || budget_amount.equals("")) {
                budget_amount = "0";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "1";
            }
            
            cost = request.getParameter("cost");
            if (cost == null || cost.equals("")) {
                cost = "0";
            }

            quantity = request.getParameter("quantity");
            if (quantity == null || quantity.equals("")) {
                quantity = "0";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                if(!budget_amount.equals("0")){
                    synchronized (this) {
                        mtssCosting = new MtssCosting();
                        mtssCosting.setId(Integer.parseInt(helper.getMaxSerialNo("MTSS_Costing")) + 1);
                        mtssCosting.setAdminSegment(admin_segment);
                        String new_programme_segment = "";
                        if(programme_segment.length()==14){
                            new_programme_segment = programme_segment;
                        }else{
                            new_programme_segment = helper.getMaxSerialNo("MTSS_Costing", programme_segment);

                        }
                        mtssCosting.setProgrammeSegment(new_programme_segment);
                        mtssCosting.setEconomicSegment(economic_segment);
                        mtssCosting.setFunctionalSegment(functional_segment);
                        mtssCosting.setFundSegment(fund_segment);
                        mtssCosting.setGeoSegment(geo_segment);
                        mtssCosting.setDeptId(dept_id);
                        mtssCosting.setBudgetYearId(Integer.parseInt(budget_year_id));
                        mtssCosting.setBudgetAmount(Double.parseDouble(budget_amount));
                        mtssCosting.setDateCreated(utility.dbDateNow());
                        mtssCosting.setOrgId(1);
                        mtssCosting.setCost(Double.parseDouble(cost));
                        mtssCosting.setQuantity(Double.parseDouble(quantity));
                        resp = helper.insert(mtssCosting);

                    }
                }else{
                    String new_programme_segment = "";
                    if(programme_segment.length()==14){
                        new_programme_segment = programme_segment;
                    }else{
                        new_programme_segment = helper.getMaxSerialNo("MTSS_Costing", programme_segment);

                    }
                    resp = Utility.ActionResponse.INSERTED.toString()+"_"+new_programme_segment;
                }
                Gson gson = new Gson();
                resp = gson.toJson(resp);
                
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    //System.out.println("id "+id+"   budget_year_id "+budget_year_id+"   quantity "+quantity+"   budget_amount "+budget_amount);
                    mtssCosting = helper.exists(Integer.parseInt(id));
                    mtssCosting.setAdminSegment(admin_segment);
                    mtssCosting.setProgrammeSegment(programme_segment);
                    mtssCosting.setEconomicSegment(economic_segment);
                    mtssCosting.setFunctionalSegment(functional_segment);
                    mtssCosting.setFundSegment(fund_segment);
                    mtssCosting.setGeoSegment(geo_segment);
                    mtssCosting.setDeptId(dept_id);
                    mtssCosting.setBudgetYearId(Integer.parseInt(budget_year_id));
                    mtssCosting.setBudgetAmount(Double.parseDouble(budget_amount));
                    mtssCosting.setCost(Double.parseDouble(cost));
                    mtssCosting.setQuantity(Double.parseDouble(quantity));
                    resp = helper.update(mtssCosting);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    String [] id_arr = id.split("_");
                    mtssCosting = helper.exists(Integer.parseInt(id_arr[0]));
                    resp = helper.delete(mtssCosting);
                    if(resp.equals(Utility.ActionResponse.DELETED.toString())){
                        mtssCosting = helper.exists(Integer.parseInt(id_arr[1]));
                        resp = helper.delete(mtssCosting);
                        if(resp.equals(Utility.ActionResponse.DELETED.toString())){
                        mtssCosting = helper.exists(Integer.parseInt(id_arr[2]));
                            resp = helper.delete(mtssCosting);
                            if(resp.equals(Utility.ActionResponse.DELETED.toString())){
                                mtssCosting = helper.exists(Integer.parseInt(id_arr[3]));
                                resp = helper.delete(mtssCosting);
                            }
                        }
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_NAME)) {
                String userid = session.getAttribute("userid").toString();
                String userrole = session.getAttribute("userrole").toString();
                resp = helper.fetchByUserRole(userid, userrole);
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
                resp = helper.fetchBudgetHeads();
            }
            
            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchById(id);
            }
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                if(!admin_segment.equals("")){
                    resp = helper.fetchAdminSegmment(id, admin_segment);
                }
                if(!economic_segment.equals("")){
                    resp = helper.fetchEconSegmment(economic_segment);
                }
                if(!programme_segment.equals("")){
                    resp = helper.fetchProgSegmment();
                }
                if(!functional_segment.equals("")){
                    resp = helper.fetchFuncSegmment();
                }
                if(!fund_segment.equals("")){
                    resp = helper.fetchFundSegmment();
                }
                if(!geo_segment.equals("")){
                    resp = helper.fetchGeoSegmment();
                }
                if(!dept_id.equals("")){
                    resp = helper.fetchDept(dept_id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                //System.out.println("admin_segment "+admin_segment+"     economic_segment "+economic_segment+"     programme_segment "+programme_segment+"     budget_year_id "+budget_year_id);
                if(!economic_segment.equals("")){
                    resp = helper.fetchAll(admin_segment, economic_segment, budget_year_id);
                }
                if(!programme_segment.equals("")){
                    economic_segment = programme_segment;
                    resp = helper.fetchAllProgrammes(admin_segment, economic_segment, budget_year_id);
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
            Logger.getLogger(MtssCostingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MtssCostingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
