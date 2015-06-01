package ua.com.joinit.entity;

import javax.persistence.*;

/**
 * Created by krupet on 03.02.2015.
 */
@Entity
//@Table(name = "Event") // Linux
@Table(name = "event") // Win problems with encoding in mysql
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

        if (creationDate != event.creationDate) return false;
        if (eventDate != event.eventDate) return false;
        if (id != event.id) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (eventName != null ? !eventName.equals(event.eventName) : event.eventName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (eventDate ^ (eventDate >>> 32));
        result = 31 * result + (int) (creationDate ^ (creationDate >>> 32));
        return result;
    }
}
