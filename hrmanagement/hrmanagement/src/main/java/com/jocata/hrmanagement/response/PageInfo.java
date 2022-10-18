package com.jocata.hrmanagement.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import java.io.Serializable;

@JsonIgnoreProperties
public class PageInfo implements Serializable {


    private Integer totalPages;
    private Integer currentPage;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
