package com.ghclient.app.util;

import com.ghclient.app.BuildConfig;

public final class Assertion {

    public static void throwIfConditionNotMet(boolean condition, String assertion) {
        if (BuildConfig.DEBUG && !condition) {
            throw new AssertionError(assertion);
        }
    }

}
