package com.ps.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private List<String> attachments;

    public void addAttachment(String filename) {
        attachments.add(filename);
    }

    public List<String> getAttachments() {
        return attachments;
    }
}
