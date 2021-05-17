/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

import com.bpp.utility.Utility;
import com.google.gson.Gson;
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
public class ProjectDetailHibernateHelper {

    /* ProjectDetail methods begin */
    public synchronized String insert(ProjectDetail projectDetail) {
        ProjectDetail checkProjectDetail = exists(projectDetail.getMda(), projectDetail.getYearId(), projectDetail.getName());
        if (checkProjectDetail == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(projectDetail);
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

    public synchronized String update(ProjectDetail projectDetail) {
        ///System.out.println("Updating "+projectDetail.getName());
        ProjectDetail checkProjectDetail = exists(projectDetail.getId());
        if (checkProjectDetail != null) {
            checkProjectDetail = exists(projectDetail.getMda(), projectDetail.getYearId(), projectDetail.getName());
            if (checkProjectDetail != null && projectDetail.getId() != checkProjectDetail.getId()) {
                return Utility.ActionResponse.RECORD_EXISTS.toString();
            } else {
                final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = null;
                try {
                    //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    tx = session.beginTransaction();
                    //session.beginTransaction();
                    session.merge(projectDetail);
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
                return Utility.ActionResponse.UPDATED.toString();
            }
        } else {
            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String rankProjects() {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.rankProjects").executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            //System.out.println("error: " + e.getMessage());
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
    }

    public synchronized String delete(ProjectDetail projectDetail) {
        ProjectDetail checkProjectDetail = fetchOne(projectDetail.getId() + "");
        if (checkProjectDetail != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                String hqlDelete = "delete Scoring c where c.projectCode = :project_code";
                session.createQuery(hqlDelete).setString("project_code", projectDetail.getProjectCode()).executeUpdate();
                //session.beginTransaction();
                session.delete(projectDetail);
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
            //System.out.println("No projectDetail 3 in Helper");
            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized ProjectDetail exists(int id) {
        ProjectDetail projectDetail = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProjectDetail as a where a.id='" + id + "'");
            q.setMaxResults(1);
            projectDetail = (ProjectDetail) q.uniqueResult();

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
        return projectDetail;
    }

    public synchronized ProjectDetail exists(String name) {
        ProjectDetail projectDetail = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProjectDetail as a where a.name='" + name + "'");
            q.setMaxResults(1);
            projectDetail = (ProjectDetail) q.uniqueResult();

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
        return projectDetail;
    }

    public synchronized ProjectDetail exists(int mda_id, int year, String name) {
        ProjectDetail projectDetail = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProjectDetail as a where a.name='" + name + "' and a.yearId='" + year + "' and a.mda='" + mda_id + "'");
            q.setMaxResults(1);
            projectDetail = (ProjectDetail) q.uniqueResult();

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
        return projectDetail;
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

//    public synchronized String fetchAll() {
//        List projectDetailsList = null;
//        String jsonList = "";
//
//        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = null;
//        try {
//            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            //session.beginTransaction();
//            tx = session.beginTransaction();
//            String sql = "select * from Project_Detail a where a.name<>'' order by a.name";
//            //sql = "select productid from stockin";
//            SQLQuery q = session.createSQLQuery(sql);
//            projectDetailsList = q.list();
////            Query q = session.createQuery("from ProjectDetail as a where a.name<>'' order by a.name");
////            projectDetailsList =  q.list();
//            //session.getTransaction().commit();
//            Gson gson = new Gson();
//            jsonList = gson.toJson(projectDetailsList);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            //session.close();
//        }
//        return jsonList;
//    }
    public static Integer[] getRanksArray(Float[] array) {

        List<Float> list = Arrays.asList(array);
        Integer[] outArr = list.stream()
                .sorted()
                .map(e -> list.indexOf(e))
                .toArray(Integer[]::new);
        return outArr;
    }

    public synchronized String Rank(Integer by) {
        List projectDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {

            session.beginTransaction();
            String sql = "UPDATE a SET rank = b.NewRanking FROM Project_Detail a INNER JOIN (SELECT id, RANK() OVER (PARTITION BY year_id, project_status  ORDER BY score DESC ) AS NewRanking FROM Project_Detail) b ON a.score>0 and a.id = b.id";
            //String sql = "Select * FROM Project_Detail as a  where a.project_status=1 and a.score>=0 ORDER BY a.score ASC";//Select * FROM Project_Detail as a where a.project_status=1";
            SQLQuery q = session.createSQLQuery(sql);//.addEntity(ProjectDetail.class);
            q.executeUpdate();
            session.getTransaction().commit();
            /*
            projectDetailsList = q.list();

            List newProjs = null;
            List exProjs = null;
            for (int i = 0; i < projectDetailsList.size(); i++) {
                ProjectDetail ps = (ProjectDetail) projectDetailsList.get(i);
                if (ps.getYear_id() < by) {
                    exProjs.add(projectDetailsList.get(i));
                } else {
                    newProjs.add(projectDetailsList.get(i));
                }
            }
             Float[] arrr ;                        
            Integer[] ranked;
            arrr = new Float[exProjs.size()];
            for (int i = 0; i < exProjs.size(); i++) {
                ProjectDetail ps3 = (ProjectDetail) exProjs.get(i);
                arrr[i]= ps3.getScore();
            }
           ranked=getRanksArray(arrr);
           for (int i = 0; i < exProjs.size(); i++) {
               ProjectDetail ps3 = (ProjectDetail) exProjs.get(i);
               ps3.setRank(ranked[i]);
               update(ps3);
           }
           
           arrr = new Float[newProjs.size()];
            for (int i = 0; i < newProjs.size(); i++) {
                ProjectDetail ps3 = (ProjectDetail) newProjs.get(i);
                arrr[i]= ps3.getScore();
            }
          ranked=getRanksArray(arrr);
           for (int i = 0; i < newProjs.size(); i++) {
               ProjectDetail ps3 = (ProjectDetail) newProjs.get(i);
               ps3.setRank(ranked[i]);
               update(ps3);
           }
             */

 /* Gson gson = new Gson();
            jsonList = gson.toJson(projectDetailsList);
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
        List projectDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.* from Policies as a order by group_code";
            String sql;//= "select a.* from Project_Detail as a where  a.policy = '" + policy + "' and  a.year_id ='" + budgetyear + "' and a.project_status=1 order by a.code";

            if ("1".equals(NewOld)) {
                //new projects
                sql = "select a.* from Project_Detail as a where  a.policy = '" + policy + "' and  a.year_id ='" + budgetyear + "' and a.project_status=1 order by a.code";
            } else {
                //old projects
                sql = "select a.* from Project_Detail as a where  a.policy = '" + policy + "' and  a.year_id <'" + budgetyear + "' and a.project_status=1 and a.score>=0 order by a.code";
            }
            SQLQuery q = session.createSQLQuery(sql);
            projectDetailsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(projectDetailsList);
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
        List projectDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.*, (select year from Budget_Years where id=a.year_id) as year from Project_Detail a order by a.project_status desc, a.score desc";//where a.project_status =1 

            SQLQuery q = session.createSQLQuery(sql);
            projectDetailsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(projectDetailsList);
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

    public synchronized String updateScoreRank() {
        List projectDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            session.createSQLQuery("update Project_Detail set rank=0, score=0 where project_code is null or project_code='' or project_code not in (select distinct b.project_code from scoring b where b.project_code=project_code and b.project_year=(select id from Budget_Years where is_current_base_year=1)+1)").executeUpdate();

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

    public synchronized String fetchProjectCode(int mda, String policy, String programme) {
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select max(code) as maxcode from Project_Detail where mda= " + mda + " and policy='" + policy + "' and programme='" + programme + "'";
            SQLQuery q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            recordlist = q.list();
            HashMap hmap = (HashMap) recordlist.get(0);

            if (hmap.get("maxcode") == null) {
                resp = "0";
            } else {
                resp = hmap.get("maxcode").toString();
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

    public synchronized ProjectDetail fetchOnebyCode(String code) {

        ProjectDetail projectDetail = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProjectDetail as a where a.code='" + code + "'");
            projectDetail = (ProjectDetail) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return projectDetail;

    }

    public synchronized ProjectDetail fetchOne(String id) {

        ProjectDetail projectDetail = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProjectDetail as a where a.id='" + id + "'");
            projectDetail = (ProjectDetail) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return projectDetail;

    }

    public synchronized ProjectDetail fetchOne(int id) {

        ProjectDetail projectDetail = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ProjectDetail as a where a.id='" + id + "'");
            projectDetail = (ProjectDetail) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return projectDetail;

    }

    public synchronized String fetchOneJSON(String id) {
        List projectDetailList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();

            //String sql = "select a.id,a.name,a.code,a.parent from Project_Detail a where a.id = "+id+"  order by a.id";
            String sql = "select a.*, (select group_code from policies where policy_code=a.policy) from Project_Detail as a where a.id = '" + id + "' ";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
//            .addScalar("id", StandardBasicTypes.INTEGER).addScalar("name", StandardBasicTypes.STRING)
//                    .addScalar("code", StandardBasicTypes.STRING).addScalar("parent", StandardBasicTypes.INTEGER).addScalar("date_created", StandardBasicTypes.STRING)
//                    .addScalar("org_id", StandardBasicTypes.INTEGER).addScalar("mda", StandardBasicTypes.INTEGER).addScalar("department", StandardBasicTypes.INTEGER)
//                    .addScalar("sector_goal", StandardBasicTypes.INTEGER).addScalar("year_id", StandardBasicTypes.INTEGER).addScalar("project_status", StandardBasicTypes.BINARY)
//                    .addScalar("score", StandardBasicTypes.FLOAT).addScalar("rank", StandardBasicTypes.INTEGER).addScalar("sector_programme", StandardBasicTypes.INTEGER);
            q.setMaxResults(1);
            projectDetailList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(projectDetailList);
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
        List projectDetailList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "Select MAX(  RIGHT(a.code, LEN(a.code) - '" + sz + "')) AS NewCode from Project_Detail as a where  a.parent= '" + parent + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("NewCode", StandardBasicTypes.STRING);
            q.setMaxResults(1);
            projectDetailList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(projectDetailList);
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
    /* ProjectDetail methods end */

}
