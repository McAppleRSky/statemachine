package ru.khtu.statemachine.app.service.json;

import ru.khtu.statemachine.app.constant.enums.ResponseKey;
import ru.khtu.statemachine.app.mapper.StateTransitSubactionAttributeMapperDto;
import ru.khtu.statemachine.app.mapper.StateTransitSubactionInclusionMapperDto;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionMapperHelperDto;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionSubactionMapperHelperDto;

import java.util.List;
import java.util.Map;

public interface StateTransitionResponseJsonBuilder {

    StateTransitionResponseJsonBuilder setState();

    StateTransitionResponseJsonBuilder setTransition(
            StateTransitionMapperHelperDto stateTransitionMapperHelperDto );

    StateTransitionResponseJsonBuilder setSubaction(
            StateTransitionSubactionMapperHelperDto stateTransitionSubactionMapperHelperDto );

    StateTransitionResponseJsonBuilder setInclusion(
//            List<Map<String, Object>> subactionDao
//            StateTransitSubactionInclusionMapperDto stateTransitSubactionInclusionMapperDto
    );

    StateTransitionResponseJsonBuilder setAttribute(
//            List<Map<String, Object>> subactionDao
//            StateTransitSubactionAttributeMapperDto stateTransitSubactionAttributeMapperDto
    );

    Map<String, Object> build();

}
