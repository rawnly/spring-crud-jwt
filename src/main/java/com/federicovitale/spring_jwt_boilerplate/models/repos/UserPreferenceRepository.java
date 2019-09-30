package com.federicovitale.spring_jwt_boilerplate.models.repos;

import com.federicovitale.spring_jwt_boilerplate.models.User.User;
import com.federicovitale.spring_jwt_boilerplate.models.entities.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    Optional<UserPreference> findByUser(User user);
    void deleteByUser(User user);
}
