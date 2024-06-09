package com.integration.employees.domain.model;

import com.integration.employees.infraestructure.database.entity.PositionEntity;

import java.time.LocalDateTime;

public class EmployeeDTO {
    private Long id;
    private Long idNumber;
    private String name;
    private String photo;
    private LocalDateTime hireDate;
    private PositionEntity position;

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }
}
