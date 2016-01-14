/*******************************************************************************
 * Copyright (c) 2014 Faktor Zehn AG.
 * 
 * Alle Rechte vorbehalten.
 *******************************************************************************/

package org.linkki.test.matcher;

import org.faktorips.runtime.MessageList;
import org.faktorips.runtime.ObjectProperty;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class MessageListObjectPropertyMatcher extends TypeSafeMatcher<MessageList> {

    private final ObjectProperty objectProperty;

    private final int count;

    public MessageListObjectPropertyMatcher(ObjectProperty objectProperty) {
        this(objectProperty, 1);
    }

    public MessageListObjectPropertyMatcher(ObjectProperty objectProperty, int count) {
        this.objectProperty = objectProperty;
        this.count = count;
    }

    @Override
    public void describeTo(Description description) {
        if (count == 1) {
            description.appendText("a message for " + objectProperty);
        } else {
            description.appendText(count + " messages for " + objectProperty);
        }
    }

    @Override
    protected boolean matchesSafely(MessageList ml) {
        return ml.getMessagesFor(objectProperty.getObject(), objectProperty.getProperty()).size() == count;
    }

}
