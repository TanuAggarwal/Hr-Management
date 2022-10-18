package com.jocata.hrmanagement.dao.impl;

import com.jocata.hrmanagement.dao.EmployeeDetailsDao;
import com.jocata.hrmanagement.dao.HibernateUtils;
import com.jocata.hrmanagement.entity.EmployeeDetails;
import com.jocata.hrmanagement.entity.EmployeeManagerProjectMapping;
import com.jocata.hrmanagement.entity.ProjectDetails;
import com.jocata.hrmanagement.entity.Values;
import com.jocata.hrmanagement.request.GetEmployeedetailsRequest;
import com.jocata.hrmanagement.request.ManagerIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDetailsDaoImpl implements EmployeeDetailsDao {

    @Autowired
    HibernateUtils hibernateUtils;

    @Override
    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        Integer id = (Integer) hibernateUtils.save((Object) employeeDetails);
        return hibernateUtils.findEntityById(employeeDetails, id);
    }

    @Override
    public EmployeeDetails getEmployeeDetails(GetEmployeedetailsRequest getEmployeedetailsRequest) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        return hibernateUtils.findEntityById(employeeDetails, getEmployeedetailsRequest.getEmpId());
    }

    @Override
    public List<EmployeeDetails> getAllListByName(String name) {
        return hibernateUtils.loadEntityByHql("from EmployeeDetails where empName=" + "'" + name + "'");
    }

    @Override
    public List<Values> getListDesignation() {
        return hibernateUtils.loadEntityByHql("from Values");
    }

    @Override
    public List<EmployeeDetails> getListEmployee() {
        return hibernateUtils.loadEntityByHql("from EmployeeDetails");
    }

    @Override
    public List<EmployeeManagerProjectMapping> getListProjectMap() {
        return hibernateUtils.loadEntityByHql("from EmployeeManagerProjectMapping");
    }

    @Override
    public Values getValuesById(Integer id) {
        Values value = new Values();
        return hibernateUtils.findEntityById(value, id);
    }

    @Override
    public EmployeeDetails getEmployeeByid(Integer id) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        return hibernateUtils.findEntityById(employeeDetails, id);
    }

    @Override
    public List<EmployeeDetails> getEmployeeListUnderManager(ManagerIdRequest getEmployeeListUnderManagerRequest) {
        return hibernateUtils.loadEntityByHql("from EmployeeDetails where reportingManager=" + "'" + getEmployeeListUnderManagerRequest.getManagerId() + "'");
    }

    @Override
    public List<ProjectDetails> getProjectListUnderManager(ManagerIdRequest managerIdRequest) {
        return hibernateUtils.loadEntityByHql("from ProjectDetails where managerId=" + "'" + managerIdRequest.getManagerId() + "'");
    }

    @Override
    public EmployeeManagerProjectMapping saveProjectMapping(EmployeeManagerProjectMapping employeeManagerProjectMapping) {
        Integer id = (Integer) hibernateUtils.save((Object) employeeManagerProjectMapping);
        return hibernateUtils.findEntityById(employeeManagerProjectMapping, id);
    }

    @Override
    public ProjectDetails getProjectByid(Integer id) {
        ProjectDetails projectDetails = new ProjectDetails();
        return hibernateUtils.findEntityById(projectDetails, id);
    }

    @Override
    public EmployeeManagerProjectMapping getMappingByEmpId(EmployeeDetails employeeDetails) {
        return hibernateUtils.getMappingByEmpId(employeeDetails);
    }

    @Override
    public EmployeeManagerProjectMapping updateProjectMapping(EmployeeManagerProjectMapping employeeManagerProjectMapping) {
        hibernateUtils.update(employeeManagerProjectMapping);
        return hibernateUtils.findEntityById(employeeManagerProjectMapping , employeeManagerProjectMapping.getId());
    }

    @Override
    public List<EmployeeDetails> orderbyname() {
        return hibernateUtils.orderbyname();
    }

    @Override
    public List<EmployeeDetails> designationInternSorted() {
        return hibernateUtils.orderByNameAndDesignationMustBeIntern();
    }

}






