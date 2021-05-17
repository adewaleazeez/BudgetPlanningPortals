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
public class MenuRoleHibernateHelper {

    public static final String TABLE_NAME = "MenuRole";
    public static final String RAW_TABLE_NAME = "Menu_Role";

    public synchronized MenuRole fetchObj(int id) {
        MenuRole menuRole = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            menuRole = (MenuRole) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return menuRole;
    }

//    public synchronized String fetchAll() {
//        List menuRoleList = null;
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
//            menuRoleList = q.list();
//
//            Gson gson = new Gson();
//            jsonList = gson.toJson(menuRoleList);
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
    public synchronized String insert(MenuRole menuRole) {
        MenuRole checkMenuRole = getObj(menuRole.getId() + "");
        if (checkMenuRole == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //session.save(menuRole);
                
                session.createSQLQuery("EXEC dbo.insertMenuRole :Role_id, :Menu_id, :Org_id")
                    .setParameter("Role_id", menuRole.getRoles().getId())
                    .setParameter("Menu_id", menuRole.getSystemMenu().getId())
                    .setParameter("Org_id", menuRole.getOrgId())
                    .executeUpdate();
                
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

    public synchronized String update(MenuRole menuRole) {
        MenuRole checkMenuRole = getObj(menuRole.getId() + "");
        if (checkMenuRole == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //session.merge(menuRole);
                
                session.createSQLQuery("EXEC dbo.updateMenuRole :Role_id, :Menu_id, :Id")
                    .setParameter("Role_id", menuRole.getRoles().getId())
                    .setParameter("Menu_id", menuRole.getSystemMenu().getId())
                    .setParameter("Id", menuRole.getId())
                    .executeUpdate();

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
            if (menuRole.getId() == checkMenuRole.getId()) {
                return Utility.ActionResponse.UPDATED.toString();
            } else {
                return Utility.ActionResponse.RECORD_EXISTS.toString();
            }
        }

    }

    public synchronized String delete(MenuRole menuRole) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //session.delete(menuRole);
            session.createSQLQuery("EXEC dbo.deleteMenuRole :Id")
                    .setParameter("Id", menuRole.getId())
                    .executeUpdate();

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

    public synchronized MenuRole exists(String name) {
        MenuRole menuRole = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Menu_Role as a where a.id='" + name + "'").addEntity(MenuRole.class);
            //q.setMaxResults(1);
            menuRole = (MenuRole) q.uniqueResult();
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
        return menuRole;
    }

    public synchronized MenuRole exists(String role_id, String menu_id) {
        MenuRole menuRole = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Menu_Role as a where a.role_id = '" + role_id+"' and a.menu_id = '" + menu_id + "'").addEntity(MenuRole.class);
	        menuRole = (MenuRole) q.uniqueResult();
            //System.out.println("SQL: select * from Menu_Role as a where a.role_id = '" + role_id+"' and a.menu_id = '" + menu_id + "'");
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
        	//session.close();
        }
        
        return menuRole;
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

    public synchronized List fetchByMenuId(int menu_id) {
        List<MenuRole> userRoleList = null;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from MenuRole as a where a.systemMenu.id = " + menu_id;
            Query q = session.createQuery(sql);
            userRoleList = (List<MenuRole>) q.list();

//            Gson gson = new Gson();
//            jsonList = gson.toJson(userList);
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        
        return userRoleList;
    }

    public synchronized String fetchAll() {
        List menuRoleList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.role_id, a.menu_id from Menu_Role a where a.id<>'' order by a.id";
            SQLQuery q = session.createSQLQuery(sql);
            menuRoleList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(menuRoleList);
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
            tx = session.beginTransaction();
            String sql = "select a.id, a.role_id, a.menu_id, c.name as category_name, b.name as menu_name, b.menu_url, (select max(accessible) from Menu_User where user_id in (select user_id from User_Role where role_id='" + id + "')  and menu_id=a.menu_id) as accessible from Menu_Role a, System_Menu b, Menu_category c where a.role_id='" + id + "' and b.id=a.menu_id and b.menu_category_id=c.id order by b.menu_category_id, b.name";
            SQLQuery q = session.createSQLQuery(sql);
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

    public synchronized String fetchOne(String id) {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select a.id, a.role_id, a.menu_id from Menu_Role a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("role_id", StandardBasicTypes.INTEGER)
                    .addScalar("menu_id", StandardBasicTypes.INTEGER);

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

    public synchronized MenuRole getObj(String id) {

        MenuRole menuRole = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Menu_Role as a where a.id='" + id + "'").addEntity(MenuRole.class);
            menuRole = (MenuRole) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return menuRole;

    }

    public synchronized String deleteAll(String menu_id) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();

            tx = session.beginTransaction();
            String hqlDelete = "delete MenuRole c where c.systemMenu.id = :oldMenu_id";
            session.createQuery(hqlDelete).setString("oldMenu_id", menu_id).executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return Utility.ActionResponse.DELETED.toString();
    }

}
