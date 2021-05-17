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
 * Meeting details Hibernate helper class
 * 
 * @author Lekan
 * @since 20/6/2017
 */
public class MeetingDetailsHibernateHelper {
	
	public static final String TABLE_NAME = "MeetingDetails";
	public static final String RAW_TABLE_NAME = "Meeting_Details";

    public synchronized String insert(MeetingDetails btt) {    	
        MeetingDetails checker = exists(btt.getId());
        
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

    public synchronized String update(MeetingDetails btt) {
    	MeetingDetails checker = exists(btt.getId());
        
        if (checker != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();
                
                session.merge(btt);

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

    public synchronized String delete(MeetingDetails btt) {
    	MeetingDetails checker = exists(btt.getId());
        
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
    
    public synchronized MeetingDetails exists(int id) {
        MeetingDetails btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);           
	        btt = (MeetingDetails) q.uniqueResult();

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
    
    public synchronized MeetingDetails fetchObj(int id) {
        MeetingDetails meetingDetails = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            meetingDetails = (MeetingDetails) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return meetingDetails;
    }
    
    public synchronized String fetch(int id, int userID) {
        List meetingDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select a.id, a.name, a.venue, b.name as activityName, a.meeting_date, c.meeting_role_id from " + RAW_TABLE_NAME + " as a " +
            			 "inner join " + BudgetTimetableActivitiesHibernateHelper.RAW_TABLE_NAME + " b on a.budget_timetable_activity_id = b.id " +
            			 "inner join " + MeetingAttendantsHibernateHelper.RAW_TABLE_NAME + " c on a.id = c.meeting_id " +
            			 "where a.id = " + id + " and c.user_id = " + userID;
            
            SQLQuery q = session.createSQLQuery(sql);
            meetingDetailsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(meetingDetailsList);
            
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
    
    public synchronized String fetchAll(int budgetTimetableActivityID) {
        List meetingDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select a.id, a.name, a.venue, b.name as activityName from " + RAW_TABLE_NAME + " a " + 
            			 "inner join " + BudgetTimetableActivitiesHibernateHelper.RAW_TABLE_NAME + " b on a.budget_timetable_activity_id = b.id " + 
            			 "where a.budget_timetable_activity_id = " + budgetTimetableActivityID + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql);
            meetingDetailsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(meetingDetailsList);
            
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
    
    public synchronized String fetchAll(String userID) {
        List meetingDetailsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            String sql = "select distinct a.id, a.name, a.venue, b.name as activityName, a.meeting_date from " + RAW_TABLE_NAME + " a " + 
            			 "inner join " + BudgetTimetableActivitiesHibernateHelper.RAW_TABLE_NAME + " b on a.budget_timetable_activity_id = b.id " +
            			 "inner join " + MeetingAttendantsHibernateHelper.RAW_TABLE_NAME + " c on a.id = c.meeting_id " +
            			 "where c.user_id = " + userID + " order by a.meeting_date desc";
            
            SQLQuery q = session.createSQLQuery(sql);
            meetingDetailsList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(meetingDetailsList);
            
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
