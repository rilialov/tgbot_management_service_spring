package tgbot.management_service.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "trackings")
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "tracking_note", nullable = false)
    private String trackingNote;

    @ManyToOne
    @JoinColumn(name="task")
    private Task task;

    @Column(name = "\"user\"")
    private long user;

    public Tracking() {
    }

    public Tracking(String trackingNote, Task task, long user) {
        this.startTime = LocalDateTime.now();
        this.trackingNote = trackingNote;
        this.task = task;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTrackingNote() {
        return trackingNote;
    }

    public void setTrackingNote(String trackingNote) {
        this.trackingNote = trackingNote;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracking tracking = (Tracking) o;
        return id == tracking.id && startTime.equals(tracking.startTime) && trackingNote.equals(tracking.trackingNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, trackingNote);
    }
}
