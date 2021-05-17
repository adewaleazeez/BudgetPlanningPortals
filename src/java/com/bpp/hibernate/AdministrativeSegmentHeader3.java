package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AdministrativeSegmentHeader3 generated by hbm2java
 */
public class AdministrativeSegmentHeader3  implements java.io.Serializable {


     private int id;
     private AdministrativeSegmentHeader2 administrativeSegmentHeader2;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;
     private Set administrativeSegments = new HashSet(0);

    public AdministrativeSegmentHeader3() {
    }

	
    public AdministrativeSegmentHeader3(int id, AdministrativeSegmentHeader2 administrativeSegmentHeader2, String name, String code, Date dateCreated) {
        this.id = id;
        this.administrativeSegmentHeader2 = administrativeSegmentHeader2;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public AdministrativeSegmentHeader3(int id, AdministrativeSegmentHeader2 administrativeSegmentHeader2, String name, String code, Date dateCreated, Integer orgId, Set administrativeSegments) {
       this.id = id;
       this.administrativeSegmentHeader2 = administrativeSegmentHeader2;
       this.name = name;
       this.code = code;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.administrativeSegments = administrativeSegments;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public AdministrativeSegmentHeader2 getAdministrativeSegmentHeader2() {
        return this.administrativeSegmentHeader2;
    }
    
    public void setAdministrativeSegmentHeader2(AdministrativeSegmentHeader2 administrativeSegmentHeader2) {
        this.administrativeSegmentHeader2 = administrativeSegmentHeader2;
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
    public Set getAdministrativeSegments() {
        return this.administrativeSegments;
    }
    
    public void setAdministrativeSegments(Set administrativeSegments) {
        this.administrativeSegments = administrativeSegments;
    }




}


