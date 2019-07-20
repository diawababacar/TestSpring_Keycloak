package com.abdev.sbtest.apiHrManager.controller;

import com.abdev.sbtest.apiHrManager.models.Employee;
import com.abdev.sbtest.apiHrManager.models.User;
import com.abdev.sbtest.apiHrManager.repository.EmployeeRepository;
import com.abdev.sbtest.apiHrManager.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ApiEmp")
@Api(value = "API REST Employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository  userRepository;


    @GetMapping("/employees")
    @ApiOperation(value = "Retourne La liste des employees")
    public List<User> listEmployees(){
        return userRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    @ApiOperation(value = "Retourne un Employé")
    public ResponseEntity<?> getEmployeeId(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping("/employee")
    @ApiOperation(value = "Enregistrer un employé")
    public Employee  saveProduct(@RequestBody Employee    employee){
        return employeeRepository.save(employee);
    }


    @DeleteMapping("/employee/{id}")
    @ApiOperation(value = "Supprimer un employee en Spécifiant L'id uniquement")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id")long  id){
        return ResponseEntity.ok(userRepository.deleteById(id));
    }

    @PutMapping("/employee")
    @ApiOperation(value = "Modifier un employee en Spécifiant L'id et Les Nouvelles Valeurs")
    public Employee putEmployee(@RequestBody Employee    employee){
        return employeeRepository.save(employee);
    }

}
