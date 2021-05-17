/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;


import com.bpp.hibernate.GeographicSegmentHeader1HibernateHelper;
import com.bpp.hibernate.GeographicSegmentHeader1;
import com.bpp.hibernate.GeographicSegmentHeader3HibernateHelper;
import com.bpp.hibernate.GeographicSegmentHeader3;
import com.bpp.hibernate.GeographicSegmentHeader2HibernateHelper;
import com.bpp.hibernate.GeographicSegmentHeader2;
import com.bpp.hibernate.GeographicSegmentHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.GeographicSegment;
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
public class GeographicSegmentServlet extends HttpServlet {

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
            
            GeographicSegmentHeader1HibernateHelper header1Helper = new GeographicSegmentHeader1HibernateHelper();
            GeographicSegmentHeader2HibernateHelper header2Helper = new GeographicSegmentHeader2HibernateHelper();
            GeographicSegmentHeader3HibernateHelper header3Helper = new GeographicSegmentHeader3HibernateHelper();
            GeographicSegmentHibernateHelper helper = new GeographicSegmentHibernateHelper();
            GeographicSegment GeographicSegment = new GeographicSegment();
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

            if (option.equals(Utility.OPTION_INSERT) && entity.equals("GeographicSegment")) {
                System.out.println("option " + option);
                System.out.println("Entity " + entity);
                synchronized (this) {
                    GeographicSegment geographicSegment = new GeographicSegment();
                    GeographicSegmentHeader3 segmentParent = header3Helper.fetchOne(parent);
                    geographicSegment.setId(Integer.parseInt(helper.getMaxSerialNo("Geographic_Segment")) + 1);
                    geographicSegment.setDateCreated(utility.dbDateNow());
                    geographicSegment.setName(name);
                    geographicSegment.setCode(code);
                    geographicSegment.setOrgId(1);
                    geographicSegment.setGeographicSegmentHeader3(segmentParent);
                    //geographicSegment.setParent(segmentParent);
                    resp = helper.insert(geographicSegment);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("GeographicSegmentHeader1")) {
                synchronized (this) {
                    GeographicSegmentHeader1 geographicSegmentHeader1 = new GeographicSegmentHeader1();
                    geographicSegmentHeader1.setId(Integer.parseInt(header1Helper.getMaxSerialNo("Geographic_Segment_Header1")) + 1);
                    geographicSegmentHeader1.setDateCreated(utility.dbDateNow());
                    geographicSegmentHeader1.setName(name);
                    geographicSegmentHeader1.setOrgId(1);
                    geographicSegmentHeader1.setCode(code);
                    resp = header1Helper.insert(geographicSegmentHeader1);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("GeographicSegmentHeader2")) {
                synchronized (this) {
                    GeographicSegmentHeader1 header2Parent = header1Helper.fetchOne(parent);
                    GeographicSegmentHeader2 geographicSegmentHeader2 = new GeographicSegmentHeader2();
                    geographicSegmentHeader2.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Geographic_Segment_Header1")) + 1);
                    geographicSegmentHeader2.setDateCreated(utility.dbDateNow());
                    geographicSegmentHeader2.setName(name);
                    geographicSegmentHeader2.setOrgId(1);
                    geographicSegmentHeader2.setCode(code);
                    geographicSegmentHeader2.setGeographicSegmentHeader1(header2Parent);
                    //geographicSegmentHeader2.setParent(header2Parent);
                    resp = header2Helper.insert(geographicSegmentHeader2);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("GeographicSegmentHeader3")) {
                synchronized (this) {
                    GeographicSegmentHeader2 header3Parent = header2Helper.fetchOne(parent);
                    GeographicSegmentHeader3 geographicSegmentHeader3 = new GeographicSegmentHeader3();
                    geographicSegmentHeader3.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Geographic_Segment_Header1")) + 1);
                    geographicSegmentHeader3.setDateCreated(utility.dbDateNow());
                    geographicSegmentHeader3.setName(name);
                    geographicSegmentHeader3.setOrgId(1);
                    geographicSegmentHeader3.setCode(code);
                    geographicSegmentHeader3.setGeographicSegmentHeader2(header3Parent);
                    //geographicSegmentHeader3.setParent(header3Parent);
                    resp = header3Helper.insert(geographicSegmentHeader3);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("GeographicSegment")) {
                synchronized (this) {
                    GeographicSegment geographicSegment = new GeographicSegment();
                    geographicSegment = helper.fetchOne(id);
                    if (geographicSegment != null) {
                        geographicSegment.setName(name);
                        geographicSegment.setCode(code);
                        resp = helper.update(geographicSegment);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("GeographicSegmentHeader1")) {
                synchronized (this) {
                    GeographicSegmentHeader1 geographicSegmentHeader1 = new GeographicSegmentHeader1();
                    geographicSegmentHeader1 = header1Helper.fetchOne(id);
                    if (geographicSegmentHeader1 != null) {
                        geographicSegmentHeader1.setName(name);
                        geographicSegmentHeader1.setCode(code);
                        resp = header1Helper.update(geographicSegmentHeader1);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("GeographicSegmentHeader2")) {
                synchronized (this) {
                    GeographicSegmentHeader2 geographicSegmentHeader2 = new GeographicSegmentHeader2();
                    geographicSegmentHeader2 = header2Helper.fetchOne(id);
                    if (geographicSegmentHeader2 != null) {
                        geographicSegmentHeader2.setName(name);
                        geographicSegmentHeader2.setCode(code);
                        resp = header2Helper.update(geographicSegmentHeader2);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("GeographicSegmentHeader3")) {
                synchronized (this) {
                    GeographicSegmentHeader3 geographicSegmentHeader3 = new GeographicSegmentHeader3();
                    geographicSegmentHeader3 = header3Helper.fetchOne(id);
                    if (geographicSegmentHeader3 != null) {
                        geographicSegmentHeader3.setName(name);
                        geographicSegmentHeader3.setCode(code);
                        resp = header3Helper.update(geographicSegmentHeader3);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("GeographicSegment")) {
                synchronized (this) {
                    GeographicSegment geographicSegment = new GeographicSegment();
                    geographicSegment = helper.fetchOne(id);
                    if (geographicSegment != null) {
                        resp = helper.delete(geographicSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("GeographicSegmentHeader1")) {
                synchronized (this) {
                    GeographicSegmentHeader1 geographicSegmentHeader1 = new GeographicSegmentHeader1();
                    geographicSegmentHeader1 = header1Helper.fetchOne(id);
                    if (geographicSegmentHeader1 != null) {
                        resp = header1Helper.delete(geographicSegmentHeader1);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("GeographicSegmentHeader2")) {
                synchronized (this) {
                    GeographicSegmentHeader2 geographicSegmentHeader2 = new GeographicSegmentHeader2();
                    geographicSegmentHeader2 = header2Helper.fetchOne(id);
                    if (geographicSegmentHeader2 != null) {
                        resp = header2Helper.delete(geographicSegmentHeader2);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if(option.equals(Utility.OPTION_DELETE) && entity.equals("GeographicSegmentHeader3")) {
                synchronized (this) {
                    System.out.println("Deleting Header 3 with id " + id);
                    GeographicSegmentHeader3 geographicSegmentHeader3 = new GeographicSegmentHeader3();
                    geographicSegmentHeader3 = header3Helper.fetchOne(id);
                    if (geographicSegmentHeader3 != null) {
                        resp = header3Helper.delete(geographicSegmentHeader3);
                    } else {
                        System.out.println("No geographicSegment 3 in servlet");
                        
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
             
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("GeographicSegment")) {
                resp = helper.fetchAll(Integer.parseInt(parent));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("GeographicSegmentHeader1")) {
                resp = header1Helper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("GeographicSegmentHeader2")) {
                resp = header2Helper.fetchAll(Integer.parseInt(parent));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("GeographicSegmentHeader3")) {
                resp = header3Helper.fetchAll(Integer.parseInt(parent));
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("GeographicSegment")) {
                synchronized (this) {
                    resp = helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("GeographicSegmentHeader1")) {
                synchronized (this) {
                    resp = header1Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("GeographicSegmentHeader2")) {
                synchronized (this) {
                    resp = header2Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("GeographicSegmentHeader3")) {
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
            Logger.getLogger(GeographicSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GeographicSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
