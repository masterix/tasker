package com.ps.project.boundary;

import com.ps.project.entity.Project;
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
