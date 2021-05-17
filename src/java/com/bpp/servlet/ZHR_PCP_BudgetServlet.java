/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
import com.bpp.hibernate.SapPersonnel;
import com.bpp.hibernate.SAPersonnelHibernateHelper;
import com.bpp.hibernate.SapActuals;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;

import functions.rfc.sap.document.sap_com.ZHR_PCP_BUDGET_APPStub;
import functions.rfc.sap.document.sap_com.ZHR_PCP_BUDGET_APPStub.ZHR_PCP_BUDG;
import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Personnel Cost Planning Servlet class
 *
 * @author Lekan
 * @since 19/10/2017
 *
 */
public class ZHR_PCP_BudgetServlet extends HttpServlet {

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
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            SAPersonnelHibernateHelper helper = new SAPersonnelHibernateHelper();

            String option;
            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    try {
                        
                        int currentYear = new BudgetYearHibernateHelper().fetchCurrentYear2().getYear();
//http://41.76.158.37:8000/sap/bc/webrfc?sap-client=410&_FUNCTION=YY_BUDGET_PCP&FISCAL_YEAR=2018            
                        String url = Utility.SAP_QA_ENDPOINT+"/sap/bc/webrfc?sap-client=410&_FUNCTION=YY_BUDGET_PCP&FISCAL_YEAR="+(currentYear);
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
                            SapPersonnel pci = new SapPersonnel();
                            pci.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                            pci.setDateCreated(Utility.dbDateNow());
                            pci.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                            pci.setAdminSegment(innerObj.get("admin_segment").toString());
                            pci.setAmtCurrent(Double.parseDouble(innerObj.get("amt_current").toString()));
                            pci.setAmtNext(Double.parseDouble(innerObj.get("amt_next").toString()));
                            pci.setCostCenter(innerObj.get("cost_center").toString());
                            pci.setDeptUnitId(innerObj.get("dept_unit_id").toString());
                            pci.setDeptUnitName(innerObj.get("dept_unit_name").toString());
                            pci.setFiscalYear(innerObj.get("fisc_year").toString());
                            pci.setHcCurrent(Double.parseDouble(innerObj.get("hc_current").toString()));
                            pci.setHcNext(Double.parseDouble(innerObj.get("hc_next").toString()));
                            pci.setMandt(innerObj.get("mandt").toString());
                            pci.setMdaName(innerObj.get("mda_name").toString());
                            pci.setMdaOrgId(innerObj.get("mda_org_id").toString());
                            pci.setPosJobId(innerObj.get("pos_job_id").toString());
                            pci.setPosJobName(innerObj.get("pos_job_name").toString());
                            pci.setRepDate(innerObj.get("rep_date").toString());
                            pci.setRepTime(innerObj.get("rep_time").toString());
                            pci.setSalGrade(innerObj.get("sal_grade").toString());
                            pci.setBasicCurrent(Double.parseDouble(innerObj.get("basic_c").toString()));
                            pci.setBonusCurrent(Double.parseDouble(innerObj.get("bonus_c").toString()));
                            pci.setOthersCurrent(Double.parseDouble(innerObj.get("others_c").toString()));
                            pci.setRentCurrent(Double.parseDouble(innerObj.get("rent_c").toString()));
                            pci.setTransportCurrent(Double.parseDouble(innerObj.get("transport_c").toString()));
                            pci.setBasicNext(Double.parseDouble(innerObj.get("basic").toString()));
                            pci.setBonusNext(Double.parseDouble(innerObj.get("bonus").toString()));
                            pci.setOthersNext(Double.parseDouble(innerObj.get("others").toString()));
                            pci.setRentNext(Double.parseDouble(innerObj.get("rent").toString()));
                            pci.setTransportNext(Double.parseDouble(innerObj.get("transport").toString()));
                            
                            resp = helper.insert(pci);

                            if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
                                SapPersonnel pci2 = helper.exists(pci);
                                pci2.setAmtCurrent(Double.parseDouble(innerObj.get("amt_current").toString()));
                                pci2.setAmtNext(Double.parseDouble(innerObj.get("amt_next").toString()));
                                pci2.setHcCurrent(Double.parseDouble(innerObj.get("hc_current").toString()));
                                pci2.setHcNext(Double.parseDouble(innerObj.get("hc_next").toString()));
                                pci2.setBasicCurrent(Double.parseDouble(innerObj.get("basic_c").toString()));
                                pci2.setBonusCurrent(Double.parseDouble(innerObj.get("bonus_c").toString()));
                                pci2.setOthersCurrent(Double.parseDouble(innerObj.get("others_c").toString()));
                                pci2.setRentCurrent(Double.parseDouble(innerObj.get("rent_c").toString()));
                                pci2.setTransportCurrent(Double.parseDouble(innerObj.get("transport_c").toString()));
                                pci2.setBasicNext(Double.parseDouble(innerObj.get("basic").toString()));
                                pci2.setBonusNext(Double.parseDouble(innerObj.get("bonus").toString()));
                                pci2.setOthersNext(Double.parseDouble(innerObj.get("others").toString()));
                                pci2.setRentNext(Double.parseDouble(innerObj.get("rent").toString()));
                                pci2.setTransportNext(Double.parseDouble(innerObj.get("transport").toString()));
                            
                                helper.update(pci2);
                            }
                        
//                        ZHR_PCP_BUDGET_APPStub newstub = new ZHR_PCP_BUDGET_APPStub();
//                        ZHR_PCP_BUDGET_APPStub.ZHR_PCP_BUDGET_APP zhrPcpBudgetRequest = new ZHR_PCP_BUDGET_APPStub.ZHR_PCP_BUDGET_APP();
//                        
//                        zhrPcpBudgetRequest.setZHR_PCP_BUDG(new functions.rfc.sap.document.sap_com.ZHR_PCP_BUDGET_APPStub.TABLE_OF_ZHR_PCP_BUDG());
//                
//                        ZHR_PCP_BUDGET_APPStub.ZHR_PCP_BUDGET_APPResponse zhrPcpBudgetResponse = newstub.zHR_PCP_BUDGET_APP(zhrPcpBudgetRequest);
//                        ZHR_PCP_BUDG[] zhrPcpBudgetValue = zhrPcpBudgetResponse.getZHR_PCP_BUDG().getItem();
//                
//                        for (ZHR_PCP_BUDG zhrPcpBudgetItemVal : zhrPcpBudgetValue) {
//                            SapPersonnel pci = new SapPersonnel();
//                            pci.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
//                            pci.setDateCreated(Utility.dbDateNow());
//                            pci.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
//                            pci.setAdminSegment(zhrPcpBudgetItemVal.getADMIN_SEGMENT().getChar12());
//                            pci.setAmtCurrent(zhrPcpBudgetItemVal.getAMT_CURRENT().getCurr152().doubleValue());
//                            pci.setAmtNext(zhrPcpBudgetItemVal.getAMT_NEXT().getCurr152().doubleValue());
//                            pci.setCostCenter(zhrPcpBudgetItemVal.getCOST_CENTER().getChar10());
//                            pci.setDeptUnitId(zhrPcpBudgetItemVal.getDEPT_UNIT_ID().getChar12());
//                            pci.setDeptUnitName(zhrPcpBudgetItemVal.getDEPT_UNIT_NAME().getChar40());
//                            pci.setFiscalYear(zhrPcpBudgetItemVal.getFISCAL_YEAR().getChar4());
//                            pci.setHcCurrent(zhrPcpBudgetItemVal.getHC_CURRENT().hashCode());
//                            pci.setHcNext(zhrPcpBudgetItemVal.getHC_NEXT().hashCode());
//                            pci.setMandt(zhrPcpBudgetItemVal.getMANDT().getClnt3());
//                            pci.setMdaName(zhrPcpBudgetItemVal.getMDA_NAME().getChar40());
//                            pci.setMdaOrgId(zhrPcpBudgetItemVal.getMDA_ORG_ID().getChar12());
//                            pci.setPosJobId(zhrPcpBudgetItemVal.getPOS_JOB_ID().getChar12());
//                            pci.setPosJobName(zhrPcpBudgetItemVal.getPOS_JOB_NAME().getChar40());
//                            pci.setRepDate(zhrPcpBudgetItemVal.getREP_DATE().getDate10());
//                            pci.setRepTime(zhrPcpBudgetItemVal.getREP_TIME().toString());
//                            pci.setSalGrade(zhrPcpBudgetItemVal.getSAL_GRADE().getChar8());
//                            pci.setBasicCurrent(zhrPcpBudgetItemVal.getBASIC_C().getCurr152().doubleValue());
//                            pci.setBonusCurrent(zhrPcpBudgetItemVal.getBONUS_C().getCurr152().doubleValue());
//                            pci.setOthersCurrent(zhrPcpBudgetItemVal.getOTHERS_C().getCurr152().doubleValue());
//                            pci.setRentCurrent(zhrPcpBudgetItemVal.getRENT_C().getCurr152().doubleValue());
//                            pci.setTransportCurrent(zhrPcpBudgetItemVal.getTRANSPORT_C().getCurr152().doubleValue());
//                            pci.setBasicNext(zhrPcpBudgetItemVal.getBASIC().getCurr152().doubleValue());
//                            pci.setBonusNext(zhrPcpBudgetItemVal.getBONUS().getCurr152().doubleValue());
//                            pci.setOthersNext(zhrPcpBudgetItemVal.getOTHERS().getCurr152().doubleValue());
//                            pci.setRentNext(zhrPcpBudgetItemVal.getRENT().getCurr152().doubleValue());
//                            pci.setTransportNext(zhrPcpBudgetItemVal.getTRANSPORT().getCurr152().doubleValue());
//                            
//                            resp = helper.insert(pci);
//
//                            if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
//                                SapPersonnel pci2 = helper.exists(pci);
//                                pci2.setAmtCurrent(zhrPcpBudgetItemVal.getAMT_CURRENT().getCurr152().doubleValue());
//                                pci2.setAmtNext(zhrPcpBudgetItemVal.getAMT_NEXT().getCurr152().doubleValue());
//                                pci2.setHcCurrent(zhrPcpBudgetItemVal.getHC_CURRENT().hashCode());
//                                pci2.setHcNext(zhrPcpBudgetItemVal.getHC_NEXT().hashCode());
//                                pci2.setBasicCurrent(zhrPcpBudgetItemVal.getBASIC_C().getCurr152().doubleValue());
//                                pci2.setBonusCurrent(zhrPcpBudgetItemVal.getBONUS_C().getCurr152().doubleValue());
//                                pci2.setOthersCurrent(zhrPcpBudgetItemVal.getOTHERS_C().getCurr152().doubleValue());
//                                pci2.setRentCurrent(zhrPcpBudgetItemVal.getRENT_C().getCurr152().doubleValue());
//                                pci2.setTransportCurrent(zhrPcpBudgetItemVal.getTRANSPORT_C().getCurr152().doubleValue());
//                                pci2.setBasicNext(zhrPcpBudgetItemVal.getBASIC().getCurr152().doubleValue());
//                                pci2.setBonusNext(zhrPcpBudgetItemVal.getBONUS().getCurr152().doubleValue());
//                                pci2.setOthersNext(zhrPcpBudgetItemVal.getOTHERS().getCurr152().doubleValue());
//                                pci2.setRentNext(zhrPcpBudgetItemVal.getRENT().getCurr152().doubleValue());
//                                pci2.setTransportNext(zhrPcpBudgetItemVal.getTRANSPORT().getCurr152().doubleValue());
//                            
//                                helper.update(pci2);
//                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                int year = Integer.parseInt(request.getParameter("year"));
                int prevYear = year - 1;

