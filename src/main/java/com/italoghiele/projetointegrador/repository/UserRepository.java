package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Long> {

    Optional<User> findOneByEmailIgnoreCaseAndDeletedFalse(String email);
}
