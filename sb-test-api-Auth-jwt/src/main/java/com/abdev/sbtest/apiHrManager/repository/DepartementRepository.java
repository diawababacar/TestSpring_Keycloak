package com.abdev.sbtest.apiHrManager.repository;

import com.abdev.sbtest.apiHrManager.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface DepartementRepository extends JpaRepository<Departement,Long> {
    Departement findById(long aLong);
    Departement deleteById(long id);
    Departement findByNo(String numero);
    Departement findByName(String   name);
}
