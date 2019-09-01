package com.federicovitale.spring_jwt_boilerplate.models.repos;

import com.federicovitale.spring_jwt_boilerplate.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddrRepository extends JpaRepository<Address, Long> {
}
