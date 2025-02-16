package ru.khtu.statemachine.app.mapper;

import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionAttributeDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AttributeMapper {

    private static Map<String, Object> map(StateTransitionSubactionAttributeDto dto) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(new String("fieldName"), dto.getFieldName());
        result.put(new String("constValue"), dto.getConstValue());
        return result;
    }

    public static List<Map<String, Object>> mapListToListMap(List<StateTransitionSubactionAttributeDto> stateTransitionSubactionAttributeDtos) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (StateTransitionSubactionAttributeDto stateTransitionSubactionAttributeDto : stateTransitionSubactionAttributeDtos) {
            result.add(map(stateTransitionSubactionAttributeDto));
        }
        return result;
    }

}
