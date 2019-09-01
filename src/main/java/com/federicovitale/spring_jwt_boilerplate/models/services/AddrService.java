package com.federicovitale.spring_jwt_boilerplate.models.services;

import com.federicovitale.spring_jwt_boilerplate.models.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddrService extends CRUDService<Address> {
    @Override
    Optional<Address> findById(Long id);

    @Override
    List<Address> findAll();

    @Override
    Address save(Address address);

    @Override
    List<Address> saveAll(Iterable<Address> t);

    @Override
    void deleteById(Long id);

    @Override
    void delete(Address address);

    @Override
    void deleteAll(Iterable<Address> t);
}
