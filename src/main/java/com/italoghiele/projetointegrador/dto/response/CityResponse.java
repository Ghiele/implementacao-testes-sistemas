package com.italoghiele.projetointegrador.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CityResponse {

    private Long id;

    private String name;

    private Long ibgeCityId;

    private Long ibgeStateId;
}
