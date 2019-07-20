package com.abdev.sbtest.apiHrManager.repository;

import com.abdev.sbtest.apiHrManager.models.Role;
import com.abdev.sbtest.apiHrManager.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
