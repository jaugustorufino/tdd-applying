package functionalTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskList;
import org.taskmanager.TaskPriority;

import java.time.LocalDate;
import java.util.UUID;

public class TaskListEquivalencePartitioningTest {

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
    public void testNotAddTaskWithInvalidProps() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(null, "Description", LocalDate.now(), TaskPriority.HIGH);
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
    public void testNotUpdateTaskWithInvalidProps() {
        Task taskToUpdate = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdate);

        Task updatedTask = new Task(null, "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        Task task = taskList.updateTask(taskToUpdate.getId(), updatedTask);

        assertEquals(task.getId(), taskToUpdate.getId());
        assertEquals(task.getTitle(), taskToUpdate.getTitle());
        assertEquals(task.getDescription(), taskToUpdate.getDescription());
        assertEquals(task.getDueDate(), taskToUpdate.getDueDate());
        assertEquals(task.getPriority(), taskToUpdate.getPriority());
    }

    @Test
    public void testThrowErrorUpdateTaskWithInvalidTaskId() {
        Task updatedTask = new Task("New Title", "New Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);

        assertThrows(IllegalArgumentException.class, () -> {
            UUID invalidTaskId = UUID.randomUUID();
            taskList.updateTask(invalidTaskId, updatedTask);
        });
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
    public void testDeleteTaskByInvalidId() {
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
