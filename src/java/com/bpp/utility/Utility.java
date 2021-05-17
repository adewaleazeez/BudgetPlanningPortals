/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.utility;

/**
 *
 * @author ADEWALE
 */
import com.bpp.hibernate.FrameworkMethodsHibernateHelper;
import com.bpp.hibernate.HibernateUtil;
import com.google.gson.Gson;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.json.JSONException;

public class Utility {

    public static final int SYSTEM_ORGANIZATION_ID = 1;

    public static final String OPTION_INSERT = "insert";
    public static final String OPTION_INSERT_SUPPLEMENTARY_RECORD = "insertsupplementaryrecord";
    public static final String OPTION_INSERT_CONTINGENCY_RECORD = "insertcontingencyrecord";
    public static final String OPTION_UPDATE = "update";
    public static final String OPTION_UPDATE_SUPPLEMENTARY_VALUE = "updatesupplementaryvalue";
    public static final String OPTION_UPDATE_SUPPLEMENTARY_RECORD = "updatesupplementaryrecord";
    public static final String OPTION_UPDATE_CONTINGENCY_VALUE = "updatecontingencyvalue";
    public static final String OPTION_UPDATE_CONTINGENCY_RECORD = "updatecontingencyrecord";
    public static final String OPTION_UPDATE_ALL = "updateall";
    public static final String OPTION_SELECT = "select";
    public static final String OPTION_DELETE = "delete";
    public static final String OPTION_DELETE_SUPPLEMENTARY_RECORD = "deletesupplementaryrecord";
    public static final String OPTION_DELETE_CONTINGENCY_RECORD = "deletecontingencyrecord";
    public static final String OPTION_DISTRIBUTE_ENVELOPES = "distributeenvelopes";
    public static final String OPTION_DISTRIBUTE_ENVELOPES_SUPPLEMENTARY_RECORD = "distributeenvelopessupplementaryrecord";
    public static final String OPTION_DISTRIBUTE_ENVELOPES_CONTINGENCY_RECORD = "distributeenvelopescontingencyrecord";
    public static final String OPTION_GET_ENVELOPES_BY_MDAS = "getenvelopesbymda";
    public static final String OPTION_GET_ENVELOPES_BY_MDAS_SUPPLEMENTARY = "getenvelopesbymdasupplementary";
    public static final String OPTION_GET_ENVELOPES_BY_MDAS_CONTINGENCY = "getenvelopesbymdacontingency";
    public static final String OPTION_SELECT_ALL = "selectall";
    public static final String OPTION_SELECT_ALL_SUPPLEMENTARY_RECORD = "selectallsupplementaryrecord";
    public static final String OPTION_SELECT_ALL_CONTINGENCY_RECORD = "selectallcontingencyrecord";
    public static final String OPTION_SELECT_ALL_BY_MDAS = "selectallbymda";
    public static final String OPTION_SELECT_ALL_BY_GROUP = "selectallbygroup";
    public static final String OPTION_CHECK_WORKFLOW_STATUS = "check_workflow";
    public static final String OPTION_SELECT_A_RECORD = "selectone";
    public static final String OPTION_SELECT_A_RECORD_SUPPLEMENTARY_VALUE = "selectonesupplementaryvalue";
    public static final String OPTION_SELECT_A_RECORD_CONTINGENCY_VALUE = "selectonecontingencyvalue";
    public static final String OPTION_CHECK_LOGIN = "checklogin";
    public static final String OPTION_REQUEST_PASSWORD_RESET = "requestpasswordreset";
    public static final String OPTION_RESET_PASSWORD = "resetpassword";
    public static final String OPTION_CHANGE_PASSWORD = "changepassword";
    public static final String OPTION_RESET_COOKIE = "resetcookie";
    public static final String OPTION_CLEAR_SESSION = "clearsession";

    public static final String OPTION_UPDATE_USER_MENUS = "updateusermenu";
    public static final String OPTION_UPDATE_ALL_MENUS_STATUS = "updateallmenustatus";
    public static final String OPTION_UPDATE_MENU_STATUS = "updatemenustatus";
    public static final String OPTION_DELETE_BY_ID_SUPPLEMENTARY_RECORD = "deletebyidsupplementaryrecord";
    public static final String OPTION_DELETE_BY_ID_CONTINGENCY_RECORD = "deletebyidcontingencyrecord";
    public static final String OPTION_DELETE_BY_ID = "deletebyid";
    public static final String OPTION_SELECT_BY_ID = "selectbyid";
    public static final String OPTION_SELECT_BY_ID_SUPPLEMENTARY_RECORD = "selectbyidsupplementaryrecord";
    public static final String OPTION_SELECT_BY_ID_CONTINGENCY_RECORD = "selectbyidcontingencyrecord";
    public static final String OPTION_SELECT_BY_NAME = "selectbyname";
    public static final String OPTION_CHECK_USER_STATUS = "checkuserstatus";
    public static final String OPTION_RESET_MENU = "resetmenu";
    public static final String OPTION_GET_SMS_BALANCE = "getbalance";
    public static final String OPTION_GET_AVAILABLE_YEARS = "availableyears";

