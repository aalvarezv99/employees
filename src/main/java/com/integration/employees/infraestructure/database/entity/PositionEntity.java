package com.integration.employees.infraestructure.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "position")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionEntity() {
    }

    public PositionEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
