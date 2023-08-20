import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.taskmanager.TaskList;

public class TaskListTest {

    @Test
    public void testTaskListCreation() {
        TaskList taskList = new TaskList();
        assertNotNull(taskList);
    }

    @Test
    public void testEmptyTaskListOnCreation() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }
}
