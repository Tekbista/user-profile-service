package com.bista.user_profile_service.controller;

import com.bista.user_profile_service.entity.User;
import com.bista.user_profile_service.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/profile")
    public User addUserProfile(@AuthenticationPrincipal Jwt jwt, @RequestBody User user) {
        return userService.saveUser(user, jwt);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam String keycloakId) {
        User user = userService.findByKeycloakId(keycloakId);
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/profile")
    public ResponseEntity<User> updateUserProfile(@RequestParam String keycloakId, User updatedUser) {
        User user = userService.updateUser(keycloakId, updatedUser);
        return ResponseEntity.ok().body(user);
    }

}
