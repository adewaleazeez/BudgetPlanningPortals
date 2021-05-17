/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetVersions;
import com.bpp.hibernate.BudgetVersionsHibernateHelper;
import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.BudgetYears;
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
 * Budget Versions Servlet class
 * 
 * @author Lekan
 * @since 18/6/2017
 */
public class BudgetVersionsServlet extends HttpServlet {

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
            
        	BudgetVersionsHibernateHelper helper = new BudgetVersionsHibernateHelper();
            BudgetVersions budgetVersion = new BudgetVersions();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    budgetVersion.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    budgetVersion.setDateCreated(Utility.dbDateNow());
                    budgetVersion.setName(request.getParameter("name"));
                    budgetVersion.setDescription(request.getParameter("description"));
                    budgetVersion.setMtefFromYear(Integer.parseInt(request.getParameter("mtefFromYear")));
                    budgetVersion.setMtefToYear(Integer.parseInt(request.getParameter("mtefToYear")));
                    //budgetVersion.setMybfContigencyValue(Double.valueOf(request.getParameter("mybfContigencyValue")));
                    budgetVersion.setMybfContigencyValue(0.0);
                    budgetVersion.setMybfSupplementaryValue(0.0);
                    
                    BudgetYears budgetYears = new BudgetYearHibernateHelper().exists(Integer.parseInt(request.getParameter("year")));
                    budgetVersion.setBudgetYears(budgetYears);
                    
                    budgetVersion.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(budgetVersion);
                    
                    if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                    	resp = String.valueOf(budgetVersion.getId());
                    else
                    	resp = "0";
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    budgetVersion = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    budgetVersion.setMybfContigencyValue(Double.valueOf(request.getParameter("mybfContigencyValue")));
                    
                    resp = helper.update(budgetVersion);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE_SUPPLEMENTARY_VALUE)) {
                synchronized (this) {
                    resp = helper.updateSupplementaryValue(request.getParameter("supplementary_value"), request.getParameter("budget_year"));
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	budgetVersion = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(budgetVersion);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD_SUPPLEMENTARY_VALUE)) {
                synchronized (this) {
                    resp = helper.selectSupplementaryValue(request.getParameter("budget_year"));
                }
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD_CONTINGENCY_VALUE)) {
                synchronized (this) {
                    resp = helper.selectContingencyValue(request.getParameter("budget_year"));
                }
            }

            if (option.equals(Utility.OPTION_SELECT))
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            
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
