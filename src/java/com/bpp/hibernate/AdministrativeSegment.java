package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AdministrativeSegment generated by hbm2java
 */
public class AdministrativeSegment  implements java.io.Serializable {


     private int id;
     private AdministrativeSegmentHeader3 administrativeSegmentHeader3;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;

    public AdministrativeSegment() {
    }

	
    public AdministrativeSegment(int id, AdministrativeSegmentHeader3 administrativeSegmentHeader3, String name, String code, Date dateCreated) {
        this.id = id;
        this.administrativeSegmentHeader3 = administrativeSegmentHeader3;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public AdministrativeSegment(int id, AdministrativeSegmentHeader3 administrativeSegmentHeader3, String name, String code, Date dateCreated, Integer orgId) {
       this.id = id;
       this.administrativeSegmentHeader3 = administrativeSegmentHeader3;
       this.name = name;
       this.code = code;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public AdministrativeSegmentHeader3 getAdministrativeSegmentHeader3() {
        return this.administrativeSegmentHeader3;
    }
    
    public void setAdministrativeSegmentHeader3(AdministrativeSegmentHeader3 administrativeSegmentHeader3) {
        this.administrativeSegmentHeader3 = administrativeSegmentHeader3;
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




}


