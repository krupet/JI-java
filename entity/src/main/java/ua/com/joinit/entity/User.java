package ua.com.joinit.entity;


import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by krupet on 03.02.2015.
 */
@Entity
@Table(name = "User")
public class User implements Serializable{

    @Id
    @Column(name = "Id", unique = true)
    @GeneratedValue
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
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

    public User(Long id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public User(Long id, String name, String nickName) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (nickName != null ? !nickName.equals(user.nickName) : user.nickName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        return result;
    }
}
