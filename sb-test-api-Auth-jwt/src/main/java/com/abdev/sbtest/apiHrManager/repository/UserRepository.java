package com.abdev.sbtest.apiHrManager.repository;

import com.abdev.sbtest.apiHrManager.models.RoleName;
import com.abdev.sbtest.apiHrManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long aLong);

    User deleteById(long id);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    List<User> findAllByRoles(RoleName roleName);


    Optional<User> findByUsernameOrEmail(String username, String email);
}
