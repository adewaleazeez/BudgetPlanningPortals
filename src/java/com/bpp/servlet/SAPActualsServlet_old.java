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
import mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_EXPENDITUREStub;
import mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_EXPENDITUREStub.YbudgActual;
import mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_REVENUEStub;

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
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 
//import java.text.ParseException;
import java.util.Iterator;
import org.json.JSONException;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

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
public class SAPActualsServlet_old extends HttpServlet {

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
            
            //Get EXPENDITURE ACTUALS for current and immediate past year only!
//            YBUDGET_ACTUALS_EXPENDITUREStub stub = new YBUDGET_ACTUALS_EXPENDITUREStub();
//            YBUDGET_ACTUALS_EXPENDITUREStub.YbudgetActualsExpenditure actualRequest = new YBUDGET_ACTUALS_EXPENDITUREStub.YbudgetActualsExpenditure();
//            
//            //Set parameter values
//            actualRequest.setActualReturn(new mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_EXPENDITUREStub.TableOfYbudgreturn());
//            actualRequest.setActualTab(new mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_EXPENDITUREStub.TableOfYbudgActual());
//            
//            YBUDGET_ACTUALS_EXPENDITUREStub.String expYear = new YBUDGET_ACTUALS_EXPENDITUREStub.String();
//            expYear.setString(String.valueOf(currentYear - 1));
//            actualRequest.setFiscYr(expYear);
//            
//            YBUDGET_ACTUALS_EXPENDITUREStub.String expYearEnd = new YBUDGET_ACTUALS_EXPENDITUREStub.String();
//            expYearEnd.setString(String.valueOf(currentYear));
//            actualRequest.setFiscYrEnd(expYearEnd);
//            
//            YBUDGET_ACTUALS_EXPENDITUREStub.String expFmArea = new YBUDGET_ACTUALS_EXPENDITUREStub.String();
//            expFmArea.setString(Utility.SAP_QA_FM_AREA);
//            actualRequest.setFmarea(expFmArea);
//            
//            actualRequest.setRecordType(new YBUDGET_ACTUALS_EXPENDITUREStub.String());
//            actualRequest.setVersion(new YBUDGET_ACTUALS_EXPENDITUREStub.String());
//            
//            YBUDGET_ACTUALS_EXPENDITUREStub.YbudgetActualsExpenditureResponse expActualResponse = stub.ybudgetActualsExpenditure(actualRequest);
//            
//            YbudgActual[] expActualValues = expActualResponse.getActualTab().getItem();
            
            String url = Utility.SAP_QA_ENDPOINT+"/sap/bc/webrfc?sap-client=410&_FUNCTION=YY_ACTUALS_EXPENDITURE&FMAREA="+Utility.SAP_QA_FM_AREA+"&FISC_YR="+(currentYear)+"&FISC_YR_END="+(currentYear+1);
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);
            StringBuffer responses;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                responses = new StringBuffer ();
                while ((inputLine = in.readLine()) != null) {
                    responses.append(inputLine);
                }
            }
            //print in String
            //System.out.println(responses.toString());
            //Read JSON response and print
            //JSONObject jsonObject = new JSONObject(responses.toString());
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responses.toString());
            // get a String from the JSON object
            
            // get an array from the JSON object
            JSONArray results = (JSONArray) jsonObject.get("results");
            
