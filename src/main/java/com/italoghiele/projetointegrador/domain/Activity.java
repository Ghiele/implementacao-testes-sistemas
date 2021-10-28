package com.italoghiele.projetointegrador.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_activity")
public class Activity implements Serializable {
    private final static Long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @NotEmpty
    @Column(name = "date")
    private LocalDateTime date;

    @NotEmpty
    @Column(name = "duration")
    private Long durationInMinutes;

    @NotNull
    @Column(name = "registration_fee")
    private double registrationFee;

    @NotNull
    @OneToOne
    private User creator;

    @NotNull
    @OneToMany
    private List<User> presentUsers = new ArrayList<>();

    @NotNull
    @OneToMany
    private List<User> subscribedUsers = new ArrayList<>();

    @JsonIgnore
    @JoinColumn(name = "event_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Event event;

}
