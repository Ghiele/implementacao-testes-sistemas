package com.italoghiele.projetointegrador.dto.response;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.italoghiele.projetointegrador.domain.Activity;
import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.PhoneNumber;
import lombok.Builder;
import lombok.Value;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Value
@Builder
public class EventResponse {

    private Long id;

    private String name;

    private String description;

    private Date date;

    private Address address;

    private List<PhoneNumber> phoneNumbers;

    private List<ActivityResponse> activities;

    private Long creatorId;
}
