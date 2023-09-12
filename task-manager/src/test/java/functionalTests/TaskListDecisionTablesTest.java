package functionalTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskList;
import org.taskmanager.TaskPriority;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListDecisionTablesTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTaskWithValidProps() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(1, taskList.size());
    }

    @Test
    public void testNotAddTaskWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(null, "Description", LocalDate.now(), TaskPriority.HIGH);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    public void testNotAddTaskWithInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("Title", null, LocalDate.now(), TaskPriority.HIGH);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    public void testNotAddTaskWithInvalidDueDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("Title", "Description", null, TaskPriority.HIGH);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    public void testNotAddTaskWithInvalidPriority() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("Title", "Description", LocalDate.now(), null);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    public void testUpdateTaskWithValidProps() {
        Task taskToUpdate = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdate);

        Task updatedTask = new Task("New Title", "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        Task task = taskList.updateTask(taskToUpdate.getId(), updatedTask);

        assertEquals(task.getId(), taskToUpdate.getId());
        assertEquals(task.getTitle(), updatedTask.getTitle());
        assertEquals(task.getDescription(), updatedTask.getDescription());
        assertEquals(task.getDueDate(), updatedTask.getDueDate());
        assertEquals(task.getPriority(), updatedTask.getPriority());
    }

    @Test
    public void testNotUpdateTaskWithInvalidTaskId() {
        Task task = new Task("Title", "Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(task);

        Task updatedTask = new Task("New Title", "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);

        assertThrows(IllegalArgumentException.class, () -> {
            UUID invalidTaskId = UUID.randomUUID();
            taskList.updateTask(invalidTaskId, updatedTask);
        });

        assertNotEquals(task.getId(), updatedTask.getId());
        assertNotEquals(task.getTitle(), updatedTask.getTitle());
        assertNotEquals(task.getDescription(), updatedTask.getDescription());
        assertNotEquals(task.getDueDate(), updatedTask.getDueDate());
        assertNotEquals(task.getPriority(), updatedTask.getPriority());
    }

    @Test
    public void testNotUpdateTaskWithInvalidTitle() {
        Task task = new Task("Title", "Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(task);

        Task updatedTask = new Task(null, task.getDescription(), task.getDueDate(), task.getPriority());

        assertThrows(IllegalArgumentException.class, () -> {
            taskList.updateTask(task.getId(), updatedTask);
        });

        assertNotEquals(task.getId(), updatedTask.getId());
        assertNotEquals(task.getTitle(), updatedTask.getTitle());
    }

    @Test
    public void testNotUpdateTaskWithInvalidDescription() {
        Task task = new Task("Title", "Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(task);

        Task updatedTask = new Task(task.getTitle(), null, task.getDueDate(), task.getPriority());

        assertThrows(IllegalArgumentException.class, () -> {
            taskList.updateTask(task.getId(), updatedTask);
        });

        assertNotEquals(task.getId(), updatedTask.getId());
        assertNotEquals(task.getDescription(), updatedTask.getDescription());
    }

    @Test
    public void testNotUpdateTaskWithInvalidDueDate() {
        Task task = new Task("Title", "Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(task);

        Task updatedTask = new Task(task.getTitle(), task.getDescription(), null, task.getPriority());

        assertThrows(IllegalArgumentException.class, () -> {
            taskList.updateTask(task.getId(), updatedTask);
        });

        assertNotEquals(task.getId(), updatedTask.getId());
        assertNotEquals(task.getDueDate(), updatedTask.getDueDate());
    }

    @Test
    public void testNotUpdateTaskWithInvalidPriority() {
        Task task = new Task("Title", "Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(task);

        Task updatedTask = new Task(task.getTitle(), task.getDescription(), task.getDueDate(), null);

        assertThrows(IllegalArgumentException.class, () -> {
            taskList.updateTask(task.getId(), updatedTask);
        });

        assertNotEquals(task.getId(), updatedTask.getId());
        assertNotEquals(task.getPriority(), updatedTask.getPriority());
    }

    @Test
    public void testDeleteTaskByValidId() {
        Task taskToDelete = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToDelete);

        Task task = taskList.deleteTask(taskToDelete.getId());
        assertEquals(taskToDelete, task);

        assertNull(taskList.findTask(taskToDelete.getId()));
    }

    @Test
    public void testNotDeleteTaskByInvalidId() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        UUID invalidTaskId = UUID.randomUUID();
        Task deletedTask = taskList.deleteTask(invalidTaskId);
        assertNull(deletedTask);

        assertEquals(1, taskList.size());
    }

    @Test
    public void testFindTaskByValidId() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(taskList.findTask(task.getId()), task);
    }

    @Test
    public void testNotFindTaskByInvalidId() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        UUID invalidTaskId = UUID.randomUUID();

        assertNotEquals(taskList.findTask(invalidTaskId), task);
        assertNull(taskList.findTask(invalidTaskId));
    }

    @Test
    public void testUpdateTaskPriority() {
        Task taskToUpdatePriority = new Task("Title", "Description", LocalDate.now(), TaskPriority.LOW);
        taskList.addTask(taskToUpdatePriority);

        Task task = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(taskToUpdatePriority, task);
        assertEquals(TaskPriority.LOW, task.getPriority());

        taskList.updateTaskPriority(task.getId(), TaskPriority.HIGH);

        Task updatedTask = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(updatedTask, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }

    @Test
    public void testNotUpdateTaskPriorityWithInvalidTask() {
        Task taskToUpdatePriority = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdatePriority);

        Task task = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(taskToUpdatePriority, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());

        assertThrows(IllegalArgumentException.class, () -> {
            UUID invalidTaskId = UUID.randomUUID();
            taskList.updateTaskPriority(invalidTaskId, TaskPriority.LOW);
        });

        Task updatedTask = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(updatedTask, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }
}
