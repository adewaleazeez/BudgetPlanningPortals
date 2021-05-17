/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.BudgetYears;
import com.bpp.hibernate.UsersHibernateHelper;
import com.google.gson.Gson;
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
 * Budget Year Servlet class
 * 
 * @author Lekan
 * @since 18/6/2017
 */
public class BudgetYearServlet extends HttpServlet {

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
            
        	BudgetYearHibernateHelper helper = new BudgetYearHibernateHelper();
            BudgetYears budgetYear = new BudgetYears();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    budgetYear.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    budgetYear.setDateCreated(Utility.dbDateNow());
                    budgetYear.setName(request.getParameter("name"));
                    budgetYear.setYear(Integer.parseInt(request.getParameter("year")));
                    //budgetYear.setIsCurrentBaseYear(Boolean.parseBoolean(request.getParameter("isCurrentBaseYear")));                    
                    budgetYear.setIsCurrentBaseYear(false);                    
                    budgetYear.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    if(Boolean.parseBoolean(request.getParameter("isCurrentBaseYear")))
                    	helper.updateAll();
                    
                    resp = helper.insert(budgetYear);
                    helper.setCurrentVersion();
                    
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    
                    int maxid = Integer.parseInt(helper.getMaxserialNo());
                    budgetYear = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    if((maxid - budgetYear.getId()) >=  3){
                        budgetYear.setName(request.getParameter("name"));
                        budgetYear.setIsCurrentBaseYear(Boolean.parseBoolean(request.getParameter("isCurrentBaseYear")));

                        if(Boolean.parseBoolean(request.getParameter("isCurrentBaseYear")))
                            helper.updateAll();

                        resp = helper.update(budgetYear);
                        helper.setCurrentVersion();
                    }else{
                        resp = Utility.ActionResponse.BLOCKED.toString();
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	budgetYear = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(budgetYear);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
            	if(request.getParameter("id") == null)
            		resp = helper.fetchCurrentYear();
            	else
            		resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL))
                resp = helper.fetchAll();
            
            //System.out.println(resp);
            
            out.println(resp);
        } finally {
            out.close();
        }
    }

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
        	ex.printStackTrace();
            //Logger.getLogger(BudgetYearServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            //Logger.getLogger(BudgetYearServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Budget Timetable";
    }
}
