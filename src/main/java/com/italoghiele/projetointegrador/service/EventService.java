package com.italoghiele.projetointegrador.service;

import com.italoghiele.projetointegrador.dto.EventRequest;
import com.italoghiele.projetointegrador.dto.response.EventResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EventService {

    EventResponse create(@NotNull @RequestBody EventRequest request);

    List<EventResponse> findAllEvents();

    List<EventResponse> findByUser();
}
