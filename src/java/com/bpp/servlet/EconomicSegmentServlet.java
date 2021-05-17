/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;


import com.bpp.hibernate.EconomicSegmentHeader1HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHeader1;
import com.bpp.hibernate.EconomicSegmentHeader3HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHeader3;
import com.bpp.hibernate.EconomicSegmentHeader4HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHeader4;
import com.bpp.hibernate.EconomicSegmentHeader2HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHeader2;
import com.bpp.hibernate.EconomicSegmentHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.EconomicSegment;
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
public class EconomicSegmentServlet extends HttpServlet {

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
            
            EconomicSegmentHeader1HibernateHelper header1Helper = new EconomicSegmentHeader1HibernateHelper();
            EconomicSegmentHeader2HibernateHelper header2Helper = new EconomicSegmentHeader2HibernateHelper();
            EconomicSegmentHeader3HibernateHelper header3Helper = new EconomicSegmentHeader3HibernateHelper();
            EconomicSegmentHeader4HibernateHelper header4Helper = new EconomicSegmentHeader4HibernateHelper();
            EconomicSegmentHibernateHelper helper = new EconomicSegmentHibernateHelper();
            EconomicSegment EconomicSegment = new EconomicSegment();
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

            if (option.equals(Utility.OPTION_INSERT) && entity.equals("EconomicSegment")) {
                System.out.println("option " + option);
                System.out.println("Entity " + entity);
                synchronized (this) {
                    EconomicSegment economicSegment = new EconomicSegment();
                    EconomicSegmentHeader4 segmentParent = header4Helper.fetchOne(parent);
                    economicSegment.setId(Integer.parseInt(helper.getMaxSerialNo("Economic_Segment")) + 1);
                    economicSegment.setDateCreated(utility.dbDateNow());
                    economicSegment.setName(name);
                    economicSegment.setCode(code);
                    economicSegment.setOrgId(1);
                    economicSegment.setEconomicSegmentHeader4(segmentParent);
                    //economicSegment..setParent(segmentParent);
                    resp = helper.insert(economicSegment);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("EconomicSegmentHeader1")) {
                synchronized (this) {
                    EconomicSegmentHeader1 economicSegmentHeader1 = new EconomicSegmentHeader1();
                    economicSegmentHeader1.setId(Integer.parseInt(header1Helper.getMaxSerialNo("Economic_Segment_Header1")) + 1);
                    economicSegmentHeader1.setDateCreated(utility.dbDateNow());
                    economicSegmentHeader1.setName(name);
                    economicSegmentHeader1.setOrgId(1);
                    economicSegmentHeader1.setCode(code);
                    resp = header1Helper.insert(economicSegmentHeader1);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("EconomicSegmentHeader2")) {
                synchronized (this) {
                    EconomicSegmentHeader1 header2Parent = header1Helper.fetchOne(parent);
                    EconomicSegmentHeader2 economicSegmentHeader2 = new EconomicSegmentHeader2();
                    economicSegmentHeader2.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Economic_Segment_Header1")) + 1);
                    economicSegmentHeader2.setDateCreated(utility.dbDateNow());
                    economicSegmentHeader2.setName(name);
                    economicSegmentHeader2.setOrgId(1);
                    economicSegmentHeader2.setCode(code);
                    economicSegmentHeader2.setEconomicSegmentHeader1(header2Parent);
                    //economicSegmentHeader2.setParent(header2Parent);
                    resp = header2Helper.insert(economicSegmentHeader2);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("EconomicSegmentHeader3")) {
                synchronized (this) {
                    EconomicSegmentHeader2 header3Parent = header2Helper.fetchOne(parent);
                    EconomicSegmentHeader3 economicSegmentHeader3 = new EconomicSegmentHeader3();
                    economicSegmentHeader3.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Economic_Segment_Header1")) + 1);
                    economicSegmentHeader3.setDateCreated(utility.dbDateNow());
                    economicSegmentHeader3.setName(name);
                    economicSegmentHeader3.setOrgId(1);
                    economicSegmentHeader3.setCode(code);
                    economicSegmentHeader3.setEconomicSegmentHeader2(header3Parent);
                    //economicSegmentHeader3.setParent(header3Parent);
                    resp = header3Helper.insert(economicSegmentHeader3);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("EconomicSegmentHeader4")) {
                synchronized (this) {
                    EconomicSegmentHeader3 header4Parent = header3Helper.fetchOne(parent);
                    EconomicSegmentHeader4 economicSegmentHeader4 = new EconomicSegmentHeader4();
                    economicSegmentHeader4.setId(Integer.parseInt(header3Helper.getMaxSerialNo("Economic_Segment_Header1")) + 1);
                    economicSegmentHeader4.setDateCreated(utility.dbDateNow());
                    economicSegmentHeader4.setName(name);
                    economicSegmentHeader4.setOrgId(1);
                    economicSegmentHeader4.setCode(code);
                    economicSegmentHeader4.setEconomicSegmentHeader3(header4Parent);
                    //economicSegmentHeader4.setParent(header4Parent);
                    resp = header4Helper.insert(economicSegmentHeader4);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("EconomicSegment")) {
                synchronized (this) {
                    EconomicSegment economicSegment = new EconomicSegment();
                    economicSegment = helper.fetchOne(id);
                    if (economicSegment != null) {
                        economicSegment.setName(name);
                        economicSegment.setCode(code);
                        resp = helper.update(economicSegment);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("EconomicSegmentHeader1")) {
                synchronized (this) {
                    EconomicSegmentHeader1 economicSegmentHeader1 = new EconomicSegmentHeader1();
                    economicSegmentHeader1 = header1Helper.fetchOne(id);
                    if (economicSegmentHeader1 != null) {
                        economicSegmentHeader1.setName(name);
                        economicSegmentHeader1.setCode(code);
                        resp = header1Helper.update(economicSegmentHeader1);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("EconomicSegmentHeader2")) {
                synchronized (this) {
                    EconomicSegmentHeader2 economicSegmentHeader2 = new EconomicSegmentHeader2();
                    economicSegmentHeader2 = header2Helper.fetchOne(id);
                    if (economicSegmentHeader2 != null) {
                        economicSegmentHeader2.setName(name);
                        economicSegmentHeader2.setCode(code);
                        resp = header2Helper.update(economicSegmentHeader2);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("EconomicSegmentHeader3")) {
                synchronized (this) {
                    EconomicSegmentHeader3 economicSegmentHeader3 = new EconomicSegmentHeader3();
                    economicSegmentHeader3 = header3Helper.fetchOne(id);
                    if (economicSegmentHeader3 != null) {
                        economicSegmentHeader3.setName(name);
                        economicSegmentHeader3.setCode(code);
                        resp = header3Helper.update(economicSegmentHeader3);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("EconomicSegmentHeader4")) {
                synchronized (this) {
                    EconomicSegmentHeader4 economicSegmentHeader4 = new EconomicSegmentHeader4();
                    economicSegmentHeader4 = header4Helper.fetchOne(id);
                    if (economicSegmentHeader4 != null) {
                        economicSegmentHeader4.setName(name);
                        economicSegmentHeader4.setCode(code);
                        resp = header4Helper.update(economicSegmentHeader4);
                    }else{
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("EconomicSegment")) {
                synchronized (this) {
                    EconomicSegment economicSegment = new EconomicSegment();
                    economicSegment = helper.fetchOne(id);
                    if (economicSegment != null) {
                        resp = helper.delete(economicSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("EconomicSegmentHeader1")) {
                synchronized (this) {
                    EconomicSegmentHeader1 economicSegmentHeader1 = new EconomicSegmentHeader1();
                    economicSegmentHeader1 = header1Helper.fetchOne(id);
                    if (economicSegmentHeader1 != null) {
                        resp = header1Helper.delete(economicSegmentHeader1);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_DELETE) && entity.equals("EconomicSegmentHeader2")) {
                synchronized (this) {
                    EconomicSegmentHeader2 economicSegmentHeader2 = new EconomicSegmentHeader2();
                    economicSegmentHeader2 = header2Helper.fetchOne(id);
                    if (economicSegmentHeader2 != null) {
                        resp = header2Helper.delete(economicSegmentHeader2);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            
            if(option.equals(Utility.OPTION_DELETE) && entity.equals("EconomicSegmentHeader3")) {
                synchronized (this) {
                    System.out.println("Deleting Header 3 with id " + id);
                    EconomicSegmentHeader3 economicSegmentHeader3 = new EconomicSegmentHeader3();
                    economicSegmentHeader3 = header3Helper.fetchOne(id);
                    if (economicSegmentHeader3 != null) {
                        resp = header3Helper.delete(economicSegmentHeader3);
                    } else {
                        System.out.println("No economicSegment 3 in servlet");
                        
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if(option.equals(Utility.OPTION_DELETE) && entity.equals("EconomicSegmentHeader4")) {
                synchronized (this) {
                    System.out.println("Deleting Header 4 with id " + id);
                    EconomicSegmentHeader4 economicSegmentHeader4 = new EconomicSegmentHeader4();
                    economicSegmentHeader4 = header4Helper.fetchOne(id);
                    if (economicSegmentHeader4 != null) {
                        resp = header4Helper.delete(economicSegmentHeader4);
                    } else {
                        System.out.println("No economicSegment 4 in servlet");
                        
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("EconomicSegment")) {
                resp = helper.fetchAll(Integer.parseInt(parent));
            }
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("EconomicSegmentHeader1")) {
                resp = header1Helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("EconomicSegmentHeader2")) {
                resp = header2Helper.fetchAll(Integer.parseInt(parent));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("EconomicSegmentHeader3")) {
                resp = header3Helper.fetchAll(Integer.parseInt(parent));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("EconomicSegmentHeader4")) {
                resp = header4Helper.fetchAll(Integer.parseInt(parent));
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("EconomicSegment")) {
                synchronized (this) {
                    resp = helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("EconomicSegmentHeader1")) {
                synchronized (this) {
                    resp = header1Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("EconomicSegmentHeader2")) {
                synchronized (this) {
                    resp = header2Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("EconomicSegmentHeader3")) {
                synchronized (this) {
                    resp = header3Helper.fetchOneJSON(id);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("EconomicSegmentHeader4")) {
                synchronized (this) {
                    resp = header4Helper.fetchOneJSON(id);
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
            Logger.getLogger(EconomicSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EconomicSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
