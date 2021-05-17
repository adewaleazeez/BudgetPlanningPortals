/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

/**
 *
 * @author Adewale
 */
public class YearBudgetHibernateHelper {

    public static final String TABLE_NAME = "YearBudget";
    public static final String RAW_TABLE_NAME = "Year_Budget";

    /* YearBudget methods begin */
    public synchronized String insert(YearBudget yearBudget) {
//YearBudget checkRole = exists(yearBudget.getAdminSegment(), yearBudget.getEconomicSegment());
        YearBudget checkRole = exists(yearBudget.getAdminSegment(), yearBudget.getEconomicSegment(), yearBudget.getProgrammeSegment(),
                yearBudget.getFunctionalSegment(), yearBudget.getFundSegment(), yearBudget.getGeoSegment(), yearBudget.getBudgetYearId());

        if (checkRole == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
//session.beginTransaction();
                session.save(yearBudget);
//session.getTransaction().commit();

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
            return Utility.ActionResponse.INSERTED.toString();
        } else {
            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String update(YearBudget yearBudget) {
        YearBudget checkRole = exists(yearBudget.getId());
        if (checkRole != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
//session.beginTransaction();
                session.merge(yearBudget);
//session.getTransaction().commit();

                tx.commit();
            } catch (HibernateException e) {
                System.out.println("error: " + e.getMessage());
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

            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(YearBudget yearBudget) {
        YearBudget checkRole = exists(yearBudget.getId());
        if (checkRole != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
//session.beginTransaction();
                session.delete(yearBudget);
//session.getTransaction().commit();

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

            return Utility.ActionResponse.DELETED.toString();
        } else {

            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized YearBudget exists(String admin_segment, String economic_segment, String programme_segment, String functional_segment,
            String Fund_segment, String geo_segment, int budget_year_id) {
//public synchronized YearBudget exists(String admin_segment, String economic_segment) {
        YearBudget yearBudget = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from YearBudget as a where a.adminSegment='" + admin_segment + "' and a.economicSegment='" + economic_segment
                    + "' and a.programmeSegment='" + programme_segment + "' and a.functionalSegment='" + functional_segment
                    + "' and a.fundSegment='" + Fund_segment + "' and a.geoSegment='" + geo_segment
                    + "' and a.budgetYearId='" + budget_year_id + "'");
//q.setMaxResults(1);
            yearBudget = (YearBudget) q.uniqueResult();
//session.getTransaction().commit();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
//session.close();
        }
        return yearBudget;
    }

    public synchronized YearBudget exists(int id) {
        YearBudget btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            btt = (YearBudget) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
//session.close();
        }

        return btt;
    }

    public synchronized String getMaxSerialNo(String tablename) {
        tablename = "dbo." + tablename;
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select max(id) as maxserialno from " + tablename;
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

    public synchronized String getMaxSerialNo(String tablename, String code) {
        
//System.out.println("code "+code);
        tablename = "dbo." + tablename;
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select max(programme_segment) as maxserialno from " + tablename + " where programme_segment like '" + code + "%'";
//System.out.println("sql "+sql);
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
            switch (resp.length()) {
                case 1:
                    resp = code + "01";
                    break;
                case 12:
                    resp += "01";
                    break;
                default:
                    String resp_a = resp.substring(13);
                    int activity = Integer.parseInt(resp_a) + 1;
                    if (activity < 10) {
                        resp = code + "0" + activity;
                    } else {
                        resp = code + activity;
                    }
                    break;
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
//System.out.println("resp "+resp);
        if(code.length()==14){
            resp = code;
        }
        return resp;
    }

    public synchronized String fetchAdminSegmment(String component_id, String admin_segment) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select gl_account from Budget_Type_Components where id=" + component_id;

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();

            String gl_code = objList.get(0).toString();
            switch (gl_code.length()) {
                case 1:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header1 where code='" + gl_code + "'";
                    break;
                case 2:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header2 where code='" + gl_code + "'";
                    break;
                case 4:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header3 where code='" + gl_code + "'";
                    break;
                case 6:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header4 where code='" + gl_code + "'";
                    break;
                default:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment where code in (" + gl_code + ")";
                    break;
            }
//System.out.println("sql:::"+sql);

            q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchAdminSegmmentContingency(String component_id, String admin_segment) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select gl_account from Budget_Type_Components where id=" + component_id;

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();

            String gl_code = objList.get(0).toString();
            switch (gl_code.length()) {
                case 1:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Contingency where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header1 where code='" + gl_code + "'";
                    break;
                case 2:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Contingency where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header2 where code='" + gl_code + "'";
                    break;
                case 4:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Contingency where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header3 where code='" + gl_code + "'";
                    break;
                case 6:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Contingency where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header4 where code='" + gl_code + "'";
                    break;
                default:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Contingency where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment where code in (" + gl_code + ")";
                    break;
            }
//System.out.println("sql:::"+sql);

            q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchAdminSegmmentSupplementary(String component_id, String admin_segment) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select gl_account from Budget_Type_Components where id=" + component_id;

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();

            String gl_code = objList.get(0).toString();
            switch (gl_code.length()) {
                case 1:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Supplementary where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header1 where code='" + gl_code + "'";
                    break;
                case 2:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Supplementary where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header2 where code='" + gl_code + "'";
                    break;
                case 4:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Supplementary where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header3 where code='" + gl_code + "'";
                    break;
                case 6:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Supplementary where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment_Header4 where code='" + gl_code + "'";
                    break;
                default:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling_Supplementary where mda_id='" + admin_segment + "' "
                            + " and budget_type_component_id='" + component_id + "' and budget_year_id=(select id from Budget_Years "
                            + "where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + "as year_id from Economic_Segment where code in (" + gl_code + ")";
                    break;
            }
//System.out.println("sql:::"+sql);

            q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchEconSegmment(String econ_segment) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select c.code, concat(c.name, ' [', c.code, ']') from Economic_Segment c where c.code like '" + econ_segment + "%'";
            if (econ_segment.contains(",")) {
                sql = "select c.code, concat(c.name, ' [', c.code, ']') from Economic_Segment c where c.code in (" + econ_segment + ")";
            }
//System.out.println("sql " + sql);
            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchProgSegmment() {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select c.code, concat(c.name, ' [', c.code, ']') from Programme_Segment c ";

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchFuncSegmment() {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select c.code, concat(c.name, ' [', c.code, ']') from Functional_Segment c ";

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchFundSegmment() {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select c.code, concat(c.name, ' [', c.code, ']') from Fund_Segment c ";

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchGeoSegmment() {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select c.code, concat(c.name, ' [', c.code, ']') from Geographic_Segment c ";

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchDept(String mda_id) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select c.administrative_segment, concat(c.name, ' [', c.administrative_segment, ']') from Departments c where c.mda_id='" + mda_id + "'";

            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);

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

    public synchronized String fetchAll(String admin_segment, String economic_segment, String budget_year_id, String narration, String budget_type_id) {
//System.out.println("admin_segment "+admin_segment+" economic_segment "+economic_segment+" budget_year_id "+budget_year_id);
        List yearBudgetsList = null;
        String jsonList = "";
        YearBudgetHibernateHelper helper = new YearBudgetHibernateHelper();
        YearBudgetVersions bv = helper.fetchActiveBudgetVersions();

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//session.beginTransaction();
            tx = session.beginTransaction();
            if (budget_year_id.equals("")) {
                budget_year_id = "0";
            }
            if (!narration.equals("")) {
                narration = " and a.narration like '%"+narration+"%' ";
            }
            int year = Integer.parseInt(budget_year_id);
            String sql = "select (select iif((select b.id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                    + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                    + " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.id from Year_Budget d where d.budget_version_id =" + bv.getId() + " and a.admin_segment=d.admin_segment and a.economic_segment=d.economic_segment"
                    + " and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment and a.functional_segment=d.functional_segment"
                    + " and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.id from Year_Budget e where e.budget_version_id =" + bv.getId() + " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment"
                    + " and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment"
                    + " and c.code=e.economic_segment and e.budget_year_id=" + year + "))) as id, c.code, (select e.narration from Year_Budget e where e.budget_version_id =" + bv.getId() + " and a.admin_segment=e.admin_segment "
                    + " and a.economic_segment=e.economic_segment and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment and c.code=e.economic_segment "
                    + " and e.budget_year_id=" + year + ") as narration, "
                    + " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment "
                    + " and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment "
                    + " group by b.FundsCtr, b.CmmtItem),0)) as amount1, "
                    + " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment "
                    + " and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment "
                    + " group by b.FundsCtr, b.CmmtItem),0)) as amount2, "
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id =" + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                    + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                    + " and b.budget_year_id=" + (year - 1) + ") as amount3, "
                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id =" + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                    + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                    + " and b.budget_year_id=" + year + ") as amount4, (select iif((select b.dept_id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                    + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                    + " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.dept_id from Year_Budget d where d.budget_version_id =" + bv.getId() + " and a.admin_segment=d.admin_segment and a.economic_segment=d.economic_segment"
                    + " and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment and a.functional_segment=d.functional_segment"
                    + " and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.dept_id from Year_Budget e where e.budget_version_id =" + bv.getId() + " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment"
                    + " and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment"
                    + " and c.code=e.economic_segment and e.budget_year_id=" + year + "))) as dept_id FROM Economic_Segment c, Year_Budget a "
                    + " where a.budget_version_id =" + bv.getId() + " and c.code=a.economic_segment and a.admin_segment='" + admin_segment + "' and a.economic_segment like '" + economic_segment + "%'" + narration + " and a.sap_budget_type ='" + budget_type_id + "' "
                    + " group by c.name, c.code, a.admin_segment, a.programme_segment, a.economic_segment, a.functional_segment, a.fund_segment, a.geo_segment ";

//"select 1, c.code, c.name, " +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.CmmtItem and b.FundsCtr=a.FundsCtrand b.FundedProg=a.FundedProg " +
//" and b.fund=a.fund and b.BusArea=a.BusArea and b.FuncArea=a.FuncAreagroup by b.FundsCtr, b.CmmtItem),0)) as amount1, " +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.CmmtItem and b.FundsCtr=a.FundsCtrand b.FundedProg=a.FundedProg " +
//" and b.fund=a.fund and b.BusArea=a.BusArea and b.FuncArea=a.FuncAreagroup by b.FundsCtr, b.CmmtItem),0)) as amount2," +
//" 0 as amount3, 0 as amount4from SAP_Actuals a, Economic_Segment c where a.CmmtItem=c.code and a.CmmtItem like '" + economic_segment + "%' and a.FundsCtr='" + admin_segment + "' " +
//" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.CmmtItem=b.economic_segment and concat('0',a.FundsCtr)=b.admin_segment" +
//" and a.FundedProg=substring(b.programme_segment,1,12) and a.fund=b.fund_segment and a.BusArea=b.geo_segment and a.FuncArea=b.functional_segment and b.budget_year_id=" + (year - 1) + ")=0 " +
//" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.CmmtItem=b.economic_segment and concat('0',a.FundsCtr)=b.admin_segment" +
//" and a.FundedProg=substring(b.programme_segment,1,12) and a.fund=b.fund_segment and a.BusArea=b.geo_segment and a.FuncArea=b.functional_segment and b.budget_year_id=" + year + ")=0" +
//" group by a.FundsCtr, a.CmmtItem, a.FundedProg, a.fund, a.BusArea, a.FuncArea, c.code, c.name" +
//" UNION SELECT (select b.id from Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment and a.programme_segment=b.programme_segment " +
//" and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment and b.budget_year_id=" + (year - 1) + " and b.budget_version_id = "+bv.getId()+") as id, c.code, c.name," +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
//" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
//" group by b.FundsCtr, b.CmmtItem),0)) as amount1, " +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
//" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
//" group by b.FundsCtr, b.CmmtItem),0)) as amount2, " +
//" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
//" and b.budget_year_id=" + (year - 1) + ") as amount3,0 as amount4 FROM Year_Budget a, Economic_Segment c " +
//" where a.admin_segment='" + admin_segment + "' and a.economic_segment like '" + economic_segment + "%' and a.budget_version_id = "+bv.getId()+" and a.economic_segment=c.code " +
//" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
//" and b.budget_year_id=" + year + ")=0" +
//" group by a.admin_segment, a.economic_segment, a.programme_segment, a.fund_segment, a.geo_segment, a.functional_segment, c.code, c.name" +
//" UNION SELECT (select b.id from Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment and a.programme_segment=b.programme_segment " +
//" and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment and b.budget_year_id=" + year + " and b.budget_version_id = "+bv.getId()+") as id, c.code, c.name," +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
//" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
//" group by b.FundsCtr, b.CmmtItem),0)) as amount1, " +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
//" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
//" group by b.FundsCtr, b.CmmtItem),0)) as amount2, 0 as amount3," +
//" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
//" and b.budget_year_id=" + year + ") as amount4 FROM Year_Budget a, Economic_Segment c " +
//" where a.admin_segment='" + admin_segment + "' and a.economic_segment like '" + economic_segment + "%' and a.budget_version_id = "+bv.getId()+" and a.economic_segment=c.code " +
//" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
//" and b.budget_year_id=" + (year - 1) + ")=0" +
//" group by a.admin_segment, a.economic_segment, a.programme_segment, a.fund_segment, a.geo_segment, a.functional_segment, c.code, c.name" +
//" UNION select (select b.id from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
//" and c.code=b.economic_segment and b.budget_year_id=" + year + ") as id, c.code, c.name, " +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment" +
//" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment" +
//" group by b.FundsCtr, b.CmmtItem),0)) as amount1," +
//" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment" +
//" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment" +
//" group by b.FundsCtr, b.CmmtItem),0)) as amount2, " +
//" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
//" and b.budget_year_id=" + (year - 1) + ") as amount3, " +
//" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
//" and b.budget_year_id=" + year + ") as amount4 FROM Economic_Segment c, Year_Budget a " +
//" where a.budget_version_id = "+bv.getId()+" and c.code=a.economic_segment and a.admin_segment='" + admin_segment + "' and a.economic_segment like '" + economic_segment + "%'" +
//" and (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id ="+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
//" and b.budget_year_id=" + (year - 1) + ")>0" +
//" and (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id ="+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
//" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
//" and b.budget_year_id=" + year + ")>0" +
//" group by c.name, c.code, a.admin_segment, a.programme_segment, a.economic_segment, a.functional_segment, a.fund_segment, a.geo_segment ";
// sql = " select 1, c.code, c.name, "
//+ " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=c.code and b.FundsCtr='" + admin_segment + "'),0)) as amount1, "
//+ " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=c.code and b.FundsCtr='" + admin_segment + "'),0)) as amount2, 0 as amount3, 0 as amount4 "
//+ " from SAP_Actuals a, Economic_Segment c where a.CmmtItem=c.code and a.CmmtItem like '" + economic_segment + "%' and a.FundsCtr='" + admin_segment + "' "
//+ " and (select ISNULL((select sum(budget_amount) from Year_Budget where budget_version_id = "+bv.getId()+" and budget_year_id=" + (year - 1) + " and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%'),0))=0 "
//+ " and (select ISNULL((select sum(budget_amount) from Year_Budget where budget_version_id = "+bv.getId()+" and budget_year_id=" + year + " and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%'),0))=0 "
//+ " group by a.FundsCtr, a.CmmtItem, c.code, c.name "
//+ " UNION SELECT 1, c.code, c.name, "
//+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 2) + " and CmmtItem like '" + economic_segment + "%' and FundsCtr='" + admin_segment + "'),0)) as amount1, "
//+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 1) + " and CmmtItem like '" + economic_segment + "%' and FundsCtr='" + admin_segment + "'),0)) as amount2, "
//+ " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and b.admin_segment='" + admin_segment + "' "
//+ " and b.economic_segment like '" + economic_segment + "%' and b.budget_year_id=" + (year - 1) + ") as amount3, "
//+ " 0 as amount4 FROM Year_Budget a, Economic_Segment c where a.budget_version_id = "+bv.getId()+" and a.economic_segment=c.code and (select sum(b.budget_amount) FROM Year_Budget b where b.admin_segment='" + admin_segment + "' "
//+ " and b.economic_segment like '" + economic_segment + "%' and b.budget_year_id=" + year + ")=0 group by a.admin_segment, a.economic_segment, c.code, c.name "
//+ " UNION SELECT a.id, (select code from Economic_Segment where code=a.economic_segment) as code, "
//+ " (select name from Economic_Segment where code=a.economic_segment) as name, "
//+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 2) + " and CmmtItem like '" + economic_segment + "%' and FundsCtr='" + admin_segment + "'),0)) as amount1, "
//+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 1) + " and CmmtItem like '" + economic_segment + "%' and FundsCtr='" + admin_segment + "'),0)) as amount2, "
//+ " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%' and b.budget_year_id=" + (year - 1)
//+ " and a.admin_segment=b.admin_segment and a.programme_segment=b.programme_segment and a.economic_segment=b.economic_segment and a.functional_segment=b.functional_segment "
//+ " and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.dept_id=b.dept_id) as amount3, "
//+ " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%' and b.budget_year_id=" + year
//+ " and a.admin_segment=b.admin_segment and a.programme_segment=b.programme_segment and a.economic_segment=b.economic_segment and a.functional_segment=b.functional_segment "
//+ " and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.dept_id=b.dept_id) as amount4 "
//+ " FROM Year_Budget a wherea.budget_version_id = "+bv.getId()+" and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%' "
//+ " group by a.budget_year_id,a.id,a.admin_segment,a.programme_segment,a.economic_segment,a.functional_segment,a.fund_segment,a.geo_segment,a.dept_id ";
            if (economic_segment.contains(",")) {
                sql = "select (select iif((select b.id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                        + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                        + " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.id from Year_Budget d where d.budget_version_id =" + bv.getId() + " and a.admin_segment=d.admin_segment and a.economic_segment=d.economic_segment"
                        + " and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment and a.functional_segment=d.functional_segment"
                        + " and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.id from Year_Budget e where e.budget_version_id =" + bv.getId() + " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment"
                        + " and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment"
                        + " and c.code=e.economic_segment and e.budget_year_id=" + year + "))) as id, c.code, (select e.narration from Year_Budget e where e.budget_version_id =" + bv.getId() + " and a.admin_segment=e.admin_segment "
                        + " and a.economic_segment=e.economic_segment and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment and c.code=e.economic_segment "
                        + " and e.budget_year_id=" + year + ") as narration, "
                        + " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment "
                        + " and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment "
                        + " group by b.FundsCtr, b.CmmtItem),0)) as amount1, "
                        + " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment "
                        + " and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment "
                        + " group by b.FundsCtr, b.CmmtItem),0)) as amount2, "
                        + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id =" + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                        + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                        + " and b.budget_year_id=" + (year - 1) + ") as amount3, "
                        + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id =" + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                        + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                        + " and b.budget_year_id=" + year + ") as amount4, (select iif((select b.dept_id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment"
                        + " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment"
                        + " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.dept_id from Year_Budget d where d.budget_version_id =" + bv.getId() + " and a.admin_segment=d.admin_segment and a.economic_segment=d.economic_segment"
                        + " and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment and a.functional_segment=d.functional_segment"
                        + " and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.dept_id from Year_Budget e where e.budget_version_id =" + bv.getId() + " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment"
                        + " and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment"
                        + " and c.code=e.economic_segment and e.budget_year_id=" + year + "))) as dept_id FROM Economic_Segment c, Year_Budget a "
                        + " where a.budget_version_id =" + bv.getId() + " and c.code=a.economic_segment and a.admin_segment='" + admin_segment + "' and a.economic_segment in (" + economic_segment + ") "+ narration + " and a.sap_budget_type ='" + budget_type_id + "' "
                        + " group by c.name, c.code, a.admin_segment, a.programme_segment, a.economic_segment, a.functional_segment, a.fund_segment, a.geo_segment";

                //sql = "select 1, c.code, c.name, " +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.CmmtItem and b.FundsCtr=a.FundsCtrand b.FundedProg=a.FundedProg " +
                //" and b.fund=a.fund and b.BusArea=a.BusArea and b.FuncArea=a.FuncAreagroup by b.FundsCtr, b.CmmtItem),0)) as amount1, " +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.CmmtItem and b.FundsCtr=a.FundsCtrand b.FundedProg=a.FundedProg " +
                //" and b.fund=a.fund and b.BusArea=a.BusArea and b.FuncArea=a.FuncAreagroup by b.FundsCtr, b.CmmtItem),0)) as amount2," +
                //" 0 as amount3, 0 as amount4from SAP_Actuals a, Economic_Segment c where a.CmmtItem=c.code and a.CmmtItemin (" + economic_segment + ") and a.FundsCtr='" + admin_segment + "' " +
                //" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.CmmtItem=b.economic_segment and concat('0',a.FundsCtr)=b.admin_segment" +
                //" and a.FundedProg=substring(b.programme_segment,1,12) and a.fund=b.fund_segment and a.BusArea=b.geo_segment and a.FuncArea=b.functional_segment and b.budget_year_id=" + (year - 1) + ")=0 " +
                //" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.CmmtItem=b.economic_segment and concat('0',a.FundsCtr)=b.admin_segment" +
                //" and a.FundedProg=substring(b.programme_segment,1,12) and a.fund=b.fund_segment and a.BusArea=b.geo_segment and a.FuncArea=b.functional_segment and b.budget_year_id=" + year + ")=0" +
                //" group by a.FundsCtr, a.CmmtItem, a.FundedProg, a.fund, a.BusArea, a.FuncArea, c.code, c.name" +
                //" UNION SELECT (select b.id from Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment and a.programme_segment=b.programme_segment " +
                //" and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment and b.budget_year_id=" + (year - 1) + " and b.budget_version_id = "+bv.getId()+") as id, c.code, c.name," +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
                //" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
                //" group by b.FundsCtr, b.CmmtItem),0)) as amount1, " +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
                //" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
                //" group by b.FundsCtr, b.CmmtItem),0)) as amount2, " +
                //" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                //" and b.budget_year_id=" + (year - 1) + ") as amount3,0 as amount4 FROM Year_Budget a, Economic_Segment c " +
                //" where a.admin_segment='" + admin_segment + "' and a.economic_segment in (" + economic_segment + ") and a.budget_version_id = "+bv.getId()+" and a.economic_segment=c.code " +
                //" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                //" and b.budget_year_id=" + year + ")=0" +
                //" group by a.admin_segment, a.economic_segment, a.programme_segment, a.fund_segment, a.geo_segment, a.functional_segment, c.code, c.name" +
                //" UNION SELECT (select b.id from Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment and a.programme_segment=b.programme_segment " +
                //" and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment and b.budget_year_id=" + year + " and b.budget_version_id = "+bv.getId()+") as id, c.code, c.name," +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
                //" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
                //" group by b.FundsCtr, b.CmmtItem),0)) as amount1, " +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment " +
                //" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment " +
                //" group by b.FundsCtr, b.CmmtItem),0)) as amount2, 0 as amount3," +
                //" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                //" and b.budget_year_id=" + year + ") as amount4 FROM Year_Budget a, Economic_Segment c " +
                //" where a.admin_segment='" + admin_segment + "' and a.economic_segmentin (" + economic_segment + ") and a.budget_version_id = "+bv.getId()+" and a.economic_segment=c.code " +
                //" and (select ISNULL(sum(b.budget_amount),0) FROM Year_Budget b where a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                //" and b.budget_year_id=" + (year - 1) + ")=0" +
                //" group by a.admin_segment, a.economic_segment, a.programme_segment, a.fund_segment, a.geo_segment, a.functional_segment, c.code, c.name" +
                //" UNION select (select b.id from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                //" and c.code=b.economic_segment and b.budget_year_id=" + year + ") as id, c.code, c.name, " +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment" +
                //" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment" +
                //" group by b.FundsCtr, b.CmmtItem),0)) as amount1," +
                //" (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment" +
                //" and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment" +
                //" group by b.FundsCtr, b.CmmtItem),0)) as amount2, " +
                //" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
                //" and b.budget_year_id=" + (year - 1) + ") as amount3, " +
                //" (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
                //" and b.budget_year_id=" + year + ") as amount4 FROM Economic_Segment c, Year_Budget a " +
                //" where a.budget_version_id = "+bv.getId()+" and c.code=a.economic_segment and a.admin_segment='" + admin_segment + "' and a.economic_segment in (" + economic_segment + ") " +
                //" and (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id ="+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
                //" and b.budget_year_id=" + (year - 1) + ")>0" +
                //" and (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id ="+bv.getId()+" and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment" +
                //" and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment" +
                //" and b.budget_year_id=" + year + ")>0" +
                //" group by c.name, c.code, a.admin_segment, a.programme_segment, a.economic_segment, a.functional_segment, a.fund_segment, a.geo_segment ";
                //sql = " select 1, c.code, c.name, "
                //+ " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=c.code and b.FundsCtr='" + admin_segment + "'),0)) as amount1, "
                //+ " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=c.code and b.FundsCtr='" + admin_segment + "'),0)) as amount2, 0 as amount3, 0 as amount4 "
                //+ " from SAP_Actuals a, Economic_Segment c where a.CmmtItem=c.code and a.CmmtItem in (" + economic_segment + ") and a.FundsCtr='" + admin_segment + "' "
                //+ " and (select ISNULL((select sum(budget_amount) from Year_Budget where budget_version_id = "+bv.getId()+" and budget_year_id=" + (year - 1) + " and admin_segment='" + admin_segment + "' and economic_segment in (" + economic_segment + ")),0))=0 "
                //+ " and (select ISNULL((select sum(budget_amount) from Year_Budget where budget_version_id = "+bv.getId()+" and budget_year_id=" + year + " and admin_segment='" + admin_segment + "' and economic_segment in (" + economic_segment + ")),0))=0 "
                //+ " group by a.FundsCtr, a.CmmtItem, c.code, c.name "
                //+ " UNION SELECT 1, c.code, c.name, "
                //+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 2) + " and CmmtItem in (" + economic_segment + ") and FundsCtr='" + admin_segment + "'),0)) as amount1, "
                //+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 1) + " and CmmtItem in (" + economic_segment + ") and FundsCtr='" + admin_segment + "'),0)) as amount2, "
                //+ " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and b.admin_segment='" + admin_segment + "' "
                //+ " and b.economic_segment in (" + economic_segment + ") and b.budget_year_id=" + (year - 1) + ") as amount3, "
                //+ " 0 as amount4 FROM Year_Budget a, Economic_Segment c where a.budget_version_id = "+bv.getId()+" and a.economic_segment=c.code and (select sum(b.budget_amount) FROM Year_Budget b where b.admin_segment='" + admin_segment + "' "
                //+ " and b.economic_segment in (" + economic_segment + ") and b.budget_year_id=" + year + ")=0 group by a.admin_segment, a.economic_segment, c.code, c.name "
                //+ " UNION SELECT a.id, (select code from Economic_Segment where code=a.economic_segment) as code, "
                //+ " (select name from Economic_Segment where code=a.economic_segment) as name, "
                //+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 2) + " and CmmtItem in (" + economic_segment + ") and FundsCtr='" + admin_segment + "'),0)) as amount1, "
                //+ " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 1) + " and CmmtItem in (" + economic_segment + ") and FundsCtr='" + admin_segment + "'),0)) as amount2, "
                //+ " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and admin_segment='" + admin_segment + "' and economic_segment in (" + economic_segment + ") and b.budget_year_id=" + (year - 1)
                //+ " and a.admin_segment=b.admin_segment and a.programme_segment=b.programme_segment and a.economic_segment=b.economic_segment and a.functional_segment=b.functional_segment "
                //+ " and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.dept_id=b.dept_id) as amount3, "
                //+ " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = "+bv.getId()+" and admin_segment='" + admin_segment + "' and economic_segment in (" + economic_segment + ") and b.budget_year_id=" + year
                //+ " and a.admin_segment=b.admin_segment and a.programme_segment=b.programme_segment and a.economic_segment=b.economic_segment and a.functional_segment=b.functional_segment "
                //+ " and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.dept_id=b.dept_id) as amount4 "
                //+ " FROM Year_Budget a where a.budget_version_id = "+bv.getId()+" and admin_segment='" + admin_segment + "' and economic_segment in (" + economic_segment + ") "
                //+ " group by a.budget_year_id,a.id,a.admin_segment,a.programme_segment,a.economic_segment,a.functional_segment,a.fund_segment,a.geo_segment,a.dept_id ";
            }

            //System.out.println("sql: " + sql);
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
//Query q = session.createQuery("from YearBudget as a where a.name<>'' order by a.name");
//yearBudgetsList =q.list();
//session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchAllProgrammes(String admin_segment, String economic_segment, String budget_year_id, String narration, String budget_type_id) {
//System.out.println("admin_segment "+admin_segment+" economic_segment "+economic_segment+" budget_year_id "+budget_year_id);
        List yearBudgetsList = null;
        String jsonList = "";

        YearBudgetHibernateHelper helper = new YearBudgetHibernateHelper();
        YearBudgetVersions bv = helper.fetchActiveBudgetVersions();
        if (!narration.equals("")) {
            narration = " and a.narration like '%"+narration+"%' ";
        }
        if (budget_year_id.equals("")) {
            budget_year_id = "0";
        }
        int year = Integer.parseInt(budget_year_id);
        YearBudget last_version = helper.fetchLastBudgetVersions(year -1);
        
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
//final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//session.beginTransaction();
            tx = session.beginTransaction();
            
            String sql ="SELECT id, concat(policy,programme,code,objective) as code, name, 0 as amt1, 0 as amt2, 0 as amt3, 0 as amt4, year_id, rank, '' as c_perc, '' as c_from, '' as c_to, '' as narration, '' as dept_id " +
                " FROM Project_Detail  where project_status=1  and mda in (select id from mdas where administrative_Segment='"+admin_segment+"') " +
                " UNION select (select iif((select b.id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.id from Year_Budget d where d.budget_version_id = " + last_version.getBudgetVersionId() + " and a.admin_segment=d.admin_segment " +
                " and a.economic_segment=d.economic_segment and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment " +
                " and a.functional_segment=d.functional_segment and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.id from Year_Budget e where e.budget_version_id = " + bv.getId() + " " +
                " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment " +
                " and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment and c.code=e.economic_segment and e.budget_year_id=" + year + "))) as id, a.programme_segment as code, c.name,  " +
                " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment  " +
                " and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment  " +
                " group by b.FundsCtr, b.CmmtItem),0)) as amount1,  " +
                " (select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 1) + " and b.CmmtItem=a.economic_segment and concat('0',b.FundsCtr)=a.admin_segment  " +
                " and b.FundedProg=substring(a.programme_segment,1,12) and b.fund=a.fund_segment and b.BusArea=a.geo_segment and b.FuncArea=a.functional_segment  " +
                " group by b.FundsCtr, b.CmmtItem),0)) as amount2,  " +
                " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = " + last_version.getBudgetVersionId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and b.budget_year_id=" + (year - 1) + ") as amount3,  " +
                " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and b.budget_year_id=" + year + ") as amount4, " +
                " (select year_id from Project_Detail where concat(policy,programme,code,objective)=substring(a.programme_segment, 1, 12)) as year_id, " +
                " (select rank from Project_Detail where concat(policy,programme,code,objective)=substring(a.programme_segment, 1, 12)) as rank, " +
                " (select ISNULL((b.percent_complete),0) from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and b.budget_year_id=" + year + ") as percent_complete, " +
                " (select ISNULL((b.complete_from),'0000-00-00') from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and b.budget_year_id=" + year + ") as complete_from, " +
                " (select ISNULL((b.complete_to),'0000-00-00') from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and b.budget_year_id=" + year + ") as complete_to,  a.narration, (select iif((select b.dept_id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.dept_id from Year_Budget d where d.budget_version_id = " + last_version.getBudgetVersionId() + " and a.admin_segment=d.admin_segment " +
                " and a.economic_segment=d.economic_segment and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment " +
                " and a.functional_segment=d.functional_segment and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.dept_id from Year_Budget e where e.budget_version_id = " + bv.getId() + " " +
                " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment " +
                " and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment and c.code=e.economic_segment and e.budget_year_id=" + year + "))) as dept_id " +
                " FROM Economic_Segment c, Year_Budget a where a.budget_version_id = (select budget_version_id from Year_Budget where id=(select iif((select b.id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.id from Year_Budget d where d.budget_version_id = " + last_version.getBudgetVersionId() + " and a.admin_segment=d.admin_segment " +
                " and a.economic_segment=d.economic_segment and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment " +
                " and a.functional_segment=d.functional_segment and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.id from Year_Budget e where e.budget_version_id = " + bv.getId() + " " +
                " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment " +
                " and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment and c.code=e.economic_segment and e.budget_year_id=" + year + ")))) and c.code=a.economic_segment and a.admin_segment='"+admin_segment+"' " +
                " and a.economic_segment like '"+economic_segment+"%' and substring(a.programme_segment, 1, 12) in (select concat(policy,programme,code,objective) from Project_Detail) "+
                " and a.id=(select iif((select b.id from Year_Budget b where b.budget_version_id = " + bv.getId() + " and a.admin_segment=b.admin_segment and a.economic_segment=b.economic_segment " +
                " and a.programme_segment=b.programme_segment and a.fund_segment=b.fund_segment and a.geo_segment=b.geo_segment and a.functional_segment=b.functional_segment " +
                " and c.code=b.economic_segment and b.budget_year_id=" + year + ") is null,(select d.id from Year_Budget d where d.budget_version_id = " + last_version.getBudgetVersionId() + " and a.admin_segment=d.admin_segment " +
                " and a.economic_segment=d.economic_segment and a.programme_segment=d.programme_segment and a.fund_segment=d.fund_segment and a.geo_segment=d.geo_segment " +
                " and a.functional_segment=d.functional_segment and c.code=d.economic_segment and d.budget_year_id=" + (year - 1) + "), (select e.id from Year_Budget e where e.budget_version_id = " + bv.getId() + " " +
                " and a.admin_segment=e.admin_segment and a.economic_segment=e.economic_segment and a.programme_segment=e.programme_segment and a.fund_segment=e.fund_segment " +
                " and a.geo_segment=e.geo_segment and a.functional_segment=e.functional_segment and c.code=e.economic_segment and e.budget_year_id=" + year + "))) " + narration + " and a.sap_budget_type ='" + budget_type_id + "' " +
                " group by c.code, c.name, a.admin_segment, a.programme_segment, a.economic_segment, a.functional_segment, a.fund_segment, a.geo_segment, a.narration, a.dept_id" +
                " order by code, rank";
            //System.out.println("fetchAllProgrammes sql: "+sql);
//            " SELECT id, concat(policy,code) as code, name, 0 as amt1, 0 as amt2, 0 as amt3, 0 as amt4, year_id, rank, '' as c_perc, '' as c_from, '' as c_to FROM Project_Detail "
//                    + " where project_status=1and mda in (select id from mdas where administrative_Segment='" + admin_segment + "')"
//                    + " UNION select 1 as id, c.code, c.name,(select ISNULL((select sum(b.AmountTc) from SAP_Actuals b where b.FiscYear=" + (year - 2)
//                    + " and b.FundedProg=c.code and b.FundsCtr='" + admin_segment + "'),0)) as amount1,(select ISNULL((select sum(b.AmountTc) from SAP_Actuals b "
//                    + " where b.FiscYear=" + (year - 1) + " and b.FundedProg=c.codeand b.FundsCtr='" + admin_segment + "'),0)) as amount2, 0 as amount3, 0 as amount4, "
//                    + " (select year_id from Project_Detail where code=substring(c.code,3,10)) as year_id, "
//                    + " 100 as rank, '' as c_perc, '' as c_from, '' as c_tofrom SAP_Actuals a, Project_Detail c where a.FundedProg=c.code and a.FundedProg like '" + economic_segment + "%' "
//                    + " and a.FundsCtr='" + admin_segment + "' and (select ISNULL((select sum(budget_amount) from Year_Budgetwhere budget_version_id = " + bv.getId() + " and budget_year_id=" + (year - 1)
//                    + " and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%'),0))=0and (select ISNULL((select sum(budget_amount) "
//                    + " from Year_Budget where budget_version_id = " + bv.getId() + " and budget_year_id=" + year + "and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%'),0))=0 "
//                    + " group by a.FundsCtr, a.FundedProg, c.code, c.name "
//                    + " UNION SELECT 1 as id, c.code, c.name,(select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 2) + "and FundedProg like '" + economic_segment + "%' "
//                    + " and FundsCtr='" + admin_segment + "'),0)) as amount1,(select ISNULL((select sum(AmountTc)from SAP_Actuals where FiscYear=" + (year - 1)
//                    + " and FundedProg like '" + economic_segment + "%' and FundsCtr='" + admin_segment + "'),0)) as amount2,(select ISNULL(sum(b.budget_amount),0) from Year_Budget b "
//                    + " where a.budget_version_id = " + bv.getId() + " and b.admin_segment='" + admin_segment + "'and b.economic_segment like '" + economic_segment + "%' and b.budget_year_id=" + (year - 1) + ") as amount3,0 as amount4, "
//                    + " (select year_id from Project_Detail where code=substring(c.code,3,10)) as year_id, 100 as rank, '' as c_perc, '' as c_from, '' as c_toFROM Year_Budget a, Project_Detail c where a.budget_version_id = " + bv.getId() + " and a.economic_segment=c.code and (select sum(b.budget_amount) "
//                    + " FROM Year_Budget b where b.budget_version_id = " + bv.getId() + " and b.admin_segment='" + admin_segment + "'and b.economic_segment like '" + economic_segment + "%'and b.budget_year_id=" + year + ")=0 "
//                    + " group by a.admin_segment, a.economic_segment, c.code, c.name "
//                    + " UNION SELECT a.id, a.programme_segment as code,(select name from Project_Detail where code=substring(a.programme_segment,3,10)) as name, "
//                    + " (select ISNULL((select sum(AmountTc) from SAP_Actuals where FiscYear=" + (year - 2) + " and FundedProg like '" + economic_segment + "%'and FundsCtr='" + admin_segment + "'),0)) as amount1, "
//                    + " (select ISNULL((select sum(AmountTc) from SAP_Actualswhere FiscYear=" + (year - 1) + " and FundedProg like '" + economic_segment + "%' and FundsCtr='" + admin_segment + "'),0)) as amount2, "
//                    + " (select ISNULL(sum(b.budget_amount),0) from Year_Budget b where b.budget_version_id = " + bv.getId() + " and admin_segment='" + admin_segment + "'and economic_segment like '" + economic_segment + "%' and b.budget_year_id=" + (year - 1)
//                    + " and a.admin_segment=b.admin_segmentand a.programme_segment=b.programme_segment and a.economic_segment=b.economic_segment "
//                    + " and a.functional_segment=b.functional_segmentand a.fund_segment=b.fund_segmentand a.geo_segment=b.geo_segment and a.dept_id=b.dept_id) as amount3, "
//                    + " sum(a.budget_amount) as amount4, (select year_id from Project_Detail where code=substring(a.programme_segment,3,10)) as year_id, 100 as rank, ISNULL(a.percent_complete,''), ISNULL(a.complete_from,''), ISNULL(a.complete_to,'') "
//                    + " FROM Year_Budget a where a.budget_version_id = " + bv.getId() + " and admin_segment='" + admin_segment + "' and economic_segment like '" + economic_segment + "%' "
//                    + " group by a.budget_year_id,a.id,a.admin_segment,a.programme_segment,a.economic_segment,a.functional_segment,a.fund_segment,a.geo_segment,a.dept_id, a.percent_complete, a.complete_from, a.complete_to "
//                    + " order by year_id, code, rank ";
//System.out.println("sql:++++ "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
//Query q = session.createQuery("from YearBudget as a where a.name<>'' order by a.name");
//yearBudgetsList =q.list();
//session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchBudgetHeads(String budget_type_id) {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select c.id, concat(c.name, ' [', c.gl_account, ']') from Budget_type_Components c where c.is_budgeted=0 and c.budget_type_id="+budget_type_id+"ORDER BY c.budget_type_id desc, c.id";
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchById(String id) {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.* from year_budget a where id='" + id + "'";
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchByUserRole(String userid, String userrole) {
        List subsectorsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "";
            if (userrole.contains("Admin")) {
                sql = "select a.id, concat(a.name, ' [', a.administrative_segment, ']') from Mdas a where a.sub_sector_code<>'00' order by a.name";
            } else {
                sql = "select a.id, concat(a.name, ' [', a.administrative_segment, ']') from Mdas a where a.sub_sector_code<>'00' and a.id=(select mda_id from users where id='" + userid + "') order by a.sub_sector_code, a.name";
            }
            //System.out.println("sql  "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            subsectorsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(subsectorsList);
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

    @SuppressWarnings("unchecked")
    public synchronized List<YearBudget> fetchAll2(int budgetYearID, String sapBudgetType) {
        List<YearBudget> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            //String sql = "select top 10 * from " + RAW_TABLE_NAME + " a where a.budget_year_id = " + budgetYearID + " and a.budget_amount<>0 and a.sap_document_number is NULL and a.sap_budget_type='"+sapBudgetType+"' and a.budget_version_id=(select id from year_budget_versions where status=1)  and admin_segment not in ('022800700200', '051800100100', '055100100100') and programme_segment not in ('09050001212302', '01090000100101', '01090000100102', '01090000100103','02110000080101', '02170001300104', '02090000010201', '02060000140102', '02060000140103', '02060000140104', '02110000120102', '02110000120103', '02140000170101') and economic_segment not in ('31010201', '31010101','32010510') order by a.id";
            String sql = "select top 10 * from " + RAW_TABLE_NAME + " a where a.budget_year_id = " + budgetYearID + " and a.budget_amount<>0 and a.sap_document_number is NULL and a.sap_budget_type='"+sapBudgetType+"' and a.budget_version_id=(select id from year_budget_versions where status=1) order by a.id";
            
//and a.sap_budget_type = '" + sapBudgetType + "' 
//System.out.println("sql "+sql);

            SQLQuery q = session.createSQLQuery(sql).addEntity(YearBudget.class);
            objList = (List<YearBudget>) q.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {

        }

        return objList;
    }

    public synchronized String updateAll(int budgetYearID, String sapBudgetType, String sapDocumentNumber, int maxrecno) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "Update " + RAW_TABLE_NAME + " set sap_document_number = '" + sapDocumentNumber + "', sap_budget_type = '" + sapBudgetType + "' "
                    + " where budget_year_id = " + budgetYearID + " and sap_document_number is NULL and id <= "+maxrecno;
//and a.sap_budget_type = '" + sapBudgetType + "' 
//System.out.println("sql "+sql);
            session.createSQLQuery(sql).executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            System.out.println("error: " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return Utility.ActionResponse.FAILED.toString();
        } finally {
//session.close();
        }

        return Utility.ActionResponse.UPDATED.toString();
    }

    public synchronized String fetchBudgetVersions() {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//String sql = "select a.*, (select status from Year_Budget_Versions where id=(select max(id) from Year_Budget_Versions where budget_year_id=(select id from Budget_Years where is_current_base_year=1)+1)) as maxstatus from Year_Budget_Versions a order by a.id";
            String sql = "select iif(status=1, 'selected','') as status, id,year_budget_version from Year_Budget_Versions"; //where budget_year_id=(select id from Budget_Years where is_current_base_year=1)+1 order by id";
//System.out.println("sql:"+sql);
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchEnvelopeByMDAs(String mda_id, String year_id) {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.*, (select name from Budget_Type_Components where id=a.budget_type_component_id) from MTSS_MDA_Ceiling a where a.budget_type_component_id<>'1' and a.mda_id='"+mda_id+"' and a.budget_year_id='"+year_id+"'";
//System.out.println("sql:"+sql);
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchEnvelopeByMDAsContingency(String mda_id, String year_id) {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.*, (select name from Budget_Type_Components where id=a.budget_type_component_id) from MTSS_MDA_Ceiling_Contingency a where a.budget_type_component_id<>'1' and a.mda_id='"+mda_id+"' and a.budget_year_id='"+year_id+"'";
//System.out.println("sql:"+sql);
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchEnvelopeByMDAsSupplementary(String mda_id, String year_id) {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.*, (select name from Budget_Type_Components where id=a.budget_type_component_id) from MTSS_MDA_Ceiling_Supplementary a where a.budget_type_component_id<>'1' and a.mda_id='"+mda_id+"' and a.budget_year_id='"+year_id+"'";
//System.out.println("sql:"+sql);
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

//    public synchronized String fetchApprovedBudgetVersions(String year) {
//        List yearBudgetsList = null;
//        String jsonList = "";
//
//        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
////String sql = "select a.*, (select status from Year_Budget_Versions where id=(select max(id) from Year_Budget_Versions where budget_year_id=(select id from Budget_Years where is_current_base_year=1)+1)) as maxstatus from Year_Budget_Versions a order by a.id";
//            String sql = "select max(budget_version_id) as budgetversion, budget_year_id from Year_Budget where budget_year_id='"+year+"'"; //where budget_year_id=(select id from Budget_Years where is_current_base_year=1)+1 order by id";
////System.out.println("sql:"+sql);
//            SQLQuery q = session.createSQLQuery(sql);
//            yearBudgetsList = q.list();
//            Gson gson = new Gson();
//            jsonList = gson.toJson(yearBudgetsList);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
////session.close();
//        }
//        return jsonList;
//    }

    public synchronized YearBudgetVersions fetchActiveBudgetVersions() {
        YearBudgetVersions btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createSQLQuery("select * from Year_Budget_Versions as a where a.status=1").addEntity(YearBudgetVersions.class);
            btt = (YearBudgetVersions) q.uniqueResult();
            
            //Query q = session.createQuery("from YearBudgetVersions as a where a.status=1");
            //btt = (YearBudgetVersions) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
//session.close();
        }

        return btt;
    }


    public synchronized YearBudget fetchLastBudgetVersions(int year) {
        YearBudget btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createSQLQuery("select * from Year_Budget as a where a.budget_version_id=(select max(budget_version_id) from Year_Budget where budget_year_id="+year+")").addEntity(YearBudget.class);
            q.setMaxResults(1);
            btt = (YearBudget) q.uniqueResult();
            //Query q = session.createQuery("from YearBudgetVersions as a where a.status=1");
            //btt = (YearBudgetVersions) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
//session.close();
        }

        return btt;
    }

    public synchronized String fetchSapBudgetTypes() {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.sap_budget_type,a.budget_type from Year_Budget_Types a order by a.sap_budget_type";
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchBudgetTypes() {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select * from Budget_Types order by name";
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized String fetchBudgetCurrencies() {
        List yearBudgetsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.* from Currencies a order by a.id";
            SQLQuery q = session.createSQLQuery(sql);
            yearBudgetsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(yearBudgetsList);
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

    public synchronized Users fetchBudgetDirector() {
        Users btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
//(select user_id from user_role where role_id=(select id from roles where name='Director'))
            Query q = session.createQuery("from Users where id=7");
            btt = (Users) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
//session.close();
        }

        return btt;
    }
    
    public synchronized String fetchBudgetDirectors() {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select firstname, lastname, email from  users where id in (select user_id from user_role where role_id = (select id from roles where name='Director'))";
            //System.out.println("sql::: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mdasList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mdasList);
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
    
    public synchronized String changeBudgetVersion(String year_id, String version_id, String type_id) {
// use the following to auto-assign weights to MDAs
//update MDAs set MDAs.mda_weight=((MDAs.id * 1.0) /((select sum(b.id) from MDAs b where b.sub_sector_code=MDAs.sub_sector_code) * 1.0) * 100)
//SELECT * FROM [dbo].[MDAs] where sub_sector_code<>'00' order by sub_sector_code

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.changeBudgetVersion :year_id, :version_id, :type_id")
                    .setParameter("year_id", year_id)
                    .setParameter("version_id", version_id)
                    .setParameter("type_id", type_id)
                    .executeUpdate();

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

        return Utility.ActionResponse.SUCCESSFULL.toString();
    }


    public synchronized String approveBudget(String year_id, String version_id, String type_id, String admin_seg) {
// use the following to auto-assign weights to MDAs
//update MDAs set MDAs.mda_weight=((MDAs.id * 1.0) /((select sum(b.id) from MDAs b where b.sub_sector_code=MDAs.sub_sector_code) * 1.0) * 100)
//SELECT * FROM [dbo].[MDAs] where sub_sector_code<>'00' order by sub_sector_code
        int year = Integer.parseInt(year_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("update Year_Budget set dept_id= 'Approved' where budget_year_id in (select year from Budget_Years where id="+year+" or id="+(year - 1)+") and budget_version_id ="+version_id+" and sap_budget_type='"+type_id+"' and admin_segment='"+admin_seg+"'").executeUpdate();
            tx.commit();
            //System.out.println("update Year_Budget set dept_id= 'Approved' where budget_year_id in (select year from Budget_Years where id="+year+" or id="+(year - 1)+") and budget_version_id ="+version_id+" and sap_budget_type='"+type_id+"' and admin_segment='"+admin_seg+"'");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return "";
        } finally {
//session.close();
        }

        return Utility.ActionResponse.SUCCESSFULL.toString();
    }

    public synchronized String unlockBudget(String year_id, String version_id, String type_id, String admin_seg) {
// use the following to auto-assign weights to MDAs
//update MDAs set MDAs.mda_weight=((MDAs.id * 1.0) /((select sum(b.id) from MDAs b where b.sub_sector_code=MDAs.sub_sector_code) * 1.0) * 100)
//SELECT * FROM [dbo].[MDAs] where sub_sector_code<>'00' order by sub_sector_code
        int year = Integer.parseInt(year_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("update Year_Budget set dept_id= '' where budget_year_id in (select year from Budget_Years where id="+year+" or id="+(year - 1)+") and budget_version_id ="+version_id+" and sap_budget_type='"+type_id+"' and admin_segment='"+admin_seg+"'").executeUpdate();
            tx.commit();
            //System.out.println("update Year_Budget set dept_id= '' where budget_year_id in (select year from Budget_Years where id="+year+" or id="+(year - 1)+") and budget_version_id ="+version_id+" and sap_budget_type='"+type_id+"' and admin_segment='"+admin_seg+"'");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return "";
        } finally {
//session.close();
        }

        return Utility.ActionResponse.SUCCESSFULL.toString();
    }

    /* YearBudget methods end */
}
