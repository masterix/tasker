package com.ps.tags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("tags")
public class Tag {
    @Id
    private Long id;
    private String name;
}
