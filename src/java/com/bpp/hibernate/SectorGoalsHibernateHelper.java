/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

/**
 *
 * @author Ola
 */
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

public class SectorGoalsHibernateHelper {

    public static final String TABLE_NAME = "SectorGoals";
    public static final String RAW_TABLE_NAME = "SectorGoals";

    /* SectorGoals methods begin */
    public synchronized String insert(SectorGoals sectorgoals) {
        SectorGoals checkSectorGoal = exists(sectorgoals.getName(), sectorgoals.getSectorGoalCode());
        //System.out.println(checkSectorGoal.getName()); 
        if (checkSectorGoal == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(sectorgoals);
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

    public synchronized String update(SectorGoals sectorgoals) {
        //SectorGoals checkSectorGoals = exists(sectorgoals.getName(), sectorgoals.getSectorGoalCode());
        SectorGoals checkSectorGoals = exists(sectorgoals.getId());
        //if (checkSectorGoals != null && (checkSectorGoals.getId() == sectorgoals.getId() && checkSectorGoals.getName().equals(sectorgoals.getName())) {
        if (checkSectorGoals != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(sectorgoals);
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

    public synchronized String delete(SectorGoals sectorgoals) {
        SectorGoals checkSectorGoals = exists(sectorgoals.getId());
        if (checkSectorGoals != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(sectorgoals);
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

    public synchronized SectorGoals exists(String name, String code) {
        SectorGoals sectorgoals = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from SectorGoals as a where a.name='" + name + "' or a.sectorGoalCode='" + code + "'");
            //;            
            sectorgoals = (SectorGoals) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            sectorgoals= null;
        } finally {
            //session.close();
        }
        return sectorgoals;
    }

    public synchronized SectorGoals exists(int id) {
        SectorGoals sectorgoals = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from SectorGoals as a where a.id='" + id + "'");

            sectorgoals = (SectorGoals) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return sectorgoals;
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

    public synchronized SectorGoals fetchObj(int id) {
        SectorGoals sectorgoals = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            sectorgoals = (SectorGoals) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return sectorgoals;
    }

    public synchronized String fetch(int id) {
        List sectorgoalsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;

            SQLQuery q = session.createSQLQuery(sql);
            sectorgoalsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(sectorgoalsList);

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

    public synchronized String fetchAll() {
        List sectorgoalsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from SectorGoals a where a.name<>'' order by a.sector_goal_code";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            sectorgoalsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(sectorgoalsList);
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
