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
public class MtssCostingHibernateHelper {

	public static final String TABLE_NAME = "MtssCosting";
	public static final String RAW_TABLE_NAME = "MTSS_Costing";

	/* MtssCosting methods begin */
    public synchronized String insert(MtssCosting mtssCosting) {
        //MtssCosting checkRole = exists(mtssCosting.getAdmin_segment(), mtssCosting.getEconomic_segment());
        MtssCosting checkRole = exists(mtssCosting.getAdminSegment(), mtssCosting.getEconomicSegment(), mtssCosting.getProgrammeSegment(), 
        mtssCosting.getFunctionalSegment(), mtssCosting.getFundSegment(), mtssCosting.getGeoSegment(), mtssCosting.getBudgetYearId());
        if (checkRole == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(mtssCosting);
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
            return Utility.ActionResponse.INSERTED.toString()+"_"+mtssCosting.getProgrammeSegment();
        } else {
            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String update(MtssCosting mtssCosting) {
        MtssCosting checkRole = exists(mtssCosting.getId());
        if (checkRole != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(mtssCosting);
                //session.getTransaction().commit();

                tx.commit();
            } catch (HibernateException e) {
                System.out.println("error: "+e.getMessage());
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return "";
            } finally {
                //session.close();
            }
            return Utility.ActionResponse.UPDATED.toString()+"_"+mtssCosting.getProgrammeSegment();
        } else {

            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(MtssCosting mtssCosting) {
        MtssCosting checkRole = exists(mtssCosting.getId());
        if (checkRole != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(mtssCosting);
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

    public synchronized MtssCosting exists(String admin_segment, String economic_segment, String programme_segment, String functional_segment, 
            String Fund_segment, String geo_segment, int budget_year_id) {
//    public synchronized MtssCosting exists(String admin_segment, String economic_segment) {
        MtssCosting mtssCosting = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from MtssCosting as a where a.adminSegment='" + admin_segment + "' and a.economicSegment='" + economic_segment 
                    + "' and a.programmeSegment='" + programme_segment + "' and a.functionalSegment='" + functional_segment 
                    + "' and a.fundSegment='" + Fund_segment + "' and a.geoSegment='" + geo_segment 
                    + "' and a.budgetYearId='" + budget_year_id + "'");
            //q.setMaxResults(1);
            mtssCosting = (MtssCosting) q.uniqueResult();
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
        return mtssCosting;
    }

    public synchronized MtssCosting exists(int id) {
        MtssCosting btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);           
	        btt = (MtssCosting) q.uniqueResult();

            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
        	//session.close();
        }
        
        return btt;
    }

    public synchronized String getMaxSerialNo(String tablename) {
        tablename = "dbo."+tablename;
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select max(id) as maxserialno from "+tablename;
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
        tablename = "dbo."+tablename;
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select max(programme_segment) as maxserialno from "+tablename+" where programme_segment like '"+code+"%'";
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
                    resp = code+"01";
                    break;
                case 12:
                    resp += "01";
                    break;
                default:
                    String resp_a = resp.substring(13);
                    int activity = Integer.parseInt(resp_a) + 1;
                    if(activity < 10){
                        resp = code + "0" + activity;
                    }else{
                        resp = code + activity;
                    }   break;
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
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='"+admin_segment+"' "
                            + " and budget_type_component_id='"+component_id+"' and budget_year_id=(select id from Budget_Years "
                            + " where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + " as year_id, (select value from Framework_parameters where year=(select year from Budget_Years where is_current_base_year=1)+1 "
                            + " and  framework_parameter_type_id=(select id from Framework_parameter_types where name like '%nflation%')) as inflation_rate "
                            + " from Economic_Segment_Header1 where code='"+gl_code+"'";
                    break;
                case 2:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='"+admin_segment+"' "
                            + " and budget_type_component_id='"+component_id+"' and budget_year_id=(select id from Budget_Years "
                            + " where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + " as year_id, (select value from Framework_parameters where year=(select year from Budget_Years where is_current_base_year=1)+1 "
                            + " and  framework_parameter_type_id=(select id from Framework_parameter_types where name like '%nflation%')) as inflation_rate "
                            + " from Economic_Segment_Header2 where code='"+gl_code+"'";
                    break;
                case 4:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='"+admin_segment+"' "
                            + " and budget_type_component_id='"+component_id+"' and budget_year_id=(select id from Budget_Years "
                            + " where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + " as year_id, (select value from Framework_parameters where year=(select year from Budget_Years where is_current_base_year=1)+1 "
                            + " and  framework_parameter_type_id=(select id from Framework_parameter_types where name like '%nflation%')) as inflation_rate "
                            + " from Economic_Segment_Header3 where code='"+gl_code+"'";
                    break;
                case 6:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='"+admin_segment+"' "
                            + " and budget_type_component_id='"+component_id+"' and budget_year_id=(select id from Budget_Years "
                            + " where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + " as year_id, (select value from Framework_parameters where year=(select year from Budget_Years where is_current_base_year=1)+1 "
                            + " and  framework_parameter_type_id=(select id from Framework_parameter_types where name like '%nflation%')) as inflation_rate "
                            + " from Economic_Segment_Header4 where code='"+gl_code+"'";
                    break;
                default:
                    sql = "select code, name, (select sum(total_amount) FROM MTSS_MDA_Ceiling where mda_id='"+admin_segment+"' "
                            + " and budget_type_component_id='"+component_id+"' and budget_year_id=(select id from Budget_Years "
                            + " where is_current_base_year=1)+1) as envelope, (select year from Budget_Years where is_current_base_year=1)+1 "
                            + " as year_id, (select value from Framework_parameters where year=(select year from Budget_Years where is_current_base_year=1)+1"
                            + " and  framework_parameter_type_id=(select id from Framework_parameter_types where name like '%nflation%')) as inflation_rate "
                            + " from Economic_Segment where code in ("+gl_code+")";
                    break;
            }
//System.out.println("sql  "+sql);
            
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
            
            String sql = "select c.code, concat(c.name, ' [', c.code, ']') from Economic_Segment c where c.code like '" + econ_segment +"%'";
            if(econ_segment.contains(",")){
                sql = "select c.code, concat(c.name, ' [', c.code, ']') from Economic_Segment c where c.code in (" + econ_segment +")";
            }
            //System.out.println("sql   "+sql);
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
            
            String sql = "select c.administrative_segment, concat(c.name, ' [', c.administrative_segment, ']') from Departments c where c.mda_id='"+mda_id+"'";
            
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
                
    public synchronized String fetchAll(String admin_segment, String economic_segment, String budget_year_id) {
//System.out.println("admin_segment "+admin_segment+"     economic_segment "+economic_segment+"     budget_year_id "+budget_year_id);
        List mtssCostingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            if(budget_year_id.equals("")){
                budget_year_id = "0";
            }
            int year = Integer.parseInt(budget_year_id) - 1;
            String sql = " SELECT a.id, (select code from Economic_Segment where code=a.economic_segment) as code, (select name from Economic_Segment where code=a.economic_segment) as name, " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2015) as amount0,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2016) as amount1,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2017) as amount2,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2018) as amount3,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2019) as amount4,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2020) as amount5,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2021) as amount6  " +
                " FROM MTSS_Costing a where a.admin_segment='"+admin_segment+"' and a.economic_segment like '"+economic_segment+"%'  and a.budget_year_id="+(year + 1);

