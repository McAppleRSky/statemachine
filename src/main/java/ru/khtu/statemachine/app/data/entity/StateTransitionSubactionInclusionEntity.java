package ru.khtu.statemachine.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "s_statetransit_subact_inclusion")
public class StateTransitionSubactionInclusionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "action_name", length=100, nullable = false)
    private String actionName;

    @Column(name = "inclusion")
    private Boolean inclusion;

    @Column(name = "exclusion")
    private Boolean exclusion;

    @Column(name = "id_state_transit", length=100, nullable = false)
    private Long idStateTransition;

    @Column(name = "id_statetransit_subact", length=100, nullable = false)
    private Long idStateTransitionSubaction;

}
