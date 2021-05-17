/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

import static com.bpp.hibernate.SectorHibernateHelper.TABLE_NAME;
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
public class MtssSectorCeilingHibernateHelper {

	public static final String TABLE_NAME = "MtssSectorCeiling";
	public static final String RAW_TABLE_NAME = "Mtss_Sector_Ceiling";

    /* MtssSectorCeiling methods begin */
    public synchronized String insert(MtssSectorCeiling mtsssectorceiling) {
        MtssSectorCeiling checkSector = exists(mtsssectorceiling.getId());
        if (checkSector == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(mtsssectorceiling);
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

    public synchronized String update(MtssSectorCeiling mtsssectorceiling) {
        MtssSectorCeiling checkSector = exists(mtsssectorceiling.getId());
        if (checkSector == null || checkSector.getId() == mtsssectorceiling.getId()) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(mtsssectorceiling);
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

    public synchronized String delete(MtssSectorCeiling mtsssectorceiling) {
        MtssSectorCeiling checkSector = exists(mtsssectorceiling.getId());
        if (checkSector != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(mtsssectorceiling);
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

    public synchronized MtssSectorCeiling exists(String id) {
        MtssSectorCeiling mtsssectorceiling = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from MtssSectorCeiling as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            mtsssectorceiling = (MtssSectorCeiling) q.uniqueResult();
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
        return mtsssectorceiling;
    }

    public synchronized MtssSectorCeiling exists(int id) {
        MtssSectorCeiling mtsssectorceiling = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from MtssSectorCeiling as a where a.id='" + id + "'");
            
            mtsssectorceiling = (MtssSectorCeiling) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return mtsssectorceiling;
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
        
    public synchronized String fetchYearMenu(int id) {
        List mtsssectorceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = " SELECT DISTINCT year, (SELECT DISTINCT b.mybf_contigency_value FROM Request_Details a, Budget_Versions b "
                        + " WHERE a.request_type_id=1  AND a.fully_approved=1 AND b.id=a.version_id) as mybf_contigency_value FROM Budget_Years " 
                        + " WHERE is_current_base_year=1 UNION SELECT DISTINCT a.budget_year, '1' FROM MYBF_Previous_Forward a WHERE a.budget_year "
                        + " BETWEEN (SELECT year+1 FROM Budget_Years where is_current_base_year=1) AND "
                        + " (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b "
                        + " WHERE a.request_type_id="+ id + "  AND a.fully_approved=1 AND b.id=a.version_id) GROUP BY a.budget_year " ;
            SQLQuery q = session.createSQLQuery(sql);
            mtsssectorceilingsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(mtsssectorceilingsList);
            
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

    public synchronized MtssSectorCeiling fetchObj(int id) {
        MtssSectorCeiling mtsssectorceiling = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            mtsssectorceiling = (MtssSectorCeiling) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return mtsssectorceiling;
    }
    
    public synchronized MtssSectorCeiling fetchObjById(int budget_year_id, int budget_type_component_id) {
        MtssSectorCeiling sector = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            //Query q = session.createQuery("from " + TABLE_NAME + " as a where a.sectors.id = " + sector_id + " and a.budgetYears.id = " + budget_year_id + " and a.budgetTypeComponents.id = " + budget_type_component_id);            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.budgetYears.id = " + budget_year_id + " and a.budgetTypeComponents.id = " + budget_type_component_id);            
            sector = (MtssSectorCeiling) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return sector;
    }
    
    public synchronized String fetch(int id) {
        List mtsssectorceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;
            
            SQLQuery q = session.createSQLQuery(sql);
            mtsssectorceilingsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(mtsssectorceilingsList);
            
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
        List mtsssectorceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Mtss_Sector_Ceiling a where a.id<>'0' order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            mtsssectorceilingsList = q.list();
//            Query q = session.createQuery("from MtssSectorCeiling as a where a.name<>'' order by a.name");
//            mtsssectorceilingsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtsssectorceilingsList);
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
    
    public synchronized String fetchHeadrs() {
        List mtsssectorceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT 0 as id, 'Revenue' as name, '1' AS orderkey UNION "
                 + "select c.id, c.name, '1' AS orderkey from Budget_type_Components c where c.budget_type_id=2 AND c.is_budgeted=1 ORDER BY orderkey";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            mtsssectorceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtsssectorceilingsList);
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
    
    public synchronized String fetchDatalist(int mybf_id, int year_id) {
        List mtsssectorceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            /*String sql = "SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, "
                 + " '1' as budget_type_component_id, (SELECT SUM(a.budget_value) FROM MYBF_Figures a) as budget_value, '1' AS orderkey "
                 + " FROM MYBF_Figures a,  Budget_Years b WHERE a.version_id=(SELECT version_id FROM Request_Details WHERE "
                 + " request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ") AND a.budget_type_component_id IN "
                 + " (select c.id from Budget_type_Components c where c.budget_type_id=1 AND c.is_budgeted=1) "
                 + " UNION ALL SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id, "
                 + " a.budget_value, '1' AS orderkey FROM MYBF_Figures a,  Budget_Years b WHERE a.version_id=(SELECT version_id "
                 + " FROM Request_Details WHERE request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ") AND "
                 + " a.budget_type_component_id IN (select c.id from Budget_type_Components c where c.budget_type_id=2 AND c.is_budgeted=1) "
                 + " UNION ALL SELECT DISTINCT a.budget_year, '1' as budget_type_component_id, SUM(a.budget_value) as budget_value, "
                 + " '1' AS orderkey FROM MYBF_Previous_Forward a WHERE a.budget_year BETWEEN (SELECT year+1 FROM Budget_Years where "
                 + " is_current_base_year=1) AND (SELECT DISTINCT b.mtefToYear FROM Request_Details a, Budget_Versions b "
                 + " WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 AND b.id=a.version_id AND a.budget_year_id ="+ year_id + ") GROUP BY a.budget_year"
                 + " UNION ALL SELECT DISTINCT a.budget_year, (SELECT b.budget_type_component_id FROM MYBF_Figures b WHERE "
                 + " b.id=a.mybf_figure_id) as budget_type_component_id, a.budget_value, '1' AS orderkey FROM MYBF_Previous_Forward a WHERE mybf_figure_id IN "
                 + " (SELECT DISTINCT a.id FROM MYBF_Figures a,  Budget_Years b WHERE a.version_id=(SELECT version_id FROM Request_Details WHERE "
                 + " request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ") AND a.budget_type_component_id IN "
                 + " (select c.id from Budget_type_Components c where c.budget_type_id=2 AND c.is_budgeted=1)) AND (a.budget_year BETWEEN "
                 + " (SELECT year+1 FROM Budget_Years where is_current_base_year=1) AND (SELECT DISTINCT b.mtefToYear FROM Request_Details a, "
                 + " Budget_Versions b WHERE a.request_type_id="+ mybf_id + " AND a.fully_approved=1 AND b.id=a.version_id AND a.budget_year_id ="+ year_id + ")) "
                 + " ORDER BY budget_year, orderkey, budget_type_component_id ";*/
            
            String sql = "select (select year from Budget_Years where is_current_base_year=1) as budget_year,  '1' as budget_type_component_id, (SELECT sum(budget_value) " +
                    " FROM MYBF_Figures  WHERE version_id=(SELECT version_id FROM Request_Details WHERE request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id=(select id " +
                    " from Budget_Years where is_current_base_year=(select id from Budget_Years where is_current_base_year=1))) AND budget_type_component_id IN  " +
                    " (select id from Budget_type_Components where budget_type_id=1 AND is_budgeted=0)) as budget_value, '1' AS orderkey" +
                    " UNION ALL select (select year from Budget_Years where is_current_base_year=1)+1 as budget_year,  '1' as budget_type_component_id, " +
                    " (SELECT sum(budget_value) FROM MYBF_Previous_Forward where budget_year=(select year from Budget_Years where is_current_base_year=1)+1 and mybf_figure_id IN " +
                    " (select id from MYBF_Figures where budget_type_component_id IN (select id from Budget_type_Components where budget_type_id=1 AND is_budgeted=0 and " +
                    " version_id=(SELECT version_id FROM Request_Details WHERE  request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ")) and " + 
                    " budget_year_id="+ year_id + ")) as budget_value, '1' AS orderkey" +
                    " UNION ALL select (select year from Budget_Years where is_current_base_year=1)+2 as budget_year,  '1' as budget_type_component_id, " +
                    " (SELECT sum(budget_value) FROM MYBF_Previous_Forward where budget_year = (select year from Budget_Years where is_current_base_year=1)+2 and mybf_figure_id IN " +
                    " (select id from MYBF_Figures where budget_type_component_id IN (select id from Budget_type_Components where budget_type_id=1 AND is_budgeted=0 and " +
                    " version_id=(SELECT version_id FROM Request_Details WHERE  request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ")) and " + 
                    " budget_year_id="+ year_id + ")) as budget_value, '1' AS orderkey" +
                    " UNION ALL select (select year from Budget_Years where is_current_base_year=1)+3 as budget_year,  '1' as budget_type_component_id, " +
                    " (SELECT sum(budget_value) FROM MYBF_Previous_Forward where budget_year = (select year from Budget_Years where is_current_base_year=1)+3 and mybf_figure_id IN " +
                    " (select id from MYBF_Figures where budget_type_component_id IN (select id from Budget_type_Components where budget_type_id=1 AND is_budgeted=0 and " +
                    " version_id=(SELECT version_id FROM Request_Details WHERE  request_type_id="+ mybf_id + " AND fully_approved=1 AND budget_year_id="+ year_id + ")) and " + 
                    " budget_year_id="+ year_id + ")) as budget_value, '1' AS orderkey" +
                    " UNION ALL SELECT DISTINCT (SELECT b.year from Budget_Years b WHERE a.budget_year_id=b.id) as budget_year, a.budget_type_component_id, a.total_amount as budget_value, " + 
                    " '1' AS orderkey FROM MTSS_SubSector_Ceiling a WHERE a.Sub_sector_id=0  ORDER BY budget_year, orderkey, budget_type_component_id";
            //sql = "select productid from stockin";
            //System.out.println("sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            mtsssectorceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtsssectorceilingsList);
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
    
        public synchronized String fetchAllEnvelope(String budget_year_id) {
        List mtsssectorceilingsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = " SELECT concat((SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), '_', budget_type_component_id, '_', sector_id) as id, "
                + " d.total_amount FROM MTSS_Sector_Ceiling d WHERE budget_year_id BETWEEN "+budget_year_id+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+budget_year_id+")) "
                + " ORDER BY budget_year_id, budget_type_component_id, sector_id ";
            //(SELECT e.year FROM Budget_Years e WHERE e.id=d.budget_year_id), d.budget_type_component_id, d.sector_id, 
            SQLQuery q = session.createSQLQuery(sql);
            mtsssectorceilingsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mtsssectorceilingsList);
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

    public synchronized String distributeEnvelopes(int mybf_id, int year_id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.distributeSectorEnvelopes :mybf_id, :year_id")
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

    public synchronized String deleteAllForms(int budget_year_id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("DELETE FROM MTSS_Sector_Ceiling WHERE budget_year_id BETWEEN "+budget_year_id+" AND "
                + " (SELECT a.id FROM Budget_Years a WHERE year = (SELECT DISTINCT c.mtefToYear FROM Request_Details b, Budget_Versions c "
                + " WHERE b.request_type_id=1 AND b.fully_approved=1 AND c.id=b.version_id AND b.budget_year_id ="+budget_year_id+")) ").executeUpdate();

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

        
    /* MtssSectorCeiling methods end */

}
