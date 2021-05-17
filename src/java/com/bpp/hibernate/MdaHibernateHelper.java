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
public class MdaHibernateHelper {
	public static final String TABLE_NAME = "Mdas";
	public static final String RAW_TABLE_NAME = "MDAs";

    /* Mdas methods begin */
    public synchronized String insert(Mdas mda) {
        Mdas checkMda = exists(mda.getName());
        if (checkMda == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(mda);
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

    public synchronized String update(Mdas mda) {
        Mdas checkMda = exists(mda.getId());
        if (checkMda != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(mda);
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

    public synchronized String delete(Mdas mda) {
        Mdas checkMda = exists(mda.getId());
        if (checkMda != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(mda);
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

    public synchronized String updateSubSectorCode(String id, String sub_sector_code) {

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery("update Mdas set sub_sector_code = '"+sub_sector_code+"' where id="+id).executeUpdate();

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

    public synchronized Mdas exists(String name) {
        Mdas mda = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //Query q = session.createQuery("from Mdas as a where a.name='" + name + "' and a.subsectors.id='" + sub_sector_id + "'");
            Query q = session.createQuery("from Mdas as a where a.name='" + name + "'");
            q.setMaxResults(1);
            mda = (Mdas) q.uniqueResult();
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
        return mda;
    }

    public synchronized Mdas exists(int id) {
        Mdas mda = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Mdas as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            mda = (Mdas) q.uniqueResult();
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
        return mda;
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

    public synchronized Mdas fetchObj(int id) {
        Mdas mda = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            mda = (Mdas) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return mda;
    }
    
    public synchronized Mdas fetchBySubSector(String sub_sector_code) {
        Mdas mda = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.* from " + RAW_TABLE_NAME + " where a.id=(select min(b.id) from " + RAW_TABLE_NAME + "  b where b.sub_sector_code=" + sub_sector_code + ") ";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            
            //Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            mda = (Mdas) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return mda;
    }
    
    public synchronized String fetchAll() {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, a.name, (select b.name from Sub_Sectors b where a.sub_sector_id=b.id), administrative_segment from Mdas a where a.name<>'' and a.sub_sector_code<>'00' order by a.sub_sector_id, a.name";
            String sql = "select a.id, a.name, (select b.name from Sub_Sectors b where a.sub_sector_code=b.sub_sector_code), a.administrative_segment from Mdas a where a.name<>'' and a.sub_sector_code<>'00' order by a.name";
            //sql = "select productid from stockin";
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
    
    public synchronized String fetchAllMDAs() {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, a.name, (select b.name from Sub_Sectors b where a.sub_sector_id=b.id), administrative_segment from Mdas a where a.name<>'' and a.sub_sector_code<>'00' order by a.sub_sector_id, a.name";
            //String sql = "select a.id, a.name, (select b.name from Sub_Sectors b where a.sub_sector_code=b.sub_sector_code), a.administrative_segment from Mdas a where a.name<>'' order by a.name";
            String sql = "select a.id, administrative_segment, a.name, a.mda_type, a.mda_weight,a.sub_sector_code from Mdas a where a.name<>''  order by a.name";
            //sql = "select productid from stockin";
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
    
    public synchronized String fetchAll(String sub_sector_code) {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id, administrative_segment, a.name, a.mda_type, a.mda_weight from Mdas a where a.sub_sector_code='"+sub_sector_code+"' order by a.name";
            //sql = "select productid from stockin";
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
    
    public synchronized String fetchByUserRole(String userid, String userrole) {
        List subsectorsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "";
            if(userrole.contains("Admin")){
                sql = "select a.id, concat(a.sub_sector_code,' - ',a.name) as code from Sub_Sectors a order by a.sub_sector_code, a.name";
            }else{
                sql = "select a.id, concat(a.sub_sector_code,' - ',a.name) as code from Sub_Sectors a where a.sub_sector_code="
                    + " (select sub_sector_code from MDAs where id = (select mda_id from users where id='"+userid+"')) order by a.sub_sector_code, a.name";
            }
            SQLQuery q = session.createSQLQuery(sql);
            subsectorsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(subsectorsList);
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
    
    public synchronized String fetchBySubSectorCode(String id) {
        List mdasList = null;
        String jsonList = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, administrative_segment, a.name, a.mda_type, a.mda_weight from Mdas a where a.sub_sector_code='"+sub_sector_code+"' order by a.name";
            String sql = "select a.id, concat(a.administrative_segment,' - ',a.name) as code from Mdas a "
                    + " where a.sub_sector_code=(select distinct b.sub_sector_code from sub_sectors b where b.id='"+id+"') "
                    + " order by a.administrative_segment, a.name";
            //sql = "select productid from stockin";
            //System.out.println("sql::: "+sql);
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
    
    public synchronized String fetchNoSector() {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id, administrative_segment, a.name, a.mda_type, a.mda_weight,a.sub_sector_code from Mdas a where a.sub_sector_code='00' order by a.name";
            //sql = "select productid from stockin";
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
    
    @SuppressWarnings("unchecked")
	public synchronized List<Mdas> fetchAllCategorizedSector() {
        List<Mdas> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select * from " + RAW_TABLE_NAME + " a " + 
		       			 "where a.sub_sector_code != '00' order by a.sub_sector_code, a.name";
            
            SQLQuery q = session.createSQLQuery(sql).addEntity(Mdas.class);
            objList = (List<Mdas>) q.list();
            
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
    
    public synchronized String fetchAll(int sub_sector_id) {
        List mdaList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select a.id, a.name, b.name as subsectorName, a.administrative_segment from " + RAW_TABLE_NAME + " a " +
            			 "inner join " + SubSectorHibernateHelper.RAW_TABLE_NAME + " b where a.sub_sector_id = b.id " +
       			 		 "and a.sub_sector_id = " + sub_sector_id + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql);
            mdaList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(mdaList);
            
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

            String sql = "select a.id, a.sub_sector_code, a.name, a.mda_type, a.mda_weight, a.administrative_segment from Mdas a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("sub_sector_code",StandardBasicTypes.STRING)
                    .addScalar("name",StandardBasicTypes.STRING).addScalar("mda_type",StandardBasicTypes.STRING)
                    .addScalar("mda_weight",StandardBasicTypes.STRING).addScalar("administrative_segment",StandardBasicTypes.STRING);

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
    
    public synchronized String fetchUsers(String id) {
        List mdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id, a.firstname,a.lastname,a.email from Users a where a.dept_id in (select distinct b.id from departments b where b.mda_id = '"+id+"');";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("firstname",StandardBasicTypes.STRING).addScalar("lastname",StandardBasicTypes.STRING).addScalar("email",StandardBasicTypes.STRING)
                                ;
            mdasList = q.list();
//            Query q = session.createQuery("from Mdas as a where a.name<>'' order by a.name");
//            mdasList =  q.list();
            //session.getTransaction().commit();
//Add a comment to this line
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
    
    public synchronized String sumWeight(int id, String sub_sector_code) {
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select sum(mda_weight) as weight from Mdas a where substring(sub_sector_code,1,2)='"+sub_sector_code.substring(0,2)+"' and id <> " + id;
            
            SQLQuery q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            recordlist = q.list();
            HashMap hmap = (HashMap) recordlist.get(0);

            if (hmap.get("weight") == null) {
                resp = "0";
            } else {
                resp = hmap.get("weight").toString();
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

    /* Mdas methods end */

}
