package ru.khtu.statemachine.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "s_statetransit_subact_attribute")
public class StateTransitionSubactionAttributeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "field_name", length=100, nullable = false)
    private String fieldName;

    @Column(name = "const_value", length=100, nullable = false)
    private String constValue;

    @Column(name = "id_state_transit", length=100, nullable = false)
    private Long idStateTransition;

    @Column(name = "id_statetransit_subact", length=100, nullable = false)
    private Long idStateTransitionSubaction;

}
