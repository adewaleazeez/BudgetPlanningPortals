package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * MtefDepartmentFigures generated by hbm2java
 */
public class MtefDepartmentFigures  implements java.io.Serializable {


     private int id;
     private BudgetStatus budgetStatus;
     private BudgetVersions budgetVersions;
     private BudgetYears budgetYears;
     private Users users;
     private int deptId;
     private String chartOfAccount;
     private String budgetLine;
     private double budgetValue;
     private Date dateCreated;
     private Integer orgId;

    public MtefDepartmentFigures() {
    }

	
    public MtefDepartmentFigures(int id, BudgetStatus budgetStatus, BudgetVersions budgetVersions, BudgetYears budgetYears, Users users, int deptId, String budgetLine, double budgetValue, Date dateCreated) {
        this.id = id;
        this.budgetStatus = budgetStatus;
        this.budgetVersions = budgetVersions;
        this.budgetYears = budgetYears;
        this.users = users;
        this.deptId = deptId;
        this.budgetLine = budgetLine;
        this.budgetValue = budgetValue;
        this.dateCreated = dateCreated;
    }
    public MtefDepartmentFigures(int id, BudgetStatus budgetStatus, BudgetVersions budgetVersions, BudgetYears budgetYears, Users users, int deptId, String chartOfAccount, String budgetLine, double budgetValue, Date dateCreated, Integer orgId) {
       this.id = id;
       this.budgetStatus = budgetStatus;
       this.budgetVersions = budgetVersions;
       this.budgetYears = budgetYears;
       this.users = users;
       this.deptId = deptId;
       this.chartOfAccount = chartOfAccount;
       this.budgetLine = budgetLine;
       this.budgetValue = budgetValue;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public BudgetStatus getBudgetStatus() {
        return this.budgetStatus;
    }
    
    public void setBudgetStatus(BudgetStatus budgetStatus) {
        this.budgetStatus = budgetStatus;
    }
    public BudgetVersions getBudgetVersions() {
        return this.budgetVersions;
    }
    
    public void setBudgetVersions(BudgetVersions budgetVersions) {
        this.budgetVersions = budgetVersions;
    }
    public BudgetYears getBudgetYears() {
        return this.budgetYears;
    }
    
    public void setBudgetYears(BudgetYears budgetYears) {
        this.budgetYears = budgetYears;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public int getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getChartOfAccount() {
        return this.chartOfAccount;
    }
    
    public void setChartOfAccount(String chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }
    public String getBudgetLine() {
        return this.budgetLine;
    }
    
    public void setBudgetLine(String budgetLine) {
        this.budgetLine = budgetLine;
    }
    public double getBudgetValue() {
        return this.budgetValue;
    }
    
    public void setBudgetValue(double budgetValue) {
        this.budgetValue = budgetValue;
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


