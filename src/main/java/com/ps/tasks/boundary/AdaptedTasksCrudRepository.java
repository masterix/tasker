package com.ps.tasks.boundary;

import com.ps.exceptions.NotFoundException;
import com.ps.tasks.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Primary
@Repository
@AllArgsConstructor
public class AdaptedTasksCrudRepository implements TasksRepository {
    private final TasksCrudRepository tasksCrudRepository;

    @Override
    public void add(Task task) {
        tasksCrudRepository.save(task);
    }

    @Override
    public List<Task> fetchAll() {
        return tasksCrudRepository.findAll();
    }

    @Override
    public Task fetchById(Long id) {
        return tasksCrudRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find task with id: " + id));
    }

    @Override
    public void delete(Long id) {
        tasksCrudRepository.deleteById(id);
    }

    @Override
    public void update(Long id, String title, String description) {
        tasksCrudRepository.updateTitleDescription(id, title, description);
    }

    @Override
    public void save(Task task) {
        tasksCrudRepository.save(task);
    }

    @Override
    public List<Task> findByTitle(String title) {
        return tasksCrudRepository.findAllByTitleLike(title);
    }

    @Override
    public List<Task> findWithAttachments() {
//        return tasksCrudRepository.findWithAttachments();
        return Collections.emptyList();
    }
}
