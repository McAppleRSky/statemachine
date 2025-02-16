package ru.khtu.statemachine.app.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khtu.statemachine.app.data.helper.StateTransitionSubactionHelper;
import ru.khtu.statemachine.app.mapper.StateTransitSubactionAttributeMapperDto;
import ru.khtu.statemachine.app.mapper.StateTransitSubactionInclusionMapperDto;
import ru.khtu.statemachine.app.repository.StateTransitionSubactionAttributeRepository;
import ru.khtu.statemachine.app.repository.StateTransitionSubactionInclusionRepository;

@RequiredArgsConstructor
@Component
public class StatemachineStateTransitionSubactionInclusionAttributeComponentImpl implements StatemachineStateTransitionSubactionInclusionAttributeComponent {

    private final StateTransitionSubactionInclusionRepository stateTransitionSubactionInclusionRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final StateTransitSubactionInclusionMapperDto stateTransitSubactionInclusionMapper;
    private final StateTransitionSubactionAttributeRepository stateTransitionSubactionAttributeRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final StateTransitSubactionAttributeMapperDto stateTransitSubactionAttributeMapper;

    @Override
    public void getByStateTransitionsSubactionAndPut(StateTransitionSubactionHelper stateTransitionSubaction) {
        stateTransitionSubaction.setStateTransitionSubactionInclusionDto(
                stateTransitSubactionInclusionMapper.toDtos(
                        stateTransitionSubactionInclusionRepository.findByStateTransitionIdAndStateTransitionSubactionId(
                                stateTransitionSubaction.getIdStateTransit(),
                                stateTransitionSubaction.getId() ) ) );
        stateTransitionSubaction.setStateTransitionSubactionAttributeDto(
                stateTransitSubactionAttributeMapper.toDtos(
                        stateTransitionSubactionAttributeRepository.findByStateTransitionIdAndStateTransitionSubactionId(
                                stateTransitionSubaction.getIdStateTransit(),
                                stateTransitionSubaction.getId() ) ) );
    }

}
