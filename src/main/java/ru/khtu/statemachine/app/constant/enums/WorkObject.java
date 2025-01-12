package ru.khtu.statemachine.app.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum WorkObject {

    W_PEOPLE(
            new String("wPeople") ),
    W_SPACE(
            new String("wSpace") );

    private final String string;


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

}
