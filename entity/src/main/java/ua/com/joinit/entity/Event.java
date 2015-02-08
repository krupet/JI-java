package ua.com.joinit.entity;

import javax.persistence.*;

/**
 * Created by krupet on 03.02.2015.
 */
@Entity
@Table(name = "Event")
public class Event {
    @Id
    @Column(name = "Id")
    @GeneratedValue
    public Long id;

    @Column(name = "Date")
    public String date;

    @Column(name = "Description")
    public String description;

    public Event() {
    }

    public Event(String date) {
        this.date = date;
    }

    public Event(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
