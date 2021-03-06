package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * FundSegmentHeader1 generated by hbm2java
 */
public class FundSegmentHeader1  implements java.io.Serializable {


     private int id;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;

    public FundSegmentHeader1() {
    }

	
    public FundSegmentHeader1(int id, String name, String code, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public FundSegmentHeader1(int id, String name, String code, Date dateCreated, Integer orgId) {
       this.id = id;
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


