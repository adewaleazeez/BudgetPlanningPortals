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
public class ProgrammeSegmentHeader1HibernateHelper {

    /* ProgrammeSegment methods begin */
    public synchronized String insert(ProgrammeSegmentHeader1 programmeSegment) {
        ProgrammeSegmentHeader1 checkProgrammeSegment = exists(programmeSegment.getName());
        if (checkProgrammeSegment == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(programmeSegment);
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

    public synchronized String update(ProgrammeSegmentHeader1 programmeSegment) {
        System.out.println("Updating "+programmeSegment.getName());
        ProgrammeSegmentHeader1 checkProgrammeSegment = exists(programmeSegment.getName());
        if (checkProgrammeSegment == null  || programmeSegment.getId() == checkProgrammeSegment.getId()) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(programmeSegment);
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

    public synchronized String delete(ProgrammeSegmentHeader1 programmeSegment) {
        ProgrammeSegmentHeader1 checkProgrammeSegment = fetchOne(programmeSegment.getId()+"");
        if (checkProgrammeSegment != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(programmeSegment);
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

    public synchronized ProgrammeSegmentHeader1 exists(String name) {
        ProgrammeSegmentHeader1 programmeSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader1 as a where a.name='" + name + "'");
            //q.setMaxResults(1);
            programmeSegment = (ProgrammeSegmentHeader1) q.uniqueResult();
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
        return programmeSegment;
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
            System.out.println(sql);
            SQLQuery q = session.createSQLQuery(sql);
            System.out.println("Gets data. I'm here");
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
        List programmeSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Programme_Segment_Header1 a where a.name<>'' order by a.name";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            programmeSegmentsList = q.list();
//            Query q = session.createQuery("from ProgrammeSegment as a where a.name<>'' order by a.name");
//            programmeSegmentsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(programmeSegmentsList);
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
    
   
    
    public synchronized ProgrammeSegmentHeader1 fetchOne(String id){
        
        ProgrammeSegmentHeader1 programmeSegment = null;
        
       
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader1 as a where a.id='" + id + "'");
            programmeSegment = (ProgrammeSegmentHeader1) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        
        return programmeSegment;
        
    }
    
    public synchronized String fetchOneJSON(String id) {
        List programmeSegmentList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id,a.name,a.code from Programme_Segment_Header1 a where a.id = "+id+"  order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("name",StandardBasicTypes.STRING)
                    .addScalar("code",StandardBasicTypes.STRING);
            q.setMaxResults(1);
            programmeSegmentList = q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(programmeSegmentList);
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
    /* ProgrammeSegment methods end */

}
