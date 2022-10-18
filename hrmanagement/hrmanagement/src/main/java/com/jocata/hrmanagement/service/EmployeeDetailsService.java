package com.jocata.hrmanagement.service;

import com.jocata.hrmanagement.entity.EmployeeDetails;
import com.jocata.hrmanagement.exceptions.ValidationExceptions;
import com.jocata.hrmanagement.request.GetEmployeedetailsRequest;
import com.jocata.hrmanagement.request.ManagerIdRequest;
import com.jocata.hrmanagement.request.ProjectEmployeeMappingRequest;
import com.jocata.hrmanagement.request.SaveEmployeedetailsRequest;
import com.jocata.hrmanagement.response.*;

import java.util.List;

public interface EmployeeDetailsService {

    SaveEmployeeDetailsResponse saveEmployeeDetails(SaveEmployeedetailsRequest employeeDetails) throws ValidationExceptions;

    GetEmployeeDetailsResponse getEmployeeDetails(GetEmployeedetailsRequest getEmployeedetailsRequest);

    List<GetEmployeeDetailsListByNameResponse> getEmployeeDetailsByName(String name);

    DesignationResponse getListDesignation();

    List<GetEmployeeDetailsListByNameResponse> getListEmployee();

    List<GetEmployeeDetailsListByNameResponse> getListEmployeeSortByName();

    List<GetEmployeeDetailsListByNameResponse> getListEmployeeSortByDesignation();

    List<GetEmployeeListUnderManagerResponse> getEmployeeListUnderManager(ManagerIdRequest managerIdRequest);

    List<ProjectDetailsResponse> getProjectListUnderManager(ManagerIdRequest managerIdRequest);

    ProjectEmployeeMappingResponse saveProjectMapping(ProjectEmployeeMappingRequest projectEmployeeMappingRequest);

    List<EmployeeDetails> orderbyname();
    List<EmployeeDetails> designationinternSorted();

}
