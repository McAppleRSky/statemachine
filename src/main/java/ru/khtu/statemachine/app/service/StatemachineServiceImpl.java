package ru.khtu.statemachine.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khtu.statemachine.app.component.StatemachineComponent;
import ru.khtu.statemachine.app.constant.enums.ResponseKey;
import ru.khtu.statemachine.app.constant.enums.WorkObject;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionMapperHelperDto;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionSubactionMapperHelperDto;
import ru.khtu.statemachine.app.service.json.StateTransitionResponseJsonBuilder;
import ru.khtu.statemachine.app.service.json.StateTransitionResponseJsonBuilderImpl;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StatemachineServiceImpl implements StatemachineService {

    private final StatemachineComponent statemachineComponent;
    private final StateTransitionMapperHelperDto stateTransitionMapperHelperDto;
    private final StateTransitionSubactionMapperHelperDto stateTransitionSubactionMapperHelperDto;

    @Override
    public Map<String, Object> getStateTransition(WorkObject workObject) {
        return new StateTransitionResponseJsonBuilderImpl(workObject, this.statemachineComponent)
                .setState(ResponseKey.STATES)
                .setTransition(ResponseKey.TRANSIT, stateTransitionMapperHelperDto)
                .setSubaction(ResponseKey.SUBACTION, stateTransitionSubactionMapperHelperDto)
                .build();
    }

}
