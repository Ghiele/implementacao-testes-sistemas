package com.italoghiele.projetointegrador.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_phone_number")
public class PhoneNumber implements Serializable {
    private final static Long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "primary_phone")
    private boolean primaryPhone;
}
