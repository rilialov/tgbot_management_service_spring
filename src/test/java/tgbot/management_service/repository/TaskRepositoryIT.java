package tgbot.management_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tgbot.management_service.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryIT {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findByID() {
        Optional<Task> optional = taskRepository.findById(1L);

        assertTrue(optional.isPresent());
        assertEquals("Create Service connected to telegram", optional.get().getTaskName());
    }

    @Test
    void findAll() {
        Iterable<Task> iterable = taskRepository.findAll();
        List<Task> taskList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

        assertNotNull(taskList);
        assertTrue(taskList.size() > 0);
    }

    @Test
    void save() {
        Task task = new Task("Test Name", "Test Note");

        Task saved = taskRepository.save(task);

        Optional<Task> optional = taskRepository.findById(saved.getId());
        assertTrue(optional.isPresent());
        assertEquals("Test Name", optional.get().getTaskName());
        assertEquals("Test Note", optional.get().getTaskNote());
    }

    @Test
    void deleteById() {
        Task task = new Task("Test Name", "Test Note");
        Task saved = taskRepository.save(task);

        taskRepository.deleteById(saved.getId());

        Optional<Task> deleted = taskRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }
}