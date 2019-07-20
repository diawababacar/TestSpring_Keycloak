package com.abdev.sbtest.apiHrManager.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartementRegister {
    private String no;
    private String  name;
    private String dateCreation;
    private List<EmployeRegister> employeRegisters;
}
