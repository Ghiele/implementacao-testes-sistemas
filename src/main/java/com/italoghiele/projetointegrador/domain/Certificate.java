package com.italoghiele.projetointegrador.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_certificate")
public class Certificate implements Serializable {
    private final static Long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @OneToOne
    private Activity activity;

    @NotNull
    @Column(name = "date_of_issue")
    private Date dateOfIssue;
}
