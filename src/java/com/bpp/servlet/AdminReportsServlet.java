/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetTypeComponentsHibernateHelper;
import com.bpp.hibernate.HibernateUtil;
import com.bpp.hibernate.ReportServletHelper;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

/**
 *
 * @author Ola
 */
public class AdminReportsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    float ExchangeRate = 1;
    String CurrencySymbol;
    int ActiveVersion;
    String ActiveBudgetType;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        String resp = "Nothing to Show";
        String _budget_year = request.getParameter("budget_year");
        String _ReportNumber = request.getParameter("ReportNumber");
        String _ReportItem = null;
        String _BudgetVersions2Report = request.getParameter("BudgetVersions2Report");
        //System.out.println("_BudgetVersions2Report   "+_BudgetVersions2Report);
        String _ItemName = null;
        String _budget_type  = request.getParameter("budget_type");
        //String _ActiveCurrency  = request.getParameter("ActiveCurrency");
        //String _ActiveVersion  = request.getParameter("ActiveVersion");
        //String _ActiveBudgetType  = request.getParameter("ActiveBudgetType");
        //System.out.println("_ActiveCurrency::: "+_ActiveCurrency);
        _ReportItem = request.getParameter("ReportItem");
        _ItemName = request.getParameter("ItemName");
        String _ReportHead = request.getParameter("ReportHead");
        String _ActiveCurrency = request.getParameter("ActiveCurrency");
        int ActiveCurrency;
        if (_ActiveCurrency != null && !_ActiveCurrency.equals("")) {
            ActiveCurrency = Integer.parseInt(_ActiveCurrency);
        } else {
            ActiveCurrency = 4;
        }

        String _ActiveVersion = request.getParameter("ActiveVersion");

        if (_ActiveVersion != null && !_ActiveVersion.equals("")) {
            ActiveVersion = Integer.parseInt(_ActiveVersion);
        } else {
            ActiveVersion = 5; //
        }

        String _ActiveBudgetType = request.getParameter("ActiveBudgetType");

        if (_ActiveBudgetType != null && !_ActiveBudgetType.equals("")) {
            ActiveBudgetType = GetBudgetType(_ActiveBudgetType);
        } else {
            ActiveBudgetType = "BI"; //
        }

        CurrencySymbol = GetCurrencySymbol(ActiveCurrency);
        ExchangeRate = GetCurrencyExchangeRate(ActiveCurrency);

        Integer ReportNumber;
        //String ReportItem=_ReportItem;
        Integer year;
        String ReportHead;
        String html = "";
        ////////////////////////////////

        if (_budget_type == null) {
            _budget_type = "";
        }
        
        if (_budget_year != null && !_budget_year.equals("")) {
            year = Integer.parseInt(_budget_year);
        } else {
            out.println(resp);
            return;
        }
        if (_ReportNumber != null && !_ReportNumber.equals("")) {
            ReportNumber = Integer.parseInt(_ReportNumber);
            if (ReportNumber == 13) {
                _ReportHead = "21010103,21020202,22010101,22010102,22010104,22060102,22060202,22070104,22070105,22070106";
            }
        } else {
            out.println(resp);
            return;
        }
        String[] ReportHeadAry = null;
        if (_ReportHead != null) {
            ReportHeadAry = _ReportHead.split("\\,");
        } else {
            ReportHeadAry = request.getParameterValues("ReportHead");
        }

