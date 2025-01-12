package ru.khtu.statemachine.app.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khtu.statemachine.app.data.dto.StateDto;
import ru.khtu.statemachine.app.constant.enums.WorkObject;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.mapper.StateMapperDto;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionMapperHelper;
import ru.khtu.statemachine.app.repository.StateRepository;
import ru.khtu.statemachine.app.repository.StateTransitionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Component
public class StatemachineComponentImpl implements StatemachineComponent {

    private final StateRepository stateRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final StateMapperDto stateMapper;
    private final StateTransitionRepository stateTransitionRepository;
    private final StatemachineStateTransitionSubactionComponent statemachineStateTransitionSubactionComponent;

    public Map.Entry<List<StateDto>, List<StateTransitionHelper>> getStateTransition(WorkObject workObject) {
        List<StateDto> state = stateMapper.toDtos(
                stateRepository.findByWoName(workObject.getString()) );
        List<StateTransitionHelper> transition = StateTransitionMapperHelper.toHelper(
                stateTransitionRepository.findByWoName(workObject.getString()) );
        statemachineStateTransitionSubactionComponent.getByStateTransitionsAndPutSubaction(transition);
        return Collections.singletonMap(state, transition).entrySet().iterator().next();
    }

}
