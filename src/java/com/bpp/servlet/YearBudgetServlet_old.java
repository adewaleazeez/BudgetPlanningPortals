/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.BudgetYears;
import com.bpp.hibernate.Users;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.hibernate.YearBudget;
import com.bpp.hibernate.YearBudgetHibernateHelper;
import com.bpp.hibernate.YearBudgetVersions;
import com.google.gson.Gson;
import com.bpp.utility.Utility;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Char10;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Char16;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Char24;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Char4;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Char50;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Char6;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Cuky5;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Decimal234;
//import mc_style.functions.soap.sap.document.sap_com.YPOST_INI_BUDGETStub.Numeric4;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class YearBudgetServlet_old extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException, MessagingException {
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

            if (option.equals(Utility.OPTION_SELECT_BUDGET_VERSION)) {
                resp = helper.fetchBudgetVersions();
            }

            if (option.equals(Utility.OPTION_SELECT_BUDGET_TYPE)) {
                resp = helper.fetchBudgetTypes();
            }

            if (option.equals(Utility.OPTION_SELECT_BUDGET_CURRENCY)) {
                resp = helper.fetchBudgetCurrencies();
            }

            if (option.equals(Utility.OPTION_CHANGE_BUDGET_VERSION)) {
                resp = helper.changeBudgetVersion(budget_year_id, budget_version_id, budget_type_id);
                if (resp.equals(Utility.ActionResponse.SUCCESSFULL.toString())) {
                    YearBudgetVersions bv = helper.fetchActiveBudgetVersions();
                    //Users director = helper.fetchBudgetDirector();
                    resp = helper.fetchBudgetDirectors();
                    JsonParser parser = new JsonParser();
                    Object jsonEleArray = parser.parse(new StringReader(resp));
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
//http://41.76.158.37:8000/sap/bc/webrfc?_FUNCTION=YY_INITIAL_BUDGET&sap-client=410&DOCDATE=20180807&DOCTYPE=BUDG&ITEM1=1&&2017&&SB&&01101&&051702609800&&22020302&&70980&&99999999999999&&NGN&&100&&TEST1&ITEM2=2&&2017&&SB&&01101&&051702609800&&22020302&&70980&&99999999999999&&NGN&&200&&TEST2&ITEMN=2&NO_OF_RECORDS=2&VERSION=0


                //Get current year
                BudgetYears budgetYear = new BudgetYearHibernateHelper().fetchObjByYear(request.getParameter("budgetYear"));
                int currentYearID = budgetYear.getId();
                int currentYear = budgetYear.getYear();

                String budgetType = request.getParameter("budgetType");

                //Get approved budget items and push
                List<YearBudget> yearBudgetItems = new YearBudgetHibernateHelper().fetchAll2(currentYear, budgetType);
//System.out.println("***** currentYear  "+currentYear);         
//System.out.println("***** budgetType  "+budgetType);         
//System.out.println("***** yearBudgetItems size "+yearBudgetItems.size());         

                //POST Budget
                YPOST_INI_BUDGETStub stub = new YPOST_INI_BUDGETStub();

                if (budgetType.equals(Utility.SAP_HOUSE_APPROVED_BUDGET_TYPE)) {
//System.out.println("***** +Utility.SAP_HOUSE_APPROVED_BUDGET_TYPE "+Utility.SAP_HOUSE_APPROVED_BUDGET_TYPE);
                    YPOST_INI_BUDGETStub.YpostIniBudget yearBudgetRequest = new YPOST_INI_BUDGETStub.YpostIniBudget();
//System.out.println("***** yearBudgetRequest "+yearBudgetRequest);         

//                    yearBudgetRequest.setDocdate(new YPOST_INI_BUDGETStub.String(Utility.convertToSAPDate(new Date())));
//                    yearBudgetRequest.setDoctype(new YPOST_INI_BUDGETStub.String(Utility.SAP_HOUSE_APPROVED_BUDGET_DOC_TYPE));
//                    yearBudgetRequest.setVersion(new YPOST_INI_BUDGETStub.String("O"));
                    //Gather budget items
                    YPOST_INI_BUDGETStub.YbudgLine budgetIn = new YPOST_INI_BUDGETStub.YbudgLine();
//System.out.println("***** budgetIn "+budgetIn);         

                    int counter = 1;
                    for (YearBudget item : yearBudgetItems) {
                        YPOST_INI_BUDGETStub.YbudglineE budgetItem = new YPOST_INI_BUDGETStub.YbudglineE();
//System.out.println("***** budgetItem "+budgetItem);         

                        YPOST_INI_BUDGETStub.Char6 itemNo = new YPOST_INI_BUDGETStub.Char6();
                        itemNo.setChar6(String.valueOf(counter));
                        budgetItem.setItmNo(itemNo);
//System.out.println("***** itemNo "+itemNo);         

                        YPOST_INI_BUDGETStub.Numeric4 fiscYear = new YPOST_INI_BUDGETStub.Numeric4();
                        fiscYear.setNumeric4(String.valueOf(currentYear));
                        budgetItem.setFiscYear(fiscYear);
//System.out.println("***** fiscYear "+fiscYear);         

                        YPOST_INI_BUDGETStub.Char4 budgType = new YPOST_INI_BUDGETStub.Char4();
                        budgType.setChar4(String.valueOf(budgetType));
                        budgetItem.setBudgType(budgType);
//System.out.println("***** budgType "+budgType);         

                        YPOST_INI_BUDGETStub.Char10 fund = new YPOST_INI_BUDGETStub.Char10();
                        fund.setChar10(String.valueOf(item.getFundSegment()));
                        budgetItem.setFund(fund);
//System.out.println("***** fund "+fund);         

                        YPOST_INI_BUDGETStub.Char16 fundsCtr = new YPOST_INI_BUDGETStub.Char16();
                        fundsCtr.setChar16(String.valueOf(item.getAdminSegment()));
                        budgetItem.setFundsCtr(fundsCtr);
//System.out.println("***** fundsCtr "+fundsCtr);         

                        YPOST_INI_BUDGETStub.Char24 cmmtItem = new YPOST_INI_BUDGETStub.Char24();
                        cmmtItem.setChar24(String.valueOf(item.getEconomicSegment()));
                        budgetItem.setCmmtItem(cmmtItem);
//System.out.println("***** cmmtItem "+cmmtItem);         

                        YPOST_INI_BUDGETStub.Char24 funcArea = new YPOST_INI_BUDGETStub.Char24();
                        funcArea.setChar24(String.valueOf(item.getFunctionalSegment()));
                        budgetItem.setFuncArea(funcArea);
//System.out.println("***** funcArea "+funcArea);         

                        YPOST_INI_BUDGETStub.Char24 fundedProg = new YPOST_INI_BUDGETStub.Char24();
                        fundedProg.setChar24(String.valueOf(item.getProgrammeSegment()));
                        budgetItem.setFundedProg(fundedProg);
//System.out.println("***** fundedProg "+fundedProg);         

                        YPOST_INI_BUDGETStub.Cuky5 transCurr = new YPOST_INI_BUDGETStub.Cuky5();
                        transCurr.setCuky5(String.valueOf(Utility.SAP_DEFAULT_CURRENCY));
                        budgetItem.setTransCurr(transCurr);
//System.out.println("***** transCurr "+transCurr);         

                        YPOST_INI_BUDGETStub.Decimal234 totalAmt = new YPOST_INI_BUDGETStub.Decimal234();
                        totalAmt.setDecimal234(BigDecimal.valueOf(item.getBudgetAmount()));
                        budgetItem.setTotalAmt(totalAmt);
//System.out.println("***** totalAmt "+totalAmt);         

                        YPOST_INI_BUDGETStub.Char50 itemTxt = new YPOST_INI_BUDGETStub.Char50();
                        itemTxt.setChar50(String.valueOf(item.getDeptId()));
                        budgetItem.setItemTxt(itemTxt);
//System.out.println("***** itemTxt "+itemTxt);         

                        YPOST_INI_BUDGETStub.Char40 geoCode = new YPOST_INI_BUDGETStub.Char40();
                        geoCode.setChar40(String.valueOf(item.getGeoSegment()));
                        budgetItem.setGeocode(geoCode);
//System.out.println("***** itemTxt "+itemTxt);         

                        //budgetItem.setItmNo(new Char6(String.valueOf(counter)));
                        //budgetItem.setFiscYear(new Numeric4(String.valueOf(currentYearID)));
                        //budgetItem.setBudgType(new Char4(budgetType));
                        //budgetItem.setFund(new Char10(item.getFundSegment()));
                        //budgetItem.setFundsCtr(new Char16(item.getAdminSegment()));
                        //budgetItem.setCmmtItem(new Char24(item.getEconomicSegment()));
                        //budgetItem.setFuncArea(new Char24(item.getFunctionalSegment()));
                        //budgetItem.setFundedProg(new Char24(item.getProgrammeSegment()));
                        //budgetItem.setTransCurr(new Cuky5(Utility.SAP_DEFAULT_CURRENCY));
                        //budgetItem.setTotalAmt(new Decimal234(new BigDecimal(item.getBudget_amount())));
                        //budgetItem.setItemTxt(new Char50(item.getGeoSegment()));
                        budgetIn.addItem(budgetItem);

                        yearBudgetRequest.setBudgetIn(budgetIn);
//Ybudgline Ybudgreturn YpostIniBudget YpostIniBudgetResponse

                        System.out.println(counter + "   budgetItem  " + budgetItem);
                        counter++;
                        if (counter == 6) {
                            break;
                        }
                    }

                    YPOST_INI_BUDGETStub.YbudgReturn budgetResult = new YPOST_INI_BUDGETStub.YbudgReturn();
                    yearBudgetRequest.setBudgetResult(budgetResult);

                    YPOST_INI_BUDGETStub.String docDate = new YPOST_INI_BUDGETStub.String();
                    docDate.setString(String.valueOf(Utility.convertToSAPDate(new Date())));
                    yearBudgetRequest.setDocdate(docDate);
                    System.out.println("docDate " + docDate.toString());

                    YPOST_INI_BUDGETStub.String docType = new YPOST_INI_BUDGETStub.String();
                    docType.setString(String.valueOf(Utility.SAP_HOUSE_APPROVED_BUDGET_DOC_TYPE));
                    yearBudgetRequest.setDoctype(docType);
                    System.out.println("docType " + docType.toString());

                    YPOST_INI_BUDGETStub.String version = new YPOST_INI_BUDGETStub.String();
                    version.setString(String.valueOf("0"));
                    yearBudgetRequest.setVersion(version);
                    System.out.println("version " + version.toString());
                    System.out.println("yearBudgetRequest " + yearBudgetRequest);

                    YPOST_INI_BUDGETStub.YpostIniBudgetResponse yearBudgetResponse = stub.ypostIniBudget(yearBudgetRequest);
                    System.out.println("***** yearBudgetResponse  " + yearBudgetResponse);
                    yearBudgetResponse.setBudgetResult(budgetResult);

                    String documentNumber = yearBudgetResponse.getDocumentnumber().getString();

                    System.out.println("***** budgetIn  " + budgetIn);
                    if (!documentNumber.equals("")) {
                        //resp = new YearBudgetHibernateHelper().updateAll(currentYearID, budgetType, documentNumber);
                    }
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
                Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(YearBudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
