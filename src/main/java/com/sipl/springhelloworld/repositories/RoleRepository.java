package com.sipl.springhelloworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sipl.springhelloworld.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
