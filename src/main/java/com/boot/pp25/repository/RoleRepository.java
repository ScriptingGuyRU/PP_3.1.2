package com.boot.pp25.repository;

import com.boot.pp25.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository {
    Role getRole(String name);
}
