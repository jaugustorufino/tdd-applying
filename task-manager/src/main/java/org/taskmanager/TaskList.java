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

    public int size() {
        return tasks.size();
    }
}
