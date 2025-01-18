package ru.khtu.statemachine.app.mapper.helper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.khtu.statemachine.app.data.dto.StateTransitionDto;
import ru.khtu.statemachine.app.data.helper.StateTransitionHelper;
import ru.khtu.statemachine.app.mapper.MapperDto;

@Mapper(componentModel = "spring")
public interface StateTransitionMapperHelperDto extends MapperDto<StateTransitionHelper, StateTransitionDto> {

    @Override
    @Mapping(target = "nameOfClass", source = "helper.className")
    @Mapping(target = "nameOfMethod", source = "helper.methodName")
    @Mapping(target = "isReadOnly", source = "helper.readOnly")
    StateTransitionDto toDto(StateTransitionHelper helper);

}
