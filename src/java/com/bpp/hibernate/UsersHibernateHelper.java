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
public class UsersHibernateHelper {

	public static final String TABLE_NAME = "Users";
	public static final String RAW_TABLE_NAME = "Users";

    /* Users methods begin */
    public synchronized String insert(Users user) {
        Users checkUser = exists(user.getUsername());
        if (checkUser == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(user);
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

    public synchronized void saveUserIdSession(int userid) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.createSQLQuery("EXEC dbo.saveUserIdSession :userid")
                .setParameter("userid", userid)
                .executeUpdate();
            //session.getTransaction().commit();
//System.out.println("user.getId() "+user.getId());
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("error: " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
    }
    
    public synchronized String update(Users user) {
        Users checkUser = exists(user.getUsername());
        if (checkUser != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
               
                //session.beginTransaction();
                session.merge(user);
                //session.getTransaction().commit();
//System.out.println("user.getId() "+user.getId());
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

    public synchronized String delete(Users user) {
        Users checkUser = exists(user.getUsername());
        if (checkUser != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(user);
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

    public synchronized Users exists(String username) {
        Users user = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Users as a where a.username='" + username + "'");
            //q.setMaxResults(1);
            user = (Users) q.uniqueResult();
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
        return user;
    }

    public synchronized Users exists(int id) {
        Users btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);           
	        btt = (Users) q.uniqueResult();

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
        
        return btt;
    }

    public synchronized Users exists_id(String id) {
        Users user = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Users as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            user = (Users) q.uniqueResult();
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
        return user;
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
 
	/*public synchronized List fetchAll() {
        List<Users> usersList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Users as a where a.username<>'' order by a.username");
            usersList = (List<Users>) q.list();
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
        return usersList;
    }*/

    @SuppressWarnings("unchecked")
	public synchronized List<Users> fetchAll2() {
        List<Users> objList = null;
  
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " a order by a.id";			  
            SQLQuery q = session.createSQLQuery(sql).addEntity(Users.class);
            objList = (List<Users>) q.list();
			
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
    
    public synchronized String fetchAll(String str) {
        List usersList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select distinct a.id, a.userimage, a.email, a.firstname, a.lastname, (select iif(b.name is null,'', b.name) from Departments b where a.dept_id=b.id) as dept, " //b.name as dept
                    + " stuff((select ', ' + d.name from Roles d, User_Role c   "
                    + " where c.user_id=a.id and c.role_id=d.id for xml path('')), 1, 2, '') as role, a.phoneno, a.userstatus from Users a,  "
                    + " User_Role c, Roles d where a.email<>'' and c.user_id=a.id and c.role_id=d.id  ";
								
								
            if (str != null && !str.equals("")) {
                sql += " and (a.id like '%" + str + "%' or a.userimage like '%" + str + "%' or a.email like '%" + str 
                        + "%' or a.firstname like '%" + str + "%' or a.lastname like '%" + str + "%' or b.name like '%" + str 
                        + "%' or d.name like '%" + str + "%' or a.userstatus like '%" + str + "%' or a.phoneno like '%" + str + "%')";
            }
            sql += " order by a.email ";
            System.out.println("sql  "+sql);
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("userimage",StandardBasicTypes.STRING)
                    .addScalar("email",StandardBasicTypes.STRING).addScalar("firstname",StandardBasicTypes.STRING)
                    .addScalar("lastname",StandardBasicTypes.STRING).addScalar("dept",StandardBasicTypes.STRING)
                    .addScalar("role",StandardBasicTypes.STRING).addScalar("phoneno",StandardBasicTypes.STRING)
                    .addScalar("userstatus",StandardBasicTypes.STRING);
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

    public synchronized String fetchOne(String id) {
        List usersList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select a.id, a.userimage, a.email, a.firstname, a.lastname, a.userstatus, a.mda_id, a.dept_id, "
                    + " CONVERT(VARCHAR(20), a.last_login_date, 100) as last_login_date, DATEDIFF(DAY, a.last_login_date, GETDATE()) as lastlogin, "
                    + " a.phoneno, (select stuff((select ', ' + ltrim(str(d.id)) from Roles d, User_Role c "
                    + " where c.user_id=a.id and c.role_id=d.id for xml path('')), 1, 2, '')) as role from Users a where a.id = '" + id + "'";
                                //System.out.println("sql "+sql); //(select distinct ltrim(str(b.mda_id)) from Departments b where b.id=a.dept_id) as mda
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("userimage",StandardBasicTypes.STRING)
                    .addScalar("email",StandardBasicTypes.STRING).addScalar("firstname",StandardBasicTypes.STRING)
                    .addScalar("lastname",StandardBasicTypes.STRING).addScalar("userstatus",StandardBasicTypes.STRING)
                    .addScalar("mda_id",StandardBasicTypes.STRING).addScalar("dept_id",StandardBasicTypes.STRING)
                    .addScalar("last_login_date",StandardBasicTypes.STRING).addScalar("lastlogin",StandardBasicTypes.INTEGER)
                    .addScalar("phoneno",StandardBasicTypes.STRING).addScalar("role",StandardBasicTypes.STRING);

            usersList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(usersList);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                ////tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return jsonList;
    }

    public synchronized Users fetchObj(int id) {
        Users user = null;										

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();	 
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            user = (Users) q.uniqueResult();
            
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

    public synchronized String fetchAll() {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.firstname, a.lastname, b.name as deptName, c.name as mdaName from " + RAW_TABLE_NAME + " a " + 
       			 		 "inner join " + DepartmentHibernateHelper.RAW_TABLE_NAME + " b on a.dept_id = b.id " +
       			 		 "inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " c on b.mda_id = c.id " +
       			 		 "order by a.id";
            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();
            //System.out.println("sql  "+sql);
            Gson gson = new Gson();
            jsonList = gson.toJson(objList);
            
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
    
    public synchronized String fetchAllByMdas(int mdaID) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.firstname, a.lastname, b.name as deptName, c.name as mdaName from " + RAW_TABLE_NAME + " a " + 
       			 		 "inner join " + DepartmentHibernateHelper.RAW_TABLE_NAME + " b on a.dept_id = b.id " +
       			 		 "inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " c on b.mda_id = c.id " +
       			 		 "where c.id = " + mdaID + " order by a.id";
            //System.out.println("sql: "+sql);
            SQLQuery q = session.createSQLQuery(sql);
            objList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(objList);
            
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
   	public synchronized List<Users> fetchAll2(int mdaID) {
           List<Users> objList = null;

           final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction tx = null;
           
           try {
               tx = session.beginTransaction();
               
               String sql = "select a.* from " + RAW_TABLE_NAME + " a " + 
	     			 		"inner join " + DepartmentHibernateHelper.RAW_TABLE_NAME + " b on a.dept_id = b.id " +
	     			 		"inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " c on b.mda_id = c.id " +
	     			 		"where c.id = " + mdaID + " order by a.id";
               
               SQLQuery q = session.createSQLQuery(sql).addEntity(Users.class);
               objList = (List<Users>) q.list();
               
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

	/* Users methods end */
}								  