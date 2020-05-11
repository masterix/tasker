package com.ps.tasks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "attachments")
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String comment;

    public Attachment(String filename, String comment) {
        this.filename = filename;
        this.comment = comment;
    }
}
