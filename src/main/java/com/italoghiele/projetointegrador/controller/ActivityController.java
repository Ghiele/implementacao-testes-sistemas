package com.italoghiele.projetointegrador.controller;

import com.italoghiele.projetointegrador.dto.ActivityRequest;
import com.italoghiele.projetointegrador.dto.response.ActivityResponse;
import com.italoghiele.projetointegrador.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@Validated
@RequestMapping("activity")
@ConditionalOnWebApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Secured({ Roles.USER_MANAGER })
public class ActivityController extends AbstractController {

    private final ActivityService activityService;

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ActivityResponse create(@Valid @RequestBody ActivityRequest request){
        return activityService.create(request);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<ActivityResponse> getAllActivities(){
        return activityService.findAllActivities();
    }

    @GetMapping(value = "/{id}")
    public List<ActivityResponse> getActivitiesByUser(){
        return activityService.findByUser();
    }
}
