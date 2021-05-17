package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private int id;
     private String username;
     private String firstname;
     private String lastname;
     private String email;
     private int deptId;
     private String userPassword;
     private Date dateCreated;
     private Integer orgId;
     private byte[] passwordSalt;
     private String userimage;
     private String userstatus;
     private Date lastLoginDate;
     private String phoneno;
     private int mdaId;
     private Set menuUsers = new HashSet(0);
     private Set requestApprovalses = new HashSet(0);
     private Set mybfFigureses = new HashSet(0);
     private Set auditTrailses = new HashSet(0);
     private Set publicProjectSuggestionses = new HashSet(0);
     private Set userRoles = new HashSet(0);
     private Set mybfNarrationses = new HashSet(0);
     private Set mtefDepartmentFigureses = new HashSet(0);
     private Set departmentHods = new HashSet(0);
     private Set meetingAttendantses = new HashSet(0);
     private Set meetingDeliberationses = new HashSet(0);
     private Set mtefBoVersions = new HashSet(0);

    public Users() {
    }

	
    public Users(int id, String username, String firstname, String lastname, String email, int deptId, String userPassword, int mdaId) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.deptId = deptId;
        this.userPassword = userPassword;
        this.mdaId = mdaId;
    }
    public Users(int id, String username, String firstname, String lastname, String email, int deptId, String userPassword, Date dateCreated, Integer orgId, byte[] passwordSalt, String userimage, String userstatus, Date lastLoginDate, String phoneno, int mdaId, Set menuUsers, Set requestApprovalses, Set mybfFigureses, Set auditTrailses, Set publicProjectSuggestionses, Set userRoles, Set mybfNarrationses, Set mtefDepartmentFigureses, Set departmentHods, Set meetingAttendantses, Set meetingDeliberationses, Set mtefBoVersions) {
       this.id = id;
       this.username = username;
       this.firstname = firstname;
       this.lastname = lastname;
       this.email = email;
       this.deptId = deptId;
       this.userPassword = userPassword;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
       this.passwordSalt = passwordSalt;
       this.userimage = userimage;
       this.userstatus = userstatus;
       this.lastLoginDate = lastLoginDate;
       this.phoneno = phoneno;
       this.mdaId = mdaId;
       this.menuUsers = menuUsers;
       this.requestApprovalses = requestApprovalses;
       this.mybfFigureses = mybfFigureses;
       this.auditTrailses = auditTrailses;
       this.publicProjectSuggestionses = publicProjectSuggestionses;
       this.userRoles = userRoles;
       this.mybfNarrationses = mybfNarrationses;
       this.mtefDepartmentFigureses = mtefDepartmentFigureses;
       this.departmentHods = departmentHods;
       this.meetingAttendantses = meetingAttendantses;
       this.meetingDeliberationses = meetingDeliberationses;
       this.mtefBoVersions = mtefBoVersions;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public int getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
    public byte[] getPasswordSalt() {
        return this.passwordSalt;
    }
    
    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
    public String getUserimage() {
        return this.userimage;
    }
    
    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }
    public String getUserstatus() {
        return this.userstatus;
    }
    
    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }
    public Date getLastLoginDate() {
        return this.lastLoginDate;
    }
    
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    public String getPhoneno() {
        return this.phoneno;
    }
    
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
    public int getMdaId() {
        return this.mdaId;
    }
    
    public void setMdaId(int mdaId) {
        this.mdaId = mdaId;
    }
    public Set getMenuUsers() {
        return this.menuUsers;
    }
    
    public void setMenuUsers(Set menuUsers) {
        this.menuUsers = menuUsers;
    }
    public Set getRequestApprovalses() {
        return this.requestApprovalses;
    }
    
    public void setRequestApprovalses(Set requestApprovalses) {
        this.requestApprovalses = requestApprovalses;
    }
    public Set getMybfFigureses() {
        return this.mybfFigureses;
    }
    
    public void setMybfFigureses(Set mybfFigureses) {
        this.mybfFigureses = mybfFigureses;
    }
    public Set getAuditTrailses() {
        return this.auditTrailses;
    }
    
    public void setAuditTrailses(Set auditTrailses) {
        this.auditTrailses = auditTrailses;
    }
    public Set getPublicProjectSuggestionses() {
        return this.publicProjectSuggestionses;
    }
    
    public void setPublicProjectSuggestionses(Set publicProjectSuggestionses) {
        this.publicProjectSuggestionses = publicProjectSuggestionses;
    }
    public Set getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set userRoles) {
        this.userRoles = userRoles;
    }
    public Set getMybfNarrationses() {
        return this.mybfNarrationses;
    }
    
    public void setMybfNarrationses(Set mybfNarrationses) {
        this.mybfNarrationses = mybfNarrationses;
    }
    public Set getMtefDepartmentFigureses() {
        return this.mtefDepartmentFigureses;
    }
    
    public void setMtefDepartmentFigureses(Set mtefDepartmentFigureses) {
        this.mtefDepartmentFigureses = mtefDepartmentFigureses;
    }
    public Set getDepartmentHods() {
        return this.departmentHods;
    }
    
    public void setDepartmentHods(Set departmentHods) {
        this.departmentHods = departmentHods;
    }
    public Set getMeetingAttendantses() {
        return this.meetingAttendantses;
    }
    
    public void setMeetingAttendantses(Set meetingAttendantses) {
        this.meetingAttendantses = meetingAttendantses;
    }
    public Set getMeetingDeliberationses() {
        return this.meetingDeliberationses;
    }
    
    public void setMeetingDeliberationses(Set meetingDeliberationses) {
        this.meetingDeliberationses = meetingDeliberationses;
    }
    public Set getMtefBoVersions() {
        return this.mtefBoVersions;
    }
    
    public void setMtefBoVersions(Set mtefBoVersions) {
        this.mtefBoVersions = mtefBoVersions;
    }




}

