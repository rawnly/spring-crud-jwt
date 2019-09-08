package com.federicovitale.spring_jwt_boilerplate.models.repos;

import com.federicovitale.spring_jwt_boilerplate.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
    Optional<User> findByEmailVerificationToken(String verificationToken);
    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String username);
}
