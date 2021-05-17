/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetStatus;
import com.bpp.hibernate.BudgetStatusHibernateHelper;
import com.bpp.hibernate.BudgetVersionStatus;
import com.bpp.hibernate.BudgetVersionStatusHibernateHelper;
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
 * Budget Version Status Servlet class
 * 
 * @author Lekan
 * @since 28/7/2017
 */
public class BudgetVersionStatusServlet extends HttpServlet {

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
            
        	BudgetVersionStatusHibernateHelper helper = new BudgetVersionStatusHibernateHelper();
            BudgetVersionStatus budgetVersionStatus = new BudgetVersionStatus();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    budgetVersionStatus.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    budgetVersionStatus.setDateCreated(Utility.dbDateNow());
                    
                    BudgetVersions budgetVersion = new BudgetVersionsHibernateHelper().exists(Integer.parseInt(request.getParameter("budgetVersionID")));
                    budgetVersionStatus.setBudgetVersions(budgetVersion);
                    
                    BudgetStatus budgetStatus = new BudgetStatusHibernateHelper().exists(Integer.parseInt(request.getParameter("budgetStatusID")));
                    budgetVersionStatus.setBudgetStatus(budgetStatus);
                    
                    BudgetYears budgetYear = new BudgetYearHibernateHelper().exists(Integer.parseInt(request.getParameter("year")));
                    budgetVersionStatus.setBudgetYears(budgetYear);
                    
                    budgetVersionStatus.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(budgetVersionStatus);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    budgetVersionStatus = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    
                    BudgetStatus budgetStatus = new BudgetStatusHibernateHelper().exists(Integer.parseInt(request.getParameter("budgetStatusID")));
                    budgetVersionStatus.setBudgetStatus(budgetStatus);
                    
                    resp = helper.update(budgetVersionStatus);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	budgetVersionStatus = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(budgetVersionStatus);
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
        return "Budget Version Status";
    }
}
