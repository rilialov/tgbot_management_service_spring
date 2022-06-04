package tgbot.management_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tgbot.management_service.entity.Task;
import tgbot.management_service.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/tasks")
class TasksController {

    private final TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> findAll() {
        Iterable<Task> iterable = taskRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Task findById(@PathVariable("id") Long id) {
        Task task = new Task();
        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isPresent()) {
            task = optional.get();
        }
        return task;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task resource) {
        Task task = new Task();
        if (resource != null) {
            task = taskRepository.save(resource);
        }
        return task;
    }

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

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        if (id != null) {
            taskRepository.deleteById(id);
        }
    }

}
