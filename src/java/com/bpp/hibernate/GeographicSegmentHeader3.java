package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * GeographicSegmentHeader3 generated by hbm2java
 */
public class GeographicSegmentHeader3  implements java.io.Serializable {


     private int id;
     private GeographicSegmentHeader2 geographicSegmentHeader2;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;
     private Set geographicSegments = new HashSet(0);

    public GeographicSegmentHeader3() {
    }

	
    public GeographicSegmentHeader3(int id, GeographicSegmentHeader2 geographicSegmentHeader2, String name, String code, Date dateCreated) {
        this.id = id;
        this.geographicSegmentHeader2 = geographicSegmentHeader2;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public GeographicSegmentHeader3(int id, GeographicSegmentHeader2 geographicSegmentHeader2, String name, String code, Date dateCreated, Integer orgId, Set geographicSegments) {
       this.id = id;
       this.geographicSegmentHeader2 = geographicSegmentHeader2;
       this.name = name;
       this.code = code;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.geographicSegments = geographicSegments;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public GeographicSegmentHeader2 getGeographicSegmentHeader2() {
        return this.geographicSegmentHeader2;
    }
    
    public void setGeographicSegmentHeader2(GeographicSegmentHeader2 geographicSegmentHeader2) {
        this.geographicSegmentHeader2 = geographicSegmentHeader2;
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
    public Set getGeographicSegments() {
        return this.geographicSegments;
    }
    
    public void setGeographicSegments(Set geographicSegments) {
        this.geographicSegments = geographicSegments;
    }




}

