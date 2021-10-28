package com.italoghiele.projetointegrador.dto.response;

import com.italoghiele.projetointegrador.domain.City;
import com.italoghiele.projetointegrador.domain.enums.State;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressResponse {

    private Long id;

    private String publicPlace;

    private String number;

    private String complement;

    private String neighborhood;

    private CityResponse cityResponse;

    private StateResponse stateResponse;

    private String zipCode;

    private boolean primaryAddress;

}
