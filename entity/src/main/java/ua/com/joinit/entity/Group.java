package ua.com.joinit.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krupet on 03.02.2015.
 */
public class Group {
    @Id
    @GeneratedValue
    public Long id;

    public String name;
    public String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<User> members;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Event> events;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public void addUserToGroup(User user){
        if(members == null) members = new HashSet<User>();
        members.add(user);
    }

    public void addEvent(Event event){
        if(events == null) events = new HashSet<Event>();
        events.add(event);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getMembers() {
        return members;
    }

    public Set<Event> getEvents() {
        return events;
    }
}
