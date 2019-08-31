package com.federicovitale.snkrshub.models.services.impl;

import com.federicovitale.snkrshub.models.entities.User;
import com.federicovitale.snkrshub.models.entities.UserPreference;
import com.federicovitale.snkrshub.models.repos.UserPreferenceRepository;
import com.federicovitale.snkrshub.models.services.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Override
    public void deleteByUser(User user) {
        userPreferenceRepository.deleteByUser(user);
    }

    @Override
    public Optional<UserPreference> findById(Long id) {
        return userPreferenceRepository.findById(id);
    }

    @Override
    public Optional<UserPreference> findByUser(User user) {
        return userPreferenceRepository.findByUser(user);
    }

    @Override
    public UserPreference save(UserPreference userPreference) {
        return userPreferenceRepository.save(userPreference);
    }

    @Override
    public void delete(UserPreference userPreference) {
        userPreferenceRepository.delete(userPreference);
    }

    @Override
    public void deleteById(Long id) {
        userPreferenceRepository.deleteById(id);
    }
}
