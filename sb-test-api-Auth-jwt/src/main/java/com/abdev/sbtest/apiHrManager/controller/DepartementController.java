package com.abdev.sbtest.apiHrManager.controller;


import com.abdev.sbtest.apiHrManager.models.Departement;
import com.abdev.sbtest.apiHrManager.repository.DepartementRepository;
import com.abdev.sbtest.apiHrManager.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/ApiDep")
@CrossOrigin(origins = "*")
public class DepartementController {
    @Autowired
    private DepartementRepository departementRepository;

    private ApiService  apiService = new ApiService();

    @GetMapping("/departement/{no}")
    public Departement getDepartementNo(@PathVariable("no") String no) {
        return departementRepository.findByNo(no);
    }

    @GetMapping("/departement/{name}")
    public Departement getDepartementName(@PathVariable("name") String name) {
        return departementRepository.findByName(name);
    }

    @PostMapping("/departement")
    public Departement  saveDepartement(@RequestBody Departement    departement){
        return departementRepository.save(departement);
    }

    @DeleteMapping("/departement/{id}")
    public ResponseEntity<?> deleteDepartement(@PathVariable(value = "id")long  id){
        return ResponseEntity.ok(departementRepository.deleteById(id));
    }

    @PutMapping("/departement/{id}")
    public ResponseEntity<?> putDepartement(@PathVariable(value = "id")long  id,@RequestBody Departement    departement){
        if (departementRepository.findById(id) != null){
            departement.setId(id);
            return ResponseEntity.ok(departementRepository.save(departement));
        }
        else {
            return ResponseEntity.ok("La Modification est impossible");
        }
    }

    @GetMapping("/rapport-employee")
    public void exportCSV(HttpServletResponse response) throws Exception {
        apiService.exportRapportCSV(response);

    }
}
