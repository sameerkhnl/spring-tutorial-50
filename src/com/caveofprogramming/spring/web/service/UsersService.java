package com.caveofprogramming.spring.web.service;

import java.util.List;

import com.caveofprogramming.spring.web.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("usersService")
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private MessagesDao messagesDao;

    @Secured("ROLE_ADMIN")
    public List<User> getUsers() {
        return usersDao.getUsers();
    }

    public void createUser(User user) {
        usersDao.create(user);
    }

    public User getUser(String username) {
        User user =  usersDao.getUser(username);
        return user;
    }

    public boolean exists(String username) {
        return usersDao.exists(username);
    }

    public void sendMessage(Message message) {
        messagesDao.saveOrUpdate(message);
    }

    public List<Message> getMessages(String username) {
        return messagesDao.getMessages(username);
    }
}