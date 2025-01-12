package ru.khtu.statemachine.app.component;

import ru.khtu.statemachine.app.data.dto.StateDto;
import ru.khtu.statemachine.app.constant.enums.WorkObject;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.mapper.StateMapperDto;

import java.util.List;
import java.util.Map;

public interface StatemachineComponent {

    Map.Entry<List<StateDto>, List<StateTransitionHelper>> getStateTransition(WorkObject workObject);

    StateMapperDto getStateMapper();

}
