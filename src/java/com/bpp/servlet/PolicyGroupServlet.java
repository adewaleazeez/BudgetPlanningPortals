/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.PolicyGroup;
import com.bpp.hibernate.PolicyGroupHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "PolicyGroupServlet", urlPatterns = {"/PolicyGroupServlet"})
public class PolicyGroupServlet extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException, MessagingException {
                HttpSession session = request.getSession(true);


            
        //System.out.println("sjd");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));
            PolicyGroupHibernateHelper helper = new PolicyGroupHibernateHelper();
           // BudgetYearHibernateHelper budgetyearhelper = new BudgetYearHibernateHelper();
            PolicyGroup policygroup = new PolicyGroup();
            //PolicyMda policyMda = new PolicyMda();
           // Mdas mdas = new Mdas();
            Utility utility = new Utility();

            String option;
            String id;
            String group_code;
            String group_name;
            String date_created;
            String org_id;
/* id
group_code  description
group_name  policyDate
org_id
date_created*/


            
            
            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            group_code = request.getParameter("group_code");
            if (group_code == null) {
                group_code = "";
            }

            group_name = request.getParameter("group_name");
            if (group_name == null) {
                group_name = "";
            }

            date_created = request.getParameter("date_created");
            if (date_created == null) {
                date_created = "";
            }

            org_id = request.getParameter("org_id");
            if (org_id == null) {
                org_id = "1";
            }


            if (option.equals(Utility.OPTION_RESET_COOKIE)) {
                createCookie(response, id, "");
            }
            if (option.equals(Utility.OPTION_INSERT)) {
                resp = Utility.ActionResponse.RECORD_EXISTS.toString();                              
                policygroup= helper.exists(group_code);
                if (policygroup == null) {
                    synchronized (this) {
                        policygroup = new PolicyGroup();
                        policygroup .setId(Integer.parseInt(helper.getMaxSerialNo("Policy_Group")) + 1);
                        policygroup.setDateCreated(utility.dbDateNow());
                        policygroup.setGroupName(group_name); //.setPolicyDate(utility.dbDateNow());
                        policygroup.setGroupCode(group_code);//.setDescription(description);
                        policygroup.setOrgId(1);

                        resp = helper.insert(policygroup);                       
                    }
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }
            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    
                    policygroup = helper.exists(Integer.parseInt(id));
                    resp = helper.delete(policygroup);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }
            if (option.equals(Utility.OPTION_UPDATE)) {
                resp = Utility.ActionResponse.NO_RECORD.toString();
                policygroup = helper.exists(Integer.parseInt(id));
                if (policygroup != null) {
                    synchronized (this) {
                        //policygroup.setGroup_code(group_code);//.setDescription(description);
                        policygroup.setGroupName(group_name);
                        resp = helper.update(policygroup);                       
                    }
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll();
                //resp = helper.fetchAll();//(group_code);// .fetchAll(policy_year_id);
                
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchOne(id);
            }            
            out.println(resp);

        } finally {
            out.close();
        }
    }

    public synchronized String readCookie(HttpServletRequest request, String cookiename) {
        Cookie[] cookies = request.getCookies();
        String resp = "";
        for (Cookie c : cookies) {
            if (c.getName().equals(cookiename)) {
                resp = c.getValue();
            }
        }
        return resp;
    }

//    public synchronized String readCookieValues(HttpServletRequest request, String cookiename) {
//        Cookie[] cookies = request.getCookies();
//        String resp = "";
//        for (Cookie c : cookies) {
//            if (c.getName().equals(cookiename)) {
//                resp = "value: " + c.getValue() + "   name: " + c.getName() + "   domain: " + c.getDomain() + "   path: " + c.getPath();
//            }
//        }
//        return resp;
//    }
    public synchronized void createCookie(HttpServletResponse response, String cookiename, String cookievalue) {
        Cookie c = new Cookie(cookiename, cookievalue);
        c.setMaxAge(24 * 60 * 60);
        response.addCookie(c);
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
            Logger.getLogger(PolicyGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PolicyGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PolicyGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PolicyGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
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
