package com.boot.pp25.service;

import com.boot.pp25.model.User;
import com.boot.pp25.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService  {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Oops, this user not founded");
        }
    }
}
