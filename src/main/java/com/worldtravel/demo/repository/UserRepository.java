package com.worldtravel.demo.repository;

import com.worldtravel.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String userEmail);
    User findUserByEmail(String userEmail);
}
