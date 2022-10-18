package com.jocata.hrmanagement.dao.impl;

import com.jocata.hrmanagement.entity.EmployeeDetails;

import java.util.Comparator;

public class SortByName implements Comparator<EmployeeDetails> {
    @Override
    public int compare(EmployeeDetails o1, EmployeeDetails o2) {
        if(o1.getEmpName().equals(o2.getEmpName()))
        return o1.getEmpId()- o2.getEmpId();

        return o1.getEmpName().compareTo(o2.getEmpName());
    }
}
