package ru.khtu.statemachine.app.mapper;

import org.mapstruct.Mapper;
import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionAttributeDto;
import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionInclusionDto;
import ru.khtu.statemachine.app.data.entity.StateTransitionSubactionAttributeEntity;
import ru.khtu.statemachine.app.data.entity.StateTransitionSubactionInclusionEntity;

@Mapper(componentModel = "spring")
public interface StateTransitSubactionAttributeMapperDto extends MapperDto<StateTransitionSubactionAttributeEntity, StateTransitionSubactionAttributeDto>{

    StateTransitionSubactionAttributeDto toDto(StateTransitionSubactionAttributeEntity entity);

    StateTransitionSubactionAttributeEntity toEntity(StateTransitionSubactionAttributeDto dto);

}
