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
import com.bpp.hibernate.SectorHibernateHelper;
import com.bpp.hibernate.Sectors;
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
public class SubSectorServlet extends HttpServlet {

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

            SubSectorHibernateHelper helper = new SubSectorHibernateHelper();
            SectorHibernateHelper sectorhelper = new SectorHibernateHelper();
            MdaHibernateHelper mdahelper = new MdaHibernateHelper();
            SubSectors subsectors = new SubSectors();
            Sectors sector = new Sectors();
            Mdas mda = new Mdas();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String sub_sector_code;
            String dateCreated;
            String orgId;
            String sub_sector_weight;
            String sector_id;

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

            sector_id = request.getParameter("sector_id");
            if (sector_id == null) {
                sector_id = "";
            }

            sub_sector_weight = request.getParameter("sub_sector_weight");
            if (sub_sector_weight == null) {
                sub_sector_weight = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    sector = sectorhelper.exists(sub_sector_code);
                    Gson gson = new Gson();
                    Double weight = Double.parseDouble(helper.sumWeight(0));
                    if ((weight + Double.parseDouble(sub_sector_weight)) > 100.0) {
                        //resp = gson.toJson("invalidweight");
                        resp = "invalidweight";
                    } else {
                        subsectors = new SubSectors();
                        subsectors.setId(Integer.parseInt(helper.getMaxSerialNo("Sub_Sectors")) + 1);
                        subsectors.setSubSectorCode(sub_sector_code);
                        subsectors.setDateCreated(utility.dbDateNow());
                        subsectors.setName(name);
                        subsectors.setOrgId(1);
                        subsectors.setSectorId(Integer.parseInt(sector_id));
                        //subsectors.setSectors(sector);
                        subsectors.setSubSectorWeight(Double.parseDouble(sub_sector_weight));
                        resp = helper.insert(subsectors);
                        //resp = gson.toJson(resp);
                    }
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    Gson gson = new Gson();
                    //sector = sectorhelper.exists(sub_sector_code);
                    subsectors = helper.exists(Integer.parseInt(id));
                    if (subsectors != null) {
                        Double weight = Double.parseDouble(helper.sumWeight(subsectors.getId()));
                        if ((weight + Double.parseDouble(sub_sector_weight)) > 100.0) {
                            //resp = gson.toJson("invalidweight");
                            resp = "invalidweight";
                        } else {
                            subsectors.setName(name);
                            subsectors.setSubSectorCode(sub_sector_code);
                            subsectors.setSectorId(Integer.parseInt(sector_id));
                            //subsectors.setSectors(sector);
                            subsectors.setSubSectorWeight(Double.parseDouble(sub_sector_weight));
                            resp = helper.update(subsectors);
                            //resp = gson.toJson(resp);
                        }
                    } else {
                        resp = gson.toJson(Utility.ActionResponse.NO_RECORD);
                    }

                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    subsectors = helper.exists(Integer.parseInt(id));
                    mda = mdahelper.fetchBySubSector(subsectors.getSubSectorCode());
                    if(mda == null){
                        resp = helper.delete(subsectors);
                    }else{
                        resp = Utility.ActionResponse.RECORD_EXISTS.toString();
                    }
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_ALL_BY_GROUP)) {
                if(sector_id.equals("0")){
                    resp = helper.fetchAll();
                }else{
                    resp = helper.fetchByGroup(sector_id);
                }
            }

            if (option.equals(Utility.OPTION_SELECT)) {
                resp = helper.fetchAll2();
            }

            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchByCode();
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
            Logger.getLogger(SubSectorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SubSectorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