    public static final String OPTION_SELECT_PARENT_BY_CODE = "selectparentbycode";
    public static final String OPTION_SELECT_CHILD_BY_CODE = "selectchildbycode";
    public static final String OPTION_GET_NEW_CODE = "getnewcode";
    public static final String OPTION_SELECT_POLICIES = "selectpolicies";

    public static final String OPTION_PUSH_BUDGET = "push_budget";

    public static final String OPTION_SYNC_APPROVED_BUDGET = "syncapprovedbudget";
    public static final String OPTION_APPROVE_BUDGET = "approvebudget";
    public static final String OPTION_UNLOCK_BUDGET = "unlockbudget";

    public static final String OPTION_SELECT_BUDGET_VERSION = "selectbudgetversion";
    public static final String OPTION_SELECT_SAP_BUDGET_TYPE = "selectsapbudgettype";
    public static final String OPTION_SELECT_BUDGET_TYPE = "selectbudgettype";
    public static final String OPTION_SELECT_BUDGET_CURRENCY = "selectbudgetcurrency";
    public static final String OPTION_CHANGE_BUDGET_VERSION = "changebudgetversion";
    
    public static final int ESTIMATES_SUBMISSION_REQUEST_TYPE_ID = 1;

    public static final String SERVER_URL = "/BudgetPlanningPortals"; //osg_budget_app
    public static final String SERVER_NAME = "localhost";
    public static final String SERVER_PORT = "8080";
//    public static final String SERVER_NAME = "10.50.2.16";
//    public static final String SERVER_PORT = "9090";
//    public static final String SERVER_NAME = "18.195.167.1";
//    public static final String SERVER_PORT = "8080";
    public static final String SERVER_PROTOCOL = "http://";
    public static final String SITE_URL = "/BudgetPlanningPortals";
    public static final int FORECAST_ESTIMATES_REQUEST_TYPE = 1;
    public static final int OPEN_FOR_APPROVAL_STATUS = 0;
    public static final int APPROVAL_STATUS = 1;
    public static final int REJECTION_STATUS = 2;
    public static final int OPEN_BUDGET_VERSION = 1;
    public static final int LOCKED_BUDGET_VERSION = 2;
    public static final int UNLOCKED_BUDGET_VERSION = 3;
    public static final int APPROVED_BUDGET_VERSION = 4;
    public static final int REJECTED_BUDGET_VERSION = 5;
    public static final int SUBMISSION_NOTIFICATION_TYPE = 1;
    public static final int APPROVAL_NOTIFICATION_TYPE = 2;
    public static final int REJECTION_NOTIFICATION_TYPE = 3;
    public static final int FULL_APPROVAL_NOTIFICATION_TYPE = 4;
    public static final String MYBF_REQUEST_ID = "1";

    public static final int MYBF_REQUEST_TYPE = 1;
    public static final int MTSS_REQUEST_TYPE = 2;
    public static final int MTEF_REQUEST_TYPE = 3;

    public static final int MEETING_ATTENDANT_ROLE = 2;

    public static final int EMAIL_MESSAGE_TYPE = 1;
    public static final int SMS_MESSAGE_TYPE = 2;

    /**
     * SAP Credentials
     *
     * @author Adewale http://scdsap.serveconsulting.com:8001
     *
     */
//    public final static String SAP_QA_ENDPOINT = "http://169.50.212.105:8003";
//    public final static String SAP_QA_AUTH_USER = "xserviceuser";
//    public final static String SAP_QA_AUTH_PASSWORD = "Admin@1234";
//    public final static String SAP_QA_ENDPOINT = "http://scdsap.serveconsulting.com:8001";
    public final static String SAP_QA_ENDPOINT = "http://41.76.158.37:8000"; //"http://os3prd.cs.ondostate.gov.ng:8000"; //http://52.23.247.16​:8001
    public final static String SAP_QA_AUTH_USER = "XSERVICEUSER";
    public final static String SAP_QA_AUTH_PASSWORD = "Admin@1234";
    public static final String SAP_QA_FM_AREA = "ODSG";
    public static final String SAP_HOUSE_APPROVED_BUDGET_DOC_TYPE = "BUDG";
    public static final String SAP_SUPPLEMENTARY_BUDGET_DOC_TYPE = "BADJ";
    public static final String SAP_HOUSE_APPROVED_BUDGET_TYPE = "BI";
    public static final String SAP_SUPPLEMENTARY_BUDGET_TYPE = "SB";
    public static final String SAP_CONTINGENCY_BUDGET_TYPE = "CB";
    public static final String SAP_DEFAULT_CURRENCY = "NGN";
    //public static final String ONDOSTATEOFNIGERIA = "ONDO STATE OF NIGERIA";
    public static final String ONDOSTATEOFNIGERIA = "ONDO STATE OF NIGERIA";
            
