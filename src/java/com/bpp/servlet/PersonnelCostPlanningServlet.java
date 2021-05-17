/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
import com.bpp.hibernate.PersonnelCostItems;
import com.bpp.hibernate.PersonnelCostItemsHibernateHelper;
import com.bpp.hibernate.PersonnelGradeLevels;
import com.bpp.hibernate.PersonnelGradeLevelsHibernateHelper;
import com.bpp.hibernate.PersonnelRelInfo;
import com.bpp.hibernate.PersonnelRelInfoHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;

import functions.rfc.sap.document.sap_com.YPCP_COSTITEMSStub;
import functions.rfc.sap.document.sap_com.YPCP_COSTITEMSStub.ZPCP_COSTITEMS_LINE;
import functions.rfc.sap.document.sap_com.YPCP_GRADEStub;
import functions.rfc.sap.document.sap_com.YPCP_GRADEStub.ZPCP_GRADE_LINE;
import functions.rfc.sap.document.sap_com.YPCP_RELINFOStub;
import functions.rfc.sap.document.sap_com.YPCP_RELINFOStub.ZPCP_RELINFO_LINE;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Personnel Cost Planning Servlet class
 *
 * @author Lekan
 * @since 19/10/2017
 *
 */
public class PersonnelCostPlanningServlet extends HttpServlet {

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

            PersonnelCostItemsHibernateHelper helper = new PersonnelCostItemsHibernateHelper();
            PersonnelGradeLevelsHibernateHelper helper2 = new PersonnelGradeLevelsHibernateHelper();
            PersonnelRelInfoHibernateHelper helper3 = new PersonnelRelInfoHibernateHelper();

            String option;
            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    try {
                        
                        //Get Personnel Cost Items for current runID!            	
                        YPCP_COSTITEMSStub stub = new YPCP_COSTITEMSStub();
                        YPCP_COSTITEMSStub.YPCP_COSTITEMS costItemRequest = new YPCP_COSTITEMSStub.YPCP_COSTITEMS();

                        String runID = "0000000147",
                                objectNumber = "50000517";

                        int year = new BudgetYearHibernateHelper().fetchCurrentYear2().getYear();

                        costItemRequest.setCOSTITEM_TAB(new YPCP_COSTITEMSStub.TABLE_OF_ZPCP_COSTITEMS_LINE());
                        costItemRequest.setOBJECT_NUMBER(new YPCP_COSTITEMSStub.String(objectNumber));
                        costItemRequest.setOBJECT_TYPE(new YPCP_COSTITEMSStub.String("O"));
                        costItemRequest.setRUNID(new YPCP_COSTITEMSStub.String(runID));

                        YPCP_COSTITEMSStub.YPCP_COSTITEMSResponse costItemResponse = stub.yPCP_COSTITEMS(costItemRequest);

                        ZPCP_COSTITEMS_LINE[] costItemValues = costItemResponse.getCOSTITEM_TAB().getItem();

                        for (ZPCP_COSTITEMS_LINE costItemVal : costItemValues) {
                            PersonnelCostItems pci = new PersonnelCostItems();
                            pci.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                            pci.setDateCreated(Utility.dbDateNow());
                            pci.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                            pci.setRunId(runID);
                            pci.setYear(year);
                            pci.setObjectType(costItemVal.getOTYPE().getChar2());
                            pci.setObjectId(costItemVal.getOBJID().getNumeric8());
                            pci.setCostItem(costItemVal.getCITEM().getChar4());
                            pci.setStartDate(DateFormat.getDateInstance().parse(costItemVal.getBEGDA().getDate10()));
                            pci.setEndDate(DateFormat.getDateInstance().parse(costItemVal.getENDDA().getDate10()));
                            pci.setAmount(costItemVal.getBETRG().getCurr152().doubleValue());
                            pci.setAmountPerUnit(costItemVal.getRATE().getCurr152().doubleValue());
                            pci.setCurrencyKey(costItemVal.getWAERS().getCuky5());
                            pci.setNumber(costItemVal.getZNUMBER().getDecimal152().toString());
                            pci.setUnit(costItemVal.getUNIT().getChar3());
                            pci.setPeriod(costItemVal.getPERIO().getNumeric2());
                            pci.setInterface_(costItemVal.getINTERFACE().getChar4());
                            pci.setHrPosition(costItemVal.getHR_POSITION().getNumeric8());
                            pci.setCostItem(costItemVal.getCITEM_BAS().getChar4());
                            pci.setOrgGroup(costItemVal.getORGGRP().getChar3());
                            pci.setIndicatorDiffca(costItemVal.getDIFFCA().getNumeric6());
                            pci.setAccAssignId(costItemVal.getACC_ID().getNumeric8());
                            pci.setDerived(costItemVal.getDERIVED().getChar1());
                            pci.setEventId(costItemVal.getEVENT_ID().getNumeric5());

                            resp = helper.insert(pci);

                            if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
                                PersonnelCostItems pci2 = helper.fetchObj(pci.getRunId(), pci.getYear(), pci.getObjectType(), pci.getObjectId());
                                pci2.setAmount(pci.getAmount());

                                helper.update(pci2);
                            }
                        }

                        //Get Grade Levels on current runID
                        YPCP_GRADEStub stub2 = new YPCP_GRADEStub();
                        YPCP_GRADEStub.YPCP_GRADE gradeRequest = new YPCP_GRADEStub.YPCP_GRADE();

                        gradeRequest.setCOSTITEM_TAB(new YPCP_GRADEStub.TABLE_OF_ZPCP_GRADE_LINE());
                        gradeRequest.setOBJECT_NUMBER(new YPCP_GRADEStub.String(objectNumber));
                        gradeRequest.setOBJECT_TYPE(new YPCP_GRADEStub.String("O"));
                        gradeRequest.setRUNID(new YPCP_GRADEStub.String(runID));

