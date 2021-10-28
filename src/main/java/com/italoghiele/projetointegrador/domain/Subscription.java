package com.italoghiele.projetointegrador.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity(name = "tb_subscription")
public class Subscription implements Serializable {
    private final static Long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @OneToOne
    private Activity activity;

    @NotNull
    @Column(name = "date_of_payment")
    private boolean dateOfPayment;
}
