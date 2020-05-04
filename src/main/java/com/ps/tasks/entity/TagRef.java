package com.ps.tasks.entity;

import com.ps.tags.entity.Tag;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tags_tasks")
public class TagRef {
    Long tag;

    public TagRef() {
    }

    public TagRef(Tag tag) {
        this.tag = tag.getId();
    }
}
