package com.italoghiele.projetointegrador.dto.converter;

import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.dto.AddressRequest;
import com.italoghiele.projetointegrador.dto.UserRequest;
import com.italoghiele.projetointegrador.dto.response.UserResponse;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class UserConverter {

    public static List<User> convertRequestToEntity(List<UserRequest> userRequests, Long id) {

        if (CollectionUtils.isEmpty(userRequests)) {
            return Collections.emptyList();
        }

        return userRequests.stream().map(userRequest -> convertRequestToEntity(userRequest, id))
                .collect(Collectors.toList());
    }

    public static User convertRequestToEntity(UserRequest userRequest, Long id) {

        if (userRequest == null) {
            return null;
        }

        List<AddressRequest> addressesRequest = userRequest.getAddressesRequest();

        List<Address> addresses = AddressConverter.convertRequestToEntity(addressesRequest);

        User user = User.builder().id(id).name(userRequest.getName()).email(userRequest.getEmail()).password(userRequest.getPassword())
                .gender(userRequest.getGender()).cpf(userRequest.getCpf()).language(userRequest.getLanguage()).phoneNumbers(userRequest.getPhoneNumbers())
                .addresses(addresses).build();

        return user;
    }

    public static List<UserResponse> convertEntityToResponse(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }

        return users.stream().map(UserConverter::convertEntityToResponse).collect(Collectors.toList());
    }

    public static UserResponse convertEntityToResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumbers(user.getPhoneNumbers())
                .gender(user.getGender().getDescription())
                .cpf(user.getCpf())
                .language(user.getLanguage().getDescription())
                .addresses(user.getAddresses())
                .build();
    }
}
