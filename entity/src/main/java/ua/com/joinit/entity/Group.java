package ua.com.joinit.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krupet on 03.02.2015.
 */
@Entity
@Table(name = "groups_of_users")
public class Group {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "group_name")
    private String name;

    @Column(name = "group_desc")
    private String description;

    @Column(name = "group_creation_date")
    private long creationDate;

    @ManyToMany(targetEntity = User.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "group_users",
            joinColumns = { @JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users;

    @ManyToMany(targetEntity = Event.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "group_events",
            joinColumns = { @JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private Set<Event> events;

    public Group() {
    }

    //just for tests
    public Group(String name, String description, long creationDate, Set<User> users) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.users = users;
    }

    //just for tests
    public Group(String name, String description, long creationDate, Set<User> users, Set<Event> events) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.users = users;
        this.events = events;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (creationDate != group.creationDate) return false;
        if (id != group.id) return false;
        if (description != null ? !description.equals(group.description) : group.description != null) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (users != null ? !users.equals(group.users) : group.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (creationDate ^ (creationDate >>> 32));
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}
