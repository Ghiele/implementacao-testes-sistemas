package com.italoghiele.projetointegrador.controller;

import com.italoghiele.projetointegrador.dto.response.EventResponse;
import com.italoghiele.projetointegrador.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("subscription")
@ConditionalOnWebApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Secured({ Roles.USER_MANAGER })
public class SubscriptionController extends AbstractController {

    private final SubscriptionService subscriptionService;

    @PostMapping(value = "/event/{eventId}")
    public void eventSubscription(@PathVariable Long eventId){
        subscriptionService.eventSubscription(eventId);
    }

    @PostMapping(value = "/event/{eventId}/cancel")
    public void cancelEventSubscription(@PathVariable Long eventId){
        subscriptionService.cancelEventSubscription(eventId);
    }

    @PostMapping(value = "/activity/{activityID}")
    public void activitySubscription(@PathVariable Long activityID){
        subscriptionService.activitySubscription(activityID);
    }

    @PostMapping(value = "/activity/{activityID}/cancel")
    public void cancelActivitySubscription(@PathVariable Long activityID){
        subscriptionService.cancelActivitySubscription(activityID);
    }

    @GetMapping(value = "/{userId}")
    public List<EventResponse> getAllSubscriptionsByUserId(@PathVariable Long userId){
        return subscriptionService.findAllSubscriptionsByUserId(userId);
    }



}
