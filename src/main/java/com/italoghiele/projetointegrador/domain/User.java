package com.italoghiele.projetointegrador.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.italoghiele.projetointegrador.domain.enums.Gender;
import com.italoghiele.projetointegrador.domain.enums.Language;
import com.italoghiele.projetointegrador.domain.enums.Profile;
import com.italoghiele.projetointegrador.validation.beanvalidation.CPFValid;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_user")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Email
    @NotNull
    @Column(unique = true, name = "email")
    private String email;

    @JsonIgnore
    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "gender")
    private Integer gender;

    @NotNull
    @CPFValid
    @Column(unique = true, name = "cpf")
    private String cpf;

    @NotNull
    @Column(name = "language_id")
    private Integer language;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @NotNull
    @Column(name = "deleted")
    @Builder.Default
    private Boolean deleted = false;

    @NotNull
    @Column(name = "active")
    private Boolean active;

    @NotNull
    @OneToMany
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @NotNull
    @OneToMany
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_profiles")
    private Set<Integer> profiles = new HashSet<>();

    public Gender getGender(){
        return Gender.toEnum(gender);
    }

    public Language getLanguage(){
        return Language.toEnum(language);
    }

    public Set<Profile> getProfiles(){
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCod());
    }
}
