package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BudgetVersions generated by hbm2java
 */
public class BudgetVersions  implements java.io.Serializable {


     private int id;
     private BudgetYears budgetYears;
     private String name;
     private int mtefFromYear;
     private int mtefToYear;
     private String description;
     private Date dateCreated;
     private double mybfContigencyValue;
     private double mybfSupplementaryValue;
     private Integer orgId;
     private Set mtefDepartmentFigureses = new HashSet(0);
     private Set budgetVersionStatuses = new HashSet(0);
     private Set mybfFigureses = new HashSet(0);

    public BudgetVersions() {
    }

	
    public BudgetVersions(int id, BudgetYears budgetYears, String name, int mtefFromYear, int mtefToYear, String description, Date dateCreated, double mybfContigencyValue, double mybfSupplementaryValue) {
        this.id = id;
        this.budgetYears = budgetYears;
        this.name = name;
        this.mtefFromYear = mtefFromYear;
        this.mtefToYear = mtefToYear;
        this.description = description;
        this.dateCreated = dateCreated;
        this.mybfContigencyValue = mybfContigencyValue;
        this.mybfContigencyValue = mybfSupplementaryValue;
    }
    public BudgetVersions(int id, BudgetYears budgetYears, String name, int mtefFromYear, int mtefToYear, String description, Date dateCreated, double mybfContigencyValue, double mybfSupplementaryValue, Integer orgId, Set mtefDepartmentFigureses, Set budgetVersionStatuses, Set mybfFigureses) {
       this.id = id;
       this.budgetYears = budgetYears;
       this.name = name;
       this.mtefFromYear = mtefFromYear;
       this.mtefToYear = mtefToYear;
       this.description = description;
       this.dateCreated = dateCreated;
       this.mybfContigencyValue = mybfContigencyValue;
       this.orgId = orgId;
       this.mybfContigencyValue = mybfSupplementaryValue;
       this.mtefDepartmentFigureses = mtefDepartmentFigureses;
       this.budgetVersionStatuses = budgetVersionStatuses;
       this.mybfFigureses = mybfFigureses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public BudgetYears getBudgetYears() {
        return this.budgetYears;
    }
    
    public void setBudgetYears(BudgetYears budgetYears) {
        this.budgetYears = budgetYears;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getMtefFromYear() {
        return this.mtefFromYear;
    }
    
    public void setMtefFromYear(int mtefFromYear) {
        this.mtefFromYear = mtefFromYear;
    }
    public int getMtefToYear() {
        return this.mtefToYear;
    }
    
    public void setMtefToYear(int mtefToYear) {
        this.mtefToYear = mtefToYear;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public double getMybfContigencyValue() {
        return this.mybfContigencyValue;
    }
    
    public void setMybfContigencyValue(double mybfContigencyValue) {
        this.mybfContigencyValue = mybfContigencyValue;
    }

    public double getMybfSupplementaryValue() {
        return this.mybfSupplementaryValue;
    }

    public void setMybfSupplementaryValue(double mybfSupplementaryValue) {
        this.mybfSupplementaryValue = mybfSupplementaryValue;
    }
    
    public Integer getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
    public Set getMtefDepartmentFigureses() {
        return this.mtefDepartmentFigureses;
    }
    
    public void setMtefDepartmentFigureses(Set mtefDepartmentFigureses) {
        this.mtefDepartmentFigureses = mtefDepartmentFigureses;
    }
    public Set getBudgetVersionStatuses() {
        return this.budgetVersionStatuses;
    }
    
    public void setBudgetVersionStatuses(Set budgetVersionStatuses) {
        this.budgetVersionStatuses = budgetVersionStatuses;
    }
    public Set getMybfFigureses() {
        return this.mybfFigureses;
    }
    
    public void setMybfFigureses(Set mybfFigureses) {
        this.mybfFigureses = mybfFigureses;
    }




}


