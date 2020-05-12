package com.boot.pp25.service;

import com.boot.pp25.model.User;
import com.boot.pp25.repository.UserRepository;
import com.boot.pp25.service.abstractServ.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.getUserById(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.addUser(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findUserByUserName(String s) {
        return userRepository.getUserByName(s);
    }

}
