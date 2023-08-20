import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task();
        Assertions.assertNotNull(task);
    }
}
