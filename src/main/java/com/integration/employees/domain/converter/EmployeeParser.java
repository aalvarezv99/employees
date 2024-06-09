package com.integration.employees.domain.converter;

import com.integration.employees.domain.model.EmployeeDTO;
import com.integration.employees.infraestructure.database.entity.EmployeeEntity;

public class EmployeeParser {
    public static EmployeeDTO presenterToDTO(EmployeeEntity employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setIdNumber(employee.getIdNumber());
        employeeDTO.setName(employee.getName());
        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setPhoto(employee.getPhoto());
        employeeDTO.setPosition(employee.getPosition());
        return employeeDTO;
    }

    public static EmployeeEntity presenterToEntity(EmployeeDTO employee) {
        return new EmployeeEntity(employee.getIdNumber(), employee.getName(), employee.getPhoto(), employee.getHireDate(), employee.getPosition());
    }
}
