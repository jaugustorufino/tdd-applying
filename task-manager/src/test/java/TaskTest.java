import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.taskmanager.Task;
import org.taskmanager.TaskPriority;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Title", "Description", "19/02/2023", TaskPriority.HIGH);
    }

    @Test
    public void testTaskCreation() {
        assertNotNull(task);

        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals("19/02/2023", task.getDueDate());
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }

    @Test
    public void testTaskUpdate() {
        task.setTitle("New Title");
        task.setDescription("New Description");
        task.setDueDate("29/05/2023");
        task.setPriority(TaskPriority.LOW);

        assertEquals("New Title", task.getTitle());
        assertEquals("New Description", task.getDescription());
        assertEquals("29/05/2023", task.getDueDate());
        assertEquals(TaskPriority.LOW, task.getPriority());
    }

    @Test
    public void testTaskUniqueness() {
        Task otherTask = new Task("Other Title", "Other Description", "20/08/2023", TaskPriority.MEDIUM);
        assertNotEquals(task.getId(), otherTask.getId());
    }

    @Test
    public void testTaskEquals() {
        Task otherTask = new Task(task.getId(), "Other Title", "Other Description", "20/08/2023", TaskPriority.MEDIUM);
        assertEquals(task, otherTask);

        Task differentTask = new Task("Other Title", "Other Description", "20/08/2023", TaskPriority.MEDIUM);
        assertNotEquals(task, differentTask);
    }

    @Test
    public void testTaskHashCode() {
        Task otherTask = new Task(task.getId(), "Other Title", "Other Description", "20/08/2023", TaskPriority.MEDIUM);
        assertEquals(task.hashCode(), otherTask.hashCode());

        Task differentTask = new Task("Other Title", "Other Description", "20/08/2023", TaskPriority.MEDIUM);
        assertNotEquals(task.hashCode(), differentTask.hashCode());
    }

    @Test
    public void testToString() {
        String expectedStringTask = "Task #" + task.getId() + ": " +
                "\nTitle: " + task.getTitle() +
                "\nDescription: " + task.getDescription() +
                "\nDue Date: " + task.getDueDate() +
                "\nPriority: " + task.getPriority();

        assertEquals(expectedStringTask, task.toString());

        Task otherTask = new Task("Other Title", "Other Description", "20/08/2023", TaskPriority.MEDIUM);
        String expectedStringOtherTask = "Task #" + otherTask.getId() + ": " +
                "\nTitle: " + otherTask.getTitle() +
                "\nDescription: " + otherTask.getDescription() +
                "\nDue Date: " + otherTask.getDueDate() +
                "\nPriority: " + otherTask.getPriority();

        assertEquals(expectedStringOtherTask, otherTask.toString());
    }
}
