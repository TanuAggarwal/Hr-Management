package com.jocata.hrmanagement.service.impl;

import com.jocata.hrmanagement.dao.EmployeeDetailsDao;
import com.jocata.hrmanagement.dao.impl.SortByName;
import com.jocata.hrmanagement.dao.impl.SortByDepartment;
import com.jocata.hrmanagement.entity.EmployeeDetails;
import com.jocata.hrmanagement.entity.EmployeeManagerProjectMapping;
import com.jocata.hrmanagement.entity.ProjectDetails;
import com.jocata.hrmanagement.entity.Values;
import com.jocata.hrmanagement.exceptions.ValidationExceptions;
import com.jocata.hrmanagement.request.GetEmployeedetailsRequest;
import com.jocata.hrmanagement.request.ManagerIdRequest;
import com.jocata.hrmanagement.request.ProjectEmployeeMappingRequest;
import com.jocata.hrmanagement.request.SaveEmployeedetailsRequest;
import com.jocata.hrmanagement.response.*;
import com.jocata.hrmanagement.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    @Autowired
    EmployeeDetailsDao employeeDetailsDao;


    @Override
    public SaveEmployeeDetailsResponse saveEmployeeDetails(SaveEmployeedetailsRequest request) throws ValidationExceptions {

        SaveEmployeeDetailsResponse response = new SaveEmployeeDetailsResponse();
        List<EmployeeDetails> list = null;
        int length = String.valueOf(request.getMobileNo()).length();
        System.out.println(length);
        list = employeeDetailsDao.getListEmployee();
        if (list.size() == 0) {
            response.setStatus("Failed");
            response.setResult("No data Found");
            return response;
        }
        Boolean flag = false;
        EmployeeDetails employeeDetails = new EmployeeDetails();
        try {
            System.out.println(request.getEmailId());
            for (int i = 0; i < list.size(); i++) {
                flag = true;
                System.out.println(list.get(i).getEmailId());
                String mailid = list.get(i).getEmailId();
                String companyid = list.get(i).getCompanyMailId();
                Long mobile = list.get(i).getMobileNo();
                if (mailid.equals(request.getEmailId())) {
                    flag = false;
//                    response.setStatus("Failed");
//                    response.setResult("MailId Already Exists");
//                    return response;
                    throw new ValidationExceptions("MailId Already Exists");

                } else if (companyid.equals(request.getCompanyMailId())) {
                    flag = false;
//                    response.setStatus("Failed");
//                    response.setResult("Company MailId Already Exists");
//                    return response;
                    throw new ValidationExceptions("CompanyMail Already Exists");
                } else if (mobile.equals(request.getMobileNo())) {
                    flag = false;
//                    response.setStatus("Failed");
//                    response.setResult("Mobile Number Already Exists");
//                    return response;
                    throw new ValidationExceptions("Mobile Already Exists");

                } else if (length < 10 || length > 10) {

                    flag = false;
//                    response.setStatus("Failed");
//                    response.setResult("Mobile must be 10 Digits");
//                    return response;
                    throw new ValidationExceptions("Phone number must be of 10 digits");

                }
            }
            if (flag) {
                employeeDetails.setCompanyMailId(request.getCompanyMailId());
                employeeDetails.setCurrentAddress(request.getCurrentAddress());
                employeeDetails.setEmailId(request.getEmailId());
                employeeDetails.setMobileNo(request.getMobileNo());
                employeeDetails.setEmpName(request.getEmpName());
                employeeDetails.setExperience(request.getExperience());
                employeeDetails.setPermanentAddress(request.getPermanentAddress());
                Integer designation = request.getDesignation();
                Values designationId = employeeDetailsDao.getValuesById(designation);
                if (designationId != null) {
                    employeeDetails.setDesignation(designationId);
                    response.setStatus("Success");
                } else {
                    response.setStatus("Failed");
                    response.setResult("Designation Not Valid");
                    return response;
                }
                Integer reportingManager = request.getReportingManager();
                EmployeeDetails reportingmanagerid = employeeDetailsDao.getEmployeeByid(reportingManager);
                if (reportingmanagerid != null) {

                    if (reportingmanagerid.getDesignation().getId() > (request.getDesignation() + 2)) {
                        employeeDetails.setReportingManager(reportingmanagerid);

                        response.setStatus("Success");
                    } else {
                        response.setStatus("Failed");
                        response.setResult("Reporting manager Invalid");
                        return response;
                    }
                } else {
                    response.setStatus("Failed");
                    response.setResult(" Manager Invalid");
                    return response;
                }
                employeeDetails = employeeDetailsDao.saveEmployeeDetails(employeeDetails);
                if (response.getStatus().equals("Success")) {
                    response.setResult("Data Saved Success with Employee id " + employeeDetails.getEmpId());
                } else {
                    response.setResult("Data save unsucess");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setResult(e.getMessage());
            response.setStatus("Failed");
//                return response;
        }
        return response;
    }


    @Override
    public GetEmployeeDetailsResponse getEmployeeDetails(GetEmployeedetailsRequest getEmployeedetailsRequest) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        GetEmployeeDetailsResponse getEmployeeDetailsResponse = new GetEmployeeDetailsResponse();
        try {
            employeeDetails = employeeDetailsDao.getEmployeeDetails(getEmployeedetailsRequest);
        } catch (Exception e) {
            System.out.println("No Data Found");
            getEmployeeDetailsResponse.setStatus("Failed");
            getEmployeeDetailsResponse.setResult("No data Found");

            return getEmployeeDetailsResponse;
        }


        if (employeeDetails != null) {

            getEmployeeDetailsResponse.setCompanyMailId(employeeDetails.getCompanyMailId());
            getEmployeeDetailsResponse.setCurrentAddress(employeeDetails.getCurrentAddress());
            getEmployeeDetailsResponse.setDesignation(employeeDetails.getDesignation().getName());
            getEmployeeDetailsResponse.setEmailId(employeeDetails.getEmailId());
            getEmployeeDetailsResponse.setEmpName(employeeDetails.getEmpName());
            getEmployeeDetailsResponse.setExperience(employeeDetails.getExperience());
            getEmployeeDetailsResponse.setMobileNo(employeeDetails.getMobileNo());
            getEmployeeDetailsResponse.setPermanentAddress(employeeDetails.getPermanentAddress());


            if (employeeDetails.getReportingManager() == null) {
                getEmployeeDetailsResponse.setReportingManager("Reporting to self");
            } else
                getEmployeeDetailsResponse.setReportingManager(employeeDetails.getReportingManager().getEmpName());

            if (employeeDetails != null) {
                getEmployeeDetailsResponse.setStatus("Success");
                getEmployeeDetailsResponse.setResult("Data Fetched Success");
            }

        } else if (employeeDetails == null) {

            getEmployeeDetailsResponse.setStatus("Failed");
            getEmployeeDetailsResponse.setResult("No Data Found");
            return getEmployeeDetailsResponse;
        }

        return getEmployeeDetailsResponse;
    }

    @Override
    public List<GetEmployeeDetailsListByNameResponse> getEmployeeDetailsByName(String name) {

//        GetEmployeeDetailsListByNameResponse getEmployeeDetailsListByNameResponse = new GetEmployeeDetailsListByNameResponse();
        List<GetEmployeeDetailsListByNameResponse> list = new ArrayList<>();


        List<EmployeeDetails> list1 = employeeDetailsDao.getAllListByName(name);

        if(list1!=null)
        {
            int size = list1.size();
            int i = 0;
            while (size-- != 0) {
                GetEmployeeDetailsListByNameResponse response = new GetEmployeeDetailsListByNameResponse();
                response.setEmpId(list1.get(i).getEmpId());
                response.setName(list1.get(i).getEmpName());
                response.setDesignation(list1.get(i).getDesignation().getName());

                ReportingManagerResponse reportingManagerResponse = new ReportingManagerResponse();
                reportingManagerResponse.setId(list1.get(i).getReportingManager().getEmpId());
                reportingManagerResponse.setName(list1.get(i).getReportingManager().getEmpName());

                response.setReportingManager(reportingManagerResponse);
                list.add(response);
                i++;
            }
        }
        return list;


    }

    @Override
    public DesignationResponse getListDesignation() {


        List<Values> list = employeeDetailsDao.getListDesignation();

        DesignationResponse response = new DesignationResponse();
//        List<DesignationList> list1 = new ArrayList<>();
        Map<Integer, String>  designation = list.stream().collect(Collectors.toMap(Values::getId, Values::getName));
//        for (int i = 0; i < list.size(); i++) {
//            DesignationList designationList = new DesignationList();
//
//            designationList.setId(list.get(i).getId());
//            designationList.setDesignation(list.get(i).getName());
//
//            list1.add(designationList);
//        }
//        Map<Integer , String> response

        response.setDesignation(designation);
        return response;
    }

    @Override
    public List<GetEmployeeDetailsListByNameResponse> getListEmployee() {


        List<EmployeeDetails> list = employeeDetailsDao.getListEmployee();
        List<GetEmployeeDetailsListByNameResponse> list1 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            GetEmployeeDetailsListByNameResponse response = new GetEmployeeDetailsListByNameResponse();
            response.setEmpId(list.get(i).getEmpId());
            response.setName(list.get(i).getEmpName());
            response.setDesignation(list.get(i).getDesignation().getName());

            ReportingManagerResponse manager = new ReportingManagerResponse();
            if (list.get(i).getReportingManager() != null) {
                manager.setName(list.get(i).getReportingManager().getEmpName());
                manager.setId(list.get(i).getReportingManager().getEmpId());
            } else {
                manager.setName("Reporting To Self");
                manager.setId(list.get(i).getEmpId());
            }


            response.setReportingManager(manager);

            list1.add(response);
        }
        return list1;
    }

    @Override
    public List<GetEmployeeDetailsListByNameResponse> getListEmployeeSortByName() {
        List<EmployeeDetails> list = employeeDetailsDao.getListEmployee();
        List<GetEmployeeDetailsListByNameResponse> list1 = new ArrayList<>();
        Collections.sort(list, new SortByName());
        for (int i = 0; i < list.size(); i++) {
            GetEmployeeDetailsListByNameResponse response = new GetEmployeeDetailsListByNameResponse();
            response.setEmpId(list.get(i).getEmpId());
            response.setName(list.get(i).getEmpName());
            response.setDesignation(list.get(i).getDesignation().getName());

            ReportingManagerResponse manager = new ReportingManagerResponse();
            if (list.get(i).getReportingManager() != null) {
                manager.setName(list.get(i).getReportingManager().getEmpName());
                manager.setId(list.get(i).getReportingManager().getEmpId());
            } else {
                manager.setName("Reporting To Self");
                manager.setId(list.get(i).getEmpId());
            }


            response.setReportingManager(manager);

            list1.add(response);
        }
        return list1;
    }

    @Override
    public List<GetEmployeeDetailsListByNameResponse> getListEmployeeSortByDesignation() {
        List<EmployeeDetails> list = employeeDetailsDao.getListEmployee();
        List<GetEmployeeDetailsListByNameResponse> list1 = new ArrayList<>();
        Collections.sort(list, new SortByDepartment());
        for (int i = 0; i < list.size(); i++) {
            GetEmployeeDetailsListByNameResponse response = new GetEmployeeDetailsListByNameResponse();
            response.setEmpId(list.get(i).getEmpId());
            response.setName(list.get(i).getEmpName());
            response.setDesignation(list.get(i).getDesignation().getName());

            ReportingManagerResponse manager = new ReportingManagerResponse();
            if (list.get(i).getReportingManager() != null) {
                manager.setName(list.get(i).getReportingManager().getEmpName());
                manager.setId(list.get(i).getReportingManager().getEmpId());
            } else {
                manager.setName("Reporting To Self");
                manager.setId(list.get(i).getEmpId());
            }


            response.setReportingManager(manager);

            list1.add(response);
        }
        return list1;
    }


    @Override
    public List<ProjectDetailsResponse> getProjectListUnderManager(ManagerIdRequest managerIdRequest) {

        List<ProjectDetailsResponse> list = new ArrayList<>();
        List<ProjectDetails> list1 = employeeDetailsDao.getProjectListUnderManager(managerIdRequest);
        int size = list1.size();
        int i = 0;
        while (size-- != 0) {
            ProjectDetailsResponse response = new ProjectDetailsResponse();

            response.setProjectId(list1.get(i).getProjectId());
            response.setProjectName(list1.get(i).getProjectName());
            response.setManagerId(list1.get(i).getManagerId().getEmpId());
            response.setManagerName(list1.get(i).getManagerId().getEmpName());

            list.add(response);
            i++;
        }

        return list;


    }

    @Override
    public ProjectEmployeeMappingResponse saveProjectMapping(ProjectEmployeeMappingRequest projectEmployeeMappingRequest) {

        List<EmployeeManagerProjectMapping> list = employeeDetailsDao.getListProjectMap();
        boolean flag = false;
       if(list!=null)
       {

           for(int i=0;i< list.size();i++)
           {
               if(list.get(i).getEmpDetails().getEmpId() == projectEmployeeMappingRequest.getEmpId())
                   flag = true;
               else
                   flag=false;
           }
       }
        EmployeeDetails empDetails = employeeDetailsDao.getEmployeeByid(projectEmployeeMappingRequest.getEmpId());
        ProjectEmployeeMappingResponse response = new ProjectEmployeeMappingResponse();
        EmployeeManagerProjectMapping empMapping = new EmployeeManagerProjectMapping();
        if(flag){
            empMapping = employeeDetailsDao.getMappingByEmpId(empDetails);

            empMapping.setId(empMapping.getId());

            Integer projectId = projectEmployeeMappingRequest.getProjectId();
            ProjectDetails project = null;
            try {
                project = employeeDetailsDao.getProjectByid(projectId);
            } catch (NullPointerException e) {
                e.printStackTrace();
                throw new NullPointerException("No Data Found");
            }
            empMapping.setProjectDetails(project);
            employeeDetailsDao.updateProjectMapping(empMapping);

            try {
                response.setEmpId(empMapping.getEmpDetails().getEmpId());
                response.setEmpName(empMapping.getEmpDetails().getEmpName());
                response.setProjectId(empMapping.getProjectDetails().getProjectId());
                response.setProjectName(empMapping.getProjectDetails().getProjectName());
                response.setMapId(empMapping.getId());
            } catch (NullPointerException e) {
                throw new NullPointerException("Data Not Found");
            }


            response.setStatus("Success");

            response.setMessage("Employee Mapped with Projct success");

            return response;
        }
        else
        {
            Integer empId = projectEmployeeMappingRequest.getEmpId();
            EmployeeDetails employee = null;
            try {
                employee = employeeDetailsDao.getEmployeeByid(empId);
            } catch (NullPointerException e) {
                System.out.println("Data Not Found " + e);
                throw new NullPointerException("Data Not Found ");

            }
            System.out.println(employee.getEmpName());
            empMapping.setEmpDetails(employee);

            Integer projectId = projectEmployeeMappingRequest.getProjectId();
            ProjectDetails project = null;
            try {
                project = employeeDetailsDao.getProjectByid(projectId);
            } catch (NullPointerException e) {
                e.printStackTrace();
                throw new NullPointerException("No Data Found");
            }

            empMapping.setProjectDetails(project);


            try {
                empMapping = employeeDetailsDao.saveProjectMapping(empMapping);
            } catch (Exception e) {
                System.out.println("No Data Found" + e);
                e.printStackTrace();
                return response;
            }

            try {
                response.setMapId(empMapping.getId());
                response.setEmpId(empMapping.getEmpDetails().getEmpId());
                response.setEmpName(empMapping.getEmpDetails().getEmpName());
                response.setProjectId(empMapping.getProjectDetails().getProjectId());
                response.setProjectName(empMapping.getProjectDetails().getProjectName());
            } catch (NullPointerException e) {
                throw new NullPointerException("Data Not Found");
            }


            response.setStatus("Success");

            response.setMessage("Employee Mapped with Projct success");
        }

        return response;









//


//        Integer flag = 1;
//
//
//        EmployeeManagerProjectMapping employeeManagerProjectMapping = new EmployeeManagerProjectMapping();
//        for (int i = 0; i < list.size(); i++) {
//            flag = 1;
//            if (list.get(i).getEmpDetails().getEmpId() != projectEmployeeMappingRequest.getEmpId()) {
//                flag = 0;
//            } else {
//                flag = 1;
//                break;
//            }
//        }
//        ProjectEmployeeMappingResponse response = new ProjectEmployeeMappingResponse();
//        if (flag == 0) {
//
//            Integer empId = projectEmployeeMappingRequest.getEmpId();
//            EmployeeDetails employee = null;
//            try {
//                employee = employeeDetailsDao.getEmployeeByid(empId);
//            } catch (NullPointerException e) {
//                System.out.println("Data Not Found " + e);
//                throw new NullPointerException("Data Not Found ");
//
//            }
//
//            employeeManagerProjectMapping.setEmpDetails(employee);
//
//            Integer projectId = projectEmployeeMappingRequest.getProjectId();
//            ProjectDetails project = null;
//            try {
//                project = employeeDetailsDao.getProjectByid(projectId);
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//                throw new NullPointerException("No Data Found");
//            }
//
//            employeeManagerProjectMapping.setProjectDetails(project);
//
//
//            try {
//                employeeManagerProjectMapping = employeeDetailsDao.saveProjectMapping(employeeManagerProjectMapping);
//            } catch (Exception e) {
//                System.out.println("No Data Found" + e);
//                e.printStackTrace();
//                return response;
//            }
//
//            try {
//                response.setEmpId(employeeManagerProjectMapping.getEmpDetails().getEmpId());
//                response.setEmpName(employeeManagerProjectMapping.getEmpDetails().getEmpName());
//                response.setProjectId(employeeManagerProjectMapping.getProjectDetails().getProjectId());
//                response.setProjectName(employeeManagerProjectMapping.getProjectDetails().getProjectName());
//            } catch (NullPointerException e) {
//                throw new NullPointerException("Data Not Found");
//            }
//
//
//            response.setStatus("Success");
//
//            response.setMessage("Employee Mapped with Projct success");
//        } else {
//            response.setStatus("Failed");
//            response.setMessage("Mapping Already Exists");
//            return response;
//
//        }
//
//        return response;


    }

    @Override
    public List<EmployeeDetails> orderbyname() {
        return employeeDetailsDao.orderbyname();
    }

    @Override
    public List<EmployeeDetails> designationinternSorted() {
        EmployeeDetails emp = new EmployeeDetails();
        System.out.println(emp.getClass().getSimpleName());
        return employeeDetailsDao.designationInternSorted();



    }

    @Override
    public List<GetEmployeeListUnderManagerResponse> getEmployeeListUnderManager(ManagerIdRequest managerIdRequest) {
        List<GetEmployeeListUnderManagerResponse> list = new ArrayList<>();
        List<EmployeeDetails> list1 = employeeDetailsDao.getEmployeeListUnderManager(managerIdRequest);
        int size = list1.size();
        int i = 0;
        while (size-- != 0) {
            GetEmployeeListUnderManagerResponse response = new GetEmployeeListUnderManagerResponse();

            response.setEmpId(list1.get(i).getEmpId());
            response.setName(list1.get(i).getEmpName());
            response.setDesignation(list1.get(i).getDesignation().getName());

            list.add(response);
            i++;
        }

        return list;


    }
}

