package com.caveofprogramming.spring.web.dao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class User {

    @NotBlank(message = "${NotBlank.user.username}")
    @Size(min = 5, max = 15, message = "${Size.user.username}")
    @Pattern(regexp = "^\\w{5,}$", message = "${Pattern.user.username}")
    private String username;
    private String authority;

    @NotBlank(message = "${NotBlank.user.name}")
    @Size(min = 5, max = 20, message = "${Size.user.name}")
    private String name;

    private boolean enabled;

    @NotBlank(message = "${NotBlank.user.email}")
    @Email(message = "${Email.user.email}")
    private String email;

    @NotBlank(message = "${Password cannot be blank.}")
    @Pattern(regexp = "^\\S+$", message = "${Pattern.user.password}")
    @Size(min = 5, max = 80, message = "${Size.user.password }")
    private String password;


    public User() {

    }

    public User(String username, String authority, String name, boolean enabled, String email, String password) {
        this.username = username;
        this.authority = authority;
        this.name = name;
        this.enabled = enabled;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(username, user.username) &&
                Objects.equals(authority, user.authority) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authority, name, enabled, email);
    }
}
