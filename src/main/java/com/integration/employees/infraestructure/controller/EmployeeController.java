package com.integration.employees.infraestructure.controller;

import com.integration.employees.domain.converter.EmployeeParser;
import com.integration.employees.domain.model.EmployeeDTO;
import com.integration.employees.infraestructure.database.entity.EmployeeEntity;
import com.integration.employees.infraestructure.database.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return (List<EmployeeEntity>) employeeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employee) {
        EmployeeEntity employeeCreate = EmployeeParser.presenterToEntity(employee);
        EmployeeEntity createdEmployee = employeeRepository.save(employeeCreate);
        EmployeeDTO employeeDTO = EmployeeParser.presenterToDTO(createdEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDetails) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employee.setIdNumber(employeeDetails.getIdNumber());
        employee.setName(employeeDetails.getName());
        employee.setPosition(employeeDetails.getPosition());
        employee.setPhoto(employeeDetails.getPhoto());
        employee.setHireDate(employeeDetails.getHireDate());

        EmployeeEntity updatedEmployee = employeeRepository.save(employee);
        EmployeeDTO employeeDTO = EmployeeParser.presenterToDTO(updatedEmployee);
        return ResponseEntity.ok(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
        return ResponseEntity.noContent().build();
    }
}
