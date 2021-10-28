package com.italoghiele.projetointegrador.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.City;
import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.domain.enums.State;
import com.italoghiele.projetointegrador.dto.AddressRequest;
import com.italoghiele.projetointegrador.dto.response.AddressResponse;
import com.italoghiele.projetointegrador.dto.response.CityResponse;
import com.italoghiele.projetointegrador.dto.response.StateResponse;
import com.italoghiele.projetointegrador.dto.response.ViacepAddressResponse;
import com.italoghiele.projetointegrador.repository.AddressRepository;
import com.italoghiele.projetointegrador.repository.CityRepository;
import com.italoghiele.projetointegrador.service.AddressService;
import com.italoghiele.projetointegrador.service.api.ApiIBGEService;
import com.italoghiele.projetointegrador.service.api.ApiViacepService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final CityRepository cityRepository;

    private final ApiIBGEService apiIBGEService;

    private final ApiViacepService apiViacepService;

    public Address findCreate(AddressRequest request) {

        Address address = null;
        City city = null;

        if (request.getId() != null) {
            address = addressRepository.findById(request.getId()).orElse(null);
        }

        if (request.getCityRequest().getIbgeCityId() != null) {
            Long n = request.getCityRequest().getIbgeCityId();
            city = cityRepository.findByIbgeCityId(n);
        }

        if (city == null) {
            city = cityRepository.save(City.builder()
                    .name(request.getCityRequest().getName())
                    .ibgeCityId(request.getCityRequest().getIbgeCityId())
                    .ibgeStateId(request.getCityRequest().getIbgeStateId())
                    .build());
        }

        if (address == null) {
            Boolean b = request.isPrimaryAddress();

            address = Address.builder()
                    .publicPlace(request.getPublicPlace())
                    .number(request.getNumber())
                    .complement(request.getComplement())
                    .neighborhood(request.getNeighborhood())
                    .city(city)
                    .zipCode(request.getZipCode())
                    .primaryAddress(request.isPrimaryAddress())
                    .build();
        }

        return addressRepository.save(address);
    }

    public List<StateResponse> findAllStates() {
        return this.toStateResponse();
    }

    public List<CityResponse> findAllCitiesByState(String stateInitials) {
        try {
            List<Object> response = apiIBGEService.getCitiesByState(stateInitials).execute().body();

            List<CityResponse> citiesResponse = new ArrayList<>();

            for (Object object : response) {
                JsonNode city = new ObjectMapper().valueToTree(object);

                citiesResponse.add(
                        CityResponse.builder()
                                .name(city.get("nome").textValue())
                                .ibgeCityId(city.get("id").asLong())
                                .ibgeStateId(city.get("microrregiao").get("mesorregiao").get("UF").get("id").asLong())
                                .build()
                );
            }

            return citiesResponse;
        } catch (IOException e) {
            throw new RuntimeException("Error to call the IBGE service", e);
        }
    }

    public AddressResponse findAddressByCep(String cep) {
        try {
            ViacepAddressResponse response = apiViacepService.getAddressByCEP(cep).execute().body();

            State state = State.toEnum(response.getUf());

            CityResponse cityResponse = CityResponse.builder()
                    .name(response.getLocalidade())
                    .ibgeCityId(Long.valueOf(response.getIbge()))
                    .ibgeStateId(state.getIbgeId())
                    .build();
            StateResponse stateResponse = StateResponse.builder()
                    .name(state.getName())
                    .ibgeId(state.getIbgeId())
                    .initials(state.getInitials())
                    .build();

            AddressResponse addressResponse = AddressResponse.builder()
                    .publicPlace(response.getLogradouro())
                    .neighborhood(response.getBairro())
                    .cityResponse(cityResponse)
                    .stateResponse(stateResponse)
                    .zipCode(response.getCep())
                    .build();

            return addressResponse;
        } catch (IOException e) {
            throw new RuntimeException("Error to call the VIACEP service", e);
        }
    }

    private List<StateResponse> toStateResponse() {
        List<StateResponse> stateResponses = new ArrayList<>();

        for (State state : State.values()) {
            stateResponses.add(StateResponse.builder().ibgeId(state.getIbgeId()).name(state.getName()).initials(state.getInitials()).build());
        }
        return stateResponses;
    }
}
