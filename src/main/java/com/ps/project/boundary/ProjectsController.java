package com.ps.project.boundary;

import com.ps.exceptions.NotFoundException;
import com.ps.project.control.ProjectsService;
import com.ps.project.entity.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping(path = "/api/projects")
public class ProjectsController {
    private final ProjectsService projectsService;

    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping()
    public ResponseEntity<List<Object>> index(@RequestParam(value = "full", required = false) boolean full) {
        if (full) {
            List<Project> allFull = projectsService.findAllFull();
            return ResponseEntity
                    .ok(new ArrayList<>(allFull));
        } else {
            return ResponseEntity
                .ok(new ArrayList<>(projectsService.findAll()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectResponse> show(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(ProjectResponse.from(projectsService.findById(id)));
        } catch (NotFoundException e) {
            log.error("Could not found project with id {}", id);

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        log.info("Creating new project");
        projectsService.createProject(createProjectRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable Long id, @RequestBody UpdateProjectRequest updateProjectRequestt) {
        log.info("Updating project {}", id);
        try {
            projectsService.updateProject(id, updateProjectRequestt);

            return ResponseEntity
                    .noContent()
                    .build();
        } catch (NotFoundException e) {
            log.error("error while updating project");

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long id) {
        log.info("Deleting project with id {}", id);
        try {
            projectsService.delete(id);

            return ResponseEntity
                    .noContent()
                    .build();
        } catch (NotFoundException e) {
            log.error("error while deleting project");

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
