import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task();
        Assertions.assertNotNull(task);
    }
}
