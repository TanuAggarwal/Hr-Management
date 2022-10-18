package com.jocata.hrmanagement.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonIgnoreProperties
public class ProjectEmployeeMappingRequest implements Serializable {

    @NotNull(message = "EmployeeId cannot be null")
    private Integer empId;

    @NotNull(message = "ProjectId cannot be null")
    private Integer projectId;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
