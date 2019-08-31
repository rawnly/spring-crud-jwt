package com.federicovitale.snkrshub.models.services;

import com.federicovitale.snkrshub.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends CRUDService<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<User> findByResetToken(String resetToken);
    Optional<User> findByEmailVerificationToken(String verificationToken);

    Optional<User> verify(String verificationToken);

    User softDelete(User user);
    Optional<User> softDeleteById(Long id);
    Optional<User> findByUsernameOrEmail(String username, String email);


    List<User> findAll();

    User save(User user);
    List<User> saveAll(Iterable<User> users);

    void deleteById(Long id);
    void delete(User user);
    void deleteAll(Iterable<User> users);

    Optional<User> getUser();
}
