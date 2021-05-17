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
 * Personnel Rel Info Hibernate helper class
 *
 * @author Lekan
 * @since 19/10/2017
 */
public class PersonnelRelInfoHibernateHelper {

	public static final String TABLE_NAME = "PersonnelRelInfo";
	public static final String RAW_TABLE_NAME = "Personnel_Rel_Info";

    public synchronized String insert(PersonnelRelInfo btt) {    	
        PersonnelRelInfo checker = exists(btt.getId());
        
        if (checker == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.save(btt);

                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return Utility.ActionResponse.FAILED.toString();
            } finally {
            	
            }
            
            return Utility.ActionResponse.INSERTED.toString();
        } else {
        	return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String update(PersonnelRelInfo btt) {
    	PersonnelRelInfo checker = exists(btt.getId());
        
        if (checker != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.merge(btt);

                tx.commit();
            } catch (HibernateException e) {
                System.out.println("error: "+e.getMessage());
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return "";
            } finally {
            	
            }
            
            return Utility.ActionResponse.UPDATED.toString();
        } else {
        	return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(PersonnelRelInfo btt) {
    	PersonnelRelInfo checker = exists(btt.getId());
        
        if (checker != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.delete(btt);

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
    
    public synchronized PersonnelRelInfo exists(String id) {
        PersonnelRelInfo role = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id='" + id + "'");
            
            role = (PersonnelRelInfo) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            
        }
        return role;
    }
    
    public synchronized PersonnelRelInfo exists(int id) {
        PersonnelRelInfo btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);           
	        btt = (PersonnelRelInfo) q.uniqueResult();

            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
        	
        }
        
        return btt;
    }

    public synchronized String getMaxserialNo() {
        String tablename = "dbo." + RAW_TABLE_NAME;
        
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
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
        	
        }
        return resp;
    }
    
    public synchronized PersonnelRelInfo fetchObj(int id) {
        PersonnelRelInfo obj = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a where a.id = " + id).addEntity(PersonnelRelInfo.class);            
            obj = (PersonnelRelInfo) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	
        }
        
        return obj;
    }
    
    public synchronized String fetch(int id) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = '" + id + "'";
            
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
            
        }
        
        return jsonList;
    }
    
    public synchronized PersonnelRelInfo fetchObj(String runID, int year, String objectType, String objectID) {
    	PersonnelRelInfo obj = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a " +
            		           				 "where a.run_id = '" + runID + "' and year = " + year + " and " +
            								 "object_type = '" + objectType + "' and object_id = '" + objectID + "'").addEntity(PersonnelRelInfo.class);            
            obj = (PersonnelRelInfo) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	
        }
        
        return obj;
    }
    
    public synchronized String fetchAll(int year) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.* from " + RAW_TABLE_NAME + " a where a.year = " + year + " order by a.id";
            
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
            
        }
        
        return jsonList;
    }
}
