package hibernate;

import javax.persistence.*;
import java.util.DoubleSummaryStatistics;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 15/04/2014
 * Time: 10:35 PM
 */
@Entity
@Table(name = "EVENTS", schema = "PUBLIC", catalog = "PUBLIC")
public class EventsEntity {
    private int id;
    private String title;
    private String url;
    private String clazz;
    private Long start;
    private Long end;
     private Float x;
    private Float y;

    @Basic
    @Column(name = "X", nullable = true, insertable = true, updatable = true, length = 100)
    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }


    @Basic
    @Column(name = "Y", nullable = true, insertable = true, updatable = true, length = 100)
    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    private UsersEntity user;

    public EventsEntity() {
    }

    public EventsEntity(String title, String url, String clazz, Long start, Long end, UsersEntity user) {

        this.title = title;
        this.url = url;
        this.clazz = clazz;
        this.start = start;
        this.end = end;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_ID", nullable = false, insertable = true, updatable = true, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "URL", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "CLASS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Basic
    @Column(name = "START", nullable = true, insertable = true, updatable = true)
    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Basic
    @Column(name = "END", nullable = true, insertable = true, updatable = true)
    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsEntity that = (EventsEntity) o;

        if (id != that.id) return false;
        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UsersEntity.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "EVENTS_USERS", joinColumns = {@JoinColumn(name = "EVENT_ID", referencedColumnName = "event_id")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "user_id")})

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventsEntity{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", clazz='").append(clazz).append('\'');
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
