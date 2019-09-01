package com.federicovitale.spring_jwt_boilerplate.models.services.impl;

import com.federicovitale.spring_jwt_boilerplate.models.entities.Address;
import com.federicovitale.spring_jwt_boilerplate.models.repos.AddrRepository;
import com.federicovitale.spring_jwt_boilerplate.models.services.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddrServiceImpl implements AddrService {
    @Autowired
    private AddrRepository addrRepository;

    @Override
    public Optional<Address> findById(Long id) {
        return addrRepository.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addrRepository.findAll();
    }

    @Override
    public Address save(Address address) {
        return addrRepository.save(address);
    }

    @Override
    public List<Address> saveAll(Iterable<Address> t) {
        return addrRepository.saveAll(t);
    }

    @Override
    public void deleteById(Long id) {
        addrRepository.deleteById(id);
    }

    @Override
    public void delete(Address address) {
        addrRepository.delete(address);
    }

    @Override
    public void deleteAll(Iterable<Address> t) {
        addrRepository.deleteAll(t);
    }
}
