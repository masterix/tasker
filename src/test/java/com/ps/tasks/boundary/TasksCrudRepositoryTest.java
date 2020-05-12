package com.ps.tasks.boundary;

import com.ps.Clock;
import com.ps.SystemClock;
import com.ps.tasks.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TasksCrudRepositoryTest {

    Clock clock = new SystemClock();

    @Autowired
    TasksCrudRepository repository;

    @Test
    public void shouldLoadEntity() {
        Task task = new Task("Buy radio", "modern", clock.time());

        repository.save(task);
        List<Task> tasks = repository.findAll();

        assertThat(tasks.size()).isEqualTo(1);
        assertThat(tasks.get(0).getTitle()).isEqualToIgnoringCase("Buy radio");
        assertThat(tasks.get(0).getDescription()).isEqualToIgnoringCase("modern");
    }

    @Test
    public void shouldLoadView() {
        Task task = new Task("Buy radio", "modern", clock.time());

        repository.save(task);
        List<TaskView> tasks = repository.findAllBy();

        assertThat(tasks.size()).isEqualTo(1);
        assertThat(tasks.get(0).getTitle()).isEqualToIgnoringCase("Buy radio");
        assertThat(tasks.get(0).getDescription()).isEqualToIgnoringCase("modern");
    }
}