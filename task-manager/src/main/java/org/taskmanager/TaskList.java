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
        taskToUpdate.updateAll(updatedTask);

        return taskToUpdate;
    }

    public Task deleteTask(UUID id) {
        return this.tasks.remove(id);
    }

    public void updateTaskPriority(UUID id, TaskPriority newTaskPriority) {
        Task taskToUpdate = this.findTask(id);
        taskToUpdate.setPriority(newTaskPriority);
    }

    public String listSortedTasks() {
        List<Task> list = new ArrayList<>(this.tasks.values());
        System.out.println(list.get(0));

        Collections.sort(list);
        System.out.println(list.get(0));


        String stringList = "";

        for (int i = 0; i < list.size(); i++) {
            stringList = stringList.concat(list.get(i).toString());
            if (i < list.size() - 1) {
                stringList = stringList.concat("\n\n");
            }
        }

        return stringList;
    }

    public int size() {
        return tasks.size();
    }
}
