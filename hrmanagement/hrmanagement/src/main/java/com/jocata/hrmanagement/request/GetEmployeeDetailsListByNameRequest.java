package com.jocata.hrmanagement.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties
public class GetEmployeeDetailsListByNameRequest implements Serializable {

    @NotNull(message = "Name cannot be null")
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
