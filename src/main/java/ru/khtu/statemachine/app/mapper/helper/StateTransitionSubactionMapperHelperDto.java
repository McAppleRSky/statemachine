package ru.khtu.statemachine.app.mapper.helper;

import org.mapstruct.Mapper;
import ru.khtu.statemachine.app.data.dto.StateTransitionSubactionDto;
import ru.khtu.statemachine.app.data.helper.StateTransitionSubactionHelper;
import ru.khtu.statemachine.app.mapper.MapperDto;

@Mapper(componentModel = "spring")
public interface StateTransitionSubactionMapperHelperDto extends MapperDto<StateTransitionSubactionHelper, StateTransitionSubactionDto> {

    @Override
    StateTransitionSubactionDto toDto(StateTransitionSubactionHelper helper);

}
