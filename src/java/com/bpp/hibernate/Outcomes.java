package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Outcomes generated by hbm2java
 */
public class Outcomes  implements java.io.Serializable {


     private int id;
     private Goals goals;
     private String description;
     private Date outcomeDate;
     private Date dateCreated;
     private Integer orgId;
     private Set outputses = new HashSet(0);

    public Outcomes() {
    }

	
    public Outcomes(int id, Goals goals, Date outcomeDate, Date dateCreated) {
        this.id = id;
        this.goals = goals;
        this.outcomeDate = outcomeDate;
        this.dateCreated = dateCreated;
    }
    public Outcomes(int id, Goals goals, String description, Date outcomeDate, Date dateCreated, Integer orgId, Set outputses) {
       this.id = id;
       this.goals = goals;
       this.description = description;
       this.outcomeDate = outcomeDate;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.outputses = outputses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Goals getGoals() {
        return this.goals;
    }
    
    public void setGoals(Goals goals) {
        this.goals = goals;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getOutcomeDate() {
        return this.outcomeDate;
    }
    
    public void setOutcomeDate(Date outcomeDate) {
        this.outcomeDate = outcomeDate;
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
    public Set getOutputses() {
        return this.outputses;
    }
    
    public void setOutputses(Set outputses) {
        this.outputses = outputses;
    }




}

