package com.github.token;

/**
 * Created by leon on 2018/8/2.
 */
public enum IntervalEnum {
    INTERVAL_HALF(30),INTERVAL_ONE(60),INTERVAL_FIVE(300);

    private int value;

    IntervalEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
