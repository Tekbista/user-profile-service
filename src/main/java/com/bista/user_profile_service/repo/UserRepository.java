package com.bista.user_profile_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bista.user_profile_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByKeycloakId(String keycloakId);

}
