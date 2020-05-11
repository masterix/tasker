package com.ps.project.control;

import com.ps.exceptions.NotFoundException;
import com.ps.project.boundary.CreateProjectRequest;
import com.ps.project.boundary.ProjectsCrudRepository;
import com.ps.project.boundary.UpdateProjectRequest;
import com.ps.project.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectsService {
    private final ProjectsCrudRepository projectsRepository;

    public ProjectsService(ProjectsCrudRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public List<Project> findAll() {
        return StreamSupport
                .stream(projectsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void createProject(CreateProjectRequest createProjectRequest) {
        projectsRepository.save(new Project(createProjectRequest.getName()));
    }

    public void updateProject(Long id, UpdateProjectRequest updateProjectRequest) {
        if (!projectsRepository.existsById(id)) {
            throw new NotFoundException("Could not found project with id: " + id);
        }
//        projectsRepository.updateName(id, updateProjectRequest.getName());
    }

    public Project findById(Long id) {
        return projectsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found project with id: " + id));
    }

    public void delete(Long id) {
        projectsRepository.deleteById(id);
    }
}
