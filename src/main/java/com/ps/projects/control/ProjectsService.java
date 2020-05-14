package com.ps.projects.control;

import com.ps.exceptions.NotFoundException;
import com.ps.projects.boundary.*;
import com.ps.projects.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {
    private final ProjectsCrudRepository projectsRepository;

    public ProjectsService(ProjectsCrudRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public List<SimpleProjectResponse> findAll() {
        return projectsRepository.findAllBy();
    }

    public List<Project> findAllFull() {
        return projectsRepository.findAll();
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
