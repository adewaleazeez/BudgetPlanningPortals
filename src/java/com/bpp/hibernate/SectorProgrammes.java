package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * SectorProgrammes generated by hbm2java
 */
public class SectorProgrammes  implements java.io.Serializable {


     private int id;
     private String sectorProgrammeCode;
     private String name;
     private Date dateCreated;
     private Integer orgId;

    public SectorProgrammes() {
    }

	
    public SectorProgrammes(int id, String sectorProgrammeCode, String name) {
        this.id = id;
        this.sectorProgrammeCode = sectorProgrammeCode;
        this.name = name;
    }
    public SectorProgrammes(int id, String sectorProgrammeCode, String name, Date dateCreated, Integer orgId) {
       this.id = id;
       this.sectorProgrammeCode = sectorProgrammeCode;
       this.name = name;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getSectorProgrammeCode() {
        return this.sectorProgrammeCode;
    }
    
    public void setSectorProgrammeCode(String sectorProgrammeCode) {
        this.sectorProgrammeCode = sectorProgrammeCode;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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


