package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Notifications generated by hbm2java
 */
public class Notifications  implements java.io.Serializable {


     private int id;
     private NotificationTypes notificationTypes;
     private RequestTypes requestTypes;
     private String notificationSubject;
     private String notificationText;
     private int roleId;
     private Date dateCreated;
     private Integer orgId;

    public Notifications() {
    }

	
    public Notifications(int id, NotificationTypes notificationTypes, RequestTypes requestTypes, String notificationSubject, int roleId, Date dateCreated) {
        this.id = id;
        this.notificationTypes = notificationTypes;
        this.requestTypes = requestTypes;
        this.notificationSubject = notificationSubject;
        this.roleId = roleId;
        this.dateCreated = dateCreated;
    }
    public Notifications(int id, NotificationTypes notificationTypes, RequestTypes requestTypes, String notificationSubject, String notificationText, int roleId, Date dateCreated, Integer orgId) {
       this.id = id;
       this.notificationTypes = notificationTypes;
       this.requestTypes = requestTypes;
       this.notificationSubject = notificationSubject;
       this.notificationText = notificationText;
       this.roleId = roleId;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public NotificationTypes getNotificationTypes() {
        return this.notificationTypes;
    }
    
    public void setNotificationTypes(NotificationTypes notificationTypes) {
        this.notificationTypes = notificationTypes;
    }
    public RequestTypes getRequestTypes() {
        return this.requestTypes;
    }
    
    public void setRequestTypes(RequestTypes requestTypes) {
        this.requestTypes = requestTypes;
    }
    public String getNotificationSubject() {
        return this.notificationSubject;
    }
    
    public void setNotificationSubject(String notificationSubject) {
        this.notificationSubject = notificationSubject;
    }
    public String getNotificationText() {
        return this.notificationText;
    }
    
    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
    public int getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
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


