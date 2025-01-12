package ru.khtu.statemachine.app.service;

import ru.khtu.statemachine.app.constant.enums.WorkObject;

import java.util.Map;

public interface StatemachineService {

    Map<String, Object> getStateTransition(WorkObject workObject);

}
