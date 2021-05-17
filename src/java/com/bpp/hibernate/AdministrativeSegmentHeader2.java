package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AdministrativeSegmentHeader2 generated by hbm2java
 */
public class AdministrativeSegmentHeader2  implements java.io.Serializable {


     private int id;
     private AdministrativeSegmentHeader1 administrativeSegmentHeader1;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;
     private Set administrativeSegmentHeader3s = new HashSet(0);

    public AdministrativeSegmentHeader2() {
    }

	
    public AdministrativeSegmentHeader2(int id, AdministrativeSegmentHeader1 administrativeSegmentHeader1, String name, String code, Date dateCreated) {
        this.id = id;
        this.administrativeSegmentHeader1 = administrativeSegmentHeader1;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public AdministrativeSegmentHeader2(int id, AdministrativeSegmentHeader1 administrativeSegmentHeader1, String name, String code, Date dateCreated, Integer orgId, Set administrativeSegmentHeader3s) {
       this.id = id;
       this.administrativeSegmentHeader1 = administrativeSegmentHeader1;
       this.name = name;
       this.code = code;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.administrativeSegmentHeader3s = administrativeSegmentHeader3s;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public AdministrativeSegmentHeader1 getAdministrativeSegmentHeader1() {
        return this.administrativeSegmentHeader1;
    }
    
    public void setAdministrativeSegmentHeader1(AdministrativeSegmentHeader1 administrativeSegmentHeader1) {
        this.administrativeSegmentHeader1 = administrativeSegmentHeader1;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
    public Set getAdministrativeSegmentHeader3s() {
        return this.administrativeSegmentHeader3s;
    }
    
    public void setAdministrativeSegmentHeader3s(Set administrativeSegmentHeader3s) {
        this.administrativeSegmentHeader3s = administrativeSegmentHeader3s;
    }




}


