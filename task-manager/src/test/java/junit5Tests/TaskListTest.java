package junit5Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.taskmanager.Task;
import org.taskmanager.TaskList;
import org.taskmanager.TaskPriority;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task List")
public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    @DisplayName("should create a empty task list")
    public void testEmptyTaskListOnCreation() {
        assertNotNull(taskList);
        assertEquals(0, taskList.size());
    }

    @Test
    @DisplayName("should add a task in task list")
    public void testAddTask() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(1, taskList.size());
    }

    @Test
    @DisplayName("should find a task in task list")
    public void testFindTask() {
        Task task = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(task);

        assertEquals(taskList.findTask(task.getId()), task);

        Task otherTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        taskList.addTask(otherTask);

        assertEquals(taskList.findTask(otherTask.getId()), otherTask);
    }

    @Test
    @DisplayName("should update a task by id in task list")
    public void testUpdateTaskById() {
        Task taskToUpdate = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToUpdate);

        Task updatedTask = new Task("Other Title", "Other Description", LocalDate.now().plusDays(1), TaskPriority.MEDIUM);
        Task task = taskList.updateTask(taskToUpdate.getId(), updatedTask);

        assertAll("task properties", () -> {
            assertEquals(task.getId(), taskToUpdate.getId());
            assertEquals(task.getTitle(), updatedTask.getTitle());
            assertEquals(task.getDescription(), updatedTask.getDescription());
            assertEquals(task.getDueDate(), updatedTask.getDueDate());
            assertEquals(task.getPriority(), updatedTask.getPriority());

            assertEquals(taskList.findTask(taskToUpdate.getId()), task);
        });
    }

    @Test
    @DisplayName("should delete a task by id in task list")
    public void testDeleteTaskById() {
        Task taskToDelete = new Task("Title", "Description", LocalDate.now(), TaskPriority.HIGH);
        taskList.addTask(taskToDelete);

        Task task = taskList.deleteTask(taskToDelete.getId());
        assertEquals(taskToDelete, task);

        assertNull(taskList.findTask(taskToDelete.getId()));
    }

    @Test
    @DisplayName("should update the task priority by id in task list")
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
    @DisplayName("should list the tasks sorted by priority")
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
    @DisplayName("should list the tasks sorted by due date")
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

    @Test
    @DisplayName("should list the tasks sorted by priority and due date")
    public void testListTasksSortedByPriorityAndDueDate() {
        Task task1 = new Task("Title1", "Description1", LocalDate.now().plusDays(2), TaskPriority.LOW);
        Task task2 = new Task("Title2", "Description2", LocalDate.now().plusDays(5), TaskPriority.MEDIUM);
        Task task3 = new Task("Title3", "Description3", LocalDate.now(), TaskPriority.HIGH);
        Task task4 = new Task("Title4", "Description4", LocalDate.now().plusDays(4), TaskPriority.HIGH);

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.addTask(task4);

        String sortedTaskList = taskList.listSortedTasks();
        String expectedSortedTaskList = task3.toString() + "\n\n" + task4.toString() + "\n\n" + task2.toString() + "\n\n" + task1.toString();
        assertEquals(expectedSortedTaskList, sortedTaskList);
    }
}
