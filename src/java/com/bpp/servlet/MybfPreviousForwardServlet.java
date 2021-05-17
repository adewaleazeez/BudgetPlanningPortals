/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MybfFigures;
import com.bpp.hibernate.MybfFiguresHibernateHelper;
import com.bpp.hibernate.MybfPreviousForward;
import com.bpp.hibernate.MybfPreviousForwardHibernateHelper;
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
 * MTEF Previous Forward Servlet class
 * 
 * @author Lekan
 * @since 1/7/2017
 */
public class MybfPreviousForwardServlet extends HttpServlet {

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
            
        	MybfPreviousForwardHibernateHelper helper = new MybfPreviousForwardHibernateHelper();
            MybfPreviousForward mtefPreviousForward = new MybfPreviousForward();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    mtefPreviousForward.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    mtefPreviousForward.setDateCreated(Utility.dbDateNow());
                    mtefPreviousForward.setBudgetYear(Integer.parseInt(request.getParameter("budgetYear")));
                    mtefPreviousForward.setBudgetValue(Double.valueOf(request.getParameter("budgetValue")));
                    
                    MybfFigures MybfFigures = new MybfFiguresHibernateHelper().exists(Integer.parseInt(request.getParameter("mybfFigureID")));
                    mtefPreviousForward.setMybfFigureId(MybfFigures.getId());
                    
                    mtefPreviousForward.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(mtefPreviousForward);
                    
                    if(resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
                    	mtefPreviousForward = helper.exists(mtefPreviousForward.getBudgetYear(), mtefPreviousForward.getMybfFigureId());
                    	mtefPreviousForward.setBudgetValue(Double.valueOf(request.getParameter("budgetValue")));
                    	
                    	resp = helper.update(mtefPreviousForward);
                    }
                    
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    mtefPreviousForward = helper.fetchObj(Integer.parseInt(request.getParameter("mybfFigureID")),
                    									  Integer.parseInt(request.getParameter("budgetYear")));
                    
                    mtefPreviousForward.setBudgetValue(Double.valueOf(request.getParameter("budgetValue")));
                    
                    resp = helper.update(mtefPreviousForward);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	mtefPreviousForward = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(mtefPreviousForward);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT))
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
            	resp = helper.fetchAll(Integer.parseInt(request.getParameter("mybfFigureID")), Integer.parseInt(request.getParameter("budgetTypeComponentID")),
            						   Integer.parseInt(request.getParameter("yearFrom")), 
            						   Integer.parseInt(request.getParameter("yearTo")));
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
