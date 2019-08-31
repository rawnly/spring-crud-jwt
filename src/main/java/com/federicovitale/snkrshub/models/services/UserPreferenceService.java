package com.federicovitale.snkrshub.models.services;

import com.federicovitale.snkrshub.models.entities.User;
import com.federicovitale.snkrshub.models.entities.UserPreference;

import java.util.Optional;

public interface UserPreferenceService {
    Optional<UserPreference> findById(Long id);
    Optional<UserPreference> findByUser(User user);

    UserPreference save(UserPreference userPreference);

    void deleteByUser(User user);
    void delete(UserPreference userPreference);
    void deleteById(Long id);
}
