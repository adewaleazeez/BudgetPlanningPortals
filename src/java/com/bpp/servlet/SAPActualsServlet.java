/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.SapActuals;
import com.bpp.hibernate.SAPActualsHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.MybfPreviousForwardHibernateHelper;
import com.bpp.utility.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet for fetching and processing actuals from SAP
 *
 * @author Lekan
 * @since 20/9/2017
 */
public class SAPActualsServlet extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        UsersHibernateHelper userhelper = new UsersHibernateHelper();
        //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));
        SAPActualsHibernateHelper helper = new SAPActualsHibernateHelper();
        MybfPreviousForwardHibernateHelper mybfPreviousForwardHelper = new MybfPreviousForwardHibernateHelper();
        String option;
        String resp = null;
        option = request.getParameter("option");
        if (option == null) {
            option = "";
        }
        if (option.equals(Utility.OPTION_SELECT_ALL)) {
            int currentYear = new BudgetYearHibernateHelper().fetchCurrentYear2().getYear();
            
            String url = Utility.SAP_QA_ENDPOINT+"/sap/bc/webrfc?sap-client=410&_FUNCTION=YY_ACTUALS_EXPENDITURE&FMAREA="+Utility.SAP_QA_FM_AREA+"&FISC_YR="+(currentYear)+"&FISC_YR_END="+(currentYear+1);
            //System.out.println("url: "+url);
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // optional default is GET
            con.setRequestMethod("GET");
            
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            
            //int responseCode = con.getResponseCode();
            StringBuffer responses;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                responses = new StringBuffer ();
                while ((inputLine = in.readLine()) != null) {
                    responses.append(inputLine);
                }
            }
            //System.out.println("Expenditure: "+responses.toString());
            
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responses.toString());

            
            // get an array from the JSON object
            JSONArray results = (JSONArray) jsonObject.get("results");
            

            Iterator i = results.iterator();
            
            // take each value from the json array separately
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                
                SapActuals sapActual = new SapActuals();
                sapActual.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                sapActual.setDateCreated(Utility.dbDateNow());
                sapActual.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                sapActual.setRecType(innerObj.get("rec_type").toString());
                sapActual.setVersionName(innerObj.get("version").toString());
                sapActual.setValueTyp(innerObj.get("value_type").toString());
                sapActual.setFmDocument(innerObj.get("fm_document").toString());
                sapActual.setFiDocument(innerObj.get("fi_document").toString());
                sapActual.setFund(innerObj.get("funds").toString());
                sapActual.setFundsCtr(innerObj.get("funds_ctr").toString());
                sapActual.setCmmtItem(innerObj.get("commitment_item").toString());
                sapActual.setFundedProg(innerObj.get("funded_program").toString());
                sapActual.setBusArea(innerObj.get("business_area").toString());
                sapActual.setAmountTc(Double.parseDouble(innerObj.get("amount_tc").toString()));
                sapActual.setAmountLc(Double.parseDouble(innerObj.get("amount_lc").toString()));
                sapActual.setCurrency(innerObj.get("currency").toString());
                sapActual.setGlAccount(innerObj.get("gl_account").toString());
                sapActual.setPeriod(innerObj.get("period").toString());
                sapActual.setFuncArea(innerObj.get("func_area").toString());
                sapActual.setFiscYear(Integer.parseInt(innerObj.get("fisc_year").toString()));
                if (isValidYear(sapActual.getFiscYear()) && sapActual.getFmDocument().length()>0) {
                    resp = helper.insert(sapActual);
                }

            }

            url = Utility.SAP_QA_ENDPOINT+"/sap/bc/webrfc?sap-client=410&_FUNCTION=YY_ACTUALS_REVENUE&FMAREA="+Utility.SAP_QA_FM_AREA+"&FISC_YR="+(currentYear)+"&FISC_YR_END="+(currentYear + 1);
            
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            
            // optional default is GET
            con.setRequestMethod("GET");
            
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            
            //responseCode = con.getResponseCode();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                responses = new StringBuffer ();
                while ((inputLine = in.readLine()) != null) {
                    responses.append(inputLine);
                }
            }
            //System.out.println("Revenue: "+responses.toString());
            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(responses.toString());
            
            // get an array from the JSON object
            results = (JSONArray) jsonObject.get("results");
            

            i = results.iterator();
            
            // take each value from the json array separately
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                SapActuals sapActual = new SapActuals();
                sapActual.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                sapActual.setDateCreated(Utility.dbDateNow());
                sapActual.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                sapActual.setRecType(innerObj.get("rec_type").toString());
                sapActual.setVersionName(innerObj.get("version").toString());
                sapActual.setValueTyp(innerObj.get("value_type").toString());
                sapActual.setFmDocument(innerObj.get("fm_document").toString());
                sapActual.setFiDocument(innerObj.get("fi_document").toString());
                sapActual.setFund(innerObj.get("funds").toString());
                sapActual.setFundsCtr(innerObj.get("funds_ctr").toString());
                sapActual.setCmmtItem(innerObj.get("commitment_item").toString());
                sapActual.setFundedProg(innerObj.get("funded_program").toString());
                sapActual.setBusArea(innerObj.get("business_area").toString());
                sapActual.setAmountTc(Double.parseDouble(innerObj.get("amount_tc").toString()));
                sapActual.setAmountLc(Double.parseDouble(innerObj.get("amount_lc").toString()));
                sapActual.setCurrency(innerObj.get("currency").toString());
                sapActual.setGlAccount(innerObj.get("gl_account").toString());
                sapActual.setPeriod(innerObj.get("period").toString());
                sapActual.setFuncArea(innerObj.get("func_area").toString());
                sapActual.setFiscYear(Integer.parseInt(innerObj.get("fisc_year").toString()));
                if (isValidYear(sapActual.getFiscYear()) && sapActual.getFmDocument().length()>0) {//(sapActual.getAmountTc() > 0 || sapActual.getAmountLc() > 0) &&
                    resp = helper.insert(sapActual);
                }

            }
            

            //Update MYBF Previous Records
            int versionID = helper.getCurrentBudgetVersionID().getVersionId();
            mybfPreviousForwardHelper.updateMybfPreviousForward(versionID, currentYear);
            mybfPreviousForwardHelper.updateMybfPreviousForward(versionID, currentYear - 1);
        }

        out.println(resp);
        out.close();
    }

    private boolean isValidYear(Integer year) {
        if (year <= 0) {
            return false;
        } else {
            if (year.toString().length() < 4 || year.toString().length() > 4) {
                return false;
            } else {
                return true;
            }
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SAPActualsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SAPActualsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SAPActualsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SAPActualsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "SAP Actuals";
    }
}
