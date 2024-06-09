package com.integration.employees.infraestructure.database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long idNumber;
    private String name;
    private String photo;
    private LocalDateTime hireDate;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private PositionEntity position;

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

    @PrePersist
    public void prePersist() {
        if (this.hireDate == null) {
            this.hireDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (hireDate == null) {
            hireDate = LocalDateTime.now();
        }
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    public EmployeeEntity() {
    }

    public EmployeeEntity(Long idNumber, String name, String photo, LocalDateTime hireDate, PositionEntity position) {
        this.idNumber = idNumber;
        this.name = name;
        this.photo = photo;
        this.hireDate = hireDate;
        this.position = position;
    }

}
