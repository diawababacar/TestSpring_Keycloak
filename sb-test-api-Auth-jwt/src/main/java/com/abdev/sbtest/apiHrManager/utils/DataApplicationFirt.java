package com.abdev.sbtest.apiHrManager.utils;

import com.abdev.sbtest.apiHrManager.models.RapportEmploye;
import com.abdev.sbtest.apiHrManager.payload.DepartementRegister;
import com.abdev.sbtest.apiHrManager.payload.EmployeRegister;
import com.abdev.sbtest.apiHrManager.payload.ManageurRegister;
import com.abdev.sbtest.apiHrManager.repository.RapportEmployeRepository;
import com.abdev.sbtest.apiHrManager.services.ApiService;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DataApplicationFirt {

    public void saveListRapport(ApiService  apiService,RapportEmployeRepository rapportEmployeRepository) {
        Stream.of("Name Projet 1 ","Name Projet 1 ","Name Projet 2 ","Name Projet 3 ","Name Projet 4 ").forEach(
                name->{
                    rapportEmployeRepository.save(new RapportEmploye(null,name,apiService.getCurrentLocalDateTimeStamp(),apiService.getCurrentTimeUsingCalendar()));
                }
        );
    }

    public void saveListHrManager(ApiService  apiService){
        Stream.of("Administrateur Diaw admin 02/01/1994 Masculin admin@gmail.com admin",
                "Manageur1 boh manageur2 02/01/1994 Masculin manageur294@gmail.com manageur",
                "Manageur2 Ngom manageur3 02/01/1994 Masculin manageur394@gmail.com manageur",
                "Manageur3 Diop manageur4 02/01/1994 feminin manageur494@gmail.com manageur").forEach(emp ->{
            apiService.saveHrManager(new ManageurRegister(emp.split(" ")[2],emp.split(" ")[0],emp.split(" ")[1],emp.split(" ")[3],emp.split(" ")[4],emp.split(" ")[5],emp.split(" ")[6],"REF_MANAGEUR"+ RandomString.make(6).toUpperCase(),apiService.getCurrentDate()));

        });
    }

    public void saveListEmployee(ApiService  apiService){

        Stream.of("Ababacar Diaw ababacar 02/01/1994 Masculin diaw.ababacar94@gmail.com alouchboyz",
                "Djiby boh papedjiby 02/01/1994 Masculin boh.ababacar94@gmail.com bidahking",
                "Mohamed Ngom thefuture 02/01/1994 Masculin ngom.ababacar94@gmail.com ngomb",
                "maimouna Diop maimouna 02/01/1994 feminin diop.ababacar94@gmail.com maimai").forEach(emp ->{
            apiService.saveEmploye(new EmployeRegister(null,emp.split(" ")[2],emp.split(" ")[0],emp.split(" ")[1],emp.split(" ")[3],emp.split(" ")[4],emp.split(" ")[5],emp.split(" ")[6],"REF_EMP"+ RandomString.make(6).toUpperCase(),apiService.getCurrentDate(),null));

        });

    }

    public void saveListDepartement(ApiService  apiService){

        Stream.of("Departement 1",
                "Departement 2",
                "Departement 3",
                "Departement 4").forEach(dep ->{
            apiService.saveDepartementNotEmploye(new DepartementRegister(null,dep,apiService.getCurrentDate(),null));

        });

    }

    public void saveListChefDepartement(ApiService  apiService){
        Stream.of("Ababacar Diaw abdourahmane 02/01/1994 Masculin abdourahmane.ababacar94@gmail.com alouchboyz",
                "Djiby boh laminedia 02/01/1994 Masculin laminedia.ababacar94@gmail.com bidahking",
                "Mohamed Ngom petiwilane 02/01/1994 Masculin petiwilane.ababacar94@gmail.com ngomb",
                "maimouna Diop amathbs 02/01/1994 feminin amathbs.ababacar94@gmail.com maimai").forEach(emp ->{
            apiService.saveChefDepartement(new EmployeRegister(null,emp.split(" ")[2],emp.split(" ")[0],emp.split(" ")[1],emp.split(" ")[3],emp.split(" ")[4],emp.split(" ")[5],emp.split(" ")[6],"REF_EMP"+ RandomString.make(6).toUpperCase(),apiService.getCurrentDate(),null));

        });
    }


    public void executeAll(ApiService  apiService,RapportEmployeRepository rapportEmployeRepository){
        saveListHrManager(apiService);
        saveListEmployee(apiService);
        saveListDepartement(apiService);
        saveListRapport(apiService,rapportEmployeRepository);
        saveListChefDepartement(apiService);
    }



}
