package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * MeetingDeliberations generated by hbm2java
 */
public class MeetingDeliberations  implements java.io.Serializable {


     private int id;
     private MeetingDetails meetingDetails;
     private Users users;
     private String deliberationText;
     private Date timeline;
     private Date dateCreated;
     private Integer orgId;

    public MeetingDeliberations() {
    }

	
    public MeetingDeliberations(int id, MeetingDetails meetingDetails, Users users, Date timeline, Date dateCreated) {
        this.id = id;
        this.meetingDetails = meetingDetails;
        this.users = users;
        this.timeline = timeline;
        this.dateCreated = dateCreated;
    }
    public MeetingDeliberations(int id, MeetingDetails meetingDetails, Users users, String deliberationText, Date timeline, Date dateCreated, Integer orgId) {
       this.id = id;
       this.meetingDetails = meetingDetails;
       this.users = users;
       this.deliberationText = deliberationText;
       this.timeline = timeline;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public MeetingDetails getMeetingDetails() {
        return this.meetingDetails;
    }
    
    public void setMeetingDetails(MeetingDetails meetingDetails) {
        this.meetingDetails = meetingDetails;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public String getDeliberationText() {
        return this.deliberationText;
    }
    
    public void setDeliberationText(String deliberationText) {
        this.deliberationText = deliberationText;
    }
    public Date getTimeline() {
        return this.timeline;
    }
    
    public void setTimeline(Date timeline) {
        this.timeline = timeline;
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


