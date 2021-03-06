package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Policies generated by hbm2java
 */
public class Policies  implements java.io.Serializable {


     private int id;
     private String description;
     private Date policyDate;
     private Date dateCreated;
     private Integer orgId;
     private Double policyWeight;
     private Integer policyYearId;
     private String policyCode;
     private String groupCode;
     private Set policyMdas = new HashSet(0);

    public Policies() {
    }

	
    public Policies(int id, Date policyDate, Date dateCreated) {
        this.id = id;
        this.policyDate = policyDate;
        this.dateCreated = dateCreated;
    }
    public Policies(int id, String description, Date policyDate, Date dateCreated, Integer orgId, Double policyWeight, Integer policyYearId, String policyCode, String groupCode, Set policyMdas) {
       this.id = id;
       this.description = description;
       this.policyDate = policyDate;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.policyWeight = policyWeight;
       this.policyYearId = policyYearId;
       this.policyCode = policyCode;
       this.groupCode = groupCode;
       this.policyMdas = policyMdas;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getPolicyDate() {
        return this.policyDate;
    }
    
    public void setPolicyDate(Date policyDate) {
        this.policyDate = policyDate;
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
    public Double getPolicyWeight() {
        return this.policyWeight;
    }
    
    public void setPolicyWeight(Double policyWeight) {
        this.policyWeight = policyWeight;
    }
    public Integer getPolicyYearId() {
        return this.policyYearId;
    }
    
    public void setPolicyYearId(Integer policyYearId) {
        this.policyYearId = policyYearId;
    }
    public String getPolicyCode() {
        return this.policyCode;
    }
    
    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }
    public String getGroupCode() {
        return this.groupCode;
    }
    
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    public Set getPolicyMdas() {
        return this.policyMdas;
    }
    
    public void setPolicyMdas(Set policyMdas) {
        this.policyMdas = policyMdas;
    }




}


