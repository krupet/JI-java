package ua.com.joinit.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by krupet on 03.02.2015.
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_desc")
    private String description;

    @Column(name = "event_date")
    private long eventDate;

    @Column(name = "event_creation_date")
    private long creationDate;

    public Event() {
    }

    // just for tests
    public Event(String eventName, String description, long eventDate, long creationDate) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                ", creationDate=" + creationDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (eventDate != event.eventDate) return false;
        if (creationDate != event.creationDate) return false;
        if (!eventName.equals(event.eventName)) return false;
        return description.equals(event.description);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + eventName.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (int) (eventDate ^ (eventDate >>> 32));
        result = 31 * result + (int) (creationDate ^ (creationDate >>> 32));
        return result;
    }
}
