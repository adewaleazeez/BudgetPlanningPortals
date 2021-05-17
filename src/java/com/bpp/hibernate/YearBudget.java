package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * YearBudget generated by hbm2java
 */
public class YearBudget  implements java.io.Serializable {


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
     private String percentComplete;
     private String completeFrom;
     private String completeTo;
     private String sapDocumentNumber;
     private String sapBudgetType;
     private Integer budgetVersionId;
     private String narration;

    public YearBudget() {
    }

	
    public YearBudget(int id) {
        this.id = id;
    }
    public YearBudget(int id, String adminSegment, String programmeSegment, String economicSegment, String functionalSegment, String fundSegment, String geoSegment, Integer budgetYearId, Double budgetAmount, Date dateCreated, Integer orgId, String deptId, String percentComplete, String completeFrom, String completeTo, String sapDocumentNumber, String sapBudgetType, Integer budgetVersionId, String narration) {
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
       this.percentComplete = percentComplete;
       this.completeFrom = completeFrom;
       this.completeTo = completeTo;
       this.sapDocumentNumber = sapDocumentNumber;
       this.sapBudgetType = sapBudgetType;
       this.budgetVersionId = budgetVersionId;
       this.narration = narration;
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
    public String getPercentComplete() {
        return this.percentComplete;
    }
    
    public void setPercentComplete(String percentComplete) {
        this.percentComplete = percentComplete;
    }
    public String getCompleteFrom() {
        return this.completeFrom;
    }
    
    public void setCompleteFrom(String completeFrom) {
        this.completeFrom = completeFrom;
    }
    public String getCompleteTo() {
        return this.completeTo;
    }
    
    public void setCompleteTo(String completeTo) {
        this.completeTo = completeTo;
    }
    public String getSapDocumentNumber() {
        return this.sapDocumentNumber;
    }
    
    public void setSapDocumentNumber(String sapDocumentNumber) {
        this.sapDocumentNumber = sapDocumentNumber;
    }
    public String getSapBudgetType() {
        return this.sapBudgetType;
    }
    
    public void setSapBudgetType(String sapBudgetType) {
        this.sapBudgetType = sapBudgetType;
    }
    public Integer getBudgetVersionId() {
        return this.budgetVersionId;
    }
    
    public void setBudgetVersionId(Integer budgetVersionId) {
        this.budgetVersionId = budgetVersionId;
    }
    public String getNarration() {
        return this.narration;
    }
    
    public void setNarration(String narration) {
        this.narration = narration;
    }




}


