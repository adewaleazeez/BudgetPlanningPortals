/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
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
public class MdaServlet extends HttpServlet {

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

            MdaHibernateHelper helper = new MdaHibernateHelper();
            SubSectorHibernateHelper subsectorhelper = new SubSectorHibernateHelper();
            Mdas mdas = new Mdas();
            SubSectors subsector = new SubSectors();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String sub_sector_code;
            String dateCreated;
            String orgId;
            String mda_type;
            String mda_weight;
            String administrative_segment;
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

            sub_sector_code = request.getParameter("sub_sector_code");
            if (sub_sector_code == null) {
                sub_sector_code = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "";
            }

            mda_type = request.getParameter("mda_type");
            if (mda_type == null) {
                mda_type = "";
            }

            mda_weight = request.getParameter("mda_weight");
            if (mda_weight == null) {
                mda_weight = "";
            }

            administrative_segment = request.getParameter("administrative_segment");
            if (administrative_segment == null) {
                administrative_segment = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    Gson gson = new Gson();
                    Float weight = Float.parseFloat(helper.sumWeight(0,administrative_segment));
                    if ((weight + Float.parseFloat(mda_weight)) > 100.0) {
                        resp = gson.toJson("invalidweight");
                    } else {
                        //subsector = subsectorhelper.exists(name);
                        mdas = new Mdas();
                        mdas.setId(Integer.parseInt(helper.getMaxSerialNo("MDAs")) + 1);
                        mdas.setDateCreated(utility.dbDateNow());
                        mdas.setName(name);
                        mdas.setOrgId(1);
                        //mdas.setSubsectors(subsector);
                        mdas.setMdaType(mda_type);
                        mdas.setMdaWeight(Double.parseDouble(mda_weight));
                        mdas.setAdministrativeSegment(administrative_segment);
                        mdas.setSubSectorCode(sub_sector_code);
                        resp = helper.insert(mdas);
                        resp = gson.toJson(resp);
                    }
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    Gson gson = new Gson();
                    mdas = helper.exists(Integer.parseInt(id));
                    if (mdas != null) {
                        Float weight = Float.parseFloat(helper.sumWeight(mdas.getId(),administrative_segment));
                        if ((weight + Float.parseFloat(mda_weight)) > 100.0) {
                            resp = gson.toJson("invalidweight");
                        } else {
                            //subsector = subsectorhelper.exists(name);
                            //System.out.println("subsector "+subsector);
                            //System.out.println("administrative_segment "+administrative_segment);
                            
                            mdas.setName(name);
                            //mdas.setSubsectors(subsector);
                            mdas.setMdaType(mda_type);
                            mdas.setMdaWeight(Double.parseDouble(mda_weight));
                            mdas.setAdministrativeSegment(administrative_segment);
                            mdas.setSubSectorCode(sub_sector_code);
                            resp = helper.update(mdas);
                            resp = gson.toJson(resp);
                        }
                    } else {
                        resp = gson.toJson(Utility.ActionResponse.NO_RECORD);
                    }

                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    mdas = helper.exists(Integer.parseInt(id));
                    resp = helper.delete(mdas);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_ALL_BY_MDAS)) {
                resp = helper.fetchAllMDAs();
            }

            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAll(sub_sector_code);
            }

            if (option.equals(Utility.OPTION_SELECT)) {
                resp = helper.fetchNoSector();
            }

            if (option.equals(Utility.OPTION_UPDATE_ALL)) {
                resp = helper.updateSubSectorCode(id, sub_sector_code);
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
            Logger.getLogger(MdaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MdaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
