package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EconomicSegmentHeader3 generated by hbm2java
 */
public class EconomicSegmentHeader3  implements java.io.Serializable {


     private int id;
     private EconomicSegmentHeader2 economicSegmentHeader2;
     private String name;
     private String code;
     private Date dateCreated;
     private Integer orgId;
     private Set economicSegmentHeader4s = new HashSet(0);

    public EconomicSegmentHeader3() {
    }

	
    public EconomicSegmentHeader3(int id, EconomicSegmentHeader2 economicSegmentHeader2, String name, String code, Date dateCreated) {
        this.id = id;
        this.economicSegmentHeader2 = economicSegmentHeader2;
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
    }
    public EconomicSegmentHeader3(int id, EconomicSegmentHeader2 economicSegmentHeader2, String name, String code, Date dateCreated, Integer orgId, Set economicSegmentHeader4s) {
       this.id = id;
       this.economicSegmentHeader2 = economicSegmentHeader2;
       this.name = name;
       this.code = code;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.economicSegmentHeader4s = economicSegmentHeader4s;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public EconomicSegmentHeader2 getEconomicSegmentHeader2() {
        return this.economicSegmentHeader2;
    }
    
    public void setEconomicSegmentHeader2(EconomicSegmentHeader2 economicSegmentHeader2) {
        this.economicSegmentHeader2 = economicSegmentHeader2;
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
    public Set getEconomicSegmentHeader4s() {
        return this.economicSegmentHeader4s;
    }
    
    public void setEconomicSegmentHeader4s(Set economicSegmentHeader4s) {
        this.economicSegmentHeader4s = economicSegmentHeader4s;
    }




}

