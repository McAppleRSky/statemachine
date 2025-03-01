package ru.khtu.statemachine.app.service.json;

import org.apache.commons.lang3.NotImplementedException;
import ru.khtu.statemachine.app.component.StatemachineComponent;
import ru.khtu.statemachine.app.constant.enums.ResponseKey;
import ru.khtu.statemachine.app.constant.enums.WorkObject;
import ru.khtu.statemachine.app.data.dto.StateDto;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.data.helper.StateTransitionSubactionHelper;
import ru.khtu.statemachine.app.mapper.AttributeMapper;
import ru.khtu.statemachine.app.mapper.InclusionMapper;
import ru.khtu.statemachine.app.mapper.ModuleFormMapper;
import ru.khtu.statemachine.app.mapper.StateMapperDto;
import ru.khtu.statemachine.app.mapper.helper.jackson.StateTransitionJacksonMapperHelperDto;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionMapperHelperDto;
import ru.khtu.statemachine.app.mapper.helper.jackson.StateTransitionSubactionJacksonMapperHelperDto;
import ru.khtu.statemachine.app.mapper.helper.StateTransitionSubactionMapperHelperDto;

import java.util.*;

public class StateTransitionResponseJsonBuilderImpl
        extends StateTransitionResponseJsonBuilderAbstract implements StateTransitionResponseJsonBuilder {

    private final StatemachineComponent statemachineComponent;
    private final StateMapperDto stateMapper;
    private final List<StateDto> stateData;
    private final List<StateTransitionHelper> tarnsitionData;
    private Map<StateTransitionHelper, Map<String, Object>> mapStateTransitionHelperToStringObject;
    private Map<StateTransitionSubactionHelper, Map<String, Object>> mapStateTransitionSubactionHelperToStringObject;

    public StateTransitionResponseJsonBuilderImpl(WorkObject workObject, StatemachineComponent statemachineComponent) {
        super(workObject);
        this.statemachineComponent = statemachineComponent;
        this.stateMapper = this.statemachineComponent.getStateMapper();
        Map.Entry<
                List<StateDto>,
                List<StateTransitionHelper> > data = this.statemachineComponent.getStateTransition(workObject);
        this.stateData = data.getKey();
        this.tarnsitionData = data.getValue();
    }

    @Override
    public StateTransitionResponseJsonBuilder setState() {
        super.stateTransition.add(
                Collections.singletonMap(ResponseKey.STATES.getString(), this.stateMapper.toStrings(this.stateData) ) );
        return this;
    }

    @Override
    public StateTransitionResponseJsonBuilder setTransition(StateTransitionMapperHelperDto stateTransitionMapperHelperDto) {
        Map.Entry<
                ArrayList<Map<String, Object>>,
                Map<StateTransitionHelper, Map<String, Object>> > entryMapList
                = new StateTransitionJacksonMapperHelperDto(
                        stateTransitionMapperHelperDto ).mapHelperToStringObject(this.tarnsitionData);
        if (entryMapList != null) {
            super.stateTransition.add(
                    Collections.singletonMap(ResponseKey.TRANSIT.getString(), entryMapList.getKey()) );
            this.mapStateTransitionHelperToStringObject = entryMapList.getValue();
        }
        return this;
    }

    @Override
    public StateTransitionResponseJsonBuilder setSubaction(
            StateTransitionSubactionMapperHelperDto stateTransitionSubactionMapperHelperDto ) {
        this.mapStateTransitionSubactionHelperToStringObject
                = new StateTransitionSubactionJacksonMapperHelperDto(
                        stateTransitionSubactionMapperHelperDto ).mapHelperToStringObject(this.tarnsitionData);
        if (this.mapStateTransitionSubactionHelperToStringObject != null) {
            for (StateTransitionHelper stateTransitionHelper : this.tarnsitionData) {
                if (stateTransitionHelper.getStateTransitionSubaction() != null
                        && this.mapStateTransitionHelperToStringObject != null) {
                    this.mapStateTransitionHelperToStringObject.get(stateTransitionHelper)
                            .put(
                                    ResponseKey.SUBACTION.getString(),
                                    Collections.singletonList(
                                            this.mapStateTransitionSubactionHelperToStringObject
                                                    .get(stateTransitionHelper.getStateTransitionSubaction())) );
                } else {
                    this.mapStateTransitionHelperToStringObject.get(stateTransitionHelper)
                            .put(ResponseKey.SUBACTION.getString(), new ArrayList<>(0));
                }
            }
        }
        return this;
    }

    @Override
    public StateTransitionResponseJsonBuilder setInclusion(/*StateTransitSubactionInclusionMapperDto stateTransitSubactionInclusionMapperDto*/) {
        for (StateTransitionHelper stateTransitionHelper : this.tarnsitionData) {
            if ( stateTransitionHelper.getStateTransitionSubaction() != null
                    && stateTransitionHelper.getStateTransitionSubaction().getStateTransitionSubactionInclusionDto() != null
                    && !stateTransitionHelper.getStateTransitionSubaction().getStateTransitionSubactionInclusionDto().isEmpty() ) {
                StateTransitionSubactionHelper stateTransitionSubaction = stateTransitionHelper.getStateTransitionSubaction();
                Map<String, Object> stringObjectMap = this.mapStateTransitionSubactionHelperToStringObject.get(stateTransitionSubaction);
                stringObjectMap.put(
                        ResponseKey.INCLUSION.getString(),
                        InclusionMapper.mapListToListMap(stateTransitionSubaction.getStateTransitionSubactionInclusionDto()) );
//                throw new NotImplementedException("Inclusion not implemented");
            }
        }
        return this;
    }

    @Override
    public StateTransitionResponseJsonBuilder setAttribute(/*StateTransitSubactionAttributeMapperDto stateTransitSubactionAttributeMapperDto*/) {
        for (StateTransitionHelper stateTransitionHelper : this.tarnsitionData) {
            if ( stateTransitionHelper.getStateTransitionSubaction() != null
                    && stateTransitionHelper.getStateTransitionSubaction().getStateTransitionSubactionAttributeDto() != null
                    && !stateTransitionHelper.getStateTransitionSubaction().getStateTransitionSubactionAttributeDto().isEmpty() ) {
                StateTransitionSubactionHelper stateTransitionSubaction = stateTransitionHelper.getStateTransitionSubaction();
                Map<String, Object> stringObjectMap = this.mapStateTransitionSubactionHelperToStringObject.get(stateTransitionSubaction);
                stringObjectMap.put(
                        ResponseKey.ATTRIBUTE.getString(),
                        AttributeMapper.mapListToListMap(stateTransitionSubaction.getStateTransitionSubactionAttributeDto()) );
//                throw new NotImplementedException("Attribute not implemented");
            }
        }
        return this;
    }

    @Override
    public StateTransitionResponseJsonBuilder setModuleForm() {
        for (StateTransitionHelper stateTransitionHelper : this.tarnsitionData) {
            if ( stateTransitionHelper.getStateTransitionSubaction() != null
                    && stateTransitionHelper.getStateTransitionSubaction().getStateTransitionSubactionModuleFormDto() != null
                    && !stateTransitionHelper.getStateTransitionSubaction().getStateTransitionSubactionModuleFormDto().isEmpty() ) {
                StateTransitionSubactionHelper stateTransitionSubaction = stateTransitionHelper.getStateTransitionSubaction();
                Map<String, Object> stringObjectMap = this.mapStateTransitionSubactionHelperToStringObject.get(stateTransitionSubaction);
                stringObjectMap.put(
                        ResponseKey.MODULE_FORM.getString(),
                        ModuleFormMapper.mapListToListMap(stateTransitionSubaction.getStateTransitionSubactionModuleFormDto()) );
            }
        }
        return this;
    }

    @Override
    public Map<String, Object> build() {
        return super.mapResult;
    }

}
