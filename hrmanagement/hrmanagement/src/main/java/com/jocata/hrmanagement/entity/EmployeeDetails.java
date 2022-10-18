package com.jocata.hrmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employeedetails", schema = "hrm")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @NotNull(message = "Employee name cannot be null")
    @Column(name = "emp_name", nullable = false, length = 25)
    private String empName;
    @Column(name = "experience", nullable = false, length = 2)
    private String experience;
    @Column(name = "current_address", nullable = false, length = 500)
    private String currentAddress;
    @NotNull(message = "Address cannot be null")
    @Column(name = "permanent_address", nullable = false, length = 500)
    private String permanentAddress;


    @Email(message = "Enter valid email")
    @Column(name = "email_id", nullable = false, length = 75)
    private String emailId;
    @Column(name = "company_mail_id", nullable = false, length = 75)
    private String companyMailId;


    @Column(name = "mobile_no", nullable = false, length = 10)
    private Long mobileNo;

    @ManyToOne
    @JoinColumn(name = "reporting_manager", nullable = true)
    private EmployeeDetails reportingManager;

    @ManyToOne
    @JoinColumn(name = "designation", nullable = false)
    private Values designation;

    public EmployeeDetails getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(EmployeeDetails reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Values getDesignation() {
        return designation;
    }

    public void setDesignation(Values designation) {
        this.designation = designation;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

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
}

