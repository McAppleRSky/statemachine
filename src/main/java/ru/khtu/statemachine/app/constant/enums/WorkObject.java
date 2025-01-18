package ru.khtu.statemachine.app.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum WorkObject {

    CLASSIFICATION(
            new String("Classification"),
            new String("Classification") ),
    W_SPACE(
            new String("Space"),
            new String("wSpace") ),
    W_PEOPLE(
            new String("People"),
            new String("wPeople") ),
    ;

    private final String title, string;

    private static final Map<String, WorkObject> map = new HashMap<>();

    static {
        for (WorkObject enumItem : WorkObject.values()) {
            map.put(enumItem.getString(), enumItem);
        }
    }

    public static WorkObject get(String string) {
        for (String stringKey : map.keySet()) {
            if (stringKey.equals(string)) {
                return map.get(stringKey);
            }
        }
        throw new NotImplementedException("NotImplementedException for string : " + string);
    }

    public static final Map<Catalog, EnumSet<WorkObject>> mapCatalogWorkObjectSet = new LinkedHashMap<>();
    private static final Map<String, WorkObject> mapStringWorkObject = new LinkedHashMap<>();

    static {
        for (WorkObject enumItem : WorkObject.values()) {
            mapStringWorkObject.put(enumItem.getString(), enumItem);
        }
        mapCatalogWorkObjectSet.put(
                Catalog.CLASSIFICATION,
                EnumSet.of(
                        WorkObject.CLASSIFICATION ) );
        mapCatalogWorkObjectSet.put(
                Catalog.LOCATION,
                EnumSet.of(
                        WorkObject.W_SPACE ) );
        mapCatalogWorkObjectSet.put(
                Catalog.PEOPLE,
                EnumSet.of(
                        WorkObject.W_PEOPLE ) );
    }

}
