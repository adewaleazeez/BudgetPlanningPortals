package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * PersonnelGradeLevels generated by hbm2java
 */
public class PersonnelGradeLevels  implements java.io.Serializable {


     private int id;
     private String runId;
     private int year;
     private String client;
     private String objectType;
     private String objectId;
     private Date startDate;
     private Date endDate;
     private String personnelArea;
     private String personnelSubArea;
     private String companyCode;
     private String businessArea;
     private String employeeGroup;
     private String employeeSubgroup;
     private String orgKey;
     private String legalPerson;
     private String workContract;
     private String payScaleType;
     private String payScaleArea;
     private String esGrouping;
     private String payScaleGroup;
     private String payScaleLevel;
     private String payScaleLevel2;
     private String countryGrouping;
     private Date dateCreated;
     private Integer orgId;

    public PersonnelGradeLevels() {
    }

	
    public PersonnelGradeLevels(int id, String runId, int year, String objectType, String objectId, Date startDate, Date endDate, String payScaleType, String payScaleArea, String esGrouping, String payScaleGroup, String payScaleLevel, String payScaleLevel2, String countryGrouping, Date dateCreated) {
        this.id = id;
        this.runId = runId;
        this.year = year;
        this.objectType = objectType;
        this.objectId = objectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payScaleType = payScaleType;
        this.payScaleArea = payScaleArea;
        this.esGrouping = esGrouping;
        this.payScaleGroup = payScaleGroup;
        this.payScaleLevel = payScaleLevel;
        this.payScaleLevel2 = payScaleLevel2;
        this.countryGrouping = countryGrouping;
        this.dateCreated = dateCreated;
    }
    public PersonnelGradeLevels(int id, String runId, int year, String client, String objectType, String objectId, Date startDate, Date endDate, String personnelArea, String personnelSubArea, String companyCode, String businessArea, String employeeGroup, String employeeSubgroup, String orgKey, String legalPerson, String workContract, String payScaleType, String payScaleArea, String esGrouping, String payScaleGroup, String payScaleLevel, String payScaleLevel2, String countryGrouping, Date dateCreated, Integer orgId) {
       this.id = id;
       this.runId = runId;
       this.year = year;
       this.client = client;
       this.objectType = objectType;
       this.objectId = objectId;
       this.startDate = startDate;
       this.endDate = endDate;
       this.personnelArea = personnelArea;
       this.personnelSubArea = personnelSubArea;
       this.companyCode = companyCode;
       this.businessArea = businessArea;
       this.employeeGroup = employeeGroup;
       this.employeeSubgroup = employeeSubgroup;
       this.orgKey = orgKey;
       this.legalPerson = legalPerson;
       this.workContract = workContract;
       this.payScaleType = payScaleType;
       this.payScaleArea = payScaleArea;
       this.esGrouping = esGrouping;
       this.payScaleGroup = payScaleGroup;
       this.payScaleLevel = payScaleLevel;
       this.payScaleLevel2 = payScaleLevel2;
       this.countryGrouping = countryGrouping;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getRunId() {
        return this.runId;
    }
    
    public void setRunId(String runId) {
        this.runId = runId;
    }
    public int getYear() {
        return this.year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    public String getClient() {
        return this.client;
    }
    
    public void setClient(String client) {
        this.client = client;
    }
    public String getObjectType() {
        return this.objectType;
    }
    
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getPersonnelArea() {
        return this.personnelArea;
    }
    
    public void setPersonnelArea(String personnelArea) {
        this.personnelArea = personnelArea;
    }
    public String getPersonnelSubArea() {
        return this.personnelSubArea;
    }
    
    public void setPersonnelSubArea(String personnelSubArea) {
        this.personnelSubArea = personnelSubArea;
    }
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getBusinessArea() {
        return this.businessArea;
    }
    
    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
    public String getEmployeeGroup() {
        return this.employeeGroup;
    }
    
    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup;
    }
    public String getEmployeeSubgroup() {
        return this.employeeSubgroup;
    }
    
    public void setEmployeeSubgroup(String employeeSubgroup) {
        this.employeeSubgroup = employeeSubgroup;
    }
    public String getOrgKey() {
        return this.orgKey;
    }
    
    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }
    public String getLegalPerson() {
        return this.legalPerson;
    }
    
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
    public String getWorkContract() {
        return this.workContract;
    }
    
    public void setWorkContract(String workContract) {
        this.workContract = workContract;
    }
    public String getPayScaleType() {
        return this.payScaleType;
    }
    
    public void setPayScaleType(String payScaleType) {
        this.payScaleType = payScaleType;
    }
    public String getPayScaleArea() {
        return this.payScaleArea;
    }
    
    public void setPayScaleArea(String payScaleArea) {
        this.payScaleArea = payScaleArea;
    }
    public String getEsGrouping() {
        return this.esGrouping;
    }
    
    public void setEsGrouping(String esGrouping) {
        this.esGrouping = esGrouping;
    }
    public String getPayScaleGroup() {
        return this.payScaleGroup;
    }
    
    public void setPayScaleGroup(String payScaleGroup) {
        this.payScaleGroup = payScaleGroup;
    }
    public String getPayScaleLevel() {
        return this.payScaleLevel;
    }
    
    public void setPayScaleLevel(String payScaleLevel) {
        this.payScaleLevel = payScaleLevel;
    }
    public String getPayScaleLevel2() {
        return this.payScaleLevel2;
    }
    
    public void setPayScaleLevel2(String payScaleLevel2) {
        this.payScaleLevel2 = payScaleLevel2;
    }
    public String getCountryGrouping() {
        return this.countryGrouping;
    }
    
    public void setCountryGrouping(String countryGrouping) {
        this.countryGrouping = countryGrouping;
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


