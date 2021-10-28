package com.italoghiele.projetointegrador.service.impl;

import com.italoghiele.projetointegrador.domain.Activity;
import com.italoghiele.projetointegrador.domain.Event;
import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.domain.enums.Profile;
import com.italoghiele.projetointegrador.dto.ActivityRequest;
import com.italoghiele.projetointegrador.dto.response.ActivityResponse;
import com.italoghiele.projetointegrador.dto.response.EventResponse;
import com.italoghiele.projetointegrador.repository.ActivityRepository;
import com.italoghiele.projetointegrador.repository.EventRepository;
import com.italoghiele.projetointegrador.repository.UserRepository;
import com.italoghiele.projetointegrador.security.UserSS;
import com.italoghiele.projetointegrador.service.ActivityService;
import com.italoghiele.projetointegrador.service.SubscriptionService;
import com.italoghiele.projetointegrador.service.exceptions.AuthorizationException;
import com.italoghiele.projetointegrador.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubscriptionServiceImpl implements SubscriptionService {

    private final EventRepository eventRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public List<EventResponse> findAllSubscriptionsByUserId(Long userId){
        List<EventResponse> eventResponseList = new ArrayList<>();

        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        List<Event> eventList = eventRepository.findBySubscribedUsers(user);
        List<Activity> activityList = activityRepository.findBySubscribedUsers(user);

        eventList.forEach(e -> {
            e.getActivities().forEach(a -> {
                if(!activityList.contains(a)){
                    e.getActivities().remove(a);
                }
            });
        });

        if(eventList == null || user == null){
            throw new ObjectNotFoundException("User or Event not found");
        }

        eventList.forEach(e -> eventResponseList.add(toResponse(e)));

        return eventResponseList;
    }

    public void eventSubscription(Long eventId){
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        Event event = eventRepository.findById(eventId).orElse(null);

        if(event == null || user == null){
            throw new ObjectNotFoundException("User or Event not found");
        }

        List<User> subscribedUsers = event.getSubscribedUsers();

        if(!subscribedUsers.contains(user)) {
            subscribedUsers.add(user);
            event.setSubscribedUsers(subscribedUsers);
            eventRepository.save(event);
        }

    }

    public void cancelEventSubscription(Long eventId){
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        Event event = eventRepository.findById(eventId).orElse(null);

        if(event == null || user == null){
            throw new ObjectNotFoundException("User or Event not found");
        }

        List<User> subscribedUsers = event.getSubscribedUsers();

        if(subscribedUsers.contains(user)) {
            subscribedUsers.remove(user);
            event.setSubscribedUsers(subscribedUsers);
            eventRepository.save(event);
        }
    }

    public void activitySubscription(Long activityId){
        Event event = null;
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);
        Activity activity = activityRepository.findById(activityId).orElse(null);

        if(activity != null){
           event = eventRepository.findById(activity.getEvent().getId()).orElse(null);
        }

        if(activity == null || user == null || event == null){
            throw new ObjectNotFoundException("User or Activity or Event not found");
        }

        List<User> eventSubscribedUsers = event.getSubscribedUsers();

        if(!eventSubscribedUsers.contains(user)){
            throw new ObjectNotFoundException("User not registered for the event " + event.getName());
        }

        List<User> activitySubscribedUsers = activity.getSubscribedUsers();

        if(!activitySubscribedUsers.contains(user)) {
            activitySubscribedUsers.add(user);
            activity.setSubscribedUsers(activitySubscribedUsers);
            activityRepository.save(activity);
        }
    }

    public void cancelActivitySubscription(Long activityId){
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        Activity activity = activityRepository.findById(activityId).orElse(null);

        if(activity == null || user == null){
            throw new ObjectNotFoundException("User or Activity not found");
        }

        List<User> subscribedUsers = activity.getSubscribedUsers();

        if(subscribedUsers.contains(user)) {
            subscribedUsers.remove(user);
            activity.setSubscribedUsers(subscribedUsers);
            activityRepository.save(activity);
        }
    }

    private EventResponse toResponse(Event event) {
        List<ActivityResponse> activityResponseList = new ArrayList<>();

        if ( event.getActivities() != null) {
            event.getActivities().forEach(a -> activityResponseList.add(activityTooResponse(a)));
        }


        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .date(event.getDate())
                .address(event.getAddress())
                .phoneNumbers(event.getPhoneNumbers())
                .activities(activityResponseList)
                .creatorId(event.getCreator().getId())
                .build();
    }

    private ActivityResponse activityTooResponse(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .name(activity.getName())
                .description(activity.getDescription())
                .date(activity.getDate())
                .durationInMinutes(activity.getDurationInMinutes())
                .registrationFee(activity.getRegistrationFee())
                .creatorId(activity.getCreator().getId())
                .creatorName(activity.getCreator().getName())
                .build();
    }

}
