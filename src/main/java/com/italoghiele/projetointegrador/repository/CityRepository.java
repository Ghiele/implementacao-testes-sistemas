package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.City;
import com.italoghiele.projetointegrador.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends CrudRepository <City, Long> {

    City findByIbgeCityId(Long ibgeCityId);
}
