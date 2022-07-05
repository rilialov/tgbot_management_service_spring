package tgbot.management_service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tgbot.management_service.entity.Task;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}
