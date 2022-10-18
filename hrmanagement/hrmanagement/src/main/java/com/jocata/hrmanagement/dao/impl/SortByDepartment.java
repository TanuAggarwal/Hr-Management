package com.jocata.hrmanagement.dao.impl;

import com.jocata.hrmanagement.entity.EmployeeDetails;

import java.util.Comparator;

public class SortByDepartment implements Comparator<EmployeeDetails> {
    @Override
    public int compare(EmployeeDetails o1, EmployeeDetails o2) {

        if(o1.getDesignation().getName().equals(o2.getDesignation().getName()))
        return o1.getEmpId()- o2.getEmpId();

        return o1.getDesignation().getName().compareTo(o2.getDesignation().getName());
    }
}
