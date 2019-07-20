package com.abdev.sbtest.apiHrManager.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartementResponse {
    private String no;
    private String  name;
    private String dateCreation;
    private List<EmployeResponse> employeResponses;
}
