package com.italoghiele.projetointegrador.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class ActivityRequest {

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private LocalDateTime date;

    @NotEmpty
    private Long durationInMinutes;

    @NotNull
    private double registrationFee;

    @NotNull
    private Long creatorId;

    @NotEmpty
    @NotBlank
    @NotNull
    private Long eventId;
}
