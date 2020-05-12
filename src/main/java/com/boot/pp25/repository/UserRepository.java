package com.boot.pp25.repository;

import com.boot.pp25.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository{
    List<User> getAllUsers();

    boolean addUser(User user);

    void delete(Long id);

    void editUser(User user);

    User getUserById(Long id);

    User getUserByName(String s);

    User getUserByEmail(String email);
}
