/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetReport;
import com.bpp.hibernate.HibernateUtil;
import com.bpp.hibernate.ReportServletHelper;
import com.bpp.hibernate.YearBudgetVersions;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author OLAWUMMI
 */
public class ReportServlet extends HttpServlet {

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
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        //Variables

        String resp = "Nothing to Show";
        String option = request.getParameter("option");
        String _budget_year = request.getParameter("budget_year");
        String _ReportNumber = request.getParameter("ReportNumber");
        String _budget_type = request.getParameter("budget_type");
        String _ReportItem = request.getParameter("ReportItem");
        String _ItemName = request.getParameter("ItemName");
        String _ReportHead = request.getParameter("ReportHead");
        String _Currency = request.getParameter("Currency");
        String _CurrencyRate = request.getParameter("CurrencyRate");
        String ReportId = request.getParameter("ReportId");
        String _ActiveCurrency = request.getParameter("ActiveCurrency");
        if (_ActiveCurrency != null && _ActiveCurrency != "") {
            // _ActiveCurrency= _ActiveCurrency;
        } else {
            _ActiveCurrency = "4";
        }
        if (_ActiveCurrency.equals("") || _ActiveCurrency == null) {
            _ActiveCurrency = "4";
        }
        ReportServletHelper rptHelper = new ReportServletHelper();

        int year = Integer.parseInt(_budget_year);
        if (option.equals("Report")) {
            // List Reports            
            List<BudgetReport> BudgetReportList = rptHelper.fetchAll(year);
            String html = "<table class='table table-bordered table-striped'><thead><tr><th>Report Id</th><th>Title</th><th>Budget Year</th><th>Creation Date</th></tr></thead><tbody>";

            for (BudgetReport rpts : BudgetReportList) {
                html += "<tr onclick='LoadBudgetDocument(" + rpts.getId() + ")'><td>" + rpts.getId() + "</td><td>" + rpts.getReportTitle() + "</td><td>" + rpts.getBudgetYear() + "</td><td>" + rpts.getReportDate() + "<td>";
            }
            html += "</tbody></table>";
            resp = html;
        } else if (option.equals(Utility.OPTION_SELECT)) {
            //Load Single Report
            if (!ReportId.equals("")) {
                resp = rptHelper.fetch(ReportId);
            }
        } else if (option.equals("getBudgetyears")) {
            //Load allBuddget Years
            resp = rptHelper.fetchBudgetYears();
        } else if (option.equals("getBudgetVersions")) {
            resp = rptHelper.fetchYearBudgetVersion();
            System.out.println("resp  "+resp);
        } else if (option.equals("getCurrencies")) {
            resp = rptHelper.fetchCurrencies();
        } else if (option.equals("getBudgetType")) {
            resp = rptHelper.fetchBudgetType();
        } else if (option.equals("ChangeCurrency")) {
            System.out.println("ac :" + _ActiveCurrency);
            if (_ActiveCurrency.equals("")) {
                resp = "Currency Change Not Successful";
            } else {
                int currency = Integer.parseInt(_ActiveCurrency);

                resp = "Currency Change Successful";
            }
        } else if (option.equals(Utility.OPTION_UPDATE)) {
            String DocumentTitle = request.getParameter("ReportTitle");
            String DocumentText = request.getParameter("ReportText");
            BudgetReport budgetreport = new BudgetReport();
            budgetreport.setReportText(DocumentText);
            budgetreport.setReportTitle(DocumentTitle);
            budgetreport.setBudgetYear(Integer.parseInt(_budget_year));
            if (ReportId != null && ReportId != "") {
                budgetreport.setId(Integer.parseInt(ReportId));   
                resp = rptHelper.update(budgetreport);
            }else{
                resp = rptHelper.insert(budgetreport);
            }
             
        } else {

            // String ReportHeaders = request.getParameterMap().getParameter("ReportHeaders");
            Map<String, String[]> allMap = request.getParameterMap();
            for (String key : allMap.keySet()) {
                String[] strArr = (String[]) allMap.get(key);
                for (String val : strArr) {
                    System.out.println(key + " = " + val);
                }
            }
//            Integer ReportNumber;
//
//            Integer year;
//            String ReportHead;
//            String html = "";
        }

