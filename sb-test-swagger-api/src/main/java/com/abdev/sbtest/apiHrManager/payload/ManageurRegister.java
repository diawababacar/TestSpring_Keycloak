package com.abdev.sbtest.apiHrManager.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageurRegister {
    private String username;
    private String  first_name;
    private String  last_name;
    private String birth_date ;
    private String gender;
    private String email;
    private String password;
    private String  ref_hr_manager;
    private String hire_date;
}
