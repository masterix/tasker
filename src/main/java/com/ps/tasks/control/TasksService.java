package com.ps.tasks.control;

import com.ps.Clock;
import com.ps.tags.control.TagsService;
import com.ps.tags.entity.Tag;
import com.ps.tasks.boundary.StorageService;
import com.ps.tasks.boundary.TasksRepository;
import com.ps.tasks.entity.Attachment;
import com.ps.tasks.entity.Task;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final Clock clock;
    private final StorageService storageService;
    private final TagsService tagsService;

    public TasksService(TasksRepository tasksRepository, Clock clock, StorageService storageService, TagsService tagsService) {
        this.tasksRepository = tasksRepository;
        this.clock = clock;
        this.storageService = storageService;
        this.tagsService = tagsService;
    }

    public Task addTask(String title, String description) {
        Task task = new Task(
                title,
                description,
                this.clock.time()
        );

        tasksRepository.add(task);

        return task;
    }

    public void updateTask(Long id, String title, String description) {
        tasksRepository.update(id, title, description);
    }

    public List<Task> fetchAll() {
        return tasksRepository.fetchAll();
    }

    public List<Task> filterByTitle(String title) {
        return tasksRepository.findByTitle(title);
    }

    public List<Task> findWithAttachments() {
        return tasksRepository.findWithAttachments();
    }

    public Task fetchById(Long id) {
        return tasksRepository.fetchById(id);
    }

    public void delete(Long id) {
        tasksRepository.delete(id);
    }

    public void addAttachmentToTask(Long id, String filename, String comment) {
        Task task = tasksRepository.fetchById(id);
        task.addAttachment(filename, comment);
        tasksRepository.save(task);
    }

    public Optional<Resource> loadAttachment(Long id, String filename) throws MalformedURLException {
        Optional<Resource> attachment = Optional.empty();
        Task task = tasksRepository.fetchById(id);
        if (task.getAttachments().stream().map(Attachment::getFilename).anyMatch(it -> it.equals(filename))) {
            attachment = Optional.of(storageService.loadFile(filename));
        }

        return attachment;
    }

    public void addTag(Long id, Long tagId) {
        Task task = tasksRepository.fetchById(id);
        Tag tag = tagsService.findById(tagId);
        task.addTag(tag);
        tasksRepository.save(task);
    }

    public void removeTag(Long id, Long tagId) {
        Task task = tasksRepository.fetchById(id);
        Tag tag = tagsService.findById(tagId);
        task.removeTag(tag);
        tasksRepository.save(task);
    }
}
