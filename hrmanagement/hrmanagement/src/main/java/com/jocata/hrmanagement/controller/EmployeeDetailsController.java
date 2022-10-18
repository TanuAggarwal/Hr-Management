package com.jocata.hrmanagement.controller;

import com.jocata.hrmanagement.entity.EmployeeDetails;
import com.jocata.hrmanagement.exceptions.ErrorDetails;
import com.jocata.hrmanagement.exceptions.ValidationExceptions;
import com.jocata.hrmanagement.request.*;
import com.jocata.hrmanagement.response.*;
import com.jocata.hrmanagement.service.EmployeeDetailsService;
import com.jocata.hrmanagement.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/empdetails")
public class EmployeeDetailsController {

    @Autowired
    EmployeeDetailsService employeeDetailsService;

    @Autowired
    PageService pageService;

    @PostMapping("/saveempdetails")
    public ResponseEntity<?> saveempdetails(@Valid @RequestBody  SaveEmployeedetailsRequest edr){
        SaveEmployeeDetailsResponse response = new SaveEmployeeDetailsResponse();
        try{
         response = employeeDetailsService.saveEmployeeDetails(edr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/getempdetails")
    public ResponseEntity<?> getempdetails(@Valid @RequestBody  GetEmployeedetailsRequest gedr) {
        GetEmployeeDetailsResponse response = employeeDetailsService.getEmployeeDetails(gedr);
        return new ResponseEntity<GetEmployeeDetailsResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/EmployeeDetailsListByName")
    public List<GetEmployeeDetailsListByNameResponse> getListByName(@Valid @RequestBody  GetEmployeeDetailsListByNameRequest request) {
        return employeeDetailsService.getEmployeeDetailsByName(request.getName());
    }

    @GetMapping("/GetDesignationList")
    public DesignationResponse getDesignationList() {
        return employeeDetailsService.getListDesignation();
    }

    @GetMapping("/GetAllEmployeeList")
    public List<GetEmployeeDetailsListByNameResponse> getAllEmpoyeeList() {
        return employeeDetailsService.getListEmployee();
    }

    @PostMapping("/GetEmployeeUnderManager")
    public List<GetEmployeeListUnderManagerResponse> getEmployeesUnderManager(@Valid @RequestBody ManagerIdRequest request) {
        return employeeDetailsService.getEmployeeListUnderManager(request);
    }

    @PostMapping("/GetProjectUnderManager")
    public List<ProjectDetailsResponse> getProjectUnderManager(@Valid  @RequestBody ManagerIdRequest request) {
        return employeeDetailsService.getProjectListUnderManager(request);
    }


    @PostMapping("/saveProjectDetails")
    public ResponseEntity<?> saveProjectDetails(@Valid @RequestBody ProjectEmployeeMappingRequest pemr) {

        ProjectEmployeeMappingResponse response = employeeDetailsService.saveProjectMapping(pemr);
//        employeeDetailsService.saveEmployeeDetails(edr);
        return new ResponseEntity<ProjectEmployeeMappingResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> getPaginated(@Valid @RequestBody PageRequest pageNo) {

        EmployeeDetailsResponse response = pageService.findPaginated(pageNo);

        return new ResponseEntity<EmployeeDetailsResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("/sortByName")
    public List<GetEmployeeDetailsListByNameResponse> getAllEmpoyeeListsortByName() {
        return employeeDetailsService.getListEmployeeSortByName();
    }


    @GetMapping("/sortByDesignation")
    public List<GetEmployeeDetailsListByNameResponse> getAllEmpoyeeListsortByDesignation() {

        return employeeDetailsService.getListEmployeeSortByDesignation();
    }

    @GetMapping("/orderByName")
    public List<EmployeeDetails> orderbyname() {
        return employeeDetailsService.orderbyname();
    }
    @GetMapping("/sortedDesignationIntern")
    public List<EmployeeDetails> Intern() {
        return employeeDetailsService.designationinternSorted();
    }


}
