package com.jocata.hrmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "projectdetails", schema = "hrm")
public class ProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "project_name")
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private EmployeeDetails managerId;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public EmployeeDetails getManagerId() {
        return managerId;
    }

    public void setManagerId(EmployeeDetails managerId) {
        this.managerId = managerId;
    }
}
