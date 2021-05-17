/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.MessageTypes;
import com.bpp.hibernate.MessageTypesHibernateHelper;
import com.bpp.hibernate.Messages;
import com.bpp.hibernate.MessagesHibernateHelper;
import com.bpp.hibernate.UserRole;
import com.bpp.hibernate.UserRoleHibernateHelper;
import com.bpp.hibernate.Users;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Message Type Servlet class
 * 
 * @author Lekan
 * @since 31/7/2017
 */
public class MessagesServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MessagesHibernateHelper helper;
	Messages message;

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
            
        	helper = new MessagesHibernateHelper();
        	message = new Messages();
        	
            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    message.setDateCreated(Utility.dbDateNow());
                    message.setMessage(request.getParameter("message"));
                    
                    MessageTypes messageType = new MessageTypesHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("messageTypeID")));
                    message.setMessageTypes(messageType);
                                        
                    message.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    if(Integer.parseInt(request.getParameter("userID")) == -1) {
                    	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                    	
                    	if(request.getParameter("specificUserRoleID") != null) {
                    		List<UserRole> users = new UserRoleHibernateHelper().fetchAll2(Integer.parseInt(request.getParameter("specificUserRoleID")));
                    		
                    		for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                    			UserRole userRole = (UserRole) iterator2.next();
                    			
                                Users user = userRole.getUsers();
                                
                                message.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                                
                                if(message.getMessageTypes().getId() == Utility.EMAIL_MESSAGE_TYPE) {
                                	message.setTitle(request.getParameter("title"));
                                	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getEmail());
                                	
                                	if(user.getEmail() != null)
                                		resp = helper.insert(message);
                                	
                                	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                    	executor.execute(new Runnable() {
    										@Override
    										public void run() {
    											sendEmailMessage(user, message.getTitle(), message.getMessage());
    										}
    									});
                                }
                                else if(message.getMessageTypes().getId() == Utility.SMS_MESSAGE_TYPE) {
                                	message.setTitle("");
                                	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getPhoneno());
                                	
                                	if(user.getPhoneno() != null)
                                		resp = helper.insert(message);
                                	
                                	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                    	executor.execute(new Runnable() {
    										@Override
    										public void run() {
    											sendSMSMessage(user, message.getMessage());
    										}
    									});
                                }
                    		}
                    	}
                    	else if(request.getParameter("specificMDAID") != null) {
                    		List<Users> users = new UsersHibernateHelper().fetchAll2(Integer.parseInt(request.getParameter("specificMDAID")));
                    		
                    		for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                    			Users user = (Users) iterator2.next();
                    			
                    			message.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                                
                                if(message.getMessageTypes().getId() == Utility.EMAIL_MESSAGE_TYPE) {
                                	message.setTitle(request.getParameter("title"));
                                	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getEmail());
                                	
                                	if(user.getEmail() != null)
                                		resp = helper.insert(message);
                                	
                                	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                    	executor.execute(new Runnable() {
    										@Override
    										public void run() {
    											sendEmailMessage(user, message.getTitle(), message.getMessage());
    										}
    									});
                                }
                                else if(message.getMessageTypes().getId() == Utility.SMS_MESSAGE_TYPE) {
                                	message.setTitle("");
                                	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getPhoneno());
                                	
                                	if(user.getPhoneno() != null)
                                		resp = helper.insert(message);
                                	
                                	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                    	executor.execute(new Runnable() {
    										@Override
    										public void run() {
    											sendSMSMessage(user, message.getMessage());
    										}
    									});
                                }
                    		}
                    	}
                    	else {
                    		List<Users> users = new UsersHibernateHelper().fetchAll2();
                    		
                    		for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
                    			Users user = (Users) iterator2.next();
                    			
                    			message.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                                
                                if(message.getMessageTypes().getId() == Utility.EMAIL_MESSAGE_TYPE) {
                                	message.setTitle(request.getParameter("title"));
                                	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getEmail());
                                	
                                	if(user.getEmail() != null)
                                		resp = helper.insert(message);
                                	
                                	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                    	executor.execute(new Runnable() {
    										@Override
    										public void run() {
    											sendEmailMessage(user, message.getTitle(), message.getMessage());
    										}
    									});
                                }
                                else if(message.getMessageTypes().getId() == Utility.SMS_MESSAGE_TYPE) {
                                	message.setTitle("");
                                	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getPhoneno());
                                	
                                	if(user.getPhoneno() != null)
                                		resp = helper.insert(message);
                                	
                                	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                                    	executor.execute(new Runnable() {
    										@Override
    										public void run() {
    											sendSMSMessage(user, message.getMessage());
    										}
    									});
                                }
                    		}
                    	}
                    }
                    else {
	                    Users user = new UsersHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("userID")));
	                    
	                    message.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                        
                        if(message.getMessageTypes().getId() == Utility.EMAIL_MESSAGE_TYPE) {
                        	message.setTitle(request.getParameter("title"));
                        	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getEmail());
                        	
                        	if(user.getEmail() != null)
                        		resp = helper.insert(message);
                        	
                        	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                        		sendEmailMessage(user, message.getTitle(), message.getMessage());
                        }
                        else if(message.getMessageTypes().getId() == Utility.SMS_MESSAGE_TYPE) {
                        	message.setTitle("");
                        	message.setRecipients(user.getFirstname() + " " + user.getLastname() + " - " + user.getPhoneno());
                        	
                        	if(user.getPhoneno() != null)
                        		resp = helper.insert(message);
                        	
                        	if(resp.equals(Utility.ActionResponse.INSERTED.toString()))
                        		sendSMSMessage(user, message.getMessage());
                        }
                    }
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    message = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    
                    message.setDateCreated(Utility.dbDateNow());
                    message.setTitle(request.getParameter("title"));
                    message.setMessage(request.getParameter("message"));
                    
                    resp = helper.update(message);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	message = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(message);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT))
            	resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
            	if(request.getParameter("userID") != null) {
            		Users user = new UsersHibernateHelper().fetchObj(Integer.parseInt(request.getParameter("userID")));
            		
            		resp = helper.fetchAll(user.getEmail(), user.getPhoneno());
            	}
            	else
            		resp = helper.fetchAll();
            }
            
            if (option.equals(Utility.OPTION_GET_SMS_BALANCE))
                resp = "&#8358;" + balance();
            
            out.println(resp);
        }
        finally {
            out.close();
        }
    }
    
    private void sendEmailMessage(Users user, String subject, String message) {
    	String mailBody = message;
    	
    	try {
        	//Send Email Notification to User
			Utility utility = new Utility();
			utility.setMailServerProperties();
			utility.createEmailMessage(subject, mailBody, user.getEmail());
			utility.sendEmail();
    	}
    	catch (Exception e) {
			
		}
    }
    
    private void sendSMSMessage(Users user, String message) {
    	String SMS_Message = message;
    	
    	try {
			//Send SMS
			if(user.getPhoneno() != null)
				Utility.sendSMS(user.getPhoneno(), SMS_Message);
    	}
    	catch (Exception e) {
			
		}
    }
    
    public String balance() {
		//String main_url = "http://panel.xwireless.net/API/WebSMS/Http/v1.0a/index.php?method=credit_check&username=info@serveconsulting.com&password=Sasegbon14A&format=text";
    	String main_url = "http://smsc.xwireless.net/API/WebSMS/Http/v3.1/index.php?method=credit_check&username=info@serveconsulting.com&password=Sasegbon14A&format=json";
		String balance = "";
		
		HttpURLConnection urlConnection = null;
		URL url;
		DataInputStream in = null;
		BufferedReader reader = null;

		try {
			url = new URL(main_url);
       	 
        	urlConnection = (HttpURLConnection) url.openConnection();
        	urlConnection.setDoInput (true);
        	urlConnection.setUseCaches (false);
        	urlConnection.setRequestMethod("GET");
        	urlConnection.connect();
		  
        	int respCode = urlConnection.getResponseCode();

            if(respCode == 200 || respCode == 201) {
            	in = new DataInputStream(urlConnection.getInputStream());
                
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
                
                String tokenVariables = URLDecoder.decode(reader.readLine(), "UTF-8");
                
                in.close();
                reader.close();
                
                System.out.println(tokenVariables);
                
                Gson gson = new Gson();
				Type type = new TypeToken<Map<String, Object>>(){}.getType();
				Map<String, Object> data = gson.fromJson(tokenVariables, type);
				String route = gson.toJson(data.get("3"));
				data = gson.fromJson(route, type);
				balance = data.get("availablecredit").toString();
            }
            else
            	System.out.println(respCode);
		} catch (Exception e) {
		  e.printStackTrace();
		}
		finally {
			try {
				if(in != null)
					in.close();
				
				if(reader != null)
					reader.close();
			} catch (Exception e) {
			}
		}
		
		return balance;
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
        } catch (Exception ex) {
        	ex.printStackTrace();
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
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Messages Servlet";
    }
}
