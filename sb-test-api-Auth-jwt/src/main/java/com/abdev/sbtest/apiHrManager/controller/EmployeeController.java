package com.abdev.sbtest.apiHrManager.controller;

import com.abdev.sbtest.apiHrManager.models.Employee;
import com.abdev.sbtest.apiHrManager.models.User;
import com.abdev.sbtest.apiHrManager.repository.EmployeeRepository;
import com.abdev.sbtest.apiHrManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ApiEmp")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository  userRepository;


    @GetMapping("/employees")
    public List<User> listEmployees(){
        return userRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeId(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping("/employee")
    public Employee  saveProduct(@RequestBody Employee    employee){
        return employeeRepository.save(employee);
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id")long  id){
        return ResponseEntity.ok(userRepository.deleteById(id));
    }

    @PutMapping("/employee")
    public Employee putEmployee(@RequestBody Employee    employee){
        return employeeRepository.save(employee);
    }

}
