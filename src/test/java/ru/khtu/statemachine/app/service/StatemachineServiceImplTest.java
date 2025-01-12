package ru.khtu.statemachine.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.khtu.statemachine.app.service.json.StateTransitionResponseJsonBuilderImpl;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatemachineServiceImplTest {

    private final String DATA_STRUCT_JS = new String("ru/khtu/service/js/ResponseBuilderDataStractTest.json"),
            BUILD_JS = new String("ru/khtu/service/js/ResponseBuilderBuildTest.json"),
            STATES_JS = new String("ru/khtu/statemachine/app/service/ResponseBuilderStatesTest.json"),
            TRANSIT_JS = new String("ru/khtu/statemachine/app/service/ResponseBuilderTransitTest.json"),
            SUBACTION_JS = new String("ru/khtu/statemachine/app/service/ResponseBuilderSubactionTest.json");
    @Test
    void buildStatesTest() throws IOException {
        Map<String, Object> dataStruct = new ObjectMapper().readValue(
                ResourceHelper.testClassLoad(DATA_STRUCT_JS),
                new TypeReference<Map<String, Object>>() {} );
        /*String actual = new ObjectMapper().writeValueAsString(
                new StateTransitionResponseJsonBuilderImpl(WorkObject.W_PEOPLE.getString())
                        .setStates(getListMap(dataStruct, WorkObject.W_PEOPLE, Table.STATE))
                        .build() );*/
//        assertEquals(ResourceHelper.testClassLoad(STATES_JS), actual);
    }

}
