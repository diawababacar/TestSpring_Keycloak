package com.abdev.sbtest.apiHrManager.services;

import com.abdev.sbtest.apiHrManager.exception.AppException;
import com.abdev.sbtest.apiHrManager.exception.ResourceNotFoundException;
import com.abdev.sbtest.apiHrManager.models.*;
import com.abdev.sbtest.apiHrManager.payload.*;
import com.abdev.sbtest.apiHrManager.repository.*;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ApiService {
    @Autowired
    private RoleRepository  roleRepository;

    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private DepartementRepository   departementRepository;
    @Autowired
    private EmployeeRepository  employeeRepository;
    @Autowired
    private HrManagerRepository hrManagerRepository;
    @Autowired
    private RapportEmployeRepository    rapportEmployeRepository;

    private User    user;
    private Employee    employee;
    private HrManager   hrManager;
    private Departement departement;
    private ManageurRegister    manageurRegister;
    private EmployeRegister employeRegister;
    private Role    role;
    private DepartementResponse departementResponse;
    private EmployeResponse employeResponse;
    private RapportEmployeResponse  rapportEmployeResponse;


    public ApiService(){
        this.user = new User();
        this.employee = new Employee();
        this.hrManager = new HrManager();
        this.departement = new Departement();
        this.manageurRegister   =   new ManageurRegister();
        this.employeRegister    =   new EmployeRegister();
        this.role = new Role();
        this.departementResponse = new DepartementResponse();
        this.employeResponse = new EmployeResponse();
        this.rapportEmployeResponse = new RapportEmployeResponse();
    }


    public List<User>   userList(RoleName   roleName){
        List<User>  employees =   userRepository.findAllByRoles(roleName);
        if (employees == null){
            throw new RuntimeException("La Liste des "+roleName+" est Vide");
        }
        return employees;
    }

    /*
     * Services Pour Employée
     * */

    public Employee saveEmploye(EmployeRegister employeRegister){
        employee    =   new Employee();
        if (employeRegister.getId() != null){
            employee.setId(employeRegister.getId());
            employee.setRoles(null);
        }
        employee.setLast_name(employeRegister.getLast_name());
        employee.setFirst_name(employeRegister.getFirst_name());
        employee.setUsername(employeRegister.getUsername());
        employee.setBirth_date(employeRegister.getBirth_date());
        employee.setGender(employeRegister.getBirth_date());
        employee.setEmail(employeRegister.getEmail());
        employee.setPassword(employeRegister.getPassword());
        employee.setRef_employee(employeRegister.getRef_employee());
        employee.setHire_date(employeRegister.getHire_date());
        employee.setRoles(Collections.singleton(createRole(RoleName.ROLE_EMPLOYEE)));

        return employeeRepository.save(employee);
    }


    /*
     * Services Pour Hr Manager
     * */
    public HrManager    saveHrManager(ManageurRegister  manageurRegister){
        hrManager   =   new HrManager();
        hrManager.setLast_name(manageurRegister.getLast_name());
        hrManager.setFirst_name(manageurRegister.getFirst_name());
        hrManager.setUsername(manageurRegister.getUsername());
        hrManager.setBirth_date(manageurRegister.getBirth_date());
        hrManager.setGender(manageurRegister.getBirth_date());
        hrManager.setEmail(manageurRegister.getEmail());
        hrManager.setPassword(manageurRegister.getPassword());
        hrManager.setRoles(Collections.singleton(createRole(RoleName.ROLE_HR_MANAGER)));
        hrManager.setRef_hr_manager(manageurRegister.getRef_hr_manager());
        hrManager.setHire_date(manageurRegister.getHire_date());
        return hrManagerRepository.save(hrManager);
    }




    /*
    * Services Pour Departement
    * */


    public Departement  saveDepartementNotEmploye(DepartementRegister departementRegister){
        departement =   new Departement();

        departement.setNo(RandomString.make(10)+RandomString.make(4).toUpperCase());
        departement.setName(departementRegister.getName());
        departement.setDateCreation(departementRegister.getDateCreation());

        return departementRepository.save(departement);
    }





    //Cette Methode Permet de mettre à jour un employe en Chef de Departement
    public Employee  saveChefDepartement(EmployeRegister employeRegister){
        employee    =   new Employee();
        employee.setLast_name(employeRegister.getLast_name());
        employee.setFirst_name(employeRegister.getFirst_name());
        employee.setUsername(employeRegister.getUsername());
        employee.setBirth_date(employeRegister.getBirth_date());
        employee.setGender(employeRegister.getBirth_date());
        employee.setEmail(employeRegister.getEmail());
        employee.setPassword(employeRegister.getPassword());
        employee.setRef_employee(employeRegister.getRef_employee());
        employee.setHire_date(employeRegister.getHire_date());
        employee.setRoles(Collections.singleton(createRole(RoleName.ROLE_CHEF_DEPARTEMENT)));
        return employeeRepository.save(employee);
    }

    //Cette Methode me permet de retourner un Role pour un  utilisateur
    public Role createRole(RoleName roleName){
        Role    userRole = roleRepository.findByRoleName(roleName).orElseThrow(() -> new AppException("Ce Role d'utilisateur n'est pas défini"));
        return userRole;
    }


    public User getUserId(long  id,RoleName roleName){
        User    user    =   userRepository.findById(id);
        Role    userRole = roleRepository.findByRoleName(roleName).orElseThrow(() -> new AppException("Ce Role d'utilisateur n'est pas défini"));
        /*if (user.getRole().getRoleName()    !=  userRole.getRoleName()){
            throw new ResourceNotFoundException(""+roleName, ""+roleName, userRole);
        }*/
        return user;
    }

    //Cette exporter le rapport journalier des employés
    public void exportRapportCSV(HttpServletResponse response) throws Exception {
        String  filename    =   "report_ {"+getCurrentDate()+"} .csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        //create a csv writer
        StatefulBeanToCsv<RapportEmployeResponse> writer = new StatefulBeanToCsvBuilder<RapportEmployeResponse>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
        //write all employe to csv file
        writer.write(rapportEmployeResponseList());
    }

    public List<RapportEmployeResponse> rapportEmployeResponseList(){
        List<RapportEmployeResponse> rapportEmployeResponses  =   new ArrayList<>();
        List<RapportEmploye>    rapportEmployes =   rapportEmployeRepository.findAllByTimeNow(getCurrentLocalDateTimeStamp());

        for (RapportEmploye rapportEmploye : rapportEmployes){
            RapportEmployeResponse  rapportEmployeResponse  =   new RapportEmployeResponse();
            rapportEmployeResponse.setId(rapportEmploye.getId());
            rapportEmployeResponse.setNameProject(rapportEmploye.getNameProject());
            rapportEmployeResponse.setTimeAdd(rapportEmploye.getTimeAdd());
            rapportEmployeResponse.setTimeNow(getCurrentTimeUsingCalendar());
            rapportEmployeResponses.add(rapportEmployeResponse);
        }

        return rapportEmployeResponses;
    }

    // Date + Heure
    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    // Heure
    public String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    // Heure
    public String getCurrentDate() {
        SimpleDateFormat dateformat  =   new SimpleDateFormat("yyyy-MM-dd");
        String  dateReport = dateformat.format(new Date());
        return dateReport;
    }

}
