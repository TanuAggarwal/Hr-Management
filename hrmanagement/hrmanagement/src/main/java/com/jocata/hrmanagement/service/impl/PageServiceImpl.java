package com.jocata.hrmanagement.service.impl;

import com.jocata.hrmanagement.dao.EmployeeDetailsDao;
import com.jocata.hrmanagement.dao.PageDao;
import com.jocata.hrmanagement.entity.EmployeeDetails;
import com.jocata.hrmanagement.response.*;
import com.jocata.hrmanagement.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;

    @Autowired
    private EmployeeDetailsDao employeeDetailsDao;


    @Override
    public EmployeeDetailsResponse findPaginated(com.jocata.hrmanagement.request.PageRequest request) {
        int pazeSize = 10;
        System.out.println("hello from get list employee");
        List<EmployeeDetails> employeeList = employeeDetailsDao.getListEmployee();
        int totalPages = employeeList.size() / pazeSize;

        System.out.println("hello from page");

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(request.getPageNo());
        pageInfo.setTotalPages(totalPages);
        pageInfo.setCount(employeeList.size());

        EmployeeDetailsResponse response = new EmployeeDetailsResponse();

        response.setPageInfo(pageInfo);
        System.out.println("hello from page response");

        Page<EmployeeDetails> pagedResult = null;

        if(request.getPageNo()!=null && request.getSortBy() == null)
        {
            Pageable paging = PageRequest.of(request.getPageNo(), pazeSize);
             pagedResult = pageDao.findAll(paging);
        }
        else if(request.getPageNo()!=null && request.getSortBy() != null)
        {
            Pageable sortedByName = PageRequest.of(request.getPageNo(), pazeSize, Sort.by(request.getSortBy()));
            pagedResult = pageDao.findAll(sortedByName);
        }

        List<EmployeeDetails> list = pagedResult.toList();


        SaveEmployeeDetailsResponse result = new SaveEmployeeDetailsResponse();
        if (request.getPageNo() > totalPages) {

            result.setResult("Data Not Found");
            result.setStatus("Failed");
            response.setResult(result);
        } else {
            result.setStatus("Success");
            Integer s = 10 * request.getPageNo() + 1;
            Integer count = employeeList.size();

            result.setResult(s + "/" + count);
            response.setResult(result);
        }

        List<EmployeePaging> list1 = new ArrayList<>();
        System.out.println(totalPages);

        for (int i = 0; i < list.size(); i++) {
            EmployeePaging employeePaging = new EmployeePaging();

            employeePaging.setName(list.get(i).getEmpName());
            employeePaging.setEmpId(list.get(i).getEmpId());
            employeePaging.setDesignation(list.get(i).getDesignation().getName());

            ReportingManagerResponse manager = new ReportingManagerResponse();

            if (list.get(i).getReportingManager() != null) {
                manager.setName(list.get(i).getReportingManager().getEmpName());
                manager.setId(list.get(i).getReportingManager().getEmpId());
            } else {
                manager.setName("Reporting To Self");
                manager.setId(list.get(i).getEmpId());
            }
            employeePaging.setReportingManager(manager);


            list1.add(employeePaging);
        }

        response.setEmployeePaging(list1);

        return response;
    }
}
