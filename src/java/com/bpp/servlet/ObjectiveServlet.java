/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.ObjectiveHibernateHelper;
import com.bpp.hibernate.Objectives;
import com.bpp.hibernate.SubSectorHibernateHelper;
import com.bpp.hibernate.SubSectors;
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
public class ObjectiveServlet extends HttpServlet {

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

            ObjectiveHibernateHelper helper = new ObjectiveHibernateHelper();
            SubSectorHibernateHelper subsectorhelper = new SubSectorHibernateHelper();
            Objectives objectives = new Objectives();
            SubSectors subsector = new SubSectors();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String programme;
            String dateCreated;
            String orgId;
            String code;
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

            programme = request.getParameter("programme");
            if (programme == null) {
                programme = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "";
            }

            code = request.getParameter("code");
            if (code == null) {
                code = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    Gson gson = new Gson();
                    objectives = new Objectives();
                    objectives.setId(Integer.parseInt(helper.getMaxSerialNo("Objectives")) + 1);
                    objectives.setDatecreated(utility.dbDateNow());
                    objectives.setName(name);
                    objectives.setOrgId(1);
                    objectives.setCode(code);
                    objectives.setProgramme(Integer.parseInt(programme));
                    resp = helper.insert(objectives);
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    Gson gson = new Gson();
                    objectives = helper.exists(Integer.parseInt(id));
                    if (objectives != null) {
                        objectives.setName(name);
                        objectives.setProgramme(Integer.parseInt(programme));
                        objectives.setCode(code);
                        resp = helper.update(objectives);
                        resp = gson.toJson(resp);
                    } else {
                        resp = gson.toJson(Utility.ActionResponse.NO_RECORD);
                    }

                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    objectives = helper.exists(Integer.parseInt(id));
                    resp = helper.delete(objectives);
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                if(programme.equals("")){
                    resp = helper.fetchAll();
                }else{
                    resp = helper.fetchAll(programme);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAll(programme);
            }

            if (option.equals(Utility.OPTION_UPDATE_ALL)) {
                resp = helper.updateSubSectorCode(id, programme);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ObjectiveServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ObjectiveServlet.class.getName()).log(Level.SEVERE, null, ex);
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
