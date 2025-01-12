package ru.khtu.statemachine.app.service.json;

import ru.khtu.statemachine.app.constant.enums.ResponseKey;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionMapperHelperDto;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionSubactionMapperHelperDto;

import java.util.List;
import java.util.Map;

public interface StateTransitionResponseJsonBuilder {

    StateTransitionResponseJsonBuilder setState(ResponseKey statesKey);

    StateTransitionResponseJsonBuilder setTransition(
            ResponseKey transitionKey,
            StateTransitionMapperHelperDto stateTransitionMapperHelperDto );

    StateTransitionResponseJsonBuilder setSubaction(
            ResponseKey subactionKey,
            StateTransitionSubactionMapperHelperDto stateTransitionSubactionMapperHelperDto );

    StateTransitionResponseJsonBuilder setInclusion(List<Map<String, Object>> subactionDao);

    StateTransitionResponseJsonBuilder setAttribute(List<Map<String, Object>> subactionDao);

    Map<String, Object> build();

}
