/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.SectorGoalsHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.SectorGoals;
import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ola
 */
public class SectorGoalsServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));
            
            SectorGoalsHibernateHelper helper = new SectorGoalsHibernateHelper();
            SectorGoals sectorgoals = new SectorGoals();
            Utility utility = new Utility();

            String option;
            String id;
            String sector_goal_code;
            String name;
            String date_created;
            String org_id;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }
            sector_goal_code = request.getParameter("sector_goal_code");
            if (sector_goal_code == null) {
                sector_goal_code = "";
            }

            name = request.getParameter("name");
            if (name == null) {
                name = "";
            }

            date_created = request.getParameter("date_created");
            if (date_created == null) {
                date_created = "";
            }

            org_id = request.getParameter("org_id");
            if (org_id == null) {
                org_id = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    SectorGoals tmp = new SectorGoals();
                    tmp = helper.exists(name, sector_goal_code);

                    if (tmp != null) {
                        resp = Utility.ActionResponse.RECORD_EXISTS.toString();
                    } else {

                        sectorgoals = new SectorGoals();

                        sectorgoals.setId(Integer.parseInt(helper.getMaxSerialNo("SectorGoals")) + 1);
                        sectorgoals.setDateCreated(utility.dbDateNow());
                        sectorgoals.setName(name);
                        sectorgoals.setSectorGoalCode(sector_goal_code);
                        sectorgoals.setOrgId(1);
                        resp = helper.insert(sectorgoals);
                        //Gson gson = new Gson();
                        //resp = gson.toJson(resp);
                    }
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    sectorgoals = helper.exists(name, sector_goal_code);
                    //if(sectorgoals)
                    sectorgoals.setName(name);
                    sectorgoals.setSectorGoalCode(sector_goal_code);
                    resp = helper.update(sectorgoals);
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    sectorgoals = helper.exists(Integer.parseInt(id));
                    resp = helper.delete(sectorgoals);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
