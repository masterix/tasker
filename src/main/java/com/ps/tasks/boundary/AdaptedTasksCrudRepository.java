package com.ps.tasks.boundary;

import com.ps.exceptions.NotFoundException;
import com.ps.tasks.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

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
        return StreamSupport
                .stream(tasksCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
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
        tasksCrudRepository
                .findById(id)
                .map(task -> {
                    task.setTitle(title);
                    task.setDescription(description);

                    return task;
                })
                .ifPresent(tasksCrudRepository::save);
    }

    @Override
    public void save(Task task) {
        tasksCrudRepository.save(task);
    }
}
