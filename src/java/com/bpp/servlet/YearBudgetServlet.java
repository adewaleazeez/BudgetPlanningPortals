/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.BudgetYears;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.YearBudget;
import com.bpp.hibernate.YearBudgetHibernateHelper;
import com.bpp.hibernate.YearBudgetVersions;
import com.google.gson.Gson;
import com.bpp.utility.Utility;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class YearBudgetServlet extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException, MessagingException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            YearBudgetHibernateHelper helper = new YearBudgetHibernateHelper();
            YearBudget yearBudget = new YearBudget();
            Utility utility = new Utility();

            String option;
            String id;
            String admin_segment;
            String programme_segment;
            String economic_segment;
            String functional_segment;
            String fund_segment;
            String geo_segment;
            String dept_id;
            String budget_year_id;
            String budget_amount;
            String dateCreated;
            String orgId;
            String percent_complete;
            String complete_from;
            String complete_to;
            String budget_version_id;
            String budget_type_id;
            String narration;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            admin_segment = request.getParameter("admin_segment");
            if (admin_segment == null) {
                admin_segment = "";
            }

            programme_segment = request.getParameter("programme_segment");
            if (programme_segment == null) {
                programme_segment = "";
            }

            economic_segment = request.getParameter("economic_segment");
            if (economic_segment == null) {
                economic_segment = "";
            }

            functional_segment = request.getParameter("functional_segment");
            if (functional_segment == null) {
                functional_segment = "";
            }

            fund_segment = request.getParameter("fund_segment");
            if (fund_segment == null) {
                fund_segment = "";
            }

            geo_segment = request.getParameter("geo_segment");
            if (geo_segment == null) {
                geo_segment = "";
            }

            dept_id = request.getParameter("dept_id");
            if (dept_id == null) {
                dept_id = "";
            }

            budget_year_id = request.getParameter("budget_year_id");
            if (budget_year_id == null) {
                budget_year_id = "";
            }

            budget_amount = request.getParameter("budget_amount");
            if (budget_amount == null) {
                budget_amount = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "";
            }

            percent_complete = request.getParameter("percent_complete");
            if (percent_complete == null) {
                percent_complete = "";
            }

            complete_from = request.getParameter("complete_from");
            if (complete_from == null) {
                complete_from = "";
            }

            complete_to = request.getParameter("complete_to");
            if (complete_to == null) {
                complete_to = "";
            }

            budget_version_id = request.getParameter("budget_version_id");
            if (budget_version_id == null) {
                budget_version_id = "";
            }

            budget_type_id = request.getParameter("budget_type_id");
            if (budget_type_id == null) {
                budget_type_id = "";
            }

            narration = request.getParameter("narration");
            if (narration == null) {
                narration = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    yearBudget.setId(Integer.parseInt(helper.getMaxSerialNo("Year_Budget")) + 1);
                    yearBudget.setAdminSegment(admin_segment);
                    String new_programme_segment = "";
                    if (programme_segment.equals("99999999999999")) {
                        new_programme_segment = programme_segment;
                    } else {
                        new_programme_segment = helper.getMaxSerialNo("Year_Budget", programme_segment);
                    }
                    yearBudget.setProgrammeSegment(new_programme_segment);
                    yearBudget.setEconomicSegment(economic_segment);
                    yearBudget.setFunctionalSegment(functional_segment);
                    yearBudget.setFundSegment(fund_segment);
                    yearBudget.setGeoSegment(geo_segment);
                    yearBudget.setDeptId(dept_id);
                    yearBudget.setBudgetYearId(Integer.parseInt(budget_year_id));
                    yearBudget.setBudgetAmount(Double.parseDouble(budget_amount));
                    yearBudget.setDateCreated(utility.dbDateNow());
                    yearBudget.setOrgId(1);
                    yearBudget.setPercentComplete(percent_complete);
                    yearBudget.setCompleteFrom(complete_from);
                    yearBudget.setCompleteTo(complete_to);
                    yearBudget.setBudgetVersionId(Integer.parseInt(budget_version_id));
                    yearBudget.setSapBudgetType(budget_type_id);
                    yearBudget.setNarration(narration);
                    resp = helper.insert(yearBudget);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    yearBudget = helper.exists(Integer.parseInt(id));
                    yearBudget.setAdminSegment(admin_segment);
                    yearBudget.setProgrammeSegment(programme_segment);
                    yearBudget.setEconomicSegment(economic_segment);
                    yearBudget.setFunctionalSegment(functional_segment);
                    yearBudget.setFundSegment(fund_segment);
                    yearBudget.setGeoSegment(geo_segment);
                    yearBudget.setDeptId(dept_id);
                    yearBudget.setBudgetYearId(Integer.parseInt(budget_year_id));
                    yearBudget.setBudgetAmount(Double.parseDouble(budget_amount));
                    yearBudget.setPercentComplete(percent_complete);
                    yearBudget.setCompleteFrom(complete_from);
                    yearBudget.setCompleteTo(complete_to);
                    yearBudget.setNarration(narration);
                    resp = helper.update(yearBudget);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    yearBudget = helper.exists(Integer.parseInt(id));
                    resp = helper.delete(yearBudget);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_SELECT_BY_NAME)) {
                String userid = session.getAttribute("userid").toString();
                String userrole = session.getAttribute("userrole").toString();
                resp = helper.fetchByUserRole(userid, userrole);
            }

            if (option.equals(Utility.OPTION_GET_ENVELOPES_BY_MDAS)) {
                resp = helper.fetchEnvelopeByMDAs(admin_segment, budget_year_id);
            }

            if (option.equals(Utility.OPTION_GET_ENVELOPES_BY_MDAS_CONTINGENCY)) {
                resp = helper.fetchEnvelopeByMDAsContingency(admin_segment, budget_year_id);
            }

            if (option.equals(Utility.OPTION_GET_ENVELOPES_BY_MDAS_SUPPLEMENTARY)) {
                resp = helper.fetchEnvelopeByMDAsSupplementary(admin_segment, budget_year_id);
            }

            if (option.equals(Utility.OPTION_SELECT_BUDGET_VERSION)) {
                resp = helper.fetchBudgetVersions();
            }

            if (option.equals(Utility.OPTION_SELECT_SAP_BUDGET_TYPE)) {
                resp = helper.fetchSapBudgetTypes();
            }

            if (option.equals(Utility.OPTION_SELECT_BUDGET_TYPE)) {
                resp = helper.fetchBudgetTypes();
            }

            if (option.equals(Utility.OPTION_SELECT_BUDGET_CURRENCY)) {
                resp = helper.fetchBudgetCurrencies();
            }

            if (option.equals(Utility.OPTION_CHANGE_BUDGET_VERSION)) {
                resp = helper.changeBudgetVersion(budget_year_id, budget_version_id, budget_type_id);
                //System.out.println("resp ::: "+resp);
                if (resp.equals(Utility.ActionResponse.SUCCESSFULL.toString())) {
                    YearBudgetVersions bv = helper.fetchActiveBudgetVersions();
                    //Users director = helper.fetchBudgetDirector();
                    String myresp = helper.fetchBudgetDirectors();
                    JsonParser parser = new JsonParser();
                    Object jsonEleArray = parser.parse(new StringReader(myresp));
                    JsonElement jsonElement = (JsonElement) jsonEleArray;
                    JsonArray jarr = jsonElement.getAsJsonArray();
                    for (JsonElement je : jarr) {
                        JsonElement jsonSubElement = (JsonElement) je;
                        JsonArray subjarr = jsonSubElement.getAsJsonArray();
                        String emailSubject = "RE: Budget Version Generated for " + bv.getYearBudgetVersion();
                        String emailBody = "Dear " + subjarr.get(0).getAsString() + " " + subjarr.get(1).getAsString() + ",<br><br>"
                                + "This is to inform you that a new budget version has been created for : " + bv.getYearBudgetVersion() + "<br>"
                                + "You are hereby advised to contact all officers who need to act on the new version.<br><br>"
                                + "Regards<br><br>System Administrator.";
                        String emailrecipients = subjarr.get(2).getAsString();
                        utility.setMailServerProperties();
                        utility.createEmailMessage(emailSubject, emailBody, emailrecipients);
                        utility.sendEmail();
                    }
                }
            }

            if (option.equals(Utility.OPTION_APPROVE_BUDGET)) {
                resp = helper.approveBudget(budget_year_id, budget_version_id, budget_type_id, admin_segment);
                //System.out.println(budget_year_id+"    "+budget_version_id+"    "+budget_type_id+"    "+admin_segment);
                if (resp.equals(Utility.ActionResponse.SUCCESSFULL.toString())) {
                    YearBudgetVersions bv = helper.fetchActiveBudgetVersions();
                    //Users director = helper.fetchBudgetDirector();
                    String myresp = helper.fetchBudgetDirectors();
                    JsonParser parser = new JsonParser();
                    Object jsonEleArray = parser.parse(new StringReader(myresp));
                    JsonElement jsonElement = (JsonElement) jsonEleArray;
                    JsonArray jarr = jsonElement.getAsJsonArray();
                    for (JsonElement je : jarr) {
                        JsonElement jsonSubElement = (JsonElement) je;
                        JsonArray subjarr = jsonSubElement.getAsJsonArray();
                        String emailSubject = "RE: Budget Approval By Account Officer for " +dept_id;
                        String emailBody = "Dear " + subjarr.get(0).getAsString() + " " + subjarr.get(1).getAsString() + ",<br><br>"
                                + "This is to inform you that the Account Office ("+session.getAttribute("fname").toString()+" "+session.getAttribute("lname").toString()+") has approved budget version  " + bv.getYearBudgetVersion() + " for MDA ("+dept_id+")<br>"
                                + "You are hereby advised to contact all officers who need to act on the approved budget.<br><br>"
                                + "Regards<br><br>System Administrator.";
                        String emailrecipients = subjarr.get(2).getAsString();
                        utility.setMailServerProperties();
                        utility.createEmailMessage(emailSubject, emailBody, emailrecipients);
                        utility.sendEmail();
                    }
                    //resp = Utility.ActionResponse.SUCCESSFULL.toString();
                }
            }

            if (option.equals(Utility.OPTION_UNLOCK_BUDGET)) {
                resp = helper.unlockBudget(budget_year_id, budget_version_id, budget_type_id, admin_segment);
                //System.out.println(budget_year_id+"    "+budget_version_id+"    "+budget_type_id+"    "+admin_segment);
                //System.out.println("2 resp    "+resp);
                if (resp.equals(Utility.ActionResponse.SUCCESSFULL.toString())) {
                    YearBudgetVersions bv = helper.fetchActiveBudgetVersions();
                    //Users director = helper.fetchBudgetDirector();
                    String myresp = helper.fetchBudgetDirectors();
                    JsonParser parser = new JsonParser();
                    Object jsonEleArray = parser.parse(new StringReader(myresp));
                    JsonElement jsonElement = (JsonElement) jsonEleArray;
                    JsonArray jarr = jsonElement.getAsJsonArray();
                    for (JsonElement je : jarr) {
                        JsonElement jsonSubElement = (JsonElement) je;
                        JsonArray subjarr = jsonSubElement.getAsJsonArray();
                        String emailSubject = "RE: Budget Unlock By System Administrator for " +dept_id;
                        String emailBody = "Dear " + subjarr.get(0).getAsString() + " " + subjarr.get(1).getAsString() + ",<br><br>"
                                + "This is to inform you that the System Administrator ("+session.getAttribute("fname").toString()+" "+session.getAttribute("lname").toString()+") has unlocked budget version  " + bv.getYearBudgetVersion() + " for ("+dept_id+") <br>"
                                + "You are hereby advised to contact all officers who need to act on the unlocked budget.<br><br>"
                                + "Regards<br><br>System Administrator.";
                        String emailrecipients = subjarr.get(2).getAsString();
                        utility.setMailServerProperties();
                        utility.createEmailMessage(emailSubject, emailBody, emailrecipients);
                        utility.sendEmail();
                    }
                    //resp = Utility.ActionResponse.SUCCESSFULL.toString();
                }
            }

            if (option.equals(Utility.OPTION_SELECT)) {
                resp = helper.fetchBudgetHeads(budget_type_id);
            }

            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchById(id);
            }
            
            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                if (!admin_segment.equals("")) {
                    resp = helper.fetchAdminSegmment(id, admin_segment);
                }
                if (!economic_segment.equals("")) {
                    resp = helper.fetchEconSegmment(economic_segment);
                }
                if (!programme_segment.equals("")) {
                    resp = helper.fetchProgSegmment();
                }
                if (!functional_segment.equals("")) {
                    resp = helper.fetchFuncSegmment();
                }
                if (!fund_segment.equals("")) {
                    resp = helper.fetchFundSegmment();
                }
                if (!geo_segment.equals("")) {
                    resp = helper.fetchGeoSegmment();
                }
                if (!dept_id.equals("")) {
                    resp = helper.fetchDept(dept_id);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD_CONTINGENCY_VALUE)) {
                if (!admin_segment.equals("")) {
                    resp = helper.fetchAdminSegmmentContingency(id, admin_segment);
                }
            }
            if (option.equals(Utility.OPTION_SELECT_A_RECORD_SUPPLEMENTARY_VALUE)) {
                if (!admin_segment.equals("")) {
                    resp = helper.fetchAdminSegmmentSupplementary(id, admin_segment);
                }
            }
            
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                if (!economic_segment.equals("")) {
                    resp = helper.fetchAll(admin_segment, economic_segment, budget_year_id, narration, budget_type_id);
                }
                if (!programme_segment.equals("")) {
                    economic_segment = programme_segment;
                    resp = helper.fetchAllProgrammes(admin_segment, economic_segment, budget_year_id, narration, budget_type_id);
                }
            }

            if (option.equals(Utility.OPTION_PUSH_BUDGET)) {

                //Get current year
                BudgetYears budgetYear = new BudgetYearHibernateHelper().fetchObjByYear(request.getParameter("budgetYear"));
                int currentYearID = budgetYear.getId();
                int currentYear = budgetYear.getYear();

                String budgetType = request.getParameter("budgetType");

                //Get approved budget items and push
                List<YearBudget> yearBudgetItems = new YearBudgetHibernateHelper().fetchAll2(currentYear, budgetType);
                while (yearBudgetItems.size() > 0) {
                    if (budgetType.equals(Utility.SAP_HOUSE_APPROVED_BUDGET_TYPE)) {

                        int counter = 0;
                        String param = "";
                        int maxrecno = 0;
                        for (YearBudget item : yearBudgetItems) {
                            String num = (++counter)+"";
                            num = (new String(new char[4 - num.length()]).replace("\0", "0"))+num;
                            String itemNo = num
                                    + "&&" + item.getBudgetYearId()
                                    + "&&" + item.getSapBudgetType()
                                    + "&&" + item.getFundSegment()
                                    + "&&" + item.getAdminSegment()
                                    + "&&" + item.getEconomicSegment()
                                    + "&&" + item.getFunctionalSegment()
                                    + "&&" + item.getProgrammeSegment()
                                    + "&&" + Utility.SAP_DEFAULT_CURRENCY
                                    + "&&" + String.format("%.2f", item.getBudgetAmount())
                                    + "&&" + item.getNarration()
                                    + "&&" + item.getGeoSegment();

                            param += "&ITEM" + (counter) + "=" + URLEncoder.encode(itemNo, "UTF-8");
                            maxrecno = item.getId();
                        }

                        String url = Utility.SAP_QA_ENDPOINT + "/sap/bc/webrfc?_FUNCTION=YY_INITIAL_BUDGET&sap-client=400&DOCDATE=20181024&DOCTYPE=BUDG" + param + "&ITEMN=" + counter + "&NO_OF_RECORDS=" + counter + "&VERSION=0";
                        System.out.println("url: " + url);
                        //System.out.println("maxrecno: " + maxrecno);

                        URL obj = new URL(url);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        // optional default is GET
                        con.setRequestMethod("POST");

                        //add request header 
                        //con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        con.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");

                        //int responseCode = con.getResponseCode();
                        StringBuffer responses;
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                            String inputLine;
                            responses = new StringBuffer();
                            while ((inputLine = in.readLine()) != null) {
                                responses.append(inputLine);
                            }
                        }
                        //System.out.println("responses:::   " + responses);

                        JSONParser jsonParser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) jsonParser.parse(responses.toString());
                        JSONObject results = (JSONObject) jsonObject.get("results");
                        JSONObject doc = (JSONObject) results.get("doc");
                        String documentNumber = doc.get("documentnumber").toString();
                        //System.out.println("documentNumber:::   " + documentNumber);
                        if (!documentNumber.equals("")) {
                            resp = new YearBudgetHibernateHelper().updateAll(currentYear, budgetType, documentNumber, maxrecno);
                            //System.out.println("resp:::   " + resp);
                        }else{
                            JSONArray msg = (JSONArray) results.get("msg");
                            Iterator i = msg.iterator();

                            // take each value from the json array separately
                            resp="error";
                            while (i.hasNext()) {
                                JSONObject innerObj = (JSONObject) i.next();
                                //System.out.println("innerObj:::   " + innerObj);
                                if(!resp.contains(innerObj.get("message").toString())){
                                    resp += innerObj.get("message").toString() + "_~_";
                                }
                            }
                            break;
                        }

                    }
                    yearBudgetItems = new YearBudgetHibernateHelper().fetchAll2(currentYear, budgetType);
                }
            }

            out.println(resp);

        } finally {
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
            try {
                processRequest(request, response);
            } catch (MessagingException ex) {
                //Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                //Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            //Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            //Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            //Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
