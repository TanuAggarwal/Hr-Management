package com.jocata.hrmanagement.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonIgnoreProperties
public class ManagerIdRequest implements Serializable {

    @NotNull(message = "ManagerId cannot be null")
    private Integer managerId;

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }


}
