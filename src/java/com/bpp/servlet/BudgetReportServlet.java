/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.EconomicSegment;
import com.bpp.hibernate.EconomicSegmentHeader1;
import com.bpp.hibernate.EconomicSegmentHeader1HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHeader2;
import com.bpp.hibernate.EconomicSegmentHeader2HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHeader3;
import com.bpp.hibernate.EconomicSegmentHeader3HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHeader4;
import com.bpp.hibernate.EconomicSegmentHeader4HibernateHelper;
import com.bpp.hibernate.EconomicSegmentHibernateHelper;
import com.bpp.hibernate.HibernateUtil;
import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
import com.bpp.hibernate.SectorHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Ola
 */
public class BudgetReportServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String[] sql = new String[30];
    String H1;
    String H2[] = new String[30];
    String TableHead[] = new String[30];
    String H3[] = new String[30];
    String head = null;
    String budget_year;
    String EH1ReportItem;
    MdaHibernateHelper MdaHelper = new MdaHibernateHelper();
    EconomicSegmentHeader4HibernateHelper EH4 = new EconomicSegmentHeader4HibernateHelper();
    EconomicSegmentHeader3HibernateHelper EH3 = new EconomicSegmentHeader3HibernateHelper();
    EconomicSegmentHeader2HibernateHelper EH2 = new EconomicSegmentHeader2HibernateHelper();
    EconomicSegmentHeader1HibernateHelper EH1 = new EconomicSegmentHeader1HibernateHelper();
    EconomicSegmentHibernateHelper EH = new EconomicSegmentHibernateHelper();
    SectorHibernateHelper SH = new SectorHibernateHelper();
    Integer year = 2017;
    int EH1Item = 0;

    protected void SetValues() {
        sql[0] = "SELECT project_code as ProjectCode, b.[year] as ProjectYear, score as ProjectScore FROM Scoring AS p INNER JOIN dbo.Budget_Years AS b ON (p.project_year = b.id)";//SELECT project_code as ProjectCode, project_year as ProjectYear, score as ProjectScore from Scoring";
        H1 = "ONDO STATE OF NIGERIA";
        H2[0] = "CONSOLIDATED BUDGET SUMMARY (MASTER BUDGET)";
        H3[0] = "BASED ON Sector";
        H2[25] = "";
        H3[25] = "";
        //String[] TableHead = {"Sn", "Objective Code", "MDA", "Objective"};
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, MessagingException {
        SetValues();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final Session _session = HibernateUtil.getSessionFactory().getCurrentSession();
        List lst;
        String html = null;
        String resp = null;
        String tmp = "";
        Transaction tx = null;
        head = request.getParameter("Heading");
        budget_year = request.getParameter("budget_year");
        EH1ReportItem = request.getParameter("EH1ReportItem");
        if (EH1ReportItem == null) {
            EH1Item = 0;
        } else {
            EH1Item = Integer.parseInt(EH1ReportItem);
        }

        HttpSession session = request.getSession(true);
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));
            
            String option;
            int opt = 0;
            int opt1 = 0;
            option = request.getParameter("option1");
            if (option == null) {
                opt = 0;
            } else {
                opt = Integer.parseInt(option);
            }
            if (opt == -2) {
                opt1 = 25;

            }

            if (opt == -2) {
                //Custom Report
                String str = request.getParameter("strSQ");
                html = RunClientSQL(str, opt1, _session);
            } else if (opt == 3 || opt == 4) {
                //Segment Reports
                int seg = 0;
                if (opt == 3) {
                    seg = 0;
                } else if (opt == 4) {
                    seg = EH1Item;
                }

                html = DoBudgetBook(head, seg);
            } else { //Report Type1
                tx = _session.beginTransaction();
                Query q = _session.createSQLQuery(sql[opt]);

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
                List<Object[]> list = (List<Object[]>) Q.list();

                String table1 = "<table class='table table-bordered table-striped'><thead><tr>";

                for (Map.Entry<String, Object> entry : head.get(0).entrySet()) {
                    tmp = entry.getKey();
                    tmp = tmp.replaceAll("([^_])([A-Z])", "$1 $2");
                    table1 += "<th>" + tmp + "</th>";
                }
                //_session.getTransaction().commit();
                html = "<div style='text-align: center; line-height: .91'> <h1>" + H1 + "</h1><h2>" + H2[opt] + "</h2><h2>" + H3[opt] + "</h2></div>" + table1 + table;
            }
            resp = html;
            //         tx.commit();

            out.println(resp);
        } finally {
            // _session.close();
            out.close();
        }
    }

    protected String RunClientSQL(String strSQL, int opt, Session _session) {
        String html = null;
        //strSQL = strSQL.replaceAll("[^a-zA-Z0-9 .,]|(?<!\\d)[.,]|[.,](?!\\d)", "");
        //Ensure no Insert/Delete65
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
        html = Query2Table(strSQL, opt, _session);
        return html;
    }

    protected String Query2Table(String sql, int opt, Session _session) {
        // final Session _session = HibernateUtil.getSessionFactory().getCurrentSession();
        String html = null;
        String tmp = null;
        Transaction tx = null;
        tx = _session.beginTransaction();
        Query q = _session.createSQLQuery(sql);

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
        List<Object[]> list = (List<Object[]>) Q.list();

        String table1 = "<table class='table table-bordered table-striped'><thead><tr>";

        for (Map.Entry<String, Object> entry : head.get(0).entrySet()) {
            tmp = entry.getKey();
            tmp = tmp.replaceAll("([^_])([A-Z])", "$1 $2");
            table1 += "<th>" + tmp + "</th>";
        }
        //_session.getTransaction().commit();
        html = "<div style='text-align: center; line-height: .91'> <h1>" + H1 + "</h1><h2>" + H2[opt] + "</h2><h2>" + H3[opt] + "</h2></div>" + table1 + table;
        return html;
    }

    protected String DoBudgetBook(String Heading, int segment) {
        //Sellect all items of Heading
        String html = "<table><thead><tr><td>Code</td><td>Revenue Details</td><td>Actual Revenue Jan - Dec " + (year - 2) + "</td><td>Actual Revenue Jan - Dec " + (year - 1) + "</td><td>Approved Estimates " + (year - 1) + "</td><td>Approved Estimates " + year + "</td></thead></tr>";
        double Est0 = 0.00;
        double Est1 = 0.00;

        if (null != Heading) {
            switch (Heading) {
                case "Sectors":
                    //Report by Sectors
                    
                    break;
                case "ProgrammeSegment":
                    break;
                case "AdministrativeSegment":
                    List<Mdas> mda = new ArrayList<>();
                    String mdastr = MdaHelper.fetchAll();
                    JSONArray mdaArray;
                    try {
                        mdaArray = new JSONArray(mdastr);
                        int count = mdaArray.length();
                        for (int i = 0; i < count; i++) {
                            System.out.println(mdaArray.getJSONArray(i).getString(1));
                        }
                        System.out.println(mdaArray);

                    } catch (JSONException ex) {
                        Logger.getLogger(BudgetReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case "GeographicSegment":
                    break;
                case "EconomicSegment":
                    String dummy = "";
                    int byear = Integer.parseInt(budget_year);
                    double[] tempsum = null;
                    List esh1 = EH1.fetchAllAsList(segment);   //Fetch All H1                    
                    for (int i = 0; i < esh1.size(); i++) {
                        EconomicSegmentHeader1 E1 = (EconomicSegmentHeader1) esh1.get(i);
                        dummy = E1.getCode();//H1 Code 1-digit
                        tempsum = EH.GetEstimate(byear, dummy);
                        // Est1 =tempsum[1];// EH.GetEstimate(byear, dummy); //Sum for all H1 in the year_budget current year
                        html += "<tr bgcolor=' #ffd699'><b><td>" + dummy + "</td><td>" + E1.getName() + "</td><td align='right'>" + String.format("%,.2f", tempsum[2]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[3]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[0]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[1]) + "</td></b></tr>";

                        List esh2 = EH2.fetchAllAsList(dummy);    //Fetch All H2 filtered by H1 code
                        for (int j = 0; j < esh2.size(); j++) {
                            EconomicSegmentHeader2 E2 = (EconomicSegmentHeader2) esh2.get(j);
                            dummy = E2.getCode();//2 -digit Code
                            tempsum = EH.GetEstimate(byear, dummy);

                            html += "<tr bgcolor='#ffe0b3'><td>" + dummy + "</td><td>" + E2.getName() + "</td><td align='right'>" + String.format("%,.2f", tempsum[2]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[3]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[0]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[1]) + "</td></tr>";

                            List esh3 = EH3.fetchAllAsList(dummy);
                            for (int k = 0; k < esh3.size(); k++) {
                                EconomicSegmentHeader3 E3 = (EconomicSegmentHeader3) esh3.get(k);
                                dummy = E3.getCode();// H3 - 4-digit Code
                                tempsum = EH.GetEstimate(byear, dummy);

                                html += "<tr bgcolor='#ffebcc'><td>" + dummy + "</td><td>" + E3.getName() + "</td><td align='right'>" + String.format("%,.2f", tempsum[2]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[3]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[0]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[1]) + "</td></tr>";

                                List esh4 = EH4.fetchAllAsList(dummy);
                                for (int l = 0; l < esh4.size(); l++) {
                                    EconomicSegmentHeader4 E4 = (EconomicSegmentHeader4) esh4.get(l);
                                    dummy = E4.getCode();// H4 - 6-digit Code
                                    tempsum = EH.GetEstimate(byear, dummy);
                                    html += "<tr bgcolor='#fff5e6'><td>" + dummy + "</td><td>" + E4.getName() + "</td><td align='right'>" + String.format("%,.2f", tempsum[2]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[3]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[0]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[1]) + "</td></tr>";

                                    List esh = EH.fetchAllAsList(dummy);  //List all line items
                                    for (int q = 0; q < esh.size(); q++) {
                                        EconomicSegment E = (EconomicSegment) esh.get(q);
                                        dummy = E.getCode();//
                                        tempsum = EH.GetValue(byear, dummy);
                                        html += "<tr><td>&nbsp;&nbsp;" + dummy + "</td><td>" + E.getName() + "</td><td align='right'>" + String.format("%,.2f", tempsum[0]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[1]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[2]) + "</td><td align='right'>" + String.format("%,.2f", tempsum[3]) + "</td></tr>";
                                    }
                                }
                            }
                        }
                    }
                    html += "</table>";
                    break;
                default:
                    break;
            }
        }
        return html;
    }
//    public synchronized double[] GetEstimate_mda(Integer year, String code, String table, String Table_SAP) {
//        double[] bdgValue = null;
//        final Session _session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = null;
//        try {
//            tx = _session.beginTransaction();
//            String sql = "select coalesce(sum(Year_Budget.budget_amount),0) as ss, 'A'  from Year_Budget  where TableName like '" + code + "%' and budget_year_id = " + (year - 1)
//                    + " UNION select coalesce(sum(Year_Budget.budget_amount),0) as ss,'B'  from Year_Budget  where TableName like '" + code + "%' and budget_year_id = " + (year)
//                    + " group by budget_year_id UNION"
//                    + " select coalesce(sum(AmountLc),0) as ss, 'C' from SAP_Actuals  where Table_SAP like '" + code + "%' and FiscYear = " + (year - 2)
//                    + " UNION select coalesce(sum(AmountLc),0) as ss,'D' from SAP_Actuals  where Table_SAP like '" + code + "%' and FiscYear = " + (year-1)
//                    + " group by FiscYear";
//            sql=sql.replaceAll("table", table);
//            sql=sql.replaceAll("Table_SAP", Table_SAP);
//            bdgValue = new double[4];
//            int start = sql.length();
//            if (start > 0) {
//                List<Object[]> tmp = _session.createSQLQuery(sql).list();
//                int i = 0;
//                for (Object[] tmp1 : tmp) {
//                    i = Arrays.asList(tmp1).indexOf("A");
//                    if (i == 1) {
//                        bdgValue[0] = (double) tmp1[0];
//                    }
//                    i = Arrays.asList(tmp1).indexOf("B");
//                    if (i == 1) {
//                        bdgValue[1] = (double) tmp1[0];
//                    }
//                    i = Arrays.asList(tmp1).indexOf("C");
//                    if (i == 1) {
//                        bdgValue[2] = (double) tmp1[0];
//                    }
//                    i = Arrays.asList(tmp1).indexOf("D");
//                    if (i == 1) {
//                        bdgValue[3] = (double) tmp1[0];
//                    }
//                }
//            }
//
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            //_session.close();
//        }
//
//        return bdgValue;
//    }
  
    
    public synchronized String readCookie(HttpServletRequest request, String cookiename) {
        Cookie[] cookies = request.getCookies();
        String resp = "";
        for (Cookie c : cookies) {
            if (c.getName().equals(cookiename)) {
                resp = c.getValue();
            }
        }
        return resp;
    }

    public synchronized void createCookie(HttpServletResponse response, String cookiename, String cookievalue) {
        Cookie c = new Cookie(cookiename, cookievalue);
        c.setMaxAge(24 * 60 * 60);
        response.addCookie(c);
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
            Logger.getLogger(BudgetReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(BudgetReportServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BudgetReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(BudgetReportServlet.class.getName()).log(Level.SEVERE, null, ex);
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
