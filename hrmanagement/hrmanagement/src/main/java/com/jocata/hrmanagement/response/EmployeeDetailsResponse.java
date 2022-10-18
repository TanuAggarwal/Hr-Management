package com.jocata.hrmanagement.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;


@JsonIgnoreProperties
public class EmployeeDetailsResponse implements Serializable {

    private PageInfo pageInfo;

    private List<EmployeePaging> employeePaging;

    private SaveEmployeeDetailsResponse Result;

    public SaveEmployeeDetailsResponse getResult() {
        return Result;
    }

    public void setResult(SaveEmployeeDetailsResponse result) {
        Result = result;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<EmployeePaging> getEmployeePaging() {
        return employeePaging;
    }

    public void setEmployeePaging(List<EmployeePaging> employeePaging) {
        this.employeePaging = employeePaging;
    }
}
