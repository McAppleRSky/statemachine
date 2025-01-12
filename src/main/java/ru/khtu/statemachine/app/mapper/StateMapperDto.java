package ru.khtu.statemachine.app.mapper;

import org.mapstruct.Mapper;
import ru.khtu.statemachine.app.data.dto.StateDto;
import ru.khtu.statemachine.app.data.entity.StateEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateMapperDto extends MapperDto<StateEntity, StateDto>{

    StateDto toDto(StateEntity entity);

    StateEntity toEntity(StateDto dto);

    List<StateDto> toDtos(List<StateEntity> entities);

    List<StateEntity> toEntities(List<StateDto> dtos);

    default String dtoToStr(StateDto dto) {
        return dto.getWoState();
    }

    List<String> toStrings(List<StateDto> dtos);

}
