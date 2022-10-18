package com.jocata.hrmanagement.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonIgnoreProperties
public class SaveEmployeedetailsRequest implements Serializable {


    @NotNull(message = "Employee Name Cannot Be Null")
    private String empName;

    @NotNull(message = "Experience cannot be null")
    private String experience;

    @NotNull(message = "CurrentAddress cannot be null")
    private String currentAddress;

    @NotNull(message = "Permanent Address cannot be null")
    private String permanentAddress;

    @NotNull(message = "EmailId cannot be null")
    private String emailId;

    @NotNull(message = "Company Mail cannot be null")
    private String companyMailId;

    @NotNull(message = "Mobile cannot be null")
    private Long mobileNo;

    @NotNull(message = "Reporting Manager cannot be null")
    private Integer reportingManager;

    @NotNull(message = "Designation cannot be null")
    private Integer designation;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCompanyMailId() {
        return companyMailId;
    }

    public void setCompanyMailId(String companyMailId) {
        this.companyMailId = companyMailId;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(Integer reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Integer getDesignation() {
        return designation;
    }

    public void setDesignation(Integer designation) {
        this.designation = designation;
    }
}
