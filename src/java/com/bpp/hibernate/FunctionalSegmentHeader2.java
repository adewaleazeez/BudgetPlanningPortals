package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FunctionalSegmentHeader2 generated by hbm2java
 */
public class FunctionalSegmentHeader2  implements java.io.Serializable {


     private int id;
     private FunctionalSegmentHeader1 functionalSegmentHeader1;
     private Date dateCreated;
     private Integer orgId;
     private String code;
     private String name;
     private Set functionalSegments = new HashSet(0);

    public FunctionalSegmentHeader2() {
    }

	
    public FunctionalSegmentHeader2(int id, FunctionalSegmentHeader1 functionalSegmentHeader1, Date dateCreated, String name) {
        this.id = id;
        this.functionalSegmentHeader1 = functionalSegmentHeader1;
        this.dateCreated = dateCreated;
        this.name = name;
    }
    public FunctionalSegmentHeader2(int id, FunctionalSegmentHeader1 functionalSegmentHeader1, Date dateCreated, Integer orgId, String code, String name, Set functionalSegments) {
       this.id = id;
       this.functionalSegmentHeader1 = functionalSegmentHeader1;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.code = code;
       this.name = name;
       this.functionalSegments = functionalSegments;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public FunctionalSegmentHeader1 getFunctionalSegmentHeader1() {
        return this.functionalSegmentHeader1;
    }
    
    public void setFunctionalSegmentHeader1(FunctionalSegmentHeader1 functionalSegmentHeader1) {
        this.functionalSegmentHeader1 = functionalSegmentHeader1;
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
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getFunctionalSegments() {
        return this.functionalSegments;
    }
    
    public void setFunctionalSegments(Set functionalSegments) {
        this.functionalSegments = functionalSegments;
    }




}

