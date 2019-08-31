package com.federicovitale.snkrshub.models.services.impl;

import com.federicovitale.snkrshub.exceptions.EmailExistsException;
import com.federicovitale.snkrshub.exceptions.UsernameExistsException;
import com.federicovitale.snkrshub.models.entities.User;
import com.federicovitale.snkrshub.models.entities.UserPreference;
import com.federicovitale.snkrshub.models.repos.UserRepository;
import com.federicovitale.snkrshub.models.services.UserPreferenceService;
import com.federicovitale.snkrshub.models.services.UserService;
import com.federicovitale.snkrshub.utils.enums.UserStatusError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPreferenceServiceImpl userPreferenceService;

    @Override
    public Optional<User> verify(String verificationToken) {
        return findByEmailVerificationToken(verificationToken).map(user -> {
            user.setActive(true);
            user.setVerified(true);
            user.setEmailVerificationToken(null);
            return userRepository.save(user);
        });
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    @Override
    public Optional<User> findByEmailVerificationToken(String verificationToken) {
        return userRepository.findByEmailVerificationToken(verificationToken);
    }

    @Override
    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userRepository.findByUsername(userDetails.getUsername());
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) throws UsernameExistsException, EmailExistsException {
        if ( user.getId() == null ) {
            if ( userRepository.existsByUsername(user.getUsername()) ) {
                throw new UsernameExistsException(String.format("A user with the username: '%s' is already registered", user.getUsername()));
            }

            if ( userRepository.existsByEmail(user.getUsername()) ) {
                throw new EmailExistsException(String.format("A user with the email: '%s' is already registered", user.getEmail()));
            }

            user = userRepository.save(user);

            UserPreference userPreference = new UserPreference(user);
            userPreferenceService.save(userPreference);

            return user;
        }

        user = userRepository.save(user);

        return user;
    }

    @Override
    public List<User> saveAll(Iterable<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id).ifPresent(userPreferenceService::deleteByUser);
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userPreferenceService.findByUser(user).ifPresent(userPreferenceService::delete);
        userRepository.delete(user);
    }

    @Override
    public void deleteAll(Iterable<User> users) {
        users.forEach(userPreferenceService::deleteByUser);
        userRepository.deleteAll(users);
    }

    @Override
    public Optional<User> findByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public User softDelete(User user) {
        user.setDeleted(true);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> softDeleteById(Long id) {
        return userRepository.findById(id).map(user -> {
            user.setDeleted(true);
            return userRepository.save(user);
        });
    }
}
