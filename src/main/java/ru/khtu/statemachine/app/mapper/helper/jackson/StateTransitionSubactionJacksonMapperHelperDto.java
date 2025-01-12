package ru.khtu.statemachine.app.mapper.helper.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.data.helper.StateTransitionSubactionHelper;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionSubactionMapperHelperDto;

import java.util.*;

public class StateTransitionSubactionJacksonMapperHelperDto {

    private final ObjectMapper jacksonObjectMapper;
    private final StateTransitionSubactionMapperHelperDto stateTransitionSubactionMapperHelperDto;

    public StateTransitionSubactionJacksonMapperHelperDto(
            StateTransitionSubactionMapperHelperDto stateTransitionSubactionMapperHelperDto ) {
        this.jacksonObjectMapper = new ObjectMapper();
        this.stateTransitionSubactionMapperHelperDto = stateTransitionSubactionMapperHelperDto;
    }

    public Map<StateTransitionSubactionHelper, Map<String, Object>> mapHelperToStringObject(
                    List<StateTransitionHelper> helpers ) {
        Map<StateTransitionSubactionHelper, Map<String, Object>> result = null;
        if (helpers != null && !helpers.isEmpty()) {
            Map<StateTransitionSubactionHelper, Map<String, Object>> resultMap = new LinkedHashMap<>();
//            ArrayList<Map<String, Object>> resultList = new ArrayList<>();
            for (StateTransitionHelper helper : helpers) {
                if (helper.getStateTransitionSubaction() != null) {
                    Map<String, Object> mapStringObject = this.jacksonObjectMapper.convertValue(
                            stateTransitionSubactionMapperHelperDto.toDto(helper.getStateTransitionSubaction()),
                            new TypeReference<Map<String, Object>>() {} );
//                    resultList.add(mapStringObject);
                    resultMap.put(helper.getStateTransitionSubaction(), mapStringObject);
                }
            }
            result = resultMap;
        }
        return result;
    }

}
