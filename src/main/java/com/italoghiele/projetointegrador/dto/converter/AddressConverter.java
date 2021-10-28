package com.italoghiele.projetointegrador.dto.converter;

import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.City;
import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.dto.AddressRequest;
import com.italoghiele.projetointegrador.dto.UserRequest;
import com.italoghiele.projetointegrador.dto.response.AddressResponse;
import com.italoghiele.projetointegrador.dto.response.UserResponse;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AddressConverter {

    public static List<Address> convertRequestToEntity(List<AddressRequest> addressRequests) {

        if (CollectionUtils.isEmpty(addressRequests)) {
            return Collections.emptyList();
        }

        return addressRequests.stream().map(addressRequest -> convertRequestToEntity(addressRequest))
                .collect(Collectors.toList());
    }

    public static Address convertRequestToEntity(AddressRequest addressRequest) {

        if (addressRequest == null) {
            return null;
        }

        Address address = Address.builder()
                .build();

//        AddressRequest
//        Long id;
//        String streetAddress;
//        String number;
//        String complement;
//        String district;
//        Long cityId;
//        String cityName;
//        Long stateId;
//        String stateName;
//        String zipCode;
//        boolean primaryAddress;
//
//
//        Address
//        Long id;
//        String streetAddress;
//        String number;
//        String complement;
//        String district;
//        City city;
//        String zipCode;
//        boolean isPrimaryAddress;

        return address;
    }

    public static List<AddressResponse> convertEntityToResponse(List<Address> addresses) {
        if (CollectionUtils.isEmpty(addresses)) {
            return Collections.emptyList();
        }

        return addresses.stream().map(AddressConverter::convertEntityToResponse).collect(Collectors.toList());
    }

    public static AddressResponse convertEntityToResponse(Address address) {
        if (address == null) {
            return null;
        }

        return AddressResponse.builder().build();
    }
}
