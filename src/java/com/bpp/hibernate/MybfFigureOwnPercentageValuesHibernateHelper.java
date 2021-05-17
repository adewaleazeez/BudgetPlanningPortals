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
 * MTEF Figure Own Percentage Values Hibernate helper class
 * 
 * @author Lekan
 * @since 15/7/2017
 */
public class MybfFigureOwnPercentageValuesHibernateHelper {
	
	public static final String TABLE_NAME = "MybfFigureOwnPercentageValues";
	public static final String RAW_TABLE_NAME = "MYBF_Figure_Own_Percentage_Values";

    public synchronized String insert(MybfFigureOwnPercentageValues btt) {    	
        MybfFigureOwnPercentageValues checker = exists(btt.getMybfFigureId(), btt.getYear());
        
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
                return "";
            } finally {
            	//session.close();
            }
            
            return Utility.ActionResponse.INSERTED.toString();
        } else {
        	return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String update(MybfFigureOwnPercentageValues btt) {
    	MybfFigureOwnPercentageValues checker = exists(btt.getMybfFigureId(), btt.getYear());
        
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
            	//session.close();
            }
            
            return Utility.ActionResponse.UPDATED.toString();
        } else {

        	return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(MybfFigureOwnPercentageValues btt) {
    	MybfFigureOwnPercentageValues checker = exists(btt.getMybfFigureId(), btt.getYear());
        
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
            	//session.close();
            }

            return Utility.ActionResponse.DELETED.toString();
        } else {

        	return Utility.ActionResponse.NO_RECORD.toString();
        }
    }
    
    public synchronized MybfFigureOwnPercentageValues exists(int mybfFigureID, int year) {
        MybfFigureOwnPercentageValues btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a " +
            								 "where a.year = " + year + " and " +
            								 "a.mybf_figure_id = " + mybfFigureID).addEntity(MybfFigureOwnPercentageValues.class);           
	        btt = (MybfFigureOwnPercentageValues) q.uniqueResult();

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
        	//session.close();
        }
        return resp;
    }
    
    public synchronized MybfFigureOwnPercentageValues fetchObj(int id) {
        MybfFigureOwnPercentageValues obj = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            obj = (MybfFigureOwnPercentageValues) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
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
            
            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;
            
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
    
    public synchronized String fetchAll(int mybfFigureID) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.percentage, a.year from " + RAW_TABLE_NAME + " as a " +
		       			 "where a.mybf_figure_id = " + mybfFigureID + " order by a.id";
            
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
}
