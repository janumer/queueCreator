package com.queue.creator.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class EmployeeBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer empId;
    private String empName;
    private String empAddress;
    private String empDoj;

}
