package tgbot.management_service.repository;

import org.springframework.data.repository.CrudRepository;
import tgbot.management_service.entity.Tracking;

public interface TrackingRepository extends CrudRepository<Tracking, Long> {
}