    public enum ActionResponse {
        OK, INSERTED, DELETED, UPDATED, RECORD_EXISTS, NO_RECORD, SUCCESSFULL, FAILED, BLOCKED, FULLY_APPROVED
    }

    public String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String reverseString(String str) {
        StringBuilder builder = new StringBuilder();
        for (int count = str.length() - 1; count >= 0; count--) {
            builder.append(str.charAt(count));
        }
        return builder.toString();
    }
    
    public static String RecordStatus() throws JSONException {
        String recordStatusList = null;
        String[] statusList = {"ACTIVE", "NEW", "APPROVED", "REJECTED", "DELETED", "IN_PROCESS", "LOCKED", "COMPLETED", "LOGIN", "LOGOUT"};
        Gson gson = new Gson();
        recordStatusList = gson.toJson(statusList);
        return recordStatusList;
    }


    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    public void setMailServerProperties() {
        //String emailPort = "587";//gmail's smtp port

        emailProperties = System.getProperties();
        //emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

    }

    public void createEmailMessageWithAttachment(String filename, String emailSubject, String emailBody, String emailrecipients) throws AddressException,
            MessagingException {

        String[] toEmails = emailrecipients.replaceAll(";", ",").split(","); //{"adewale_azeez@hotmail.com", "adewaleazeez@yahoo.co.uk", "adewaleazeez@gmail.com"};

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailBody, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        String[] attachFiles = {filename};
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        // sets the multi-part as e-mail's content
        emailMessage.setContent(multipart);

    }

    public void createEmailMessage(String emailSubject, String emailBody, String emailrecipients) throws AddressException,
            MessagingException {
        String[] toEmails = emailrecipients.split(",");

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        for (String toEmail : toEmails) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailBody, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        /*String[] attachFiles = {filename};
         if (attachFiles != null && attachFiles.length > 0) {
         for (String filePath : attachFiles) {
         MimeBodyPart attachPart = new MimeBodyPart();

         try {
         attachPart.attachFile(filePath);
         } catch (IOException ex) {
         ex.printStackTrace();
         }

         multipart.addBodyPart(attachPart);
         }
         }*/
        // sets the multi-part as e-mail's content
        emailMessage.setContent(multipart);

    }

