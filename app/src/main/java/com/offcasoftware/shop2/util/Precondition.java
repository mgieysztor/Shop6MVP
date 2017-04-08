package com.offcasoftware.shop2.util;

/**
 * Created by RENT on 2017-04-06.
 */

public final class Precondition {
    private Precondition() {
    }

    ;

    public static <T>T checkNotNull(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        } else {
            return t;
        }

    }
}
