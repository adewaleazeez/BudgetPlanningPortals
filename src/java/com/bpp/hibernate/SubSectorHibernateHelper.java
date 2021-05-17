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
public class SubSectorHibernateHelper {
	public static final String TABLE_NAME = "SubSectors";
	public static final String RAW_TABLE_NAME = "Sub_Sectors";

    /* Sub_Sectors methods begin */
    public synchronized String insert(SubSectors subsector) {
        SubSectors checkSubSector = exists(subsector.getId());
        if (checkSubSector == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(subsector);
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

    public synchronized String update(SubSectors subsector) {
        SubSectors checkSubSector = exists(subsector.getId());
        if (checkSubSector != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(subsector);
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

    public synchronized String delete(SubSectors subsector) {
        SubSectors checkSubSector = exists(subsector.getId());
        if (checkSubSector != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(subsector);
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

    public synchronized SubSectors exists(String name) {
        SubSectors subsector = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //Query q = session.createQuery("from SubSectors as a where a.name='" + name + "' and a.sectors.id='" + sector_id + "'");
            Query q = session.createQuery("from SubSectors as a where a.name='" + name + "'");
            q.setMaxResults(1);
            subsector = (SubSectors) q.uniqueResult();
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
        return subsector;
    }

    public synchronized SubSectors exists(int id) {
        SubSectors subsector = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from SubSectors as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            subsector = (SubSectors) q.uniqueResult();
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
        return subsector;
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

    public synchronized SubSectors fetchObj(int id) {
        SubSectors subsector = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            subsector = (SubSectors) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return subsector;
    }
    
    public synchronized String fetchAll() {
        List subsectorsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, a.name, (select b.name from Sectors b where a.sector_id=b.id) from Sub_Sectors a where a.name<>'' order by a.sector_id, a.name";
            String sql = "select a.id, a.sub_sector_code, a.name, a.sub_sector_weight from Sub_Sectors a where a.sub_sector_code<>'' order by a.sub_sector_code, a.name";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            subsectorsList = q.list();
//            Query q = session.createQuery("from SubSectors as a where a.name<>'' order by a.name");
//            subsectorsList =  q.list();
            //session.getTransaction().commit();
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
    
    public synchronized String fetchByGroup(String sectoreid) {
        List subsectorsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, a.name, (select b.name from Sectors b where a.sector_id=b.id) from Sub_Sectors a where a.name<>'' order by a.sector_id, a.name";
            String sql = "select a.id, a.sub_sector_code, a.name, a.sub_sector_weight from Sub_Sectors a where a.sector_id='"+sectoreid+"' order by a.sub_sector_code, a.name";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            subsectorsList = q.list();
//            Query q = session.createQuery("from SubSectors as a where a.name<>'' order by a.name");
//            subsectorsList =  q.list();
            //session.getTransaction().commit();
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
    
    public synchronized String fetchAll2() {
        List subsectorsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            //String sql = "select a.id, a.name, (select b.name from Sectors b where a.sector_id=b.id) from Sub_Sectors a where a.name<>'' order by a.sector_id, a.name";
            String sql = "select a.* from Sub_Sectors a where a.sub_sector_code<>'' order by a.sub_sector_code";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            subsectorsList = q.list();
//            Query q = session.createQuery("from SubSectors as a where a.name<>'' order by a.name");
//            subsectorsList =  q.list();
            //session.getTransaction().commit();
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
    
    public synchronized String fetchByCode() {
        List subsectorsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select a.sub_sector_code, concat(a.sub_sector_code,' - ',a.name) as code from Sub_Sectors a order by a.sub_sector_code, a.name";
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
    
    public synchronized String fetchAll(int sectorID) {
        List subsectorList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select a.id, a.name, b.name as sectorName from " + RAW_TABLE_NAME + " a " +
            			 "inner join " + SectorHibernateHelper.RAW_TABLE_NAME + " b where a.sector_id = b.id " +
       			 		 "and a.sector_id = " + sectorID + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql);
            subsectorList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(subsectorList);
            
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
        List subsectorsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sql = "select a.id, a.sub_sector_code, a.name, a.sub_sector_weight, a.sector_id from Sub_Sectors a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql); //.addScalar("id",StandardBasicTypes.INTEGER).addScalar("sub_sector_code",StandardBasicTypes.STRING)
                    //.addScalar("name",StandardBasicTypes.STRING).addScalar("sub_sector_weight",StandardBasicTypes.FLOAT);

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
    
    public synchronized String sumWeight(int id) {
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select sum(sub_sector_weight) as weight from Sub_Sectors a where id <> " + id;
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

    /* SubSectors methods end */

}
