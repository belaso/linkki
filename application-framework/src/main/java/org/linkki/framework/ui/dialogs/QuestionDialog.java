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

import org.linkki.util.handler.Handler;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A modal dialog that asks the user a question that can be confirmed with OK (and not confirmed
 * with Cancel).
 */
public class QuestionDialog extends OkCancelDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new dialog.
     * 
     * @param caption The caption.
     * @param content A component containing the question to ask.
     * @param okHandler A function that is executed when the OK button was pressed.
     */
    public QuestionDialog(String caption, Component content, Handler okHandler) {
        super(caption, content, okHandler, ButtonOption.OK_CANCEL);
    }

    /**
     * Opens the dialog.
     * 
     * @param caption The caption.
     * @param question The question to ask the user.
     * @param okHandler A function that is executed when the OK button was pressed.
     */
    public static QuestionDialog open(String caption, String question, Handler okHandler) {
        Label questionLabel = new Label();
        questionLabel.setValue(question);
        questionLabel.setStyleName(ValoTheme.LABEL_H3);
        questionLabel.setContentMode(ContentMode.HTML);
        return open(caption, questionLabel, okHandler);
    }

    /**
     * Opens the dialog.
     * 
     * @param caption The caption.
     * @param content A component containing the question to ask.
     * @param okHandler A function that is executed when the OK button was pressed.
     */
    public static QuestionDialog open(String caption,
            Component content,
            Handler okHandler) {
        QuestionDialog d = new QuestionDialog(caption, content, okHandler);
        UI.getCurrent().addWindow(d);
        return d;
    }

}
