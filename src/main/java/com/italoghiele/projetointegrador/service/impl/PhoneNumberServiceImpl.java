package com.italoghiele.projetointegrador.service.impl;


import com.italoghiele.projetointegrador.domain.PhoneNumber;
import com.italoghiele.projetointegrador.repository.PhoneNumberRepository;
import com.italoghiele.projetointegrador.service.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumber create(PhoneNumber request){
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneNumber(request.getPhoneNumber())
                .primaryPhone(request.isPrimaryPhone())
                .build();

        return phoneNumberRepository.save(phoneNumber);
    }
}
