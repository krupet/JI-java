package ua.com.joinit.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krupet on 03.02.2015.
 */
@Entity
@Table(name = "User")
public class User implements Serializable{

    @Id
    @Column(name = "Id")
    @GeneratedValue
    public Long id;

    @Column(name = "Name")
    public String name;

    @Column(name = "NickName")
    public String nickName;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public Set<Event> userEvents;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public Set<Group> userGroups;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
