package com.jocata.hrmanagement.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties
public class DesignationResponse implements Serializable {

    private Map<Integer ,String> designation ;

    public Map<Integer, String> getDesignation() {
        return designation;
    }

    public void setDesignation(Map<Integer, String> designation) {
        this.designation = designation;
    }

//    private List<DesignationList> designationList;
//
//    public List<DesignationList> getDesignationListList() {
//        return designationList;
//    }
//
//    public void setDesignationList(List<DesignationList> designationList) {
//        this.designationList = designationList;
//    }
}
