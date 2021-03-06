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
package org.linkki.framework.ui.dialogs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.linkki.util.handler.Handler;

import com.vaadin.ui.Label;

/**
 * A dialog to present an information to the user, who has to confirm it with OK.
 */
public class ConfirmationDialogTest {

    @Test
    public void testCancelCallsOk() {
        Handler handler = mock(Handler.class);
        ConfirmationDialog dialog = new ConfirmationDialog("", new Label(), handler);
        dialog.cancel();
        verify(handler).apply();
    }

}
