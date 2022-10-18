package com.jocata.hrmanagement.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonIgnoreProperties
public class GetEmployeedetailsRequest implements Serializable {

    @NotNull(message = "EmployeeId cannot be null")
    private Integer empId;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}
