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
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

/**
 * Budget Phases Hibernate Helper class
 *
 * @author Adewale
 * @since 18/6/2017
 */
public class SystemMenuHibernateHelper {

    public static final String TABLE_NAME = "SystemMenu";
    public static final String RAW_TABLE_NAME = "SystemMenu";

    public synchronized SystemMenu fetchObj(int id) {
        SystemMenu systemMenu = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            systemMenu = (SystemMenu) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return systemMenu;
    }

//    public synchronized String fetchAll() {
//        List systemMenuList = null;
//        String jsonList = "";
//
//        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            String sql = "select * from " + RAW_TABLE_NAME + " a order by a.id";
//
//            SQLQuery q = session.createSQLQuery(sql);
//            systemMenuList = q.list();
//
//            Gson gson = new Gson();
//            jsonList = gson.toJson(systemMenuList);
//
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            //session.close();
//        }
//
//        return jsonList;
//    }
    public synchronized String insert(SystemMenu systemMenu) {
        SystemMenu checkSystemMenu = exists(systemMenu.getMenuUrl());
        if (checkSystemMenu == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(systemMenu);
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

    public synchronized String update(SystemMenu systemMenu) {
        SystemMenu checkSystemMenu = exists(systemMenu.getMenuUrl());
        if (checkSystemMenu == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(systemMenu);
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
            if (systemMenu.getId() == checkSystemMenu.getId()) {
                final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction tx = null;
                try {
                    //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    tx = session.beginTransaction();
                    //session.beginTransaction();
                    session.merge(systemMenu);
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

    }

    public synchronized String delete(SystemMenu systemMenu) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            //session.beginTransaction();
            session.delete(systemMenu);
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
    }

    public synchronized String updateRank(String id, String rank) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("update System_Menu set rank = '"+rank+"' where id="+id).executeUpdate();

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

        return Utility.ActionResponse.UPDATED.toString();
    }

    public synchronized SystemMenu exists(String url) {
        SystemMenu systemMenu = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from System_Menu as a where a.menu_url='" + url + "'").addEntity(SystemMenu.class);
            //q.setMaxResults(1);
            systemMenu = (SystemMenu) q.uniqueResult();
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
        return systemMenu;
    }

    public synchronized SystemMenu exists(int id) {
        SystemMenu systemMenu = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from System_Menu as a where a.id='" + id + "'").addEntity(SystemMenu.class);
            //q.setMaxResults(1);
            systemMenu = (SystemMenu) q.uniqueResult();
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
        return systemMenu;
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
        List systemMenuList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.name, a.menu_url, a.menu_category from SystemMenu a where a.name<>'' order by a.menu_category, a.name";
            SQLQuery q = session.createSQLQuery(sql);
            systemMenuList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(systemMenuList);
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

    public synchronized String fetchAll(String id) {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, a.rank, a.name, a.menu_url from System_Menu a where a.menu_category_id='" + id + "' order by a.rank, a.name ";
            String sql = "select a.id, a.rank, a.name, a.menu_url, stuff((select ', ' + b.name from Roles b, Menu_Role c   "
                    + " where c.menu_id=a.id and c.role_id=b.id for xml path('')), 1, 2, '') as role from System_Menu a where a.menu_category_id='" + id + "' order by a.rank, a.name ";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("rank", StandardBasicTypes.INTEGER)
                    .addScalar("name", StandardBasicTypes.STRING).addScalar("menu_url", StandardBasicTypes.STRING).addScalar("role", StandardBasicTypes.STRING);
            //SQLQuery q = session.createSQLQuery(sql);
            mdasList = q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(mdasList);
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
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select a.id, a.menu_category_id, a.name, a.menu_url, stuff((select ', ' + ltrim(str(b.id)) from Roles b, Menu_Role c   "
                    + " where c.menu_id=a.id and c.role_id=b.id for xml path('')), 1, 2, '') as role from System_Menu a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("menu_category_id", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING).addScalar("menu_url", StandardBasicTypes.STRING).addScalar("role", StandardBasicTypes.STRING);

            mdasList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mdasList);
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

    public synchronized String getObj(String name) {
        List mdasList = null;
        String jsonList = "";
        SystemMenu systemMenu = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select menu_url from System_Menu as a where a.name='" + name + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("menu_url", StandardBasicTypes.STRING);
            //.addScalar("id", StandardBasicTypes.INTEGER).addScalar("menu_category_id", StandardBasicTypes.STRING)
            //        .addScalar("name", StandardBasicTypes.STRING).addScalar("menu_url", StandardBasicTypes.STRING).addScalar("role", StandardBasicTypes.STRING);

            mdasList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(mdasList);
            
            //Query q = session.createSQLQuery("select * from System_Menu as a where a.name='" + name + "'").addEntity(SystemMenu.class);
            //systemMenu = (SystemMenu) q.uniqueResult();
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
