package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksCrudRepository extends CrudRepository<Task, Long> {

    @Modifying
    @Query("UPDATE Tasks SET title = :title, description = :description WHERE id = :id")
    void updateTitleDescription(@Param("id") Long id, @Param("title") String title, @Param("description") String description);

    @Query("SELECT * FROM Tasks t WHERE upper(t.title) LIKE '%' || upper(:title) || '%'")
    List<Task> findByTitle(@Param("title") String title);

    @Query("SELECT * FROM Tasks t JOIN Attachments a ON t.id = a.task")
    List<Task> findWithAttachments();
}