            if(economic_segment.contains(",")){
                sql = " SELECT a.id, (select code from Economic_Segment where code=a.economic_segment) as code, (select name from Economic_Segment where code=a.economic_segment) as name, " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2015) as amount0,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2016) as amount1,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2017) as amount2,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2018) as amount3,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2019) as amount4,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2020) as amount5,  " +
                " (select ISNULL(sum(budget_amount),0) from MTSS_Costing where a.admin_segment=admin_segment and a.economic_segment=economic_segment and a.programme_segment=programme_segment " +
                " and a.functional_segment=functional_segment and a.fund_segment=fund_segment and a.geo_segment=geo_segment and budget_year_id=2021) as amount6  " +
                " FROM MTSS_Costing a where a.admin_segment='"+admin_segment+"' and a.economic_segment  in ("+economic_segment+")  and a.budget_year_id="+(year + 1);
                
            }
            
            //System.out.println("fetchAll sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtssCostingsList = q.list();
//            Query q = session.createQuery("from MtssCosting as a where a.name<>'' order by a.name");
//            mtssCostingsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssCostingsList);
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
    
    public synchronized String fetchAllProgrammes(String admin_segment, String economic_segment, String budget_year_id) {
//System.out.println("admin_segment "+admin_segment+"     economic_segment "+economic_segment+"     budget_year_id "+budget_year_id);
        List mtssCostingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            if(budget_year_id.equals("")){
                budget_year_id = "0";
            }
            int year = Integer.parseInt(budget_year_id) - 1;
            String sql = " SELECT max(id), concat(policy,programme,code,objective) as code, name, 0 as amt0, 0 as amt1, 0 as amt2, 0 as amt3, 0 as amt4, 0 as amt5, 0 as amt6, (select d.year from budget_years d where d.id=year_id) as year_id, rank FROM Project_Detail " +
                " where project_status=1 and mda in (select id from mdas where administrative_Segment='"+admin_segment+"') group by policy,programme,code,objective, name, year_id, rank" + 
                " UNION SELECT (select b.id from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year + 1)+") as id, " +
                " a.programme_segment as code,  (select name from Project_Detail where project_code=substring(a.programme_segment,1,12)) as name, " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year - 3)+") as amount0, " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year - 2)+") as amount1, " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year - 1)+") as amount2, " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year)+") as amount3, " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year + 1)+") as amount4, " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year + 2)+") as amount5, " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where b.admin_segment='"+admin_segment+"' and b.programme_segment=a.programme_segment and b.economic_segment like '"+economic_segment+"%' and b.budget_year_id="+(year + 3)+") as amount6, " +
                " (select year from budget_years where id=(select year_id from Project_Detail where project_code=substring(a.programme_segment,1,12))) as year_id, 100 as rank " +
                " FROM MTSS_Costing a where a.admin_segment='"+admin_segment+"' and a.economic_segment like '"+economic_segment+"%' " +
                " group by a.admin_segment,a.programme_segment,a.economic_segment order by year_id, code, rank ";
