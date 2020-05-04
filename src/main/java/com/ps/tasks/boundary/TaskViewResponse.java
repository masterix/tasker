package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskViewResponse {
    Long id;
    String title;
    String description;
    LocalDateTime createdAt;

    static TaskViewResponse from(Task task) {
        return new TaskViewResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt()
        );
    }
}
