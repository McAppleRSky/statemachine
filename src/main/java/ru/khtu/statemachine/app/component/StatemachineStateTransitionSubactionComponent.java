package ru.khtu.statemachine.app.component;

import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;

import java.util.List;

public interface StatemachineStateTransitionSubactionComponent {

    void getByStateTransitionsAndPutSubaction(List<StateTransitionHelper> stateTransitions);

}
