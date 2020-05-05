package com.ps.project.boundary;

import com.ps.project.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectsCrudRepository extends CrudRepository<Project, Long> {
}
