package hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 02/04/2014
 * Time: 09:00 PM
 */
@Entity
@Table(name = "USERS", schema = "PUBLIC", catalog = "PUBLIC")
public class UsersEntity {


    private int id;
    private String user;
    private String pass;
    private String email;
    private Set<EventsEntity> events = new HashSet<EventsEntity>();


    public UsersEntity(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public UsersEntity() {
    }

    public UsersEntity(String user, String pass, String email) {
        this.user = user;
        this.pass = pass;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "PASS", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, insertable = true, updatable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = EventsEntity.class, mappedBy = "user", cascade = CascadeType.ALL)
    public Set<EventsEntity> getEvents() {
        return events;
    }

    public void setEvents(Set<EventsEntity> events) {
        this.events = events;
    }
}
