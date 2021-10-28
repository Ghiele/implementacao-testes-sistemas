package com.italoghiele.projetointegrador.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StateResponse {

    private Long ibgeId;

    private String name;

    private String initials;
}
