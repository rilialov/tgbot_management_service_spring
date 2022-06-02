package tgbot.management_service.repository;

import org.springframework.data.repository.CrudRepository;
import tgbot.management_service.entity.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {
}
