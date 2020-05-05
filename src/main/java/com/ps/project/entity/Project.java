package com.ps.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("projects")
public class Project {
    @Id
    private Long id;
    private String name;
}
