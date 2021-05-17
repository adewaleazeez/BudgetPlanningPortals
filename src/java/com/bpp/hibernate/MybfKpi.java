package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * MybfKpi generated by hbm2java
 */
public class MybfKpi  implements java.io.Serializable {


     private int id;
     private MybfFigures mybfFigures;
     private String objective;
     private String policy;
     private String kpi;
     private Date dateCreated;
     private Integer orgId;

    public MybfKpi() {
    }

	
    public MybfKpi(int id, MybfFigures mybfFigures, String objective, String policy, Date dateCreated) {
        this.id = id;
        this.mybfFigures = mybfFigures;
        this.objective = objective;
        this.policy = policy;
        this.dateCreated = dateCreated;
    }
    public MybfKpi(int id, MybfFigures mybfFigures, String objective, String policy, String kpi, Date dateCreated, Integer orgId) {
       this.id = id;
       this.mybfFigures = mybfFigures;
       this.objective = objective;
       this.policy = policy;
       this.kpi = kpi;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public MybfFigures getMybfFigures() {
        return this.mybfFigures;
    }
    
    public void setMybfFigures(MybfFigures mybfFigures) {
        this.mybfFigures = mybfFigures;
    }
    public String getObjective() {
        return this.objective;
    }
    
    public void setObjective(String objective) {
        this.objective = objective;
    }
    public String getPolicy() {
        return this.policy;
    }
    
    public void setPolicy(String policy) {
        this.policy = policy;
    }
    public String getKpi() {
        return this.kpi;
    }
    
    public void setKpi(String kpi) {
        this.kpi = kpi;
    }
    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Integer getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }




}

