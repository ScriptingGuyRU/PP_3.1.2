package com.boot.pp25.repository;

import com.boot.pp25.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Role getRole(String name) {
        Role role = (Role) entityManager
                .createQuery("select r from Role r where name = :name")
                .setParameter("name",name).getResultList().get(0);
        return role;
    }
}
