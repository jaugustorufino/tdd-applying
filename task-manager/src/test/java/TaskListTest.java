import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testTaskListCreation() {
        TaskList taskList = new TaskList();
        Assertions.assertNotNull(taskList);
    }

}
