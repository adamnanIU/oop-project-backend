package oop.project.chatroom.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String messageContent;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User messageOwner;


    public String getMessageContent() {
        return messageContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public User getMessageOwner() {
        return messageOwner;
    }

    public void setMessageOwner(User messageOwner) {
        this.messageOwner = messageOwner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
