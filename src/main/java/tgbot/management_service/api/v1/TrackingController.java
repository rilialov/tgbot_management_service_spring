package tgbot.management_service.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tgbot.management_service.entity.Tracking;
import tgbot.management_service.repository.TrackingRepository;

import java.util.Optional;

@Tag(name = "Tracking", description = "The Tracking API")
@RestController
@RequestMapping("/api/v1/tracking")
public class TrackingController {

    private final TrackingRepository trackingRepository;

    public TrackingController(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    @Operation(summary = "Gets all tracking")
    @GetMapping
    public Page<Tracking> findAll(Pageable pageable) {
        return trackingRepository.findAll(pageable);
    }

    @Operation(summary = "Gets tracking by id")
    @GetMapping(value = "/{id}")
    public Tracking findById(@PathVariable("id") Long id) {
        return trackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tracking", id));
    }

    @Operation(summary = "Creates new tracking")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tracking create(@RequestBody Tracking resource) {
        Tracking tracking = new Tracking();
        if (resource != null) {
            tracking = trackingRepository.save(resource);
        }
        return tracking;
    }

    @Operation(summary = "Updates tracking")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Tracking resource) {
        Optional<Tracking> optional = trackingRepository.findById(id);
        if (optional.isPresent() && resource != null) {
            Tracking tracking = optional.get();
            tracking.setTrackingNote(resource.getTrackingNote());
            tracking.setUser(resource.getUser());
            tracking.setEndTime(resource.getEndTime());
            tracking.setTask(resource.getTask());
            trackingRepository.save(tracking);
        }
    }

    @Operation(summary = "Deletes tracking")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        if (id != null) {
            trackingRepository.deleteById(id);
        }
    }
}
