package ru.khtu.statemachine.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "s_statetransit_module_form")
public class StateTransitionSubactionModuleFormEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "module", length=100, nullable = false)
    private String module;

    @Column(name = "form", length=100, nullable = false)
    private String form;

    @Column(name = "id_state_transit", length=100, nullable = false)
    private Long idStateTransition;

    @Column(name = "id_statetransit_subact", length=100, nullable = false)
    private Long idStateTransitionSubaction;

}
