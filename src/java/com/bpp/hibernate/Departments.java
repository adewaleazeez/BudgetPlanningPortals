package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Departments generated by hbm2java
 */
public class Departments  implements java.io.Serializable {


     private int id;
     private String name;
     private int mdaId;
     private Date dateCreated;
     private Integer orgId;
     private String administrativeSegment;
     private Set departmentHods = new HashSet(0);

    public Departments() {
    }

	
    public Departments(int id, String name, int mdaId, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.mdaId = mdaId;
        this.dateCreated = dateCreated;
    }
    public Departments(int id, String name, int mdaId, Date dateCreated, Integer orgId, String administrativeSegment, Set departmentHods) {
       this.id = id;
       this.name = name;
       this.mdaId = mdaId;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.administrativeSegment = administrativeSegment;
       this.departmentHods = departmentHods;
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
    public int getMdaId() {
        return this.mdaId;
    }
    
    public void setMdaId(int mdaId) {
        this.mdaId = mdaId;
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
    public String getAdministrativeSegment() {
        return this.administrativeSegment;
    }
    
    public void setAdministrativeSegment(String administrativeSegment) {
        this.administrativeSegment = administrativeSegment;
    }
    public Set getDepartmentHods() {
        return this.departmentHods;
    }
    
    public void setDepartmentHods(Set departmentHods) {
        this.departmentHods = departmentHods;
    }




}


