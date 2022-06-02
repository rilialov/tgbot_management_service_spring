package tgbot.management_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tgbot.management_service.entity.Report;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportRepositoryIT {

    @Autowired
    ReportRepository reportRepository;

    @Test
    void findByID() {
        Optional<Report> optional = reportRepository.findById(1L);

        assertTrue(optional.isPresent());
        assertEquals(864000L, optional.get().getFullTime());
    }

    @Test
    void findAll() {
        Iterable<Report> iterable = reportRepository.findAll();
        List<Report> reportList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

        assertNotNull(reportList);
        assertTrue(reportList.size() > 0);
    }

    @Test
    void save() {
        Report report = new Report(LocalDate.now(), 1L);

        Report saved = reportRepository.save(report);

        Optional<Report> optional = reportRepository.findById(saved.getId());
        assertTrue(optional.isPresent());
        assertEquals(1L, optional.get().getUser());
    }

    @Test
    void deleteById() {
        reportRepository.deleteById(10L);

        Optional<Report> deleted = reportRepository.findById(10L);
        assertFalse(deleted.isPresent());
    }
}