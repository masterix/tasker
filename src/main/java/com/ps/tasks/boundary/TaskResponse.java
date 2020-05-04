package com.ps.tasks.boundary;

import com.ps.tags.entity.Tag;
import com.ps.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TaskResponse {
    Long id;
    String title;
    String description;
    LocalDateTime createdAt;
    Set<AttachmentResponse> attachments;
    Set<TagResponse> tags;

    static TaskResponse from(Task task, Set<Tag> tags) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getAttachments()
                        .stream()
                        .map(AttachmentResponse::from)
                        .collect(Collectors.toSet()),
                tags
                        .stream()
                        .map(TagResponse::from)
                        .collect(Collectors.toSet())
        );
    }
}
