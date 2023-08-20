import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskList;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testTaskListCreation() {
        assertNotNull(taskList);
    }

    @Test
    public void testEmptyTaskListOnCreation() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Title", "Description", "19/02/2023", "Priority");
        taskList.addTask(task);

        assertEquals(1, taskList.size());
    }

    @Test
    public void testFindTask() {
        Task task = new Task("Title", "Description", "19/02/2023", "Priority");
        taskList.addTask(task);

        assertEquals(taskList.findTask(task.getId()), task);
    }
}
