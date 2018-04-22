package com.caveofprogramming.spring.web.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.caveofprogramming.spring.web.validation.ValidEmail;

import java.util.Objects;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Size(min=20, max=255, message="${Size.offer.text}", groups = {FormValidationGroup.class})
    private String text;

    @ManyToOne
    @JoinColumn(name = "username")
	private User user;

    public Offer(int id, User user, String text) {
        this.id = id;
        this.user = user;
        this.text = text;
    }

    public Offer(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public Offer() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(text, offer.text) &&
                Objects.equals(user, offer.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text, user);
    }
}
