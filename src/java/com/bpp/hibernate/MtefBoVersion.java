package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * MtefBoVersion generated by hbm2java
 */
public class MtefBoVersion  implements java.io.Serializable {


     private int id;
     private BudgetYears budgetYears;
     private Users users;
     private String name;
     private Date dateCreated;
     private Integer orgId;

    public MtefBoVersion() {
    }

	
    public MtefBoVersion(int id, BudgetYears budgetYears, Users users, String name, Date dateCreated) {
        this.id = id;
        this.budgetYears = budgetYears;
        this.users = users;
        this.name = name;
        this.dateCreated = dateCreated;
    }
    public MtefBoVersion(int id, BudgetYears budgetYears, Users users, String name, Date dateCreated, Integer orgId) {
       this.id = id;
       this.budgetYears = budgetYears;
       this.users = users;
       this.name = name;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
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
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
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




}


