package tgbot.management_service.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tgbot.management_service.entity.Report;
import tgbot.management_service.repository.ReportRepository;

import java.util.Optional;

@Tag(name = "Report", description = "The Report API")
@RestController
@RequestMapping("/api/v1/reports")
class ReportsController {

    private final ReportRepository reportRepository;

    private final ReportsModelAssembler assembler;

    ReportsController(ReportRepository reportRepository, ReportsModelAssembler assembler) {
        this.reportRepository = reportRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Gets all reports")
    @GetMapping
    public Page<Report> findAll(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Operation(summary = "Gets report by id")
    @GetMapping(value = "/{id}")
    EntityModel<Report> findById(@PathVariable("id") Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("report", id));
        return assembler.toModel(report);
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
