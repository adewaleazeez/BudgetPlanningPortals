/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MeetingAgendaDetails;
import com.bpp.hibernate.MeetingAttendants;
import com.bpp.hibernate.MeetingAttendantsHibernateHelper;
import com.bpp.hibernate.MeetingDetails;
import com.bpp.hibernate.MeetingDetailsHibernateHelper;
import com.bpp.hibernate.MeetingRoles;
import com.bpp.hibernate.MeetingRolesHibernateHelper;
import com.bpp.hibernate.UserRole;
import com.bpp.hibernate.UserRoleHibernateHelper;
import com.bpp.hibernate.Users;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
public class MeetingAttendantsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MeetingAttendantsHibernateHelper helper;
	MeetingAttendants meetingAttendant;

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
            
        	helper = new MeetingAttendantsHibernateHelper();
            meetingAttendant = new MeetingAttendants();
            
            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    meetingAttendant.setDateCreated(Utility.dbDateNow());
                    meetingAttendant.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    MeetingDetails meetingDetails = new MeetingDetailsHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("meetingID")));
                    meetingAttendant.setMeetingDetails(meetingDetails);
                    
                    if(Integer.parseInt(request.getParameter("userID")) == -1) {
                    	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                    	
                    	MeetingRoles role = new MeetingRolesHibernateHelper().fetchObj(Utility.MEETING_ATTENDANT_ROLE);
                        meetingAttendant.setMeetingRoles(role);
                        
                    	if(request.getParameter("specificUserRoleID") != null) {
                    		List<UserRole> users = new UserRoleHibernateHelper().fetchAll2(Integer.parseInt(request.getParameter("specificUserRoleID")));
                    		
                    		for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                    			UserRole userRole = (UserRole) iterator2.next();
                    			
                                Users user = userRole.getUsers();
                                
                                if(helper.fetchObj(meetingAttendant.getMeetingDetails().getId(), user.getId()) != null)
                                	continue;
                                
                                meetingAttendant.setUsers(user);
                                
                                meetingAttendant.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                                resp = helper.insert(meetingAttendant);
                                
                                if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                	executor.execute(new Runnable() {
										@Override
										public void run() {
											sendMeetingMessage(user, meetingDetails, meetingAttendant);
										}
									});
                    		}
                    	}
                    	else if(request.getParameter("specificMDAID") != null) {
                    		List<Users> users = new UsersHibernateHelper().fetchAll2(Integer.parseInt(request.getParameter("specificMDAID")));
                    		
                    		for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                    			Users user = (Users) iterator2.next();
                    			
                                if(helper.fetchObj(meetingAttendant.getMeetingDetails().getId(), user.getId()) != null)
                                	continue;
                                
                                meetingAttendant.setUsers(user);
                                
                                meetingAttendant.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                                resp = helper.insert(meetingAttendant);
                                
                                if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                	executor.execute(new Runnable() {
										@Override
										public void run() {
											sendMeetingMessage(user, meetingDetails, meetingAttendant);
										}
									});
                    		}
                    	}
                    	else {
                    		List<Users> users = new UsersHibernateHelper().fetchAll2();
                    		
                    		for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                    			Users user = (Users) iterator2.next();
                    			
                    			if(helper.fetchObj(meetingAttendant.getMeetingDetails().getId(), user.getId()) != null)
                                	continue;
                    			
                                meetingAttendant.setUsers(user);
                                
                                meetingAttendant.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                                resp = helper.insert(meetingAttendant);
                                
                                if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                	executor.execute(new Runnable() {
										@Override
										public void run() {
											sendMeetingMessage(user, meetingDetails, meetingAttendant);
										}
									});
                    		}
                    	}
                    }
                    else {
	                    MeetingRoles role = new MeetingRolesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("meetingRoleID")));
	                    meetingAttendant.setMeetingRoles(role);
	                    
	                    Users user = new UsersHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("userID")));
	                    meetingAttendant.setUsers(user);
	                    
	                    meetingAttendant.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
	                    resp = helper.insert(meetingAttendant);
	                    
	                    if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
	                    	sendMeetingMessage(user, meetingDetails, meetingAttendant);
                    }
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    meetingAttendant = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    
                    MeetingRoles role = new MeetingRolesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("meetingRoleID")));
                    meetingAttendant.setMeetingRoles(role);
                    
                    resp = helper.update(meetingAttendant);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	meetingAttendant = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(meetingAttendant);
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
    
    private void sendMeetingMessage(Users user, MeetingDetails meetingDetails, MeetingAttendants meetingAttendant) {
    	String agendas = "";
    	
    	for (Iterator iterator2 = meetingDetails.getMeetingAgendaDetailses().iterator(); iterator2.hasNext();) {
    		MeetingAgendaDetails meetingAgendaDetails = (MeetingAgendaDetails) iterator2.next();
    		agendas += "> " + meetingAgendaDetails.getMeetingAgenda().getName() + " - " + meetingAgendaDetails.getName() + "<br/>";
    	}
    	
    	String mailBody = "Dear " + user.getFirstname() + " " + user.getLastname() + ", <br/><br/>" +
    					  "You are hereby invited to a meeting on a budget planning/preparation activity, see details below:<br/><br/>" +
    					  "<span style='font-size: 14px;'><b>Meeting Details</b><br/>" +
    					  "Title: " + meetingDetails.getName() + "<br/>" +
    					  "Venue: " + meetingDetails.getVenue() + "<br/>" +
    					  "Date: " + meetingDetails.getMeetingDate().toString() + "<br/>" +
    					  "Timetable Activity: " + meetingDetails.getBudgetTimetableActivities().getName() + 
    					  " (Description: " + meetingDetails.getBudgetTimetableActivities().getDescription() + ")<br/><br/>" +
    					  "<span style='font-size: 14px;'><b>Meeting Agenda</b><br/>" +
    					  agendas + "<br/>" +
    					  "<i><b>Your Meeting Role:</b> " + meetingAttendant.getMeetingRoles().getName() + "</i><br/><br/>" +
    					  "Regards,<br/>Budget Module System";
    	
    	String SMS_Message = "Hello " + user.getFirstname() + ", you are invited to a meeting on a budget planning activity. " +
    						 "Title: " + meetingDetails.getName() + "; Venue: " + meetingDetails.getVenue() + "; " +
    						 "Date: " + meetingDetails.getMeetingDate().toString() + ". Role: " + meetingAttendant.getMeetingRoles().getName();
    	
    	try {
        	//Send Email Notification to User
			Utility utility = new Utility();
			utility.setMailServerProperties();
			utility.createEmailMessage("Meeting Invitation", mailBody, user.getEmail());
			utility.sendEmail();
			
			//Send SMS
			if(user.getPhoneno() != null)
				Utility.sendSMS(user.getPhoneno(), SMS_Message);
    	}
    	catch (Exception e) {
			
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