//        System.out.println(ReportNumber);
        if (null != _ReportItem) {
            switch (_ReportItem) {
                case "VersionReport":
                    if (_BudgetVersions2Report != null && !_BudgetVersions2Report.equals("")) {
                        String[] BudgetVersions2Report = _BudgetVersions2Report.split("\\,");
                        resp = VersionReport(year, BudgetVersions2Report, _ReportItem, _ItemName, _ReportHead, ReportNumber, _budget_type);
                    }
                    break;
                case "Custom":
                    resp = RunClientSQL(_ItemName);
                    break;
                case "Allocation":
                    switch (ReportNumber) {
                        case 5:
                            resp = RevenueReport(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                            break;
                        case 17:
                            resp = RevenueReport0(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                            break;
                        case 3:
                            resp = RevenueFirstSchedule(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                            break;
                        case 4:
                            resp = RevenueSecondSchedule(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                            break;
                        default:
                            break;
                    }
                    break;
                case "Personnel":
                    switch (ReportNumber) {
                        case 40:
                            resp = SummaryPersonnelCost(year, ReportNumber, _budget_type, _ActiveVersion);
                            break;
                        case 42:
                            resp = PersonnelReport1(year, ReportNumber, _budget_type, _ActiveVersion);
                            break;
                        default:
                            resp = PersonnelReport(year, ReportNumber, _budget_type, _ActiveVersion);
                            break;
                    }
                    break;
                case "Sectors":
                    resp = SectorReport(year, _ReportItem, _ItemName, _ReportHead, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "SubSectors":
                    resp = SectorReport(year, _ReportItem, _ItemName, _ReportHead, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "Revenue":
                    if (ReportNumber == 6) {
                        resp = RevenuReportbyEconomicSegment(year, _ReportItem, _ItemName, _ReportHead, ReportNumber, _budget_type, _ActiveVersion);
                    } else {
                        resp = RevenuReport(year, _ReportItem, _ItemName, _ReportHead, ReportNumber, _budget_type, _ActiveVersion);
                    }
                    break;
                case "Independent Revenue":
                    resp = GetIRbySectorReports(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "Revenue Details":
                    resp = RevenueEstimatesReport(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "Recurrent Estimates":
                    resp = RevenueReport1(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "Grants":
                    resp = GrantReport(year, _ReportHead, _ItemName, _ReportItem, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "CRF":
                    resp = CRFReport(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "Expenditures":
                    resp = RevenueEstimatesReport0(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "Sectoral Summary":
                    resp = SectoralSummary(year, ReportHeadAry, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case "Segments":
                    if (ReportNumber == 33) {
                        _ItemName = "Economic Segment";
                    }
                    resp = SegmentReport(year, _ReportHead, _ItemName, ReportNumber, _budget_type, _ActiveVersion);
                    //resp = SectoralSummary(year, ReportHeadAry, ReportNumber);
                    break;
                case "Capital Estimates":
            switch (ReportNumber) {
                case 20:
                    resp = GetMdaReports(year, _ItemName, _ReportHead, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case 21:
                    resp = GetMdasReports(year, _ItemName, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case 22:
                    resp = GetBudgetBySectors(year, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                default:
                    break;
            }
                    break;
                case "Budget Summary":
            switch (ReportNumber) {
                case 26:
                    resp = GetFunctionalSegmentBudget(year, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case 25:
                    resp = SummaryProgrammeSegment(year, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case 28:
                    resp = CapitalBudgetbyPolicyReport(year, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case 19:
                case 18:
                    resp = ConsolidatedBudgetSummary0(year, ReportNumber, _budget_type, _ActiveVersion);
                    break;
                case 24:
                    resp=CapitalBudgetAllocation2MDAS(year, ReportNumber, _budget_type);
                    break;
                default:
                    break;
            }

                    break;

                default:
                    break;
            }
        }
        out.println(resp);
    }

    public String SectorReport(Integer year, String Item, String _ItemName, String code, Integer ReportNumber, String _budget_type, String _ActiveVersion) {
         //System.out.println("SectorReport   year::: "+year);
        String[][] TableVals = null;
        String A = "A";
        String B = "B";
        String C = "C";
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (2,3) ";
        }
        String htmltable = null;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "WITH nV AS (Select M.administrative_segment as code, M.name as name,"
                    + " (select sum(b.AmountLc) from SAP_Actuals b where b.FiscYear=(" + (year - 2) + ") and b.FundsCtr=M.administrative_segment "+budget_type_condition_actual+") as A1,"
                    + " (select sum(b.AmountLc) from SAP_Actuals b where b.FiscYear=(" + (year - 1) + ") and b.FundsCtr=M.administrative_segment "+budget_type_condition_actual+") as A2,"
                    + " (select sum(c.budget_amount) from Year_Budget c where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=(" + (year - 1) + ") and c.admin_segment=M.administrative_segment "+budget_type_condition_budget+") as A3,"
                    + " (select sum(c.budget_amount) from Year_Budget c where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=(" + (year - 0) + ") and c.admin_segment=M.administrative_segment "+budget_type_condition_budget+") as A4"
                    + " from MDAs M WHERE M.sub_sector_code <>'00' and left(M.administrative_segment,2) = '" + code + "')"
                    + " Select x.code,x.name,x.A1,x.A2,x.A3,x.A4 from nV as x "
                    + " UNION SELECT 'Total','Total', Sum(x.A1),SUM(x.A2),SUM(x.A3),SUM(x.A4) from nV as x";
            String sql2 = "WITH nV AS (Select M.administrative_segment as code, M.name as name,"
                    + " (select sum(b.AmountLc) from SAP_Actuals b where b.FiscYear=(" + (year - 2) + ") and b.FundsCtr=M.administrative_segment "+budget_type_condition_actual+") as A1,"
                    + " (select sum(b.AmountLc) from SAP_Actuals b where b.FiscYear=(" + (year - 1) + ") and b.FundsCtr=M.administrative_segment "+budget_type_condition_actual+") as A2,"
                    + " (select sum(c.budget_amount) from Year_Budget c where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=(" + (year - 1) + ") and c.admin_segment=M.administrative_segment "+budget_type_condition_budget+") as A3,"
                    + " (select sum(c.budget_amount) from Year_Budget c where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=(" + (year - 0) + ") and c.admin_segment=M.administrative_segment "+budget_type_condition_budget+") as A4"
                    + " from MDAs M WHERE M.sub_sector_code <>'00' and M.sub_sector_code = '" + code + "')"
                    + " Select x.code,x.name,x.A1,x.A2,x.A3,x.A4 from nV as x "
                    + " UNION SELECT 'Total','Total', Sum(x.A1),SUM(x.A2),SUM(x.A3),SUM(x.A4) from nV as x";

            if (ReportNumber == 2) {
                sql = sql2;
            }
            //System.out.println(sql);
            int start = sql.length();
            String html = "";
            List<Object[]> tmp0 = session.createSQLQuery(sql).list();
            int i = 0;
            int ncount = tmp0.size();
            for (Object[] tmp : tmp0) {
                if (!"Total".equals(tmp[0])) {
                    html += "<tr><td>" + tmp[0] + "</td><td>" + tmp[1] + "</td>";
                    for (int j = 2; j < 6; j++) {
                        if(tmp[j]==null) {
                            tmp[j] = 0.0;
                        }
                        html += "<td align='right'>" + ConvertCurrency((tmp[j])) + "</td>";
                    }
                    html += "</tr>";
                } else {
                    html += "<tr><th colspan='2'>" + tmp[0] + "</th>";
                    for (int j = 2; j < 6; j++) {
                        if(tmp[j]==null) {
                            tmp[j] = 0.0;
                        }
                        html += "<th text-align:right>" + ConvertCurrency((tmp[j])) + "</th>";
                    }
                    html += "</tr>";
                }
            }

            //Generate HTML Table
            htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                    + Titles(ReportNumber, Item + "," + _ItemName, year, _budget_type, _ActiveVersion)
                    + "</th></tr><tr><th>Code</th><th>Description</th><th>Actual Revenue</br>Jan - Dec " + (year - 2) + "</th><th>Actual Revenue</br>Jan - Dec</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + year + "</th></tr></thead><tbody>";

            htmltable = htmltable + html + "</tbody></table>";

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

    public String GrantReport(Integer year, String code, String category, String Head, Integer ReportNum, String _budget_type, String _ActiveVersion) {
         //System.out.println("GrantReport   year::: "+year);
       String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(s.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(s.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (2,3) ";
        }
        String sql = "with nV as(SELECT M.administrative_segment as code, M.name as name, (select ISNULL(sum(s.AmountLc),0) from SAP_Actuals s where s.FiscYear=(" + (year - 2) + ") and s.CmmtItem like '" + code + "%' AND S.FundsCtr= M.administrative_segment) as A1, "
                + "(select ISNULL(sum(s.AmountLc),0) from SAP_Actuals s where s.FiscYear=(" + (year - 1) + ") and s.CmmtItem like '" + code + "%' AND S.FundsCtr= M.administrative_segment "+budget_type_condition_actual+") as A2, "
                + "(SELECT ISNULL(sum(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.economic_segment LIKE '" + code + "%' and y.admin_segment=m.administrative_segment "+budget_type_condition_budget+" and y.budget_year_id=(" + (year - 1) + ")) as B1, "
                + "(SELECT ISNULL(sum(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.economic_segment LIKE '" + code + "%' and y.admin_segment=m.administrative_segment "+budget_type_condition_budget+" and y.budget_year_id=(" + (year - 0) + ")) as B2 FROM MDAs M WHERE M.sub_sector_code<>'00') "
                + "SELECT n.code, n.name, n.A1, n.A2,n.B1,n.B2 from nV as n where (n.A1 <> 0 or n.A2 <> 0 or n.b1 <> 0 or n.b2 <> 0) UNION SELECT 'Total', '', SUM(n.A1), SUM(n.A2),SUM(n.B1),SUM(n.B2) from nV as n ";
        //System.out.println(sql);
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy = Titles(ReportNum, " , ", year, _budget_type, _ActiveVersion);
        String htmltable = null;
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + dummy;
        htmltable += "</tr><tr><th>Code</th><th>Name of Organization</th><th>Actual Expenditure from Subvention Jan- Dec " + (year - 2) + "</th><th>Actual Expenditure from Subvention Jan- Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th><th>Approved Estimates " + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmp1 : tmp) {
                Check4Nulls(tmp1, 2);
                htmltable += "<tr>";
                for (int i = 0; i < 6; i++) {
                    if (i > 1) {
                        if(tmp1[i]==null) {
                            tmp1[i] = 0.0;
                        }
                        htmltable += "<td  align='right'>" + ConvertCurrency((tmp1[i])) + "</td>";
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

    public String RevenuReportbyEconomicSegment(Integer year, String code, String category, String Head, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenuReportbyEconomicSegment   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (2,3) ";
        }
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy = Titles(ReportNum, " , ", year, _budget_type, _ActiveVersion);
        String htmltable = null;
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + dummy;
        htmltable += "</tr><tr><th>Code</th><th>Revenue Details</th><th>Actual Revenue Jan - Dec " + (year - 2) + "</th><th>Actual Revenue Jan - Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th><th>Approved Estimates " + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "";
            sql = "select a.code, a.name,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " from Economic_Segment_Header1 a  where a.code LIKE'" + Head + "%' UNION "
                    + " select a.code, a.name, "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " from Economic_Segment_Header3 a where a.code LIKE'" + Head + "%' "
                    + " UNION select a.code, a.name,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1,  "
                    + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b  INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3,  "
                    + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " from Economic_Segment_Header4 a where a.code LIKE'" + Head + "%'";
            System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmp1 : tmp) {
                Check4Nulls(tmp1, 2);
                htmltable += "<tr>";
                for (int i = 0; i < 6; i++) {
                    if (i > 1) {
                        if(tmp1[i]==null) {
                            tmp1[i] = 0.0;
                        }
                        htmltable += "<td  align='right'>" + ConvertCurrency((tmp1[i])) + "</td>";
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

    public String RevenuReport(Integer year, String code, String category, String Head, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenuReport   year::: "+year);
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy = Titles(ReportNum, " , ", year, _budget_type, _ActiveVersion);
        String htmltable = null;
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + dummy;
        htmltable += "</tr><tr><th>Code</th><th>Revenue Details</th><th>Actual Revenue Jan - Dec " + (year - 2) + "</th><th>Actual Revenue Jan - Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th><th>Approved Estimates " + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "";
            sql = Queries(year, category, Head, _budget_type, _ActiveVersion);//summary
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmp1 : tmp) {
                Check4Nulls(tmp1, 2);
                htmltable += "<tr>";
                for (int i = 0; i < 6; i++) {
                    if (i > 1) {
                        if(tmp1[i]==null) {
                            tmp1[i] = 0.0;
                        }
                        htmltable += "<td  align='right'>" + ConvertCurrency((tmp1[i])) + "</td>";
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

    public String VersionReport(Integer year, String[] BudgetVersions2Report, String code, String category, String Head, Integer ReportNum, String _budget_type) {
        //year=2017;
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy = Titles(ReportNum, "", year, "", _budget_type);
        String htmltable = "";
        String[] version = new String[5];
        ReportServletHelper hlp = new ReportServletHelper();
        int iCount = 0;
        for (String itm : BudgetVersions2Report) {
            version[iCount] = hlp.fetchYearBudgetVersionName(itm);
            iCount++;
        }
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + "<br> " + year + " Budget version Comparism";
        htmltable += "</tr><tr><th>Code</th><th>Budget Details</th><th>" + version[0] + " (" + CurrencySymbol + ")" + "</th><th> " + version[1] + " (" + CurrencySymbol + ")" + "</th><th> " + version[2] + " (" + CurrencySymbol + ")" + "</th><th> " + version[3] + " (" + CurrencySymbol + ")" + "</th><th> " + version[4] + " (" + CurrencySymbol + ")" + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "";
            sql = VQueries(year, category, Head, _budget_type, BudgetVersions2Report);//summary
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmp1 : tmp) {
                Check4Nulls(tmp1, 2);
                htmltable += "<tr>";
                for (int i = 0; i < 7; i++) {
                    if (i > 1) {
                        if (tmp1[i] == null) {
                            tmp1[i] = 0.0;
                        }
                        htmltable += "<td  align='right'>" + ConvertCurrency((tmp1[i])) + "</td>";
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
	
	public String GetIRbySectorReports(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("GetIRbySectorReports   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        //Get the List of viable MDAs
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion)
                + "</th></tr><tr><th>Code</th><th>Organization Name</th><th>Actual Revenue</br>Jan - Dec " + (year - 2) + "</th><th>Actual Revenue</br>Jan - Dec</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + year + "</th></tr></thead><tbody>";

        double[] smry = new double[Filt.length + 4];
        String Heading[] = Filt;

        int i = 0;
        Integer j = 0;
        String sql = "";
        String other = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //get sector codes and names
            String html = "";

            sql = "SELECT  Sectors.sector_code, Sectors.name as na, a.administrative_segment, a.name as nb,"
                    + " (select coalesce( sum(b.AmountLc),0)  from SAP_Actuals b where b.FiscYear=(" + (year - 2) + ") and b.FundsCtr = a.administrative_segment "+budget_type_condition_actual+" and b.CmmtItem like '" + Filt[i] + "%') as amt1,"
                    + " (select coalesce( sum(b.AmountLc),0) from SAP_Actuals b where b.FiscYear=(" + (year - 1) + ") and b.FundsCtr = a.administrative_segment "+budget_type_condition_actual+" and b.CmmtItem like '" + Filt[i] + "%') as amt2,"
                    + " (select coalesce( sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and b.budget_year_id=(" + (year - 1) + ") and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" and b.economic_segment like '" + Filt[i] + "%') as amt3,"
                    + " (select coalesce( sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and b.budget_year_id=(" + (year) + ") and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" and b.economic_segment like '" + Filt[i] + "%') as amt4"
                    + " FROM dbo.MDAs AS a INNER JOIN Sectors ON Sectors.sector_code = left(a.administrative_segment,2) WHERE a.sub_sector_code != '00' order BY Sectors.sector_code , a.administrative_segment, a.name ,Sectors.name";
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            Integer kcount = tmp.get(0).length;
            String dummy = "";
            for (int n = 0; n < ncount; n++) {
                html += "<tr>";
                if (!dummy.equals((String) tmp.get(n)[0])) {
                    html += "<th>" + tmp.get(n)[0] + "</th><th>" + tmp.get(n)[1] + "</th><th></th><th></th><th></th><th></th></tr><tr>";
                    for (int k = 2; k < kcount; k++) {
                        if (k > 3) {
                            if(tmp.get(n)[k]==null) {
                                tmp.get(n)[k] = 0.0;
                            }
                            html += "<td align='right'>" + ConvertCurrency((tmp.get(n)[k])) + "</td>";
                        } else {
                            html += "<td>" + tmp.get(n)[k] + "</td>";
                        }
                    }
                } else {
                    for (int k = 2; k < kcount; k++) {
                        if (k > 3) {
                            if(tmp.get(n)[k]==null) {
                                tmp.get(n)[k] = 0.0;
                            }
                            html += "<td align='right'>" + ConvertCurrency((tmp.get(n)[k])) + "</td>";
                        } else {
                            html += "<td>" + tmp.get(n)[k] + "</td>";
                        }
                    }
                }
                html += "</tr>";
                dummy = (String) tmp.get(n)[0];
            }

            htmltable = htmltable + html + "</tbody></table>";
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

    public String RevenueFirstSchedule(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenueFirstSchedule   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        //Get the List of viable MDAs
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='" + Filt.length + 3 + "'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, Filt.toString(), year, _budget_type, _ActiveVersion)
                + "</th></tr><tr><th>Code</th><th>MDA Name</th>";
        String Heading[] = Filt;
        BudgetTypeComponentsHibernateHelper btc = new BudgetTypeComponentsHibernateHelper();
        int i = 0;
        for (String str : Filt) {
            htmltable += "<th>" + btc.fetch_name(str).replace("\"", "").replace("[", "").replace("]", "") + "</th>";// Heading[i] = btc.fetch_name(str);
            i++;
        }
        htmltable += "<th>Total</th></tr></thead><tbody>";
        i = 0;
        Integer j = 0;
        String sql = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            sql = "WITH Nv as(Select a.administrative_segment as code, a.name as Name,"
                    + " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.economic_segment LIKE '21010101%' AND b.budget_year_id=" + (year) + ") as A0 ,"
                    + " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.economic_segment LIKE '2202%' AND b.budget_year_id=" + (year) + ") as A1 ,"
                    + " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.economic_segment LIKE '2210%' AND b.budget_year_id=" + (year) + ") as A2 ,"
                    + " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.budget_year_id=" + (year) + ") as Total"
                    + " FROM MDAs a where a.sub_sector_code !='00') SELECT N.code, N.name, N.A0,N.A1,N.A2, N.A0+N.A1+N.A2  from Nv N UNION select 'Total', '', Sum(N.A0),sum(N.A1),sum(N.A2),Sum(N.A0)+sum(N.A1)+sum(N.A2) from Nv N";

            System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            //Generate HTML Table
            String html = "";
            j = 0;
            for (Object[] tmp1 : tmp) {
                html += "<tr>";
                if ("Total".equals(tmp1[0])) {
                    html += "<th colspan='2'>Total</th><th style='display:none'></th>";
                    for (int k = 2; k < tmp1.length; k++) {
                        if(tmp1[k]==null) {
                            tmp1[k] = 0.0;
                        }
                        html += "<td align='right'>" + ConvertCurrency((tmp1[k])) + "</td>";
					}
                } else {
                    for (int k = 0; k < tmp1.length; k++) {
                        if (k > 1) {
                            if(tmp1[k]==null) {
                                tmp1[k] = 0.0;
                            }
                            html += "<td align='right'>" + ConvertCurrency((tmp1[k])) + "</td>";
                        } else {
                            html += "<td>" + tmp1[k] + "</td>";
                        }
                    }
                }
                html += "</tr>";
            }

            htmltable += html + "</tbody></table>";
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

    public String RevenueSecondSchedule(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenueSecondSchedule   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        //Get the List of viable MDAs
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='" + Filt.length + 4 + "'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, Filt.toString(), year, _budget_type, _ActiveVersion)
                + "</th></tr><tr><th>Code</th><th>MDA Name</th>";
        String Heading[] = Filt;
        BudgetTypeComponentsHibernateHelper btc = new BudgetTypeComponentsHibernateHelper();
        int i = 0;
        for (String str : Filt) {
            htmltable += "<th>" + btc.fetch_name(str).replace("\"", "").replace("[", "").replace("]", "") + "</th>";// Heading[i] = btc.fetch_name(str);
            i++;
        }
        htmltable += "<th>Total</th></tr></thead><tbody>";
        i = 0;
        Integer j = 0;
        String sql = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            sql = "WITH Nv as(Select a.administrative_segment as code, a.name as Name,";
            for (String str : Filt) {
                sql += " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.economic_segment LIKE '" + str + "%' AND b.budget_year_id=" + year + ") as A" + i++ + "";
            }
            //sql += "      select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment AND b.economic_segment LIKE '21010101%' AND b.budget_year_id=" + (year) + ") as A0 ,"
//                    + " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment AND b.economic_segment LIKE '2202%' AND b.budget_year_id=" + (year) + ") as A1 ,"
//                    + " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment AND b.economic_segment LIKE '2210%' AND b.budget_year_id=" + (year) + ") as A2 ,"
            // sql += " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment AND b.budget_year_id=" + (year) + ") as Total"
            sql += " FROM MDAs a where a.sub_sector_code !='00') SELECT N.code, N.name, N.A0, N.A0 as A1  from Nv N UNION select 'Total', '', Sum(N.A0),Sum(N.A0) from Nv N";
            //System.out.println("sql::" + sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            //Generate HTML Table
            String html = "";
            j = 0;
            for (Object[] tmp1 : tmp) {
                html += "<tr>";
                if ("Total".equals(tmp1[0])) {
                    html += "<th colspan='2'>Total</th><th style='display:none'></th>";
                    for (int k = 2; k < tmp1.length; k++) {
                        if(tmp1[k]==null) {
                            tmp1[k] = 0.0;
                        }
                        html += "<td align='right'>" + ConvertCurrency((tmp1[k])) + "</td>";
                    }
                } else {
                    for (int k = 0; k < tmp1.length; k++) {
                        if (k > 1) {
                            if(tmp1[k]==null) {
                                tmp1[k] = 0.0;
                            }
                            html += "<td align='right'>" + ConvertCurrency((tmp1[k])) + "</td>";
                        } else {
                            html += "<td>" + tmp1[k] + "</td>";
                        }
                    }
                }
                html += "</tr>";
            }

            htmltable += html + "</tbody></table>";
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

    public String RevenueReport(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenueReport   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        //Get the List of viable MDAs
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='" + Filt.length + 4 + "'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, Filt.toString(), year, _budget_type, _ActiveVersion)
                + "</th></tr><tr><th>Code</th><th>MDA Name</th>";//<th>Personnel</th><th>Overhead Cost</th><th>Special Programme</th><th>Total</th></tr></thead><tbody>";
        //String[] TableVals = null;

        //TableVals = new String[4 + Filt.length];
        // double smry1 = 0, smry2 = 0, smry3 = 0, smry0 = 0;
        double[] smry = new double[Filt.length + 4];
        String Heading[] = Filt;
        BudgetTypeComponentsHibernateHelper btc = new BudgetTypeComponentsHibernateHelper();
        int i = 0;
        for (String str : Filt) {
            htmltable += "<th>" + btc.fetch_name(str).replace("\"", "").replace("[", "").replace("]", "") + "</th>";// Heading[i] = btc.fetch_name(str);
            i++;
        }
        htmltable += "<th>Others</th><th>Total</th></tr></thead><tbody>";
        i = 0;
        Integer j = 0;
        String sql = "";
        String other = "";
        ///NOT like '21010101%' AND b.economic_segment NOT like '2202%' and b.economic_segment NOT like '2210%' and b.economic_segment NOT like '14%'
        for (String str : Filt) {
            other += " AND b.economic_segment NOT like '" + str + "%' ";
        }

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            sql = "Select a.administrative_segment, a.name, ";
            for (String str : Filt) {
                sql += " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.economic_segment LIKE '" + str + "%' AND b.budget_year_id=" + year + ") as A" + i++ + " ,";
            }
            sql += " (SELECT COALESCE(SUM(b.budget_amount),0) FROM Year_Budget b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment=a.administrative_segment XXXXXXXX "+budget_type_condition_budget+"  AND b.budget_year_id=" + year + ") as A" + i++ + " ,";
            sql += " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.budget_year_id=" + year + ") as Total";
            //sql = sql.substring(0, sql.length() - 1);
            sql += " FROM MDAs a where a.sub_sector_code !='00' ORDER BY a.administrative_segment";
            sql = sql.replaceAll("XXXXXXXX", other);
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            //Generate HTML Table
            String html = "";
            j = 0;
            for (Object[] tmp1 : tmp) {
//                TableVals[0] = (String) tmp1[0];            //Admin Code
//                TableVals[1] = (String) tmp1[1];            //MDA Name
//                for (int k = 2; k < TableVals.length; k++) {
//                    smry[k] += (double) tmp1[k];
//                    TableVals[k] = ConvertCurrency( tmp1[k]);
//                }
                html += "<tr>";
                for (int k = 0; k < tmp1.length; k++) {
                    if (k > 1) {
                        if(tmp1[k]==null) {
                            tmp1[k] = 0.0;
                        }
                        smry[k] += (double) tmp1[k];
                        html += "<td align='right'>" + ConvertCurrency((tmp1[k])) + "</td>";
                    } else {
                        html += "<td>" + tmp1[k] + "</td>";
                    }
                }
                html += "</tr>";
            }
            //if (Check4Nulls(TableVals) == false) {

            //}
            htmltable = htmltable + html + "<tr><th colspan='2'>Total</th><th style='display:none'></th>";
            for (int k = 2; k < smry.length; k++) {
                //if(smry[k]==null) smry[k]=0.0;
                htmltable += "<th style='text-align:right'>" + ConvertCurrency((smry[k])) + "</th>";
            }
            htmltable += "</tr></tbody></table>";
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

    public String RevenueReport0(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenueReport0   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(B.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(B.economic_segment,1) in (2,3) ";
        }
        //Get the List of viable MDAs
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='" + Filt.length + 3 + "'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, Filt.toString(), year, _budget_type, _ActiveVersion)
                + "</th></tr><tr><th>Code</th><th>MDA Name</th>";

        double[] smry = new double[Filt.length + 3];
        String Heading[] = Filt;
        BudgetTypeComponentsHibernateHelper btc = new BudgetTypeComponentsHibernateHelper();
        int i = 0;
        for (String str : Filt) {
            htmltable += "<th>" + btc.fetch_name(str).replace("\"", "").replace("[", "").replace("]", "") + "</th>";
            i++;
        }
        htmltable += "<th>Total</th></tr></thead><tbody>";
        i = 0;
        Integer j = 0;
        String sql = "";
        String other = "";
        for (String str : Filt) {
            other += " AND b.economic_segment NOT like '" + str + "%' ";
        }

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            i = 3;
            String dummy = " (Select isnull(sum(B.budget_amount),0) from Year_Budget B where B.admin_segment= m.administrative_segment "+ budget_type_condition_budget+" and b.budget_version_id='"+_ActiveVersion+"' and B.budget_year_id=(" + year + ") and B.economic_segment like '";
            sql = "with nV as (SELECT M.administrative_segment as code , M.name as name,";
            for (String str : Filt) {
                sql += dummy + str + "%') as C" + i++ + " ,";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += " from MDAs M where M.sub_sector_code<>'00' )SELECT x.code, x.name, ";
            i = 3;
            for (String str : Filt) {
                sql += "x.C" + i++ + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            i = 3;
            sql += ",x.c3+x.c4+x.c5+x.c6+x.c7+x.c8+x.c9 FROM nV AS x UNION SELECT 'Sub Total', '', ";
            for (String str : Filt) {
                sql += "SUM(xx.C" + i++ + "),";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ",Sum(xx.c3)+Sum(xx.c4)+Sum(xx.c5) +Sum(xx.c6)+Sum(xx.c7)+Sum(xx.c8)+Sum(xx.c9) FROM nV AS xx";
//            System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            //Generate HTML Table
            String html = "";
            j = 0;
            for (Object[] tmp1 : tmp) {
                html += "<tr>";
                for (int k = 0; k < tmp1.length; k++) {

                    if (k > 1) {
                        if(tmp1[k]==null) {
                            tmp1[k] = 0.0;
                        }
                        smry[k] += (double) tmp1[k];
                        html += "<td align='right'>" + ConvertCurrency((tmp1[k])) + "</td>";
                    } else {
                        html += "<td>" + tmp1[k] + "</td>";
                    }
                }
                html += "</tr>";
            }

            htmltable = htmltable + html;
//            for (int k = 2; k < smry.length; k++) {
//                htmltable += "<th align='right'>" + ConvertCurrency( smry[k]) + "</th>";
//            }
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

    public String RevenueReport1(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenueReport1   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        //Get the List of viable MDAs
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='" + Filt.length + 4 + "'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, Filt.toString(), year, _budget_type, _ActiveVersion)
                + "</th></tr><tr><th>Sn</th><th>Code</th><th>MDA Name</th>";
        //String[] TableVals = null;

        //TableVals = new String[4 + Filt.length];
        // double smry1 = 0, smry2 = 0, smry3 = 0, smry0 = 0;
        String tmp0 = "";
        for (String flt : Filt) {
            tmp0 += "economic_segment like '" + flt + "%' or ";
        }
        tmp0 = tmp0.substring(0, tmp0.length() - 3);

        double[] smry = new double[Filt.length + 4];
        String Heading[] = Filt;
        BudgetTypeComponentsHibernateHelper btc = new BudgetTypeComponentsHibernateHelper();
        int i = 0;
        for (String str : Filt) {
            htmltable += "<th>" + btc.fetch_name(str).replace("\"", "").replace("[", "").replace("]", "") + "</th>";// Heading[i] = btc.fetch_name(str);
            i++;
        }
        htmltable += "<th>Total</th></tr></thead><tbody>";
        i = 0;
        Integer j = 0;
        String sql = "";
        String other = "";
        ///NOT like '21010101%' AND b.economic_segment NOT like '2202%' and b.economic_segment NOT like '2210%' and b.economic_segment NOT like '14%'}

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            sql = "Select ROW_NUMBER() OVER (ORDER BY a.administrative_segment) AS sn,a.administrative_segment, a.name, ";
            for (String str : Filt) {
                sql += " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND b.economic_segment LIKE '" + str + "%' AND b.budget_year_id=" + year + ") as A" + i++ + " ,";
            }
            sql += " (select COALESCE(sum(b.budget_amount),0) from Year_Budget as b WHERE b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment = a.administrative_segment "+budget_type_condition_budget+" AND (" + tmp0 + ") AND b.budget_year_id=" + year + ") as Total";
            sql += " FROM MDAs a where a.sub_sector_code !='00' ORDER BY a.administrative_segment";
            List<Object[]> tmp = session.createSQLQuery(sql).list();

            //Generate HTML Table
            String html = "";
            j = 0;
            for (Object[] tmp1 : tmp) {
                html += "<tr>";
                for (int k = 0; k < tmp1.length; k++) {
                    if (k > 2) {
                        if(tmp1[k]==null) {
                            tmp1[k] = 0.0;
                        }
                        smry[k] += (double) tmp1[k];
                        html += "<td align='right'>" + ConvertCurrency((tmp1[k])) + "</td>";
                    } else {
                        html += "<td>" + tmp1[k] + "</td>==";
                    }
                }
                html += "</tr>";
            }
            //if (Check4Nulls(TableVals) == false) {

            //}
            htmltable = htmltable + html + "<tr><th></th><th></th><th>Total</th>";
            for (int k = 3; k < smry.length; k++) {
                htmltable += "<th align='right'>" + ConvertCurrency((smry[k])) + "</th>";
            }
            htmltable += "</tr></tbody></table>";
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

    public String RevenueEstimatesReport(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenueEstimatesReport   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        String tmp0 = "and (";
        String tmp1 = "and (";
        //and (CmmtItem like '12%' or CmmtItem like '13%' or CmmtItem like '14%' or CmmtItem like '15%')
        for (String flt : Filt) {
            tmp0 += "CmmtItem like '" + flt + "%' or ";
        }
        tmp0 = tmp0.substring(0, tmp0.length() - 3);
        tmp0 += ")";
        for (String flt : Filt) {
            tmp1 += "economic_segment like '" + flt + "%' or ";
        }
        tmp1 = tmp1.substring(0, tmp1.length() - 3);
        tmp1 += ")";
        String sql = "select administrative_Segment as admin_segment,'' as code,name, 0 as amt1, 0 as amt2, 0 as amt3, 0 as amt4  from mdas"
                + " where sub_sector_code <> '00'"
                + " union"
                + " SELECT a.admin_segment, a.economic_segment as code,"
                + " (select name from Economic_Segment where code=a.economic_segment) as name,"
                + " (select ISNULL(sum(AmountLc),0) from SAP_Actuals where FiscYear=(" + (year - 2) + ") " + tmp0 + " and CmmtItem =a.economic_segment and FundsCtr=a.admin_segment "+budget_type_condition_actual+") as A1,"
                + " (select ISNULL(sum(AmountLc),0) from SAP_Actuals where FiscYear=(" + (year - 1) + ") " + tmp0 + " and CmmtItem=a.economic_segment and FundsCtr=a.admin_segment "+budget_type_condition_actual+") as A2, "
                + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment " + tmp1 + " and economic_segment=a.economic_segment "+budget_type_condition_budget+" and b.budget_year_id=(" + (year - 1) + ")) as B1,"
                + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment " + tmp1 + " and economic_segment=a.economic_segment "+budget_type_condition_budget+" and b.budget_year_id=(" + (year) + ")) as B2"
                + " FROM Year_Budget a INNER JOIN MDAs M on M.administrative_segment = a.admin_segment WHERE " + tmp1.substring(3, tmp1.length())
                + " and a.budget_version_id='"+_ActiveVersion+"' and M.sub_sector_code<>'00' group by a.admin_segment,a.programme_segment,a.economic_segment,a.functional_segment,a.fund_segment,a.geo_segment,a.dept_id, a.percent_complete, a.complete_from, a.complete_to,a.id"
                + " order by admin_segment";
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion)
                + "</th></tr><trbgcolor='#d9d9d9'><th>Code</th><th>Description</th><th>Actual Revenue</br>Jan - Dec " + (year - 2) + "</th><th>Actual Revenue</br>Jan - Dec</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + year + "</th></tr></thead><tbody>";
//        System.out.println(sql);
        double[] smry = new double[Filt.length + 4];
        String Heading[] = Filt;

        int i = 0;
        Integer j = 0;
        String other = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        smry[0] = 0;
        smry[1] = 0;
        smry[2] = 0;
        smry[3] = 0;

        try {
            tx = session.beginTransaction();
            //get sector codes and names
            String html = "";

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            Integer kcount = tmp.get(0).length;
            String dummy = "";
            for (int n = 0; n < ncount; n++) {
//                html += "<tr>";
                if ("".equals((String) tmp.get(n)[1]) || tmp.get(n)[1] == null) {
                    //New MDA
                    if (!"".equals(html)) {
                        html += "<tr bgcolor='#e6e6e6'><th colspan='2'>Total</th><th style='display:none'></th><th style='text-align:right'>" + ConvertCurrency((smry[0])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[1])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[2])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[3])) + "</th></tr><tr style='height:40px;'><td  colspan='6' ></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
                        for (int k = 0; k < 4; k++) {
                            smry[k] = 0;
                        }
                    }

                    html += "<tr bgcolor='#f2f2f2'><th>" + tmp.get(n)[0] + "</th><th colspan='5'>" + tmp.get(n)[2] + "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
                } else {
                    html += "<tr><td>" + tmp.get(n)[1] + "</td><td>" + tmp.get(n)[2] + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[3])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[4])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[5])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[6])) + "</td></tr>";
                    for (int k = 0; k < 4; k++) {
                        smry[k] += (Double) tmp.get(n)[3 + k];
                    }
                }
            }

            htmltable = htmltable + html + "</tbody></table>";
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

    public String RevenueEstimatesReport0(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("RevenueEstimatesReport0   year::: "+year);
        BudgetTypeComponentsHibernateHelper btc = new BudgetTypeComponentsHibernateHelper();
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        String CategoryStr = "";
        if (ReportNum == 12 || ReportNum == 15) {
            CategoryStr = btc.fetch_name(Filt[0]).replace("\"", "").replace("[", "").replace("]", "");
        }
        String tmp0 = "and (";
        String tmp1 = "and (";
        //and (CmmtItem like '12%' or CmmtItem like '13%' or CmmtItem like '14%' or CmmtItem like '15%')
        for (String flt : Filt) {
            tmp0 += "CmmtItem like '" + flt + "%' or ";
        }
        tmp0 = tmp0.substring(0, tmp0.length() - 3);
        tmp0 += ")";
        for (String flt : Filt) {
            tmp1 += "economic_segment like '" + flt + "%' or ";
        }
        tmp1 = tmp1.substring(0, tmp1.length() - 3);
        tmp1 += ")";
        String sql = "select administrative_Segment as admin_segment,'' as code,name, 0 as amt1, 0 as amt2, 0 as amt3, 0 as amt4  from mdas"
                + " where sub_sector_code <> '00'"
                + " union"
                + " SELECT a.admin_segment, a.economic_segment as code,"
                + " (select name from Economic_Segment where code=a.economic_segment) as name,"
                + " (select ISNULL(sum(AmountLc),0) from SAP_Actuals where FiscYear=(" + (year - 2) + ") " + tmp0 + " and CmmtItem =a.economic_segment and FundsCtr=a.admin_segment "+budget_type_condition_actual+") as A1,"
                + " (select ISNULL(sum(AmountLc),0) from SAP_Actuals where FiscYear=(" + (year - 1) + ") " + tmp0 + " and CmmtItem=a.economic_segment and FundsCtr=a.admin_segment "+budget_type_condition_actual+") as A2, "
                + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment " + tmp1 + " and economic_segment=a.economic_segment "+budget_type_condition_budget+" and b.budget_year_id=(" + (year - 1) + ")) as B1,"
                + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment " + tmp1 + " and economic_segment=a.economic_segment "+budget_type_condition_budget+" and b.budget_year_id=(" + (year) + ")) as B2"
                + " FROM Year_Budget a WHERE a.budget_version_id='"+_ActiveVersion+"' and " + tmp1.substring(3, tmp1.length())
                + " group by a.admin_segment,a.programme_segment,a.economic_segment,a.functional_segment,a.fund_segment,a.geo_segment,a.dept_id, a.percent_complete, a.complete_from, a.complete_to,a.id"
                + " order by admin_segment";
        //System.out.println(sql);
        String TableTitle = "";
        String TableContent = "";
        String TableFooter = "";
        String dummy = "";
        String h1 = "<table class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + " "
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion);
        String TableHeader = "</th></tr><trbgcolor='#d9d9d9'><th>Code</th><th>Description</th><th>Actual Revenue</br>Jan - Dec " + (year - 2) + "</th><th>Actual Revenue</br>Jan - Dec</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + year + "</th></tr></thead><tbody>";
        String htmltable = "";
        double[] smry = new double[Filt.length + 4];
        String Heading[] = Filt;

        int i = 0;
        Integer j = 0;
        String other = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        smry[0] = 0;
        smry[1] = 0;
        smry[2] = 0;
        smry[3] = 0;

        try {
            tx = session.beginTransaction();
            //get sector codes and names
            String html = "";

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            Integer kcount = tmp.get(0).length;

            for (int n = 0; n < ncount; n++) {
//                html += "<tr>";
                if ("".equals((String) tmp.get(n)[1]) || tmp.get(n)[1] == null) {
                    //New MDA
//                    if (!"".equals(dummy)) {
//                        TableFooter = "<tr bgcolor='#e6e6e6'><th colspan='2'>Total</th><th style='text-align:right'>" + ConvertCurrency( smry[0]) + "</th><th style='text-align:right'>" + ConvertCurrency( smry[1]) + "</th><th style='text-align:right'>" + ConvertCurrency( smry[2]) + "</th><th style='text-align:right'>" + ConvertCurrency( smry[3]) + "</th></tr><tr style='height:40px;'></tr>";
//                        for (int k = 0; k < 4; k++) {
//                            smry[k] = 0;
//                        }
//                    }
                    if (!"".equals(TableContent)) {
                        dummy = "" + tmp.get(n)[0] + " - " + tmp.get(n)[2];
                        TableTitle = h1 + "<br>" + dummy + "<br>" + CategoryStr;
                        TableFooter = "<tr bgcolor='#e6e6e6'><th colspan='2'>Total</th><th style='text-align:right'>" + ConvertCurrency((smry[0])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[1])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[2])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[3])) + "</th></tr><tr style='height:40px;'></tr>";
                        for (int k = 0; k < 4; k++) {
                            smry[k] = 0;
                        }

                        htmltable += TableTitle + TableHeader + TableContent + TableFooter + "<br><br></tbody></table>";
                    }
                    TableContent = "";

                } else {
                    TableContent += "<tr><td>" + tmp.get(n)[1] + "</td><td>" + tmp.get(n)[2] + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[3])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[4])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[5])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[6])) + "</td></tr>";
                    for (int k = 0; k < 4; k++) {
                        smry[k] += (Double) tmp.get(n)[3 + k];
                    }
                }
            }
//            System.out.println(htmltable);
            htmltable = "<table id='reptable'><thead><tr><td></td></tr></thead><tbody><tr><td>" + htmltable + "</td></tr></tbody></table>";
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

    public String CRFReport(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("CRFReport   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(s.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(s.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (2,3) ";
        }
        String tmp0 = "";
        //and (CmmtItem like '12%' or CmmtItem like '13%' or CmmtItem like '14%' or CmmtItem like '15%')
        for (String flt : Filt) {
            tmp0 += " e.code LIKE '" + flt + "%' or";
        }
        tmp0 = tmp0.substring(0, tmp0.length() - 3);
        String sql = "Select ROW_NUMBER() OVER (ORDER BY e.code) AS sn,e.code, e.name,"
                + " (select ISNULL(sum(s.AmountLc),0) from SAP_Actuals s INNER JOIN MDAs M on M.administrative_segment= s.FUNDSCTR where s.FiscYear=(" + (year - 2) + ") and s.CmmtItem = e.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as A1,"
                + " (select ISNULL(sum(s.AmountLc),0) from SAP_Actuals s INNER JOIN MDAs M on M.administrative_segment= s.FUNDSCTR where s.FiscYear=(" + (year - 1) + ") and s.CmmtItem = e.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as A2,"
                + " (SELECT ISNULL(sum(Y.budget_amount),0) from Year_Budget Y INNER JOIN MDAs M on M.administrative_segment= Y.admin_segment where Y.budget_version_id='"+_ActiveVersion+"' and Y.economic_segment = e.code and y.budget_year_id=(" + (year - 1) + ") and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as B1,"
                + " (SELECT ISNULL(sum(Y.budget_amount),0) from Year_Budget Y INNER JOIN MDAs M on M.administrative_segment= Y.admin_segment where Y.budget_version_id='"+_ActiveVersion+"' and Y.economic_segment = e.code and y.budget_year_id=(" + (year) + ") and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as B2"
                + " from Economic_Segment e where" + tmp0;
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='7'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion)
                + "</th></tr><tr bgcolor='#f2f2f2'><th>Sn</th><th>Code</th><th>Description</th><th>Actual Revenue</br>Jan - Dec " + (year - 2) + "</th><th>Actual Revenue</br>Jan - Dec</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + year + "</th></tr></thead><tbody>";
       //System.out.println(sql);
        double[] smry = new double[Filt.length + 4];
        String Heading[] = Filt;

        int i = 0;
        Integer j = 0;
        String other = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        smry[0] = 0;
        smry[1] = 0;
        smry[2] = 0;
        smry[3] = 0;

        try {
            tx = session.beginTransaction();
            //get sector codes and names
            String html = "";

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            Integer kcount = tmp.get(0).length;
            for (int n = 0; n < ncount; n++) {
                html += "<tr><td>" + tmp.get(n)[0] + "</td><td>" + tmp.get(n)[1] + "</td><td>" + tmp.get(n)[2] + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[3])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[4])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[5])) + "</td><td align='right'>" + ConvertCurrency((tmp.get(n)[6])) + "</td></tr>";
                for (int k = 0; k < 4; k++) {
                    smry[k] += (Double) tmp.get(n)[3 + k];
                }

            }
            html += "<tr bgcolor='#f2f2f2'><th style='border-right: 0px'></th><th style='border-right:0px;border-left:0px'></th><th style='text-align:right; border-left:0px;'>Total</th><th style='text-align:right'>" + ConvertCurrency((smry[0])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[1])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[2])) + "</th><th style='text-align:right'>" + ConvertCurrency((smry[3])) + "</th></tr>";
            htmltable = htmltable + html + "</tbody></table>";
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

    public String SectoralSummary(Integer year, String[] Filt, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("SectoralSummary   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(s.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(s.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (2,3) ";
        }
        String sql = "with nV as ("
                + " SELECT DISTINCT S.name as Sector, '' as subsector, '' As A1,'' as A2,'' as A3,'' as A4  FROM Sectors S"
                + " UNION"
                + " SELECT  S.name as Sector, Sub.name as subsector,"
                + " (SELECT isnull(Sum(Y.budget_amount),0) FROM Year_Budget Y INNER JOIN MDAs M ON M.administrative_segment = Y.admin_segment  WHERE Y.budget_version_id='"+_ActiveVersion+"' and M.sub_sector_code = Sub.sub_sector_code "+budget_type_condition_budget+" and y.budget_year_id= (" + (year - 3) + "))AS A1,"
                + " (SELECT isnull(Sum(Y.budget_amount),0) FROM Year_Budget Y INNER JOIN MDAs M ON M.administrative_segment = Y.admin_segment  WHERE Y.budget_version_id='"+_ActiveVersion+"' and M.sub_sector_code = Sub.sub_sector_code "+budget_type_condition_budget+" and y.budget_year_id= (" + (year - 2) + ")) as A2,"
                + " (SELECT isnull(Sum(Y.budget_amount),0) FROM Year_Budget Y INNER JOIN MDAs M ON M.administrative_segment = Y.admin_segment  WHERE Y.budget_version_id='"+_ActiveVersion+"' and M.sub_sector_code = Sub.sub_sector_code "+budget_type_condition_budget+" and y.budget_year_id= (" + (year - 1) + ")) as A3,"
                + " (SELECT isnull(Sum(Y.budget_amount),0) FROM Year_Budget Y INNER JOIN MDAs M ON M.administrative_segment = Y.admin_segment  WHERE Y.budget_version_id='"+_ActiveVersion+"' and M.sub_sector_code = Sub.sub_sector_code "+budget_type_condition_budget+" and y.budget_year_id= (" + (year - 0) + ")) as A4"
                + " FROM MDAs M INNER JOIN Sub_Sectors Sub ON Sub.sub_sector_code = M.sub_sector_code ,Sectors S WHERE left(M.administrative_segment,2) = S.sector_code and M.administrative_segment<>'00')"
                + " SELECT a.Sector, a.subsector,"
                + " a.A1,isnull(a.A1/(SELECT NULLIF(SUM(A1),0) from nV)*100 ,0) p1,"
                + " a.A2,isnull(a.A2/(SELECT NULLIF(SUM(A2),0) from nV)*100 ,0) p2,"
                + " a.A3,isnull(a.A3/(SELECT NULLIF(SUM(A3),0) from nV) *100 ,0) p3,"
                + " a.A4,isnull(a.A4/(SELECT NULLIF(SUM(A4),0) from nV) *100 ,0) p4"
                + " from nV a";
//        System.out.println(sql);
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='10'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion)
                + "</th></tr><trbgcolor='#d9d9d9'><th>Sn</th><th>Sector</th><th>Approved Estimates</br>" + (year - 3) + "</th><th> % of Total Estimates</br>" + (year - 3) + "</th><th>Approved Estimates</br>" + (year - 2) + "</th><th> % of Total Estimates</br>" + (year - 2) + "</th><th>Approved Estimates</br>" + (year - 1) + "</th><th> % of Total Estimates</br>" + (year - 1) + "</th><th>Approved Estimates</br>" + year + "</th><th> % of Total Estimates</br>" + (year) + "</th></tr></thead><tbody>";

        //       double[] smry = new double[4];
        String Heading[] = Filt;

        int i = 0;
        Integer j = 0;
        String other = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        double[] smry = new double[10];

        try {
            tx = session.beginTransaction();
            String html = "";

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            boolean first = true;
            for (int k = 0; k < ncount; k++) {
                if ("".equals((String) tmp.get(k)[1])) {
                    //New Sector

                    if (first == false) {
                        html += "<tr>";
                        for (int l = 1; l < 10; l++) {
                            if (l < 2) {
                                html += "<th colspan='2'> Sub Total </th><th style='display:none'></th>";
                            } else {
                                html += "<th style='text-align:right;'>" + ConvertCurrency((smry[l])) + "</th>";

                            }
                            //smry[l] = 0.0;
                        }
                        html += "</tr>";
                    }

                    for (int l = 0; l < 7; l++) {
                        smry[l] = 0.0;
                    }
                    html += "<tr><th colspan='10'>" + tmp.get(k)[0] + "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
                } else {
                    html += "<tr><td>" + i++ + "</td>";
                    for (int l = 1; l < 10; l++) {
                        if (l < 2) {
                            html += "<td>" + tmp.get(k)[l] + "</td>";
                        } else {
                            if(tmp.get(k)[l]==null) {
                                tmp.get(k)[l] = 0.0;
                            }
                            html += "<td style='text-align:right;'>" + ConvertCurrency((tmp.get(k)[l])) + "</td>";
                            smry[l] += Double.parseDouble(tmp.get(k)[l].toString());
                        }
                        //smry[l] = 0.0;
                    }
                    html += "</tr>";
                    first = false;
                }
            }

            html += "<tr>";
            for (int l = 1; l < 10; l++) {
                if (l < 2) {
                    html += "<th colspan='2'> Sub Total </th><th style='display:none'></th>";
                } else {
                    html += "<th style='text-align:right;'>" + ConvertCurrency((smry[l])) + "</th>";
                }
            }
            html += "</tr>";

            htmltable = htmltable + html + "</tbody></table>";
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

    protected String SegmentReport(Integer year, String code, String category, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("SegmentReport   year::: "+year);
        String[] consts = code.split("\\,");
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy;
        if (ReportNum == 23 || ReportNum == 33) {
            dummy = Titles(ReportNum, "", year, _budget_type, _ActiveVersion);
        } else {
            dummy = Titles(ReportNum, category + " - " + consts[1], year, _budget_type, _ActiveVersion);
        }
        String htmltable = null;
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + dummy;
        htmltable += "</tr><tr><th>Code</th><th>Revenue Details</th><th>Actual Revenue Jan - Dec " + (year - 2) + "</th><th>Actual Revenue Jan - Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th><th>Approved Estimates " + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "";

            sql = Queries(year, category, consts[0], _budget_type, _ActiveVersion);
//            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmp1 : tmp) {
                Check4Nulls(tmp1, 2);

                if (tmp1[0].toString().length() == 2) {
                    htmltable += "<tr style='background-color: #f8f8f8; '>";
                    for (int i = 0; i < 6; i++) {
                        if (i > 1) {
                            if(tmp1[i]==null) {
                                tmp1[i] = 0.0;
                            }
                            htmltable += "<th  style=\"text-align:right\">" + ConvertCurrency((tmp1[i])) + "</th>";
                        } else {
                            htmltable += "<th >" + tmp1[i] + "</th>";
                        }
                    }
                } else {
                    htmltable += "<tr>";
                    for (int i = 0; i < 6; i++) {
                        if (i > 1) {
                            if(tmp1[i]==null) {
                                tmp1[i] = 0.0;
                            }
                            htmltable += "<td  style=\"text-align:right\">" + ConvertCurrency((tmp1[i])) + "</td>";
                        } else {
                            htmltable += "<td >" + tmp1[i] + "</td>";
                        }
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

    protected String PersonnelReport(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("PersonnelReport   year::: "+year);
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy;

        dummy = Titles(ReportNum, "", year, _budget_type, _ActiveVersion);

        String htmltable = "";
        String thead = "";
        htmltable = "<table id='reptable' class='table table-bordered'><thead style='background-color: #f8f8f8; display:none'><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + dummy;
        htmltable += "<tr><th colspan='3'>Establishment</th><th>Details of Expenditure</th><th>Approved Estimate</th><th>Approved Estimate</th></tr>"
                + "<tr><th></th><th></th><th></th><th></th><th></th><th></th>"
                + "</thead><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int j = 0;
        try {
            tx = session.beginTransaction();
            String sql = "";

            sql = "with nV as( SELECT M.administrative_segment code ,M.name name , P.HC_CURRENT hc0, P.HC_NEXT hc1, P.POS_JOB_NAME details, P.SAL_GRADE GL, P.AMT_CURRENT est0, P.AMT_NEXT est1 from SAP_Personnel P INNER JOIN MDAs M on M.administrative_segment = P.Admin_Segment WHERE P.FISCAL_YEAR=" + year + ") Select n.code as code, n.name as mda, ROW_NUMBER() OVER (ORDER BY n.code) AS sn, ISNULL(n.hc0,0) as hc0, ISNULL(n.hc1,0) as hc1, concat(n.details, ' (' , n.GL,')') as Details, ISNULL(n.est0,0) as est0, ISNULL(est1,0) as est1 from nV as n UNION Select n.code as code,'Total' ,0 as sn, ISNULL(SUM(n.hc0),0) as hc0 , ISNULL(SUM(n.hc1),0) as hc1, '' as Details, ISNULL(SUM(n.est0),0) as est0, ISNULL(SUM(est1),0) as est1 from nV as n GROUP BY n.code ";
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();

            for (Object[] tmp1 : tmp) {
                if ("Total".equals(tmp1[1])) {
                    htmltable += "<tr style='background-color: #f8f8f8; '>";
                    htmltable += "<th>Total</th>";
                    for (int i = 3; i < 8; i++) {
                        if (i < 6) {
                            htmltable += "<th>" + tmp1[i] + "</th>";
                        } else {
                            if(tmp1[i]==null) {
                                tmp1[i] = 0.0;
                            }
                            htmltable += "<th  style=\"text-align:right\">" + ConvertCurrency((tmp1[i])) + "</th>";
                        }
                    }
                    htmltable += "</tr><tr class=\"page-break\"><td colspan='6'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
                    thead = "";
                    j = 1;
                } else {
                    if ("".equals(thead)) {
                        thead = "[" + tmp1[0] + "]\t" + tmp1[1];
                        htmltable += "<tr style='background-color: #f8f8f8; '><th class='text-center' colspan='6'>" + Heading + dummy + "<br>" + thead + "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
                        htmltable += "<tr><th colspan='3'>Establishment</th><th style='display:none'></th><th style='display:none'></th><th>Details of Expenditure</th><th>Approved Estimate</th><th>Approved Estimate</th></tr>"
                                + "<tr><th>Sn</th><th>" + (year - 1) + " (" + CurrencySymbol + ")" + "</th><th>" + (year - 0) + " (" + CurrencySymbol + ")" + "</th><th></th><th>" + (year - 1) + "</th><th>" + (year - 0) + " (" + CurrencySymbol + ")" + "</th></tr>";
                    }
                    htmltable += "<tr>";
                    htmltable += "<th>" + j++ + "</th>";
                    for (int i = 3; i < 8; i++) {

                        if (i < 6) {
                            htmltable += "<td>" + tmp1[i] + "</td>";
                        } else {
                            if(tmp1[i]==null) {
                                tmp1[i] = 0.0;
                            }
                            htmltable += "<td style=\"text-align:right\">" + ConvertCurrency((tmp1[i])) + "</td>";
                        }

                    }
                    htmltable += "</tr>";
                }
                //htmltable += "</tr>";
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

    protected String PersonnelReport1(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("PersonnelReport1   year::: "+year);
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy;

        dummy = Titles(ReportNum, "", year, _budget_type, _ActiveVersion);

        String htmltable = "";
        String thead = "";
        //display:none
        htmltable = "<table id='reptable' class='table table-bordered'><thead style='display:none;background-color: #f8f8f8;'><tr><th id='reptitle' class='text-center' colspan='5'>" + Heading + dummy;
        htmltable += "<tr style='white-space:nowrap'><th>Grade Level</th><th># of Staffs</th><th>" + year + " (" + CurrencySymbol + ")" + " Approved<br>Estimates</th><th># of Staffs</th><th>" + year + " (" + CurrencySymbol + ")" + " Approved<br>Estimates</th></tr>"
                + "</thead><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int j = 0;
        try {
            tx = session.beginTransaction();
            String sql = "";

            sql = "SELECT K.ADMIN_SEGMENT a, M.name b, K.SAL_GRADE c, Sum(K.HC_CURRENT) d, Sum(K.AMT_CURRENT) e, Sum(K.HC_NEXT) f, Sum(K.AMT_NEXT)"
                    + " g FROM dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS M ON M.administrative_segment = K.ADMIN_SEGMENT WHERE "
                    + "left(K.SAL_GRADE,3) <> 'Con' and K.FISCAL_YEAR=" + year + " GROUP BY K.ADMIN_SEGMENT,Left(k.SAL_GRADE,3), M.name, "
                    + "K.SAL_GRADE UNION SELECT K.ADMIN_SEGMENT a, 'zz' b, K.POS_JOB_NAME c, Sum(K.HC_CURRENT) d, Sum(K.AMT_CURRENT) e, "
                    + "Sum(K.HC_NEXT) f, Sum(K.AMT_NEXT) g FROM dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS M ON M.administrative_segment = "
                    + "K.ADMIN_SEGMENT WHERE left(K.SAL_GRADE,3) = 'Con' and K.FISCAL_YEAR=" + year + " GROUP BY K.ADMIN_SEGMENT, M.name, "
                    + "K.POS_JOB_NAME UNION SELECT K.ADMIN_SEGMENT a, 'z' b, 'Total for GL 01 - 17', Sum(K.HC_CURRENT) c, Sum(K.AMT_CURRENT) d, "
                    + "Sum(K.HC_NEXT) f, Sum(K.AMT_NEXT) g FROM dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS M ON M.administrative_segment = "
                    + "K.ADMIN_SEGMENT where K.FISCAL_YEAR=" + year + " and left(K.SAL_GRADE,3) <> 'Con' GROUP BY K.ADMIN_SEGMENT UNION SELECT "
                    + "K.ADMIN_SEGMENT a, 'zzz0' b, 'Personnel Allowances' c, '' d,'' e,'' f, '' g FROM dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs"
                    + " AS M ON M.administrative_segment = K.ADMIN_SEGMENT where K.FISCAL_YEAR=" + year + " GROUP BY K.ADMIN_SEGMENT UNION "
                    + "SELECT K.ADMIN_SEGMENT a, 'zzz1' b, 'Transport' c, '' d,Sum(K.TRANSPORT_CURRENT) e,'' f, Sum(K.TRANSPORT_NEXT) g FROM "
                    + "dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS M ON M.administrative_segment = K.ADMIN_SEGMENT where K.FISCAL_YEAR="
                    + year + " GROUP BY K.ADMIN_SEGMENT UNION SELECT K.ADMIN_SEGMENT a, 'zzz2' b, 'Rent' c, '' d,Sum(K.RENT_CURRENT) e,'' f, "
                    + "Sum(K.RENT_NEXT) g FROM dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS M ON M.administrative_segment = K.ADMIN_SEGMENT "
                    + "where K.FISCAL_YEAR=" + year + " GROUP BY K.ADMIN_SEGMENT UNION SELECT K.ADMIN_SEGMENT a, 'zzz3' b, 'Leave Bonus' c, '' "
                    + "d,Sum(K.BONUS_CURRENT) e,'' f, Sum(K.BONUS_NEXT) g FROM dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS M ON "
                    + "M.administrative_segment = K.ADMIN_SEGMENT where K.FISCAL_YEAR=" + year + " GROUP BY K.ADMIN_SEGMENT UNION "
                    + "SELECT K.ADMIN_SEGMENT a, 'zzz4' b, 'Others' c, '' d,Sum(K.OTHERS_CURRENT) e,'' f, Sum(K.OTHERS_NEXT) g FROM "
                    + "dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS M ON M.administrative_segment = K.ADMIN_SEGMENT where K.FISCAL_YEAR="
                    + +year + " GROUP BY K.ADMIN_SEGMENT UNION SELECT K.ADMIN_SEGMENT a, 'zzz5' b, 'Total Allowance' c, '' d, "
                    + "Sum(K.TRANSPORT_CURRENT)+Sum(K.RENT_CURRENT)+Sum(K.BONUS_CURRENT)+Sum(K.OTHERS_CURRENT) e, '' f, Sum(K.TRANSPORT_NEXT)+"
                    + "Sum(K.RENT_NEXT)+Sum(K.BONUS_NEXT)+Sum(K.OTHERS_NEXT) g FROM dbo.SAP_Personnel AS K INNER JOIN dbo.MDAs AS "
                    + "M ON M.administrative_segment = K.ADMIN_SEGMENT where K.FISCAL_YEAR=" + year + " GROUP BY K.ADMIN_SEGMENT UNION "
                    + "SELECT K.ADMIN_SEGMENT a, 'zzzz' b, 'Grand Total' c, Sum(K.HC_CURRENT) d, Sum(K.AMT_CURRENT)+Sum(K.TRANSPORT_CURRENT)+"
                    + "Sum(K.RENT_CURRENT)+Sum(K.BONUS_CURRENT)+Sum(K.OTHERS_CURRENT) e, Sum(K.HC_NEXT) f, Sum(K.AMT_NEXT)+"
                    + "Sum(K.TRANSPORT_NEXT)+Sum(K.RENT_NEXT)+Sum(K.BONUS_NEXT)+Sum(K.OTHERS_NEXT) g FROM dbo.SAP_Personnel AS K "
                    + "INNER JOIN dbo.MDAs AS M ON M.administrative_segment = K.ADMIN_SEGMENT where K.FISCAL_YEAR=" + year + " "
                    + "GROUP BY K.ADMIN_SEGMENT ";
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
			if (tmp.isEmpty()) {
                return "No data available";
            }
            String old = "";
            //htmltable += "<tr><th colspan='5'>[" + tmp.get(0)[0] + "] " + tmp.get(0)[1] + "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
            htmltable += "<tr style='background-color: #f8f8f8; '><th class='text-center' colspan='5'>" + Heading + dummy + "<br>" + tmp.get(0)[0] + "] " + tmp.get(0)[1] + "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
            //htmltable += "<tr><th colspan='5'>["  "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
            htmltable += "<tr style='white-space:nowrap;background-color:#f8f8f8;align='center';'><th  class='text-center'>Grade Level</th><th  class='text-center'>No of Staff</th><th class='text-center'>" + year + " (" + CurrencySymbol + ")" + " Approved<br>Estimates</th><th class='text-center'>No of Staff</th><th class='text-center'>" + year + " (" + CurrencySymbol + ")" + " Approved<br>Estimates</th></tr>";
            for (Object[] tmp1 : tmp) {
                //htmltable += "<tr>";
                if ("zzzz".equals(old)) {
                    //Make new Page header                                        
                    // htmltable += "<tr><th colspan='5'>[" + tmp1[0] + "] " + tmp1[1] + "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
                    thead = "[" + tmp1[0] + "]\t" + tmp1[1];
                    htmltable += "<tr><th class='text-center' colspan='5'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
                    htmltable += "<tr style='background-color: #f8f8f8; '><th class='text-center' colspan='5'>" + Heading + dummy + "<br>" + thead + "</th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th><th style='display:none'></th></tr>";
                    htmltable += "<tr style='white-space:nowrap;background-color: #f8f8f8;'><th class='text-center'>Grade Level</th><th class='text-center'>No of Staff</th><th class='text-center'>" + year + " Approved<br>Estimates</th><th class='text-center'>No of Staff</th><th class='text-center'>" + year + " Approved<br>Estimates</th></tr>";
                } else if ("zzz0".equals(tmp1[1]) || "zzz1".equals(tmp1[1]) || "zzz2".equals(tmp1[1]) || "zzz3".equals(tmp1[1]) || "zzz4".equals(tmp1[1]) || "zzz5".equals(tmp1[1])) {
                    htmltable += "<tr style='white-space:nowrap'><td colspan='2'>" + tmp1[2] + "</td><td style='display:none'></td><td style='text-align:right'>" + ConvertCurrency((tmp1[4])) + "</td><td></td><td style='text-align:right'>" + ConvertCurrency((tmp1[6])) + "</td></tr>";
                } else {
                    htmltable += "<tr style='white-space:nowrap'><td>" + tmp1[2] + "</td><td  style='text-align:center'>" + tmp1[3] + "</td><td style='text-align:right'>" + ConvertCurrency((tmp1[4])) + "</td><td style='text-align:center'>" + tmp1[5] + "</td><td style='text-align:right'>" + ConvertCurrency((tmp1[6])) + "</td></tr>";
                }
                old = tmp1[1].toString();
                //htmltable += "</tr>";
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

    protected String CapitalBudgetbyPolicyReport(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("CapitalBudgetbyPolicyReport   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(Y.economic_segment,1) in (2,3) ";
        }
        String Heading = "" + Utility.ONDOSTATEOFNIGERIA + "</br>";
        String dummy;

        dummy = Titles(ReportNum, "", year, _budget_type, _ActiveVersion);

        String htmltable = "";
        String thead = "";
        htmltable = "<table id='reptable' class='table table-bordered'><thead style='background-color: #f8f8f8; '><tr><th id='reptitle' class='text-center' colspan='6'>" + Heading + dummy;
        htmltable += "</tr><tr><th>Policy Code</th><th>Policy description</th><th>Actual Capital<br> jan. - Dec. " + (year - 2) + " (" + CurrencySymbol + ")" + "</th><th>Actual Capital<br> jan. - Dec. " + (year - 1) + " (" + CurrencySymbol + ")" + "</th><th>Approved Estimates<br> jan. - Dec. " + (year - 1) + " (" + CurrencySymbol + ")" + "</th><th>Approved Estimates<br> jan. - Dec. " + (year - 0) + " (" + CurrencySymbol + ")" + "</th></tr>"
                + "</thead><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int j = 0;
        try {
            tx = session.beginTransaction();
            String sql = "";

            sql = "with nV as ( SELECT P.policy_code as code, P.description as name,"
                    + " (SELECT  sum(isnull(S.AmountLc,0)) from SAP_Actuals S INNER JOIN Project_Detail ps3 ON ps3.policy = P.policy_code INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr Where LEFT(S.FundedProg,10)=ps3.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+" and S.FiscYear=" + (year - 2) + ") As A0,"
                    + " (SELECT  sum(isnull(S.AmountLc,0)) from SAP_Actuals S INNER JOIN Project_Detail ps3 ON ps3.policy = P.policy_code INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr Where LEFT(S.FundedProg,10)=ps3.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+" and S.FiscYear=" + (year - 1) + ") As A1,"
                    + " (SELECT  sum(isnull(Y.budget_amount,0)) from Year_Budget Y INNER JOIN Project_Detail ps3 ON ps3.policy = P.policy_code INNER JOIN MDAs M ON M.administrative_segment=Y.admin_segment Where Y.budget_version_id='"+_ActiveVersion+"' and LEFT(Y.programme_segment,10)=ps3.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+" and Y.budget_year_id=" + (year - 1) + ") As B0,"
                    + " (SELECT  sum(isnull(Y.budget_amount,0)) from Year_Budget Y INNER JOIN Project_Detail ps3 ON ps3.policy = P.policy_code INNER JOIN MDAs M ON M.administrative_segment=Y.admin_segment Where Y.budget_version_id='"+_ActiveVersion+"' and LEFT(Y.programme_segment,10)=ps3.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+" and Y.budget_year_id=" + (year - 0) + ") As B1"
                    + " from Policies P) SELECT v.Code, v.name, v.A0,v.A1, v.B0,v.B1 from nV as v UNION SELECT 'Total', '', sum(v.A0),sum(v.A1), sum(v.B0),sum(v.B1) from nV as v";
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmp1 : tmp) {
                if ("Total".equals(tmp1[0])) {
                    htmltable += "<tr style='background-color: #f8f8f8; '>";
                    //htmltable += "<th></th><th>Total</th>";
                    htmltable += "<td colspan='2'>Total</td>";
                    htmltable += "<td style='display:none'></td>";
                    for (int i = 2; i < 6; i++) {
                        htmltable += "<th style='text-align:right'>" + ConvertCurrency((tmp1[i])) + "</th>";
                    }
                    htmltable += "</tr>";
                } else {
                    htmltable += "<tr>";

                    for (int i = 0; i < 6; i++) {
                        if (i < 2) {
                            htmltable += "<td>" + tmp1[i] + "</td>";
                        } else {
                            if(tmp1[i]==null) {
                                tmp1[i] = 0.0;
                            }
                            htmltable += "<td style=\"text-align:right\">" + ConvertCurrency((tmp1[i])) + "</td>";
                        }
                    }
                    htmltable += "</tr>";
                }
                //htmltable += "</tr>";
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

    protected String GetMdaReports(Integer year, String Heads, String name, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("GetMdaReports   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        
        String adminSegment = Heads;
        String htmltable = null;
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Titles(ReportNum, "", year, _budget_type,"") + "<br/>" + name;

        htmltable += "</tr><tr><th>Code</th><th>Revenue Details</th><th>Actual Revenue Jan - Dec " + (year - 2) + " (" + CurrencySymbol + ")" + "</th><th>Actual Revenue Jan - Dec " + (year - 1) + " (" + CurrencySymbol + ")" + "</th><th>Approved Estimates " + (year - 1) + " (" + CurrencySymbol + ")" + "</th><th>Approved Estimates " + year + " (" + CurrencySymbol + ")" + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "(SELECT e.code, e.name, COALESCE( s.amountlc,0) as amount, 'A" + (year - 2)
                    + "' FROM Economic_Segment e  INNER JOIN SAP_Actuals s  ON e.code = s.CmmtItem  where s.FundsCtr ='" + adminSegment + "' "+budget_type_condition_actual+" and s.Fiscyear=" + (year - 2)
                    + " GROUP BY e.name, e.code,s.amountlc)"
                    + "union"
                    + "(SELECT e.code, e.name,COALESCE(  s.amountlc,0) as amount, 'A" + (year - 1)
                    + "'  FROM Economic_Segment e  INNER JOIN SAP_Actuals s  ON e.code = s.CmmtItem  where s.FundsCtr ='" + adminSegment + "' "+budget_type_condition_actual+" and s.Fiscyear=" + (year - 1)
                    + " GROUP BY e.name, e.code,s.amountlc)"
                    + "UNION"
                    + "(SELECT e.code, e.name, COALESCE( b.budget_amount,0) as amount, 'B" + (year - 1)
                    + "'   FROM Economic_Segment e  INNER JOIN Year_Budget b ON e.code = b.economic_segment where b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment='" + adminSegment + "' "+budget_type_condition_budget+" and b.budget_year_id=" + (year - 1)
                    + " GROUP BY e.name, e.code,b.budget_amount)"
                    + "union"
                    + "(SELECT e.code, e.name, COALESCE( b.budget_amount,0)  as amount, 'B" + (year)
                    + "'  FROM Economic_Segment e  INNER JOIN Year_Budget b  ON e.code = b.economic_segment where b.budget_version_id='"+_ActiveVersion+"' and b.admin_segment='" + adminSegment + "' "+budget_type_condition_budget+" and b.budget_year_id=" + (year)
                    + " GROUP BY e.name, e.code,b.budget_amount)";

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            Integer kcount = tmp.get(0).length;
            String dummy = "";
            htmltable += "<tr>";
            String Ayear2 = "A" + (year - 2) + "";
            String Ayear1 = "A" + (year - 1) + "";
            String Byear1 = "B" + (year - 1) + "";
            String Byear = "B" + (year) + "";
            Double total1 = 0.0;
            Double total2 = 0.0;
            Double total3 = 0.0;
            Double total4 = 0.0;
            //Array list to hold rows 
            ArrayList<String[]> data = new ArrayList<>();
            String[] holder = new String[6];
            //Reformat data such that data of a single programme segment fall in the same row
            for (int n = 0; n < ncount; n++) {

                if (!dummy.equals((String) tmp.get(n)[0])) {
                    data.add(holder);
                    holder = new String[6];
                    holder[0] = (String) tmp.get(n)[0];
                    holder[1] = (String) tmp.get(n)[1];

                    if (Ayear2.equals((String) tmp.get(n)[3])) {
                        if(tmp.get(n)[2]==null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[2] = ConvertCurrency((tmp.get(n)[2]));
                        total1 += (Double) tmp.get(n)[2];
                    }
                    if (Ayear1.equals((String) tmp.get(n)[3])) {
                        if(tmp.get(n)[2]==null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[3] = ConvertCurrency((tmp.get(n)[2]));
                        total2 += (Double) tmp.get(n)[2];
                    }
                    if (Byear1.equals((String) tmp.get(n)[3])) {
                        if(tmp.get(n)[2]==null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[4] = ConvertCurrency((tmp.get(n)[2]));
                        total3 += (Double) tmp.get(n)[2];
                    }
                    if (Byear.equals((String) tmp.get(n)[3])) {
                        if (tmp.get(n)[2] == null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[5] = ConvertCurrency((tmp.get(n)[2]));
                        total4 += (Double) tmp.get(n)[2];
                    }
                } else {
                    if (Ayear2.equals((String) tmp.get(n)[3])) {
                        if (tmp.get(n)[2] == null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[2] = ConvertCurrency((tmp.get(n)[2]));
                        total1 += (Double) tmp.get(n)[2];
                    }
                    if (Ayear1.equals((String) tmp.get(n)[3])) {
                        if (tmp.get(n)[2] == null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[3] = ConvertCurrency((tmp.get(n)[2]));
                        total2 += (Double) tmp.get(n)[2];
                    }
                    if (Byear1.equals((String) tmp.get(n)[3])) {
                        if (tmp.get(n)[2] == null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[4] = ConvertCurrency((tmp.get(n)[2]));
                        total3 += (Double) tmp.get(n)[2];
                    }
                    if (Byear.equals((String) tmp.get(n)[3])) {
                        if (tmp.get(n)[2] == null) {
                            tmp.get(n)[2] = 0.0;
                        }
                        holder[5] = ConvertCurrency((tmp.get(n)[2]));
                        total4 += (Double) tmp.get(n)[2];
                    }
                }
                dummy = (String) tmp.get(n)[0];
                if (n + 1 == ncount) {
                    data.add(holder);
                }
            }

            //create html table
            boolean firstRun = true;
            for (String[] tmp1 : data) {
                if (firstRun) {
                    firstRun = false;
                    continue;
                }
                htmltable += "<tr>";
                for (int i = 0; i < 6; i++) {
                    if (tmp1[i] == null) {
                        htmltable += "<td style='text-align:right;'>0.00</td>";
                    } else {
                        htmltable += "<td style='text-align:right;'>" + tmp1[i] + "</td>";
                    }
                }
                htmltable += "</tr>";
            }
            htmltable += "<tr style='text-align:right; background-color: #eee;font-weight:bold;'> <td colspan='2'>Sub-total</td> <td>" + ConvertCurrency((total1))
                    + "</td> <td>" + ConvertCurrency((total2)) + "</td> <td>" + ConvertCurrency((total3)) + "</td> <td>" + ConvertCurrency((total4)) + "</td> <td colspan='2'></td> </tr>";
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

    protected String GetMdasReports(Integer year, String Heads, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        String htmltable = null;
        htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='8'> "
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion);
        htmltable += "</tr><tr><th>Code</th><th>Revenue Details</th><th>Actual Revenue Jan - Dec " + (year - 2) + "</th><th>Actual Revenue Jan - Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th>"
                + "<th>Approved Estimates " + year + "</th><th>% of completion</th><th>Implementation Schedule</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select administrative_Segment as admin_segment,'' as code,name, 0 as amt1, 0 as amt2, 0 as amt3, 0 as amt4, 0 as year, 0 as rank, '' as p_comp, '' as c_from, '' as c_to  from mdas"
                    + " where sub_sector_code <> '00' union "
                    + " SELECT a.admin_segment, a.programme_segment as code,"
                    + " (select name from Programme_Segment where code =a.programme_segment) as name, "
                    + " (select ISNULL((select sum(AmountLc) from SAP_Actuals where FiscYear= " + (year - 2) + " and FundedProg=a.programme_segment  "+budget_type_condition_actual+" and FundsCtr=a.admin_segment),0)) as amount1,"
                    + " (select ISNULL((select sum(AmountLc) from SAP_Actuals  where FiscYear=" + (year - 1) + " and FundedProg=a.programme_segment "+budget_type_condition_actual+" and FundsCtr=a.admin_segment),0)) as amount2,"
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment  and economic_segment=a.economic_segment "+budget_type_condition_budget+" and b.budget_year_id=" + (year - 1) + "and a.admin_segment=b.admin_segment  and a.programme_segment=b.programme_segment and a.economic_segment=b.economic_segment and a.functional_segment=b.functional_segment  and a.fund_segment=b.fund_segment  and a.geo_segment=b.geo_segment and a.dept_id=b.dept_id) as amount3,"
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment  and economic_segment=a.economic_segment "+budget_type_condition_budget+" and b.budget_year_id=" + (year) + " and a.admin_segment=b.admin_segment  and a.programme_segment=b.programme_segment and a.economic_segment=b.economic_segment and a.functional_segment=b.functional_segment  and a.fund_segment=b.fund_segment  and a.geo_segment=b.geo_segment and a.dept_id=b.dept_id) as amount4,"
                    + " (select year_id from Project_Detail where code=substring(a.programme_segment,3,10)) as year_id,"
                    + " 100 as rank, ISNULL(a.percent_complete,'') as p_comp, ISNULL(a.complete_from,'') as c_from, ISNULL(a.complete_to,'') as c_to "
                    + " FROM Year_Budget a INNER JOIN MDAs M on M.administrative_segment = a.admin_segment where a.budget_version_id='"+_ActiveVersion+"' and M.sub_sector_code<>'00'"
                    + " group by a.admin_segment,a.programme_segment,a.economic_segment,a.functional_segment,a.fund_segment,a.geo_segment,a.dept_id, a.percent_complete, a.complete_from, a.complete_to,a.id"
                    + " order by admin_segment";
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            Double total1 = 0.0;
            Double total2 = 0.0;
            Double total3 = 0.0;
            Double total4 = 0.0;
            for (int n = 0; n < ncount; n++) {

                if ("".equals(tmp.get(n)[1])) {
                    if (n > 0) {
                        htmltable += "<tr style='text-align:right; background-color: #eee;font-weight:bold;'> <td colspan='2'>Total</td><td style='display:none'></td><td>" + ConvertCurrency(total1)
                                + "</td> <td>" + ConvertCurrency((total2)) + "</td> <td>" + ConvertCurrency((total3)) + "</td> <td>" + ConvertCurrency((total4)) + "</td> <td colspan='2'></td><td style='display:none'></td></tr>";
                        total1 = 0.0;
                        total2 = 0.0;
                        total3 = 0.0;
                        total4 = 0.0;
                    }

                    htmltable += "<tr>";
                    //System.out.println(tmp.get(n)[1]);
                    htmltable += "<td colspan='8' style='font-weight: bold;'> [" + tmp.get(n)[0] + "] - " + tmp.get(n)[2] + "</td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td>";
                    htmltable += "</tr>";
                } else {
					if (tmp.get(n)[3] == null) {
                        tmp.get(n)[3] = 0.0;
                    }
                    if (tmp.get(n)[4] == null) {
                        tmp.get(n)[4] = 0.0;
                    }
                    if (tmp.get(n)[5] == null) {
                        tmp.get(n)[5] = 0.0;
                    }
                    if (tmp.get(n)[6] == null) {
                        tmp.get(n)[6] = 0.0;
                    }
                    htmltable += "<tr>";
                    htmltable += "<td>" + tmp.get(n)[1] + "</td>";
                    htmltable += "<td>" + tmp.get(n)[2] + "</td>";
                    if (tmp.get(n)[3] == null) {
                        tmp.get(n)[3] = 0.0;
                    }                    htmltable += "<td style='text-align:right'>" + ConvertCurrency((tmp.get(n)[3])) + "</td>";
                    total1 += (Double) tmp.get(n)[3];
                    htmltable += "<td style='text-align:right'>" + ConvertCurrency((tmp.get(n)[4])) + "</td>";
                    total2 += (Double) tmp.get(n)[4];
                    htmltable += "<td style='text-align:right'>" + ConvertCurrency((tmp.get(n)[5])) + "</td>";
                    total3 += (Double) tmp.get(n)[5];
                    htmltable += "<td style='text-align:right'>" + ConvertCurrency((tmp.get(n)[6])) + "</td>";
                    total4 += (Double) tmp.get(n)[6];
                    htmltable += "<td>" + tmp.get(n)[9] + "</td>";
                    htmltable += "<td>" + tmp.get(n)[10] + "</td>";
                    htmltable += "</tr>";
                }

                if (n + 1 == tmp.size()) {
                    htmltable += "<tr style='text-align:right; background-color: #eee;font-weight:bold;'> <td colspan='2'>Total</td><td style='display:none'></td><td>" + ConvertCurrency(total1)
                            + "</td> <td>" + ConvertCurrency((total2)) + "</td> <td>" + ConvertCurrency((total3)) + "</td> <td>" + ConvertCurrency((total4)) + "</td> <td colspan='2'></td><td style='display:none'></td></tr>";
                    total1 = 0.0;
                    total2 = 0.0;
                    total3 = 0.0;
                    total4 = 0.0;
                }

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

    protected String GetBudgetBySectors(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        //System.out.println("GetBudgetBySectors   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion) + "</tr>";
        htmltable += "<tr><th>Admin Code</th><th>Organization Name</th><th>Actual  Jan - Dec " + (year - 2) + "</th><th>Actual Jan - Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th>"
                + "<th>Approved Estimates " + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select sector_code as sector_code, '0' as admin_segment,  name, 0 as amt1, 0 as amt2, 0 as amt3, 0 as amt4 from sectors  "
                    + "union  "
                    + "select SUBSTRING(a.admin_segment,1,2) as sector_code, a.admin_Segment as admin_segment,  "
                    + "(select name from mdas  where administrative_segment = a.admin_segment) as name, "
                    + "(select ISNULL((select sum(AmountLc) from SAP_Actuals where FiscYear=" + (year - 2) + " "+budget_type_condition_actual+" and FundsCtr=a.admin_segment),0)) as amount1,  "
                    + "(select ISNULL((select sum(AmountLc) from SAP_Actuals  where FiscYear=" + (year - 1) + " "+budget_type_condition_actual+" and FundsCtr=a.admin_segment),0)) as amount2,  "
                    + "(select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment "+budget_type_condition_budget+" and b.budget_year_id=" + (year - 1) + " ) as amount3, "
                    + "(select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id='"+_ActiveVersion+"' and admin_segment=a.admin_segment "+budget_type_condition_budget+" and b.budget_year_id=" + (year) + ") as amount4 "
                    + "from Year_Budget as a INNER JOIN MDAs as m ON(m.administrative_segment = a.admin_segment) where a.budget_version_id='"+_ActiveVersion+"' and m.sub_sector_code <> '00' "
                    + "group by a.admin_segment,a.programme_segment,a.economic_segment,a.functional_segment,a.fund_segment,a.geo_segment,a.dept_id, a.percent_complete, a.complete_from, a.complete_to,a.id "
                    + "ORDER BY sector_code";
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            String current = (String) tmp.get(0)[0];
            Double total1 = 0.0, total11 = 0.0;
            Double total2 = 0.0, total22 = 0.0;
            Double total3 = 0.0, total33 = 0.0;
            Double total4 = 0.0, total44 = 0.0;
            for (int n = 0; n < ncount; n++) {
                //output total for the last group and reset total variables
                if (!current.equals(tmp.get(n)[0])) {
                    htmltable += "<tr style='background:#eee;font-weight:bold;'>";
                    htmltable += "<td colspan='2'> Sub Total </td>";
                    htmltable += "<td style='display:none'></td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total1) + "</td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total2) + "</td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total3) + "</td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total4) + "</td>";
                    htmltable += "</tr>";
                    total11 += total1;
                    total22 += total2;
                    total33 += total3;
                    total44 += total4;
                    total1 = total2 = total3 = total4 = 0.0;
                }

                //check and output header row
                if ("0".equals(tmp.get(n)[1])) {
                    htmltable += "<tr style='font-weight:bold;'> <td> " + tmp.get(n)[0] + "</td> <td colspan='5''> " + tmp.get(n)[2] + " </td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
                } else {
                    if (tmp.get(n)[3] == null) {
                        tmp.get(n)[3] = 0.0;
                    }
                    if (tmp.get(n)[4] == null) {
                        tmp.get(n)[4] = 0.0;
                    }
                    if (tmp.get(n)[5] == null) {
                        tmp.get(n)[5] = 0.0;
                    }
                    if (tmp.get(n)[6] == null) {
                        tmp.get(n)[6] = 0.0;
                    }					
                    htmltable += "<tr>";
                    htmltable += "<td> " + tmp.get(n)[1] + "</td>";
                    htmltable += "<td> " + tmp.get(n)[2] + "</td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[3]) + "</td>";
                    total1 += (Double) tmp.get(n)[3];
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[4]) + "</td>";
                    total2 += (Double) tmp.get(n)[4];
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[5]) + "</td>";
                    total3 += (Double) tmp.get(n)[5];
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[6]) + "</td>";
                    total4 += (Double) tmp.get(n)[6];
                    htmltable += "</tr>";
                }

                //output total if its the last loop iteration
                if (n + 1 == ncount) {
                    htmltable += "<tr style='background:#eee;font-weight:bold;'>";
                    htmltable += "<td colspan='2'> Sub Total </td>";
                    htmltable += "<td style='display:none'></td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total1) + "</td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total2) + "</td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total3) + "</td>";
                    htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total4) + "</td>";
                    htmltable += "</tr>";
                }

                current = (String) tmp.get(n)[0];
            }
            total11 += total1;
            total22 += total2;
            total33 += total3;
            total44 += total4;
            htmltable += "<tr><th colspan='2'>TOTAL</th><th style='display:none'></th><th>" + ConvertCurrency(total11) + "</th><th>" + ConvertCurrency(total22) + "</th><th>" + ConvertCurrency(total33) + "</th><th>" + ConvertCurrency(total44) + "</th><th></tr>";
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

    protected String GetFunctionalSegmentBudget(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion) + "</tr>";
        htmltable += "<tr><th>Admin Code</th><th>Organization Name</th><th>Actual<br>Jan - Dec" + (year - 2) + "</th><th>Actual<br>Jan - Dec" + (year - 1) + "</th><th>Approved Estimates<br>" + (year - 1) + "</th>"
                + "<th>Approved Estimates<br>" + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "With nV as (select f.code, f.name,"
                    + " (select isnull(sum(AmountLc),0) from SAP_Actuals S INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 2) + " and substring(FuncArea,1,3)=f.code "+budget_type_condition_actual+" AND M.sub_sector_code<>'00' ) as amount1,"
                    + " (select isnull(sum(AmountLc),0) from SAP_Actuals S INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 1) + " and substring(FuncArea,1,3)=f.code "+budget_type_condition_actual+" AND M.sub_sector_code<>'00' ) as amount2,"
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and substring(Functional_Segment,1,3)=f.code and "+budget_type_condition_budget+" b.budget_year_id= " + (year - 1) + " AND M.sub_sector_code<>'00' ) as amount3,"
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and substring(Functional_Segment,1,3)=f.code and "+budget_type_condition_budget+" b.budget_year_id= " + (year - 0) + " AND M.sub_sector_code<>'00' ) as amount4"
                    + " from Functional_Segment_Header1 as f) SELECT n.code, N.Name, N.amount1,N.amount2,N.amount3,N.amount4  from nV as N UNION SELECT 'Total', '', Sum(N.amount1),SUM(N.amount2),SUM(N.amount3),SUM(N.amount4)  from nV as N";
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            for (int n = 0; n < ncount - 1; n++) {
                if (tmp.get(n)[2] == null) {
                    tmp.get(n)[2] = 0.0;
                }
                if (tmp.get(n)[3] == null) {
                    tmp.get(n)[3] = 0.0;
                }
                if (tmp.get(n)[4] == null) {
                    tmp.get(n)[4] = 0.0;
                }
                if (tmp.get(n)[5] == null) {
                    tmp.get(n)[5] = 0.0;
                }					
                htmltable += "<tr>";
                htmltable += "<td> " + tmp.get(n)[0] + "</td>";
                htmltable += "<td> " + tmp.get(n)[1] + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[2]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[3]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[4]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[5]) + "</td>";
                htmltable += "</tr>";
            }
            if (tmp.get(ncount - 1)[2] == null) {
                tmp.get(ncount - 1)[2] = 0.0;
            }
            if (tmp.get(ncount - 1)[3] == null) {
                tmp.get(ncount - 1)[3] = 0.0;
            }
            if (tmp.get(ncount - 1)[4] == null) {
                tmp.get(ncount - 1)[4] = 0.0;
            }
            if (tmp.get(ncount - 1)[5] == null) {
                tmp.get(ncount - 1)[5] = 0.0;
            }
            htmltable += "<tr style='background:#eee;font-weight:bold;'>";
            htmltable += "<td colspan='2'> Sub Total </td>";
            htmltable += "<td style='display:none'></td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(ncount - 1)[2]) + "</td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(ncount - 1)[3]) + "</td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(ncount - 1)[4]) + "</td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(ncount - 1)[5]) + "</td>";
            htmltable += "</tr>";
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

    protected String SummaryProgrammeSegment(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(S.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(b.economic_segment,1) in (2,3) ";
        }
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th id='reptitle' class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion) + "</tr>";
        htmltable += "<tr><th>Programme Code</th><th>Programme Description</th><th>Actual  Jan - Dec " + (year - 2) + "</th><th>Actual Jan - Dec " + (year - 1) + "</th><th>Approved Estimates " + (year - 1) + "</th>"
                + "<th>Approved Estimates " + year + "</th></thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "With nV as (select f.code, f.name,"
                    + " (select isnull(sum(AmountLc),0) from SAP_Actuals S  INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 2) + "  and substring(FundedProg,1,2)=f.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amount1,"
                    + " (select isnull(sum(AmountLc),0) from SAP_Actuals S  INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 1) + "  and substring(FundedProg,1,2)=f.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amount2,"
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and substring(Programme_Segment,1,2)=f.code and b.budget_year_id= " + (year - 1) + " AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amount3,"
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and substring(Programme_Segment,1,2)=f.code and b.budget_year_id= " + (year - 0) + " AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amount4"
                    + " from Programme_Segment_Header1 as f) SELECT n.code, N.Name, N.amount1,N.amount2,N.amount3,N.amount4  from nV as N --UNION SELECT 'Total', '', Sum(N.amount1),SUM(N.amount2),SUM(N.amount3),SUM(N.amount4)  from nV as N";

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            Double total1 = 0.0;
            Double total2 = 0.0;
            Double total3 = 0.0;
            Double total4 = 0.0;
            for (int n = 0; n < ncount; n++) {
                if (tmp.get(n)[2] == null) {
                    tmp.get(n)[2] = 0.0;
                }
                if (tmp.get(n)[3] == null) {
                    tmp.get(n)[3] = 0.0;
                }
                if (tmp.get(n)[4] == null) {
                    tmp.get(n)[4] = 0.0;
                }
                if (tmp.get(n)[5] == null) {
                    tmp.get(n)[5] = 0.0;
                }				
                htmltable += "<tr>";
                htmltable += "<td> " + tmp.get(n)[0] + "</td>";
                htmltable += "<td> " + tmp.get(n)[1] + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[2]) + "</td>";
                total1 += (Double) tmp.get(n)[2];
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[3]) + "</td>";
                total2 += (Double) tmp.get(n)[3];
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[4]) + "</td>";
                total3 += (Double) tmp.get(n)[4];
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[5]) + "</td>";
                total4 += (Double) tmp.get(n)[5];
                htmltable += "</tr>";

            }
            htmltable += "<tr style='background:#eee;font-weight:bold;'>";
            htmltable += "<td colspan='2'> Sub Total</td><td style='display:none;'></td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total1) + "</td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total2) + "</td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total3) + "</td>";
            htmltable += "<td style='text-align:right;'> " + ConvertCurrency(total4) + "</td>";
            htmltable += "</tr>";
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

    protected String SummaryPersonnelCost(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th class='text-center' colspan='9'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion) + "</tr>";
        htmltable += "<tr> <th>Head</th> <th>MDAs Name</th> <th>Basic Salary</th> <th>Transport</th> <th> Rent</th> <th> Bonus</th> <th>Others</th> "
                + "<th>Estimate " + (year - 1) + "</th> <th>Approved Estimate " + (year) + "</th>"
                + "</thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql ="with nV as(SELECT a.administrative_segment AS code, a.name AS name, ISNULL(sum(b.BASIC_NEXT),0) AS basic, ISNULL(sum(b.TRANSPORT_NEXT),0) AS transport, ISNULL(sum(b.RENT_NEXT),0) AS rent, ISNULL(sum(b.BONUS_NEXT),0) AS bonus, ISNULL(sum(b.OTHERS_NEXT),0) AS others, ISNULL(sum(b.BASIC_CURRENT),0)+ISNULL(sum(b.BASIC_CURRENT),0)+ISNULL(sum(b.RENT_CURRENT),0)+ISNULL(sum(b.BONUS_CURRENT),0)+ISNULL(sum(b.OTHERS_CURRENT),0) as Tc, ISNULL(sum(b.BASIC_NEXT),0)+ISNULL(sum(b.BASIC_NEXT),0)+ISNULL(sum(b.RENT_NEXT),0)+ISNULL(sum(b.BONUS_NEXT),0)+ISNULL(sum(b.OTHERS_NEXT),0) as Tn FROM MDAs AS a INNER JOIN SAP_Personnel b ON b.ADMIN_SEGMENT = a.administrative_segment WHERE b.FISCAL_YEAR="+ year +" AND a.sub_sector_code <> '00' GROUP BY a.administrative_segment, a.name) Select n.code, n.name, n.basic, n.transport, n.rent, n.bonus, n.others,Tc,Tn from nV n union all select 'Total', '', Sum(n.basic), sum(n.transport), SUM(n.rent), SUM(n.bonus),SUM(n.others), sum(n.Tc),sum(n.Tn) from nV n";
//                    //"with nV AS( select a.administrative_segment as code, a.name as name, ISNULL(sum(b.basic),0) as basic, ISNULL"
//                    + "(sum(b.Transport),0) as transport, ISNULL(sum(b.Rent),0) as total_rent, ISNULL(sum(b.Bonus),0) as total_bonus, "
//                    + "ISNULL(sum(b.others),0) as others, ISNULL(sum(b.Estimate_current_year),0) as current_year, ISNULL(sum(b.Estimate_next_year)"
//                    + ",0) as next_year from MDAs a LEFT JOIN PersonnelTable b ON(a.administrative_segment = b.Admin_Segment) where sub_sector_code <> '00' "
//                    + "GROUP BY a.administrative_segment,a.name) select n.code, n.name, n.basic, n.transport, n.total_rent, n.total_bonus,n.others, "
//                    + "n.current_year,n.next_year from nV n union select 'Total', '', Sum(n.basic), sum(n.transport), SUM(n.total_rent), SUM(n.total_bonus),"
//                    + "SUM(n.others), sum(n.current_year),sum(n.next_year) from nV n";

            List<Object[]> tmp = session.createSQLQuery(sql).list();
            Integer ncount = tmp.size();
            for (int n = 0; n < ncount - 1; n++) {
                if (tmp.get(n)[2] == null) {
                    tmp.get(n)[2] = 0.0;
                }
                if (tmp.get(n)[3] == null) {
                    tmp.get(n)[3] = 0.0;
                }
                if (tmp.get(n)[4] == null) {
                    tmp.get(n)[4] = 0.0;
                }
                if (tmp.get(n)[5] == null) {
                    tmp.get(n)[5] = 0.0;
                }
                if (tmp.get(n)[6] == null) {
                    tmp.get(n)[6] = 0.0;
                }
                if (tmp.get(n)[7] == null) {
                    tmp.get(n)[7] = 0.0;
                }
                if (tmp.get(n)[8] == null) {
                    tmp.get(n)[8] = 0.0;
                }				
                htmltable += "<tr>";
                htmltable += "<td> " + tmp.get(n)[0] + "</td>";
                htmltable += "<td> " + tmp.get(n)[1] + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[2]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[3]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[4]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[5]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[6]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[7]) + "</td>";
                htmltable += "<td style='text-align:right;'> " + ConvertCurrency(tmp.get(n)[8]) + "</td>";
                htmltable += "</tr>";

            }
            htmltable += "<tr><th colspan='2'>Total</th><th style='display:none'></th>";
            for (int i = 2; i < tmp.get(ncount - 1).length; i++) {
                htmltable += "<th style='text-align:right;'>" + ConvertCurrency(tmp.get(ncount - 1)[i]) + "</th>";
            }
            htmltable += "</tr>";
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

    protected String ConsolidatedBudgetSummary0(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        System.out.println("ConsolidatedBudgetSummary0   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (2,3) ";
        }
        
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th class='text-center' colspan='6'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion) + "</tr>";
        htmltable += "<tr><th>Sn</th><th>Description</th><th>Actual<br>Jan. - Dec. " + (year - 2) + "</th><th>Actual<br>Jan. - Dec. " + (year - 1) + "</th><th>Estimates<br>" + (year - 1) + "</th><th>Estimates<br>" + (year - 0) + "</th>"
                + "</thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int j = 1;
        int k = 0;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT 'Opening Balance',(SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '15010101%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '15010101%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '15010101%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '15010101%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 union ALL ";
                sql += " SELECT 'Receipts' A1,'' A2,'' A3,'' A4,'' A5 UNION all SELECT 'Statutory Allocation',(SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '11010101%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '11010101%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '11010101%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '11010101%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Value Added Tax', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '11010201%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '11010201%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '11010201%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '11010201%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Independent Revenue', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '12%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '12%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '12%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '12%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Aid & Grants', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '13%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00'  "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '13%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '13%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '13%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Capital Receipts', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '14%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '14%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '14%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '14%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + "union ALL SELECT 'Total Current Year Receipts', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '11010101%' or A.code like '11010201%' or A.code like '12%' or A.code like '13%' or A.code like '14%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '11010101%' or A.code like '11010201%' or A.code like '12%' or A.code like '13%' or A.code like '14%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '11010101%' or A.code like '11010201%' or A.code like '12%' or A.code like '13%' or A.code like '14%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '11010101%' or A.code like '11010201%' or A.code like '12%' or A.code like '13%' or A.code like '14%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " UNION all SELECT 'Expenditure' A1,'' A2,'' A3,'' A4,'' A5 UNION all SELECT 'A. Recurrent Debt' A1,'' A2,'' A3,'' A4,'' A5 UNION all SELECT 'CRF Charges - Public Debt Charges', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Internal Loans Repayment', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22060202%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22060202%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22060202%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22060202%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'External Loans Repayment', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22060102%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22060102%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22060102%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22060102%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Total Recurrent Debt', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'B. Recurrent Non Debt' A1,'' A2,'' A3,'' A4,'' A5 UNION all SELECT 'Personnel Cost (Salary)', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010101%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010101%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010101%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010101%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'CRF Charges - Statutory Offices Holders Salaries', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'CRF Charges - Pension , Gratuities and Benefit to Past Governors and Deputy Governors', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22010104%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22010104%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22010104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22010104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Overhead Cost', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '2202%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '2202%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '2202%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '2202%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Grants to Government Agencies/Institutions', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22040110%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22040110%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22040110%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22040110%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Grants to Government Owned Companies-Current', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22040105%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22040105%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22040105%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22040105%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Special Programme', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '2210%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '2210%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '2210%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '2210%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Transfer to Local Government funding', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22070104%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '22070104%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22070104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '22070104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Total Recurrent Non - Debt', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 "
                    + " union ALL SELECT 'Total Recurrent Expenditure', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' or A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' WHERE b.FiscYear = " + (year - 2) + " AND b.CmmtItem = A.code AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' or A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' where b.FiscYear=" + (year - 1) + " and b.CmmtItem =A.code and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' or A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON A.code like '21010103%' or A.code like '22060202%' or A.code like '22060102%' or A.code like '21010101%' or A.code like '21010103%' or A.code like '22010104%' or A.code like '2202%' or A.code like '22040110%' or A.code like '22040105%' or A.code like '2210%' or A.code like '22070104%' where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and c.economic_segment=A.code and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 ";
            if (ReportNum == 19) {
                sql += " UNION all SELECT 'C : Capital Expenditure Based on Sector' A1,'' A2,'' A3,'' A4,'' A5 UNION all SELECT E.Name, (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON A.code =b.CmmtItem WHERE b.FiscYear = " + (year - 2) + " AND M.sub_sector_code <> '00' and left(M.administrative_segment,2)= E.sector_code "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr INNER JOIN Economic_Segment AS A ON b.CmmtItem =A.code where b.FiscYear=" + (year - 1) + " and M.sub_sector_code<>'00' and left(M.administrative_segment,2)= E.sector_code "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON c.economic_segment=A.code where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and M.sub_sector_code<>'00' and left(M.administrative_segment,2)= E.sector_code "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment INNER JOIN Economic_Segment AS A ON c.economic_segment=A.code where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and M.sub_sector_code<>'00' and left(M.administrative_segment,2)= E.sector_code "+budget_type_condition_budget+") as amt4 from Sectors E "
                    + " UNION ALL SELECT DISTINCT 'Total Capital Expenditure', (SELECT isnull(sum(b.AmountLc),0) FROM SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr WHERE b.FiscYear = " + (year - 2) + " AND M.sub_sector_code <> '00' "+budget_type_condition_actual+") as amt1, "
                    + " (select isnull(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M on M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                    + " (select isnull(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M on M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 0) + " and M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4 from Sectors E GROUP BY E.sector_code";
            } else {
                sql += " UNION all SELECT 'C : Capital Expenditure Based on Project_Detail' A1,'' A2,'' A3,'' A4,'' A5 UNION all select f.name,(select isnull(sum(AmountLc),0) from SAP_Actuals S INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 2) + " and substring(FundedProg,1,2)=f.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amount1, "
                    + " (select isnull(sum(AmountLc),0) from SAP_Actuals S INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 1) + " and substring(FundedProg,1,2)=f.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amount2, "
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and substring(Programme_Segment,1,2)=f.code and b.budget_year_id= " + (year - 1) + " AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amount3, "
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and substring(Programme_Segment,1,2)=f.code and b.budget_year_id= " + (year - 0) + " AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amount4 from Programme_Segment_Header1 f "
                    + " union all select DISTINCT 'Total Capital Expenditure', (select isnull(sum(AmountLc),0) from SAP_Actuals S INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 2) + " AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amount1, "
                    + " (select isnull(sum(AmountLc),0) from SAP_Actuals S INNER JOIN MDAs M ON M.administrative_segment=S.FundsCtr where FiscYear= " + (year - 1) + " AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amount2, "
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and b.budget_year_id= " + (year - 1) + " AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amount3, "
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b INNER JOIN MDAs M ON M.administrative_segment=b.admin_segment where b.budget_version_id='"+_ActiveVersion+"' and b.budget_year_id= " + (year - 0) + " AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amount4 from Programme_Segment_Header1 f GROUP BY f.code";
            }
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmpl : tmp) {
                k++;
                if (tmpl[0].toString().substring(0, 5).equals("Total")) {
                    htmltable += "<tr style='background-color:#eee;font-weight:bold;'><td>" + j++ + "</td>";
                } else {
                    htmltable += "<tr><td>" + j++ + "</td>";
                }
                if (k == 2 || k == 9 || k == 15 || k == 26) {
                    htmltable += "<td colspan='5'>" + tmpl[0] + "</td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td>";
                } else if (k == 1) {
                    for (int i = 0; i < tmpl.length; i++) {
                        if (i > 0) {
                            if(tmpl[i]==null) {
                                tmpl[i] = 0.0;
                            }
                            htmltable += "<td style='text-align:right;font-weight:bold;'>" + ConvertCurrency(tmpl[i]) + "</td>";
                        } else {
                            htmltable += "<td style='font-weight:bold;'>" + tmpl[i] + "</td>";
                        }
                    }

                } else {
                    for (int i = 0; i < tmpl.length; i++) {
                        if (i > 0) {
                            if(tmpl[i]==null) {
                                tmpl[i] = 0.0;
                            }
                            htmltable += "<td style='text-align:right;'>" + ConvertCurrency(tmpl[i]) + "</td>";
                        } else {
                            htmltable += "<td>" + tmpl[i] + "</td>";
                        }
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


    protected String CapitalBudgetAllocation2MDAS(Integer year, Integer ReportNum, String _budget_type) {
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th class='text-center' colspan='11'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, ActiveVersion+"") + "</tr>";
        htmltable += "<tr><th>Sn</th><th>MDA Name</th><th>" + (year - 2) + "<br>(Foreign Component)<br>Estimates</th><th>" + (year - 2) + " (" + CurrencySymbol + ")" + "<br>(Local Component)<br>Estimates</th><th>" + (year - 2) + " (" + CurrencySymbol + ")" + "<br>Total<br>Approved<br>Estimates<br>(Local & Foreign<br>Component)</th><th>" + (year - 1) + " (" + CurrencySymbol + ")" + "<br>(Foreign Component)<br>Estimates</th><th>" + (year - 1) + " (" + CurrencySymbol + ")" + "<br>(Local Component)<br>Estimates</th><th>" + (year - 1) + " (" + CurrencySymbol + ")" + "<br>Total<br>Approved<br>Estimates<br>(Local & Foreign<br>Component)</th><th>" + (year - 0) + " (" + CurrencySymbol + ")" + "<br>(Foreign Component)<br>Estimates</th><th>" + (year - 0) + " (" + CurrencySymbol + ")" + "<br>(Local Component)<br>Estimates</th><th>" + (year - 0) + " (" + CurrencySymbol + ")" + "<br>Total<br>Approved<br>Estimates<br>(Local & Foreign<br>Component)</th></tr>"
                + "</thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int j = 1;
        try {
            tx = session.beginTransaction();
            String sql = "with nV as( Select M.Name Name, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010201%'and Y.budget_year_id=" + (year - 2) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "') F1, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010101%'and Y.budget_year_id=" + (year - 2) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) L1, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and (Y.economic_segment like '13010101%' or Y.economic_segment like '13010201%') and Y.budget_year_id=" + (year - 2) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) T1, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010201%'and Y.budget_year_id=" + (year - 1) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) F2, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010101%'and Y.budget_year_id=" + (year - 1) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) L2, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and (Y.economic_segment like '13010101%' or Y.economic_segment like '13010201%') and Y.budget_year_id=" + (year - 1) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) T2, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010201%'and Y.budget_year_id=" + (year - 0) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) F3, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010101%'and Y.budget_year_id=" + (year - 0) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) L3, "
                    + "(SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.admin_segment=M.administrative_segment and (Y.economic_segment like '13010101%' or Y.economic_segment like '13010201%') and Y.budget_year_id=" + (year - 0) + " and Y.budget_version_id=" + ActiveVersion + " and Y.sap_budget_type='" + ActiveBudgetType + "' ) T3 from MDAs M where M.sub_sector_code<>'00' ) "
                    + "Select N.Name, N.F1, N.L1, N.T1, N.F2, N.L2, N.T2, N.F3, N.L3, N.T3 from nV N Union ALL Select 'Total', Sum(N.F1), sum(N.L1), sum(N.T1), sum(N.F2), sum(N.L2), sum(N.T2), sum(N.F3), sum(N.L3), sum(N.T3) from nV N";
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmpl : tmp) {
                if (tmpl[0].equals("Total")) {
                    htmltable += "<tr style='background-color:#eee;font-weight:bold;'><td colspan='2'>Total</td><td style='display:none'></td>";
                    for (int i = 1; i < tmpl.length; i++) {
                        htmltable += "<td style='text-align:right;font-weight:bold;'>" + ConvertCurrency(tmpl[i]) + "</td>";
                    }
                } else {
                    htmltable += "<tr><td>" + j++ + "</td>";
                    for (int i = 0; i < tmpl.length; i++) {
                        if (i > 0) {
                            if (tmpl[i] == null) {
                                tmpl[i] = 0.0;
                            }
                            htmltable += "<td style='text-align:right;'>" + ConvertCurrency(tmpl[i]) + "</td>";
                        } else {
                            htmltable += "<td style='font-weight:bold;'>" + tmpl[i] + "</td>";
                        }
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

    /*protected String CapitalBudgetAllocation2MDAS(Integer year, Integer ReportNum, String _budget_type, String _ActiveVersion) {
        String htmltable = "<table id='reptable' class='table table-bordered'><thead><tr><th class='text-center' colspan='11'>" + Utility.ONDOSTATEOFNIGERIA + "</br>"
                + Titles(ReportNum, "", year, _budget_type, _ActiveVersion) + "</tr>";
        htmltable += "<tr><th>Sn</th><th>MDA Name</th><th>" + (year - 2) + "<br>(Foreign Component)<br>Estimates</th><th>" + (year - 2) + "<br>(Local Component)<br>Estimates</th><th>" + (year - 2) + "<br>Total<br>Approved<br>Estimates<br>(Local & Foreign<br>Component)</th><th>" + (year - 1) + "<br>(Foreign Component)<br>Estimates</th><th>" + (year - 1) + "<br>(Local Component)<br>Estimates</th><th>" + (year - 1) + "<br>Total<br>Approved<br>Estimates<br>(Local & Foreign<br>Component)</th><th>" + (year - 0) + "<br>(Foreign Component)<br>Estimates</th><th>" + (year - 0) + "<br>(Local Component)<br>Estimates</th><th>" + (year - 0) + "<br>Total<br>Approved<br>Estimates<br>(Local & Foreign<br>Component)</th></tr>"
                + "</thead></tr><tbody>";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int j = 1;
        try {
            tx = session.beginTransaction();
            String sql = "with nV as( Select M.Name Name, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + " ) L1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' andY.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and (Y.economic_segment like '32%' or Y.economic_segment like '13010201%') and Y.budget_year_id=" + (year - 2) + " ) T1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' andY.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010201%'and Y.budget_year_id=" + (year - 1) + " ) F2, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' andY.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 1) + " ) L2, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' andY.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and (Y.economic_segment like '32%' or Y.economic_segment like '13010201%') and Y.budget_year_id=" + (year - 1) + " ) T2, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' andY.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and Y.economic_segment like '13010201%'and Y.budget_year_id=" + (year - 0) + " ) F3, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' andY.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 0) + " ) L3, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' andY.admin_segment=M.administrative_segment and Y.economic_segment like '32%'and Y.budget_year_id=" + (year - 2) + ") F1, (SELECT ISNULL(SUM(Y.budget_amount),0) from Year_Budget Y where Y.budget_version_id='"+_ActiveVersion+"' and Y.admin_segment=M.administrative_segment and (Y.economic_segment like '32%' or Y.economic_segment like '13010201%') and Y.budget_year_id=" + (year - 0) + " ) T3 from MDAs M where M.sub_sector_code<>'00' ) Select N.Name, N.F1, N.L1, N.T1, N.F2, N.L2, N.T2, N.F3, N.L3, N.T3 from nV N Union ALL Select 'Total', Sum(N.F1), sum(N.L1), sum(N.T1), sum(N.F2), sum(N.L2), sum(N.T2), sum(N.F3), sum(N.L3), sum(N.T3) from nV N";
            //System.out.println(sql);
            List<Object[]> tmp = session.createSQLQuery(sql).list();
            for (Object[] tmpl : tmp) {
                if (tmpl[0].equals("Total")) {
                    htmltable += "<tr style='background-color:#eee;font-weight:bold;'><td colspan='2'>Total</td><td style='display:none'></td>";
                    for (int i = 1; i < tmpl.length; i++) {
                        htmltable += "<td style='text-align:right;font-weight:bold;'>" + String.format("%,.2f", tmpl[i]) + "</td>";
                    }
                } else {
                    htmltable += "<tr><td>" + j++ + "</td>";
                    for (int i = 0; i < tmpl.length; i++) {
                        if (i > 0) {
                            if(tmpl[i]==null) {
                                tmpl[i] = 0.0;
                            }
                            htmltable += "<td style='text-align:right;'>" + ConvertCurrency(tmpl[i]) + "</td>";
                        } else {
                            htmltable += "<td style='font-weight:bold;'>" + tmpl[i] + "</td>";
                        }
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
    }*/

    protected String RunClientSQL(String strSQL) {
        String html = null;
        if (strSQL.toLowerCase().contains(";")) {
            return "Invalid Semicolon detected /n Query Halted";
        }
        if (strSQL.toLowerCase().contains("insert")) {
            return "Invalid INSERT Statement detected /n Query Halted";
        }
        if (strSQL.toLowerCase().contains("update")) {
            return "Invalid SELECT Statement detected /n Query Halted";
        }
        if (strSQL.toLowerCase().contains("delete")) {
            return "Invalid DELETE Statement detected /n Query Halted";
        }
        html = Query2Table(strSQL);
        return html;
    }

    protected String Query2Table(String sql) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String html = null;
        String tmp = null;
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createSQLQuery(sql);
        String table = "";
        Query Q = q;
        table += "</tr></thead><tbody>";
        List<Object[]> entities = q.list();
        for (Object[] entity : entities) {

            table += "<tr>";
            for (Object entityCol : entity) {
                table += "<td>" + entityCol.toString() + "</td>";
            }
            table += "</tr>";
        }
        table += "</tbody></table>";

        Q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> head = Q.list();

        String table1 = "<table id='reptable' class='table table-bordered table-striped'><thead><tr>";

        for (Map.Entry<String, Object> entry : head.get(0).entrySet()) {
            tmp = entry.getKey();
            tmp = tmp.replaceAll("([^_])([A-Z])", "$1 $2");
            table1 += "<th>" + tmp + "</th>";
        }
        //session.getTransaction().commit();
        html = "<div style='text-align: center;'> " + Utility.ONDOSTATEOFNIGERIA + "<br>" + "REPORT :  ...</div>" + table1 + table;
        return html;
    }

    String VQueries(Integer year, String segment, String code, String _budget_type, String[] versions) {

        String budget_type_condition_actual = "";
        String budget_type_condition_budget = "";
        if (_budget_type.equals("1")) {
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (1,4) ";
        } else if (_budget_type.equals("2")) {
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (2,3) ";
        }
        String sql;
        sql = "select a.code, a.name, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,1)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[0] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt1, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,1)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[1] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt2, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,1)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[2] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt3, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,1)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[3] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt4, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,1)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[4] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt5 "
                + " from Economic_Segment_Header1 a "
                + " UNION "
                + " select a.code, a.name, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,2)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[0] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt1, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,2)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[1] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt2, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,2)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[2] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt3, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,2)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[3] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt4, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,2)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[4] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt5 "
                + " from Economic_Segment_Header2 a "
                + " UNION "
                + " select a.code, a.name, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,4)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[0] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt1, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,4)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[1] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt2, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,4)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[2] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt3, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,4)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[3] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt4, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,4)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[4] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt5 "
                + " from Economic_Segment_Header3 a "
                + " UNION "
                + " select a.code, a.name, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,6)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[0] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt1, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,6)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[1] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt2, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,6)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[2] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt3, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,6)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[3] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt4, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,6)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[4] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt5 "
                + " from Economic_Segment_Header4 a "
                + " UNION "
                + " select a.code, a.name, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,8)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[0] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt1, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,8)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[1] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt2, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,8)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[2] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt3, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,8)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[3] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt4, "
                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_year_id=" + (year) + " and left(c.economic_segment,8)=a.code and M.sub_sector_code<>'00' " + budget_type_condition_budget + " and c.budget_version_id=" + versions[4] + "  and c.sap_budget_type='" + ActiveBudgetType + "') as amt5 "
                + " from Economic_Segment a";
        System.out.println("sql::: "+sql);
        return sql;
    }

    String Queries(Integer year, String segment, String code, String _budget_type, String _ActiveVersion) {
        //System.out.println("Queries   year::: "+year);
        String budget_type_condition_actual="";
        String budget_type_condition_budget="";
        if(_budget_type.equals("1")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (1,4) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (1,4) ";
        }else if(_budget_type.equals("2")){
            budget_type_condition_actual = " and left(b.CmmtItem,1) in (2,3) ";
            budget_type_condition_budget = " and left(c.economic_segment,1) in (2,3) ";
        }
        String sqlq = null;
        if (null != segment) {
            switch (segment) {
                case "Economic Segment":
                    if (!"ALL".equals(code)) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header1 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header2 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header3 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header4 a where a.code LIKE'" + code + "%'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,8)=a.code "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment a  where a.code LIKE'" + code + "%'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,1)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header3 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment_Header4 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.CmmtItem,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.CmmtItem,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.economic_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Economic_Segment a";
                        return sqlq;
                    }
                case "Functional Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Functional_Segment_Header1 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Functional_Segment_Header2 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Functional_Segment a where left(a.code,3)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.functional_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Functional_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.functional_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Functional_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FuncArea,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.functional_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Functional_Segment a";
                        return sqlq;
                    }
                case "Fund Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.fund_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Fund_Segment_Header1 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Fund_Segment_Header2 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Fund_Segment a where left(a.code,2)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,23)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.fund_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Fund_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.fund_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Fund_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.Fund,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.fund_segment,5)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Fund_Segment a";
                        return sqlq;
                    }
                case "Geographic Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment_Header1 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment_Header2 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment_Header3 a where left(a.code,3)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment a where left(a.code,3)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,3)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,4)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,6)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment_Header3 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.BusArea,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.geo_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Geographic_Segment a";
                        return sqlq;
                    }
                case "Programme Segment":
                    if (!"all".equals(code.toLowerCase())) {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Programme_Segment_Header1 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Programme_Segment_Header2 a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Project_Detail a where left(a.code,2)='" + code + "'"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Programme_Segment a where left(a.code,2)='" + code + "'";
                        return sqlq;
                    } else {
                        sqlq = "select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,2)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Programme_Segment_Header1 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,8)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Programme_Segment_Header2 a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,10)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Project_Detail a"
                                + " UNION"
                                + " select a.code, a.name, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 2) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt1, "
                                + " (select COALESCE(sum(b.AmountLc),0) from SAP_Actuals b INNER JOIN MDAs M ON M.administrative_segment=b.FundsCtr  where b.FiscYear=" + (year - 1) + " and left(b.FundedProg,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_actual+") as amt2, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + (year - 1) + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt3, "
                                + " (select COALESCE(sum(c.budget_amount),0) from Year_Budget c INNER JOIN MDAs M ON M.administrative_segment=c.admin_segment where c.budget_version_id='"+_ActiveVersion+"' and c.budget_year_id=" + year + " and left(c.programme_segment,12)=a.code AND M.sub_sector_code<>'00' "+budget_type_condition_budget+") as amt4"
                                + " from Programme_Segment a";
                        return sqlq;
                    }
                default:
                    break;
            }
        }

        return sqlq;
    }

    String Titles(Integer idx, String constStrings, Integer year, String _budget_type, String _ActiveVersion) {
        String budget_type="";
        if(_budget_type.equals("1")){
            budget_type=" (REVENUE)";
        }else if(_budget_type.equals("2")){
            budget_type=" (EXPENDITURE)";
        }
        String[] RepTitle = new String[30];
        String[] consts = constStrings.split("\\,");
        if (null != idx) {
            switch (idx) {
                case 1:
                    return "ALLOCATIONS TO MDAs BY " + consts[1].toUpperCase() + " SECTOR "+budget_type+"</br>" + year + " BUDGET";
                case 2:
                    return "ALLOCATIONS TO MDAs BY " + consts[1].toUpperCase() + " "+budget_type+"</br>" + year + " BUDGET";
                case 3:
                    return RepTitle[3] = "FIRST SCHEDULE"+budget_type+"</br>YEAR " + year + " APPROVED RECURRENT ESTIMATES</br>ALLOCATION OF FUNDS TO MINISTRIES/ DEPARTMENTS/ AGENCIES IN THE STATE";
                case 4:
                    return "SECOND SCHEDULE"+budget_type+"</br> YEAR " + year + " CAPITAL ESTIMATES</br>ALLOCATION TO ARMS/MINISTRIES/ DEPARTMENTS/ AGENCIES";
                case 5:
                    return "SUMMARY OF APPROVED RECURRENT AND CAPITAL ESTIMATES"+budget_type+" " + year;
                case 6:
                    return "YEAR " + year + " APPROVED ESTIMATES"+budget_type+"</br>SUMMARY OF REVENUE BY ECONOMIC SEGMENT " + year;
                case 7:
                    return "YEAR " + year + " APPROVED ESTIMATES"+budget_type+"</br>DETAILS OF REVENUE BY ECONOMIC SEGMENT " + year;
                case 8:
                    return "Summary of Total Revenue Based on Sector by Independent Revenue. ".toUpperCase() + year;
                case 9:
                    return "Revenue Details<br> Revenue Estimates ".toUpperCase() + year;
                case 10:
                    return "YEAR " + year + " APPROVED ESTIMATES"+budget_type+"</br>SUMMARY OF RECUURENT ESTIMATES";
                case 11:
                    return "ESTIMATES " + year + "<br>GRANTS TO PARASTATALS / TERTIARY INSTITUTIONS "+budget_type;
                case 15:
                case 12:
                    return "ESTIMATES " + year + "<br>RECURRENT EXPENDITURE";
                case 13:
                    return "CONSOLIDATED REVENUE FUNDS CHARGES " + year;
                case 14:
                    return "ESTIMATES " + year + "<br>GRANTS AND LOANS"+budget_type;
                case 16:
                    return "<br>"+budget_type+" SECTORAL SUMMARY OF " + year + " BUDGET";
                case 17:
                    return year + "ALLOCATION OF FUNDS TO MDAs IN THE STATE"+budget_type;
                case 18:
                    return "CONSOLIDATED BUDGET SUMMARY (MASTER BUDGET) " + year + "<br>"+budget_type+" Summary of Capital Receipts".toUpperCase();
                case 19:
                    return "CONSOLIDATED BUDGET SUMMARY (MASTER BUDGET) " + year + "<br>"+budget_type+" BASED ON SECTORS".toUpperCase();
                case 21:
                    return "APPROVED CAPITAL ESTIMATES <br/>"+budget_type+" PROJECT DETAILS ".toUpperCase() + year;
                case 20:
                    return budget_type+" SPECIAL PROGRAMME ".toUpperCase() + year;
                case 23:
                    return "<br>"+budget_type+" DETAILS OF CAPITAL PROJECT BUDGET UNDER PROGRAMME" + year;
                case 22:
                    return ""+budget_type+" SUMMARY OF TOTAL CAPITAL BUDGET BASED ON SECTORS ".toUpperCase() + year;
                case 26:
                    return budget_type+" Summary of Capital Budget By functions (COFOG) ".toUpperCase() + year;
                case 24:
                    return year + " capital budget<br>"+budget_type+" allocations to ministries, departments and agencies".toUpperCase();
                case 25:
                    return budget_type+" Summary of Capital Budget By Programme ".toUpperCase() + year;
                case 27:
                    return "YEAR " + year + " APPROVED ESTIMATES</br>"+budget_type+" SUMMARY OF REVENUE BY " + consts[0].toUpperCase();
                case 28:
                    return budget_type+" Summary of Capital Budget By Policy ".toUpperCase() + year;
                case 33:
                    return "<br>"+budget_type+"CAPITAL EXPENDITURE B ECONOMIC SEGMENT " + year;
                case 40:
                    return ""+budget_type+" SUMMARY OF APPROVED PERSONNEL COST ".toUpperCase() + year;
                case 41:
                    return ("Estimates, " + year + "<br>RECURRENT EXPENDITURE ").toUpperCase();
                case 42:
                    return ("Estimates, " + year + "<br>"+budget_type+" OVERALL SUMMARY OF PERSONNEL COST ").toUpperCase();
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

    String GetBudgetType(String budgetType) {
        String dummy = "NGN";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("select a.sap_budget_type from Year_Budget_Types as a where a.id=" + budgetType);
            List symbol = q.list();
            dummy = symbol.get(0).toString();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return dummy;
    }

    String GetCurrencySymbol(int ActiveCurrency) {
        String dummy = "NGN";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("select a.currency_symbol from Currencies as a where a.id=" + ActiveCurrency);
            List symbol = q.list();
            dummy = symbol.get(0).toString();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return dummy;
    }

    float GetCurrencyExchangeRate(int ActiveCurrency) {
        float dummy = 1f;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("select a.currency_rate from Currencies as a where a.id=" + ActiveCurrency);
            List exchange = q.list();
            String _dummy = exchange.get(0).toString();
            if ("".equals(_dummy)) {
                dummy = 1f;
            } else {
                dummy = Float.parseFloat(_dummy);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return dummy;
    }

    String ConvertCurrency(Object value) {
        if (value == null) {
            value = "0";
        }
        String _value = value.toString();
        float amount = Float.parseFloat(_value);
        amount = amount / ExchangeRate;
        return String.format("%,.2f", amount);
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
