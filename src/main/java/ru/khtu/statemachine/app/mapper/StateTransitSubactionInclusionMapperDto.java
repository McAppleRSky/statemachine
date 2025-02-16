package ru.khtu.statemachine.app.mapper;

import org.mapstruct.Mapper;
import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionInclusionDto;
import ru.khtu.statemachine.app.data.entity.StateTransitionSubactionInclusionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateTransitSubactionInclusionMapperDto extends MapperDto<StateTransitionSubactionInclusionEntity, StateTransitionSubactionInclusionDto>{

    StateTransitionSubactionInclusionDto toDto(StateTransitionSubactionInclusionEntity entity);

    StateTransitionSubactionInclusionEntity toEntity(StateTransitionSubactionInclusionDto dto);

    List<StateTransitionSubactionInclusionDto> toDtos(List<StateTransitionSubactionInclusionEntity> entities);

    List<StateTransitionSubactionInclusionEntity> toEntities(List<StateTransitionSubactionInclusionDto> dtos);

    /*default String dtoToStr(StateTransitionSubactionAttributeDto dto) {
        return dto.getWoState();
    }*/

//    List<String> toStrings(List<StateTransitionSubactionInclusionDto> dtos);

}
