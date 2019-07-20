package com.abdev.sbtest.apiHrManager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String no;
    private String  name;
    private String    dateCreation;
    @OneToMany(mappedBy = "departement",fetch = FetchType.LAZY)
    private List<Employee>    employees;
}
