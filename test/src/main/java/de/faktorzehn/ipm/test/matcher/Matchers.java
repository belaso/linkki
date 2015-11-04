/*******************************************************************************
 * Copyright (c) 2014 Faktor Zehn AG.
 * 
 * Alle Rechte vorbehalten.
 *******************************************************************************/

package de.faktorzehn.ipm.test.matcher;

import java.util.function.Predicate;

public class Matchers {

    public static OptionalPresentMatcher<Object> absent() {
        return new OptionalPresentMatcher<>(false);
    }

    public static OptionalPresentMatcher<Object> present() {
        return new OptionalPresentMatcher<>(true);
    }

    public static <T> OptionalValueMatcher<T> hasValue(T value) {
        return new OptionalValueMatcher<>(value);
    }

    public static <T> PredicateMatcher<T> matches(Predicate<T> function, String description) {
        return new PredicateMatcher<>(function, description);
    }

    public static <T> PredicateMatcher<T> matches(Predicate<T> function) {
        return new PredicateMatcher<>(function, "function that matches");
    }

}
