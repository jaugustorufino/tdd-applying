package junit5Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskPriority;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task")
public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
    }

    @Test
    @DisplayName("should create a task")
    public void testTaskCreation() {
        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals(LocalDate.now(), task.getDueDate());
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }

    @Test
    @DisplayName("should update each task attributes")
    public void testTaskUpdateBySetters() {
        task.setTitle("New Title");
        task.setDescription("New Description");
        task.setDueDate(LocalDate.now().plusDays(1));
        task.setPriority(TaskPriority.LOW);

        assertAll("task properties", () -> {
            assertEquals("New Title", task.getTitle());
            assertEquals("New Description", task.getDescription());
            assertEquals(LocalDate.now().plusDays(1), task.getDueDate());
            assertEquals(TaskPriority.LOW, task.getPriority());
        });
    }

    @Test
    @DisplayName("should update all the task attributes")
    public void testTaskUpdateAll() {
        Task newTask = new Task("New Title", "New Description", LocalDate.now().plusDays(1), TaskPriority.LOW);
        task.updateAll(newTask);

        assertAll("task properties", () -> {
            assertEquals("New Title", task.getTitle());
            assertEquals("New Description", task.getDescription());
            assertEquals(LocalDate.now().plusDays(1), task.getDueDate());
            assertEquals(TaskPriority.LOW, task.getPriority());
        });
    }

    @Test
    @DisplayName("should create tasks with unique ids")
    public void testTaskUniqueness() {
        Task otherTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        assertNotEquals(task.getId(), otherTask.getId());
    }

    @Test
    @DisplayName("tasks should be the same when they have the same id")
    public void testTaskEquals() {
        Task otherTask = new Task(task.getId(), "Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        assertEquals(task, otherTask);

        Task differentTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        assertNotEquals(task, differentTask);
    }

    @Test
    @DisplayName("tasks should be the same when they have the same hashcode")
    public void testTaskHashCode() {
        Task otherTask = new Task(task.getId(), "Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        assertEquals(task.hashCode(), otherTask.hashCode());

        Task differentTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        assertNotEquals(task.hashCode(), differentTask.hashCode());
    }

    @Test
    @DisplayName("should return correct task properties in toString")
    public void testToString() {
        String expectedStringTask = "Task #" + task.getId() + ": " +
                "\nTitle: " + task.getTitle() +
                "\nDescription: " + task.getDescription() +
                "\nDue Date: " + task.getDueDate() +
                "\nPriority: " + task.getPriority();

        assertEquals(expectedStringTask, task.toString());

        Task otherTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        String expectedStringOtherTask = "Task #" + otherTask.getId() + ": " +
                "\nTitle: " + otherTask.getTitle() +
                "\nDescription: " + otherTask.getDescription() +
                "\nDue Date: " + otherTask.getDueDate() +
                "\nPriority: " + otherTask.getPriority();

        assertEquals(expectedStringOtherTask, otherTask.toString());
    }
}
