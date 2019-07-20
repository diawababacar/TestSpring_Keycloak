package com.abdev.sbtest.apiHrManager.repository;

import com.abdev.sbtest.apiHrManager.models.HrManager;
import com.abdev.sbtest.apiHrManager.models.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HrManagerRepository extends UserRepository{
    HrManager findById(long aLong);
    HrManager deleteById(long id);
}
