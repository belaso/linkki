/*
 * Copyright Faktor Zehn AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.linkki.core.matcher;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matcher;
import org.hamcrest.core.CombinableMatcher;
import org.linkki.core.message.Message;
import org.linkki.core.message.ObjectProperty;

import com.vaadin.server.ErrorMessage.ErrorLevel;

public class MessageMatchers {

    public static EmptyMessageListMatcher emptyMessageList() {
        return new EmptyMessageListMatcher();
    }

    public static MessageListSizeMatcher hasSize(int size) {
        return new MessageListSizeMatcher(equalTo(size));
    }

    public static MessageListSizeMatcher hasSize(Matcher<Integer> intMatcher) {
        return new MessageListSizeMatcher(intMatcher);
    }

    public static MessageListObjectPropertyMatcher hasMessageFor(Object o, String property) {
        return new MessageListObjectPropertyMatcher(new ObjectProperty(o, property));
    }

    public static MessageListObjectPropertyMatcher hasMessagesFor(int count, Object o, String property) {
        return new MessageListObjectPropertyMatcher(new ObjectProperty(o, property), count);
    }

    public static MessageListMessageMatcher hasMessage(String code) {
        return new MessageListMessageMatcher(new MessageCodeMatcher(code));
    }

    public static MessageListMessageMatcher hasInfoMessage(String code) {
        return new MessageListMessageMatcher(codeAndErrorLevel(code, ErrorLevel.INFORMATION));
    }

    public static MessageListMessageMatcher hasWarningMessage(String code) {
        return new MessageListMessageMatcher(codeAndErrorLevel(code, ErrorLevel.WARNING));
    }

    public static MessageListMessageMatcher hasErrorMessage(String code) {
        return new MessageListMessageMatcher(codeAndErrorLevel(code, ErrorLevel.ERROR));
    }

    private static Matcher<Message> codeAndErrorLevel(String code, ErrorLevel errorLevel) {
        return CombinableMatcher.both(new MessageCodeMatcher(code)).and(new MessageErrorLevelMatcher(errorLevel));
    }

}
