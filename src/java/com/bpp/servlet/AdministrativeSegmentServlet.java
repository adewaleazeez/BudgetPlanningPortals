/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;


import com.bpp.hibernate.AdministrativeSegmentHeader1HibernateHelper;
import com.bpp.hibernate.AdministrativeSegmentHeader1;
import com.bpp.hibernate.AdministrativeSegmentHeader3HibernateHelper;
import com.bpp.hibernate.AdministrativeSegmentHeader3;
import com.bpp.hibernate.AdministrativeSegmentHeader2HibernateHelper;
import com.bpp.hibernate.AdministrativeSegmentHeader2;
import com.bpp.hibernate.AdministrativeSegmentHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.AdministrativeSegment;
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
public class AdministrativeSegmentServlet extends HttpServlet {

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
            
            AdministrativeSegmentHeader1HibernateHelper header1Helper = new AdministrativeSegmentHeader1HibernateHelper();
            AdministrativeSegmentHeader2HibernateHelper header2Helper = new AdministrativeSegmentHeader2HibernateHelper();
            AdministrativeSegmentHeader3HibernateHelper header3Helper = new AdministrativeSegmentHeader3HibernateHelper();
            AdministrativeSegmentHibernateHelper helper = new AdministrativeSegmentHibernateHelper();
            AdministrativeSegment AdministrativeSegment = new AdministrativeSegment();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String dateCreated;
            String orgId;
            String entity;
            String code;
            String parent;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }
            
            entity = request.getParameter("entity");
            if(entity == null){
                entity = "";
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
            
            parent = request.getParameter("parent");
            if (parent == null) {
                parent = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "";
            }

            if (option.equals(Utility.OPTION_INSERT) && entity.equals("AdministrativeSegment")) {
                System.out.println("option " + option);
                System.out.println("Entity " + entity);
                synchronized (this) {
                    AdministrativeSegment administrativeSegment = new AdministrativeSegment();
                    AdministrativeSegmentHeader3 segmentParent = header3Helper.fetchOne(parent);
                    administrativeSegment.setId(Integer.parseInt(helper.getMaxSerialNo("Administrative_Segment")) + 1);
                    administrativeSegment.setDateCreated(utility.dbDateNow());
                    administrativeSegment.setName(name);
                    administrativeSegment.setCode(code);
                    administrativeSegment.setOrgId(1);
                    administrativeSegment.setAdministrativeSegmentHeader3(segmentParent);
                    //administrativeSegment.setParent(segmentParent);
                    resp = helper.insert(administrativeSegment);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("AdministrativeSegmentHeader1")) {
                synchronized (this) {
                    AdministrativeSegmentHeader1 administrativeSegmentHeader1 = new AdministrativeSegmentHeader1();
                    administrativeSegmentHeader1.setId(Integer.parseInt(header1Helper.getMaxSerialNo("Administrative_Segment_Header1")) + 1);
                    administrativeSegmentHeader1.setDateCreated(utility.dbDateNow());
                    administrativeSegmentHeader1.setName(name);
                    administrativeSegmentHeader1.setOrgId(1);
                    administrativeSegmentHeader1.setCode(code);
                    resp = header1Helper.insert(administrativeSegmentHeader1);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("AdministrativeSegmentHeader2")) {
                synchronized (this) {
                    AdministrativeSegmentHeader1 header2Parent = header1Helper.fetchOne(parent);
                    AdministrativeSegmentHeader2 administrativeSegmentHeader2 = new AdministrativeSegmentHeader2();
                    administrativeSegmentHeader2.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Administrative_Segment_Header1")) + 1);
                    administrativeSegmentHeader2.setDateCreated(utility.dbDateNow());
                    administrativeSegmentHeader2.setName(name);
                    administrativeSegmentHeader2.setOrgId(1);
                    administrativeSegmentHeader2.setCode(code);
                    administrativeSegmentHeader2.setAdministrativeSegmentHeader1(header2Parent);
                    //administrativeSegmentHeader2.setParent(header2Parent);
                    resp = header2Helper.insert(administrativeSegmentHeader2);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("AdministrativeSegmentHeader3")) {
                synchronized (this) {
                    AdministrativeSegmentHeader2 header3Parent = header2Helper.fetchOne(parent);
                    AdministrativeSegmentHeader3 administrativeSegmentHeader3 = new AdministrativeSegmentHeader3();
                    administrativeSegmentHeader3.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Administrative_Segment_Header1")) + 1);
                    administrativeSegmentHeader3.setDateCreated(utility.dbDateNow());
                    administrativeSegmentHeader3.setName(name);
                    administrativeSegmentHeader3.setOrgId(1);
                    administrativeSegmentHeader3.setCode(code);
                    administrativeSegmentHeader3.setAdministrativeSegmentHeader2(header3Parent);
                    //administrativeSegmentHeader3.setParent(header3Parent);
                    resp = header3Helper.insert(administrativeSegmentHeader3);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("AdministrativeSegment")) {
                synchronized (this) {
                    AdministrativeSegment administrativeSegment = new AdministrativeSegment();
                    administrativeSegment = helper.fetchOne(id);
                    if (administrativeSegment != null) {
                        administrativeSegment.setName(name);
                        administrativeSegment.setCode(code);
                        resp = helper.update(administrativeSegment);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("AdministrativeSegmentHeader1")) {
                synchronized (this) {
                    AdministrativeSegmentHeader1 administrativeSegmentHeader1 = new AdministrativeSegmentHeader1();
                    administrativeSegmentHeader1 = header1Helper.fetchOne(id);
                    if (administrativeSegmentHeader1 != null) {
                        administrativeSegmentHeader1.setName(name);
                        administrativeSegmentHeader1.setCode(code);
                        resp = header1Helper.update(administrativeSegmentHeader1);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("AdministrativeSegmentHeader2")) {
                synchronized (this) {
                    AdministrativeSegmentHeader2 administrativeSegmentHeader2 = new AdministrativeSegmentHeader2();
                    administrativeSegmentHeader2 = header2Helper.fetchOne(id);
                    if (administrativeSegmentHeader2 != null) {
                        administrativeSegmentHeader2.setName(name);
                        administrativeSegmentHeader2.setCode(code);
                        resp = header2Helper.update(administrativeSegmentHeader2);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("AdministrativeSegmentHeader3")) {
                synchronized (this) {
                    AdministrativeSegmentHeader3 administrativeSegmentHeader3 = new AdministrativeSegmentHeader3();
                    administrativeSegmentHeader3 = header3Helper.fetchOne(id);
                    if (administrativeSegmentHeader3 != null) {
                        administrativeSegmentHeader3.setName(name);
                        administrativeSegmentHeader3.setCode(code);
                        resp = header3Helper.update(administrativeSegmentHeader3);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("AdministrativeSegment")) {
                synchronized (this) {
                    AdministrativeSegment administrativeSegment = new AdministrativeSegment();
                    administrativeSegment = helper.fetchOne(id);
                    if (administrativeSegment != null) {
                        resp = helper.delete(administrativeSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("AdministrativeSegmentHeader1")) {
                synchronized (this) {
                    AdministrativeSegmentHeader1 administrativeSegmentHeader1 = new AdministrativeSegmentHeader1();
                    administrativeSegmentHeader1 = header1Helper.fetchOne(id);
                    if (administrativeSegmentHeader1 != null) {
                        resp = header1Helper.delete(administrativeSegmentHeader1);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("AdministrativeSegmentHeader2")) {
                synchronized (this) {
                    AdministrativeSegmentHeader2 administrativeSegmentHeader2 = new AdministrativeSegmentHeader2();
                    administrativeSegmentHeader2 = header2Helper.fetchOne(id);
                    if (administrativeSegmentHeader2 != null) {
                        resp = header2Helper.delete(administrativeSegmentHeader2);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if(option.equals(Utility.OPTION_DELETE) && entity.equals("AdministrativeSegmentHeader3")) {
                synchronized (this) {
                    System.out.println("Deleting Header 3 with id " + id);
                    AdministrativeSegmentHeader3 administrativeSegmentHeader3 = new AdministrativeSegmentHeader3();
                    administrativeSegmentHeader3 = header3Helper.fetchOne(id);
                    if (administrativeSegmentHeader3 != null) {
                        resp = header3Helper.delete(administrativeSegmentHeader3);
                    } else {
                        System.out.println("No administrativeSegment 3 in servlet");
                        
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("AdministrativeSegment")) {
                resp = helper.fetchAll(Integer.parseInt(parent));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("AdministrativeSegmentHeader1")) {
                resp = header1Helper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("AdministrativeSegmentHeader2")) {
                resp = header2Helper.fetchAll(Integer.parseInt(parent));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("AdministrativeSegmentHeader3")) {
                resp = header3Helper.fetchAll(Integer.parseInt(parent));
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("AdministrativeSegment")) {
                synchronized (this) {
                    resp = helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("AdministrativeSegmentHeader1")) {
                synchronized (this) {
                    resp = header1Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("AdministrativeSegmentHeader2")) {
                synchronized (this) {
                    resp = header2Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("AdministrativeSegmentHeader3")) {
                synchronized (this) {
                    resp = header3Helper.fetchOneJSON(id);
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
            Logger.getLogger(AdministrativeSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdministrativeSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
