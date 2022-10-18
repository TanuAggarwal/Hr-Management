package com.jocata.hrmanagement.dao;

import com.jocata.hrmanagement.entity.EmployeeDetails;
import com.jocata.hrmanagement.entity.EmployeeManagerProjectMapping;
import com.jocata.hrmanagement.entity.ProjectDetails;
import com.jocata.hrmanagement.entity.Values;
import com.jocata.hrmanagement.request.GetEmployeedetailsRequest;
import com.jocata.hrmanagement.request.ManagerIdRequest;

import java.util.List;


public interface EmployeeDetailsDao {
    EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);

    EmployeeDetails getEmployeeDetails(GetEmployeedetailsRequest getEmployeedetailsRequest);

    List<EmployeeDetails> getAllListByName(String name);

    List<Values> getListDesignation();

    List<EmployeeDetails> getListEmployee();

    List<EmployeeManagerProjectMapping> getListProjectMap();

    Values getValuesById(Integer id);

    EmployeeDetails getEmployeeByid(Integer id);

    List<EmployeeDetails> getEmployeeListUnderManager(ManagerIdRequest managerIdRequest);

    List<ProjectDetails> getProjectListUnderManager(ManagerIdRequest managerIdRequest);

    EmployeeManagerProjectMapping saveProjectMapping(EmployeeManagerProjectMapping employeeManagerProjectMapping);

    ProjectDetails getProjectByid(Integer id);

    EmployeeManagerProjectMapping getMappingByEmpId(EmployeeDetails employeeDetails);

    EmployeeManagerProjectMapping updateProjectMapping(EmployeeManagerProjectMapping employeeManagerProjectMapping);

    List<EmployeeDetails> orderbyname();

    List<EmployeeDetails> designationInternSorted();

}
