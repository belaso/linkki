/*******************************************************************************
 * Copyright (c) 2014 Faktor Zehn AG.
 * 
 * Alle Rechte vorbehalten.
 *******************************************************************************/

package org.linkki.core.ui.components;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.linkki.core.ui.components.ItemCaptionProvider.IdAndNameCaptionProvider;

import com.vaadin.ui.TwinColSelect;

/**
 * Extends {@link TwinColSelect} and overrides various methods in order to implement a different
 * behavior for obtaining item captions, namely to use an {@link #getItemCaptionProvider() item
 * caption provider} function when an item's caption is needed. As an additional bonus
 * "subset chooser" is a much nicer name for a multi-select component with a left and right list.
 */
public class SubsetChooser extends TwinColSelect {

    private static final long serialVersionUID = -3423711510318680768L;

    private ItemCaptionProvider<?> itemCaptionProvider;

    /** Default constructor that uses a {@link IdAndNameCaptionProvider}. */
    public SubsetChooser() {
        this(new IdAndNameCaptionProvider());
    }

    /**
     * Creates a new SubsetChoose using the given function to determine captions for the displayed
     * items.
     * 
     * @param itemCaptionProvider the function to determine captions for the displayed items
     */
    public SubsetChooser(@Nonnull ItemCaptionProvider<?> itemCaptionProvider) {
        super();
        this.itemCaptionProvider = requireNonNull(itemCaptionProvider);
    }

    /**
     * Throws an {@link UnsupportedOperationException} as this class does not allow explicit item
     * captions. Instead, the {@link #getItemCaptionProvider() item caption provider} is used to get
     * captions for items.
     */
    @Override
    public void setItemCaption(Object itemId, String caption) {
        throw new UnsupportedOperationException("SubsetChooser does not support explicit item captions");
    }

    /**
     * Throws an {@link UnsupportedOperationException} as this class does not allow different item
     * caption modes. Instead, the {@link #getItemCaptionProvider() item caption provider} is always
     * used to get captions for items.
     */
    @Override
    public void setItemCaptionMode(ItemCaptionMode mode) {
        throw new UnsupportedOperationException("SubsetChooser does not allow to specify the item caption mode");
    }

    /**
     * Returns {@link com.vaadin.ui.AbstractSelect.ItemCaptionMode#ITEM} as the closest
     * approximation of the behavior this class implements, namely the usage of the
     * {@link #getItemCaptionProvider() item caption provider} to get captions for items.
     */
    @Override
    public ItemCaptionMode getItemCaptionMode() {
        return ItemCaptionMode.ITEM;
    }

    /** Sets the function to use as the item caption provider. */
    public void setItemCaptionProvider(ItemCaptionProvider<?> newItemCaptionProvider) {
        itemCaptionProvider = requireNonNull(newItemCaptionProvider);
    }

    /** Returns the function that is used to obtain an item's caption. */
    public ItemCaptionProvider<?> getItemCaptionProvider() {
        return itemCaptionProvider;
    }

    /**
     * Returns the caption of the given item using this object's {@link #getItemCaptionProvider()
     * item caption provider}.
     * 
     * @param itemId an item
     * @return the item's caption
     */
    @Override
    public String getItemCaption(Object itemId) {
        if (itemId == null) {
            return StringUtils.EMPTY;
        }
        return itemCaptionProvider.getUnsafeCaption(itemId);
    }

}
