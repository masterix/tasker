package com.ps;

import com.ps.tags.boundary.TagsCrudRepository;
import com.ps.tags.entity.Tag;
import com.ps.tasks.boundary.TasksRepository;
import com.ps.tasks.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
@Slf4j
public class DevStartup {

    private final TasksRepository tasksRepository;
    private final TagsCrudRepository tagsCrudRepository;
    private final Clock clock;

    public DevStartup(TasksRepository tasksRepository, TagsCrudRepository tagsCrudRepository, Clock clock) {
        this.tasksRepository = tasksRepository;
        this.tagsCrudRepository = tagsCrudRepository;
        this.clock = clock;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeApplication() {
        log.info("Initializing DEV environment");
        insertTasks();
        insertTags();
    }

    private void insertTags() {
        List<Tag> tags = Arrays.asList(
                new Tag("URGENT"),
                new Tag("Home"),
                new Tag("Job")
        );

        tagsCrudRepository.saveAll(tags);
    }

    private void insertTasks() {
        List<Task> tasks = Arrays.asList(
                new Task("Dokończyć zadanie", "Opis do zadania", clock.time()),
                new Task("Obejrzeć moduł 6", "Opis do zadania", clock.time()),
                new Task("Stworzyć własny projekt", "Opis do zadania", clock.time())
        );

        tasksRepository.addAll(tasks);
    }
}
