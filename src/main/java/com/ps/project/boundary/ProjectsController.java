package com.ps.project.boundary;

import com.ps.project.control.ProjectsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController()
@RequestMapping(path = "/api/projects")
public class ProjectsController {
    private final ProjectsService projectsService;

    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponse>> index() {
        return ResponseEntity
        .ok(projectsService.findAll()
                .stream()
                .map(p -> new ProjectResponse(p.getId(), p.getName()))
                .collect(toList()));
    }
}
