package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.Event;
import com.italoghiele.projetointegrador.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository <Event, Long> {

    List<Event> findAll();

    List<Event> findByCreator(User user);

    List<Event> findBySubscribedUsers(User user);
}
