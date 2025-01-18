package ru.khtu.statemachine.app.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum Catalog {

    CLASSIFICATION("Classification", "Classification"),
    LOCATION("Location", "Location"),
    PEOPLE("People", "People"),
    ;

    private final String title, string;

    private static final Map<String, Catalog> map = new HashMap<>();

    static {
        for (Catalog enumItem : Catalog.values()) {
            map.put(enumItem.getString(), enumItem);
        }
    }

    public static Catalog get(String string) {
        for (String stringKey : map.keySet()) {
            if (stringKey.equals(string)) {
                return map.get(stringKey);
            }
        }
        throw new NotImplementedException("NotImplementedException for string : " + string);
    }

}
