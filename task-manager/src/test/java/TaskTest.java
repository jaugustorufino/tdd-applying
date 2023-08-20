import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("Title", "Description", "19/02/2023", "Priority");

        assertNotNull(task);

        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals("19/02/2023", task.getDueDate());
        assertEquals("Priority", task.getPriority());
    }

    @Test
    public void testTaskUpdate() {
        Task task = new Task("Title", "Description", "19/02/2023", "Priority");

        task.setTitle("New Title");
        task.setDescription("New Description");
        task.setDueDate("29/05/2023");
        task.setPriority("New Priority");

        assertEquals("New Title", task.getTitle());
        assertEquals("New Description", task.getDescription());
        assertEquals("29/05/2023", task.getDueDate());
        assertEquals("New Priority", task.getPriority());
    }
}
