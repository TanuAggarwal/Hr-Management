package com.jocata.hrmanagement.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties
public class EmployeePaging implements Serializable {

    private Integer empId;
    private String name;
    private String designation;


    private ReportingManagerResponse reportingManager;


    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public ReportingManagerResponse getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(ReportingManagerResponse reportingManager) {
        this.reportingManager = reportingManager;
    }
}
