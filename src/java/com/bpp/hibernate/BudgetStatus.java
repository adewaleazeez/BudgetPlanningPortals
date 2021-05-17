package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BudgetStatus generated by hbm2java
 */
public class BudgetStatus  implements java.io.Serializable {


     private int id;
     private String name;
     private Date dateCreated;
     private Integer orgId;
     private Set budgetVersionStatuses = new HashSet(0);
     private Set mtefDepartmentFigureses = new HashSet(0);

    public BudgetStatus() {
    }

	
    public BudgetStatus(int id, String name, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }
    public BudgetStatus(int id, String name, Date dateCreated, Integer orgId, Set budgetVersionStatuses, Set mtefDepartmentFigureses) {
       this.id = id;
       this.name = name;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.budgetVersionStatuses = budgetVersionStatuses;
       this.mtefDepartmentFigureses = mtefDepartmentFigureses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    public Set getBudgetVersionStatuses() {
        return this.budgetVersionStatuses;
    }
    
    public void setBudgetVersionStatuses(Set budgetVersionStatuses) {
        this.budgetVersionStatuses = budgetVersionStatuses;
    }
    public Set getMtefDepartmentFigureses() {
        return this.mtefDepartmentFigureses;
    }
    
    public void setMtefDepartmentFigureses(Set mtefDepartmentFigureses) {
        this.mtefDepartmentFigureses = mtefDepartmentFigureses;
    }




}


