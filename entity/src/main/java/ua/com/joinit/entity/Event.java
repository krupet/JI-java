package ua.com.joinit.entity;

import javax.persistence.*;

/**
 * Created by krupet on 03.02.2015.
 */
public class Event {
    @Id
    @GeneratedValue
    public Long id;

    public String description;
    public String date;

    public Event() {
    }

    public Event(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
