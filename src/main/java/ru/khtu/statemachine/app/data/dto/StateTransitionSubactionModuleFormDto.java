package ru.khtu.statemachine.app.data.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StateTransitionSubactionModuleFormDto {

    private String module;

    private Boolean form;

}
