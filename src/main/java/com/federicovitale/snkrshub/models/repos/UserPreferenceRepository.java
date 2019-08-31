package com.federicovitale.snkrshub.models.repos;

import com.federicovitale.snkrshub.models.entities.User;
import com.federicovitale.snkrshub.models.entities.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    Optional<UserPreference> findByUser(User user);
    void deleteByUser(User user);
}
