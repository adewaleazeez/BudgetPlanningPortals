package com.bpp.hibernate;
// Generated Aug 14, 2018 4:15:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * SapActuals generated by hbm2java
 */
public class SapActuals  implements java.io.Serializable {


     private int id;
     private String recType;
     private String versionName;
     private String valueTyp;
     private String fmDocument;
     private String fiDocument;
     private String fund;
     private String fundsCtr;
     private String cmmtItem;
     private String fundedProg;
     private String busArea;
     private double amountTc;
     private double amountLc;
     private String currency;
     private String glAccount;
     private String period;
     private String funcArea;
     private int fiscYear;
     private Date dateCreated;
     private Integer orgId;

    public SapActuals() {
    }

	
    public SapActuals(int id, String recType, String versionName, String valueTyp, String fiDocument, String fund, String fundsCtr, String cmmtItem, String fundedProg, String busArea, double amountTc, double amountLc, String currency, String glAccount, String period, String funcArea, int fiscYear, Date dateCreated, Integer orgId) {
        this.id = id;
        this.recType = recType;
        this.versionName = versionName;
        this.valueTyp = valueTyp;
        this.fiDocument = fiDocument;
        this.fund = fund;
        this.fundsCtr = fundsCtr;
        this.cmmtItem = cmmtItem;
        this.fundedProg = fundedProg;
        this.busArea = busArea;
        this.amountTc = amountTc;
        this.amountLc = amountLc;
        this.currency = currency;
        this.glAccount = glAccount;
        this.period = period;
        this.funcArea = funcArea;
        this.fiscYear = fiscYear;
        this.dateCreated = dateCreated;
        this.orgId = orgId;
    }
    public SapActuals(int id, String recType, String versionName, String valueTyp, String fmDocument, String fiDocument, String fund, String fundsCtr, String cmmtItem, String fundedProg, String busArea, double amountTc, double amountLc, String currency, String glAccount, String period, String funcArea, int fiscYear, Date dateCreated, Integer orgId) {
       this.id = id;
       this.recType = recType;
       this.versionName = versionName;
       this.valueTyp = valueTyp;
       this.fmDocument = fmDocument;
       this.fiDocument = fiDocument;
       this.fund = fund;
       this.fundsCtr = fundsCtr;
       this.cmmtItem = cmmtItem;
       this.fundedProg = fundedProg;
       this.busArea = busArea;
       this.amountTc = amountTc;
       this.amountLc = amountLc;
       this.currency = currency;
       this.glAccount = glAccount;
       this.period = period;
       this.funcArea = funcArea;
       this.fiscYear = fiscYear;
       this.dateCreated = dateCreated;
       this.orgId = orgId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getRecType() {
        return this.recType;
    }
    
    public void setRecType(String recType) {
        this.recType = recType;
    }
    public String getVersionName() {
        return this.versionName;
    }
    
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
    public String getValueTyp() {
        return this.valueTyp;
    }
    
    public void setValueTyp(String valueTyp) {
        this.valueTyp = valueTyp;
    }
    public String getFmDocument() {
        return this.fmDocument;
    }
    
    public void setFmDocument(String fmDocument) {
        this.fmDocument = fmDocument;
    }
    public String getFiDocument() {
        return this.fiDocument;
    }
    
    public void setFiDocument(String fiDocument) {
        this.fiDocument = fiDocument;
    }
    public String getFund() {
        return this.fund;
    }
    
    public void setFund(String fund) {
        this.fund = fund;
    }
    public String getFundsCtr() {
        return this.fundsCtr;
    }
    
    public void setFundsCtr(String fundsCtr) {
        this.fundsCtr = fundsCtr;
    }
    public String getCmmtItem() {
        return this.cmmtItem;
    }
    
    public void setCmmtItem(String cmmtItem) {
        this.cmmtItem = cmmtItem;
    }
    public String getFundedProg() {
        return this.fundedProg;
    }
    
    public void setFundedProg(String fundedProg) {
        this.fundedProg = fundedProg;
    }
    public String getBusArea() {
        return this.busArea;
    }
    
    public void setBusArea(String busArea) {
        this.busArea = busArea;
    }
    public double getAmountTc() {
        return this.amountTc;
    }
    
    public void setAmountTc(double amountTc) {
        this.amountTc = amountTc;
    }
    public double getAmountLc() {
        return this.amountLc;
    }
    
    public void setAmountLc(double amountLc) {
        this.amountLc = amountLc;
    }
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getGlAccount() {
        return this.glAccount;
    }
    
    public void setGlAccount(String glAccount) {
        this.glAccount = glAccount;
    }
    public String getPeriod() {
        return this.period;
    }
    
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getFuncArea() {
        return this.funcArea;
    }
    
    public void setFuncArea(String funcArea) {
        this.funcArea = funcArea;
    }
    public int getFiscYear() {
        return this.fiscYear;
    }
    
    public void setFiscYear(int fiscYear) {
        this.fiscYear = fiscYear;
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

