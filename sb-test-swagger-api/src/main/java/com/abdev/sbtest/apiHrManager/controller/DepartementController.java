package com.abdev.sbtest.apiHrManager.controller;


import com.abdev.sbtest.apiHrManager.models.Departement;
import com.abdev.sbtest.apiHrManager.repository.DepartementRepository;
import com.abdev.sbtest.apiHrManager.services.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/ApiDep")
@Api(value = "API REST Chef de Departement")
@CrossOrigin(origins = "*")
public class DepartementController {
    @Autowired
    private DepartementRepository departementRepository;

    private ApiService  apiService = new ApiService();

    @GetMapping("/departement/{no}")
    @ApiOperation(value = "Retourne un departement par spécification de Numero du Departement")
    public Departement getDepartementNo(@PathVariable("no") String no) {
        return departementRepository.findByNo(no);
    }

    @GetMapping("/departement/{name}")
    @ApiOperation(value = "Retourne un departement par spécification de Numero du Departement")
    public Departement getDepartementName(@PathVariable("name") String name) {
        return departementRepository.findByName(name);
    }

    @PostMapping("/departement")
    @ApiOperation(value = "Créer un departement")
    public Departement  saveDepartement(@RequestBody Departement    departement){
        return departementRepository.save(departement);
    }

    @DeleteMapping("/departement/{id}")
    @ApiOperation(value = "Supprimer un departement en Spécifiant L'id uniquement")
    public ResponseEntity<?> deleteDepartement(@PathVariable(value = "id")long  id){
        return ResponseEntity.ok(departementRepository.deleteById(id));
    }

    @PutMapping("/departement/{id}")
    @ApiOperation(value = "Modifier un departement en Spécifiant L'id et Les Nouvelles Valeurs")
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
    @ApiOperation(value = "Récupérer le Rapport détaillé des employés pour le jour en cours")
    public void exportCSV(HttpServletResponse response) throws Exception {
        apiService.exportRapportCSV(response);

    }
}
