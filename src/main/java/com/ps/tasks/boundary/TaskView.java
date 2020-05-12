package com.ps.tasks.boundary;

import java.time.LocalDateTime;

interface TaskView {
    Long getId();
    String getTitle();
    String getDescription();
    LocalDateTime getCreatedAt();
}
