package com.ps.project.boundary;

import com.ps.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectsCrudRepository extends JpaRepository<Project, Long> {
    @Modifying
    @Query("UPDATE Project SET name = :name WHERE id = :id ")
    public void updateName(@Param("id") Long id, @Param("name") String name);
}
