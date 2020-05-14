package com.ps.projects.boundary;

import com.ps.Clock;
import com.ps.SystemClock;
import com.ps.projects.entity.Project;
import com.ps.tasks.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProjectsCrudRepositoryTest {

    Clock clock = new SystemClock();

    @Autowired
    ProjectsCrudRepository repository;

    @Test
    public void shouldLoadEntity() {
        Project project = new Project("test project");
        Task task = new Task("task", "description", clock.time());
        project.addTask(task);

        repository.save(project);
        List<Project> projects = repository.findAll();

        assertThat(projects.size()).isEqualTo(1);
        assertThat(projects.get(0).getName()).isEqualTo("test project");
        assertThat(projects.get(0).getTasks().size()).isEqualTo(1);
    }

    @Test
    public void shouldLoadView() {
        Project project = new Project("test project");

        repository.save(project);
        List<SimpleProjectResponse> projects = repository.findAllBy();

        assertThat(projects.size()).isEqualTo(1);
        assertThat(projects.get(0).getName()).isEqualTo("test project");
        assertThat(projects.get(0).getId()).isEqualTo(1);
    }
}