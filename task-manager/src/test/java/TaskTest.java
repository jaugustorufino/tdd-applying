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
}
