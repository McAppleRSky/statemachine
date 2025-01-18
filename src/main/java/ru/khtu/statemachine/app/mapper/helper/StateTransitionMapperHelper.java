package ru.khtu.statemachine.app.mapper.helper;

import ru.khtu.statemachine.app.data.entity.StateTransitionEntity;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;

import java.util.ArrayList;
import java.util.List;

public class StateTransitionMapperHelper// extends MapperDto<StateTransitionEntity, StateTransitionHelper>
{

    public static List<StateTransitionHelper> toHelper(List<StateTransitionEntity> entities) {
        List<StateTransitionHelper> result = new ArrayList<>();
        for (StateTransitionEntity entity : entities) {
            result.add(
                    new StateTransitionHelper(
                            entity.getId(),
                            entity.getWoName(),
                            entity.getActionName(),
                            entity.getActionLabel(),
                            entity.getCurrentState(),
                            entity.getNextState(),
                            entity.getCustomUrl(),
                            entity.getClassName(),
                            entity.getMethodName(),
                            entity.getDefaultDisplay(),
                            entity.getReadOnly(),
                            entity.getCloseWindow(),
                            entity.getSecondaryAction() ) );
        }
        return result;
    }

}