// take the elements of the json array
//            for(int i=0; i<results.size(); i++){
//                System.out.println("The " + i + " element of the array: "+results.get(i));
//            }

            Iterator i = results.iterator();
            // take each value from the json array separately
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                //System.out.println("language "+ innerObj.get("lang") +  " with level " + innerObj.get("knowledge"));

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
            
            // handle a structure into the json object
            //JSONObject structure = (JSONObject) jsonObject.get("job");
            //System.out.println("Into job structure, name: " + structure.get("name"));

            //for (YbudgActual ybudgActualResponse : expActualValues) {
            //    SapActuals sapActual = new SapActuals();
            //    sapActual.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
            //    sapActual.setDateCreated(Utility.dbDateNow());
            //    sapActual.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
            //    sapActual.setRecType(ybudgActualResponse.getRecType().getChar1());
            //    sapActual.setVersionName(ybudgActualResponse.getVersion().getChar3());
            //    sapActual.setValueTyp(ybudgActualResponse.getValuetyp().getChar2());
            //    sapActual.setFmDocument(ybudgActualResponse.getFmDocument().getChar18());
            //    sapActual.setFiDocument(ybudgActualResponse.getFiDocument().getChar18());
            //    sapActual.setFund(ybudgActualResponse.getFund().getChar10());
            //    sapActual.setFundsCtr(ybudgActualResponse.getFundsCtr().getChar16());
            //    sapActual.setCmmtItem(ybudgActualResponse.getCmmtItem().getChar24());
            //    sapActual.setFundedProg(ybudgActualResponse.getFundedProg().getChar24());
            //    sapActual.setBusArea(ybudgActualResponse.getBusArea().getChar4());
            //    sapActual.setAmountTc(ybudgActualResponse.getAmountTc().getCurr172().doubleValue());
            //    sapActual.setAmountLc(ybudgActualResponse.getAmountLc().getCurr172().doubleValue());
            //    sapActual.setCurrency(ybudgActualResponse.getCurrency().getCuky5());
            //    sapActual.setGlAccount(ybudgActualResponse.getGlAccount().getChar10());
            //    sapActual.setPeriod(ybudgActualResponse.getPeriod().getChar2());
            //    sapActual.setFuncArea(ybudgActualResponse.getFuncArea().getChar16());
            //    sapActual.setFiscYear(Integer.valueOf(ybudgActualResponse.getFiscYear().getNumeric4()));
            //    
            //    if (isValidYear(sapActual.getFiscYear()) && sapActual.getFmDocument().length()>0) {//(sapActual.getAmountTc() > 0 || sapActual.getAmountLc() > 0) &&
            //        resp = helper.insert(sapActual);
            ////                        if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
            ////                            SAPActuals sapActual2 = helper.fetchObj(sapActual.getFiDocument());
            ////                            sapActual2.setAmountTc(sapActual.getAmountTc());
            ////                            sapActual2.setAmountLc(sapActual.getAmountLc());
            ////                            helper.update(sapActual2);
            ////                        }
            //    }
            //}

