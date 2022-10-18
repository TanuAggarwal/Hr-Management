package com.jocata.hrmanagement.service;

import com.jocata.hrmanagement.request.PageRequest;
import com.jocata.hrmanagement.response.EmployeeDetailsResponse;

public interface PageService {

    EmployeeDetailsResponse findPaginated(PageRequest request);


}
