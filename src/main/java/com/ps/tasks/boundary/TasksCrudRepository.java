package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TasksCrudRepository extends CrudRepository<Task, Long> {

}
