import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.taskmanager.TaskList;

public class TaskListTest {

    @Test
    public void testTaskListCreation() {
        TaskList taskList = new TaskList();
        Assertions.assertNotNull(taskList);
    }
}
