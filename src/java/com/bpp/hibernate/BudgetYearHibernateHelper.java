/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import com.bpp.utility.Utility;
import com.google.gson.Gson;

/**
 * Budget Year Hibernate Helper class
 * 
 * @author Lekan
 * @since 17/6/2017
 */
public class BudgetYearHibernateHelper {
	
	public static final String TABLE_NAME = "BudgetYears";
	public static final String RAW_TABLE_NAME = "Budget_Years";

    public synchronized String insert(BudgetYears budgetYear) {    	
        BudgetYears checkBudgetYear = exists(budgetYear.getYear());
        
        if (checkBudgetYear == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                
                session.save(budgetYear);

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
        	return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String update(BudgetYears budgetYear) {
        BudgetYears checker = exists(budgetYear.getYear());
        
        if (checker != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.merge(budgetYear);

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
    
    public synchronized String updateAll() {
    	final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "Update " + RAW_TABLE_NAME + " set is_current_base_year = 0";
            session.createSQLQuery(sql).executeUpdate();

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
    }

    public synchronized String setCurrentVersion() {
    	final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            session.createSQLQuery("Update Year_Budget_Versions set status=0").executeUpdate();
            
            String sql = "Update Year_Budget_Versions set status=1 where id=(select iif((select max(budget_version_id) as budgetversion from Year_Budget where budget_year_id=(select year from Budget_Years where is_current_base_year=1)+1) is null,1,(select max(budget_version_id) as budgetversion from Year_Budget where budget_year_id=(select year from Budget_Years where is_current_base_year=1)+1)))";
            session.createSQLQuery(sql).executeUpdate();

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
    }

    public synchronized String delete(BudgetYears budgetYear) {
        BudgetYears checker = exists(budgetYear.getYear());
        
        if (checker != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.delete(budgetYear);

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

    public synchronized BudgetYears exists(int year) {
        BudgetYears budgetYear = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as b where b.year = " + year);
            budgetYear = (BudgetYears) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return budgetYear;
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
    
    public synchronized BudgetYears fetchObj(int id) {
        BudgetYears budgetYear = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            budgetYear = (BudgetYears) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return budgetYear;
    }
   
    public synchronized BudgetYears fetchObjByYear(String year) {
        BudgetYears budgetYear = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.year = '" + year + "'");            
            budgetYear = (BudgetYears) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return budgetYear;
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
    
    public synchronized String fetchCurrentYear() {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id=(select id from " + RAW_TABLE_NAME + " as b where b.is_current_base_year = 1)+1";
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
    
    public synchronized BudgetYears fetchCurrentYear2() {
        BudgetYears budgetYear = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a where a.is_current_base_year = 1").addEntity(BudgetYears.class);            
            budgetYear = (BudgetYears) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return budgetYear;
    }

    public synchronized String fetchAll() {
    	List budgetYearList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select * from " + RAW_TABLE_NAME + " a order by a.year desc";
            
            SQLQuery q = session.createSQLQuery(sql);
            budgetYearList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(budgetYearList);
            
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
