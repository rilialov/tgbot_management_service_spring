package tgbot.management_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tgbot.management_service.entity.Tracking;
import tgbot.management_service.repository.TrackingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Tracking", description = "The Tracking API")
@RestController
@RequestMapping("/trackings")
public class TrackingsController {

    private final TrackingRepository trackingRepository;

    public TrackingsController(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    @Operation(summary = "Gets all trackings")
    @GetMapping
    public List<Tracking> findAll() {
        Iterable<Tracking> iterable = trackingRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Gets tracking by id")
    @GetMapping(value = "/{id}")
    public Tracking findById(@PathVariable("id") Long id) {
        return trackingRepository.findById(id)
                .orElseThrow(() -> new TrackingNotFoundException(id));
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
