package com.bista.user_profile_service.service;

import org.springframework.stereotype.Service;

import com.bista.user_profile_service.entity.User;
import com.bista.user_profile_service.exception.ResourceNotFoundException;
import com.bista.user_profile_service.repo.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByKeycloakId(String keycloakId) {
        User user = userRepository.findByKeycloakId(keycloakId);
        if (user != null) {
            return user;
        } else {
            throw new ResourceNotFoundException("User not found with Keycloak ID: " + keycloakId);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
