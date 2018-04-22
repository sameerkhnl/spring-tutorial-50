package com.caveofprogramming.spring.web.dao;

import com.caveofprogramming.spring.web.validation.ValidEmail;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Validated
@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 5, max = 100)
    private String subject;
    @Size
    private String content;
    //name of the sender
    private String name;
    //email of the sender
    @NotBlank(message="Email cannot be blank")
    @Email(message = "This is not a valid email address.")
    private String email;

    @ManyToOne
    @JoinColumn(name = "username")
    //user to whom the email is being sent to
    private User user;

    public String getUsername() {
        return user.getUsername();
    }

    public Message(int id, String subject, String content, String name, String email, User user) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.name = name;
        this.email = email;
        this.user = user;
    }

    public Message(String subject, String content, String name, String email, User user) {
        this.subject = subject;
        this.content = content;
        this.name = name;
        this.email = email;
        this.user = user;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                '}';
    }
}
