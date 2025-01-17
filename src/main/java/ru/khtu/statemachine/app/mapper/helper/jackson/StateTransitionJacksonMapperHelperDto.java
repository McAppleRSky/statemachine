package ru.khtu.statemachine.app.mapper.helper.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionMapperHelperDto;

import java.util.*;

public class StateTransitionJacksonMapperHelperDto {

    private final ObjectMapper jacksonObjectMapper;
    private final StateTransitionMapperHelperDto stateTransitionMapperHelperDto;

    public StateTransitionJacksonMapperHelperDto(StateTransitionMapperHelperDto stateTransitionMapperHelperDto) {
        this.jacksonObjectMapper = new ObjectMapper();
        this.stateTransitionMapperHelperDto = stateTransitionMapperHelperDto;
    }

    public Map.Entry<
            ArrayList<Map<String, Object>>,
            Map<StateTransitionHelper, Map<String, Object>>> mapHelperToStringObject(
            List<StateTransitionHelper> helpers) {
        Map.Entry<
                ArrayList<Map<String, Object>>,
                Map<StateTransitionHelper, Map<String, Object>>> result = null;
        if (helpers != null && !helpers.isEmpty()) {
            Map<StateTransitionHelper, Map<String, Object>> resultMap = new LinkedHashMap<>();
            ArrayList<Map<String, Object>> resultList = new ArrayList<>();
            for (StateTransitionHelper helper : helpers) {
                Map<String, Object> mapStringObject = this.jacksonObjectMapper
                        .convertValue(
                                stateTransitionMapperHelperDto.toDto(helper),
                                new TypeReference<Map<String, Object>>() {
                                });
                postProc(mapStringObject);
                resultList.add(mapStringObject);
                resultMap.put(helper, mapStringObject);
            }
            result = Collections.singletonMap(resultList, resultMap).entrySet().iterator().next();
        }
        return result;
    }

    private Map<String, Object> postProc(Map<String, Object> mapStringObject) {
        for (String key : mapStringObject.keySet()) {
            if ((new String("className")).equals(key)) {
                Object value = mapStringObject.remove(key);
                mapStringObject.put(new String("nameOfClass"), value);
            } else {
                if ((new String("methodName")).equals(key)) {
                    Object value = mapStringObject.remove(key);
                    mapStringObject.put(new String("nameOfMethod"), value);
                } else {
                    if ((new String("readOnly")).equals(key)) {
                        Object value = mapStringObject.remove(key);
                        mapStringObject.put(new String("isReadOnly"), value);
                    }
                }
            }
        }
        return mapStringObject;
    }

}
