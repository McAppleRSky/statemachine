package ru.khtu.statemachine.app.data.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
public class StateDto {

    private String woName;

    private String woState;

}
