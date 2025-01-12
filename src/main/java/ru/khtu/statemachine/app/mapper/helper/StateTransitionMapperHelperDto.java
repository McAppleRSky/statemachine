package ru.khtu.statemachine.app.mapper.helper;

import org.mapstruct.Mapper;
import ru.khtu.statemachine.app.data.dto.StateTransitionDto;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.mapper.MapperDto;

@Mapper(componentModel = "spring")
public interface StateTransitionMapperHelperDto extends MapperDto<StateTransitionHelper, StateTransitionDto> {

    @Override
    StateTransitionDto toDto(StateTransitionHelper helper);

}
