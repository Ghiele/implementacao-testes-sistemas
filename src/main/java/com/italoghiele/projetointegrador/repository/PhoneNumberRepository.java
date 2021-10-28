package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.PhoneNumber;
import com.italoghiele.projetointegrador.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhoneNumberRepository extends CrudRepository <PhoneNumber, Long> {

}
