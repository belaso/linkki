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
package org.linkki.core.binding.dispatcher;

import static java.util.Objects.requireNonNull;

import org.apache.commons.lang3.StringUtils;

/**
 * Naming convention for properties. Adds suffixes, e.g. "enabled", to the base property, e.g.
 * "premium". To obtain the enabled property for premium, e.g. "premiumEnabled". The respective
 * getter would be called "isPremiumEnabled()".
 */
public class PropertyNamingConvention {

    public static final String ENABLED_PROPERTY_SUFFIX = "enabled";
    public static final String VISIBLE_PROPERTY_SUFFIX = "visible";
    public static final String REQUIRED_PROPERTY_SUFFIX = "required";
    public static final String MESSAGES_PROPERTY_SUFFIX = "messages";
    public static final String AVAILABLE_VALUES_PROPERTY_SUFFIX = "availableValues";
    public static final String CAPTION_PROPERTY_SUFFIX = "caption";
    public static final String TOOLTIP_PROPERTY_SUFFIX = "toolTip";
    public static final String COMPONENT_PROPERTY_SUFFIX = "componentType";

    /**
     * @return the property without change
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getValueProperty(String property) {
        return requireNonNull(property, "property must not be null");
    }

    /**
     * @return the capitalized property with the suffix {@link #ENABLED_PROPERTY_SUFFIX}. e.g.
     *         "premium" results in "premiumEnabled".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getEnabledProperty(String property) {
        return checkAndAddSuffix(ENABLED_PROPERTY_SUFFIX, property);
    }

    /**
     * @return the capitalized property with the suffix {@link #VISIBLE_PROPERTY_SUFFIX}. e.g.
     *         "premium" results in "premiumVisible".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getVisibleProperty(String property) {
        return checkAndAddSuffix(VISIBLE_PROPERTY_SUFFIX, property);
    }

    /**
     * @return the capitalized property with the suffix {@link #MESSAGES_PROPERTY_SUFFIX}. e.g.
     *         "premium" results in "premiumMessages".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getMessagesProperty(String property) {
        return checkAndAddSuffix(MESSAGES_PROPERTY_SUFFIX, property);
    }

    /**
     * @return the capitalized property with the suffix {@link #AVAILABLE_VALUES_PROPERTY_SUFFIX}.
     *         e.g. "premium" results in "premiumAvailableValues".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getAvailableValuesProperty(String property) {
        return checkAndAddSuffix(AVAILABLE_VALUES_PROPERTY_SUFFIX, property);
    }

    /**
     * @return the capitalized property with the suffix {@link #REQUIRED_PROPERTY_SUFFIX} . e.g.
     *         "premium" results in "premiumRequired".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getRequiredProperty(String property) {
        return checkAndAddSuffix(REQUIRED_PROPERTY_SUFFIX, property);
    }

    /**
     * @return the capitalized property with the suffix {@link #CAPTION_PROPERTY_SUFFIX} . e.g.
     *         "premium" results in "premiumCaption".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getCaptionProperty(String property) {
        return checkAndAddSuffix(CAPTION_PROPERTY_SUFFIX, property);
    }

    /**
     * @return the capitalized property with the suffix {@link #TOOLTIP_PROPERTY_SUFFIX} . e.g.
     *         "premium" results in "premiumToolTip".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getToolTipProperty(String property) {
        return checkAndAddSuffix(TOOLTIP_PROPERTY_SUFFIX, property);
    }

    /**
     * @return the capitalized property with the suffix {@link #COMPONENT_PROPERTY_SUFFIX} . e.g.
     *         "premium" results in "premiumComponentType".
     * @throws NullPointerException if the given property is <code>null</code>
     */
    public String getComponentTypeProperty(String property) {
        return checkAndAddSuffix(COMPONENT_PROPERTY_SUFFIX, property);
    }

    private String checkAndAddSuffix(String suffix, String property) {
        if (StringUtils.isEmpty(requireNonNull(property, "property must not be null"))) {
            // Empty suffix is used in ButtonPmo for isVisible/isEnabled properties
            return suffix;
        }
        return StringUtils.uncapitalize(property + StringUtils.capitalize(suffix));
    }
}
