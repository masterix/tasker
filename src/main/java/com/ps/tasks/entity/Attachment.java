package com.ps.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("attachments")
@AllArgsConstructor
public class Attachment {
    private String filename;
    private String comment;
}
