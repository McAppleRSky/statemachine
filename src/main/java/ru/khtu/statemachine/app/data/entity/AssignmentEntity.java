package ru.khtu.statemachine.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Entity
@Table(name = "s_assignment")
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "spec_id", length=20, nullable = false)
    private Long specId;

    @Column(name = "wo_name", length=100)
    private String woName;

    @Column(name = "form_name", length=100)
    private String formName;

    @Column(name = "assoc_name", length=150, nullable = false)
    private String assocName;

    @Column(name = "assoc_spec_id", length=20, nullable = false)
    private Long assocSpecId;

    @Column(name = "assoc_wo_name", length=100)
    private String assocWoName;

    @Column(name = "assoc_form_name", length=100)
    private String assocFormName;

    @Column(name = "dependent_flag")
    private Boolean dependentFlag;

}
