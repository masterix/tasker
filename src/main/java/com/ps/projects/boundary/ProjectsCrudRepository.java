package com.ps.projects.boundary;

import com.ps.projects.entity.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectsCrudRepository extends JpaRepository<Project, Long> {
    @Modifying
    @Query("UPDATE Project SET name = :name WHERE id = :id ")
    public void updateName(@Param("id") Long id, @Param("name") String name);

    List<SimpleProjectResponse> findAllBy();

    @EntityGraph(value = "Project.fullDetails", type = EntityGraphType.LOAD)
    List<Project> findAll();
}
