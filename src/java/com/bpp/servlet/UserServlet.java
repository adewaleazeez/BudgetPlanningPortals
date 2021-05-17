/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.BudgetTimetable;
import com.bpp.hibernate.BudgetTimetableHibernateHelper;
import com.bpp.hibernate.BudgetYearHibernateHelper;
import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.MenuUserHibernateHelper;
import com.bpp.hibernate.Roles;
import com.bpp.hibernate.RolesHibernateHelper;
import com.bpp.hibernate.Users;
import com.bpp.hibernate.UserRole;
import com.bpp.hibernate.UserRoleHibernateHelper;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            //UsersHibernateHelper userhelper = new UsersHibernateHelper();
            ////userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            UsersHibernateHelper helper = new UsersHibernateHelper();
            UserRoleHibernateHelper userrolehelper = new UserRoleHibernateHelper();
            RolesHibernateHelper rolehelper = new RolesHibernateHelper();
            MenuUserHibernateHelper menuUserHelper = new MenuUserHibernateHelper();
            Users users = new Users();
            UserRole userole = new UserRole();
            Roles role = new Roles();
            Utility utility = new Utility();

            String option;
            String id;
            String username;
            String firstname;
            String lastname;
            String email;
            String deptId;
            String mdaId;
            String phoneNo;
            String roleId;
            String userPassword;
            String userstatus;
            String userimage;
            String dateCreated;
            String orgId;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            username = request.getParameter("username");
            if (username == null) {
                username = "";
            }

            firstname = request.getParameter("firstname");
            if (firstname == null) {
                firstname = "";
            }

            lastname = request.getParameter("lastname");
            if (lastname == null) {
                lastname = "";
            }

            email = request.getParameter("email");
            if (email == null) {
                email = "";
            }

            deptId = request.getParameter("deptId");
            if (deptId == null) {
                deptId = "";
            }

            mdaId = request.getParameter("mdaId");
            if (mdaId == null) {
                mdaId = "";
            }

            phoneNo = request.getParameter("phoneNo");
            if (phoneNo == null) {
                phoneNo = "";
            }

            roleId = request.getParameter("roleId");
            if (roleId == null) {
                roleId = "";
            }

            userPassword = request.getParameter("password");
            if (userPassword == null) {
                userPassword = "";
            }

            userstatus = request.getParameter("userstatus");
            if (userstatus == null) {
                userstatus = "";
            }

            userimage = request.getParameter("userimage");
            if (userimage == null) {
                userimage = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "1";
            }

            if (option.equals(Utility.OPTION_CHECK_LOGIN)) {
                resp = Utility.ActionResponse.FAILED.toString();
                users = helper.exists(username);
                if (users != null) {
                    if (users.getUserstatus().equals("true")) {
                        String db_password = users.getUserPassword();
                        byte[] db_salt = users.getPasswordSalt();
                        String hashed_salted_db_password = utility.get_SHA_256_SecurePassword(userPassword, db_salt);
                        Set hset = users.getUserRoles();
                        String usersrole = "";
                        String userroleids = "";
                        //Iterator it = hset.iterator();
                        //it.hasNext();
                        for (Iterator it = hset.iterator(); it.hasNext();) {
                            UserRole element = (UserRole) it.next();
                            usersrole += ((usersrole.equals("")) ? "" : ",") + element.getRoles().getName();
                            userroleids += element.getRoles().getId();                            
                            if(it.hasNext()){
                            	userroleids += ",";
                            }
                        }
                        //System.out.println("usersrole "+usersrole);
                        //System.out.println("userroleids "+userroleids);
                        if (users.getUsername().equals(username) && db_password.equals(hashed_salted_db_password)) {
                            users.setLastLoginDate(utility.dbDateNow());
                            helper.saveUserIdSession(users.getId());
                            helper.update(users);
                            MdaHibernateHelper mdahelper = new MdaHibernateHelper();

                            session.setAttribute("userid", users.getId() + "");
                            session.setAttribute("username", username);
                            session.setAttribute("fname", users.getFirstname());       
                            session.setAttribute("lname", users.getLastname());
                            session.setAttribute("userdp", users.getUserimage());
                            session.setAttribute("userrole", usersrole);
                            session.setAttribute("userroles", userroleids);
                            session.setAttribute("userid", users.getId());
                            session.setAttribute("deptid", users.getDeptId());
                            session.setAttribute("mdaid",users.getMdaId()); // new DepartmentHibernateHelper().fetchObj(users.getDeptId()).getMdaId());
                            session.setAttribute("usermda",mdahelper.exists(users.getMdaId()).getName());
                            BudgetTimetableHibernateHelper bth = new BudgetTimetableHibernateHelper();
                            BudgetTimetable bt = bth.fetchObj2(new BudgetYearHibernateHelper().fetchCurrentYear2().getId());
                            if(bt != null){
                                session.setAttribute("currenttimetableid", bth.fetchObj2(new BudgetYearHibernateHelper().fetchCurrentYear2().getId()).toString());
                            }else{
                                session.setAttribute("currenttimetableid", "0");
                            }
                            session.setMaxInactiveInterval(0);
                            createCookie(response, "userid", users.getId() + "");
                            createCookie(response, "username", username);
                            createCookie(response, "userrole", usersrole);
                            createCookie(response, "siteurl", Utility.SITE_URL);
                            resp = Utility.ActionResponse.SUCCESSFULL.toString();
                        }
                    } else {
                        resp = Utility.ActionResponse.BLOCKED.toString();

                    }
                }
                //Gson gson = new Gson();
                //resp = gson.toJson(resp);
            }
            
            if (option.equals(Utility.OPTION_CHECK_USER_STATUS)) {
                resp = Utility.ActionResponse.FAILED.toString();
                users = helper.exists(Integer.parseInt(id));
                if (users != null) {
                    if (users.getUserstatus().equals("true")) {
                        resp = Utility.ActionResponse.RECORD_EXISTS.toString();
                    } else {
                        resp = Utility.ActionResponse.BLOCKED.toString();
                    }
                }
            }
            if (option.equals(Utility.OPTION_RESET_MENU)) {
                resp = Utility.ActionResponse.FAILED.toString();
                users = helper.exists(Integer.parseInt(id));
                if (users != null) {
                    resp = menuUserHelper.fetchAllForMenu(users.getId() + "");
//                    JsonParser parser = new JsonParser();
//                    Object jsonEleArray = parser.parse(new StringReader(resp));
//                    JsonElement jsonElement = (JsonElement) jsonEleArray;
//                    JsonArray jarr = jsonElement.getAsJsonArray();
//
//                    String mymenu = "<ul class='side-nav color-gray'>";
//                    mymenu += "<li class='nav-header'>";
//                    mymenu += "<span class=''>Menu</span>";
//                    mymenu += "</li>";
//                    String menuCategory = "";
//                    for (JsonElement je : jarr) {
//                        JsonElement jsonSubElement = (JsonElement) je;
//                        JsonArray subjarr = jsonSubElement.getAsJsonArray();
//                        int counter = 0;
//                        for (JsonElement subje : subjarr) {
//                            if (counter == 3 && !menuCategory.equals(subje.toString())) {
//                                if (!menuCategory.equals("")) {
//                                    mymenu += "</ul></li>";
//                                }
//                                menuCategory = subje.toString();
//                                mymenu += "<li class='has-children'>";
//                                mymenu += "<a><i class='fa fa-file-text'></i> <span>" + menuCategory + "</span> <i class='fa fa-angle-right arrow'></i></a>";
//                                mymenu += "<ul class='child-nav'>";
//                            }
//                            if (counter == 4) {
//                                String menuUrl = subje.toString();
//                                mymenu += "<li><a onclick=gotoLink('/"+menuUrl+"')><i class='fa fa-thumb-tack'></i>";
//                                //mymenu += "<li><a onclick=gotoLink('"+Utility.SITE_URL+"/"+menuUrl+"')><i class='fa fa-thumb-tack'></i>";
//                                //mymenu += "<li><a href='" + Utility.SITE_URL + "/" + menuUrl + "' ><i class='fa fa-thumb-tack'></i>";
//                            }
//                            if (counter == 5) {
//                                String menuName = subje.toString();
//                                mymenu += "<span>" + menuName + "</span></a></li>";
//                            }
//                            counter++;
//                        }
//                    }
//                    mymenu += "</ul>";
//                    session.setAttribute("mymenu", mymenu);
//                    session.setMaxInactiveInterval(0);
//                    createCookie(response, "mymenu", mymenu);
//                    resp = Utility.ActionResponse.SUCCESSFULL.toString();
                }
            }

            if (option.equals(Utility.OPTION_REQUEST_PASSWORD_RESET)) {
                //resp = Utility.ActionResponse.FAILED.toString();
                users = helper.exists(email);
                if (users != null) {
                    if (users.getUserstatus().equals("true")) {
                        String emailSubject = "RE: Request For Password Reset";
                        String emailBody = "Dear " + users.getFirstname() + ",<br><br>"
                                + "Click the link below to reset your password: <br><br>"
                                + "<a href='http://" + Utility.SERVER_NAME + ":" + Utility.SERVER_PORT + Utility.SITE_URL
                                + "/UserServlet?option=" + Utility.OPTION_RESET_PASSWORD + "&email=" + Utility.reverseString(users.getEmail()) + "'>Click here to reset password</a><br><br><br>"
                                + "Regards,";
                        String emailrecipients = email;
                        utility.setMailServerProperties();
                        utility.createEmailMessage(emailSubject, emailBody, emailrecipients);
                        utility.sendEmail();
                        resp = Utility.ActionResponse.SUCCESSFULL.toString();
                    } else {
                        resp = Utility.ActionResponse.BLOCKED.toString();
                    }
                }else{
                    resp = Utility.ActionResponse.NO_RECORD.toString();
                }
            }

            if (option.equals(Utility.OPTION_RESET_PASSWORD)) {
                email = Utility.reverseString(email);
                users = helper.exists(email);
                if (users != null) {
                    if (users.getUserstatus().equals("true")) {
                        synchronized (this) {
                            UsersHibernateHelper userhelper = new UsersHibernateHelper();
                            //userhelper.saveUserIdSession(Integer.parseInt(users.getId()+""));
                            //userPassword = Utility.randomAlphaNumeric(10);
                            userPassword = Utility.reverseString(users.getLastname());
                            byte[] db_salt = utility.getSalt();
                            String hashed_salted_db_password = utility.get_SHA_256_SecurePassword(userPassword, db_salt);

                            users.setUserPassword(hashed_salted_db_password);
                            users.setPasswordSalt(db_salt);
                            resp = helper.update(users);
                            if (resp.equals(Utility.ActionResponse.UPDATED.toString())) {
                                String emailSubject = "RE: Password Reset";
                                String emailBody = "Dear " + users.getFirstname() + ",<br><br>"
                                        + "Your Password for accessing the Budget Planning Portals was successfully reset, the new password is stated below: <br><br>"
                                        + "Username: " + email + "<br>"
                                        + "Password: " + userPassword + "<br><br>"
                                        + "You are hereby advised to keep these information secrete as any attemp to be careless with it could compromise your account.<br><br>"
                                        + "You can change your password when you login to the system.";
                                String emailrecipients = email;
                                utility.setMailServerProperties();
                                utility.createEmailMessage(emailSubject, emailBody, emailrecipients);
                                utility.sendEmail();
                                createCookie(response, "passwordreset", "successful");
                                getServletContext().getRequestDispatcher("/codem00017").forward(request, response);
                            }
                        }
                    } else {
                        createCookie(response, "passwordreset", "failed");
                        getServletContext().getRequestDispatcher("/codem00017").forward(request, response);
                    }
                }
            }

            if (option.equals(Utility.OPTION_CHANGE_PASSWORD)) {
                users = helper.exists(username);
                if (users != null) {
                    if (users.getUserstatus().equals("true")) {
                        synchronized (this) {
                            UsersHibernateHelper userhelper = new UsersHibernateHelper();
                            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));
                            
                            //userPassword = Utility.randomAlphaNumeric(10);
                            String db_password = users.getUserPassword();
                            byte[] db_salt = utility.getSalt();
                            String hashed_salted_old_password = utility.get_SHA_256_SecurePassword(id, db_salt);
                            if (db_password.equals(hashed_salted_old_password)) {
                                String hashed_salted_db_password = utility.get_SHA_256_SecurePassword(userPassword, db_salt);
                                users.setUserPassword(hashed_salted_db_password);
                                users.setPasswordSalt(db_salt);
                                resp = helper.update(users);
                                if (resp.equals(Utility.ActionResponse.UPDATED.toString())) {
                                    String emailSubject = "RE: Password Change";
                                    String emailBody = "Dear " + users.getFirstname() + ",<br><br>"
                                            + "Your Password for accessing the Budget Planning Portals was successfully changed, the new password is stated below: <br><br>"
                                            + "Username: " + username + "<br>"
                                            + "Password: " + userPassword + "<br><br>"
                                            + "You are hereby advised to keep these information secrete as any attemp to be careless with it could compromise your account.<br><br>"
                                            + "You can change your password when you login to the system.";
                                    String emailrecipients = username;
                                    utility.setMailServerProperties();
                                    utility.createEmailMessage(emailSubject, emailBody, emailrecipients);
                                    utility.sendEmail();
                                    resp = Utility.ActionResponse.SUCCESSFULL.toString();
                                }
                            } else {//oldpassword
                                resp = Utility.ActionResponse.FAILED.toString();
                            }
                        }
                    } else {
                        resp = Utility.ActionResponse.BLOCKED.toString();
                    }
                }
            }

            if (option.equals(Utility.OPTION_RESET_COOKIE)) {
                createCookie(response, id, "");
            }
            if (option.equals(Utility.OPTION_INSERT)) {
                resp = Utility.ActionResponse.RECORD_EXISTS.toString();
                users = helper.exists(username);
                if (users == null) {
                    synchronized (this) {
                        UsersHibernateHelper userhelper = new UsersHibernateHelper();
                        //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

                        users = new Users();
                        userPassword = Utility.randomAlphaNumeric(10);
                        byte[] db_salt = utility.getSalt();
                        String hashed_salted_db_password = utility.get_SHA_256_SecurePassword(userPassword, db_salt);
                        users.setId(Integer.parseInt(helper.getMaxSerialNo("Users")) + 1);
                        users.setDateCreated(utility.dbDateNow());
                        users.setDeptId(Integer.parseInt(deptId.trim()));
                        users.setMdaId(Integer.parseInt(mdaId.trim()));
                        users.setEmail(email);
                        users.setFirstname(firstname);
                        users.setLastname(lastname);
                        users.setPhoneno(phoneNo);
                        users.setOrgId(Integer.parseInt(orgId));
                        users.setUserPassword(hashed_salted_db_password);
                        users.setPasswordSalt(db_salt);
                        users.setUsername(username);
                        users.setUserstatus(userstatus);
                        users.setUserimage(userimage);
                        resp = helper.insert(users);
                        if (resp.equals(Utility.ActionResponse.INSERTED.toString())) {
                            String[] splited_roles = roleId.split(",");
                            //System.out.println("roleId  "+roleId);
                            //System.out.println("splited_roles  "+splited_roles[0]);
                            for (String splited_role : splited_roles) {
                                role = rolehelper.exists(Integer.parseInt(splited_role));
                                userole = new UserRole();
                                userole.setId(Integer.parseInt(userrolehelper.getMaxSerialNo("User_Role")) + 1);
                                userole.setUsers(users);
                                userole.setRoles(role);
                                userole.setDateCreated(utility.dbDateNow());
                                userole.setOrgId(Integer.parseInt(orgId));
                                userrolehelper.insert(userole);
                                //System.out.println("getDateCreated   "+userole.getDateCreated());
                                //System.out.println("getOrgId  "+userole.getOrgId());
                                //System.out.println("getRoles  "+userole.getRoles());
                                //System.out.println("getUsers  "+userole.getUsers());
                            }
                            String emailSubject = "RE: User Account Details";
                            String emailBody = "Dear " + firstname + ",<br><br>"
                                    + "Your Username and Password for accessing the Budget Planning Portals are stated below: <br><br>"
                                    + "Username: " + email + "<br>"
                                    + "Password: " + userPassword + "<br><br>"
                                    + "You are hereby advised to keep these information secrete as any attemp to be careless with it could compromise your account.<br><br>"
                                    + "You can change your password when you login to the system.";
                            String emailrecipients = email;
                            utility.setMailServerProperties();
                            utility.createEmailMessage(emailSubject, emailBody, emailrecipients);
                            utility.sendEmail();
                        }
                    }
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                resp = Utility.ActionResponse.NO_RECORD.toString();
                users = helper.exists(username);
                if (users != null) {
                    synchronized (this) {
                        UsersHibernateHelper userhelper = new UsersHibernateHelper();
                        //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

                        users.setDeptId(Integer.parseInt(deptId.trim()));
                        users.setMdaId(Integer.parseInt(mdaId.trim()));
                        users.setEmail(email);
                        users.setFirstname(firstname);
                        users.setLastname(lastname);
                        users.setPhoneno(phoneNo);
                        users.setUserstatus(userstatus);
                        users.setUserimage(userimage);
                        resp = helper.update(users);
                        if (resp.equals(Utility.ActionResponse.UPDATED.toString())) {
                            //System.out.println("roleId "+roleId);
                            //System.out.println("users.getId() "+users.getId());
                            List elements = userrolehelper.fetchByUserId(users.getId());
                            //System.out.println("elements.size "+elements.size());
                            if (elements.size() > 0) {
                                Iterator i = elements.iterator();
                                while (i.hasNext()) {
                                    UserRole element = (UserRole) i.next();
                                    //System.out.println("roleId-element.getRoles().getId()  "+element.getRoles().getId());
                                    if (!roleId.contains(element.getRoles().getId() + "")) {
                                        //System.out.println("roleId userId  "+element.getRoles().getId()+"    "+users.getId());
                                        menuUserHelper.deleteById(element.getRoles().getId(), 0, users.getId());
                                    }
                                }
                            }
                            userrolehelper.deleteAll(users.getId() + "");
                            String[] splited_roles = roleId.split(",");
                            //System.out.println("roleId "+roleId);
                            for (String splited_role : splited_roles) {
                                role = rolehelper.exists(Integer.parseInt(splited_role));
                                userole.setId(Integer.parseInt(userrolehelper.getMaxSerialNo("User_Role")) + 1);
                                userole.setUsers(users);
                                userole.setRoles(role);
                                userole.setDateCreated(Utility.dbDateNow());
                                userole.setOrgId(Integer.parseInt(orgId));
                                userrolehelper.insert(userole);
                            }
                        }
                    }
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    UsersHibernateHelper userhelper = new UsersHibernateHelper();
                    //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

                    users = helper.exists(username);
                    userrolehelper.deleteAll(users.getId() + "");
                    resp = helper.delete(users);
                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_SELECT)) {
                    resp = helper.fetchAll(id);
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                if (!id.equals("")) {
                    resp = helper.fetchAll(id);
                } else {
                    resp = helper.fetchAll();
                }
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchOne(id);
            }
            if (option.equals(Utility.OPTION_SELECT_ALL_BY_MDAS)) {
                resp = helper.fetchAllByMdas(Integer.parseInt(request.getParameter("mdaID")));
            }
            out.println(resp);

//            if (option.equals("selectusers")) {
//                synchronized (this) {
//                    resp = "recordsnotexist";
//                    List elements = helper.fetchAllUsers();
//                    if (elements.size() > 0) {
//                        resp = "{\"users\":[";
//                        Iterator i = elements.iterator();
//                        while (i.hasNext()) {
//                            Users element = (Users) i.next();
//
//                            if (!resp.equals("{\"users\":[")) {
//                                resp += ",";
//                            }
//                            resp += "{\"id\" : " + '"' + element.getId() + '"' + ","
//                                    + "\"username\" : " + '"' + element.getUsername() + '"' + ","
//                                    + "\"firstname\" : " + '"' + element.getFirstname() + '"' + ","
//                                    + "\"lastname\" : " + '"' + element.getLastname() + '"' + ","
//                                    + "\"email\" : " + '"' + element.getEmail() + '"' + ","
//                                    + "\"dept_id\" : " + '"' + element.getDeptId() + '"' + ","
//                                    + "\"user_password\" : " + '"' + element.getUserPassword() + '"' + ","
//                                    + "\"date_created\" : " + '"' + element.getDateCreated() + '"' + ","
//                                    + "\"org_id\" : " + '"' + element.getOrgId() + '"' + "}";
//                        }
//                        resp += "]}";
//                    }
//                }
//                Gson gson = new Gson();
//                resp = gson.toJson(resp);
//            }
        } finally {
            out.close();
        }
    }

    public synchronized String readCookie(HttpServletRequest request, String cookiename) {
        Cookie[] cookies = request.getCookies();
        String resp = "";
        for (Cookie c : cookies) {
            if (c.getName().equals(cookiename)) {
                resp = c.getValue();
            }
        }
        return resp;
    }

//    public synchronized String readCookieValues(HttpServletRequest request, String cookiename) {
//        Cookie[] cookies = request.getCookies();
//        String resp = "";
//        for (Cookie c : cookies) {
//            if (c.getName().equals(cookiename)) {
//                resp = "value: " + c.getValue() + "   name: " + c.getName() + "   domain: " + c.getDomain() + "   path: " + c.getPath();
//            }
//        }
//        return resp;
//    }
    public synchronized void createCookie(HttpServletResponse response, String cookiename, String cookievalue) {
        Cookie c = new Cookie(cookiename, cookievalue);
        c.setMaxAge(24 * 60 * 60);
        response.addCookie(c);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
