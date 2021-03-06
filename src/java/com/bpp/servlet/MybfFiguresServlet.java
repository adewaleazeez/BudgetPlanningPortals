/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetTypeComponents;
import com.bpp.hibernate.BudgetTypeComponentsHibernateHelper;
import com.bpp.hibernate.BudgetVersions;
import com.bpp.hibernate.BudgetVersionsHibernateHelper;
import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.BudgetYears;
import com.bpp.hibernate.FrameworkMethods;
import com.bpp.hibernate.FrameworkMethodsHibernateHelper;
import com.bpp.hibernate.MybfFigures;
import com.bpp.hibernate.MybfFiguresHibernateHelper;
import com.bpp.hibernate.MybfPreviousForward;
import com.bpp.hibernate.MybfPreviousForwardHibernateHelper;
import com.bpp.hibernate.Users;
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
 * MTEF Figures Servlet class
 * 
 * @author Lekan
 * @since 1/7/2017
 */
public class MybfFiguresServlet extends HttpServlet {

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
            
        	MybfFiguresHibernateHelper helper = new MybfFiguresHibernateHelper();
            MybfFigures mybfFigure = new MybfFigures();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    mybfFigure.setDateCreated(Utility.dbDateNow());
                    mybfFigure.setMtefBoVersionId(-1);
																								   
                    mybfFigure.setChartOfAccount("-");

                    Users users = new UsersHibernateHelper().exists(Integer.parseInt(request.getParameter("userID")));
                    mybfFigure.setUsers(users);

                    BudgetVersions budgetVersions = new BudgetVersionsHibernateHelper().exists(Integer.parseInt(request.getParameter("budgetVersionID")));
                    mybfFigure.setBudgetVersions(budgetVersions);

                    BudgetYears budgetYears = new BudgetYearHibernateHelper().exists(Integer.parseInt(request.getParameter("year")));
                    mybfFigure.setBudgetYears(budgetYears);

                    mybfFigure.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);

                    int toYear = Integer.parseInt(request.getParameter("yearTo"));

                    String[] mybfValues = request.getParameterValues("mybfFiguresMap[]");
                    String[] mybfPrevForward = request.getParameterValues("mybfPrevForwardMap[]");

                    MybfPreviousForwardHibernateHelper helper2 = new MybfPreviousForwardHibernateHelper();
                    MybfPreviousForward mybfPreviousForward = null;

                    int currentStart = 0;
                    for (int i = 0; i < mybfValues.length; i += 4) {
                        mybfFigure.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                        mybfFigure.setBudgetValue(Double.parseDouble(mybfValues[i]));
                        mybfFigure.setBudgetLine(mybfValues[i + 1]);

                        BudgetTypeComponents budgetTypeComponents = new BudgetTypeComponentsHibernateHelper().exists(Integer.parseInt(mybfValues[i + 2]));
                        mybfFigure.setBudgetTypeComponents(budgetTypeComponents);

                        FrameworkMethods frameworkMethods = new FrameworkMethodsHibernateHelper().exists(Integer.parseInt(mybfValues[i + 3]));
                        mybfFigure.setFrameworkMethods(frameworkMethods);

                        resp = helper.insert(mybfFigure);
                        if (resp.equals(Utility.ActionResponse.INSERTED.toString())) {
                            for (int j = currentStart; j < mybfPrevForward.length; j += 2) {
                                mybfPreviousForward = new MybfPreviousForward();
                                mybfPreviousForward.setId(Integer.parseInt(helper2.getMaxserialNo()) + 1);
                                mybfPreviousForward.setDateCreated(Utility.dbDateNow());
                                mybfPreviousForward.setBudgetYear(Integer.parseInt(mybfPrevForward[j]));
                                mybfPreviousForward.setBudgetValue(Double.valueOf(mybfPrevForward[j + 1]));

                                mybfPreviousForward.setMybfFigureId(mybfFigure.getId());

                                mybfPreviousForward.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                                mybfPreviousForward.setBudgetTypeComponentId(mybfFigure.getBudgetTypeComponents().getId());

                                resp = helper2.insert(mybfPreviousForward);

                                if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
                                    mybfPreviousForward = helper2.exists(mybfPreviousForward.getBudgetYear(), mybfPreviousForward.getMybfFigureId());
                                    mybfPreviousForward.setBudgetValue(Double.valueOf(mybfPrevForward[j + 1]));

                                    resp = helper2.update(mybfPreviousForward);
                                }
                                if (Integer.parseInt(mybfPrevForward[j]) == toYear) {
                                    currentStart = j + 2;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    int toYear = Integer.parseInt(request.getParameter("yearTo"));

                    String[] mybfValues = request.getParameterValues("mybfFiguresMap[]");
                    String[] mybfPrevForward = request.getParameterValues("mybfPrevForwardMap[]");

                    MybfPreviousForwardHibernateHelper helper2 = new MybfPreviousForwardHibernateHelper();
                    MybfPreviousForward mybfPreviousForward = null;

                    int currentStart = 0;
                    for (int i = 0; i < mybfValues.length; i += 2) {
                        mybfFigure = helper.exists(Integer.parseInt(mybfValues[i]));
                        mybfFigure.setBudgetValue(Double.parseDouble(mybfValues[i + 1]));

                        for (int j = currentStart; j < mybfPrevForward.length; j += 2) {
                            mybfPreviousForward = helper2.fetchObj(mybfFigure.getId(), Integer.parseInt(mybfPrevForward[j]));

                            mybfPreviousForward.setBudgetValue(Double.valueOf(mybfPrevForward[j + 1]));

                            helper2.update(mybfPreviousForward);

                            if (Integer.parseInt(mybfPrevForward[j]) == toYear) {
                                currentStart = j + 2;
                                break;
                            }
                        }

                        resp = helper.update(mybfFigure);
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	mybfFigure = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(mybfFigure);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT)){
                resp = helper.fetch(Integer.parseInt(request.getParameter("budgetTypeComponentID")), 
                					Integer.parseInt(request.getParameter("budgetVersionID")), 
                					Integer.parseInt(request.getParameter("year")));
            }
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
            	resp = helper.fetchAll(Integer.parseInt(request.getParameter("budgetTypeComponentID")), 
            						   Integer.parseInt(request.getParameter("budgetVersionID")), 
            						   Integer.parseInt(request.getParameter("yearFrom")), 
            						   Integer.parseInt(request.getParameter("yearTo")));
            }
            if (option.equals(Utility.OPTION_SYNC_APPROVED_BUDGET)) {
            	resp = helper.createMybfFigures(Integer.parseInt(request.getParameter("year_id")), 
                        Integer.parseInt(request.getParameter("user_id")), 
                        Integer.parseInt(request.getParameter("version_id")));
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
