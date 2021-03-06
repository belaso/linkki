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
package org.linkki.core.ui.section.annotations;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.junit.Test;
import org.linkki.core.ui.section.annotations.adapters.CheckboxBindingDefinition;
import org.linkki.core.ui.section.annotations.adapters.ComboboxBindingDefinition;
import org.linkki.core.ui.section.annotations.adapters.DateFieldBindingDefinition;
import org.linkki.core.ui.section.annotations.adapters.DoubleFieldBindingDefinition;
import org.linkki.core.ui.section.annotations.adapters.IntegerFieldBindingDefinition;
import org.linkki.core.ui.section.annotations.adapters.LabelBindingDefinition;
import org.linkki.core.ui.section.annotations.adapters.TextAreaBindingDefinition;
import org.linkki.core.ui.section.annotations.adapters.TextFieldBindingDefinition;

import com.vaadin.ui.Component;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class UIElementDefinitionTest {

    @UICheckBox(position = 0, caption = "")
    @UIComboBox(position = 0)
    @UIDateField(position = 0)
    @UIDoubleField(position = 0)
    @UIIntegerField(position = 0)
    @UITextArea(position = 0)
    @UITextField(position = 0)
    @UILabel(position = 0)
    @OverridingMethodsMustInvokeSuper
    @UIFooBar
    @UICustom
    public void annotatedMethod() {
        // Nothing to do
    }

    private <T extends Annotation> T annotation(Class<T> annotationClass) {
        try {
            T annotation = getClass().getMethod("annotatedMethod", new Class<?>[] {}).getAnnotation(annotationClass);
            return requireNonNull(annotation, () -> "Missing annotation @" + annotationClass.getSimpleName());
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsLinkkiBindingDefinition() {
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(null), is(false));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(OverridingMethodsMustInvokeSuper.class)),
                   is(false));

        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UICheckBox.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UIComboBox.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UIDateField.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UIDoubleField.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UIIntegerField.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UITextArea.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UITextField.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UILabel.class)), is(true));
        assertThat(UIElementDefinition.isLinkkiBindingDefinition(annotation(UICustom.class)), is(true));
    }

    @Test
    public void testFrom() {
        //@formatter:off
        assertThat(UIElementDefinition.from(annotation(UICheckBox.class)), is(instanceOf(CheckboxBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UIComboBox.class)), is(instanceOf(ComboboxBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UIDateField.class)), is(instanceOf(DateFieldBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UIDoubleField.class)), is(instanceOf(DoubleFieldBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UIIntegerField.class)), is(instanceOf(IntegerFieldBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UITextArea.class)), is(instanceOf(TextAreaBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UITextField.class)), is(instanceOf(TextFieldBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UILabel.class)), is(instanceOf(LabelBindingDefinition.class)));
        assertThat(UIElementDefinition.from(annotation(UICustom.class)), is(instanceOf(UICustomBindingDefinition.class)));
        //@formatter:on
    }

    @SuppressWarnings("null")
    @Test(expected = NullPointerException.class)
    public void testFrom_ThrowsExceptionForNullAnnotation() {
        UIElementDefinition.from(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFrom_ThrowsExceptionForNonLinkkiElementAnnotation() {
        UIElementDefinition.from(annotation(OverridingMethodsMustInvokeSuper.class));
    }

    @Test(expected = RuntimeException.class)
    public void testFrom_ThrowsRuntimeExceptionIfInstantiationFails() {
        UIElementDefinition.from(annotation(UIFooBar.class));
    }

}

interface UIFooBarBindingDefinition extends UIElementDefinition {
    // no implementation class -> instantiation will fail
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@LinkkiBindingDefinition(UIFooBarBindingDefinition.class)
@interface UIFooBar {
    // dummy
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@LinkkiBindingDefinition(UICustomBindingDefinition.class)
@interface UICustom {
    // dummy
}

@SuppressWarnings("null")
@SuppressFBWarnings
class UICustomBindingDefinition implements UIElementDefinition {

    /**
     * @param uiCustom ignored
     */
    public UICustomBindingDefinition(UICustom uiCustom) {
        // ignored
    }

    @Override
    public EnabledType enabled() {
        return null;
    }

    @Override
    public VisibleType visible() {
        return null;
    }

    @Override
    public RequiredType required() {
        return null;
    }

    @Override
    public Component newComponent() {
        return null;
    }

    @Override
    public int position() {
        return 0;
    }

    @Override
    public String label() {
        return null;
    }

    @Override
    public boolean showLabel() {
        return false;
    }

}
