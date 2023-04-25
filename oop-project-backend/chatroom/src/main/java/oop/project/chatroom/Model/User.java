package oop.project.chatroom.Model;

import jakarta.persistence.*;
import oop.project.chatroom.Patterns.MessengerState;
import oop.project.chatroom.Patterns.StateContext;
import oop.project.chatroom.Patterns.UserState;

import java.util.List;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String phoneNumber;
    private boolean admin;


    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @OneToMany(mappedBy = "messageOwner", cascade = CascadeType.ALL)
    private List<Message> messages;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
