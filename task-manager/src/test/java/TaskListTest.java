import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskList;
import org.taskmanager.TaskPriority;

import java.time.LocalDate;

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
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(1, taskList.size());
    }

    @Test
    public void testFindTask() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(taskList.findTask(task.getId()), task);

        Task otherTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(otherTask);

        assertEquals(taskList.findTask(otherTask.getId()), otherTask);
    }

    @Test
    public void testUpdateTaskById() {
        Task taskToUpdate = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdate);

        Task updatedTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
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
        Task taskToDelete = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToDelete);

        Task task = taskList.deleteTask(taskToDelete.getId());
        assertEquals(taskToDelete, task);

        assertNull(taskList.findTask(taskToDelete.getId()));
    }

    @Test
    public void testUpdateTaskPriorityById() {
        Task highTask = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(highTask);

        Task task = taskList.findTask(highTask.getId());
        assertEquals(highTask, task);
        assertEquals(TaskPriority.HIGH, task.getPriority());

        taskList.updateTaskPriority(task.getId(), TaskPriority.LOW);

        Task updatedTask = taskList.findTask(highTask.getId());
        assertEquals(updatedTask, task);
        assertEquals(TaskPriority.LOW, task.getPriority());
    }

    @Test
    public void testListTasksSortedByPriority() {
        Task task1 = new Task("Title1", "Description1", LocalDate.now(), TaskPriority.MEDIUM);
        Task task2 = new Task("Title2", "Description2", LocalDate.now(), TaskPriority.HIGH);
        Task task3 = new Task("Title3", "Description3", LocalDate.now(), TaskPriority.LOW);

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        String sortedTaskList = taskList.listSortedTasks();
        String expectedSortedTaskList = task2.toString() + "\n\n" + task1.toString() + "\n\n" + task3.toString();
        assertEquals(expectedSortedTaskList, sortedTaskList);
    }

    @Test
    public void testListTasksSortedByDueDate() {
        Task task1 = new Task("Title1", "Description1", LocalDate.now().plusDays(2), TaskPriority.LOW);
        Task task2 = new Task("Title2", "Description2", LocalDate.now(), TaskPriority.LOW);
        Task task3 = new Task("Title3", "Description3", LocalDate.now().plusDays(4), TaskPriority.LOW);

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        String sortedTaskList = taskList.listSortedTasks();
        String expectedSortedTaskList = task2.toString() + "\n\n" + task1.toString() + "\n\n" + task3.toString();
        assertEquals(expectedSortedTaskList, sortedTaskList);
    }

}
