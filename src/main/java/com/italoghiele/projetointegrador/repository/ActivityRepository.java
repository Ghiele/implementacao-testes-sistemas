package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.Activity;
import com.italoghiele.projetointegrador.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository <Activity, Long> {

    List<Activity> findAll();

    List<Activity> findByCreator(User user);

    List<Activity> findBySubscribedUsers(User user);
}
