package ru.khtu.statemachine.app.component;

import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.data.helper.StateTransitionSubactionHelper;

import java.util.List;

public interface StatemachineStateTransitionSubactionInclusionAttributeComponent {

    void getByStateTransitionsSubactionAndPut(StateTransitionSubactionHelper stateTransitionSubaction);

}
