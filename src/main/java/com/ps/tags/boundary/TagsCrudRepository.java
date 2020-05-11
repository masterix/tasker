package com.ps.tags.boundary;

import com.ps.tags.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsCrudRepository extends JpaRepository<Tag, Long> {
}
