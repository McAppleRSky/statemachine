package ru.khtu.statemachine.app.mapper.helper;

import ru.khtu.statemachine.app.data.entity.StateTransitionSubactionEntity;
import ru.khtu.statemachine.app.data.helper.StateTransitionSubactionHelper;

public class StateTransitionSubactionMapperHelper {

    public static StateTransitionSubactionHelper toHelper(StateTransitionSubactionEntity entity) {
        return entity == null ? null : new StateTransitionSubactionHelper(
                entity.getId(),
                entity.getActionName(),
                entity.getProcessName(),
                entity.getIdStateTransit() );
    }

}
