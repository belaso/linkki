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
package org.linkki.core.ui.section.annotations.adapters;

import static java.util.Objects.requireNonNull;

import org.linkki.core.ui.section.annotations.AvailableValuesType;
import org.linkki.core.ui.section.annotations.EnabledType;
import org.linkki.core.ui.section.annotations.RequiredType;
import org.linkki.core.ui.section.annotations.UICustomField;
import org.linkki.core.ui.section.annotations.UIFieldDefinition;
import org.linkki.core.ui.section.annotations.VisibleType;

import com.vaadin.ui.Component;

public class CustomFieldBindingDefinition implements UIFieldDefinition {

    private final UICustomField uiCustomField;

    public CustomFieldBindingDefinition(UICustomField uiCustomField) {
        this.uiCustomField = requireNonNull(uiCustomField, "uiCustomField must not be null");
    }

    @Override
    public Component newComponent() {
        try {
            return uiCustomField.uiControl().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int position() {
        return uiCustomField.position();
    }

    @Override
    public String label() {
        return uiCustomField.label();
    }

    @Override
    public EnabledType enabled() {
        return uiCustomField.enabled();
    }

    @Override
    public RequiredType required() {
        return uiCustomField.required();
    }

    @Override
    public VisibleType visible() {
        return uiCustomField.visible();
    }

    @Override
    public AvailableValuesType availableValues() {
        return uiCustomField.content();
    }

    @Override
    public String modelAttribute() {
        return uiCustomField.modelAttribute();
    }

    @Override
    public boolean showLabel() {
        return !uiCustomField.noLabel();
    }

    @Override
    public String modelObject() {
        return uiCustomField.modelObject();
    }
}