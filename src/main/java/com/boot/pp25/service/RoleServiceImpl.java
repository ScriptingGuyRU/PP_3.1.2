package com.boot.pp25.service;

import com.boot.pp25.model.Role;
import com.boot.pp25.repository.RoleRepository;
import com.boot.pp25.service.abstractServ.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleServices {

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRole(name);
    }
}
