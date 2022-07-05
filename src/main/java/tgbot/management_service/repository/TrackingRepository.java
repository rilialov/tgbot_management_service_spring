package tgbot.management_service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tgbot.management_service.entity.Tracking;

public interface TrackingRepository extends PagingAndSortingRepository<Tracking, Long> {
}
