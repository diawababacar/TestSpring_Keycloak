package com.abdev.sbtest.apiHrManager.repository;

import com.abdev.sbtest.apiHrManager.models.Employee;
import com.abdev.sbtest.apiHrManager.models.RoleName;
import com.abdev.sbtest.apiHrManager.models.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface EmployeeRepository extends UserRepository {
    List<User> findAllByRoles(RoleName roleName);
    Employee    findById(long   id);
    Employee    deleteById(long id);
}
