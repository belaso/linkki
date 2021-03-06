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
package org.linkki.core.nls.pmo;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Defines label types and their Resource Bundle Key patterns
 */
public enum PmoLabelType {

    /**
     * pattern: &lt;PmoClassName&gt;_caption
     */
    SECTION_CAPTION {


        @Override
        public String getKey(Class<?> pmoClass, @Nullable String property) {
            return pmoClass.getSimpleName() + '_' + CAPTION_KEY;
        }
    },
    /**
     *
     * pattern: &lt;PmoClassName&gt;_&lt;propertyName&gt;_label
     */
    PROPERTY_LABEL {
        private static final String LABEL_KEY = "label";

        @Override
        public String getKey(Class<?> pmoClass, @SuppressWarnings("null") @Nonnull String property) {
            requireNonNull(property, "parameter property for PROPERTY_LABEL can not be null ");
            return pmoClass.getSimpleName() + '_' + property + '_' + LABEL_KEY;
        }
    },
    /**
     *
     * pattern: &lt;PmoClassName&gt;_&lt;propertyName&gt;_caption
     */
    BUTTON_CAPTION {
        @Override
        public String getKey(Class<?> pmoClass, @SuppressWarnings("null") @Nonnull String property) {
            requireNonNull(property, "parameter property for BUTTON_CAPTION can not be null ");
            return pmoClass.getSimpleName() + '_' + property + '_' + CAPTION_KEY;
        }
    },
    /**
     *
     * pattern: &lt;PmoClassName&gt;_&lt;propertyName&gt;_tooltip
     */
    TOOLTIP {
        private static final String TOOLTIP_KEY = "tooltip";

        @Override
        public String getKey(Class<?> pmoClass, @SuppressWarnings("null") @Nonnull String property) {
            requireNonNull(property, "parameter property for TOOLTIP can not be null ");
            return pmoClass.getSimpleName() + '_' + property + '_' + TOOLTIP_KEY;
        }
    };

    private static final String CAPTION_KEY = "caption";

    /**
     * @return the key to use when looking up the text for the given class' property
     */
    public abstract String getKey(Class<?> pmoClass, @Nullable String property);

}
