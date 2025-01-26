package com.example.hiber_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hiber_api.dto.UserDTO;
import com.example.hiber_api.model.security.User;
import com.example.hiber_api.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        return userRepository.findBy();
    }

    public void deleteById(Long id) {     
        userRepository.deleteUser(id);
    }

    public void deleteSelf() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user;
        try {
            user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist."));
        } catch (UsernameNotFoundException e) {return;}
        
        userRepository.deleteUser(user.getId());
    }
}
