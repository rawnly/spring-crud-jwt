package com.federicovitale.spring_jwt_boilerplate.models.repos;

import com.federicovitale.spring_jwt_boilerplate.models.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findAllByCompleted(Boolean completed);
    Optional<Todo> findByTitle(String title);
}
