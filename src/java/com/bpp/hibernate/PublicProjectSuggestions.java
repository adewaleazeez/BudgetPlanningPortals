package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * PublicProjectSuggestions generated by hbm2java
 */
public class PublicProjectSuggestions  implements java.io.Serializable {


     private int id;
     private BudgetYears budgetYears;
     private Lgas lgas;
     private Users users;
     private String title;
     private String description;
     private Date dateCreated;
     private Integer orgId;

    public PublicProjectSuggestions() {
    }

	
    public PublicProjectSuggestions(int id, BudgetYears budgetYears, Lgas lgas, Users users, String title, String description, Date dateCreated) {
        this.id = id;
        this.budgetYears = budgetYears;
        this.lgas = lgas;
        this.users = users;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
    }
    public PublicProjectSuggestions(int id, BudgetYears budgetYears, Lgas lgas, Users users, String title, String description, Date dateCreated, Integer orgId) {
       this.id = id;
       this.budgetYears = budgetYears;
       this.lgas = lgas;
       this.users = users;
       this.title = title;
       this.description = description;
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
    public Lgas getLgas() {
        return this.lgas;
    }
    
    public void setLgas(Lgas lgas) {
        this.lgas = lgas;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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
    public Integer getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }




}


