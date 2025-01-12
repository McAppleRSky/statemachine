package ru.khtu.statemachine.app.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khtu.statemachine.app.constant.enums.WorkObject;
import ru.khtu.statemachine.app.service.StatemachineService;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StatemachineController {

    private final StatemachineService statemachineService;
    private String PATH_KEY_WO_NAME = new String("woName");

    @RequestMapping(value = "/api/0.0.1/state-transition/{woName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> get(@PathVariable Map<String, String> woName) {
        Map<String, Object> result = null;
        switch (WorkObject.get(woName.get(PATH_KEY_WO_NAME))) {
            case W_SPACE:
                throw new NotImplementedException(PATH_KEY_WO_NAME + " - " + WorkObject.W_SPACE.getString());
            case W_PEOPLE:
                result = statemachineService.getStateTransition(WorkObject.W_PEOPLE);
                break;
        }
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
