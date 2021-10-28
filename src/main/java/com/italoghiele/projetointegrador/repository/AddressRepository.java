package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository <Address, Long> {
}
