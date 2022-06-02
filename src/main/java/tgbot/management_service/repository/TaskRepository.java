package tgbot.management_service.repository;

import org.springframework.data.repository.CrudRepository;
import tgbot.management_service.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
