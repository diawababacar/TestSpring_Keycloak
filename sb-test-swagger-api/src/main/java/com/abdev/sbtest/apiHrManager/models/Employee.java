package com.abdev.sbtest.apiHrManager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User{
    private String  ref_employee;
    private String hire_date;
    @ManyToOne
    private Departement departement;

}
