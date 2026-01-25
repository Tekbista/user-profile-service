package com.bista.user_profile_service.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.UserRepresentation;
import com.bista.user_profile_service.entity.User;
import com.bista.user_profile_service.exception.BadRequestException;
import com.bista.user_profile_service.exception.ResourceNotFoundException;
import com.bista.user_profile_service.repo.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final KeycloakUserService keycloakUserService;

    public UserService(UserRepository userRepository, KeycloakUserService keycloakUserService) {
        this.userRepository = userRepository;
        this.keycloakUserService = keycloakUserService;
    }

    public User findByKeycloakId(String keycloakId) {
        User user = userRepository.findByKeycloakId(keycloakId);
        if (user != null) {
            return user;
        } else {
            throw new ResourceNotFoundException("User not found with Keycloak ID: " + keycloakId);
        }
    }

    public User saveUser(User user, Jwt jwt) {
        String keycloakId = jwt.getSubject();

        UserRepresentation kcUser = keycloakUserService.getUser(keycloakId);
        if (user == null) {
            throw new BadRequestException("User cannot be null");
        }
        user.setKeycloakId(keycloakId);
        user.setEmail(kcUser.getEmail());
        user.setFirstName(kcUser.getFirstName());
        user.setLastName(kcUser.getLastName());
        user.setUsername(kcUser.getUsername());

        return user;
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public User updateUser(String keycloakId, User updatedUser) {
        User user = findByKeycloakId(keycloakId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with Keycloak ID: " + keycloakId);
        }
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setAddresses(updatedUser.getAddresses());
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
