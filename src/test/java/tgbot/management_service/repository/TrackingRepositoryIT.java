package tgbot.management_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tgbot.management_service.entity.Tracking;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrackingRepositoryIT {

    @Autowired
    TrackingRepository trackingRepository;

    @Test
    void findByID() {
        Optional<Tracking> optional = trackingRepository.findById(7L);

        assertTrue(optional.isPresent());
        assertEquals("Creating notifier", optional.get().getTrackingNote());
    }

    @Test
    void findAll() {
        Iterable<Tracking> iterable = trackingRepository.findAll();
        List<Tracking> trackingList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

        assertNotNull(trackingList);
        assertTrue(trackingList.size() > 0);
    }

    @Test
    void save() {
        Optional<Tracking> optional = trackingRepository.findById(1L);

        Tracking tracking = optional.get();
        tracking.setTrackingNote("Test tracking");
        trackingRepository.save(tracking);

        Optional<Tracking> changed = trackingRepository.findById(1L);
        assertTrue(changed.isPresent());
        assertEquals("Test tracking", changed.get().getTrackingNote());
    }

    @Test
    void deleteById() {
        trackingRepository.deleteById(5L);

        Optional<Tracking> deleted = trackingRepository.findById(5L);
        assertFalse(deleted.isPresent());
    }
}