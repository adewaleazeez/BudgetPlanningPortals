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
 * Request Agents Hibernate helper class
 * 
 * @author Lekan
 * @since 20/6/2017
 */
public class RequestAgentsHibernateHelper {
	
	public static final String TABLE_NAME = "RequestAgents";
	public static final String RAW_TABLE_NAME = "Request_Agents";

    public synchronized String insert(RequestAgents btt) {    	
        RequestAgents checker = exists(btt.getId());
        
        if (checker == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.save(btt);

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

    public synchronized String update(RequestAgents btt) {
    	RequestAgents checker = exists(btt.getId());
        
        if (checker != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.merge(btt);

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

    public synchronized String delete(RequestAgents btt) {
    	RequestAgents checker = exists(btt.getId());
        
        if (checker != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.delete(btt);

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
    
    public synchronized RequestAgents exists(int id) {
        RequestAgents btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);           
	        btt = (RequestAgents) q.uniqueResult();

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

    public synchronized String getMaxserialNo() {
        String tablename = "dbo." + RAW_TABLE_NAME;
        
        List recordlist = null;
        String resp = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
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
    
    public synchronized RequestAgents fetchObj(int id) {
        RequestAgents meetingAttendant = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            meetingAttendant = (RequestAgents) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return meetingAttendant;
    }
    
    public synchronized RequestAgents fetchObj(int roleID, int requestID, int mdaID) {
        RequestAgents meetingAttendant = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("select * from " + RAW_TABLE_NAME + " as a " +
            		                      	 "where a.role_id = " + roleID + " and a.request_type_id = " + requestID + " " +
            		                      	 "and a.mda_id = " + mdaID).addEntity(RequestAgents.class);
            meetingAttendant = (RequestAgents) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return meetingAttendant;
    }
    
    public synchronized String fetch(int id) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " as a where a.id = " + id;
            
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
    
    public synchronized String fetchAll(int requestTypeID) {
        List objList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, b.name as roleName, a.request_type_id, c.name as agentTypeName, role_id, request_agent_type_id, mda_id, d.name as mdaName from " + RAW_TABLE_NAME + " a " + 
                         "inner join " + RolesHibernateHelper.RAW_TABLE_NAME + " b on a.role_id = b.id " +
                         "inner join " + RequestAgentTypesHibernateHelper.RAW_TABLE_NAME + " c on a.request_agent_type_id = c.id " +
                         "inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " d on a.mda_id = d.id " +
		       			 "where a.request_type_id = " + requestTypeID + " order by a.id";
            
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
	public synchronized List<RequestAgents> fetchAll2(int requestTypeID) {
        List<RequestAgents> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select * from " + RAW_TABLE_NAME + " a " + 
		       			 "where a.request_type_id = " + requestTypeID + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql).addEntity(RequestAgents.class);
            objList = (List<RequestAgents>) q.list();
            
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
}
