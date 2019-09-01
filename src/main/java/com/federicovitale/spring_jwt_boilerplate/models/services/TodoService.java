package com.federicovitale.spring_jwt_boilerplate.models.services;

import com.federicovitale.spring_jwt_boilerplate.models.entities.Todo;

import java.util.Optional;

public interface TodoService extends CRUDService<Todo> {
    Optional<Todo> findByTitle(String title);
    Optional<Todo> findAllByCompleted(Boolean completed);
}
