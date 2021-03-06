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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an annotation used to mark a UI element created by Linkki, such as {@link UILabel} or
 * {@link UITextField}.
 * <p>
 * Every such annotation is accompanied by a {@link UIElementDefinition} which in turn is used by
 * the {@link UIAnnotationReader} to create the actual UI element based on the annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface LinkkiBindingDefinition {

    /**
     * The {@link UIElementDefinition} used to implement the annotated Annotation.
     * 
     * @see UIElementDefinition
     */
    Class<? extends UIElementDefinition> value();

}
