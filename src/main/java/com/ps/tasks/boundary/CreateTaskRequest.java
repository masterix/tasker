package com.ps.tasks.boundary;

import lombok.Data;

@Data
public class CreateTaskRequest {
    String title;
    String description;
    String attachmentComment;
}
