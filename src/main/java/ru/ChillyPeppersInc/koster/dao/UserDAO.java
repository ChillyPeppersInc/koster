package ru.ChillyPeppersInc.koster.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ChillyPeppersInc.koster.models.User;

@Component
public class UserDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User userToBeUpdated = session.get(User.class, id);

        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurname(updatedUser.getSurname());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setPassword(String.valueOf(updatedUser.getPassword()));
    }
}