        out.println(resp);
    }

    public String RevenuReportbyEconomicSegment(Integer year, String code, String category, String Head, Integer ReportNum, String _budget_type) {
        String budget_type_condition_actual = "";
        String budget_type_condition_budget = "";
        if (_budget_type.equals("1")) {
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (1,4) ";
        } else if (_budget_type.equals("2")) {
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (2,3) ";
        }
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy = Titles(ReportNum, " , ", year, _budget_type);
        String htmltable = null;
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + dummy;
        htmltable += "</tr><tr><th>Code</th><th>Revenue Details</th><th>Actual Revenue Jan - Dec " + (year - 2) + "</th><th>Actual Revenue Jan - Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th><th>Approved Estimates " + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "";
            sql = "select a.code, a.name,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c  INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment  where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 0) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4 "
                    + " from Economic_Segment_Header1 a  where a.code LIKE'" + Head + "%' UNION "
                    + " select a.code, a.name, "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 0) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4 "
                    + " from Economic_Segment_Header3 a where a.code LIKE'" + Head + "%' "
                    + " UNION select a.code, a.name,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 0) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4 "
                    + " from Economic_Segment_Header4 a where a.code LIKE'" + Head + "%'";
//            System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmp1 : tmp) {
                Check4Nulls(tmp1, 2);
                htmltable += "<tr>";
                for (int i = 0; i < 6; i++) {
                    if (i > 1) {
                        if (tmp1[i] == null) {
                            tmp1[i] = 0.0;
                        }
                        htmltable += "<td  align='right'>" + String.format("%,.2f", tmp1[i]) + "</td>";
                    } else {
                        htmltable += "<td >" + tmp1[i] + "</td>";
                    }
                }
                htmltable += "</tr>";
            }
            htmltable += "</tbody></table>";
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return htmltable;
    }

    String Queries(Integer year, String segment, String code, String _budget_type) {
        String budget_type_condition_actual = "";
        String budget_type_condition_budget = "";
        if (_budget_type.equals("1")) {
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (1,4) ";
        } else if (_budget_type.equals("2")) {
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (2,3) ";
        }
        String sqlq = null;
        if (null != segment) {
            switch (segment) {
                case "Economic Segment":
                    if (!"ALL".equals(code)) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment  where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header1 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header2 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header3 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header4 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,8)=a.code " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment a  where a.code LIKE'" + code + "%'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header3 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment_Header4 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Economic_Segment a";
                        return sqlq;
                    }
                case "Functional Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Functional_Segment_Header1 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Functional_Segment_Header2 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Functional_Segment a where left(a.code,3)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Functional_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Functional_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Functional_Segment a";
                        return sqlq;
                    }
                case "Fund Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.fund_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Fund_Segment_Header1 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Fund_Segment_Header2 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Fund_Segment a where left(a.code,2)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,23)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.fund_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Fund_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Fund_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Fund_Segment a";
                        return sqlq;
                    }
                case "Geographic Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment_Header1 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment_Header2 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment_Header3 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment a where left(a.code,3)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment_Header3 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Geographic_Segment a";
                        return sqlq;
                    }
                case "Programme Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Programme_Segment_Header1 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Programme_Segment_Header2 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Project_Detail a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Programme_Segment a where left(a.code,2)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Programme_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Programme_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Project_Detail a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_actual + ") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + year + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' " + budget_type_condition_budget + ") as amt4"
                                + " from Programme_Segment a";
                        return sqlq;
                    }
                default:
                    break;
            }
        }

        return sqlq;
    }

    String Titles(Integer idx, String constStrings, Integer year, String _budget_type) {
        String budget_type = "";
        if (_budget_type.equals("1")) {
            budget_type = " (REVENUE)";
        } else if (_budget_type.equals("2")) {
            budget_type = " (EXPENDITURE)";
        }
        String[] RepTitle = new String[30];
        String[] consts = constStrings.split("\\,");
        if (null != idx) {
            switch (idx) {
                case 1:
                    return "ALLOCATIONS TO MDAs BY " + consts[1].toUpperCase() + " SECTOR " + budget_type + "</br>" + year + " BUDGET";
                case 2:
                    return "ALLOCATIONS TO MDAs BY " + consts[1].toUpperCase() + " " + budget_type + "</br>" + year + " BUDGET";
                case 3:
                    return RepTitle[3] = "FIRST SCHEDULE" + budget_type + "</br>YEAR " + year + " APPROVED RECURRENT ESTIMATES</br>ALLOCATION OF FUNDS TO MINISTRIES/ DEPARTMENTS/ AGENCIES IN THE STATE";
                case 4:
                    return "SECOND SCHEDULE" + budget_type + "</br> YEAR " + year + " CAPITAL ESTIMATES</br>ALLOCATION TO ARMS/MINISTRIES/ DEPARTMENTS/ AGENCIES";
                case 5:
                    return "SUMMARY OF APPROVED RECURRENT AND CAPITAL ESTIMATES" + budget_type + " " + year;
                case 6:
                    return "YEAR " + year + " APPROVED ESTIMATES" + budget_type + "</br>SUMMARY OF REVENUE BY ECONOMIC SEGMENT " + year;
                case 7:
                    return "YEAR " + year + " APPROVED ESTIMATES" + budget_type + "</br>DETAILS OF REVENUE BY ECONOMIC SEGMENT " + year;
                case 8:
                    return "Summary of Total Revenue Based on Sector by Independent Revenue. ".toUpperCase() + year;
                case 9:
                    return "Revenue Details<br> Revenue Estimates ".toUpperCase() + year;
                case 10:
                    return "YEAR " + year + " APPROVED ESTIMATES" + budget_type + "</br>SUMMARY OF RECUURENT ESTIMATES";
                case 11:
                    return "ESTIMATES " + year + "<br>GRANTS TO PARASTATALS / TERTIARY INSTITUTIONS " + budget_type;
                case 15:
                case 12:
                    return "ESTIMATES " + year + "<br>RECURRENT EXPENDITURE";
                case 13:
                    return "CONSOLIDATED REVENUE FUNDS CHARGES " + year;
                case 14:
                    return "ESTIMATES " + year + "<br>GRANTS AND LOANS" + budget_type;
                case 16:
                    return "<br>" + budget_type + " SECTORAL SUMMARY OF " + year + " BUDGET";
                case 17:
                    return year + "ALLOCATION OF FUNDS TO MDAs IN THE STATE" + budget_type;
                case 18:
                    return "CONSOLIDATED BUDGET SUMMARY (MASTER BUDGET) " + year + "<br>" + budget_type + " Summary of Capital Receipts".toUpperCase();
                case 19:
                    return "CONSOLIDATED BUDGET SUMMARY (MASTER BUDGET) " + year + "<br>" + budget_type + " BASED ON SECTORS".toUpperCase();
                case 21:
                    return "APPROVED CAPITAL ESTIMATES <br/>" + budget_type + " PROJECT DETAILS ".toUpperCase() + year;
                case 20:
                    return budget_type + " SPECIAL PROGRAMME ".toUpperCase() + year;
                case 23:
                    return "<br>" + budget_type + " DETAILS OF CAPITAL PROJECT BUDGET UNDER PROGRAMME" + year;
                case 22:
                    return "" + budget_type + " SUMMARY OF TOTAL CAPITAL BUDGET BASED ON SECTORS ".toUpperCase() + year;
                case 26:
                    return budget_type + " Summary of Capital Budget By functions (COFOG) ".toUpperCase() + year;
                case 24:
                    return year + " capital budget<br>" + budget_type + " allocations to ministries, departments and agencies".toUpperCase();
                case 25:
                    return budget_type + " Summary of Capital Budget By Programme ".toUpperCase() + year;
                case 27:
                    return "YEAR " + year + " APPROVED ESTIMATES</br>" + budget_type + " SUMMARY OF REVENUE BY " + consts[0].toUpperCase();
                case 28:
                    return budget_type + " Summary of Capital Budget By Policy ".toUpperCase() + year;
                case 33:
                    return "<br>" + budget_type + "CAPITAL EXPENDITURE B ECONOMIC SEGMENT " + year;
                case 40:
                    return "" + budget_type + " SUMMARY OF APPROVED PERSONNEL COST ".toUpperCase() + year;
                case 41:
                    return ("Estimates, " + year + "<br>RECURRENT EXPENDITURE ").toUpperCase();
                case 42:
                    return ("Estimates, " + year + "<br>" + budget_type + " OVERALL SUMMARY OF PERSONNEL COST ").toUpperCase();
//                case 4000:
//                    return "RECURRENT EXPENDITURE</br>HEAD " + consts[0] + " - " + consts[1] + "</br>OVERHEAD COST";
                default:
                    return "";
            }
        }
        return "";
    }

    boolean Check4Nulls(String[] val) {
        String str = null;
        for (int i = 0; i < val.length; i++) {
            if (val[i] != null) {
                str = val[i];
            }
        }
        if (str != null) {
            return false;
        } else {
            return true;
        }
    }

    boolean Check4Nulls(Object[] val, Integer n) {
        String str = null;
        for (int i = n; i < val.length; i++) {
            if (val[i] != null || val[i] == "nu") {
                str = "x";
            }
        }
        if (str != null) {
            return false;
        } else {
            return true;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
