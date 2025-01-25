package ru.khtu.statemachine.app.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khtu.statemachine.app.constant.enums.Catalog;
import ru.khtu.statemachine.app.constant.enums.WorkObject;
import ru.khtu.statemachine.app.service.StatemachineService;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class StatemachineController {

    private final StatemachineService statemachineService;

    @RequestMapping(value = "/api/0.0.1/catalog", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCatalog() {
        List<Object> result = new ArrayList<>();
        for (ru.khtu.statemachine.app.constant.enums.Catalog catalog : ru.khtu.statemachine.app.constant.enums.Catalog.values()) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put(new String("title"), catalog.getTitle());
            map.put(new String("name"), catalog.getString());
            result.add(map);
        }
        if (result != null) {
            return new ResponseEntity<>(Collections.singletonMap(PATH_KEY_CATALOG, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private String PATH_KEY_CATALOG = new String("catalog");

    @RequestMapping(value = "/api/0.0.1/work-object/{catalog}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getWorkObject(@PathVariable Map<String, String> catalog) {
        List<Object> result = new ArrayList<>();
        String s = catalog.get(PATH_KEY_CATALOG);
        switch (Catalog.get(catalog.get(PATH_KEY_CATALOG))) {
            case CLASSIFICATION:
                throw new NotImplementedException(
                        PATH_KEY_CATALOG + " - " + Catalog.CLASSIFICATION.getString() );
            case LOCATION:
                throw new NotImplementedException(
                        PATH_KEY_CATALOG + " - " + Catalog.LOCATION.getString() );
            case PEOPLE:
                for (WorkObject workObject : WorkObject.mapCatalogWorkObjectSet.get(Catalog.PEOPLE)) {
                    Map<Object, Object> map = new HashMap<>();
                    map.put(new String("title"), workObject.getTitle());
                    map.put(new String("name"), workObject.getString());
                    result.add(map);
                }
                break;
        }
        if (result != null) {
            return new ResponseEntity<>(Collections.singletonMap(catalog.get(PATH_KEY_CATALOG), result), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private String PATH_KEY_WO_NAME = new String("woName");

    @RequestMapping(value = "/api/0.0.1/state-transition/{woName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStateTransition(@PathVariable Map<String, String> woName) {
        Map<String, Object> result = null;
        switch (WorkObject.get(woName.get(PATH_KEY_WO_NAME))) {
            case CLASSIFICATION:
                throw new NotImplementedException(PATH_KEY_WO_NAME + " - " + WorkObject.CLASSIFICATION.getString());
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
