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
 * MTEF Previous Forward Hibernate helper class
 * 
 * @author Lekan
 * @since 1/7/2017
 */
public class MybfPreviousForwardHibernateHelper {
	
	public static final String TABLE_NAME = "MybfPreviousForward";
	public static final String RAW_TABLE_NAME = "MYBF_Previous_Forward";

    public synchronized String insert(MybfPreviousForward btt) {    	
        MybfPreviousForward checker = exists(btt.getBudgetYear(), btt.getMybfFigureId());
        
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
            	
            }
            
            return Utility.ActionResponse.INSERTED.toString();
        } else {
        	return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String update(MybfPreviousForward btt) {
    	MybfPreviousForward checker = exists(btt.getBudgetYear(), btt.getMybfFigureId());
        
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
    
    public synchronized String updateMybfPreviousForward(int versionID, int year) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.updateMybfPreviousForward :versionID, :year")
                    .setParameter("versionID", versionID)
                    .setParameter("year", year)
                    .executeUpdate();
                    
            tx.commit();
            return Utility.ActionResponse.UPDATED.toString();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return Utility.ActionResponse.FAILED.toString();
        } finally {
            //session.close();
        }
    }

    public synchronized String delete(MybfPreviousForward btt) {
    	MybfPreviousForward checker = exists(btt.getBudgetYear(), btt.getMybfFigureId());
        
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
    
    public synchronized MybfPreviousForward exists(int year, int mybfFigureID) {
        MybfPreviousForward btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a " +
            								 "where a.budget_year = " + year + " and " +
            								 "a.mybf_figure_id = " + mybfFigureID).addEntity(MybfPreviousForward.class);           
	        btt = (MybfPreviousForward) q.uniqueResult();

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
    
    public synchronized MybfPreviousForward fetchObj(int id) {
        MybfPreviousForward obj = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            obj = (MybfPreviousForward) q.uniqueResult();
            
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
    
    public synchronized MybfPreviousForward fetchObj(int mybyFigureID, int year) {
        MybfPreviousForward obj = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a " +
            								 "where a.mybf_figure_id = " + mybyFigureID + " " +
            								 "and a.budget_year = " + year).addEntity(MybfPreviousForward.class);            
            obj = (MybfPreviousForward) q.uniqueResult();
            
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
            
        }
        
        return jsonList;
    }
    
    public synchronized String fetchAll(int mybfFigureID, int budgetTypeComponentID, int yearFrom, int yearTo) {
        List meetingDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.budget_year, a.budget_value from " + RAW_TABLE_NAME + " as a " +
		       			 "where a.budget_type_component_id = " + budgetTypeComponentID + " and mybf_figure_id = " + mybfFigureID + " and " +
		       			 "a.budget_year between " + yearFrom + " and " + yearTo + " order by a.budget_year";
            //System.out.println("sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            meetingDetailsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(meetingDetailsList);
            
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
    
    @SuppressWarnings("unchecked")
	public synchronized List<MybfPreviousForward> fetchAll(int year) {
        List<MybfPreviousForward> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select * from " + RAW_TABLE_NAME + " a " + 
		       			 "where a.budget_year = " + year + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql).addEntity(RequestAgents.class);
            objList = (List<MybfPreviousForward>) q.list();
            
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
}
