package com.abdev.sbtest.apiHrManager.controller;

import com.abdev.sbtest.apiHrManager.models.Departement;
import com.abdev.sbtest.apiHrManager.models.Employee;
import com.abdev.sbtest.apiHrManager.models.RoleName;
import com.abdev.sbtest.apiHrManager.models.User;
import com.abdev.sbtest.apiHrManager.repository.DepartementRepository;
import com.abdev.sbtest.apiHrManager.repository.EmployeeRepository;
import com.abdev.sbtest.apiHrManager.services.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/ApiManager")
@Api(value = "API REST Hr Manager")
@CrossOrigin(origins = "*")
public class ManagerController {

    @Autowired
    private ApiService  apiService = new ApiService();

    @Autowired
    private DepartementRepository   departementRepository;

    @Autowired
    private EmployeeRepository  employeeRepository;

    @GetMapping("/employee/{id}")
    @ApiOperation(value = "Retourne un Employé")
    public User getEmployeeId(@PathVariable(value = "id") long id){
        return apiService.getUserId(id, RoleName.ROLE_EMPLOYEE);
    }


    @GetMapping("/employees/")
    @ApiOperation(value = "Retourne une Liste d'employer")
    public List<User> getEmployeeList(){
        return apiService.userList(RoleName.ROLE_EMPLOYEE);
    }

    @GetMapping("/chefDepartement/{id}")
    @ApiOperation(value = "Retourne un chef de departement")
    public User getChefDepId(@PathVariable(value = "id") long id){
        return apiService.getUserId(id, RoleName.ROLE_CHEF_DEPARTEMENT);
    }

    @GetMapping("/chefDepartements/")
    @ApiOperation(value = "Retourne la Liste des chefs de departement")
    public List<User> getChefDepartementList(){
        return apiService.userList(RoleName.ROLE_EMPLOYEE);
    }

    @GetMapping("/departements/")
    @ApiOperation(value = "Retourne la Liste de tous les  departements")
    public List<Departement> getDepartementList(){
        return departementRepository.findAll();
    }

    @GetMapping("/departement/{id}")
    @ApiOperation(value = "Retourne un  departements")
    public Departement getDepartement(@PathVariable(value = "id")long  id){
        return departementRepository.findById(id);
    }

    @GetMapping("/departement/{no}")
    @ApiOperation(value = "Retourne un departement par spécification du Numero de Departement")
    public Departement getDepartementNo(@PathVariable("no") String no) {
        return departementRepository.findByNo(no);
    }

    @GetMapping("/departement/{name}")
    @ApiOperation(value = "Retourne un departement par spécification du nom de Departement")
    public Departement getDepartementName(@PathVariable("name") String name) {
        return departementRepository.findByName(name);
    }


    @PostMapping("/createDepartement")
    @ApiOperation(value = "Création d'un departement")
    public Departement createDep(@Valid @RequestBody Departement    departement){
        return departementRepository.save(departement);
    }

    @PostMapping("/createListDepartement")
    @ApiOperation(value = "Création d'une liste de Departement")
    public List<Departement> createListDep(@Valid @RequestBody List<Departement>  departements){
        List<Departement>   departements1   =   new ArrayList<>();
        for (Departement    departement :   departements){
            departementRepository.save(departement);
            departements1.add(departement);
        }
        return departements1;
    }

    @PutMapping("/grantEmployee/{id}")
    @ApiOperation(value = "Promouvoir un employée en tant que chef de Département en spécifiant l'id uniquement")
    public Employee grantEmployee(@PathVariable(value = "id")long  id){
        Employee    employee    =   employeeRepository.findById(id);
        employee.setRoles(Collections.singleton(apiService.createRole(RoleName.ROLE_CHEF_DEPARTEMENT)));
        return employeeRepository.save(employee);
    }

    @PostMapping("/createEmployee")
    @ApiOperation(value = "Création d'un employé pour HR Manageur")
    public Employee Emp(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/deleteDepartement/{id}")
    @ApiOperation(value = "Suppression d'un departement en Spécifiant L'id uniquement")
    public ResponseEntity<?> deleteDepartementList(@PathVariable(value = "id")long  id){
        return ResponseEntity.ok(departementRepository.deleteById(id));
    }

    @DeleteMapping("/deleteDepartementList")
    @ApiOperation(value = "Suppression d'un departement en Spécifiant L'id uniquement")
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
