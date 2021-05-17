package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Roles generated by hbm2java
 */
public class Roles  implements java.io.Serializable {


     private int id;
     private String name;
     private Date dateCreated;
     private Integer orgId;
     private Set userRoles = new HashSet(0);
     private Set menuRoles = new HashSet(0);
     private Set requestAgentses = new HashSet(0);

    public Roles() {
    }

	
    public Roles(int id, String name, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }
    public Roles(int id, String name, Date dateCreated, Integer orgId, Set userRoles, Set menuRoles, Set requestAgentses) {
       this.id = id;
       this.name = name;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.userRoles = userRoles;
       this.menuRoles = menuRoles;
       this.requestAgentses = requestAgentses;
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
    public Set getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set userRoles) {
        this.userRoles = userRoles;
    }
    public Set getMenuRoles() {
        return this.menuRoles;
    }
    
    public void setMenuRoles(Set menuRoles) {
        this.menuRoles = menuRoles;
    }
    public Set getRequestAgentses() {
        return this.requestAgentses;
    }
    
    public void setRequestAgentses(Set requestAgentses) {
        this.requestAgentses = requestAgentses;
    }




}

