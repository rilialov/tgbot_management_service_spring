package tgbot.management_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tgbot.management_service.entity.Report;
import tgbot.management_service.repository.ReportRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Report", description = "The Report API")
@RestController
@RequestMapping("/reports")
class ReportsController {

    private final ReportRepository reportRepository;

    ReportsController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Operation(summary = "Gets all reports")
    @GetMapping
    public List<Report> findAll() {
        Iterable<Report> iterable = reportRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Gets report by id")
    @GetMapping(value = "/{id}")
    public Report findById(@PathVariable("id") Long id) {
        Report report = new Report();
        Optional<Report> optional = reportRepository.findById(id);
        if (optional.isPresent()) {
            report = optional.get();
        }
        return report;
    }

    @Operation(summary = "Creates new report")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Report create(@RequestBody Report resource) {
        Report report = new Report();
        if (resource != null) {
            report = reportRepository.save(resource);
        }
        return report;
    }

    @Operation(summary = "Updates report")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Report resource) {
        Optional<Report> optional = reportRepository.findById(id);
        if (optional.isPresent() && resource != null) {
            Report report = optional.get();
            report.setDate(resource.getDate());
            report.setFullTime(resource.getFullTime());
            report.setUser(resource.getUser());
            reportRepository.save(report);
        }
    }

    @Operation(summary = "Deletes report")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        if (id != null) {
            reportRepository.deleteById(id);
        }
    }
}
