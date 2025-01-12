package ru.khtu.statemachine.app.data.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionAttributeDto;
import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionInclusionDto;

@Getter @Setter
@RequiredArgsConstructor
public class StateTransitionSubactionHelper {

    private final Long id;

    private final String actionName;

    private final String processName;

    private final Long idStateTransit;

    private StateTransitionSubactionInclusionDto stateTransitionSubactionInclusionDto;

    private StateTransitionSubactionAttributeDto stateTransitionSubactionAttributeDto;

}
