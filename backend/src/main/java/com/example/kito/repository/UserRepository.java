package com.example.kito.repository;


import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.kito.dto.UserDTO;
import com.example.kito.model.security.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<UserDTO> findBy();

    @Procedure
    void deleteUser(Long id);

}
