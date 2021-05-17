/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
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
public class ProgrammeSegmentHeader3HibernateHelper {

    /* ProgrammeSegment methods begin */
    public synchronized String insert(ProgrammeSegmentHeader3 programmeSegment) {
        ProgrammeSegmentHeader3 checkProgrammeSegment = exists(programmeSegment.getName());
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
            //Rank();
            return Utility.ActionResponse.INSERTED.toString();
        } else {
            //Rank();
            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String update(ProgrammeSegmentHeader3 programmeSegment) {
        ///System.out.println("Updating "+programmeSegment.getName());
        ProgrammeSegmentHeader3 checkProgrammeSegment = exists(programmeSegment.getName());
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
            //Rank();
            return Utility.ActionResponse.UPDATED.toString();
        } else {
//Rank();
            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String delete(ProgrammeSegmentHeader3 programmeSegment) {
        ProgrammeSegmentHeader3 checkProgrammeSegment = fetchOne(programmeSegment.getId() + "");
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
            //System.out.println("No programmeSegment 3 in Helper");
            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized ProgrammeSegmentHeader3 exists(String name) {
        ProgrammeSegmentHeader3 programmeSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader3 as a where a.name='" + name + "'");
            //q.setMaxResults(1);
            programmeSegment = (ProgrammeSegmentHeader3) q.uniqueResult();

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
            String sql = "select * from Programme_Segment_Header3 a where a.name<>'' order by a.name";
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

    public static Integer[] getRanksArray(Float[] array) {

        List<Float> list = Arrays.asList(array);
        Integer[] outArr = list.stream()
                .sorted()
                .map(e -> list.indexOf(e))
                .toArray(Integer[]::new);
        return outArr;
    }

    public synchronized String Rank(Integer by) {
        List programmeSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {

             session.beginTransaction();
            String sql = "UPDATE a SET rank = b.NewRanking FROM Programme_Segment_Header3 a INNER JOIN          (SELECT id,   RANK() OVER (PARTITION BY year_id, project_status  ORDER BY score DESC ) AS NewRanking FROM Programme_Segment_Header3) b ON a.score>0 and a.id = b.id";
            //String sql = "Select * FROM Programme_Segment_Header3 as a  where a.project_status=1 and a.score>=0 ORDER BY a.score ASC";//Select * FROM Programme_Segment_Header3 as a where a.project_status=1";
            SQLQuery q = session.createSQLQuery(sql);//.addEntity(ProgrammeSegmentHeader3.class);
            q.executeUpdate();
            session.getTransaction().commit();
/*
            programmeSegmentsList = q.list();

            List newProjs = null;
            List exProjs = null;
            for (int i = 0; i < programmeSegmentsList.size(); i++) {
                ProgrammeSegmentHeader3 ps = (ProgrammeSegmentHeader3) programmeSegmentsList.get(i);
                if (ps.getYear_id() < by) {
                    exProjs.add(programmeSegmentsList.get(i));
                } else {
                    newProjs.add(programmeSegmentsList.get(i));
                }
            }
             Float[] arrr ;                        
            Integer[] ranked;
            arrr = new Float[exProjs.size()];
            for (int i = 0; i < exProjs.size(); i++) {
                ProgrammeSegmentHeader3 ps3 = (ProgrammeSegmentHeader3) exProjs.get(i);
                arrr[i]= ps3.getScore();
            }
           ranked=getRanksArray(arrr);
           for (int i = 0; i < exProjs.size(); i++) {
               ProgrammeSegmentHeader3 ps3 = (ProgrammeSegmentHeader3) exProjs.get(i);
               ps3.setRank(ranked[i]);
               update(ps3);
           }
           
           arrr = new Float[newProjs.size()];
            for (int i = 0; i < newProjs.size(); i++) {
                ProgrammeSegmentHeader3 ps3 = (ProgrammeSegmentHeader3) newProjs.get(i);
                arrr[i]= ps3.getScore();
            }
          ranked=getRanksArray(arrr);
           for (int i = 0; i < newProjs.size(); i++) {
               ProgrammeSegmentHeader3 ps3 = (ProgrammeSegmentHeader3) newProjs.get(i);
               ps3.setRank(ranked[i]);
               update(ps3);
           }
*/

            /* Gson gson = new Gson();
            jsonList = gson.toJson(programmeSegmentsList);
            tx.commit();*/
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
           // session.close();
        }
        return "Done";
    }

    public synchronized String fetchAllPolicies(String policy, String NewOld, String budgetyear) {
        List programmeSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.* from Policies as a order by group_code";
            String sql ;//= "select a.* from Programme_Segment_Header3 as a where  a.policy = '" + policy + "' and  a.year_id ='" + budgetyear + "' and a.project_status=1 order by a.code";
            
            if ("1".equals(NewOld)) {
                //new projects
                sql = "select a.* from Programme_Segment_Header3 as a where  a.policy = '" + policy + "' and  a.year_id ='" + budgetyear + "' and a.project_status=1 order by a.code";
            } else {
                //old projects
                sql = "select a.* from Programme_Segment_Header3 as a where  a.policy = '" + policy + "' and  a.year_id <'" + budgetyear + "' and a.project_status=1 and a.score>=0 order by a.code";
            }
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
            if (parent != -1) {
                sql = "select * from Programme_Segment_Header3 a where a.parent = '" + parent + "' order by a.name";
            } else {
                sql = "select * from Programme_Segment_Header3 a where a.project_status =1 order by a.rank, a.score";
            }
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

    public synchronized ProgrammeSegmentHeader3 fetchOnebyCode(String code) {

        ProgrammeSegmentHeader3 programmeSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader3 as a where a.code='" + code + "'");
            programmeSegment = (ProgrammeSegmentHeader3) q.uniqueResult();
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

    public synchronized ProgrammeSegmentHeader3 fetchOne(String id) {

        ProgrammeSegmentHeader3 programmeSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader3 as a where a.id='" + id + "'");
            programmeSegment = (ProgrammeSegmentHeader3) q.uniqueResult();
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

    public synchronized ProgrammeSegmentHeader3 fetchOne(int id) {

        ProgrammeSegmentHeader3 programmeSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProgrammeSegmentHeader3 as a where a.id='" + id + "'");
            programmeSegment = (ProgrammeSegmentHeader3) q.uniqueResult();
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

            //String sql = "select a.id,a.name,a.code,a.parent from Programme_Segment_Header3 a where a.id = "+id+"  order by a.id";
            String sql = "select * from Programme_Segment_Header3 as a where a.id = '" + id + "'  order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("code", StandardBasicTypes.STRING).addScalar("parent", StandardBasicTypes.INTEGER).addScalar("date_created", StandardBasicTypes.STRING)
                    .addScalar("org_id", StandardBasicTypes.INTEGER).addScalar("mda", StandardBasicTypes.INTEGER).addScalar("department", StandardBasicTypes.INTEGER)
                    .addScalar("sector_goal", StandardBasicTypes.INTEGER).addScalar("year_id", StandardBasicTypes.INTEGER).addScalar("project_status", StandardBasicTypes.BINARY)
                    .addScalar("score", StandardBasicTypes.FLOAT).addScalar("rank", StandardBasicTypes.INTEGER).addScalar("sector_programme", StandardBasicTypes.INTEGER);
            q.setMaxResults(1);
            programmeSegmentList = q.list();
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
            String sql = "Select MAX(  RIGHT(a.code, LEN(a.code) - '" + sz + "')) AS NewCode from Programme_Segment_Header3 as a where  a.parent= '" + parent + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("NewCode", StandardBasicTypes.STRING);
            q.setMaxResults(1);
            programmeSegmentList = q.list();
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
