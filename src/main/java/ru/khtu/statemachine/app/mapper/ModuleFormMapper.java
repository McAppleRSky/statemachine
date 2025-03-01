package ru.khtu.statemachine.app.mapper;

import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionModuleFormDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModuleFormMapper {

    private static Map<String, Object> map(StateTransitionSubactionModuleFormDto dto) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(new String("module"), dto.getModule());
        result.put(new String("form"), dto.getForm());
        return result;
    }

    public static List<Map<String, Object>> mapListToListMap(List<StateTransitionSubactionModuleFormDto> stateTransitionSubactionModuleFormDtos) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (StateTransitionSubactionModuleFormDto stateTransitionSubactionModuleFormDto : stateTransitionSubactionModuleFormDtos) {
            result.add(map(stateTransitionSubactionModuleFormDto));
        }
        return result;
    }

}
