package com.ps.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "projects")
@Entity
@NoArgsConstructor
public class Project {
    @Id
    private Long id;
    private String name;

    public Project(String name) {
        this.name = name;
    }
}
