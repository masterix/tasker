package com.ps.project.control;

import com.ps.project.boundary.ProjectsCrudRepository;
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
}
