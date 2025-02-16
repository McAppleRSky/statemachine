package ru.khtu.statemachine.app.mapper;

import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionInclusionDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InclusionMapper {

    private static Map<String, Object> map(StateTransitionSubactionInclusionDto dto) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(new String("actionName"), dto.getActionName());
        result.put(new String("inclusion"), dto.getInclusion());
        result.put(new String("exclusion"), dto.getExclusion());
        return result;
    }

    public static List<Map<String, Object>> mapListToListMap(List<StateTransitionSubactionInclusionDto> stateTransitionSubactionInclusionDtos) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (StateTransitionSubactionInclusionDto stateTransitionSubactionInclusionDto : stateTransitionSubactionInclusionDtos) {
            result.add(map(stateTransitionSubactionInclusionDto));
        }
        return result;
    }

}
