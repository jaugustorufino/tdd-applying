package org.taskmanager;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public int size() {
        return tasks.size();
    }
}
