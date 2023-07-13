package com.jorgepatrick;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utilities {
    static <T> List<T> enumValuesInList(Class<T> enumCls) {
        T[] arr = enumCls.getEnumConstants();
        return arr == null ? Collections.emptyList() : Arrays.asList(arr);
    }
}