package ua.com.joinit.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krupet on 03.02.2015.
 */
public class User {

    @Id
    @GeneratedValue
    public Long id;

    public String name;
    public String nickName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Event> userEvents;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Group> userGroups;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public void addGroup (Group group){
        if (userGroups == null) userGroups = new HashSet<Group>();
        userGroups.add(group);
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

    public Set<Event> getUserEvents() {
        return userEvents;
    }

    public Set<Group> getUserGroups() {
        return userGroups;
    }
}
