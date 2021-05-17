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
 * @author Ola
 */
public class ScoringHibernateHelper {

    public static final String TABLE_NAME = "Scoring";
    public static final String RAW_TABLE_NAME = "Scoring";

    /* Scoring methods begin */
    public synchronized String Insert(Scoring scoring) {
        System.out.println(scoring.getId() + "   " + scoring.getProjectCode() + "   " + scoring.getProjectYear());
        Scoring theseScores = exists(scoring.getId(), scoring.getProjectCode(), scoring.getProjectYear());
        if (theseScores == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                //session.getTransaction().begin();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(scoring);
                //session.getTransaction().commit();
                tx.commit();
            } catch (HibernateException e) {
                //System.out.println("Error +++:" + e.getMessage());
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

    public  String insert(Scoring scc) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String outstring = "";
        Transaction tx = session.beginTransaction();
        try {
            session.save(scc);
            session.flush(); 
            tx.commit(); 
            System.out.println("Records inserted");
            outstring = "Records inserted";
        } catch (Exception e) {
            tx.rollback();                            // con.rollback();
        }
        return outstring;
    }

    public synchronized String update(Scoring scoring) {
        Scoring checkScoring = exists(scoring.getId());
        if (checkScoring != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(scoring);
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
            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(Scoring scoring) {
        Scoring checkScoring = exists(scoring.getId());
        if (checkScoring != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(scoring);
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

    public synchronized Scoring exists(int id, String project_code, int year_id) {
        Scoring scoring = null;

        final Session session = HibernateUtil.getSessionFactory().openSession();//HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = " Select * from Scoring as a where a.project_code='" + project_code + "' and a.project_year ='" + year_id + "' and a.id='" + id + "'";
            //String sql= " Select * from Scoring as a where a.project_code='" + project_code + "' and a.project_year ='" + year_id + "'";
            Query q = session.createSQLQuery(sql).addEntity(Scoring.class);//("from Scoring as a where a.project_code='" + project_code + "' and a.project_year ='" + year_id + "'");// and a.id='"+ id +"'");

            //q.setMaxResults(1);
            scoring = (Scoring) q.uniqueResult();

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

        return scoring;
    }

    public synchronized List exists(String project_code, int year_id) {
        List scoring = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql= " Select * from Scoring as a where a.project_code='" + project_code + "' and a.project_year ='" + year_id + "' and a.id='"+ id +"'";
            String sql = " Select * from Scoring as a where a.project_code='" + project_code + "' and a.project_year ='" + year_id + "' ORDER BY a.criteria_id DESC";
            Query q = session.createSQLQuery(sql).addEntity(Scoring.class);//("from Scoring as a where a.project_code='" + project_code + "' and a.project_year ='" + year_id + "'");// and a.id='"+ id +"'");

            //q.setMaxResults(1);
            List scr = q.list();
            if (scr.isEmpty()) {
                return null;
            } else {
                scoring = scr;
            }
            //scoring = (Scoring) scr.get(0);// q.uniqueResult();

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
        return scoring;
    }

    public synchronized Scoring exists(int id) {
        Scoring btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            btt = (Scoring) q.uniqueResult();
        
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return btt;
    }

    public synchronized Scoring exists(String code, String year, String criteria) {
        Scoring btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.projectCode='" + code + "' and a.projectYear='" + year + "' and a.criteriaId='" + criteria + "'");
            btt = (Scoring) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return btt;
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

    /*id
project_code
project_year
criteria_id
score
date_created
org_id
     */
    public synchronized String fetchAll(String code, String year) {
        List scoringList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select * from Scoring where project_code='" + code + "' and project_year='" + year + "' order by criteria_id ";
            System.out.println("****" + sql);
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("project_code", StandardBasicTypes.STRING)
                    .addScalar("project_year", StandardBasicTypes.INTEGER)
                    .addScalar("criteria_id", StandardBasicTypes.INTEGER)
                    .addScalar("score", StandardBasicTypes.FLOAT)
                    .addScalar("date_created", StandardBasicTypes.DATE)
                    .addScalar("org_id", StandardBasicTypes.INTEGER);

            scoringList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(scoringList);
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
        List scoringList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select * from Scoring as a where a.id <> ''  order by a.criteria_id ";

            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("project_code", StandardBasicTypes.STRING)
                    .addScalar("project_year", StandardBasicTypes.STRING)
                    .addScalar("criteria_id", StandardBasicTypes.INTEGER)
                    .addScalar("score", StandardBasicTypes.FLOAT)
                    .addScalar("date_created", StandardBasicTypes.DATE)
                    .addScalar("org_id", StandardBasicTypes.INTEGER);

            scoringList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(scoringList);
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

    public synchronized String fetchOne(String id) {
        List scoringList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select * from Scoring as a where a.id='" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("project_code", StandardBasicTypes.STRING)
                    .addScalar("project_year", StandardBasicTypes.STRING)
                    .addScalar("criteria_id", StandardBasicTypes.INTEGER)
                    .addScalar("score", StandardBasicTypes.FLOAT)
                    .addScalar("date_created", StandardBasicTypes.DATE)
                    .addScalar("org_id", StandardBasicTypes.INTEGER);
            scoringList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(scoringList);
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

    public synchronized Scoring fetchObj(int id) {
        Scoring scoring = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            scoring = (Scoring) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return scoring;
    }

    public synchronized String fetch(int id) {
        List scoringList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;

            SQLQuery q = session.createSQLQuery(sql);
            scoringList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(scoringList);

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
