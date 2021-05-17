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
public class PolicyHibernateHelper {

    public static final String TABLE_NAME = "Policies";
    public static final String RAW_TABLE_NAME = "Policies";

    /* Policies methods begin */
    public synchronized String insert(Policies policy) {
        Policies checkPolicy = exists(policy.getDescription());
        if (checkPolicy == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(policy);
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

    public synchronized String update(Policies policy) {
        Policies checkPolicy = exists(policy.getId());
        if (checkPolicy != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(policy);
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

    public synchronized String delete(Policies policy) {
        Policies checkPolicy = exists(policy.getId());
        if (checkPolicy != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(policy);
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

    public synchronized Policies exists(String description) {
        Policies policy = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Policies as a where a.description='" + description + "'");
            //q.setMaxResults(1);
            policy = (Policies) q.uniqueResult();

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
        return policy;
    }

    public synchronized Policies exists(int id) {
        Policies btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            btt = (Policies) q.uniqueResult();

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

    public synchronized String fetchAll(String group_code) {
        List policysList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            /*String sql = "select a.id, a.description, a.policy_weight, "
                    + " (select stuff((select ', ' + ltrim(str(d.id)) from Mdas d, Policy_Mda c "
                    + " where c.policy_id=a.id and c.mda_id=d.id for xml path('')), 1, 2, '')) as mda from Policies a "
                    + " where a.id <> ''  and a.policy_year_id = '" + policy_year_id + "' order by a.description ";*/
            String sql;
            if ("0".equals(group_code)){
            sql = "select a.id, a.description, a.policy_weight, a.group_code, a.policy_code,"
                    + " stuff((select ', ' + d.name from Mdas d, Policy_Mda c   "
                    + " where c.policy_id=a.id and c.mda_id=d.id for xml path('')), 1, 2, '') as mda from Policies a "
                    + " where a.id <> ''  order by a.group_code  DESC";
            }else{
                sql = "select a.id, a.description, a.policy_weight, a.group_code, a.policy_code,"
                    + " stuff((select ', ' + d.name from Mdas d, Policy_Mda c   "
                    + " where c.policy_id=a.id and c.mda_id=d.id for xml path('')), 1, 2, '') as mda from Policies a "
                    + " where a.id <> ''  and a.group_code = '" + group_code + "' order by a.group_code  DESC";
            }
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("description", StandardBasicTypes.STRING)
                    .addScalar("policy_weight", StandardBasicTypes.FLOAT).addScalar("policy_code", StandardBasicTypes.STRING).addScalar("group_code", StandardBasicTypes.STRING).addScalar("mda", StandardBasicTypes.STRING);

            policysList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policysList);
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
        List policysList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
 
            String sql = "select a.id, a.description, a.policy_weight, a.group_code, a.policy_code from Policies as a where a.id <> ''  order by a.group_code DESC";

            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("description", StandardBasicTypes.STRING)
                    .addScalar("policy_weight", StandardBasicTypes.FLOAT).addScalar("group_code", StandardBasicTypes.STRING).addScalar("policy_code", StandardBasicTypes.STRING);

            policysList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policysList);
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
    
    public synchronized String fetchByGroup(String group_code) {
        List policysList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
 
            String sql = "select a.* from Policies as a where a.group_code = '" + group_code + "' order by a.group_code";

            SQLQuery q = session.createSQLQuery(sql); //.addScalar("id", StandardBasicTypes.INTEGER).addScalar("description", StandardBasicTypes.STRING)
                    //.addScalar("policy_weight", StandardBasicTypes.FLOAT).addScalar("group_code", StandardBasicTypes.STRING).addScalar("policy_code", StandardBasicTypes.STRING);

            policysList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policysList);
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
        List policysList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select a.id, a.description, a.policy_weight, a.policy_year_id, a.policy_code,"
                    + " (select stuff((select ', ' + ltrim(str(d.id)) from Mdas d, Policy_Mda c "
                    + " where c.policy_id=a.id and c.mda_id=d.id for xml path('')), 1, 2, '')) as mda, a.group_code from Policies a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("description", StandardBasicTypes.STRING)
                    .addScalar("policy_weight", StandardBasicTypes.FLOAT).addScalar("policy_year_id", StandardBasicTypes.INTEGER).addScalar("policy_code", StandardBasicTypes.STRING).addScalar("group_code", StandardBasicTypes.STRING)
                    .addScalar("mda", StandardBasicTypes.STRING);

            policysList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policysList);
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

    public synchronized Policies fetchObj(int id) {
        Policies policy = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            policy = (Policies) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return policy;
    }

    public synchronized String fetch(int id) {
        List policyList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;

            SQLQuery q = session.createSQLQuery(sql);
            policyList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(policyList);

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
