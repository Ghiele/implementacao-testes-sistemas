package com.italoghiele.projetointegrador.service;

import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.dto.AddressRequest;
import com.italoghiele.projetointegrador.dto.response.AddressResponse;
import com.italoghiele.projetointegrador.dto.response.CityResponse;
import com.italoghiele.projetointegrador.dto.response.StateResponse;

import java.util.List;

public interface AddressService {

    Address findCreate(AddressRequest request);

    List<StateResponse> findAllStates();

    List<CityResponse> findAllCitiesByState(String stateInitials);

    AddressResponse findAddressByCep(String cep);
}
