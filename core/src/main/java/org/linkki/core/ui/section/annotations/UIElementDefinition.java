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

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nullable;

import com.vaadin.ui.Component;

/**
 * A common interface for annotations that are used to create and bind UI elements in a view
 * generated from an annotated PMO.
 * <p>
 * As annotations can't implement an interface, the {@link UIAnnotationReader} is used to get
 * definition instances for the annotated methods of a (PMO) class.
 * <p>
 * The static methods {@link #isLinkkiBindingDefinition(Annotation)} and {@link #from(Annotation)}
 * can be used to check annotations and create {@link UIElementDefinition} instances from them.
 * 
 * @see UIAnnotationReader
 * @see LinkkiBindingDefinition
 */
public interface UIElementDefinition extends BindingDefinition {


    Component newComponent();

    /** Mandatory attribute that defines the order in which UI-Elements are displayed */
    int position();

    /** Provides a description label next to the UI element */
    String label();

    /** Used by {@link UIButton} to enable or disable its {@link UIButton#label()} */
    boolean showLabel();

    /**
     * Returns {@code true} if the given annotation is a non-null annotation marked as
     * {@link LinkkiBindingDefinition}.
     */
    public static boolean isLinkkiBindingDefinition(@Nullable Annotation annotation) {
        if (annotation == null) {
            return false;
        } else {
            return annotation.annotationType().isAnnotationPresent(LinkkiBindingDefinition.class);
        }
    }

    /**
     * Returns the {@link UIFieldDefinition} for the given annotation. Throws an exception if the
     * annotation is {@code null} or not annotated as a {@link LinkkiBindingDefinition}. In other
     * words, this method should only be invoked if {@link #isLinkkiBindingDefinition(Annotation)}
     * returns {@code true} for the given annotation.
     */
    public static UIElementDefinition from(Annotation annotation) {
        Class<? extends Annotation> annotationClass = requireNonNull(annotation, "annotation must not be null")
                .annotationType();

        LinkkiBindingDefinition[] linkkiElements = annotationClass.getAnnotationsByType(LinkkiBindingDefinition.class);
        if (linkkiElements.length == 0) {
            throw new IllegalArgumentException(
                    annotation + " is not annotated as " + LinkkiBindingDefinition.class.getName());
        }
        Class<? extends UIElementDefinition> elementDefinitionClass = linkkiElements[0].value();

        try {
            return elementDefinitionClass.getConstructor(annotationClass).newInstance(annotation);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
