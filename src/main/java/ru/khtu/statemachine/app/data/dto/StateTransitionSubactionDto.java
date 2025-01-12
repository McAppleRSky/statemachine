package ru.khtu.statemachine.app.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
public class StateTransitionSubactionDto {

    private String actionName;

    private String processName;

}
