package com.boot.pp25.repository;

import com.boot.pp25.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String userEmail);

    User findUserByUserName(String userName);
}
