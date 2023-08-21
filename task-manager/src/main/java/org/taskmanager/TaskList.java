package org.taskmanager;

import java.util.*;

public class TaskList {

    private Map<UUID, Task> tasks;

    public TaskList() {
        this.tasks = new LinkedHashMap<>();
    }

    public void addTask(Task newTask) {
        this.tasks.put(newTask.getId(), newTask);
    }

    public Task findTask(UUID id) {
        return this.tasks.get(id);
    }

    public Task updateTask(UUID id, Task updatedTask) {
        Task taskToUpdate = this.findTask(id);

        taskToUpdate.setTitle(updatedTask.getTitle());
        taskToUpdate.setDescription(updatedTask.getDescription());
        taskToUpdate.setDueDate(updatedTask.getDueDate());
        taskToUpdate.setPriority(updatedTask.getPriority());

        return taskToUpdate;
    }

    public Task deleteTask(UUID id) {
        return this.tasks.remove(id);
    }

    public void updateTaskPriority(UUID id, TaskPriority newTaskPriority) {
        Task taskToUpdate = this.findTask(id);
        taskToUpdate.setPriority(newTaskPriority);
    }

    public int size() {
        return tasks.size();
    }
}
