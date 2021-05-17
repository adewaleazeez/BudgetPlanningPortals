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
public class MtssMDACeilingHibernateHelper {

	public static final String TABLE_NAME = "MtssMdaCeiling";
	public static final String RAW_TABLE_NAME = "MTSS_MDA_Ceiling";

    /* MtssMDACeiling methods begin */
    public synchronized String insert(MtssMdaCeiling mtssmdaceiling) {
        MtssMdaCeiling checkMda = exists(mtssmdaceiling.getId());
        if (checkMda == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(mtssmdaceiling);
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

    public synchronized String update(MtssMdaCeiling mtssmdaceiling) {
//System.out.println(mtssmdaceiling.getMdaId()+"  U mtssmdaceiling::: "+mtssmdaceiling.getBudgetYears()+"  U mtssmdaceiling::: "+mtssmdaceiling.getTotalAmount());
        MtssMdaCeiling checkMda = exists(mtssmdaceiling.getId());
        if (checkMda == null || checkMda.getId() == mtssmdaceiling.getId()) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(mtssmdaceiling);
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
            return Utility.ActionResponse.UPDATED.toString();
        } else {

            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String delete(MtssMdaCeiling mtssmdaceiling) {
        MtssMdaCeiling checkMda = exists(mtssmdaceiling.getId());
        if (checkMda != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(mtssmdaceiling);
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

    public synchronized MtssMdaCeiling exists(String id) {
        MtssMdaCeiling mtssmdaceiling = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from MtssMdaCeiling as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            mtssmdaceiling = (MtssMdaCeiling) q.uniqueResult();
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
        return mtssmdaceiling;
    }

    public synchronized MtssMdaCeiling exists(int id) {
        MtssMdaCeiling mtssmdaceiling = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from MtssMdaCeiling as a where a.id='" + id + "'");
            
            mtssmdaceiling = (MtssMdaCeiling) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return mtssmdaceiling;
    }

    public synchronized String exists_Supplementary(String mda_id, String sub_sector_id, String budget_type_component_id, String budget_year_id) {
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Query q = session.createSQLQuery("select id from MTSS_Sector_Ceiling_Supplementary as a where a.id='" + id + "'");
            String sql = "select id from MTSS_MDA_Ceiling_Supplementary as a where a.mda_id='" + mda_id + "' and a.sub_sector_id='" + sub_sector_id + "' and a.budget_type_component_id='" + budget_type_component_id + "' and a.budget_year_id='" + budget_year_id + "'";
            SQLQuery q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            recordlist = q.list();
            HashMap hmap = (HashMap) recordlist.get(0);

            if (hmap.get("id") == null) {
                resp = "0";
            } else {
                resp = hmap.get("id").toString();
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
 
    public synchronized String exists_Contingency(String mda_id, String sub_sector_id, String budget_type_component_id, String budget_year_id) {
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Query q = session.createSQLQuery("select id from MTSS_Sector_Ceiling_Contingency as a where a.id='" + id + "'");
            String sql = "select id from MTSS_MDA_Ceiling_Contingency as a where a.mda_id='" + mda_id + "' and a.sub_sector_id='" + sub_sector_id + "' and a.budget_type_component_id='" + budget_type_component_id + "' and a.budget_year_id='" + budget_year_id + "'";
            SQLQuery q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            recordlist = q.list();
            HashMap hmap = (HashMap) recordlist.get(0);

            if (hmap.get("id") == null) {
                resp = "0";
            } else {
                resp = hmap.get("id").toString();
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
 
    public synchronized String insertSupplementaryRecord(String id, String mda_id, String Sub_sector_id, String budget_type_component_id, String total_amount, String budget_year_id, String date_created, String org_id, String mda_weight) {
        String resp = null;
        id = (Integer.parseInt(getMaxSerialNo("MTSS_MDA_Ceiling_Supplementary")) + 1)+"";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("insert into MTSS_MDA_Ceiling_Supplementary values ('"+id+"','"+mda_id+"','"+Sub_sector_id+"','"+budget_type_component_id+"','"+total_amount+"','"+budget_year_id+"','"+date_created+"','"+org_id+"','"+mda_weight+"')").executeUpdate();           
            resp = Utility.ActionResponse.SUCCESSFULL.toString();
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
        
        return resp;
    }
    
    public synchronized String insertContingencyRecord(String id, String mda_id, String Sub_sector_id, String budget_type_component_id, String total_amount, String budget_year_id, String date_created, String org_id, String mda_weight) {
        String resp = null;
        id = (Integer.parseInt(getMaxSerialNo("MTSS_MDA_Ceiling_Contingency")) + 1)+"";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("insert into MTSS_MDA_Ceiling_Contingency values ('"+id+"','"+mda_id+"','"+Sub_sector_id+"','"+budget_type_component_id+"','"+total_amount+"','"+budget_year_id+"','"+date_created+"','"+org_id+"','"+mda_weight+"')").executeUpdate();           
            resp = Utility.ActionResponse.SUCCESSFULL.toString();
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
        
        return resp;
    }
    
    public synchronized String updateSupplementaryRecord(String id, String total_amount) {
        String resp = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("update MTSS_MDA_Ceiling_Supplementary set total_amount='" + total_amount + "' where id='"+id+"'").executeUpdate();           
            resp = Utility.ActionResponse.SUCCESSFULL.toString();
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
        
        return resp;
    }
    
    public synchronized String updateContingencyRecord(String id, String total_amount) {
        String resp = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("update MTSS_MDA_Ceiling_Contingency set total_amount='" + total_amount + "' where id='"+id+"'").executeUpdate();           
            resp = Utility.ActionResponse.SUCCESSFULL.toString();
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
        
        return resp;
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
        
    public synchronized String fetchYearMenu(int id, int budget_year_id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            /*SELECT DISTINCT year, '1' as mybf_contigency_value FROM Budget_Years " 
                        + " WHERE is_current_base_year=1 UNION ";
                        + " */
            String sql = " SELECT DISTINCT a.budget_year, '1' as mybf_contigency_value FROM MYBF_Previous_Forward a WHERE a.budget_year "
                        + " BETWEEN (SELECT year+1 FROM Budget_Years where is_current_base_year=1) AND "
                        + " (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b "
                        + " WHERE a.request_type_id="+ id + "  AND a.fully_approved=1 AND b.id=a.version_id and a.budget_year_id="+(budget_year_id-1)+") GROUP BY a.budget_year " ;
                    //System.out.println("sql3: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
            
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

    public synchronized MtssMdaCeiling fetchObj(int id) {
        MtssMdaCeiling mtssmdaceiling = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            mtssmdaceiling = (MtssMdaCeiling) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return mtssmdaceiling;
    }
    
    public synchronized MtssMdaCeiling fetchObjById(int sub_sector_id, int mda_id, int budget_year_id, int budget_type_component_id) {
        MtssMdaCeiling mda = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            //Query q = session.createQuery("from " + TABLE_NAME + " as a where a.mdas.id = " + sub_sector_id + " and a.budgetYears.id = " + budget_year_id + " and a.budgetTypeComponents.id = " + budget_type_component_id);            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.subSectorId = " + sub_sector_id + " and a.mdaId = " + mda_id + " and a.budgetYears.id = " + budget_year_id + " and a.budgetTypeComponents.id = " + budget_type_component_id);            
            mda = (MtssMdaCeiling) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return mda;
    }
    
    public synchronized String fetch(int id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;
            
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
            
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

    public synchronized String fetchAll() {
        List mtssmdaceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Mtss_Sub_Sector_Ceiling a where a.id<>'0' order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
//            Query q = session.createQuery("from MtssMdaCeiling as a where a.name<>'' order by a.name");
//            mtssmdaceilingsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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
    
    public synchronized String fetchHeaders() {
        List mtssmdaceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT 0 as id, 'Revenue' as name, '1' AS orderkey UNION "
                 + "select c.id, c.name, '1' AS orderkey from Budget_type_Components c where c.budget_type_id=2 AND c.is_budgeted=1 ORDER BY orderkey";
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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
    
    public synchronized String fetchDatalist(int mybf_id, int year_id, int sub_sector_id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            String sql = "SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, "
//                 + " '1' as budget_type_component_id, (SELECT SUM(a.budget_value) FROM MYBF_Figures a) as budget_value, '1' AS orderkey "
//                 + " FROM MYBF_Figures a,  Budget_Years b WHERE a.version_id=(SELECT version_id FROM Request_Details WHERE "
//                 + " request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ") AND a.budget_type_component_id IN "
//                 + " (select c.id from Budget_type_Components c where c.budget_type_id=1 AND c.is_budgeted=1) "
//                 + " UNION ALL SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id, "
//                 + " (select ISNULL((select d.total_amount from MTSS_MDA_Ceiling d where d.sub_sector_id=0 and "
//                 + " budget_year_id="+ year_id + " and d.budget_type_component_id=a.budget_type_component_id),0)) as budget_value, "
//                 + " '1' AS orderkey FROM MYBF_Figures a,  Budget_Years b WHERE a.version_id=(SELECT version_id "
//                 + " FROM Request_Details WHERE request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ") AND "
//                 + " a.budget_type_component_id IN (select c.id from Budget_type_Components c where c.budget_type_id=2 AND c.is_budgeted=1) "
//                 + " UNION ALL SELECT DISTINCT a.budget_year, '1' as budget_type_component_id, SUM(a.budget_value) as budget_value, "
//                 + " '1' AS orderkey FROM MYBF_Previous_Forward a WHERE a.budget_year BETWEEN (SELECT year+1 FROM Budget_Years where "
//                 + " is_current_base_year=1) AND (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b "
//                 + " WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 AND b.id=a.version_id AND a.budget_year_id ="+ year_id + ") GROUP BY a.budget_year"
//                 + " UNION ALL SELECT DISTINCT a.budget_year, (SELECT b.budget_type_component_id FROM MYBF_Figures b WHERE "
//                 + " b.id=a.mybf_figure_id) as budget_type_component_id, (select ISNULL((select d.total_amount from MTSS_MDA_Ceiling d where "
//                 + " d.sub_sector_id=0 and d.budget_year_id BETWEEN (SELECT year+1 FROM Budget_Years where is_current_base_year=1) AND (SELECT DISTINCT b.mtefToYear "
//                 + " FROM Request_Details a, Budget_Versions b WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 AND b.id=a.version_id AND a.budget_year_id =1) "
//                 + " and d.budget_type_component_id IN (SELECT b.budget_type_component_id FROM MYBF_Figures b WHERE b.id=a.mybf_figure_id)),0))  as budget_value, "
//                 + " '1' AS orderkey FROM MYBF_Previous_Forward a WHERE mybf_figure_id IN "
//                 + " (SELECT DISTINCT a.id FROM MYBF_Figures a,  Budget_Years b WHERE a.version_id=(SELECT version_id FROM Request_Details WHERE "
//                 + " request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ") AND a.budget_type_component_id IN "
//                 + " (select c.id from Budget_type_Components c where c.budget_type_id=2 AND c.is_budgeted=1)) AND (a.budget_year BETWEEN "
//                 + " (SELECT year+1 FROM Budget_Years where is_current_base_year=1) AND (SELECT DISTINCT b.mtefToYear FROM Request_Details a, "
//                 + " Budget_Versions b WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 AND b.id=a.version_id AND a.budget_year_id ="+ year_id + ")) "
//                 + " ORDER BY budget_year, orderkey, budget_type_component_id ";
//            
//            sql = "SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, "
//                 + " '1' as budget_type_component_id, (SELECT SUM(a.budget_value) FROM MYBF_Figures a) as budget_value, '1' AS orderkey "
//                 + " FROM MYBF_Figures a,  Budget_Years b WHERE a.version_id=(SELECT version_id FROM Request_Details WHERE "
//                 + " request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ") AND a.budget_type_component_id IN "
//                 + " (select c.id from Budget_type_Components c where c.budget_type_id=1 AND c.is_budgeted=1) "
//                 + " UNION ALL SELECT DISTINCT a.budget_year, '1' as budget_type_component_id, SUM(a.budget_value) as budget_value, "
//                 + " '1' AS orderkey FROM MYBF_Previous_Forward a WHERE a.budget_year BETWEEN (SELECT year+1 FROM Budget_Years where "
//                 + " is_current_base_year=1) AND (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b "
//                 + " WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 AND b.id=a.version_id AND a.budget_year_id="+ year_id + ") GROUP BY a.budget_year"
//                 + " UNION ALL SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id,"
//                 + " a.total_amount as budget_value, '1' AS orderkey FROM MTSS_MDA_Ceiling a  WHERE a.mda_id=0 "
//                 + " ORDER BY budget_year, orderkey, budget_type_component_id";
            
            String sql = "SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id, "
                 + " a.total_amount as budget_value, a.sub_sector_id, 0 as mda_id FROM MTSS_SubSector_Ceiling a  WHERE a.Sub_sector_id="+ sub_sector_id
                 + " AND (a.budget_year_id BETWEEN (SELECT id+1 FROM Budget_Years where is_current_base_year=1) AND (SELECT id FROM Budget_Years where year = "
                 + " (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 "
                 + " AND b.id=a.version_id AND a.budget_year_id ="+ (year_id-1) + ")))"
//                 + "UNION ALL SELECT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id, "
//                 + "a.total_amount as budget_value, a.mda_id, a.sub_sector_id FROM MTSS_MDA_Ceiling a  WHERE a.Sub_sector_id=1"
//                 + "AND (a.budget_year_id BETWEEN (SELECT id FROM Budget_Years where is_current_base_year=1) AND (SELECT id FROM Budget_Years where year = "
//                 + "(SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 "
//                 + "AND b.id=a.version_id AND a.budget_year_id="+ year_id + ")))"
                 + "ORDER BY budget_year, mda_id, sub_sector_id, budget_type_component_id";
        
            //sql = "select productid from stockin";
            //System.out.println("sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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
    
    public synchronized String fetchSupplementaryDatalist(int mybf_id, int year_id, int sub_sector_id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            String sql = "SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id, "
                 + " a.total_amount as budget_value, a.sub_sector_id, 0 as mda_id FROM MTSS_SubSector_Ceiling_Supplementary a  WHERE a.Sub_sector_id="+ sub_sector_id
                 + " AND (a.budget_year_id BETWEEN (SELECT id+1 FROM Budget_Years where is_current_base_year=1) AND (SELECT id FROM Budget_Years where year = "
                 + " (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 "
                 + " AND b.id=a.version_id AND a.budget_year_id ="+ (year_id-1) + ")))"
                 + "ORDER BY budget_year, mda_id, sub_sector_id, budget_type_component_id";
        
            //sql = "select productid from stockin";
            //System.out.println("sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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
    
    public synchronized String fetchContingencyDatalist(int mybf_id, int year_id, int sub_sector_id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            String sql = "SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id, "
                 + " a.total_amount as budget_value, a.sub_sector_id, 0 as mda_id FROM MTSS_SubSector_Ceiling_Contingency a  WHERE a.Sub_sector_id="+ sub_sector_id
                 + " AND (a.budget_year_id BETWEEN (SELECT id+1 FROM Budget_Years where is_current_base_year=1) AND (SELECT id FROM Budget_Years where year = "
                 + " (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 "
                 + " AND b.id=a.version_id AND a.budget_year_id ="+ (year_id-1) + ")))"
                 + "ORDER BY budget_year, mda_id, sub_sector_id, budget_type_component_id";
        
            //sql = "select productid from stockin";
            //System.out.println("sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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
    
    public synchronized String fetchAllEnvelope(String budget_year_id, String sub_sector_id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";
        int first_budget_year = Integer.parseInt(budget_year_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = " SELECT concat((SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), '_', budget_type_component_id, '_', mda_id) as id, "
                + " d.total_amount FROM MTSS_MDA_Ceiling d WHERE sub_sector_id="+sub_sector_id+" AND  (budget_year_id BETWEEN "+(first_budget_year)+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+(first_budget_year-1)+"))) "
                + " ORDER BY budget_year_id, mda_id, budget_type_component_id ";
            //System.out.println("sql:: "+sql);
            //(SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), d.budget_type_component_id, d.sub_sector_id, 
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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

    public synchronized String fetchAllSupplementaryEnvelope(String budget_year_id, String sub_sector_id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";
        int first_budget_year = Integer.parseInt(budget_year_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = " SELECT concat((SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), '_', budget_type_component_id, '_', mda_id) as id, "
                + " d.total_amount FROM MTSS_MDA_Ceiling_Supplementary d WHERE sub_sector_id="+sub_sector_id+" AND  (budget_year_id BETWEEN "+(first_budget_year)+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+(first_budget_year-1)+"))) "
                + " ORDER BY budget_year_id, mda_id, budget_type_component_id ";
            //System.out.println("sql:: "+sql);
            //(SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), d.budget_type_component_id, d.sub_sector_id, 
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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

    public synchronized String fetchAllContingencyEnvelope(String budget_year_id, String sub_sector_id) {
        List mtssmdaceilingsList = null;
        String jsonList = "";
        int first_budget_year = Integer.parseInt(budget_year_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = " SELECT concat((SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), '_', budget_type_component_id, '_', mda_id) as id, "
                + " d.total_amount FROM MTSS_MDA_Ceiling_Contingency d WHERE sub_sector_id="+sub_sector_id+" AND  (budget_year_id BETWEEN "+(first_budget_year)+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+(first_budget_year-1)+"))) "
                + " ORDER BY budget_year_id, mda_id, budget_type_component_id ";
            //System.out.println("sql:: "+sql);
            //(SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), d.budget_type_component_id, d.sub_sector_id, 
            SQLQuery q = session.createSQLQuery(sql);
            mtssmdaceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtssmdaceilingsList);
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

    public synchronized String distributeEnvelopes(int mybf_id, int year_id, int subsector_id) {
// use the following to auto-assign weights to MDAs
//update MDAs set MDAs.mda_weight=((MDAs.id * 1.0) /((select sum(b.id) from MDAs b where b.sub_sector_code=MDAs.sub_sector_code) * 1.0) * 100)
//SELECT * FROM [dbo].[MDAs] where sub_sector_code<>'00' order by sub_sector_code

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.distributeMDAEnvelopes :mybf_id, :year_id, :subsector_id")
                    .setParameter("mybf_id", mybf_id)
                    .setParameter("year_id", year_id)
                    .setParameter("subsector_id", subsector_id)
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

    public synchronized String distributeMDASupplementaryEnvelopes(int mybf_id, int year_id, int subsector_id) {
// use the following to auto-assign weights to MDAs
//update MDAs set MDAs.mda_weight=((MDAs.id * 1.0) /((select sum(b.id) from MDAs b where b.sub_sector_code=MDAs.sub_sector_code) * 1.0) * 100)
//SELECT * FROM [dbo].[MDAs] where sub_sector_code<>'00' order by sub_sector_code
//System.out.println("mybf_id "+mybf_id+"       year_id "+year_id+"         subsector_id "+subsector_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.distributeMDASupplementaryEnvelopes :mybf_id, :year_id, :subsector_id")
                    .setParameter("mybf_id", mybf_id)
                    .setParameter("year_id", year_id)
                    .setParameter("subsector_id", subsector_id)
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

    public synchronized String distributeMDAContingencyEnvelopes(int mybf_id, int year_id, int subsector_id) {
// use the following to auto-assign weights to MDAs
//update MDAs set MDAs.mda_weight=((MDAs.id * 1.0) /((select sum(b.id) from MDAs b where b.sub_sector_code=MDAs.sub_sector_code) * 1.0) * 100)
//SELECT * FROM [dbo].[MDAs] where sub_sector_code<>'00' order by sub_sector_code
//System.out.println("mybf_id "+mybf_id+"       year_id "+year_id+"         subsector_id "+subsector_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.distributeMDAContingencyEnvelopes :mybf_id, :year_id, :subsector_id")
                    .setParameter("mybf_id", mybf_id)
                    .setParameter("year_id", year_id)
                    .setParameter("subsector_id", subsector_id)
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

    public synchronized String createMDAInitialValues(int mybf_id, int year_id) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.createMDAInitialValues :mybf_id, :year_id")
                    .setParameter("mybf_id", mybf_id)
                    .setParameter("year_id", year_id)
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

     public synchronized String createMDASupplementaryInitialValues(int mybf_id, int year_id) {
//System.out.println("mybf_id "+mybf_id+"       year_id "+year_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.createMDASupplementaryInitialValues :mybf_id, :year_id")
                    .setParameter("mybf_id", mybf_id)
                    .setParameter("year_id", year_id)
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

     public synchronized String createMDAContingencyInitialValues(int mybf_id, int year_id) {
//System.out.println("mybf_id "+mybf_id+"       year_id "+year_id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.createMDAContingencyInitialValues :mybf_id, :year_id")
                    .setParameter("mybf_id", mybf_id)
                    .setParameter("year_id", year_id)
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

   public synchronized String deleteAllForms(int budget_year_id, int sub_sector_id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("DELETE FROM MTSS_MDA_Ceiling WHERE sub_sector_id="+sub_sector_id+" AND budget_year_id BETWEEN "+budget_year_id+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+(budget_year_id-1)+")) ").executeUpdate();
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
    }

   public synchronized String deleteAllSupplementaryForms(int budget_year_id, int sub_sector_id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("DELETE FROM MTSS_MDA_Ceiling_Supplementary WHERE sub_sector_id="+sub_sector_id+" AND budget_year_id BETWEEN "+budget_year_id+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+(budget_year_id-1)+")) ").executeUpdate();
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
    }

   public synchronized String deleteAllContingencyForms(int budget_year_id, int sub_sector_id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("DELETE FROM MTSS_MDA_Ceiling_Contingency WHERE sub_sector_id="+sub_sector_id+" AND budget_year_id BETWEEN "+budget_year_id+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+(budget_year_id-1)+")) ").executeUpdate();
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
    }

        
    /* MtssMdaCeiling methods end */

}
