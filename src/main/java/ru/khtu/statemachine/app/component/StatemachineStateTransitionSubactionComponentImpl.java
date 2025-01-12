package ru.khtu.statemachine.app.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionSubactionMapperHelper;
import ru.khtu.statemachine.app.repository.StateTransitionSubactionRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StatemachineStateTransitionSubactionComponentImpl implements StatemachineStateTransitionSubactionComponent {

    private final StateTransitionSubactionRepository stateTransitionSubactionRepository;
    private final StatemachineStateTransitionSubactionInclusionAttributeComponent statemachineStateTransitionSubactionInclusionAttributeComponent;

    @Override
    public void getByStateTransitionsAndPutSubaction(List<StateTransitionHelper> stateTransitions) {
        for (StateTransitionHelper stateTransition : stateTransitions) {
            stateTransition.setStateTransitionSubaction(
                    StateTransitionSubactionMapperHelper.toHelper(
                            stateTransitionSubactionRepository.findById(stateTransition.getId() ) ) );
            if (stateTransition.getStateTransitionSubaction() != null) {
                statemachineStateTransitionSubactionInclusionAttributeComponent.getByStateTransitionsSubactionAndPut(
                        stateTransition.getStateTransitionSubaction() );
            }
        }
    }

}
