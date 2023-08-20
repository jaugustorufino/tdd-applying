import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("Title", "Description", "19/02/2023", "Priority");

        Assertions.assertNotNull(task);

        Assertions.assertEquals(task.getTitle(), "Title");
        Assertions.assertEquals(task.getDescription(), "Description");
        Assertions.assertEquals(task.getDueDate(), "19/02/2023");
        Assertions.assertEquals(task.getPriority(), "Priority");
    }

    @Test
    public void testTaskUpdate() {
        Task task = new Task("Title", "Description", "19/02/2023", "Priority");

        task.setTitle("New Title");
        task.setDescription("New Description");
        task.setDueDate("29/05/2023");
        task.setPriority("New Priority");

        Assertions.assertEquals(task.getTitle(), "New Title");
        Assertions.assertEquals(task.getDescription(), "New Description");
        Assertions.assertEquals(task.getDueDate(), "29/05/2023");
        Assertions.assertEquals(task.getPriority(), "New Priority");
    }
}
