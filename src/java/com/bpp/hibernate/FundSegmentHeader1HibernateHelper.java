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
public class FundSegmentHeader1HibernateHelper {

    /* FundSegment methods begin */
    public synchronized String insert(FundSegmentHeader1 fundSegment) {
        FundSegmentHeader1 checkFundSegment = exists(fundSegment.getName());
        if (checkFundSegment == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(fundSegment);
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
    public synchronized String update(FundSegmentHeader1 fundSegment) {
        FundSegmentHeader1 checkFundSegment = exists(fundSegment.getName());
        if (checkFundSegment == null || fundSegment.getId() == checkFundSegment.getId()) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(fundSegment);
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
    public synchronized String delete(FundSegmentHeader1 fundSegment) {
        FundSegmentHeader1 checkFundSegment = fetchOne(fundSegment.getId()+"");
        if (checkFundSegment != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(fundSegment);
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
    public synchronized FundSegmentHeader1 exists(String name) {
        FundSegmentHeader1 fundSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Fund_Segment_Header1 as a where a.name='" + name + "'").addEntity(FundSegmentHeader1.class);
            //q.setMaxResults(1);
            fundSegment = (FundSegmentHeader1) q.uniqueResult();
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
        return fundSegment;
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
    public synchronized String fetchAll() {
        List fundSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Fund_Segment_Header1 a where a.name<>'' order by a.name";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            fundSegmentsList = q.list();
//            Query q = session.createQuery("from FundSegmentHeader1 as a where a.name<>'' order by a.name");
//            fundSegmentsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(fundSegmentsList);
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
    public synchronized FundSegmentHeader1 fetchOne(String id){
        
        FundSegmentHeader1 fundSegment = null;
        
       
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Fund_Segment_Header1 as a where a.id='" + id + "'").addEntity(FundSegmentHeader1.class);
            fundSegment = (FundSegmentHeader1) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        
        return fundSegment;
        
    }    
    public synchronized String fetchOneJSON(String id) {
        List fundSegmentList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id,a.name,a.code from Fund_Segment_Header1 a where a.id = "+id+"  order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("name",StandardBasicTypes.STRING)
                    .addScalar("code",StandardBasicTypes.INTEGER);
            q.setMaxResults(1);
            fundSegmentList = q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(fundSegmentList);
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
    /* FundSegment methods end */

}
