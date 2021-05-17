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
 * Personnel Cost Items Hibernate helper class
 *
 * @author Lekan
 * @since 19/10/2017
 */
public class SAPersonnelHibernateHelper {

	public static final String TABLE_NAME = "SapPersonnel";
	public static final String RAW_TABLE_NAME = "SAP_Personnel";

    public synchronized String insert(SapPersonnel btt) {    	
        SapPersonnel checker = exists(btt);
        
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

    public synchronized String update(SapPersonnel btt) {
    	SapPersonnel checker = exists(btt.getId());
        
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

    public synchronized String delete(SapPersonnel btt) {
    	SapPersonnel checker = exists(btt.getId());
        
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
    
    public synchronized SapPersonnel exists(String id) {
        SapPersonnel role = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id='" + id + "'");
            
            role = (SapPersonnel) q.uniqueResult();

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
    
    public synchronized SapPersonnel exists(SapPersonnel butt) {
        SapPersonnel btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.MANDT='" + butt.getMandt()
                    + "' and a.FISCAL_YEAR='" + butt.getFiscalYear() + "' and a.ADMIN_SEGMENT='" + butt.getAdminSegment()
                    + "' and a.MDA_ORG_ID='" + butt.getMdaOrgId() + "' and a.DEPT_UNIT_ID='" + butt.getDeptUnitId()
                    + "' and a.COST_CENTER='" + butt.getCostCenter() + "' and a.POS_JOB_ID='" + butt.getPosJobId()
                    + "' and a.SAL_GRADE='" + butt.getSalGrade() + "'");           
	        btt = (SapPersonnel) q.uniqueResult();

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

    public synchronized SapPersonnel exists(int id) {
        SapPersonnel btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id='" + id + "'");           
	        btt = (SapPersonnel) q.uniqueResult();

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
    
    public synchronized SapPersonnel fetchObj(int id) {
        SapPersonnel obj = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a where a.id = " + id).addEntity(SapPersonnel.class);            
            obj = (SapPersonnel) q.uniqueResult();
            
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
    
    public synchronized String fetchAll(int year) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.* from " + RAW_TABLE_NAME + " a where a.FISCAL_YEAR = '" + year + "' order by a.id";
            
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
    
    public synchronized String fetchAllYears() {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select distinct FISCAL_YEAR from " + RAW_TABLE_NAME + " order by FISCAL_YEAR";
            
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
