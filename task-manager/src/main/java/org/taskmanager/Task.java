package org.taskmanager;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Task implements Comparable<Task> {

    private UUID id;

    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;

    public Task(UUID id, String title, String description, LocalDate dueDate, TaskPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public Task(String title, String description, LocalDate dueDate, TaskPriority priority) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task #" + id + ": " +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nDue Date: " + dueDate +
                "\nPriority: " + priority;
    }

    @Override
    public int compareTo(Task otherTask) {
        int priorityComparison = Integer.compare(otherTask.getPriority().getValor(), this.getPriority().getValor());

        if (priorityComparison != 0) {
            return priorityComparison;
        }

        return this.getDueDate().compareTo(otherTask.getDueDate());
    }
}
