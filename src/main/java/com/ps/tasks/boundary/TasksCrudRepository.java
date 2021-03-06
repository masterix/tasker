package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksCrudRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query("UPDATE Task SET title = :title, description = :description WHERE id = :id")
    void updateTitleDescription(@Param("id") Long id, @Param("title") String title, @Param("description") String description);

    @Query
    List<Task> findAllByTitleLike(String title);

    @EntityGraph(value = "Task.details", type = EntityGraphType.LOAD)
    List<Task> findAll();

    List<TaskView> findAllBy();

    @Query("FROM Task t WHERE t.attachments.size > 0")
    List<Task> findWithAttachments();
}
