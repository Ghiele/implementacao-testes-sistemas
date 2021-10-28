package com.italoghiele.projetointegrador.service.impl;

import com.italoghiele.projetointegrador.domain.*;
import com.italoghiele.projetointegrador.domain.enums.Profile;
import com.italoghiele.projetointegrador.dto.EventRequest;
import com.italoghiele.projetointegrador.dto.response.ActivityResponse;
import com.italoghiele.projetointegrador.dto.response.EventResponse;
import com.italoghiele.projetointegrador.repository.*;
import com.italoghiele.projetointegrador.security.UserSS;
import com.italoghiele.projetointegrador.service.AddressService;
import com.italoghiele.projetointegrador.service.EventService;
import com.italoghiele.projetointegrador.service.PhoneNumberService;
import com.italoghiele.projetointegrador.service.exceptions.AuthorizationException;
import com.italoghiele.projetointegrador.service.exceptions.ObjectNotFoundException;
import com.italoghiele.projetointegrador.validation.MessageKey;
import com.italoghiele.projetointegrador.validation.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Validated
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private final AddressService addressService;
    private final PhoneNumberService phoneNumberService;

    public EventResponse create(EventRequest request) {
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null || !userSS.hasRole(Profile.ADMIN)) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        Address address = addressService.findCreate(request.getAddressRequest());

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        request.getPhoneNumbers().forEach(pn -> phoneNumbers.add(phoneNumberService.create(pn)));

        Event event = Event.builder()
                .name(request.getName())
                .description(request.getDescription())
                .date(request.getDate())
                .address(address)
                .creator(user)
                .phoneNumbers(phoneNumbers)
                .build();

        event = eventRepository.save(event);

        userRepository.save(user);

        return toResponse(event);
    }

    public List<EventResponse> findAllEvents() {
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null || !userSS.hasRole(Profile.ADMIN)) {
            throw new AuthorizationException("Access denied");
        }

        List<Event> eventList = eventRepository.findAll();

        List<EventResponse> eventResponseList = new ArrayList<>();

        eventList.forEach(e -> eventResponseList.add(toResponse(e)));

        return eventResponseList;
    }

    public List<EventResponse> findByUser() {
        UserSS userSS = UserServiceImpl.getAuthenticated();

        if (userSS == null) {
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(userSS.getId()).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        List<Event> eventList = eventRepository.findByCreator(user);

        List<EventResponse> eventResponseList = new ArrayList<>();

        eventList.forEach(e -> eventResponseList.add(toResponse(e)));

        return eventResponseList;
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
