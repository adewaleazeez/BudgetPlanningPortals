package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Outputs generated by hbm2java
 */
public class Outputs  implements java.io.Serializable {


     private int id;
     private Outcomes outcomes;
     private String description;
     private Date outputDate;
     private Date dateCreated;
     private Integer orgId;
     private Set incomeses = new HashSet(0);

    public Outputs() {
    }

	
    public Outputs(int id, Outcomes outcomes, Date outputDate, Date dateCreated) {
        this.id = id;
        this.outcomes = outcomes;
        this.outputDate = outputDate;
        this.dateCreated = dateCreated;
    }
    public Outputs(int id, Outcomes outcomes, String description, Date outputDate, Date dateCreated, Integer orgId, Set incomeses) {
       this.id = id;
       this.outcomes = outcomes;
       this.description = description;
       this.outputDate = outputDate;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.incomeses = incomeses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Outcomes getOutcomes() {
        return this.outcomes;
    }
    
    public void setOutcomes(Outcomes outcomes) {
        this.outcomes = outcomes;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getOutputDate() {
        return this.outputDate;
    }
    
    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
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
    public Set getIncomeses() {
        return this.incomeses;
    }
    
    public void setIncomeses(Set incomeses) {
        this.incomeses = incomeses;
    }




}


