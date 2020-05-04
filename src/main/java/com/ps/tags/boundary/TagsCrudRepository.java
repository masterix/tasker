package com.ps.tags.boundary;

import com.ps.tags.entity.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagsCrudRepository extends CrudRepository<Tag, Long> {
}
