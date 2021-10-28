package com.italoghiele.projetointegrador.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_event")
public class Event implements Serializable {

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
    private Date date;

    @NotNull
    @OneToOne
    private Address address;

    @NotNull
    @OneToOne
    private User creator;

    @NotNull
    @OneToMany
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<Activity> activities = new ArrayList<>();

    @NotNull
    @OneToMany
    private List<User> subscribedUsers = new ArrayList<>();
}
