package com.italoghiele.projetointegrador.domain;

import com.italoghiele.projetointegrador.domain.enums.Profile;
import com.italoghiele.projetointegrador.domain.enums.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_city")
public class City implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "ibge_city_id")
    private Long ibgeCityId;

    @NotEmpty
    @Column(name = "ibge_state_id")
    private Long ibgeStateId;
}
