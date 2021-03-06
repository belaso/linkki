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
package org.linkki.framework.ui.application;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.cdi.internal.Conventions;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * Subclass of the origin vaadin navigator to fix a cdi problem when switching to the same view.
 * 
 * Switching to the same view does not clear the view scope
 * (https://github.com/vaadin/cdi/issues/166). To get a clean view scope we first navigate to the
 * {@link EmptyCdiView} before navigating to the correct new view.
 *
 */
public class CdiFixNavigator extends Navigator {

    private static final long serialVersionUID = 1L;

    /**
     * Navigation state is updated before the {@link #navigateTo(String)} method is called. So we
     * need to save the name of the current view on our own.
     */
    private String currentView = StringUtils.EMPTY;

    @SuppressWarnings("null")
    private String emptyView = Conventions.deriveMappingForView(EmptyCdiView.class);

    public CdiFixNavigator(UI ui, ComponentContainer container) {
        super(ui, container);
    }

    @Override
    public void navigateTo(@Nullable String navigationState) {
        String newViewName = getViewName(navigationState);
        if (newViewName.equals(currentView)) {
            super.navigateTo(emptyView);
        }
        super.navigateTo(navigationState);
        if (isNavigationSuccessful(newViewName)) {
            currentView = newViewName;
        }
    }

    private boolean isNavigationSuccessful(String newViewName) {
        return getViewName(getState()).equals(newViewName);
    }

    String getViewName(@Nullable String fragment) {
        return fragment == null ? "" : StringUtils.substringBefore(fragment, "/");
    }
}
