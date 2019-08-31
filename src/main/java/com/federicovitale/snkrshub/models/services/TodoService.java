package com.federicovitale.snkrshub.models.services;

import com.federicovitale.snkrshub.models.entities.Todo;

import java.util.Optional;

public interface TodoService extends CRUDService<Todo> {
    Optional<Todo> findByTitle(String title);
    Optional<Todo> findAllByCompleted(Boolean completed);
}
