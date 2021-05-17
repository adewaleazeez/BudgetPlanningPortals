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
 *
 * @author Adewale
 */
public class DepartmentHODHibernateHelper {

    /* DepartmentHods methods begin */
    public synchronized String insert(DepartmentHod departmenthod) {
        DepartmentHod checkDepartmentHod = exists(departmenthod.getDepartments().getId()+"");
        if (checkDepartmentHod == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(departmenthod);
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

    public synchronized String update(DepartmentHod departmenthod) {
        DepartmentHod checkDepartmentHod = exists(departmenthod.getId()+"");
        if (checkDepartmentHod != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(departmenthod);
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

            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(DepartmentHod departmenthod) {
        DepartmentHod checkDepartmentHod = exists(departmenthod.getId()+"");
        if (checkDepartmentHod != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(departmenthod);
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

    public synchronized DepartmentHod exists(String id) {
        DepartmentHod departmenthod = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from DepartmentHod as a where a.departments.id='" + id + "'");
            //q.setMaxResults(1);
            departmenthod = (DepartmentHod) q.uniqueResult();
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
        return departmenthod;
    }
    
    public synchronized DepartmentHod dept_exists(String id) {
        DepartmentHod departmenthod = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from DepartmentHod as a where a.departments.id='" + id + "'");
            //q.setMaxResults(1);
            departmenthod = (DepartmentHod) q.uniqueResult();
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
        return departmenthod;
    }

    public synchronized DepartmentHod deleteAll(String userid) {
        DepartmentHod departmenthod = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();

            tx = session.beginTransaction();
            String hqlDelete = "delete DepartmentHod c where c.users.id = :oldUser_id";
            session.createQuery(hqlDelete).setString( "oldUser_id", userid ).executeUpdate();

//            Query q = session.createQuery("Delete DepartmentHod as a where a.user_id='" + userid + "'");
//            //q.setMaxResults(1);
//            departmenthod = (DepartmentHod) q.uniqueResult();
//            //session.getTransaction().commit();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return departmenthod;
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
        List departmenthodsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from DepartmentHod a where a.id<>'' order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            departmenthodsList = q.list();
//            Query q = session.createQuery("from DepartmentHods as a where a.name<>'' order by a.name");
//            departmenthodsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(departmenthodsList);
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

    public synchronized void deleteByHod(String id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
             tx = session.beginTransaction();
            String hqlDelete = "delete DepartmentHod c where c.departments.id = :dept_id";
            session.createQuery(hqlDelete).setString( "dept_id", id ).executeUpdate();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
    }

    /* DepartmentHods methods end */

}
