package hibernate;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 5/21/14
 * Time: 11:51 AM
 */
@Entity
@Table(name = "MESSAGES", schema = "PUBLIC", catalog = "PUBLIC")
public class MessageEntity {

    private int id;
    private String text;
    private String sender;
    private UsersEntity user;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false, insertable = true, updatable = true, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "TEXT", nullable = true, insertable = true, updatable = true, length = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Basic
    @Column(name = "SENDER", nullable = false, insertable = false, updatable = false, length = 45)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UsersEntity.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "MESSAGES_USERS", joinColumns = {@JoinColumn(name = "MESSAGE_ID", referencedColumnName = "message_id")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "user_id")})
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