//            //Get REVENUE ACTUALS for current and immediate past year only!
//            YBUDGET_ACTUALS_REVENUEStub stub2 = new YBUDGET_ACTUALS_REVENUEStub();
//            YBUDGET_ACTUALS_REVENUEStub.YbudgetActualsRevenue actualRequest2 = new YBUDGET_ACTUALS_REVENUEStub.YbudgetActualsRevenue();
//
//            //Set parameter values
//            actualRequest2.setActualReturn(new mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_REVENUEStub.TableOfYbudgreturn());
//            actualRequest2.setActualTab(new mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_REVENUEStub.TableOfYbudgActual());
//
//            YBUDGET_ACTUALS_REVENUEStub.String revYear = new YBUDGET_ACTUALS_REVENUEStub.String();
//            revYear.setString(String.valueOf(currentYear - 1));
//            actualRequest2.setFiscYr(revYear);
//
//            YBUDGET_ACTUALS_REVENUEStub.String revYearEnd = new YBUDGET_ACTUALS_REVENUEStub.String();
//            revYearEnd.setString(String.valueOf(currentYear));
//            actualRequest2.setFiscYrEnd(revYearEnd);
//
//            YBUDGET_ACTUALS_REVENUEStub.String revFmArea = new YBUDGET_ACTUALS_REVENUEStub.String();
//            revFmArea.setString(Utility.SAP_QA_FM_AREA);
//            actualRequest2.setFmarea(revFmArea);
//
//            actualRequest2.setRecordType(new YBUDGET_ACTUALS_REVENUEStub.String());
//            actualRequest2.setVersion(new YBUDGET_ACTUALS_REVENUEStub.String());
//
//            YBUDGET_ACTUALS_REVENUEStub.YbudgetActualsRevenueResponse revActualResponse = stub2.ybudgetActualsRevenue(actualRequest2);
//
//            YBUDGET_ACTUALS_REVENUEStub.YbudgActual[] revActualValues = revActualResponse.getActualTab().getItem();
//
//            for (YBUDGET_ACTUALS_REVENUEStub.YbudgActual ybudgActualResponse : revActualValues) {
//                SapActuals sapActual = new SapActuals();
//                sapActual.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
//                sapActual.setDateCreated(Utility.dbDateNow());
//                sapActual.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
//                sapActual.setRecType(ybudgActualResponse.getRecType().getChar1());
//                sapActual.setVersionName(ybudgActualResponse.getVersion().getChar3());
//                sapActual.setValueTyp(ybudgActualResponse.getValuetyp().getChar2());
//                sapActual.setFmDocument(ybudgActualResponse.getFmDocument().getChar18());
//                sapActual.setFiDocument(ybudgActualResponse.getFiDocument().getChar18());
//                sapActual.setFund(ybudgActualResponse.getFund().getChar10());
//                sapActual.setFundsCtr(ybudgActualResponse.getFundsCtr().getChar16());
//                sapActual.setCmmtItem(ybudgActualResponse.getCmmtItem().getChar24());
//                sapActual.setFundedProg(ybudgActualResponse.getFundedProg().getChar24());
//                sapActual.setBusArea(ybudgActualResponse.getBusArea().getChar4());
//                sapActual.setAmountTc(ybudgActualResponse.getAmountTc().getCurr172().doubleValue());
//                sapActual.setAmountLc(ybudgActualResponse.getAmountLc().getCurr172().doubleValue());
//                sapActual.setCurrency(ybudgActualResponse.getCurrency().getCuky5());
//                sapActual.setGlAccount(ybudgActualResponse.getGlAccount().getChar10());
//                sapActual.setPeriod(ybudgActualResponse.getPeriod().getChar2());
//                sapActual.setFuncArea(ybudgActualResponse.getFuncArea().getChar16());
//                sapActual.setFiscYear(Integer.valueOf(ybudgActualResponse.getFiscYear().getNumeric4()));
//
//                if (isValidYear(sapActual.getFiscYear())) {//(sapActual.getAmountTc() > 0 || sapActual.getAmountLc() > 0) &&
//                    resp = helper.insert(sapActual);
//
//            //                        if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
//            //                            SAPActuals sapActual2 = helper.fetchObj(sapActual.getFiDocument());
//            //                            sapActual2.setAmountTc(sapActual.getAmountTc());
//            //                            sapActual2.setAmountLc(sapActual.getAmountLc());
//            //
//            //                            helper.update(sapActual2);
//            //                        }
//                }
//            }

            url = Utility.SAP_QA_ENDPOINT+"/sap/bc/webrfc?sap-client=410&_FUNCTION=YY_ACTUALS_REVENUE&FMAREA="+Utility.SAP_QA_FM_AREA+"&FISC_YR="+(currentYear)+"&FISC_YR_END="+(currentYear + 1);
            
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);
            //StringBuffer responses;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                responses = new StringBuffer ();
                while ((inputLine = in.readLine()) != null) {
                    responses.append(inputLine);
                }
            }
            //print in String
            System.out.println(responses.toString());
            //Read JSON response and print
            //JSONObject jsonObject = new JSONObject(responses.toString());
            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(responses.toString());
            // get a String from the JSON object
            
            // get an array from the JSON object
            results = (JSONArray) jsonObject.get("results");
            

            i = results.iterator();
            // take each value from the json array separately
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                //System.out.println("language "+ innerObj.get("lang") +  " with level " + innerObj.get("knowledge"));

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
            //                mybfPreviousForwardHelper.updateMybfPreviousForward(Integer.valueOf(request.getParameter("versionID")), currentYear);
            //                mybfPreviousForwardHelper.updateMybfPreviousForward(Integer.valueOf(request.getParameter("versionID")), currentYear - 1);
        }
        //System.out.println(resp);
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
