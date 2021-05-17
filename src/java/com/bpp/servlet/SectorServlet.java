/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class SectorServlet extends HttpServlet {

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

            SectorHibernateHelper helper = new SectorHibernateHelper();
            Sectors sectors = new Sectors();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String code;
            String sector_weight;
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

            code = request.getParameter("code");
            if (code == null) {
                code = "";
            }

            sector_weight = request.getParameter("sector_weight");
            if (sector_weight == null) {
                sector_weight = "";
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
                    Gson gson = new Gson();
                    Float weight = Float.parseFloat(helper.sumWeight(0));
                    if((weight + Float.parseFloat(sector_weight))>100.0){
                        resp = gson.toJson("invalidweight");
                    }else{
                        sectors = new Sectors();
                        sectors.setId(Integer.parseInt(helper.getMaxSerialNo("Sectors")) + 1);
                        sectors.setDateCreated(utility.dbDateNow());
                        sectors.setName(name);
                        sectors.setSectorCode(code);
                        sectors.setSectorWeight(Double.parseDouble(sector_weight));
                        sectors.setOrgId(1);
                        resp = helper.insert(sectors);
                        //resp = gson.toJson(resp);
                    }

                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    Gson gson = new Gson();
                    sectors = helper.exists(Integer.parseInt(id));
                    if(sectors != null){
                        Float weight = Float.parseFloat(helper.sumWeight(sectors.getId()));
                        if((weight + Float.parseFloat(sector_weight))>100.0){
                            resp = gson.toJson("invalidweight");
                        }else{
                            sectors.setName(name);
                            sectors.setSectorCode(code);
                            sectors.setSectorWeight(Double.parseDouble(sector_weight));
                            resp = helper.update(sectors);
                            //resp = gson.toJson(resp);
                        }
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();//gson.toJson()
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    sectors = helper.exists(Integer.parseInt(id));
                    resp = helper.delete(sectors);
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetch(Integer.parseInt(id));
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
            Logger.getLogger(SectorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SectorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
