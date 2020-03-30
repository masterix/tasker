package com.ps.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskResponse {
    Long id;
    String title;
    String description;
    LocalDateTime createdAt;
}
