package com.jocata.hrmanagement.dao;

import com.jocata.hrmanagement.entity.EmployeeDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;


public interface PageDao extends PagingAndSortingRepository<EmployeeDetails, Integer> {
}
