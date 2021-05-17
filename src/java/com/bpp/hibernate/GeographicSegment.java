package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * GeographicSegment generated by hbm2java
 */
public class GeographicSegment  implements java.io.Serializable {


     private int id;
     private GeographicSegmentHeader3 geographicSegmentHeader3;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;

    public GeographicSegment() {
    }

	
    public GeographicSegment(int id, GeographicSegmentHeader3 geographicSegmentHeader3, String name, String code, Date dateCreated) {
        this.id = id;
        this.geographicSegmentHeader3 = geographicSegmentHeader3;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public GeographicSegment(int id, GeographicSegmentHeader3 geographicSegmentHeader3, String name, String code, Date dateCreated, Integer orgId) {
       this.id = id;
       this.geographicSegmentHeader3 = geographicSegmentHeader3;
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
    public GeographicSegmentHeader3 getGeographicSegmentHeader3() {
        return this.geographicSegmentHeader3;
    }
    
    public void setGeographicSegmentHeader3(GeographicSegmentHeader3 geographicSegmentHeader3) {
        this.geographicSegmentHeader3 = geographicSegmentHeader3;
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

