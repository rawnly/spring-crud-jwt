package com.federicovitale.snkrshub.models.services.impl;

import com.federicovitale.snkrshub.models.entities.Todo;
import com.federicovitale.snkrshub.models.repos.TodoRepository;
import com.federicovitale.snkrshub.models.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Optional<Todo> findByTitle(String title) {
        return todoRepository.findByTitle(title);
    }

    @Override
    public Optional<Todo> findAllByCompleted(Boolean completed) {
        return todoRepository.findAllByCompleted(completed);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> saveAll(Iterable<Todo> t) {
        return todoRepository.saveAll(t);
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void delete(Todo todo) {
        todoRepository.delete(todo);
    }

    @Override
    public void deleteAll(Iterable<Todo> t) {
        todoRepository.deleteAll(t);
    }
}
