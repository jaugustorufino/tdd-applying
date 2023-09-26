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

@DisplayName("Task List - Equivalence Partition")
public class TaskListEquivalencePartitioningTest {

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
    @DisplayName("should not add a task with invalid properties in task list")
    public void testNotAddTaskWithInvalidProps() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(null, "Description", LocalDate.now(), TaskPriority.HIGH);
            taskList.addTask(task);
        });

        assertEquals(0, taskList.size());
    }

    @Test
    @DisplayName("should update a task with valid properties in task list")
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
    @DisplayName("should not update a task with invalid properties in task list")
    public void testNotUpdateTaskWithInvalidProps() {
        Task taskToUpdate = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdate);

        Task updatedTask = new Task(null, "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        Task task = taskList.updateTask(taskToUpdate.getId(), updatedTask);

        assertAll("task properties", () -> {
            assertEquals(task.getId(), taskToUpdate.getId());
            assertEquals(task.getTitle(), taskToUpdate.getTitle());
            assertEquals(task.getDescription(), taskToUpdate.getDescription());
            assertEquals(task.getDueDate(), taskToUpdate.getDueDate());
            assertEquals(task.getPriority(), taskToUpdate.getPriority());
        });
    }

    @Test
    @DisplayName("should throw an error when updating a task with invalid id in task list")
    public void testThrowErrorUpdateTaskWithInvalidTaskId() {
        Task updatedTask = new Task("New Title", "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);

        assertThrows(IllegalArgumentException.class, () -> {
            UUID invalidTaskId = UUID.randomUUID();
            taskList.updateTask(invalidTaskId, updatedTask);
        });
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
    @DisplayName("should not delete a task in task list passing an invalid task id")
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
    @DisplayName("should not find a task in task list passing an invalid task id")
    public void testNotFindTaskByInvalidId() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        UUID invalidTaskId = UUID.randomUUID();

        assertNotEquals(taskList.findTask(invalidTaskId), task);
        assertNull(taskList.findTask(invalidTaskId));
    }

    @Test
    @DisplayName("should update the task priority to high in task list")
    public void testUpdateTaskPriorityToHigh() {
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
    @DisplayName("should update the task priority to medium in task list")
    public void testUpdateTaskPriorityToMedium() {
        Task taskToUpdatePriority = new Task("Title", "Description", LocalDate.now(), TaskPriority.LOW);
        taskList.addTask(taskToUpdatePriority);

        Task task = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(taskToUpdatePriority, task);
        assertEquals(TaskPriority.LOW, task.getPriority());

        taskList.updateTaskPriority(task.getId(), TaskPriority.MEDIUM);

        Task updatedTask = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(updatedTask, task);
        assertEquals(TaskPriority.MEDIUM, task.getPriority());
    }

    @Test
    @DisplayName("should update the task priority to low in task list")
    public void testUpdateTaskPriorityToLow() {
        Task taskToUpdatePriority = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdatePriority);

        Task task = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(taskToUpdatePriority, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());

        taskList.updateTaskPriority(task.getId(), TaskPriority.LOW);

        Task updatedTask = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(updatedTask, task);
        assertEquals(TaskPriority.LOW, task.getPriority());
    }

    @Test
    @DisplayName("should not update the task priority in task list passing an invalid task priority")
    public void testNotUpdateTaskPriorityWithInvalidPriority() {
        Task taskToUpdatePriority = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdatePriority);

        Task task = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(taskToUpdatePriority, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());

        taskList.updateTaskPriority(task.getId(), null);

        Task updatedTask = taskList.findTask(taskToUpdatePriority.getId());
        assertEquals(updatedTask, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }
}