    public String sendEmail() throws AddressException, MessagingException, IOException {
        InputStream inputStream = null;
        String emailhost = null;
        String emailport = null;
        String emailuser = null;
        String emailpassword = null;

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            emailhost = prop.getProperty("emailhost");
            emailport = prop.getProperty("emailport");
            emailuser = prop.getProperty("emailuser");
            emailpassword = prop.getProperty("emailpassword");
        } catch (Exception e) {
            System.out.println("1. Exception: " + e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        String emailHost = emailhost;
        String fromUser = emailuser;
        String fromUserEmailPassword = emailpassword;

        String returnString = "";

        try {
            Transport transport = mailSession.getTransport("smtp");

            transport.connect(emailHost, Integer.parseInt(emailport), fromUser, fromUserEmailPassword);

            if (transport.isConnected()) {
                transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
                transport.close();
                System.out.println("Email sent successfully.");
                returnString = "successfull";
            } else {
                returnString = "failed";
            }
        } catch (Exception e) {
            returnString = e.getMessage();
            System.out.println("2. Exception: " + e);
        }

        return returnString;
    }

    @SuppressWarnings("deprecation")
    public static synchronized Date dbDate(String dateCreated) {
        Date date = new Date();
        String hr = date.getHours() + "";
        String mn = date.getMinutes() + "";
        String sc = date.getSeconds() + "";
        if (dateCreated.length() < 19) {
            dateCreated += " " + (Integer.parseInt(hr) < 10 ? "0" : "") + hr + ":" + (Integer.parseInt(mn) < 10 ? "0" : "") + mn + ":" + (Integer.parseInt(sc) < 10 ? "0" : "") + sc;
            dateCreated = dateCreated.substring(0, 19);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_dateCreated = null;

        try {
            date_dateCreated = formatter.parse(dateCreated);
        } catch (ParseException e) {
        }

        return date_dateCreated;
    }

    public static synchronized Date dbDate2(String dateCreated) {
        String[] dateSplitted = dateCreated.split("/");
        String[] dateSplitted2 = dateSplitted[2].split(" ");
        String[] dateSplitted3 = dateSplitted2[1].split(":");

        if (dateSplitted2[2].equals("PM")) {
            dateSplitted3[0] = String.valueOf(Integer.valueOf(dateSplitted3[0]) + 12);
        }
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(dateSplitted2[0]).append("-").append(dateSplitted[1]).append("-").append(dateSplitted[0]).append(" ")
                .append(dateSplitted3[0]).append(":").append(dateSplitted3[1]).append(":").append(dateSplitted3[2]);

        dateCreated = strBuilder.toString();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date_dateCreated = null;

        try {
            date_dateCreated = formatter.parse(dateCreated);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date_dateCreated;
    }

    /**
     * Function to convert date to SAP date format - e.g. 20171102
     *
     * @param dateToConvert
     * @return
     */
    public static synchronized String convertToSAPDate(Date dateToConvert) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateCreated = "";

        dateCreated = formatter.format(dateToConvert);

        return dateCreated;
    }

    /**
     * Function to convert date to SAP date format - e.g. 20171102
     *
     * @param dateToConvert
     * @return
     */
    public static synchronized String convertToDBDate(Date dateToConvert) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateCreated = "";

        dateCreated = formatter.format(dateToConvert);

        return dateCreated;
    }

    /**
     * Get current date
     *
     * @return
     */
    public static synchronized Date dbDateNow() {
        Date dateNow = Calendar.getInstance(TimeZone.getTimeZone("Africa/Lagos")).getTime();

        return dateNow;
    }

    public static synchronized String getFrameworkMethods() {
        return FrameworkMethodsHibernateHelper.fetchAll2();
    }

    public static boolean sendSMS(String to, String message) {
        boolean isSuccessful = false;

        HttpURLConnection urlConnection = null;
        URL url;
        DataInputStream in = null;
        BufferedReader reader = null;

        try {
            String main_url = "http://smsc.xwireless.net/API/WebSMS/Http/v1.0a/index.php?"
                    + "username=info@serveconsulting.com&password=Sasegbon14A&"
                    + "sender=ODSG+SIFMIS&to=" + to + "&message=" + URLEncoder.encode(message, "UTF-8") + "&reqid=1&"
                    + "format=text&route_id=29"; //3

            url = new URL(main_url);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int respCode = urlConnection.getResponseCode();

            if (respCode == 200 || respCode == 201) {
                in = new DataInputStream(urlConnection.getInputStream());

                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);

                String tokenVariables = URLDecoder.decode(reader.readLine(), "UTF-8");

                in.close();
                reader.close();

                System.out.println(tokenVariables);

                isSuccessful = true;
            } else {
                System.out.println(respCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
            }
        }

        return isSuccessful;
    }

    public static boolean sendSMS(String[] toAll, String message) {
        boolean isSuccessful = false;

        HttpURLConnection urlConnection = null;
        URL url;
        DataInputStream in = null;
        BufferedReader reader = null;

        try {
            for (int i = 0; i < toAll.length; i++) {
                String main_url = "http://smsc.xwireless.net/API/WebSMS/Http/v1.0a/index.php?"
                        + "username=info@serveconsulting.com&password=Sasegbon14A&"
                        + "sender=ODSG+SIFMIS&to=" + toAll[i] + "&message=" + URLEncoder.encode(message, "UTF-8") + "&reqid=1&"
                        + "format=text&route_id=3";

                url = new URL(main_url);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int respCode = urlConnection.getResponseCode();

                if (respCode == 200 || respCode == 201) {
                    in = new DataInputStream(urlConnection.getInputStream());

                    reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);

                    String tokenVariables = URLDecoder.decode(reader.readLine(), "UTF-8");

                    in.close();
                    reader.close();

                    System.out.println(tokenVariables);

                    isSuccessful = true;
                } else {
                    System.out.println(respCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
            }
        }

        return isSuccessful;
    }
    
    public static int getMaxserialNo(String tablename) {
        List recordlist = null;
        String resp = "";

        final org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "select max(id) as maxserialno from " + tablename;
            SQLQuery q = session.createSQLQuery(sql);
            q.setMaxResults(1);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

            recordlist = q.list();
            HashMap hmap = (HashMap) recordlist.get(0);

            if (hmap.get("maxserialno") == null) {
                resp = "0";
            } else {
                resp = hmap.get("maxserialno").toString();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        int idx = Integer.parseInt(resp);
        return idx;
    }
}
