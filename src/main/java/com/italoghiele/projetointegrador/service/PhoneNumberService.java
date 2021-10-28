package com.italoghiele.projetointegrador.service;

import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.PhoneNumber;
import com.italoghiele.projetointegrador.dto.AddressRequest;

public interface PhoneNumberService {

    PhoneNumber create(PhoneNumber request);
}
