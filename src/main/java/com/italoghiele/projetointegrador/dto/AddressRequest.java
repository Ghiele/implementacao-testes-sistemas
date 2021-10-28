package com.italoghiele.projetointegrador.dto;

import com.italoghiele.projetointegrador.domain.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest {

    private Long id;

    @NotEmpty
    private String publicPlace;

    @NotEmpty
    private String number;

    private String complement;

    @NotEmpty
    private String neighborhood;

    @NotEmpty
    private CityRequest cityRequest;

    @NotEmpty
    private String zipCode;

    @NotNull
    private boolean primaryAddress;
}
