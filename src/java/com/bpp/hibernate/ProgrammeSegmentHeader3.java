package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * ProgrammeSegmentHeader3 generated by hbm2java
 */
public class ProgrammeSegmentHeader3  implements java.io.Serializable {


     private int id;
     private String name;
     private String code;
     private int parent;
     private Date dateCreated;
     private Integer orgId;
     private Integer mda;
     private Integer department;
     private Integer sectorGoal;
     private Integer yearId;
     private Boolean projectStatus;
     private Double score;
     private Integer rank;
     private Integer sectorProgramme;
     private String policy;

    public ProgrammeSegmentHeader3() {
    }

	
    public ProgrammeSegmentHeader3(int id, int parent, Date dateCreated) {
        this.id = id;
        this.parent = parent;
        this.dateCreated = dateCreated;
    }
    public ProgrammeSegmentHeader3(int id, String name, String code, int parent, Date dateCreated, Integer orgId, Integer mda, Integer department, Integer sectorGoal, Integer yearId, Boolean projectStatus, Double score, Integer rank, Integer sectorProgramme, String policy) {
       this.id = id;
       this.name = name;
       this.code = code;
       this.parent = parent;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.mda = mda;
       this.department = department;
       this.sectorGoal = sectorGoal;
       this.yearId = yearId;
       this.projectStatus = projectStatus;
       this.score = score;
       this.rank = rank;
       this.sectorProgramme = sectorProgramme;
       this.policy = policy;
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
    public int getParent() {
        return this.parent;
    }
    
    public void setParent(int parent) {
        this.parent = parent;
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
    public Integer getMda() {
        return this.mda;
    }
    
    public void setMda(Integer mda) {
        this.mda = mda;
    }
    public Integer getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Integer department) {
        this.department = department;
    }
    public Integer getSectorGoal() {
        return this.sectorGoal;
    }
    
    public void setSectorGoal(Integer sectorGoal) {
        this.sectorGoal = sectorGoal;
    }
    public Integer getYearId() {
        return this.yearId;
    }
    
    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }
    public Boolean getProjectStatus() {
        return this.projectStatus;
    }
    
    public void setProjectStatus(Boolean projectStatus) {
        this.projectStatus = projectStatus;
    }
    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }
    public Integer getRank() {
        return this.rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public Integer getSectorProgramme() {
        return this.sectorProgramme;
    }
    
    public void setSectorProgramme(Integer sectorProgramme) {
        this.sectorProgramme = sectorProgramme;
    }
    public String getPolicy() {
        return this.policy;
    }
    
    public void setPolicy(String policy) {
        this.policy = policy;
    }




}

