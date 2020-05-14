package com.ps.projects.entity;

import com.ps.tasks.entity.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "projects")
@Entity
@NoArgsConstructor
@NamedEntityGraph(
        name = "Project.fullDetails",
        attributeNodes = {
                @NamedAttributeNode(value = "tasks", subgraph = "Task.full")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "Task.full",
                        attributeNodes = {
                                @NamedAttributeNode("tags"),
                                @NamedAttributeNode("attachments")
                        })
        }
)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    private List<Task> tasks = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
