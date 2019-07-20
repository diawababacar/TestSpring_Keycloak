package com.abdev.sbtest.apiHrManager.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeResponse {
    private String username;
    private String  first_name;
    private String  last_name;
    private String birth_date ;
    private String gender;
    private String email;
    private String password;
    private String  ref_employee;
    private String hire_date;
    private DepartementResponse departementResponse;
}
