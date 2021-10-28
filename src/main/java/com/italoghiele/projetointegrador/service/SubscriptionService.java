package com.italoghiele.projetointegrador.service;

import com.italoghiele.projetointegrador.dto.ActivityRequest;
import com.italoghiele.projetointegrador.dto.response.ActivityResponse;
import com.italoghiele.projetointegrador.dto.response.EventResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SubscriptionService {

    void eventSubscription(Long eventId);

    void cancelEventSubscription(Long eventId);

    void activitySubscription(Long activityId);

    void cancelActivitySubscription(Long activityId);

    List<EventResponse> findAllSubscriptionsByUserId(Long userId);
}
