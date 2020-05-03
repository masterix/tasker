package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;
import com.sun.xml.internal.ws.api.message.Attachment;
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

    static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getAttachments()
                    .stream()
                    .map(AttachmentResponse::from)
                    .collect(Collectors.toSet())
        );
    }
}
