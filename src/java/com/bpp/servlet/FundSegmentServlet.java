/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;
  

import com.bpp.hibernate.FundSegmentHeader1HibernateHelper;
import com.bpp.hibernate.FundSegmentHeader1;
import com.bpp.hibernate.FundSegmentHeader2HibernateHelper;
import com.bpp.hibernate.FundSegmentHeader2;
import com.bpp.hibernate.FundSegmentHibernateHelper;
import com.bpp.hibernate.FundSegment;
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
public class FundSegmentServlet extends HttpServlet {

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

            FundSegmentHeader1HibernateHelper header1Helper = new FundSegmentHeader1HibernateHelper();            
            FundSegmentHibernateHelper helper = new FundSegmentHibernateHelper();
            FundSegmentHeader2HibernateHelper header2Helper = new FundSegmentHeader2HibernateHelper();
            FundSegment FundSegment = new FundSegment();
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

            if (option.equals(Utility.OPTION_INSERT) && entity.equals("FundSegment")) {
                System.out.println("option " + option);
                System.out.println("Entity " + entity);
                synchronized (this) {
                    FundSegment functionalSegment = new FundSegment();
                    FundSegmentHeader2 segmentParent = header2Helper.fetchOne(parent);
                    functionalSegment.setId(Integer.parseInt(helper.getMaxSerialNo("Functional_Segment")) + 1);
                    functionalSegment.setDateCreated(utility.dbDateNow());
                    functionalSegment.setName(name);
                    functionalSegment.setCode(code);
                    functionalSegment.setOrgId(1);
                    functionalSegment.setParent(segmentParent.getId());
                    resp = helper.insert(functionalSegment);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("FundSegmentHeader1")) {
                System.out.println("option " + option);
                System.out.println("Entity " + entity);
                synchronized (this) {
                    FundSegmentHeader1 functionalSegmentHeader1 = new FundSegmentHeader1();
                    functionalSegmentHeader1.setId(Integer.parseInt(header1Helper.getMaxSerialNo("Functional_Segment_Header1")) + 1);
                    functionalSegmentHeader1.setDateCreated(utility.dbDateNow());
                    functionalSegmentHeader1.setName(name);
                    functionalSegmentHeader1.setOrgId(1);
                    functionalSegmentHeader1.setCode(code);
                    resp = header1Helper.insert(functionalSegmentHeader1);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("FundSegmentHeader2")) {
                System.out.println("option " + option);
                System.out.println("Entity " + entity);
                synchronized (this) {
                    FundSegmentHeader1 header2Parent = header1Helper.fetchOne(parent);
                    FundSegmentHeader2 functionalSegmentHeader2 = new FundSegmentHeader2();
                    functionalSegmentHeader2.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Functional_Segment_Header1")) + 1);
                    functionalSegmentHeader2.setDateCreated(utility.dbDateNow());
                    functionalSegmentHeader2.setName(name);
                    functionalSegmentHeader2.setOrgId(1);
                    functionalSegmentHeader2.setCode(code);
                    functionalSegmentHeader2.setParent(header2Parent.getId());
                    resp = header2Helper.insert(functionalSegmentHeader2);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("FundSegment")) {
                synchronized (this) {
                    FundSegment functionalSegment = new FundSegment();
                    functionalSegment = helper.fetchOne(id);
                    if (functionalSegment != null) {
                        functionalSegment.setName(name);
                        functionalSegment.setCode(code);
                        resp = helper.update(functionalSegment);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("FundSegmentHeader1")) {
                synchronized (this) {
                    FundSegmentHeader1 functionalSegmentHeader1 = new FundSegmentHeader1();
                    functionalSegmentHeader1 = header1Helper.fetchOne(id);
                    if (functionalSegmentHeader1 != null) {
                        functionalSegmentHeader1.setName(name);
                        functionalSegmentHeader1.setCode(code);
                        resp = header1Helper.update(functionalSegmentHeader1);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("FundSegmentHeader2")) {
                synchronized (this) {
                    FundSegmentHeader2 functionalSegmentHeader2 = new FundSegmentHeader2();
                    functionalSegmentHeader2 = header2Helper.fetchOne(id);
                    if (functionalSegmentHeader2 != null) {
                        functionalSegmentHeader2.setName(name);
                        functionalSegmentHeader2.setCode(code);
                        resp = header2Helper.update(functionalSegmentHeader2);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("FundSegment")) {
                synchronized (this) {
                    FundSegment functionalSegment = new FundSegment();
                    functionalSegment = helper.fetchOne(id);
                    if (functionalSegment != null) {
                        resp = helper.delete(functionalSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("FundSegmentHeader1")) {
                synchronized (this) {
                    FundSegmentHeader1 functionalSegment = new FundSegmentHeader1();
                    functionalSegment = header1Helper.fetchOne(id);
                    if (functionalSegment != null) {
                        resp = header1Helper.delete(functionalSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("FundSegmentHeader2")) {
                synchronized (this) {
                    FundSegmentHeader2 functionalSegment = new FundSegmentHeader2();
                    functionalSegment = header2Helper.fetchOne(id);
                    if (functionalSegment != null) {
                        resp = header2Helper.delete(functionalSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("FundSegment")) {
                resp = helper.fetchAll(Integer.parseInt(parent));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("FundSegmentHeader1")) {
                resp = header1Helper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("FundSegmentHeader2")) {
                resp = header2Helper.fetchAll(Integer.parseInt(parent));
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("FundSegment")) {
                synchronized (this) {
                    resp = helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("FundSegmentHeader1")) {
                synchronized (this) {
                    resp = header1Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("FundSegmentHeader2")) {
                synchronized (this) {
                    resp = header2Helper.fetchOneJSON(id);
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
            Logger.getLogger(FundSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FundSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
