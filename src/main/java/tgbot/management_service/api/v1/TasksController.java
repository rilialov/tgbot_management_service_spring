package tgbot.management_service.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tgbot.management_service.entity.Task;
import tgbot.management_service.repository.TaskRepository;

import java.util.Optional;

@Tag(name = "Task", description = "The Task API")
@RestController
@RequestMapping("/api/v1/tasks")
class TasksController {

    private final TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Cacheable("allTasks")
    @Operation(summary = "Gets all tasks")
    @GetMapping
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Cacheable("task")
    @Operation(summary = "Gets task by id")
    @GetMapping(value = "/{id}")
    public Task findById(@PathVariable("id") Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task", id));
    }

    @Operation(summary = "Creates new task")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task resource) {
        Task task = new Task();
        if (resource != null) {
            task = taskRepository.save(resource);
        }
        return task;
    }

    @Operation(summary = "Updates task")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Task resource) {
        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isPresent() && resource != null) {
            Task task = optional.get();
            task.setTaskName(resource.getTaskName());
            task.setTaskNote(resource.getTaskNote());
            taskRepository.save(task);
        }
    }

    @Operation(summary = "Deletes task")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        if (id != null) {
            taskRepository.deleteById(id);
        }
    }

}
