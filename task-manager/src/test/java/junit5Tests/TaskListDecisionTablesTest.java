package junit5Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskList;
import org.taskmanager.TaskPriority;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task List - Decision Tables")
public class TaskListDecisionTablesTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    @DisplayName("should add a task with valid properties in task list")
    public void testAddTaskWithValidProps() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(1, taskList.size());
    }

    @Test
    @DisplayName("should not add a task with invalid title in task list")
    public void testNotAddTaskWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(null, "Description", LocalDate.now(), TaskPriority.HIGH);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    @DisplayName("should not add a task with invalid description in task list")
    public void testNotAddTaskWithInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("Title", null, LocalDate.now(), TaskPriority.HIGH);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    @DisplayName("should not add a task with invalid due date in task list")
    public void testNotAddTaskWithInvalidDueDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("Title", "Description", null, TaskPriority.HIGH);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    @DisplayName("should not add a task with invalid priority in task list")
    public void testNotAddTaskWithInvalidPriority() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("Title", "Description", LocalDate.now(), null);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    @DisplayName("should update a task by id with valid properties in task list")
    public void testUpdateTaskWithValidProps() {
        Task taskToUpdate = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdate);

        Task updatedTask = new Task("New Title", "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        Task task = taskList.updateTask(taskToUpdate.getId(), updatedTask);

        assertAll("updated task properties", () -> {
            assertEquals(task.getId(), taskToUpdate.getId());
            assertEquals(task.getTitle(), updatedTask.getTitle());
            assertEquals(task.getDescription(), updatedTask.getDescription());
            assertEquals(task.getDueDate(), updatedTask.getDueDate());
            assertEquals(task.getPriority(), updatedTask.getPriority());
        });
    }

    @Test
    @DisplayName("should not update a task with invalid id in task list")
    public void testNotUpdateTaskWithInvalidTaskId() {
        Task task = new Task("Title", "Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(task);

        Task updatedTask = new Task("New Title", "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);

        assertThrows(IllegalArgumentException.class, () -> {
            UUID invalidTaskId = UUID.randomUUID();
            taskList.updateTask(invalidTaskId, updatedTask);
        });

        assertAll("task properties", () -> {
            assertNotEquals(task.getId(), updatedTask.getId());
            assertNotEquals(task.getTitle(), updatedTask.getTitle());
            assertNotEquals(task.getDescription(), updatedTask.getDescription());
            assertNotEquals(task.getDueDate(), updatedTask.getDueDate());
            assertNotEquals(task.getPriority(), updatedTask.getPriority());
        });
    }

    @Test
    @DisplayName("should not update a task with invalid title in task list")
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
    @DisplayName("should not update a task with invalid description in task list")
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
    @DisplayName("should not update a task with invalid due date in task list")
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
    @DisplayName("should not update a task with invalid priority in task list")
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
    @DisplayName("should delete a task by id in task list")
    public void testDeleteTaskByValidId() {
        Task taskToDelete = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToDelete);

        Task task = taskList.deleteTask(taskToDelete.getId());
        assertEquals(taskToDelete, task);

        assertNull(taskList.findTask(taskToDelete.getId()));
    }

    @Test
    @DisplayName("should not delete a task with invalid id in task list")
    public void testNotDeleteTaskByInvalidId() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        UUID invalidTaskId = UUID.randomUUID();
        Task deletedTask = taskList.deleteTask(invalidTaskId);
        assertNull(deletedTask);

        assertEquals(1, taskList.size());
    }

    @Test
    @DisplayName("should find a task by id in task list")
    public void testFindTaskByValidId() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(taskList.findTask(task.getId()), task);
    }

    @Test
    @DisplayName("should not find a task with invalid id in task list")
    public void testNotFindTaskByInvalidId() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        UUID invalidTaskId = UUID.randomUUID();

        assertNotEquals(taskList.findTask(invalidTaskId), task);
        assertNull(taskList.findTask(invalidTaskId));
    }

    @Test
    @DisplayName("should update the task priority in task list")
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
    @DisplayName("should not update the task priority in task list passing an invalid task id")
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
