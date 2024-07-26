package com.queue.creator.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeBean implements Serializable {

    private Integer empId;
    private String empName;
    private String empAddress;
    private String empDoj;

}