                        YPCP_GRADEStub.YPCP_GRADEResponse gradeResponse = stub2.yPCP_GRADE(gradeRequest);

                        ZPCP_GRADE_LINE[] gradeValues = gradeResponse.getCOSTITEM_TAB().getItem();

                        for (ZPCP_GRADE_LINE gradeVal : gradeValues) {
                            PersonnelGradeLevels pgl = new PersonnelGradeLevels();
                            pgl.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                            pgl.setDateCreated(Utility.dbDateNow());
                            pgl.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                            pgl.setRunId(runID);
                            pgl.setYear(year);
                            pgl.setObjectType(gradeVal.getOTYPE().getChar2());
                            pgl.setObjectId(gradeVal.getOBJID().getNumeric8());
                            pgl.setStartDate(DateFormat.getDateInstance().parse(gradeVal.getBEGDA().getDate10()));
                            pgl.setEndDate(DateFormat.getDateInstance().parse(gradeVal.getENDDA().getDate10()));
                            pgl.setPersonnelArea(gradeVal.getWERKS().getChar4());
                            pgl.setPersonnelSubArea(gradeVal.getBTRTL().getChar4());
                            pgl.setCompanyCode(gradeVal.getBUKRS().getChar4());
                            pgl.setBusinessArea(gradeVal.getGSBER().getChar4());
                            pgl.setEmployeeGroup(gradeVal.getPERSG().getChar1());
                            pgl.setEmployeeSubgroup(gradeVal.getPERSK().getChar2());
                            pgl.setOrgKey(gradeVal.getVDSK1().getChar14());
                            pgl.setLegalPerson(gradeVal.getJUPER().getChar4());
                            pgl.setWorkContract(gradeVal.getANSVH().getChar2());
                            pgl.setPayScaleType(gradeVal.getTRFAR().getChar2());
                            pgl.setPayScaleArea(gradeVal.getTRFGB().getChar2());
                            pgl.setEsGrouping(gradeVal.getTRFKZ().getChar1());
                            pgl.setPayScaleGroup(gradeVal.getTRFGR().getChar8());
                            pgl.setPayScaleLevel(gradeVal.getTRFST().getChar2());
                            pgl.setPayScaleLevel2(gradeVal.getTRFS2().getChar2());
                            pgl.setCountryGrouping(gradeVal.getMOLGA().getChar2());

                            resp = helper2.insert(pgl);

                            if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
                                PersonnelGradeLevels pgl2 = helper2.fetchObj(pgl.getRunId(), pgl.getYear(), pgl.getObjectType(), pgl.getObjectId());
                                pgl2.setWorkContract(pgl.getWorkContract());

                                helper2.update(pgl2);
                            }
                        }

                        //Get Rel-Info
                        YPCP_RELINFOStub stub3 = new YPCP_RELINFOStub();
                        YPCP_RELINFOStub.YPCP_RELINFO relInfoRequest = new YPCP_RELINFOStub.YPCP_RELINFO();

                        relInfoRequest.setCOSTITEM_TAB(new YPCP_RELINFOStub.TABLE_OF_ZPCP_RELINFO_LINE());
                        relInfoRequest.setOBJECT_NUMBER(new YPCP_RELINFOStub.String(objectNumber));
                        relInfoRequest.setOBJECT_TYPE(new YPCP_RELINFOStub.String("O"));
                        relInfoRequest.setRUNID(new YPCP_RELINFOStub.String(runID));

                        YPCP_RELINFOStub.YPCP_RELINFOResponse relInfoResponse = stub3.yPCP_RELINFO(relInfoRequest);

                        ZPCP_RELINFO_LINE[] relInfoValues = relInfoResponse.getCOSTITEM_TAB().getItem();

                        for (ZPCP_RELINFO_LINE relInfoVal : relInfoValues) {
                            PersonnelRelInfo pri = new PersonnelRelInfo();
                            pri.setId(Integer.parseInt(helper.getMaxserialNo()) + 1);
                            pri.setDateCreated(Utility.dbDateNow());
                            pri.setOrgId(Utility.SYSTEM_ORGANIZATION_ID);
                            pri.setRunId(runID);
                            pri.setYear(year);
                            pri.setObjectType(relInfoVal.getOTYPE().getChar2());
                            pri.setObjectId(relInfoVal.getOBJID().getNumeric8());
                            pri.setStartDate(DateFormat.getDateInstance().parse(relInfoVal.getBEGDA().getDate10()));
                            pri.setEndDate(DateFormat.getDateInstance().parse(relInfoVal.getENDDA().getDate10()));
                            pri.setClient(relInfoVal.getMANDT().getClnt3());
                            pri.setAssignPercentage(relInfoVal.getPROZT().getDecimal82().intValue());
                            pri.setHrPosition(relInfoVal.getHR_POSITION().getNumeric8());
                            pri.setHrNumberOfPositions(relInfoVal.getHR_NUMBER().getDecimal82().intValue());

                            resp = helper3.insert(pri);

                            if (resp.equals(Utility.ActionResponse.RECORD_EXISTS.toString())) {
                                PersonnelRelInfo pri2 = helper3.fetchObj(pri.getRunId(), pri.getYear(), pri.getObjectType(), pri.getObjectId());
                                pri2.setHrNumberOfPositions(pri.getHrNumberOfPositions());

                                helper3.update(pri2);
                            }
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
            Logger.getLogger(PersonnelCostPlanningServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PersonnelCostPlanningServlet.class.getName()).log(Level.SEVERE, null, ex);
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
