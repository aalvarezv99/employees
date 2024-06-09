package com.integration.employees.infraestructure.database.repository;

import com.integration.employees.infraestructure.database.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
}
