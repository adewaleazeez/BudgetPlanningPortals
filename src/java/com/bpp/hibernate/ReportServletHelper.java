/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author OLAWUMMI
 */
public class ReportServletHelper {

    public synchronized String insert(BudgetReport budgetreport) {
        BudgetReport thisReport = exists(budgetreport.getId());
        if (thisReport == null) {
            budgetreport.setId(Utility.getMaxserialNo("Budget_Report")+1);
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(budgetreport);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return "";
            } finally {
                //session.close();
            }
            System.out.println("thisReport.getId()  " + budgetreport.getId());
            return Utility.ActionResponse.INSERTED.toString();
        } else {
            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String update(BudgetReport budgetreport) {

        BudgetReport thisReport = exists(budgetreport.getId());
        if (thisReport != null) {
            if (budgetreport.getReportDate() == null) {
                if (thisReport.getReportDate() == null) {
                    Date date = new Date();
                    budgetreport.setReportDate(date);
                } else {
                    budgetreport.setReportDate(thisReport.getReportDate());
                }
            }

            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.merge(budgetreport);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return "";
            } finally {
                //session.close();
            }

            return Utility.ActionResponse.UPDATED.toString();
        } else {
            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String delete(BudgetReport budgetreport) {
        BudgetReport thisReport = exists(budgetreport.getId());
        if (thisReport != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(thisReport);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return "";
            } finally {
            }
            return Utility.ActionResponse.DELETED.toString();
        } else {
            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized BudgetReport exists(String title) {
        BudgetReport thisReport = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from PrBudgetReport as a where a.ReportTitle='" + title + "'");
            thisReport = (BudgetReport) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return thisReport;
    }

    public synchronized BudgetReport exists(int id) {
        BudgetReport thisReport = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from BudgetReport as a where a.Id=" + id);
            thisReport = (BudgetReport) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return thisReport;
    }

    public synchronized String getMaxSerialNo(String tablename) {
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select max(id) as idx from " + tablename;
            SQLQuery q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            recordlist = q.list();
            HashMap hmap = (HashMap) recordlist.get(0);

            if (hmap.get("maxserialno") == null) {
                resp = "0";
            } else {
                resp = hmap.get("maxserialno").toString();
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
        return resp;
    }

    public synchronized String fetchAll() {
        List BudgetReportList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from BudgetReport";
            Query q = session.createQuery(sql);
            BudgetReportList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(BudgetReportList);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return jsonList;
    }

    public synchronized String fetchAll0(int year) {
        List BudgetReportList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from BudgetReport a where a.BudgetYear=" + year;
            Query q = session.createQuery(sql);
            BudgetReportList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(BudgetReportList);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return jsonList;
    }

    public synchronized List<BudgetReport> fetchAll(int year) {
        List<BudgetReport> BudgetReportList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from BudgetReport a where a.BudgetYear=" + year;
            Query q = session.createQuery(sql);
            BudgetReportList = (List<BudgetReport>) q.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return BudgetReportList;
    }

    public synchronized String fetch(String id) {
        BudgetReport budgetreport = null;
        String jsonList = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from BudgetReport as a where a.id='" + id + "'");
            budgetreport = (BudgetReport) q.uniqueResult();
            Gson gson = new Gson();
            jsonList = gson.toJson(budgetreport);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return jsonList;
    }

    public synchronized String fetchBudgetYears() {
        //BudgetYears by ;
        BudgetYearHibernateHelper byh = new BudgetYearHibernateHelper();
        String budgetyears = byh.fetchAll();
        return budgetyears;
    }

    public synchronized String fetchYearBudgetVersion() {
        // YearBudgetVersions  bdgtversion  = null;
        String jsonList = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("select * from Year_Budget_Versions");
            List bdgtversion = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(bdgtversion);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return jsonList;
    }
 public synchronized String fetchYearBudgetVersionName(String Idx) {
        // YearBudgetVersions  bdgtversion  = null;
        String response = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("select ybv.year_budget_version from Year_Budget_Versions ybv where ybv.id="+Idx);
            List bdgtversion = q.list();
            response=bdgtversion.get(0).toString();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return response;
    }
    public synchronized String fetchCurrencies() {
        // YearBudgetVersions  bdgtversion  = null;
        String jsonList = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("select * from Currencies");
            List bdgtversion = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(bdgtversion);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return jsonList;
    }
    public synchronized String fetchBudgetType() {
        // YearBudgetVersions  bdgtversion  = null;
        String jsonList = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("select * from Year_Budget_Types");
            List budgettypes = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(budgettypes);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return jsonList;
    }
}
