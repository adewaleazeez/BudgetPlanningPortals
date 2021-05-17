/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.RequestDetails;
import com.bpp.hibernate.RequestDetailsHibernateHelper;
import com.bpp.hibernate.RequestDocuments;
import com.bpp.hibernate.RequestDocumentsHibernateHelper;
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
 * Request Document Servlet class
 * 
 * @author Lekan
 * @since 28/6/2017
 */
public class RequestDocumentsServlet extends HttpServlet {

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
            
        	RequestDocumentsHibernateHelper helper = new RequestDocumentsHibernateHelper();
            RequestDocuments requestDocument = new RequestDocuments();

            String option;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    requestDocument.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                    requestDocument.setDateCreated(Utility.dbDateNow());
                    requestDocument.setDocumentUrl(request.getParameter("documentUrl"));
                    requestDocument.setDocumentText(request.getParameter("documentText"));
                    
                    RequestDetails requestDetails = new RequestDetailsHibernateHelper().exists(Integer.parseInt(request.getParameter("requestDetailID")));
                    requestDocument.setRequestDetails(requestDetails);
                    
                    requestDocument.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                    
                    if(helper.fetchAll(requestDetails.getId()) != null) {
                    	resp = helper.update(requestDocument);
                    	
                    	if(resp.equals(Utility.ActionResponse.NO_RECORD.toString()))
                    		resp = helper.insert(requestDocument);
                    }
                    else
                    	resp = helper.insert(requestDocument);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    requestDocument = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    
                    if(request.getParameter("documentUrl") != null)
                    	requestDocument.setDocumentUrl(request.getParameter("documentUrl"));
                    
                    requestDocument.setDocumentText(request.getParameter("documentText"));
                    
                    resp = helper.update(requestDocument);
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                	requestDocument = helper.fetchObj(Integer.parseInt(request.getParameter("id")));
                    resp = helper.delete(requestDocument);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT)) {
            	if(request.getParameter("requestDetailID") != null)
            		resp = helper.fetchLatestDocument2(Integer.parseInt(request.getParameter("requestDetailID")));
            	else
            		resp = helper.fetch(Integer.parseInt(request.getParameter("id")));
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
            	if(request.getParameter("requestTypeID") != null)
            		resp = helper.fetchAllByRequestType(Integer.parseInt(request.getParameter("requestTypeID")));
            	else
            		resp = helper.fetchAll(Integer.parseInt(request.getParameter("requestDetailID")));
            }
            
            out.println(resp);
        }
        finally {
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
        return "Request Document";
    }
}
