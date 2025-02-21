package com.example.kito.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.kito.model.security.Role;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}