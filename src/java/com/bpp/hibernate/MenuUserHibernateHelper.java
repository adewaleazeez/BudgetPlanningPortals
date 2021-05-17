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
 * @author Lekan
 * @since 18/6/2017
 */
public class MenuUserHibernateHelper {

    public static final String TABLE_NAME = "MenuUser";
    public static final String RAW_TABLE_NAME = "Menu_User";

    public synchronized MenuUser fetchObj(int id) {
        MenuUser menuUser = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);
            menuUser = (MenuUser) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return menuUser;
    }

//    public synchronized String fetchAll() {
//        List menuUserList = null;
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
//            menuUserList = q.list();
//
//            Gson gson = new Gson();
//            jsonList = gson.toJson(menuUserList);
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
    public synchronized String insert(MenuUser menuUser) {
        MenuUser checkMenuUser = getObj(menuUser.getUsers().getId()+"", menuUser.getSystemMenu().getId()+"");
        if (checkMenuUser == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(menuUser);
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

    public synchronized String update(MenuUser menuUser) {
        MenuUser checkMenuUser = getObj(menuUser.getUsers().getId()+"", menuUser.getSystemMenu().getId()+"");
        if (checkMenuUser == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(menuUser);
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
            if(menuUser.getId()==checkMenuUser.getId()){
                return Utility.ActionResponse.UPDATED.toString();
            }else{
                return Utility.ActionResponse.RECORD_EXISTS.toString();
            }
        }

    }

    public synchronized String delete(MenuUser menuUser) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            //session.beginTransaction();
            session.delete(menuUser);
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

    public synchronized String deleteById(int role_id, int menu_id, int user_id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.deleteUserMenu :Role_id, :Menu_id, :User_id")
                    .setParameter("Role_id", role_id)
                    .setParameter("Menu_id", menu_id)
                    .setParameter("User_id", user_id)
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

    public synchronized String deleteByMenuId(int menu_id) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("delete from Menu_user where menu_id="+menu_id).executeUpdate();

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

    public synchronized MenuUser exists(String id) {
        MenuUser menuUser = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Menu_User as a where a.id='" + id + "'").addEntity(MenuUser.class);
            //q.setMaxResults(1);
            menuUser = (MenuUser) q.uniqueResult();
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
        return menuUser;
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
        List menuUserList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.user_id, a.menu_id, a.accessible from Menu_User a where a.name<>'' order by a.id";
            SQLQuery q = session.createSQLQuery(sql);
            menuUserList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(menuUserList);
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

    public synchronized String updateAllMenuStatus(String user_id, String accessible) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.updateAllMenuStatus :User_id, :Accessible")
                    .setParameter("User_id", user_id)
                    .setParameter("Accessible", accessible)
                    .executeUpdate();
                    
            tx.commit();
            return Utility.ActionResponse.UPDATED.toString();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return Utility.ActionResponse.FAILED.toString();
        } finally {
            //session.close();
        }

    }

    public synchronized String updateMenuStatus(String id, String accessible) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.updateMenuStatus :Id, :Accessible")
                    .setParameter("Id", id)
                    .setParameter("Accessible", accessible)
                    .executeUpdate();
                    
            tx.commit();
            return Utility.ActionResponse.UPDATED.toString();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return Utility.ActionResponse.FAILED.toString();
        } finally {
            //session.close();
        }

    }

    public synchronized String updateMenuRoleStatus(String roleid, String menuid, String accessible) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.updateMenuRoleStatus :roleId, :menuId, :Accessible")
                    .setParameter("roleId", roleid)
                    .setParameter("menuId", menuid)
                    .setParameter("Accessible", accessible)
                    .executeUpdate();
                    
            tx.commit();
            return Utility.ActionResponse.UPDATED.toString();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return Utility.ActionResponse.FAILED.toString();
        } finally {
            //session.close();
        }

    }

    public synchronized String insertMenuUser(String user_id) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.insertMenuUser :User_id")
                    .setParameter("User_id", user_id)
                    .executeUpdate();
                    
            tx.commit();
            return Utility.ActionResponse.INSERTED.toString();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return Utility.ActionResponse.FAILED.toString();
        } finally {
            //session.close();
        }

    }
    
    public synchronized String fetchUser(String id) {
        List usersList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select distinct stuff((select ', ' + ltrim(str(d.id)) from Roles d, User_Role c   "
                    + " where c.user_id=a.id and c.role_id=d.id for xml path('')), 1, 2, '') as role from Users a,  "
                    + " Departments b, User_Role c, Roles d where a.dept_id=b.id and c.user_id=a.id "
                    + " and c.role_id=d.id  and a.id='" + id + "'";
            
            SQLQuery q = session.createSQLQuery(sql).addScalar("role",StandardBasicTypes.STRING);
            usersList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(usersList);
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
            String sql = "select a.id, a.user_id, a.menu_id, c.name as category_name, b.menu_url, b.name as menu_name, a.accessible from Menu_User a, System_Menu b, Menu_Category c where a.user_id='" + id + "' and b.id=a.menu_id and b.menu_category_id=c.id order by c.name, b.name";
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
    
    public synchronized String fetchAllForMenu(String id) {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            String sql = "select a.id, a.user_id, a.menu_id, "
//                + "(select c.name from Menu_Category c WHERE c.id=(select b.menu_category_id FROM System_Menu b WHERE b.id=a.menu_id)) as category_name, "
//                + "(select b.menu_url FROM System_Menu b WHERE b.id=a.menu_id) as menu_url, "
//                + "(select b.name FROM System_Menu b WHERE b.id=a.menu_id) as menu_name, "
//                + "a.accessible, (select b.rank FROM System_Menu b WHERE b.id=a.menu_id) as rank from Menu_User a "
//                + "WHERE a.user_id='" + id + "' and a.accessible='true' order by category_name, rank, menu_name";
            String sql = "select a.id, a.user_id, a.menu_id, c.name as category_name, b.menu_url, b.name as menu_name, a.accessible from Menu_User a, System_Menu b, Menu_Category c where a.user_id='" + id + "' and b.id=a.menu_id and b.menu_category_id=c.id and a.accessible='true' order by c.rank, b.rank, b.name";
            SQLQuery q = session.createSQLQuery(sql);
            //SQLQuery q = session.createSQLQuery(sql);
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
    
    public synchronized List fetchAllMenuID(String id) {
        List<MenuRole> MenuRoleList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "from MenuRole a where a.roles.id in (select d.id from Roles d, UserRole c where c.users.id='" + id + "' and "
                    + "c.roles.id=d.id) and  a.roles.id not in (select e.id from MenuUser e where e.users.id='" + id + "')";
            //sql = "select productid from stockin";
            Query q = session.createQuery(sql); //.addEntity(MenuRole.class);

            //Query q = session.createQuery("from Users as users where users.username<>'' order by users.username");
            MenuRoleList = (List<MenuRole>) q.list();
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
        return MenuRoleList;
    }

    public synchronized String fetchOne(String id) {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select a.id, a.user_id, a.menu_id, a.accessible from Menu_User a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("user_id",StandardBasicTypes.INTEGER)
                    .addScalar("menu_id",StandardBasicTypes.INTEGER).addScalar("accessible",StandardBasicTypes.STRING);

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
    
    public synchronized MenuUser getObj(String user_id, String menu_id){
        MenuUser menuUser = null;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from Menu_User as a where a.user_id='" + user_id + "' and a.menu_id='" + menu_id + "'").addEntity(MenuUser.class);
            menuUser = (MenuUser) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return menuUser;
        
    }
    
}
