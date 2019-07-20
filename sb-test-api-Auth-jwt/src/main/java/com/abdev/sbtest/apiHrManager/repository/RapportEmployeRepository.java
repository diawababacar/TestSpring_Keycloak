package com.abdev.sbtest.apiHrManager.repository;

import com.abdev.sbtest.apiHrManager.models.RapportEmploye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface RapportEmployeRepository extends JpaRepository<RapportEmploye,Long> {
    List<RapportEmploye>    findAllByTimeNow(String  date);
}
