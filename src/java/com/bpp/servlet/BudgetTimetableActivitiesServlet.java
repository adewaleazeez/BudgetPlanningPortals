/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetPhases;
import com.bpp.hibernate.BudgetPhasesHibernateHelper;
import com.bpp.hibernate.BudgetTimetable;
import com.bpp.hibernate.BudgetTimetableActivities;
import com.bpp.hibernate.BudgetTimetableActivitiesHibernateHelper;
import com.bpp.hibernate.BudgetTimetableHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Budget Timetable Activities Servlet class
 * 
 * @author Lekan
 * @since 19/6/2017
 */
public class BudgetTimetableActivitiesServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
            
        	BudgetTimetableActivitiesHibernateHelper helper = new BudgetTimetableActivitiesHibernateHelper();
            BudgetTimetableActivities budgetTimetableActivity = new BudgetTimetableActivities();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    budgetTimetableActivity.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    budgetTimetableActivity.setDateCreated(Utility.dbDateNow());
                    budgetTimetableActivity.setName(request.getParameter("name"));
                    budgetTimetableActivity.setDescription(request.getParameter("description"));
                    
                    BudgetTimetable budgetTimetable = new BudgetTimetableHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("budgetTimetableID")));
                    budgetTimetableActivity.setBudgetTimetable(budgetTimetable);
                    
                    budgetTimetableActivity.setFromDate(Utility.dbDate2(request.getParameter("fromDate")));
                    budgetTimetableActivity.setToDate(Utility.dbDate2(request.getParameter("toDate")));
                    
                    BudgetPhases budgetPhase = new BudgetPhasesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("budgetPhaseID")));
                    budgetTimetableActivity.setBudgetPhases(budgetPhase);
                    
                    budgetTimetableActivity.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(budgetTimetableActivity);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    budgetTimetableActivity = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    budgetTimetableActivity.setName(request.getParameter("name"));
                    budgetTimetableActivity.setDescription(request.getParameter("description"));
                    budgetTimetableActivity.setFromDate(Utility.dbDate2(request.getParameter("fromDate")));
                    budgetTimetableActivity.setToDate(Utility.dbDate2(request.getParameter("toDate")));
                    
                    BudgetPhases budgetPhase = new BudgetPhasesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("budgetPhaseID")));
                    budgetTimetableActivity.setBudgetPhases(budgetPhase);
                    
                    resp = helper.update(budgetTimetableActivity);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	budgetTimetableActivity = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(budgetTimetableActivity);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT))
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            
            if (option.equals(Utility.OPTION_SELECT_ALL))
                resp = helper.fetchAll(Integer.parseInt(request.getParameter("budgetTimetableID")));
            
            //System.out.println(resp);
            
            out.println(resp);
        }
        finally {
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
        } catch (Exception ex) {
        	ex.printStackTrace();
            //Logger.getLogger(BudgetTimetableServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        	ex.printStackTrace();
           // Logger.getLogger(BudgetTimetableServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Budget Timetable Activities";
    }// </editor-fold>

}
