package com.italoghiele.projetointegrador.service.impl;

import com.italoghiele.projetointegrador.domain.*;
import com.italoghiele.projetointegrador.domain.enums.Profile;
import com.italoghiele.projetointegrador.dto.ActivityRequest;
import com.italoghiele.projetointegrador.dto.EventRequest;
import com.italoghiele.projetointegrador.dto.response.ActivityResponse;
import com.italoghiele.projetointegrador.dto.response.EventResponse;
import com.italoghiele.projetointegrador.repository.ActivityRepository;
import com.italoghiele.projetointegrador.repository.EventRepository;
import com.italoghiele.projetointegrador.repository.UserRepository;
import com.italoghiele.projetointegrador.security.UserSS;
import com.italoghiele.projetointegrador.service.ActivityService;
import com.italoghiele.projetointegrador.service.AddressService;
import com.italoghiele.projetointegrador.service.EventService;
import com.italoghiele.projetointegrador.service.PhoneNumberService;
import com.italoghiele.projetointegrador.service.exceptions.AuthorizationException;
import com.italoghiele.projetointegrador.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Validated
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityServiceImpl implements ActivityService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final EventRepository eventRepository;

    public ActivityResponse create(ActivityRequest request) {
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null || !userSS.hasRole(Profile.ADMIN)) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(request.getCreatorId()).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        Event event = eventRepository.findById(request.getEventId()).orElse(null);

        if (event == null) {
            throw new ObjectNotFoundException("Event not found");
        }

        Activity activity = Activity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .date(request.getDate())
                .durationInMinutes(request.getDurationInMinutes())
                .registrationFee(request.getRegistrationFee())
                .creator(user)
                .event(event)
                .build();

        activity = activityRepository.save(activity);

        eventRepository.save(event);

        return toResponse(activity);
    }

    public List<ActivityResponse>  findAllActivities() {
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null || !userSS.hasRole(Profile.ADMIN)) {
            throw new AuthorizationException("Access denied");
        }

        List<Activity> activityList = activityRepository.findAll();

        List<ActivityResponse> activityResponseList = new ArrayList<>();

        activityList.forEach(a -> activityResponseList.add(toResponse(a)));

        return activityResponseList;
    }

    public List<ActivityResponse> findByUser() {
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        List<Activity> activityList = activityRepository.findByCreator(user);

        List<ActivityResponse> activityResponseList = new ArrayList<>();

        activityList.forEach(a -> activityResponseList.add(toResponse(a)));

        return activityResponseList;
    }

    public List<ActivityResponse> findByEventId() {
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        List<Activity> activityList = activityRepository.findByCreator(user);

        List<ActivityResponse> activityResponseList = new ArrayList<>();

        activityList.forEach(a -> activityResponseList.add(toResponse(a)));

        return activityResponseList;
    }

    private ActivityResponse toResponse(Activity activity) {
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
