package com.italoghiele.projetointegrador.controller;

import com.italoghiele.projetointegrador.dto.UserRequest;
import com.italoghiele.projetointegrador.dto.response.*;
import com.italoghiele.projetointegrador.service.AddressService;
import com.italoghiele.projetointegrador.service.UserService;
import com.italoghiele.projetointegrador.service.api.ApiIBGEService;
import com.italoghiele.projetointegrador.service.api.ApiViacepService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@Validated
@RequestMapping("address")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Secured({ Roles.USER_MANAGER })
public class AddressController extends AbstractController {

    private final AddressService addressService;

//    @PostMapping
//    public UserResponse create(@Valid @NotNull @RequestBody UserRequest request){
//
//        try {
//
//            List<IBGEStateResponse> ibgeStateResponses = apiIBGEService.getAllStates().execute().body();
//            System.out.println("ESTADOS: " + ibgeStateResponses.toString());
//
//            ViacepAddressResponse viacepAddressResponse = apiViacepService.getAddressByCEP("38402180").execute().body();
//            System.out.println("CEP: " + viacepAddressResponse.toString());
//
//        } catch (IOException e) {
//            throw new RuntimeException("Error to call the customer service", e);
//        }
//
//        return userService.create(request);
//    }
//
//    @GetMapping(value = "/{id}")
//    public UserResponse findById(@PathVariable Long id) {
//        return userService.findById(id);
//    }
//
//    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public List<UserResponse> findAll() {
//        return userService.findAll();
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public void deleteById(@PathVariable Long id) {
//        userService.deleteById(id);
//    }
//
//    @PutMapping(value = "/{id}")
//    public UserResponse updateById(@PathVariable Long id, @Valid @NotNull @RequestBody UserRequest request) {
//        return userService.updateById(id, request);
//    }

    @GetMapping(value = "/state")
    public List<StateResponse> getAllStates(){
        return addressService.findAllStates();
    }

    @GetMapping(value = "/{stateInitials}/city")
    public List<CityResponse> getAllCitiesByState(@PathVariable String stateInitials){
        return addressService.findAllCitiesByState(stateInitials);
    }

    @GetMapping(value = "/{cep}")
    public AddressResponse getAddressByCep(@PathVariable String cep){
        return addressService.findAddressByCep(cep);
    }
}