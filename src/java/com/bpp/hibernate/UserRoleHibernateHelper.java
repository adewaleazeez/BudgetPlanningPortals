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
public class UserRoleHibernateHelper {
	
	public static final String TABLE_NAME = "UserRole";
	public static final String RAW_TABLE_NAME = "User_Role";


    /* UserRoles methods begin */
    public synchronized String insert(UserRole userrole) {
        System.out.println(userrole.getUsers().getId()+"       "+userrole.getRoles().getId()+"");
        UserRole checkUserRole = exists(userrole.getUsers().getId()+"", userrole.getRoles().getId()+"");
        if (checkUserRole == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(userrole);
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

    public synchronized String update(UserRole userrole) {
        UserRole checkUserRole = exists(userrole.getUsers().getId()+"", userrole.getRoles().getId()+"");
        if (checkUserRole != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(userrole);
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

    public synchronized String delete(UserRole userrole) {
        UserRole checkUserRole = exists(userrole.getUsers().getId()+"", userrole.getRoles().getId()+"");
        if (checkUserRole != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(userrole);
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

    public synchronized UserRole exists(String id) {
        UserRole userrole = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from UserRole as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            userrole = (UserRole) q.uniqueResult();
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
        return userrole;
    }

    public synchronized UserRole exists(String user_id, String role_id) {
        UserRole user = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from UserRole as a where a.users.id='" + user_id + "' and a.roles.id='" + role_id + "'");
            
            user = (UserRole) q.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return user;
    }
    
    public synchronized String deleteAll(String userid) {
        UserRole userrole = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();

            tx = session.beginTransaction();
            String hqlDelete = "delete UserRole c where c.users.id = :oldUser_id";
            session.createQuery(hqlDelete).setString( "oldUser_id", userid ).executeUpdate();

//            Query q = session.createQuery("Delete UserRole as a where a.user_id='" + userid + "'");
//            //q.setMaxResults(1);
//            userrole = (UserRole) q.uniqueResult();
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
        return Utility.ActionResponse.DELETED.toString();
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

    public synchronized UserRole fetchObj(int id) {
        UserRole user = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            user = (UserRole) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return user;
    }
    
    public synchronized String fetch(int id) {
        List userList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;
            
            SQLQuery q = session.createSQLQuery(sql);
            userList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(userList);
            
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

    public synchronized List fetchByUserId(int user_id) {
        List<UserRole> userRoleList = null;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from UserRole as a where a.users.id = " + user_id;
            Query q = session.createQuery(sql);
            userRoleList = (List<UserRole>) q.list();

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
        List userrolesList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from UserRole a where a.id<>'' order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            userrolesList = q.list();
//            Query q = session.createQuery("from UserRoles as a where a.name<>'' order by a.name");
//            userrolesList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(userrolesList);
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

    public synchronized String fetchAllById(int roleID, int mdaID) {
        List userrolesList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from " + RAW_TABLE_NAME + " a " + 
            			 "inner join " + UsersHibernateHelper.RAW_TABLE_NAME + " b on a.user_id = b.id " +
            			 "inner join " + DepartmentHibernateHelper.RAW_TABLE_NAME + " c on b.dept_id = c.id " +
            			 "inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " d on c.mda_id = d.id " +
		       			 "where a.role_id = " + roleID + " and d.id = " + mdaID + " order by a.id";
            SQLQuery q = session.createSQLQuery(sql);
            userrolesList = q.list();
//            Query q = session.createQuery("from UserRoles as a where a.name<>'' order by a.name");
//            userrolesList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(userrolesList);
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

    @SuppressWarnings("unchecked")
	public synchronized List<UserRole> fetchAll2(int roleID, int mdaID) {
        List<UserRole> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " a " + 
            			 "inner join " + UsersHibernateHelper.RAW_TABLE_NAME + " b on a.user_id = b.id " +
            			 "inner join " + DepartmentHibernateHelper.RAW_TABLE_NAME + " c on b.dept_id = c.id " +
            			 "inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " d on c.mda_id = d.id " +
		       			 "where a.role_id = " + roleID + " and d.id = " + mdaID + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql).addEntity(RequestAgents.class);
            objList = (List<UserRole>) q.list();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        
        return objList;
    }

    @SuppressWarnings("unchecked")
	public synchronized List<UserRole> fetchAll2(int roleID) {
        List<UserRole> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " a " + 
            			 "inner join " + UsersHibernateHelper.RAW_TABLE_NAME + " b on a.user_id = b.id " +
            			 "inner join " + DepartmentHibernateHelper.RAW_TABLE_NAME + " c on b.dept_id = c.id " +
            			 "inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " d on c.mda_id = d.id " +
		       			 "where a.role_id = " + roleID + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql).addEntity(UserRole.class);
            objList = (List<UserRole>) q.list();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        
        return objList;
    }
    /* UserRoles methods end */

}
