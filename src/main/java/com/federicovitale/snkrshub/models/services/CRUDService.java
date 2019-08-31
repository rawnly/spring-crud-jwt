package com.federicovitale.snkrshub.models.services;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {
    Optional<T> findById(Long id);

    List<T> findAll();

    T save(T t);
    List<T> saveAll(Iterable<T> t);

    void deleteById(Long id);
    void delete(T t);
    void deleteAll(Iterable<T> t);

}
