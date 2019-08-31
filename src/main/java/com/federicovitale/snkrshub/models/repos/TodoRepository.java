package com.federicovitale.snkrshub.models.repos;

import com.federicovitale.snkrshub.models.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findAllByCompleted(Boolean completed);
    Optional<Todo> findByTitle(String title);
}
