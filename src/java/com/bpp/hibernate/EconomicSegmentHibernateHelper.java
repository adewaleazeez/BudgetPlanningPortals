/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.hibernate;

import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.util.Arrays;
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
public class EconomicSegmentHibernateHelper {

    /* EconomicSegment methods begin */
    public synchronized String insert(EconomicSegment economicSegment) {
        EconomicSegment checkEconomicSegment = exists(economicSegment.getName());
        if (checkEconomicSegment == null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.save(economicSegment);
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

    public synchronized String update(EconomicSegment economicSegment) {
        System.out.println("Updating " + economicSegment.getName());
        EconomicSegment checkEconomicSegment = exists(economicSegment.getName());
        if (checkEconomicSegment == null || economicSegment.getId() == checkEconomicSegment.getId()) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.merge(economicSegment);
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

            return Utility.ActionResponse.RECORD_EXISTS.toString();
        }
    }

    public synchronized String delete(EconomicSegment economicSegment) {
        EconomicSegment checkEconomicSegment = fetchOne(economicSegment.getId() + "");
        if (checkEconomicSegment != null) {
            final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            try {
                //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                //session.beginTransaction();
                session.delete(economicSegment);
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

    public synchronized EconomicSegment exists(String name) {
        EconomicSegment economicSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            Query q = session.createQuery("from EconomicSegment as a where a.name='" + name + "'");
            //q.setMaxResults(1);
            economicSegment = (EconomicSegment) q.uniqueResult();
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
        return economicSegment;
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
        List economicSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Economic_Segment a where a.name<>'' order by a.name";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            economicSegmentsList = q.list();
//            Query q = session.createQuery("from EconomicSegment as a where a.name<>'' order by a.name");
//            economicSegmentsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(economicSegmentsList);
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

    public synchronized double[] FectchActuals(Integer year, String code) {
        double[] bdgValue = null;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select coalesce(sum(AmountLc),0) as bdgValue from SAP_Actuals  where CmmtItem like '" + code + "%' and FiscYear > " + (year - 2) + " and  FiscYear <= " + year + " group by FiscYear";
            System.out.println(sql);
            List tmp = session.createSQLQuery(sql).addScalar("bdgValue", StandardBasicTypes.DOUBLE).list();
            int i = 0;
            int sz = tmp.size();
            if (sz < 2) {
                sz = 2;
            }
            bdgValue = new double[sz];
            for (Object tmp1 : tmp) {
                bdgValue[i] = (double) tmp1;
                i++;
            }
            if (sz < 2) {
                bdgValue[1] = 0;
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

        return bdgValue;
    }
//class BudgetLineItem
//{
//    public double BdgPrevYear; 
//    public double BdgPrevYear; 
//    public double BdgPrevYear; 
//    public double BdgPrevYear; 
//    public String LastName;  
//    public int    BirthYear; 
// };
  
    public synchronized double[] GetEstimate(Integer year, String code) {
        double[] bdgValue = null;//= 0.0f;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //String sql= "SELECT  coalesce(sum(dbo.Year_Budget.budget_amount),0) as bdgValue FROM Year_Budget WHERE Year_Budget.budget_year_id=" + year + "and  Year_Budget.economic_segment Like '" + code + "%'";
            String sql = "select coalesce(sum(Year_Budget.budget_amount),0) as ss, 'A'  from Year_Budget  where economic_segment like '" + code + "%' and budget_year_id = " + (year - 1)
                    + " UNION select coalesce(sum(Year_Budget.budget_amount),0) as ss,'B'  from Year_Budget  where economic_segment like '" + code + "%' and budget_year_id = " + (year)
                    + " group by budget_year_id UNION"
                    + " select coalesce(sum(AmountLc),0) as ss, 'C' from SAP_Actuals  where CmmtItem like '" + code + "%' and FiscYear = " + (year - 2)
                    + " UNION select coalesce(sum(AmountLc),0) as ss,'D' from SAP_Actuals  where CmmtItem like '" + code + "%' and FiscYear = " + (year-1)
                    + " group by FiscYear";
            //sql = "select coalesce(sum(dbo.Year_Budget.budget_amount),0) as bdgValue from Year_Budget  where economic_segment like '" + code + "%' and budget_year_id > " + (year - 2) + " and  budget_year_id <= " + year + " group by budget_year_id";
            //System.out.println(sql);
            //List tmp = session.createSQLQuery(sql).list();// .addScalar("bdgValue", StandardBasicTypes.DOUBLE).list();
            bdgValue = new double[4];
            int start = sql.length();
            if (start > 0) {
                List<Object[]> tmp = session.createSQLQuery(sql).list();////.addScalar("bdgValue", StandardBasicTypes.DOUBLE).list();
                int i = 0;
                for (Object[] tmp1 : tmp) {
                    i = Arrays.asList(tmp1).indexOf("A");
                    if (i == 1) {
                        bdgValue[0] = (double) tmp1[0];
                    }
                    i = Arrays.asList(tmp1).indexOf("B");
                    if (i == 1) {
                        bdgValue[1] = (double) tmp1[0];
                    }
                    i = Arrays.asList(tmp1).indexOf("C");
                    if (i == 1) {
                        bdgValue[2] = (double) tmp1[0];
                    }
                    i = Arrays.asList(tmp1).indexOf("D");
                    if (i == 1) {
                        bdgValue[3] = (double) tmp1[0];
                    }
                }
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

        return bdgValue;
    }

    public synchronized double[] GetValue(Integer year, String code) {
        double[] bdgValue = null;
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //"SELECT  coalesce(sum(dbo.Year_Budget.budget_amount),0) as bdgValue FROM Year_Budget WHERE Year_Budget.budget_year_id=0 and  left(Year_Budget.economic_segment,2)='21'"
            //String sql = "SELECT  coalesce((dbo.Year_Budget.budget_amount),0) as bdgValue FROM Year_Budget WHERE economic_segment Like '" + code + "%' and budget_year_id > " + (year - 2) + " and  budget_year_id <= " + year;

            String sql = "select Year_Budget.budget_amount as ss, 'B0' as ss2 from Year_Budget  where economic_segment like '_code%' and budget_year_id = year1"
                    + " UNION select Year_Budget.budget_amount as ss,  'B1' as ss2 from Year_Budget  where economic_segment like '_code%' and budget_year_id = __year"
                    + " UNION select AmountLc as ss , 'A0' as ss2 from SAP_Actuals  where (FiscYear = 'year2' and CmmtItem like _code)"
                    + " UNION select AmountLc as ss,  'A1' as ss2 from SAP_Actuals  where (FiscYear = 'year1' and CmmtItem like _code)";
            
            Integer y1= year-1; 
            Integer y2= year-2;
            sql=sql.replaceAll("year1", y1.toString() );
            sql=sql.replaceAll("year2", y2.toString() );
            sql=sql.replaceAll("__year", year.toString() );
            sql=sql.replace("_code", code);
            
            //System.out.println(sql); 
            //bdgValue=(double) session.createSQLQuery(sql).addScalar("bdgValue",StandardBasicTypes.DOUBLE ).uniqueResult();            
              bdgValue = new double[4]; 
            int start = sql.length() ; 
            if (start > 0) {
               // sql = sql.substring(0, start);
                List<Object[]> tmp = session.createSQLQuery(sql).list();//.addScalar("bdgValue", StandardBasicTypes.DOUBLE).list();
                int i = 0;
                for (Object[] tmp1 : tmp) {
                    i = Arrays.asList(tmp1).indexOf("A0");
                    if (i == 1) {
                        bdgValue[0] = (double) tmp1[0];
                    }
                    i = Arrays.asList(tmp1).indexOf("A1");
                    if (i == 1) {
                        bdgValue[1] = (double) tmp1[0];
                    }
                    i = Arrays.asList(tmp1).indexOf("B0");
                    if (i == 1) {
                        bdgValue[2] = (double) tmp1[0];
                    }
                    i = Arrays.asList(tmp1).indexOf("B1");
                    if (i == 1) {
                        bdgValue[3] = (double) tmp1[0];
                    }
                }
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

        return bdgValue;
    }

    public synchronized List fetchAllAsList(String code) {
        List economicSegmentsList = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Economic_Segment a where a.code LIKE '" + code + "%' order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addEntity(EconomicSegment.class);
            economicSegmentsList = q.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return economicSegmentsList;
    }

    public synchronized String fetchAll(Integer parent) {
        List economicSegmentsList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select * from Economic_Segment a where a.parent = " + parent + "order by a.name";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql);
            economicSegmentsList = q.list();
//            Query q = session.createQuery("from EconomicSegment as a where a.name<>'' order by a.name");
//            economicSegmentsList =  q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(economicSegmentsList);
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

    public synchronized EconomicSegment fetchOne(String id) {

        EconomicSegment economicSegment = null;

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from EconomicSegment as a where a.id='" + id + "'");
            economicSegment = (EconomicSegment) q.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return economicSegment;

    }

    public synchronized String fetchOneJSON(String id) {
        List economicSegmentList = null;
        String jsonList = "";

        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            //final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            tx = session.beginTransaction();
            String sql = "select a.id,a.name,a.code,a.parent from Economic_Segment a where a.id = " + id + "  order by a.id";
            //sql = "select productid from stockin";
            SQLQuery q = session.createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER).addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("code", StandardBasicTypes.STRING).addScalar("parent", StandardBasicTypes.INTEGER);
            q.setMaxResults(1);
            economicSegmentList = q.list();
            //session.getTransaction().commit();
            Gson gson = new Gson();
            jsonList = gson.toJson(economicSegmentList);
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
    /* EconomicSegment methods end */

}
