package com.ps.tasks;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {

    private Long id;

    private String title;

    private String author;

    private LocalDateTime createdAt = LocalDateTime.now();
}
