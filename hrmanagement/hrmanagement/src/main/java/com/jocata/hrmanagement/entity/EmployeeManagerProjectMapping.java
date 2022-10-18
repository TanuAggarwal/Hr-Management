package com.jocata.hrmanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projectmapping", schema = "hrm")
public class EmployeeManagerProjectMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_details_id", nullable = false)
    private ProjectDetails projectDetails;

    @OneToOne
    @JoinColumn(name = "emp_details_id", nullable = false)
    private EmployeeDetails empDetails;


    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeDetails getEmpDetails() {
        return empDetails;
    }

    public void setEmpDetails(EmployeeDetails empDetails) {
        this.empDetails = empDetails;
    }
}
