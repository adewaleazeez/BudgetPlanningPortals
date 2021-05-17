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
 * @author Ola
 */
public class PolicyGroupHibernateHelper {

	public static final String TABLE_NAME = "Policy_Group";
	public static final String RAW_TABLE_NAME = "Policy_Group";

    /* PolicyGroup methods begin */
    public synchronized String insert(PolicyGroup policygroup) {
        PolicyGroup checkPolicyGroup = exists(policygroup.getGroupCode());
        //int count =exists1(policygroup.getGroup_code());
        //System.out.println("************" + checkPolicyGroup.getId());
        //if (count==0) {//(checkPolicyGroup == null) {
        if (checkPolicyGroup == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(policygroup);
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

    public synchronized String update(PolicyGroup policygroup) {
        PolicyGroup checkPolicyGroup = exists(policygroup.getId());
        if (checkPolicyGroup != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(policygroup);
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
            return Utility.ActionResponse.NO_RECORD.toString();
        }
    }

    public synchronized String delete(PolicyGroup policygroup) {
        PolicyGroup checkPolicyGroup = exists(policygroup.getId());
        if (checkPolicyGroup != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(policygroup);
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

    public synchronized int exists1(String groupcode) {
        //PolicyGroup policygroup = null;
int count=0;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            
             tx = session.beginTransaction();
            String sql = "select a.group_code Policy_Group a order by a.group_code DESC";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            count = q.list().size();
         //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++ " + count);
            tx.commit();
            
            
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return count; //policygroup;
    }

 public synchronized PolicyGroup exists(String groupcode) {
        PolicyGroup policygroup = null;


        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery("Select * from Policy_Group as x where x.group_code = " + groupcode).addEntity(PolicyGroup.class);
            policygroup = (PolicyGroup) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }

        return policygroup;
    }
    public synchronized PolicyGroup exists(int id) {
        PolicyGroup btt = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createSQLQuery(" select * from Policy_Group as a where a.id = " + id).addEntity(PolicyGroup.class);           
	        btt = (PolicyGroup) q.uniqueResult();

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

        List policygroupsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id,a.group_code, a.group_name from Policy_Group a order by a.group_code";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            policygroupsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policygroupsList);
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
 

    public synchronized String fetchAll(String group_code) {
        List policysList = null;
        String jsonList = "";
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            /*String sql = "select a.id, a.description, a.policy_weight, "
                    + " (select stuff((select ', ' + ltrim(str(d.id)) from Mdas d, Policy_Mda c "
                    + " where c.policy_id=a.id and c.mda_id=d.id for xml path('')), 1, 2, '')) as mda from Policies a "
                    + " where a.id <> ''  and a.policy_year_id = '" + policy_year_id + "' order by a.description ";*/
            String sql = "select a.id, a.group_code, a.group_name "                    
                    + " from Policy_Group a where "
                    + " a.group_code = '" + group_code + "' order by a.group_code ";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER)
                    .addScalar("group_name",StandardBasicTypes.STRING).addScalar("group_code",StandardBasicTypes.STRING);

            policysList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policysList);
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
        List policygroupsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
 String sql = "select a.id, a.group_code, a.group_name "                    
                    + " from Policy_Group a where "
                    + " a.id = '" + id + "' order by a.group_code ";
           // String sql = "select a.id,  a.group_code, a.policy_code,"
           //         + " (select stuff((select ', ' + ltrim(str(d.id)) from Mdas d, Policy_Mda c "
           //         + " where c.policy_id=a.id and c.mda_id=d.id for xml path('')), 1, 2, '')) as mda from Policy_Group a where a.id = '" + id + "'";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("group_code",StandardBasicTypes.STRING)
                    .addScalar("group_name",StandardBasicTypes.STRING) ;

            policygroupsList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policygroupsList);
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

    public synchronized PolicyGroup fetchObj(int id) {
        PolicyGroup policygroup = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            policygroup = (PolicyGroup) q.uniqueResult();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
        	//session.close();
        }
        
        return policygroup;
    }
    
    public synchronized String fetch(int id) {
        List policygroupList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select a.group_code, a.group_name, a.id from " + RAW_TABLE_NAME + " as a where a.id = " + id;
            
            SQLQuery q = session.createSQLQuery(sql);
            policygroupList = q.list();

            Gson gson = new Gson();
            jsonList = gson.toJson(policygroupList);
            
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
