/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.Scoring;
import com.bpp.hibernate.ScoringHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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
 * @author Ola
 */
//@WebServlet(name = "ScoringServlet", urlPatterns = {"/ScoringServlet"})
public class ScoringServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));
            ScoringHibernateHelper helper = new ScoringHibernateHelper();
            //ProgrammeSegmentHeader3HibernateHelper h3 = new ProgrammeSegmentHeader3HibernateHelper();

            Scoring scoring = new Scoring();
            Utility utility = new Utility();

            String option;
            String id;
            String project_code;
            String project_year;
            String project_type;
            String score1, score2, score3, score4, score5;
            
            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            project_code = request.getParameter("project_code");
            if (project_code == null) {
                project_code = "";
            }

            project_year = request.getParameter("project_year");
            if (project_year == null) {
                project_year = "";
            }

            project_type = request.getParameter("project_type");
            if (project_type == null) {
                project_type = "";
            }

            score1 = request.getParameter("score1");
            if (score1 == null) {
                score1 = "";
            }

            score2 = request.getParameter("score2");
            if (score2 == null) {
                score2 = "";
            }

            score3 = request.getParameter("score3");
            if (score3 == null) {
                score3 = "";
            }

            score4 = request.getParameter("score4");
            if (score4 == null) {
                score4 = "";
            }

            score5 = request.getParameter("score5");
            if (score5 == null) {
                score5 = "";
            }
            if (option.equals(Utility.OPTION_UPDATE)) {
                if(project_type.equals("txtscoreEx")){
                    synchronized (this) {
                        scoring = helper.exists(project_code, project_year, "1");
                        if(scoring != null){
                            scoring.setScore(Double.parseDouble(score1));
                            helper.update(scoring);
                        }
                        
                        scoring = helper.exists(project_code, project_year, "2");
                        if(scoring != null){
                            scoring.setScore(Double.parseDouble(score2));
                            helper.update(scoring);
                        }
                        
                        scoring = helper.exists(project_code, project_year, "3");
                        if(scoring != null){
                            scoring.setScore(Double.parseDouble(score3));
                            helper.update(scoring);
                        }
                        
                        scoring = helper.exists(project_code, project_year, "4");
                        if(scoring != null){
                            scoring.setScore(Double.parseDouble(score4));
                            resp = helper.update(scoring);
                        }
                    }
                    if(resp == null)resp = "";
                }else{
                    synchronized (this) {
                        scoring = helper.exists(project_code, project_year, "5");
                        if(scoring != null){
                            scoring.setScore(Double.parseDouble(score5));
                            resp = helper.update(scoring);
                        }
                    }
                    if(resp == null)resp = "";
                }
                if(resp.equals(Utility.ActionResponse.NO_RECORD.toString()) || scoring == null){
                    option = Utility.OPTION_INSERT;
                }
                
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                if(project_type.equals("txtscoreEx")){
                    synchronized (this) {
                        scoring = new Scoring();
                        scoring.setId(Integer.parseInt(helper.getMaxSerialNo("Scoring")) + 1);
                        scoring.setProjectCode(project_code);
                        scoring.setProjectYear(Integer.parseInt(project_year));
                        scoring.setCriteriaId(1);
                        scoring.setScore(Double.parseDouble(score1));
                        scoring.setDateCreated(utility.dbDateNow());
                        scoring.setOrgId(1);
                        helper.Insert(scoring);
                        
                        scoring = new Scoring();
                        scoring.setId(Integer.parseInt(helper.getMaxSerialNo("Scoring")) + 1);
                        scoring.setProjectCode(project_code);
                        scoring.setProjectYear(Integer.parseInt(project_year));
                        scoring.setCriteriaId(2);
                        scoring.setScore(Double.parseDouble(score2));
                        scoring.setDateCreated(utility.dbDateNow());
                        scoring.setOrgId(1);
                        helper.Insert(scoring);
                        
                        scoring = new Scoring();
                        scoring.setId(Integer.parseInt(helper.getMaxSerialNo("Scoring")) + 1);
                        scoring.setProjectCode(project_code);
                        scoring.setProjectYear(Integer.parseInt(project_year));
                        scoring.setCriteriaId(3);
                        scoring.setScore(Double.parseDouble(score3));
                        scoring.setDateCreated(utility.dbDateNow());
                        scoring.setOrgId(1);
                        helper.Insert(scoring);
                        
                        scoring = new Scoring();
                        scoring.setId(Integer.parseInt(helper.getMaxSerialNo("Scoring")) + 1);
                        scoring.setProjectCode(project_code);
                        scoring.setProjectYear(Integer.parseInt(project_year));
                        scoring.setCriteriaId(4);
                        scoring.setScore(Double.parseDouble(score4));
                        scoring.setDateCreated(utility.dbDateNow());
                        scoring.setOrgId(1);
                        resp = helper.Insert(scoring);
                    }
                }else{
                    synchronized (this) {
                        scoring = new Scoring();
                        scoring.setId(Integer.parseInt(helper.getMaxSerialNo("Scoring")) + 1);
                        scoring.setProjectCode(project_code);
                        scoring.setProjectYear(Integer.parseInt(project_year));
                        scoring.setCriteriaId(5);
                        scoring.setScore(Double.parseDouble(score5));
                        scoring.setDateCreated(utility.dbDateNow());
                        scoring.setOrgId(1);
                        resp = helper.Insert(scoring);
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    scoring = helper.exists(Integer.parseInt(id));
                    resp = helper.delete(scoring);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                resp = helper.fetchAll(project_code, project_year);
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchOne(id);
            }
            out.println(resp);

        } finally {
            out.close();
        }
    }

    public int countList(List itemList) {
        int count = 0;
        for (Object i : itemList) {
            count++;
        }
        return count;
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
            Logger.getLogger(ScoringServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ScoringServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ScoringServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ScoringServlet.class.getName()).log(Level.SEVERE, null, ex);
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
