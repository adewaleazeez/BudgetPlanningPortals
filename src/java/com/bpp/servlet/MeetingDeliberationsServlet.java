/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MeetingDeliberations;
import com.bpp.hibernate.MeetingDeliberationsHibernateHelper;
import com.bpp.hibernate.MeetingDetails;
import com.bpp.hibernate.MeetingDetailsHibernateHelper;
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
 * Meeting Details Servlet class
 * 
 * @author Lekan
 * @since 20/6/2017
 */
public class MeetingDeliberationsServlet extends HttpServlet {

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
            
        	MeetingDeliberationsHibernateHelper helper = new MeetingDeliberationsHibernateHelper();
            MeetingDeliberations meetingDeliberation = new MeetingDeliberations();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    meetingDeliberation.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    meetingDeliberation.setDateCreated(Utility.dbDateNow());
                    meetingDeliberation.setDeliberationText(request.getParameter("text"));
                    
                    Users user = new UsersHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("personID")));
                    meetingDeliberation.setUsers(user);
                    
                    meetingDeliberation.setTimeline(Utility.dbDate2(request.getParameter("timeline")));
                    
                    MeetingDetails meetingDetails = new MeetingDetailsHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("meetingID")));
                    meetingDeliberation.setMeetingDetails(meetingDetails);
                    
                    meetingDeliberation.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    resp = helper.insert(meetingDeliberation);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    meetingDeliberation = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    meetingDeliberation.setDeliberationText(request.getParameter("text"));
                    
                    Users user = new UsersHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("personID")));
                    meetingDeliberation.setUsers(user);
                    
                    meetingDeliberation.setTimeline(Utility.dbDate2(request.getParameter("timeline")));
                    
                    resp = helper.update(meetingDeliberation);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	meetingDeliberation = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(meetingDeliberation);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT))
                resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            
            if (option.equals(Utility.OPTION_SELECT_ALL))
                resp = helper.fetchAll(Integer.parseInt(request.getParameter("meetingID")));
            
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
        return "Meeting Details";
    }// </editor-fold>

}
