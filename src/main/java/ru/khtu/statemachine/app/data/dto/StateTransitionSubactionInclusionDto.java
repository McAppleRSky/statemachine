package ru.khtu.statemachine.app.data.dto;

import lombok.Data;

@Data
public class StateTransitionSubactionInclusionDto {

    private Boolean inclusion;

    private Boolean exclusion;

}
