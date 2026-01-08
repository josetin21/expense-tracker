package com.josetin.expense_tracker.repo;

import com.josetin.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUsername (String username);
    boolean existsByEmail (String email);

    Optional<User> findByUsername (String username);
}
