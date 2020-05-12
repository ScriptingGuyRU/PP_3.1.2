package com.boot.pp25.repository;

import com.boot.pp25.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("From User").getResultList();
    }

    @Override
    public boolean addUser(User user) {

//            entityManager.persist(user);  // Я не смог победить persist. С ним не работает. PersistentObjectException.
        //Из-за того, что я пытаюсь сохранить user с уже существующей ролью Role.
        Session session = entityManager.unwrap(Session.class);
        session.save(user);

        return true;
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class,id);

        return user;
    }


    @Override
    public User getUserByName(String name) {
        User user = (User) entityManager
                .createQuery("select u from User u where name = :name")
                .setParameter("name",name).getSingleResult();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = (User) entityManager
                .createQuery("from User where email = :email",User.class)
                .setParameter("email",email).getSingleResult();
        return user;
    }
}
