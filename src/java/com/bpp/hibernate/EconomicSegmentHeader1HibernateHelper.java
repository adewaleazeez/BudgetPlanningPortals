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
public class EconomicSegmentHeader1HibernateHelper {

    /* EconomicSegment methods begin */
    public synchronized String insert(EconomicSegmentHeader1 economicSegment) {
        EconomicSegmentHeader1 checkEconomicSegment = exists(economicSegment.getName());
        if (checkEconomicSegment == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(economicSegment);
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

    public synchronized String update(EconomicSegmentHeader1 economicSegment) {
        System.out.println("Updating " + economicSegment.getName());
        EconomicSegmentHeader1 checkEconomicSegment = exists(economicSegment.getName());
        if (checkEconomicSegment == null || economicSegment.getId() == checkEconomicSegment.getId()) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(economicSegment);
                //session.getTransaction().commit();

                tx.commit();
            } catch (HibernateException e) {
                System.out.println("error: " + e.getMessage());
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

    public synchronized String delete(EconomicSegmentHeader1 economicSegment) {
        EconomicSegmentHeader1 checkEconomicSegment = fetchOne(economicSegment.getId() + "");
        if (checkEconomicSegment != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(economicSegment);
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

    public synchronized EconomicSegmentHeader1 exists(String name) {
        EconomicSegmentHeader1 economicSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from EconomicSegmentHeader1 as a where a.name='" + name + "'");
            //q.setMaxResults(1);
            economicSegment = (EconomicSegmentHeader1) q.uniqueResult();
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
        return economicSegment;
    }

    public synchronized String getMaxSerialNo(String tablename) {
        tablename = "dbo." + tablename;
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
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

    public synchronized String fetchAll() {
        List economicSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Economic_Segment_Header1 a where a.name<>'' order by a.code";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            economicSegmentsList = q.list();
//            Query q = session.createQuery("from EconomicSegment as a where a.name<>'' order by a.name");
//            economicSegmentsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(economicSegmentsList);
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

    public synchronized List fetchAllAsList(int segment) {
        List economicSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "";
            if (segment <= 0) {
                sql = "select * from Economic_Segment_Header1 a where a.name<>'' order by a.id";
            } else {
                sql = "select * from Economic_Segment_Header1 a where a.id='"+ segment +"' order by a.id";
            }
            //System.out.println("String sql  :" + sql);
            SQLQuery q = session.createSQLQuery(sql).addEntity(EconomicSegmentHeader1.class);
            economicSegmentsList = q.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return economicSegmentsList;
    }

    public synchronized EconomicSegmentHeader1 fetchOne(String id) {

        EconomicSegmentHeader1 economicSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from EconomicSegmentHeader1 as a where a.id='" + id + "'");
            economicSegment = (EconomicSegmentHeader1) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return economicSegment;

    }

    public synchronized String fetchOneJSON(String id) {
        List economicSegmentList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id,a.name,a.code from Economic_Segment_Header1 a where a.id = " + id + "  order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("code", StandardBasicTypes.STRING);
            q.setMaxResults(1);
            economicSegmentList = q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(economicSegmentList);
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
    /* EconomicSegment methods end */

}
