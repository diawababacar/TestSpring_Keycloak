package com.abdev.sbtest.apiHrManager.controller;

import com.abdev.sbtest.apiHrManager.models.Departement;
import com.abdev.sbtest.apiHrManager.models.Employee;
import com.abdev.sbtest.apiHrManager.models.RoleName;
import com.abdev.sbtest.apiHrManager.models.User;
import com.abdev.sbtest.apiHrManager.repository.DepartementRepository;
import com.abdev.sbtest.apiHrManager.repository.EmployeeRepository;
import com.abdev.sbtest.apiHrManager.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/ApiManager")
@CrossOrigin(origins = "*")
public class ManagerController {

    @Autowired
    private ApiService  apiService = new ApiService();

    @Autowired
    private DepartementRepository   departementRepository;

    @Autowired
    private EmployeeRepository  employeeRepository;

    @GetMapping("/employee/{id}")
    public User getEmployeeId(@PathVariable(value = "id") long id){
        return apiService.getUserId(id, RoleName.ROLE_EMPLOYEE);
    }


    @GetMapping("/employees/")
    public List<User> getEmployeeList(){
        return apiService.userList(RoleName.ROLE_EMPLOYEE);
    }

    @GetMapping("/chefDepartement/{id}")
    public User getChefDepId(@PathVariable(value = "id") long id){
        return apiService.getUserId(id, RoleName.ROLE_CHEF_DEPARTEMENT);
    }

    @GetMapping("/chefDepartements/")
    public List<User> getChefDepartementList(){
        return apiService.userList(RoleName.ROLE_EMPLOYEE);
    }

    @GetMapping("/departements/")
    public List<Departement> getDepartementList(){
        return departementRepository.findAll();
    }

    @GetMapping("/departement/{id}")
    public Departement getDepartement(@PathVariable(value = "id")long  id){
        return departementRepository.findById(id);
    }

    @GetMapping("/departement/{no}")
    public Departement getDepartementNo(@PathVariable("no") String no) {
        return departementRepository.findByNo(no);
    }

    @GetMapping("/departement/{name}")
    public Departement getDepartementName(@PathVariable("name") String name) {
        return departementRepository.findByName(name);
    }


    @PostMapping("/createDepartement")
    public Departement createDep(@Valid @RequestBody Departement    departement){
        return departementRepository.save(departement);
    }

    @PostMapping("/createListDepartement")
    public List<Departement> createListDep(@Valid @RequestBody List<Departement>  departements){
        List<Departement>   departements1   =   new ArrayList<>();
        for (Departement    departement :   departements){
            departementRepository.save(departement);
            departements1.add(departement);
        }
        return departements1;
    }

    @PutMapping("/grantEmployee/{id}")
    public Employee grantEmployee(@PathVariable(value = "id")long  id){
        Employee    employee    =   employeeRepository.findById(id);
        employee.setRoles(Collections.singleton(apiService.createRole(RoleName.ROLE_CHEF_DEPARTEMENT)));
        return employeeRepository.save(employee);
    }

    @PostMapping("/createEmployee")
    public Employee Emp(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/deleteDepartement/{id}")
    public ResponseEntity<?> deleteDepartementList(@PathVariable(value = "id")long  id){
        return ResponseEntity.ok(departementRepository.deleteById(id));
    }

    @DeleteMapping("/deleteDepartementList")
    public ResponseEntity<?> deleteDepartement(@Valid @RequestBody List<Departement>  departements){
        List<Departement>   departements1   =   new ArrayList<>();
        for (Departement    departement :   departements){
            long    id = departement.getId();
            departementRepository.deleteById(id);
            departements1.add(departement);
        }
        return ResponseEntity.ok(departements1);
    }

}
