package com.ps.project.boundary;

import com.ps.project.entity.Project;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectsCrudRepository extends CrudRepository<Project, Long> {
    @Modifying
    @Query("UPDATE Projects SET name = :name WHERE id = :id ")
    void updateName(@Param("id") Long id, @Param("name") String name);
}
