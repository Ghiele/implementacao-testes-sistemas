package com.italoghiele.projetointegrador.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_address")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "public_place")
    private String publicPlace;

    @NotEmpty
    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @NotEmpty
    @Column(name = "neighborhood")
    private String neighborhood;

    @NotEmpty
    @ManyToOne
    @JoinColumn (name = "city_id")
    private City city;

    @NotEmpty
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @Column(name = "primary_address")
    private boolean primaryAddress;
}
