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
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Adewale
 */
public class ObjectiveHibernateHelper {
	public static final String TABLE_NAME = "Objectives";
	public static final String RAW_TABLE_NAME = "objectives";

    /* Objectives methods begin */
    public synchronized String insert(Objectives objective) {
        Objectives checkObjective = exists(objective.getCode(), objective.getName(), objective.getProgramme()+"");
        if (checkObjective == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(objective);
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

    public synchronized String update(Objectives objective) {
        Objectives checkObjective = exists(objective.getId());
        if (checkObjective != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(objective);
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

            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(Objectives objective) {
        Objectives checkObjective = exists(objective.getId());
        if (checkObjective != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(objective);
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

    public synchronized String updateSubSectorCode(String id, String code) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("update Objectives set code = '"+code+"' where id="+id).executeUpdate();

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
    }

    public synchronized Objectives exists(String code, String name, String programme) {
        Objectives objective = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //Query q = session.createQuery("from Objectives as a where a.name='" + name + "' and a.subsectors.id='" + sub_sector_id + "'");
            Query q = session.createQuery("from Objectives as a where a.programme='" + programme + "' and (a.code='" + code + "' or a.name='" + name + "') ");
            q.setMaxResults(1);
            objective = (Objectives) q.uniqueResult();
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
        return objective;
    }

    public synchronized Objectives exists(int id) {
        Objectives objective = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Objectives as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            objective = (Objectives) q.uniqueResult();
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
        return objective;
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

    public synchronized Objectives fetchObj(int id) {
        Objectives objective = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            objective = (Objectives) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return objective;
    }
    
    public synchronized String fetchAll() {
        List objectivesList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, a.name, (select b.name from Sub_Sectors b where a.sub_sector_id=b.id), administrative_segment from Objectives a where a.name<>'' and a.sub_sector_code<>'00' order by a.sub_sector_id, a.name";
            String sql = "select a.id, a.name, (select b.name from Sub_Sectors b where a.sub_sector_code=b.sub_sector_code), a.administrative_segment from Objectives a where a.name<>'' and a.sub_sector_code<>'00' order by a.name";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            objectivesList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objectivesList);
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
    
    public synchronized String fetchAll(String programme) {
        List objectivesList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.* from Objectives a where a.programme=(select id from programmes where id='"+programme+"') order by a.code";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            objectivesList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objectivesList);
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
	public synchronized List<Objectives> fetchAllCategorizedSector() {
        List<Objectives> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select * from " + RAW_TABLE_NAME + " a where a.sub_sector_code != '00' order by a.sub_sector_code, a.name";
            
            SQLQuery q = session.createSQLQuery(sql).addEntity(Objectives.class);
            objList = (List<Objectives>) q.list();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        
        return objList;
    }
    
    public synchronized String fetchAll(int sub_sector_id) {
        List objectiveList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select a.id, a.name, b.name as subsectorName, a.administrative_segment from " + RAW_TABLE_NAME + " a " +
            			 "inner join " + SubSectorHibernateHelper.RAW_TABLE_NAME + " b where a.sub_sector_id = b.id " +
       			 		 "and a.sub_sector_id = " + sub_sector_id + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql);
            objectiveList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(objectiveList);
            
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

    public synchronized String fetchOne(String id) {
        List objectivesList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select a.* from Objectives a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("programme",StandardBasicTypes.STRING)
                    .addScalar("code",StandardBasicTypes.STRING).addScalar("name",StandardBasicTypes.STRING)
                    .addScalar("datecreated",StandardBasicTypes.STRING).addScalar("org_id",StandardBasicTypes.STRING);

            objectivesList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(objectivesList);
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
    
    /* Objectives methods end */

}
