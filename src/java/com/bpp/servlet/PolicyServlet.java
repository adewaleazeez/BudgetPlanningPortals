/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
import com.bpp.hibernate.Policies;
import com.bpp.hibernate.PolicyHibernateHelper;
import com.bpp.hibernate.PolicyMda;
import com.bpp.hibernate.PolicyMdaHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
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
//@WebServlet(name = "PolicyServlet", urlPatterns = {"/PolicyServlet"})
public class PolicyServlet extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            PolicyHibernateHelper helper = new PolicyHibernateHelper();
            PolicyMdaHibernateHelper policymdahelper = new PolicyMdaHibernateHelper();
            MdaHibernateHelper mdahelper = new MdaHibernateHelper();
            BudgetYearHibernateHelper budgetyearhelper = new BudgetYearHibernateHelper();
            Policies policies = new Policies();
            PolicyMda policyMda = new PolicyMda();
            Mdas mdas = new Mdas();
            Utility utility = new Utility();

            String option;
            String id;
            String description;
            String policyDate;
            String dateCreated;
            String orgId;
            String mdaId;
            String policy_weight;
            String policy_year_id;
            String group_code;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            description = request.getParameter("description");
            if (description == null) {
                description = "";
            }

            policyDate = request.getParameter("policyDate");
            if (policyDate == null) {
                policyDate = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "1";
            }

            mdaId = request.getParameter("mdaId");
            if (mdaId == null) {
                mdaId = "1";
            }

            policy_weight = request.getParameter("policy_weight");
            if (policy_weight == null) {
                policy_weight = "";
            }
            group_code = request.getParameter("group_code");
            if (group_code == null) {
                group_code = "";
            }
            policy_year_id = request.getParameter("policy_year_id");
            if (policy_year_id == null) {
                policy_year_id = "";
            }

            if (option.equals(Utility.OPTION_RESET_COOKIE)) {
                createCookie(response, id, "");
            }
            System.out.println("option  "+option);
            if (option.equals(Utility.OPTION_INSERT)) {
                resp = Utility.ActionResponse.RECORD_EXISTS.toString();
                policies = helper.exists(description);
                System.out.println("policies  "+policies);
                if (policies == null) {
                    synchronized (this) {
                        policies = new Policies();
                        policies.setId(Integer.parseInt(helper.getMaxSerialNo("Policies")) + 1);
                        policies.setDateCreated(utility.dbDateNow());
                        policies.setPolicyDate(utility.dbDateNow());
                        policies.setDescription(description);
                        policies.setOrgId(1);
                        policies.setPolicyWeight(Double.parseDouble(policy_weight));
                        policies.setPolicyYearId(Integer.parseInt(policy_year_id));
                        policies.setGroupCode(group_code);
                        resp = helper.insert(policies);
                        System.out.println("policies  "+policies);
                        System.out.println("resp  "+resp);
                        if (resp.equals(Utility.ActionResponse.INSERTED.toString())) {
                            String[] splited_mdass = mdaId.split(",");
                            for (String splited_mda : splited_mdass) {
                                mdas = mdahelper.exists(Integer.parseInt(splited_mda));
                                policyMda = new PolicyMda();
                                policyMda.setId(Integer.parseInt(policymdahelper.getMaxSerialNo("Policy_Mda")) + 1);
                                policyMda.setDateCreated(utility.dbDateNow());
                                policyMda.setMdaId(mdas.getId());
                                policyMda.setOrgId(1);
                                policyMda.setPolicies(policies);
                                policyMda.setPolicyYearId(Integer.parseInt(policy_year_id));
                                policymdahelper.insert(policyMda);
                            }
                        }
                    }
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                resp = Utility.ActionResponse.NO_RECORD.toString();
                policies = helper.exists(Integer.parseInt(id));
                if (policies != null) {
                    synchronized (this) {
                        policies.setDescription(description);
                        policies.setPolicyWeight(Double.parseDouble(policy_weight));
                        policies.setGroupCode(group_code);
                        resp = helper.update(policies);
                        if (resp.equals(Utility.ActionResponse.UPDATED.toString())) {
                            String[] splited_mdass = mdaId.split(",");
                            policymdahelper.deleteAll(policies.getId() + "");
                            for (String splited_mda : splited_mdass) {
                                mdas = mdahelper.exists(Integer.parseInt(splited_mda));
                                policyMda = new PolicyMda();
                                policyMda.setId(Integer.parseInt(policymdahelper.getMaxSerialNo("Policy_Mda")) + 1);
                                policyMda.setDateCreated(utility.dbDateNow());
                                policyMda.setMdaId(mdas.getId());
                                policyMda.setOrgId(1);
                                policyMda.setPolicies(policies);
                                policyMda.setPolicyYearId(Integer.parseInt(policy_year_id));

                                policymdahelper.insert(policyMda);
                            }
                        }
                    }
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    policies = helper.exists(Integer.parseInt(id));
                    policymdahelper.deleteAll(policies.getId() + "");
                    resp = helper.delete(policies);
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_SELECT_ALL_BY_GROUP)) {
                resp = helper.fetchAll(group_code);
            }
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                if(group_code.equals("")){
                    resp = helper.fetchAll();
                }else{
                    resp = helper.fetchByGroup(group_code);
                }
            }
            if (option.equals(Utility.OPTION_SELECT)) {
                resp = budgetyearhelper.fetchCurrentYear();
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
            Logger.getLogger(PolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
