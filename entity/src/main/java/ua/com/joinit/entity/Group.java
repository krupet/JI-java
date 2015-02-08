package ua.com.joinit.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krupet on 03.02.2015.
 */
@Entity
@Table(name = "Group")
public class Group {
    @Id
    @Column(name = "Id")
    @GeneratedValue
    public Long id;

    @Column(name = "GroupName")
    public String name;

    @Column(name = "Description")
    public String description;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public Set<User> members;
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public Set<Event> events;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
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
}
