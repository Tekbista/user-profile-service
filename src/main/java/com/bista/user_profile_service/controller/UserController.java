package com.bista.user_profile_service.controller;

import com.bista.user_profile_service.entity.User;
import com.bista.user_profile_service.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam String keycloakId) {
        User user = userService.findByKeycloakId(keycloakId);
        return ResponseEntity.ok().body(user);
    }

}
