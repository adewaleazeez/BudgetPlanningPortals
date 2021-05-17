/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.FrameworkParameterTypes;
import com.bpp.hibernate.FrameworkParameterTypesHibernateHelper;
import com.bpp.hibernate.FrameworkParameters;
import com.bpp.hibernate.FrameworkParametersHibernateHelper;
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
 * Framework Parameters Servlet class
 * 
 * @author Lekan
 * @since 18/6/2017
 */
public class FrameworkParametersServlet extends HttpServlet {

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
            
        	FrameworkParametersHibernateHelper helper = new FrameworkParametersHibernateHelper();
            FrameworkParameters frameworkParameter = new FrameworkParameters();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    frameworkParameter.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    frameworkParameter.setDateCreated(Utility.dbDateNow());
                    frameworkParameter.setValue(Double.valueOf(request.getParameter("value")));
                    frameworkParameter.setYear(Integer.parseInt(request.getParameter("year")));
                    
                    FrameworkParameterTypes frameworkParameterTypes = new FrameworkParameterTypesHibernateHelper().exists(Integer.parseInt(request.getParameter("frameworkParameterTypeID")));
                    frameworkParameter.setFrameworkParameterTypes(frameworkParameterTypes);
                    
                    frameworkParameter.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(frameworkParameter);
                    
                    if(resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
                    	frameworkParameter = helper.exists(frameworkParameter.getYear(), frameworkParameter.getFrameworkParameterTypes().getId());
                    	frameworkParameter.setValue(Double.valueOf(request.getParameter("value")));
                    	
                    	resp = helper.update(frameworkParameter);
                    }
                    
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    frameworkParameter = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    frameworkParameter.setValue(Double.valueOf(request.getParameter("value")));
                    
                    resp = helper.update(frameworkParameter);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	frameworkParameter = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(frameworkParameter);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT))
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
            	if(request.getParameter("frameworkParameterTypeID") != null)
            		resp = helper.fetchAll(Integer.parseInt(request.getParameter("frameworkParameterTypeID")), 
            							   Integer.parseInt(request.getParameter("yearFrom")), 
            							   Integer.parseInt(request.getParameter("yearTo")));
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
        return "Budget Timetable";
    }
}
