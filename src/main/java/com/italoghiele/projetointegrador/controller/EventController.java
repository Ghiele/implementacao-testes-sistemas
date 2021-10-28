package com.italoghiele.projetointegrador.controller;

import com.italoghiele.projetointegrador.dto.EventRequest;
import com.italoghiele.projetointegrador.dto.response.EventResponse;
import com.italoghiele.projetointegrador.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("event")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Secured({ Roles.USER_MANAGER })
public class EventController extends AbstractController {

    private final EventService eventService;

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public EventResponse create(@NotNull @RequestBody EventRequest request){
        return eventService.create(request);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<EventResponse> getAllEvents(){
        return eventService.findAllEvents();
    }

    @GetMapping(value = "/{id}")
    public List<EventResponse> getEventsByUser(@PathVariable Long id){
        return eventService.findByUser();
    }

}
