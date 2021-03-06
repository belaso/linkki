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
package org.linkki.core.binding.dispatcher.accessor;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

/**
 * Factory class to create {@code PropertyDescriptor}s for properties of a class. In contrast to the
 * java {@link Introspector} we do not need to search for all existing properties because we already
 * know the name of the property. Further this inspector does support default implementation in
 * interfaces whereas the {@link Introspector} does not.
 */
public class PropertyDescriptorFactory {

    private static final String IS_PREFIX = "is";

    private PropertyDescriptorFactory() {
        // do nothing
    }

    /**
     * Returns a {@link PropertyDescriptor} for the given property if the class supports this
     * property.
     * <p>
     * The method will first search for a read-write property and falls back to a read-only property
     * if there is no write method. Write-only properties are not supported at the moment.
     * 
     * @param c the class to introspect
     * @param property the property for which a descriptor is needed
     * @return an {@link Optional} of {@link PropertyDescriptor} if the class has a method to read
     *         and maybe write the property or an empty {@link Optional} if this is not the case
     */
    public static Optional<PropertyDescriptor> createPropertyDescriptor(Class<?> c, String property) {
        try {
            return Optional.of(new PropertyDescriptor(property, c));
        } catch (IntrospectionException e) {
            try {
                // Fall back to read-only property descriptor
                // just use the 'is' prefix because the property descriptor checks both (get and is)
                // internally
                return Optional
                        .of(new PropertyDescriptor(property, c, IS_PREFIX + StringUtils.capitalize(property), null));
            } catch (IntrospectionException e1) {
                return Optional.empty();
            }
        }
    }

}
