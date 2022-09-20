package com;

import java.util.Date;

public class LicenseModel {

    private long Oscs3pKBGavLicenseid;

    private String gavid;

    private String licenseType;

    private Integer maven;

    private Integer scanCode;

    private Integer fossology;

    private Integer git;

    private Integer go;

    private Integer python;

    private Integer isValid;

    private Integer attributionHelper;

    private Integer licenseCheck;

    private Date createDate;
    private Date modifiedDate;
    private Integer manual;

    public Integer getManual() {
        return manual;
    }

    public void setManual(Integer manual) {
        this.manual = manual;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getOscs3pKBGavLicenseid() {
        return Oscs3pKBGavLicenseid;
    }

    public void setOscs3pKBGavLicenseid(long oscs3pKBGavLicenseid) {
        Oscs3pKBGavLicenseid = oscs3pKBGavLicenseid;
    }

    public Integer getGo() {
        return go;
    }

    public void setGo(Integer go) {
        this.go = go;
    }

    public Integer getLicenseCheck() {
        return licenseCheck;
    }

    public void setLicenseCheck(Integer licenseCheck) {
        this.licenseCheck = licenseCheck;
    }

    public Integer getAttributionHelper() {
        return attributionHelper;
    }

    public void setAttributionHelper(Integer attributionHelper) {
        this.attributionHelper = attributionHelper;
    }

    public Integer getPython() {
        return python;
    }

    public void setPython(Integer python) {
        this.python = python;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getGavid() {
        return gavid;
    }

    public void setGavid(String gavid) {
        this.gavid = gavid;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Integer getMaven() {
        return maven;
    }

    public void setMaven(Integer maven) {
        this.maven = maven;
    }

    public Integer getScanCode() {
        return scanCode;
    }

    public void setScanCode(Integer scanCode) {
        this.scanCode = scanCode;
    }

    public Integer getFossology() {
        return fossology;
    }

    public void setFossology(Integer fossology) {
        this.fossology = fossology;
    }

    public Integer getGit() {
        return git;
    }

    public void setGit(Integer git) {
        this.git = git;
    }

}

