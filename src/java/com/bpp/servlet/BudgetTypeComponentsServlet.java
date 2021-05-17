/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetTypeComponentTypes;
import com.bpp.hibernate.BudgetTypeComponentTypesHibernateHelper;
import com.bpp.hibernate.BudgetTypeComponents;
import com.bpp.hibernate.BudgetTypeComponentsHibernateHelper;
import com.bpp.hibernate.BudgetTypes;
import com.bpp.hibernate.BudgetTypesHibernateHelper;
import com.bpp.hibernate.BudgetTypesHibernateHelper;
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
 * Budget Type Components Servlet class
 * 
 * @author Lekan
 * @since 1/7/2017
 */
public class BudgetTypeComponentsServlet extends HttpServlet {

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
            
        	BudgetTypeComponentsHibernateHelper helper = new BudgetTypeComponentsHibernateHelper();
            BudgetTypeComponents budgetTypeComponent = new BudgetTypeComponents();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    budgetTypeComponent.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    budgetTypeComponent.setDateCreated(Utility.dbDateNow());
                    budgetTypeComponent.setName(request.getParameter("name"));
                    budgetTypeComponent.setGlAccount(request.getParameter("glAccount"));
                    budgetTypeComponent.setIsBudgeted(Boolean.parseBoolean(request.getParameter("isBudgeted")));
                    BudgetTypes budgetTypes = new BudgetTypesHibernateHelper().exists(Integer.parseInt(request.getParameter("budgetTypeID")));
                    budgetTypeComponent.setBudgetTypes(budgetTypes);
                    BudgetTypeComponentTypes budgetTypeComponentTypes = new BudgetTypeComponentTypesHibernateHelper().exists(Integer.parseInt(request.getParameter("budgetTypeComponentTypeID")));
                    budgetTypeComponent.setBudgetTypeComponentTypes(budgetTypeComponentTypes);
                    budgetTypeComponent.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    resp = helper.insert(budgetTypeComponent);
                    
                    if(resp.equals(Utility.ActionResponse.INSERTED.toString())) {
                    	budgetTypeComponent.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    	budgetTypeComponent.setIsBudgeted(!Boolean.parseBoolean(request.getParameter("isBudgeted")));
                    	resp = helper.insert(budgetTypeComponent);
                    }
                    
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    budgetTypeComponent = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    
                    BudgetTypes budgetTypes = new BudgetTypesHibernateHelper().exists(Integer.parseInt(request.getParameter("budgetTypeID")));
                    budgetTypeComponent.setBudgetTypes(budgetTypes);
                    
                    resp = helper.updateAll(budgetTypeComponent.getName(), request.getParameter("name"), 
                    						budgetTypeComponent.getBudgetTypes().getId(), Integer.parseInt(request.getParameter("budgetTypeID")),
                    						Integer.parseInt(request.getParameter("budgetTypeComponentTypeID")), request.getParameter("glAccount"));
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	budgetTypeComponent = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(budgetTypeComponent);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT)){
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            }
            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchAll1();
            }												 
										  			 
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
            	if(request.getParameter("budgetTypeID") != null)
            		resp = helper.fetchAll(Integer.parseInt(request.getParameter("budgetTypeID")));
            	else
            		resp = helper.fetchAll();
            }
            
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
        return "Budget Type Components";
    }
}
