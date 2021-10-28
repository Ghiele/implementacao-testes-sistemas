package com.italoghiele.projetointegrador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.italoghiele.projetointegrador.domain.PhoneNumber;
import com.italoghiele.projetointegrador.validation.beanvalidation.CPFValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @JsonProperty("password")
    private String password;

    @NotEmpty
    private List<PhoneNumber> phoneNumbers;

    @NotEmpty
    private Integer gender;

    @NotEmpty
    @CPFValid
    private String cpf;

    @NotEmpty
    private Integer language;

    @NotEmpty
    private Date dateOfBirth;

    @NotNull
    private List<AddressRequest> addressesRequest;
}
