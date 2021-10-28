package com.italoghiele.projetointegrador.dto.response;

import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.PhoneNumber;
import com.italoghiele.projetointegrador.domain.User;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Value
@Builder
public class ActivityResponse {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime date;

    private Long durationInMinutes;

    private double registrationFee;

    private Long creatorId;

    private String creatorName;
}
