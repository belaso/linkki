package de.faktorzehn.ipm.web.ui.application.menu;

import com.vaadin.ui.MenuBar.MenuItem;

import de.faktorzehn.ipm.web.ui.application.ApplicationLayout;
import de.faktorzehn.ipm.web.ui.application.ApplicationStyles;

/**
 * Defines an item in the applications main menu.
 */
public abstract class ApplicationMenuItemDefinition implements Comparable<ApplicationMenuItemDefinition> {

    private String name;
    private int position;

    public ApplicationMenuItemDefinition(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public MenuItem createItem(ApplicationMenu menu, ApplicationLayout layout) {
        MenuItem item = internalCreateItem(menu, layout);
        item.setStyleName(ApplicationStyles.APPLICATION_MENU);
        return item;
    }

    protected abstract MenuItem internalCreateItem(ApplicationMenu menu, ApplicationLayout layout);

    @Override
    public int compareTo(ApplicationMenuItemDefinition other) {
        return this.position - other.position;
    }

}
