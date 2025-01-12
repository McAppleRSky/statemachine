package ru.khtu.statemachine.app.service.json;

import ru.khtu.statemachine.app.constant.enums.WorkObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StateTransitionResponseJsonBuilderAbstract {

    protected final WorkObject workObject;
    protected final List<Map<String, Object>> stateTransition;
    protected final Map<String, Object> mapResult;

    public StateTransitionResponseJsonBuilderAbstract(WorkObject workObject) {
        this.workObject = workObject;
        this.stateTransition = new ArrayList<>();
        this.mapResult = Collections.singletonMap(workObject.getString(), stateTransition);
    }

}
