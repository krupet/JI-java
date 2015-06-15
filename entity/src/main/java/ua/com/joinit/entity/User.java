package ua.com.joinit.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by krupet on 03.02.2015.
 */
@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @Column(name = "user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_f_name")
    private String firstName;

    @Column(name = "user_l_name")
    private String lastName;

    @Column(name = "user_nick")
    private String nickName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone_number")
    private long phone;

    @Column(name = "user_desc")
    private String aboutYourself;

    @ManyToMany(targetEntity = Event.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "event_users",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private Set<Event> events;

    @ManyToMany(targetEntity = Group.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "group_users",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<Group> groups;

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", aboutYourself='" + aboutYourself + '\'' +
                '}';
    }

    public User() {
    }

    // just for tests
    public User(String firstName, String lastName, String nickName,
                String email, long phone, String aboutYourself) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;
        this.aboutYourself = aboutYourself;
    }

    public User(String firstName, String lastName, String nickName, String email, long phone,
                String aboutYourself, Set<Event> events, Set<Group> groups) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;
        this.aboutYourself = aboutYourself;
        this.events = events;
        this.groups = groups;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (phone != user.phone) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!nickName.equals(user.nickName)) return false;
        if (!email.equals(user.email)) return false;
        if (!aboutYourself.equals(user.aboutYourself)) return false;
        if (events != null ? !events.equals(user.events) : user.events != null) return false;
        return !(groups != null ? !groups.equals(user.groups) : user.groups != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + nickName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (int) (phone ^ (phone >>> 32));
        result = 31 * result + aboutYourself.hashCode();
        result = 31 * result + (events != null ? events.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }
}