//System.out.println("fetchAllProgrammes sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtssCostingsList = q.list();
//            Query q = session.createQuery("from MtssCosting as a where a.name<>'' order by a.name");
//            mtssCostingsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssCostingsList);
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
    
    public synchronized String fetchBudgetHeads() {
        List mtssCostingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select c.id, concat(c.name, ' [', c.gl_account, ']') from Budget_type_Components c where c.is_budgeted=0 ORDER BY c.budget_type_id desc, c.id";
            SQLQuery q = session.createSQLQuery(sql);
            mtssCostingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssCostingsList);
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
        List mtssCostingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.*, (select ISNULL(sum(b.quantity),0) from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment " +
                " and b.admin_segment=a.admin_segment  and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment " +
                " and b.budget_year_id=a.budget_year_id+1) as qty1, (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+1) as amt1,  " +
                " (select ISNULL(sum(b.quantity),0) from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+2) as qty2,  " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+2) as amt2,  " +
                " (select ISNULL(sum(b.quantity),0) from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+3) as qty3,  " +
                " (select ISNULL(sum(b.budget_amount),0) from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+3) as amt3,  " +
                " (select b.id from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+1) as id2,  " +
                " (select b.id from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+2) as id3,  " +
                " (select b.id from MTSS_Costing b where a.programme_segment=b.programme_segment and b.economic_segment=a.economic_segment and b.admin_segment=a.admin_segment  " +
                " and b.functional_segment=a.functional_segment and b.fund_segment=a.fund_segment and b.geo_segment=a.geo_segment and b.budget_year_id=a.budget_year_id+3) as id4 from MTSS_Costing a where id='"+id+"'";
//System.out.println("sql:::::   "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtssCostingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssCostingsList);
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
            if(userrole.contains("Admin")){
                sql = "select a.id, concat(a.name, ' [', a.administrative_segment, ']') from Mdas a where a.sub_sector_code<>'00' order by a.name";
            }else{
                sql = "select a.id, concat(a.name, ' [', a.administrative_segment, ']') from Mdas a where  a.sub_sector_code<>'00' and a.id=(select mda_id from users where id='"+userid+"') order by a.sub_sector_code, a.name";
            }
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
    

    /* MtssCosting methods end */

}
