package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * MtssCosting generated by hbm2java
 */
public class MtssCosting  implements java.io.Serializable {


     private int id;
     private String adminSegment;
     private String programmeSegment;
     private String economicSegment;
     private String functionalSegment;
     private String fundSegment;
     private String geoSegment;
     private Integer budgetYearId;
     private Double budgetAmount;
     private Date dateCreated;
     private Integer orgId;
     private String deptId;
     private Double cost;
     private Double quantity;

    public MtssCosting() {
    }

	
    public MtssCosting(int id) {
        this.id = id;
    }
    public MtssCosting(int id, String adminSegment, String programmeSegment, String economicSegment, String functionalSegment, String fundSegment, String geoSegment, Integer budgetYearId, Double budgetAmount, Date dateCreated, Integer orgId, String deptId, Double cost, Double quantity) {
       this.id = id;
       this.adminSegment = adminSegment;
       this.programmeSegment = programmeSegment;
       this.economicSegment = economicSegment;
       this.functionalSegment = functionalSegment;
       this.fundSegment = fundSegment;
       this.geoSegment = geoSegment;
       this.budgetYearId = budgetYearId;
       this.budgetAmount = budgetAmount;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.deptId = deptId;
       this.cost = cost;
       this.quantity = quantity;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getAdminSegment() {
        return this.adminSegment;
    }
    
    public void setAdminSegment(String adminSegment) {
        this.adminSegment = adminSegment;
    }
    public String getProgrammeSegment() {
        return this.programmeSegment;
    }
    
    public void setProgrammeSegment(String programmeSegment) {
        this.programmeSegment = programmeSegment;
    }
    public String getEconomicSegment() {
        return this.economicSegment;
    }
    
    public void setEconomicSegment(String economicSegment) {
        this.economicSegment = economicSegment;
    }
    public String getFunctionalSegment() {
        return this.functionalSegment;
    }
    
    public void setFunctionalSegment(String functionalSegment) {
        this.functionalSegment = functionalSegment;
    }
    public String getFundSegment() {
        return this.fundSegment;
    }
    
    public void setFundSegment(String fundSegment) {
        this.fundSegment = fundSegment;
    }
    public String getGeoSegment() {
        return this.geoSegment;
    }
    
    public void setGeoSegment(String geoSegment) {
        this.geoSegment = geoSegment;
    }
    public Integer getBudgetYearId() {
        return this.budgetYearId;
    }
    
    public void setBudgetYearId(Integer budgetYearId) {
        this.budgetYearId = budgetYearId;
    }
    public Double getBudgetAmount() {
        return this.budgetAmount;
    }
    
    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
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
    public String getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    public Double getCost() {
        return this.cost;
    }
    
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Double getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }




}


