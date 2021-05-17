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
public class ProgrammeSegmentHeader2HibernateHelper {

    /* ProgrammeSegment methods begin */
    public synchronized String insert(ProgrammeSegmentHeader2 programmeSegment) {
        ProgrammeSegmentHeader2 checkProgrammeSegment = exists(programmeSegment.getName());
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

    public synchronized String update(ProgrammeSegmentHeader2 programmeSegment) {
        System.out.println("Updating " + programmeSegment.getName());
        ProgrammeSegmentHeader2 checkProgrammeSegment = exists(programmeSegment.getName());
        if (checkProgrammeSegment == null || programmeSegment.getId() == checkProgrammeSegment.getId()) {
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

    public synchronized String delete(ProgrammeSegmentHeader2 programmeSegment) {
        ProgrammeSegmentHeader2 checkProgrammeSegment = fetchOne(programmeSegment.getId() + "");
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

    public synchronized ProgrammeSegmentHeader2 exists(String name) {
        ProgrammeSegmentHeader2 programmeSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader2 as a where a.name='" + name + "'");
            //q.setMaxResults(2);
            programmeSegment = (ProgrammeSegmentHeader2) q.uniqueResult();
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
        List programmeSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Programme_Segment_Header2 a where a.name<>'' order by a.name";
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
    public synchronized String fetchByMDA(Integer mda) {
        List programmeSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            String sql;
                sql = "select * from Programme_Segment_Header3 a where a.mda = mda";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            programmeSegmentsList = q.list();

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

    public synchronized String fetchAll(Integer parent) {
        List programmeSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql;
            if(parent!=-1)
                sql = "select * from Programme_Segment_Header2 a where a.parent = " + parent + "order by a.name";
            else
                sql = "select * from Programme_Segment_Header2 a where a.parent <>'' order by a.id";
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

    public synchronized ProgrammeSegmentHeader2 fetchOne(String id) {

        ProgrammeSegmentHeader2 programmeSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader2 as a where a.id='" + id + "'");
            programmeSegment = (ProgrammeSegmentHeader2) q.uniqueResult();
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
            String sql = "select a.id,a.name,a.code,a.parent from Programme_Segment_Header2 a where a.id = " + id + "  order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("code", StandardBasicTypes.STRING).addScalar("parent", StandardBasicTypes.INTEGER);
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

    public synchronized String fetchCode(String parent, int sz) {
        List programmeSegmentList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "Select MAX(  RIGHT(a.code, LEN(a.code) - '" + sz + "')) AS NewCode from Programme_Segment_Header2 as a where  a.parent= '" + parent + "'";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("NewCode", StandardBasicTypes.STRING);
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
