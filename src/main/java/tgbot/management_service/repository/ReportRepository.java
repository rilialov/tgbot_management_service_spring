package tgbot.management_service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tgbot.management_service.entity.Report;

public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {
}
