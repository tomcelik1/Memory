package com.tom.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MemoryCell {

    BLUE,
    RED,
    GREEN,
    YELLOW;

    public static MemoryCell valueOf(int i) {
        switch (i) {
            case 0:
                return BLUE;
            case 1:
                return RED;
            case 2:
                return GREEN;
            case 3:
                return YELLOW;
            default:
                return null;
        }
    }

    public static List<MemoryCell> all() {
        return new ArrayList<>(Arrays.asList(values()));
    }

}
