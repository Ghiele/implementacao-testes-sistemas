package com.italoghiele.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private Long ibgeCityId;

    @NotEmpty
    private Long ibgeStateId;
}