                if (request.getParameter("mdaID") != null) {
                    int mdaID = Integer.parseInt(request.getParameter("mdaID"));

                    Mdas mda = new MdaHibernateHelper().fetchObj(mdaID);

                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.append("<div id='divReport'><div style='text-align:center'><b>ONDO STATE OF NIGERIA ESTIMATES, " + year + "<br/>RECURRENT EXPENDITURE<br/>"
                            + "ADMIN CODE: " + mda.getAdministrativeSegment() + " - " + mda.getName() + "</b></div>");

                    strBuilder.append("<table class='table table-bordered table-striped' style='clear: both'>"
                            + "<tr><td colspan='6' style='text-align:center; font-weight: bold;'><b>EXPENDITURE DETAILS</b></td></tr>"
                            + "<tr><td colspan='3' style='text-align:center; font-weight: bold;'><b>Establishment</b></td><td style='text-align:center; font-weight: bold;'><b>Details of Expenditure</td>"
                            + "<td style='text-align:center; font-weight: bold;'>Approved<br/>Estimate</td><td style='text-align:center; font-weight: bold;'>Approved<br/>Estimate</td></tr>"
                            + "<tr><td style='text-align:center; font-weight: bold;'>S/N</td><td style='text-align:center; font-weight: bold;'>" + prevYear + "</td><td style='text-align:center; font-weight: bold;'>" + year
                            + "</td><td></td><td style='text-align:center; font-weight: bold;'>" + year + " (&#8358;)</td><td style='text-align:center; font-weight: bold;'>" + prevYear + " (&#8358;)</td></tr>");

                    strBuilder.append("<tr><td colspan='3'></td><td colspan='3' style='font-weight: bold;'>Office of the Chairman</td></tr>");
                    strBuilder.append("<tr><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Chairman Board</td><td style='text-align:right;'>4,713,718.55</td><td style='text-align:right;'>4,713,718.55</td></tr>");
                    strBuilder.append("<tr><td style='text-align:center;'>2</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Principal Executive Officer II (GL.10)</td><td style='text-align:right;'>4,398,742.03</td><td style='text-align:right;'>4,398,742.03</td></tr>");
                    strBuilder.append("<tr><td style='text-align:center;'>3</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Works Superintendent (GL.07)</td><td style='text-align:right;'>265,548.12</td><td style='text-align:right;'>265,548.12</td></tr>");

                    strBuilder.append("<tr><td colspan='3'></td><td colspan='3' style='font-weight: bold;'>Department of Finance and Administration</td></tr>");
                    strBuilder.append("<tr><td style='text-align:center;'>4</td><td style='text-align:center;'>1</td><td style='text-align:center;'>0</td><td>Chief Administrative Officer (GL.13)</td><td style='text-align:right;'>0</td><td style='text-align:right;'>1,264,858.56</td></tr>");
                    strBuilder.append("<tr><td style='text-align:center;'>5</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Director (GL.16)</td><td style='text-align:right;'>916,380.12</td><td style='text-align:right;'>916,380.12</td></tr>");
                    strBuilder.append("<tr><td style='text-align:center;'>6</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Administrative Officer Grade II (GL.08)</td><td style='text-align:right;'>338,612.16</td><td style='text-align:right;'>338,612.16</td></tr>");
                    strBuilder.append("<tr><td style='text-align:center;'>7</td><td style='text-align:center;'>12</td><td style='text-align:center;'>11</td><td>Security Guard Grade I (GL.04)</td><td style='text-align:right;'>1,582,550.64</td><td style='text-align:right;'>1,726,418.88</td></tr>");

                    strBuilder.append("<tr><td style='text-align:center; font-weight: bold;'>Total: </td><td style='text-align:center; font-weight: bold;'>18</td><td style='text-align:center; font-weight: bold;'>16</td><td></td><td style='font-weight: bold; text-align:right;'>12,215,551.62</td><td style='font-weight: bold; text-align:right;'>12,344,267.33</td></tr>");

                    strBuilder.append("</table><br/><br/>");

                    //Summary
                    strBuilder.append("<div style='text-align:center'><b>ONDO STATE OF NIGERIA ESTIMATES, " + year + "<br/>"
                            + mda.getAdministrativeSegment() + " : " + mda.getName() + "<br/>OVERALL SUMMARY OF PERSONNEL COST</b></div>");

                    strBuilder.append("<table class='table table-bordered table-striped' style='clear: both'>"
                            + "<thead><tr>"
                            + "<td></td><td style='text-align:center; font-weight: bold;'>Grade Level</td>"
                            + "<td style='text-align:center; font-weight: bold;'>No of Staff</td><td style='text-align:center; font-weight: bold;'>" + year + "<br/>Approved<br/>Estimate (&#8358;)</td>"
                            + "<td style='text-align:center; font-weight: bold;'>No of Staff</td><td style='text-align:center; font-weight: bold;'>" + prevYear + "<br/>Approved<br/>Estimate (&#8358;)</td>"
                            + "</tr></thead>"
                            + "<tbody>");

                    //Grade 1 - 6
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>1</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>2</td><td style='text-align:center;'>3</td><td style='text-align:right;'>377,318.88</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>3</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>14</td><td style='text-align:right;'>1,858,540.32</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>4</td><td style='text-align:center;'>10</td><td style='text-align:right;'>1,438,682.40</td><td style='text-align:center;'>2</td><td style='text-align:right;'>287,736.48</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>5</td><td style='text-align:center;'>3</td><td style='text-align:right;'>494,285.76</td><td style='text-align:center;'>2</td><td style='text-align:right;'>325,923.84</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>6</td><td style='text-align:center;'>3</td><td style='text-align:right;'>606,217.68</td><td style='text-align:center;'>1</td><td style='text-align:right;'>202,072.56</td></tr>");
                    strBuilder.append("<tr style='font-weight: bold;'><td>Total for 1-6:</td><td></td><td style='text-align:center;'>19</td><td style='text-align:right;'>2,916,504.72</td><td style='text-align:center;'>19</td><td style='text-align:right;'>2,677,873.20</td></tr>");

                    //Grade 7 - 12
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>7</td><td style='text-align:center;'>5</td><td style='text-align:right;'>1,327,740.60</td><td style='text-align:center;'>9</td><td style='text-align:right;'>2,389,933.08</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>8</td><td style='text-align:center;'>3</td><td style='text-align:right;'>1,015,836.48</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>9</td><td style='text-align:center;'>2</td><td style='text-align:right;'>800,017.20</td><td style='text-align:center;'>2</td><td style='text-align:right;'>800,017.20</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>10</td><td style='text-align:center;'>4</td><td style='text-align:right;'>1,856,394.72</td><td style='text-align:center;'>8</td><td style='text-align:right;'>3,712,789.44</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>11</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>12</td><td style='text-align:center;'>8</td><td style='text-align:right;'>4,590,567.36</td><td style='text-align:center;'>4</td><td style='text-align:right;'>2,295,283.68</td></tr>");
                    strBuilder.append("<tr style='font-weight: bold;'><td>Total for 7-12:</td><td></td><td style='text-align:center;'>22</td><td style='text-align:right;'>9,590,567.36</td><td style='text-align:center;'>23</td><td style='text-align:right;'>9,198,023.40</td></tr>");

                    //Grade 13 - 17
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>13</td><td style='text-align:center;'>5</td><td style='text-align:right;'>3,162,146.40</td><td style='text-align:center;'>6</td><td style='text-align:right;'>3,794,575.68</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>14</td><td style='text-align:center;'>1</td><td style='text-align:right;'>694,824.84</td><td style='text-align:center;'>3</td><td style='text-align:right;'>2,084,474.52</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>15</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>16</td><td style='text-align:center;'>1</td><td style='text-align:right;'>916,380.12</td><td style='text-align:center;'>1</td><td style='text-align:right;'>916,380.12</td></tr>");
                    strBuilder.append("<tr><td></td><td style='text-align:center;'>17</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                    strBuilder.append("<tr style='font-weight: bold;'><td>Total for 13-17:</td><td></td><td style='text-align:center;'>7</td><td style='text-align:right;'>4,773,351.36</td><td style='text-align:center;'>10</td><td style='text-align:right;'>6,795,430.32</td></tr>");

                    //Total 1 - 17
                    strBuilder.append("<tr style='font-weight: bold;'><td>Total for 1-17:</td><td></td><td style='text-align:center;'>48</td><td style='text-align:right;'>17,280,412.44</td><td style='text-align:center;'>52</td><td style='text-align:right;'>18,671,326.92</td></tr>");

                    //Extra Roles
                    strBuilder.append("<tr style='font-weight: bold;'><td colspan='6'></td></tr>");
                    strBuilder.append("<tr><td>Commissioner:</td><td></td><td style='text-align:center;'>1</td><td style='text-align:right;'>4,713,718.55</td><td style='text-align:center;'>1</td><td style='text-align:right;'>4,713,718.55</td></tr>");
                    strBuilder.append("<tr><td>PS:</td><td></td><td style='text-align:center;'>1</td><td style='text-align:right;'>5,864,989.00</td><td style='text-align:center;'>1</td><td style='text-align:right;'>5,864,989.00</td></tr>");
                    strBuilder.append("<tr style='font-weight: bold;'><td colspan='6'></td></tr>");

                    //Personnel Allowances
                    strBuilder.append("<tr style='font-weight: bold;'><td colspan='6'>Personnel Allowances</td></tr>");
                    strBuilder.append("<tr><td>TRANSPORT:</td><td></td><td></td><td style='text-align:right;'>60,549,444.79</td><td style='text-align:center;'></td><td style='text-align:right;'>61,966,758.02</td></tr>");
                    strBuilder.append("<tr><td>RENT:</td><td></td><td></td><td style='text-align:right;'>61,535,994.13</td><td style='text-align:center;'></td><td style='text-align:right;'>62,849,083.64</td></tr>");
                    strBuilder.append("<tr><td>LEAVE BONUS:</td><td></td><td></td><td style='text-align:right;'>17,883,252.38</td><td style='text-align:center;'></td><td style='text-align:right;'>18,163,047.84</td></tr>");
                    strBuilder.append("<tr><td>OTHERS:</td><td></td><td></td><td style='text-align:right;'>80,086,474.48</td><td style='text-align:center;'></td><td style='text-align:right;'>81,730,087.22</td></tr>");
                    strBuilder.append("<tr><td>Total Allowance:</td><td></td><td></td><td style='text-align:right;'>220,055,165.78</td><td style='text-align:center;'></td><td style='text-align:right;'>224,708,976.72</td></tr>");
                    strBuilder.append("<tr style='font-weight: bold;'><td>GRAND TOTAL:</td><td></td><td style='text-align:center;'>50</td><td style='text-align:right;'>411,053,175.39</td><td style='text-align:center;'>54</td><td style='text-align:right;'>125,976,192.63</td></tr>");

                    strBuilder.append("</tbody></table></div>");

                    resp = strBuilder.toString();
                } else {
                    List<Mdas> allMdas = new MdaHibernateHelper().fetchAllCategorizedSector();

                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.append("<div id='divReport'>");

                    for (Mdas mda : allMdas) {
                        strBuilder.append("<div style='text-align:center'><b>ONDO STATE OF NIGERIA ESTIMATES, " + year + "<br/>RECURRENT EXPENDITURE<br/>"
                                + "ADMIN CODE: " + mda.getAdministrativeSegment() + " - " + mda.getName() + "</b></div>");

                        strBuilder.append("<table class='table table-bordered table-striped' style='clear: both'>"
                                + "<tr><td colspan='6' style='text-align:center; font-weight: bold;'><b>EXPENDITURE DETAILS</b></td></tr>"
                                + "<tr><td colspan='3' style='text-align:center; font-weight: bold;'><b>Establishment</b></td><td style='text-align:center; font-weight: bold;'><b>Details of Expenditure</td>"
                                + "<td style='text-align:center; font-weight: bold;'>Approved<br/>Estimate</td><td style='text-align:center; font-weight: bold;'>Approved<br/>Estimate</td></tr>"
                                + "<tr><td style='text-align:center; font-weight: bold;'>S/N</td><td style='text-align:center; font-weight: bold;'>" + prevYear + "</td><td style='text-align:center; font-weight: bold;'>" + year
                                + "</td><td></td><td style='text-align:center; font-weight: bold;'>" + year + " (&#8358;)</td><td style='text-align:center; font-weight: bold;'>" + prevYear + " (&#8358;)</td></tr>");

                        strBuilder.append("<tr><td colspan='3'></td><td colspan='3' style='font-weight: bold;'>Office of the Chairman</td></tr>");
                        strBuilder.append("<tr><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Chairman Board</td><td style='text-align:right;'>4,713,718.55</td><td style='text-align:right;'>4,713,718.55</td></tr>");
                        strBuilder.append("<tr><td style='text-align:center;'>2</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Principal Executive Officer II (GL.10)</td><td style='text-align:right;'>4,398,742.03</td><td style='text-align:right;'>4,398,742.03</td></tr>");
                        strBuilder.append("<tr><td style='text-align:center;'>3</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Works Superintendent (GL.07)</td><td style='text-align:right;'>265,548.12</td><td style='text-align:right;'>265,548.12</td></tr>");

                        strBuilder.append("<tr><td colspan='3'></td><td colspan='3' style='font-weight: bold;'>Department of Finance and Administration</td></tr>");
                        strBuilder.append("<tr><td style='text-align:center;'>4</td><td style='text-align:center;'>1</td><td style='text-align:center;'>0</td><td>Chief Administrative Officer (GL.13)</td><td style='text-align:right;'>0</td><td style='text-align:right;'>1,264,858.56</td></tr>");
                        strBuilder.append("<tr><td style='text-align:center;'>5</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Director (GL.16)</td><td style='text-align:right;'>916,380.12</td><td style='text-align:right;'>916,380.12</td></tr>");
                        strBuilder.append("<tr><td style='text-align:center;'>6</td><td style='text-align:center;'>1</td><td style='text-align:center;'>1</td><td>Administrative Officer Grade II (GL.08)</td><td style='text-align:right;'>338,612.16</td><td style='text-align:right;'>338,612.16</td></tr>");
                        strBuilder.append("<tr><td style='text-align:center;'>7</td><td style='text-align:center;'>12</td><td style='text-align:center;'>11</td><td>Security Guard Grade I (GL.04)</td><td style='text-align:right;'>1,582,550.64</td><td style='text-align:right;'>1,726,418.88</td></tr>");

                        strBuilder.append("<tr><td style='text-align:center; font-weight: bold;'>Total: </td><td style='text-align:center; font-weight: bold;'>18</td><td style='text-align:center; font-weight: bold;'>16</td><td></td><td style='font-weight: bold; text-align:right;'>12,215,551.62</td><td style='font-weight: bold; text-align:right;'>12,344,267.33</td></tr>");

                        strBuilder.append("</table><br/><br/>");

                        //Summary
                        strBuilder.append("<div style='text-align:center'><b>ONDO STATE OF NIGERIA ESTIMATES, " + year + "<br/>"
                                + mda.getAdministrativeSegment() + " : " + mda.getName() + "<br/>OVERALL SUMMARY OF PERSONNEL COST</b></div>");

                        strBuilder.append("<table class='table table-bordered table-striped' style='clear: both'>"
                                + "<thead><tr>"
                                + "<td></td><td style='text-align:center; font-weight: bold;'>Grade Level</td>"
                                + "<td style='text-align:center; font-weight: bold;'>No of Staff</td><td style='text-align:center; font-weight: bold;'>" + year + "<br/>Approved<br/>Estimate (&#8358;)</td>"
                                + "<td style='text-align:center; font-weight: bold;'>No of Staff</td><td style='text-align:center; font-weight: bold;'>" + prevYear + "<br/>Approved<br/>Estimate (&#8358;)</td>"
                                + "</tr></thead>"
                                + "<tbody>");

                        //Grade 1 - 6
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>1</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>2</td><td style='text-align:center;'>3</td><td style='text-align:right;'>377,318.88</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>3</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>14</td><td style='text-align:right;'>1,858,540.32</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>4</td><td style='text-align:center;'>10</td><td style='text-align:right;'>1,438,682.40</td><td style='text-align:center;'>2</td><td style='text-align:right;'>287,736.48</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>5</td><td style='text-align:center;'>3</td><td style='text-align:right;'>494,285.76</td><td style='text-align:center;'>2</td><td style='text-align:right;'>325,923.84</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>6</td><td style='text-align:center;'>3</td><td style='text-align:right;'>606,217.68</td><td style='text-align:center;'>1</td><td style='text-align:right;'>202,072.56</td></tr>");
                        strBuilder.append("<tr style='font-weight: bold;'><td>Total for 1-6:</td><td></td><td style='text-align:center;'>19</td><td style='text-align:right;'>2,916,504.72</td><td style='text-align:center;'>19</td><td style='text-align:right;'>2,677,873.20</td></tr>");

                        //Grade 7 - 12
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>7</td><td style='text-align:center;'>5</td><td style='text-align:right;'>1,327,740.60</td><td style='text-align:center;'>9</td><td style='text-align:right;'>2,389,933.08</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>8</td><td style='text-align:center;'>3</td><td style='text-align:right;'>1,015,836.48</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>9</td><td style='text-align:center;'>2</td><td style='text-align:right;'>800,017.20</td><td style='text-align:center;'>2</td><td style='text-align:right;'>800,017.20</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>10</td><td style='text-align:center;'>4</td><td style='text-align:right;'>1,856,394.72</td><td style='text-align:center;'>8</td><td style='text-align:right;'>3,712,789.44</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>11</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>12</td><td style='text-align:center;'>8</td><td style='text-align:right;'>4,590,567.36</td><td style='text-align:center;'>4</td><td style='text-align:right;'>2,295,283.68</td></tr>");
                        strBuilder.append("<tr style='font-weight: bold;'><td>Total for 7-12:</td><td></td><td style='text-align:center;'>22</td><td style='text-align:right;'>9,590,567.36</td><td style='text-align:center;'>23</td><td style='text-align:right;'>9,198,023.40</td></tr>");

                        //Grade 13 - 17
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>13</td><td style='text-align:center;'>5</td><td style='text-align:right;'>3,162,146.40</td><td style='text-align:center;'>6</td><td style='text-align:right;'>3,794,575.68</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>14</td><td style='text-align:center;'>1</td><td style='text-align:right;'>694,824.84</td><td style='text-align:center;'>3</td><td style='text-align:right;'>2,084,474.52</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>15</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>16</td><td style='text-align:center;'>1</td><td style='text-align:right;'>916,380.12</td><td style='text-align:center;'>1</td><td style='text-align:right;'>916,380.12</td></tr>");
                        strBuilder.append("<tr><td></td><td style='text-align:center;'>17</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td><td style='text-align:center;'>-</td><td style='text-align:right;'>0</td></tr>");
                        strBuilder.append("<tr style='font-weight: bold;'><td>Total for 13-17:</td><td></td><td style='text-align:center;'>7</td><td style='text-align:right;'>4,773,351.36</td><td style='text-align:center;'>10</td><td style='text-align:right;'>6,795,430.32</td></tr>");

                        //Total 1 - 17
                        strBuilder.append("<tr style='font-weight: bold;'><td>Total for 1-17:</td><td></td><td style='text-align:center;'>48</td><td style='text-align:right;'>17,280,412.44</td><td style='text-align:center;'>52</td><td style='text-align:right;'>18,671,326.92</td></tr>");

                        //Extra Roles
                        strBuilder.append("<tr style='font-weight: bold;'><td colspan='6'></td></tr>");
                        strBuilder.append("<tr><td>Commissioner:</td><td></td><td style='text-align:center;'>1</td><td style='text-align:right;'>4,713,718.55</td><td style='text-align:center;'>1</td><td style='text-align:right;'>4,713,718.55</td></tr>");
                        strBuilder.append("<tr><td>PS:</td><td></td><td style='text-align:center;'>1</td><td style='text-align:right;'>5,864,989.00</td><td style='text-align:center;'>1</td><td style='text-align:right;'>5,864,989.00</td></tr>");
                        strBuilder.append("<tr style='font-weight: bold;'><td colspan='6'></td></tr>");

                        //Personnel Allowances
                        strBuilder.append("<tr style='font-weight: bold;'><td colspan='6'>Personnel Allowances</td></tr>");
                        strBuilder.append("<tr><td>TRANSPORT:</td><td></td><td></td><td style='text-align:right;'>60,549,444.79</td><td style='text-align:center;'></td><td style='text-align:right;'>61,966,758.02</td></tr>");
                        strBuilder.append("<tr><td>RENT:</td><td></td><td></td><td style='text-align:right;'>61,535,994.13</td><td style='text-align:center;'></td><td style='text-align:right;'>62,849,083.64</td></tr>");
                        strBuilder.append("<tr><td>LEAVE BONUS:</td><td></td><td></td><td style='text-align:right;'>17,883,252.38</td><td style='text-align:center;'></td><td style='text-align:right;'>18,163,047.84</td></tr>");
                        strBuilder.append("<tr><td>OTHERS:</td><td></td><td></td><td style='text-align:right;'>80,086,474.48</td><td style='text-align:center;'></td><td style='text-align:right;'>81,730,087.22</td></tr>");
                        strBuilder.append("<tr><td>Total Allowance:</td><td></td><td></td><td style='text-align:right;'>220,055,165.78</td><td style='text-align:center;'></td><td style='text-align:right;'>224,708,976.72</td></tr>");
                        strBuilder.append("<tr style='font-weight: bold;'><td>GRAND TOTAL:</td><td></td><td style='text-align:center;'>50</td><td style='text-align:right;'>411,053,175.39</td><td style='text-align:center;'>54</td><td style='text-align:right;'>125,976,192.63</td></tr>");

                        strBuilder.append("</tbody></table><br/><br/>");
                    }

                    strBuilder.append("<div/>");

                    resp = strBuilder.toString();
                }
            }

            if (option.equals(Utility.OPTION_GET_AVAILABLE_YEARS)) {
                resp = "[[2016],[2017]]";//helper.fetchAllYears();
            }
            //System.out.println(resp);

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
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ZHR_PCP_BudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ZHR_PCP_BudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ZHR_PCP_BudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ZHR_PCP_BudgetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
