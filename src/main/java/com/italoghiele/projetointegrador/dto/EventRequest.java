package com.italoghiele.projetointegrador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.italoghiele.projetointegrador.domain.Activity;
import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.PhoneNumber;
import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.validation.beanvalidation.CPFValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private Date date;

    @NotEmpty
    private AddressRequest addressRequest;

    @NotEmpty
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    private List<Activity> activities = new ArrayList<>();
}
