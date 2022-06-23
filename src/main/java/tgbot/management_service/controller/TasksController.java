package tgbot.management_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tgbot.management_service.entity.Task;
import tgbot.management_service.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Task", description = "The Task API")
@RestController
@RequestMapping("/tasks")
class TasksController {

    private final TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Operation(summary = "Gets all tasks")
    @GetMapping
    public List<Task> findAll() {
        Iterable<Task> iterable = taskRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Gets task by id")
    @GetMapping(value = "/{id}")
    public Task findById(@PathVariable("id") Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
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
