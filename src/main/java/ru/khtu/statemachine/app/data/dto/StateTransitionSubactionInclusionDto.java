package ru.khtu.statemachine.app.data.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StateTransitionSubactionInclusionDto {

    private String actionName;

    private Boolean inclusion;

    private Boolean exclusion;

}
