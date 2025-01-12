package ru.khtu.statemachine.app.mapper;

import org.mapstruct.Mapper;
import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionInclusionDto;
import ru.khtu.statemachine.app.data.entity.StateTransitionSubactionInclusionEntity;

@Mapper(componentModel = "spring")
public interface StateTransitSubactionInclusionMapperDto extends MapperDto<StateTransitionSubactionInclusionEntity, StateTransitionSubactionInclusionDto>{

    StateTransitionSubactionInclusionDto toDto(StateTransitionSubactionInclusionEntity entity);

    StateTransitionSubactionInclusionEntity toEntity(StateTransitionSubactionInclusionDto dto);

}
