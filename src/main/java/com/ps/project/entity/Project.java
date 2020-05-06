package com.ps.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("projects")
public class Project {
    @Id
    private Long id;
    private String name;

    public Project(String name) {
        this.name = name;
    }
}
