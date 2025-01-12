package ru.khtu.statemachine.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "s_state")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "wo_name", length=100, nullable = false)
    private String woName;

    @Column(name = "wo_state", length=100, nullable = false)
    private String woState;

}
