package com.ps.projects.boundary;

import com.ps.projects.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;

    static ProjectResponse from(Project project) {
        return new ProjectResponse(project.getId(), project.getName());
    }
}
