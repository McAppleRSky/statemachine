package ru.khtu.statemachine.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "s_state_transit")
public class StateTransitionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_state_transit")
    private Long id;

    @Column(name = "wo_name", length=100, nullable = false)
    private String woName;

    @Column(name = "action_name", length=100, nullable = false)
    private String actionName;

    @Column(name = "action_label", length=100, nullable = false)
    private String actionLabel;

    @Column(name = "current_state", length=100, nullable = false)
    private String currentState;

    @Column(name = "next_state", length=100, nullable = false)
    private String nextState;

    @Column(name = "custom_url", length=500)
    private String customUrl;

    @Column(name = "class_name", length=1000)
    private String className;

    @Column(name = "methodName", length=150)
    private String methodName;

    @Column(name = "default_display")
    private Boolean defaultDisplay;

    @Column(name = "read_only")
    private Boolean readOnly;

    @Column(name = "close_window")
    private Boolean closeWindow;

    @Column(name = "secondary_action")
    private Boolean secondaryAction;

}
