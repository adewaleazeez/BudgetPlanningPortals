package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EconomicSegmentHeader1 generated by hbm2java
 */
public class EconomicSegmentHeader1  implements java.io.Serializable {


     private int id;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;
     private Set economicSegmentHeader2s = new HashSet(0);

    public EconomicSegmentHeader1() {
    }

	
    public EconomicSegmentHeader1(int id, String name, String code, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public EconomicSegmentHeader1(int id, String name, String code, Date dateCreated, Integer orgId, Set economicSegmentHeader2s) {
       this.id = id;
       this.name = name;
       this.code = code;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.economicSegmentHeader2s = economicSegmentHeader2s;
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
    public Set getEconomicSegmentHeader2s() {
        return this.economicSegmentHeader2s;
    }
    
    public void setEconomicSegmentHeader2s(Set economicSegmentHeader2s) {
        this.economicSegmentHeader2s = economicSegmentHeader2s;
    }




}


