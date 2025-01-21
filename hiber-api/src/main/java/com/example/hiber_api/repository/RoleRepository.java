package com.example.hiber_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hiber_api.model.security.Role;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}