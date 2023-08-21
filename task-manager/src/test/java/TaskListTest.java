import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskList;
import org.taskmanager.TaskPriority;

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

        Task otherTask = new Task("Other Title", "Other Description", "20/08/2023", "Other Priority");
        taskList.addTask(otherTask);

        assertEquals(taskList.findTask(otherTask.getId()), otherTask);
    }

    @Test
    public void testUpdateTaskById() {
        Task taskToUpdate = new Task("Title", "Description", "19/02/2023", "Priority");
        taskList.addTask(taskToUpdate);

        Task updatedTask = new Task("Other Title", "Other Description", "20/08/2023", "Other Priority");
        Task task = taskList.updateTask(taskToUpdate.getId(), updatedTask);

        assertEquals(task.getId(), taskToUpdate.getId());
        assertEquals(task.getTitle(), updatedTask.getTitle());
        assertEquals(task.getDescription(), updatedTask.getDescription());
        assertEquals(task.getDueDate(), updatedTask.getDueDate());
        assertEquals(task.getPriority(), updatedTask.getPriority());

        assertEquals(taskList.findTask(taskToUpdate.getId()), task);
    }

    @Test
    public void testDeleteTaskById() {
        Task taskToDelete = new Task("Title", "Description", "19/02/2023", "Priority");
        taskList.addTask(taskToDelete);

        Task task = taskList.deleteTask(taskToDelete.getId());
        assertEquals(taskToDelete, task);

        assertNull(taskList.findTask(taskToDelete.getId()));
    }

    @Test
    public void testUpdateTaskPriorityById() {
        Task highTask = new Task("Title", "Description", "19/02/2023", TaskPriority.HIGH);
        taskList.addTask(highTask);

        Task task = taskList.findTask(highTask.getId());
        assertEquals(highTask, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());

        taskList.updateTaskPriority(task.getId(), TaskPriority.LOW);

        Task updatedTask = taskList.findTask(highTask.getId());
        assertEquals(updatedTask, task);
        assertEquals(TaskPriority.LOW, task.getPriority());
    }
}
