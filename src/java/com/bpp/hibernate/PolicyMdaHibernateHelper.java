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
public class PolicyMdaHibernateHelper {
	
	public static final String TABLE_NAME = "PolicyMda";
	public static final String RAW_TABLE_NAME = "Policy_Mda";


    /* PolicyMdas methods begin */
    public synchronized String insert(PolicyMda policyMda) {
        PolicyMda checkPolicyMda = exists(policyMda.getPolicies().getId()+"", policyMda.getMdaId()+"");
        if (checkPolicyMda == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(policyMda);
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

    public synchronized String update(PolicyMda policyMda) {
        PolicyMda checkPolicyMda = exists(policyMda.getPolicies().getId()+"", policyMda.getMdaId()+"");
        if (checkPolicyMda != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(policyMda);
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

    public synchronized String delete(PolicyMda policyMda) {
        PolicyMda checkPolicyMda = exists(policyMda.getPolicies().getId()+"", policyMda.getMdaId()+"");
        if (checkPolicyMda != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(policyMda);
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

    public synchronized PolicyMda exists(String id) {
        PolicyMda policyMda = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from PolicyMda as a where a.id='" + id + "'");
            //q.setMaxResults(1);
            policyMda = (PolicyMda) q.uniqueResult();
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
        return policyMda;
    }

    public synchronized PolicyMda exists(String policy_id, String mda_id) {
        PolicyMda user = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from PolicyMda as a where a.policies.id='" + policy_id + "'");// and a.mdas.id='" + mda_id + "'
            
            user = (PolicyMda) q.uniqueResult();

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
    
    public synchronized String deleteAll(String policy_id) {
        PolicyMda policyMda = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();

            tx = session.beginTransaction();
            String hqlDelete = "delete PolicyMda c where c.policies.id = :oldPolicy_id";
            session.createQuery(hqlDelete).setString( "oldPolicy_id", policy_id ).executeUpdate();

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

    public synchronized PolicyMda fetchObj(int id) {
        PolicyMda user = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from " + TABLE_NAME + " as a where a.id = " + id);            
            user = (PolicyMda) q.uniqueResult();
            
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

    public synchronized List fetchByPolicyId(int policy_id) {
        List<PolicyMda> userRoleList = null;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from PolicyMda as a where a.policies.id = " + policy_id;
            Query q = session.createQuery(sql);
            userRoleList = (List<PolicyMda>) q.list();

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
        List policyMdasList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select * from Policy_Mda a where a.id<>'' order by a.id";
            SQLQuery q = session.createSQLQuery(sql);
            policyMdasList = q.list();
            Gson gson = new Gson();
            jsonList = gson.toJson(policyMdasList);
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
	public synchronized List<PolicyMda> fetchAll2(int policyID, int mdaID) {
        List<PolicyMda> objList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String sql = "select * from " + RAW_TABLE_NAME + " a " + 
            			 "inner join " + PolicyHibernateHelper.RAW_TABLE_NAME + " b on a.policy_id = b.id " +
            			 "inner join " + MdaHibernateHelper.RAW_TABLE_NAME + " c on a.mda_id = c.id " +
		       			 "where a.policy_id = " + policyID + " and c.id = " + mdaID + " order by a.id";
            
            SQLQuery q = session.createSQLQuery(sql).addEntity(RequestAgents.class);
            objList = (List<PolicyMda>) q.list();
            
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

    /* PolicyMdas methods end */

}
