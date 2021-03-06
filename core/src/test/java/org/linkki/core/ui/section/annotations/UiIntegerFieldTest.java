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

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

import org.junit.Test;

import com.vaadin.data.Buffered;
import com.vaadin.ui.TextField;

public class UiIntegerFieldTest {

    protected static class TestModelObjectWithPrimitiveInteger {

        private int value = 0;

        public int getIntegerValue() {
            return value;
        }

        public void setIntegerValue(int i) {
            this.value = i;
        }
    }

    protected static class TestModelObjectWithObjectInteger {

        @Nullable
        private Integer value = null;

        @CheckForNull
        public Integer getIntegerValue() {
            return value;
        }

        public void setIntegerValue(Integer i) {
            this.value = i;
        }
    }

    @UISection
    protected static class TestPmo {

        private final Object modelObject;

        public TestPmo(Object modelObject) {
            super();
            this.modelObject = modelObject;
        }

        @UIIntegerField(position = 1, modelAttribute = "integerValue")
        public void integerValue() {
            // data binding
        }

        @ModelObject
        public Object getModelObject() {
            return modelObject;
        }
    }

    /**
     * Returns a {@code TextField} that is bound to the given model object using the IPM data
     * binder. The {@code TextField} is part of a mostly mocked UI so that a rudimentary Vaadin
     * environment is in place.
     * 
     * @param modelObject the model object to which the {@code TextField} is bound
     * @return a {@code TextField} that is bound to the model object
     */
    private TextField createIntegerTextField(Object modelObject) {
        TestPmo pmo = new TestPmo(modelObject);
        return (TextField)TestUi.componentBoundTo(pmo);
    }

    @Test
    public void testSetValueWithPrimitiveIntegerInModelObject() {
        TextField textField = createIntegerTextField(new TestModelObjectWithPrimitiveInteger());

        // No assertions needed, we just make sure no exception is thrown
        textField.setValue("0");
    }

    @Test(expected = Buffered.SourceException.class)
    public void testSetValueWithPrimitiveIntegerInModelObjectFailsForNull() {
        TextField textField = createIntegerTextField(new TestModelObjectWithPrimitiveInteger());
        textField.setValue(null);
    }

    @Test
    public void testSetValueWithObjectIntegerInModelObject() {
        TextField textField = createIntegerTextField(new TestModelObjectWithObjectInteger());

        // No assertions needed, we just make sure no exception is thrown
        textField.setValue(null);
        textField.setValue("0");
    }
}
