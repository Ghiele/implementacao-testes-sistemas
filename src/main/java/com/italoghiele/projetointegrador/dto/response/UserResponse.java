package com.italoghiele.projetointegrador.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.italoghiele.projetointegrador.domain.Address;
import com.italoghiele.projetointegrador.domain.PhoneNumber;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private List<PhoneNumber> phoneNumbers;

    private String gender;

    private String cpf;

    private String language;

    private List<Address> addresses;

}
