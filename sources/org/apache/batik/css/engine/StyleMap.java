/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.css.engine;

import org.apache.batik.css.engine.value.Value;

/**
 * This class represents objects which contains property/value mappings.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class StyleMap {
    
    //
    // The masks
    //
    public final static short IMPORTANT_MASK = 0x0001;
    public final static short COMPUTED_MASK = 0x0002;
    public final static short NULL_CASCADED_MASK = 0x0004;

    public final static short LINE_HEIGHT_RELATIVE_MASK = 0x0008;
    public final static short FONT_SIZE_RELATIVE_MASK = 0x0010;
    public final static short COLOR_RELATIVE_MASK = 0x0020;
    public final static short PARENT_RELATIVE_MASK = 0x0040;
    public final static short BLOCK_WIDTH_RELATIVE_MASK = 0x0080;
    public final static short BLOCK_HEIGHT_RELATIVE_MASK = 0x0100;
    public final static short BOX_RELATIVE_MASK = 0x0200;

    public final static short ORIGIN_MASK = (short)0xE000; // 3 last bits


    //
    // The origin values.
    //
    public final static short USER_AGENT_ORIGIN = 0;
    public final static short USER_ORIGIN = 0x2000; // 0010
    public final static short NON_CSS_ORIGIN = 0x4000; // 0100
    public final static short AUTHOR_ORIGIN = 0x6000; // 0110
    public final static short INLINE_AUTHOR_ORIGIN = (short)0x8000; // 1000

    /**
     * The values.
     */
    protected Value[] values;

    /**
     * To store the value masks.
     */
    protected short[] masks;

    /**
     * Whether the values of this map cannot be re-cascaded.
     */
    protected boolean fixedCascadedValues;

    /**
     * Creates a new StyleMap.
     */
    public StyleMap(int size) {
        values = new Value[size];
        masks = new short[size];
    }

    /**
     * Whether this map has fixed cascaded value.
     */
    public boolean hasFixedCascadedValues() {
        return fixedCascadedValues;
    }

    /**
     * Sets the fixedCascadedValues property.
     */
    public void setFixedCascadedStyle(boolean b) {
        fixedCascadedValues = b;
    }

    /**
     * Returns the value at the given index, null if unspecified.
     */
    public Value getValue(int i) {
        return values[i];
    }

    /**
     * Returns the mask of the given property value.
     */
    public short getMask(int i) {
        return masks[i];
    }

    /**
     * Tells whether the given property value is important.
     */
    public boolean isImportant(int i) {
        return (masks[i] & IMPORTANT_MASK) != 0;
    }

    /**
     * Tells whether the given property value is computed.
     */
    public boolean isComputed(int i) {
        return (masks[i] & COMPUTED_MASK) != 0;
    }

    /**
     * Tells whether the given cascaded property value is null.
     */
    public boolean isNullCascaded(int i) {
        return (masks[i] & NULL_CASCADED_MASK) != 0;
    }

    /**
     * Returns the origin value.
     */
    public short getOrigin(int i) {
        return (short)(masks[i] & ORIGIN_MASK);
    }

    /**
     * Tells whether the given property value is relative to 'color'.
     */
    public boolean isColorRelative(int i) {
        return (masks[i] & COLOR_RELATIVE_MASK) != 0;
    }

    /**
     * Tells whether the given property value is relative to the parent's
     * property value.
     */
    public boolean isParentRelative(int i) {
        return (masks[i] & PARENT_RELATIVE_MASK) != 0;
    }

    /**
     * Tells whether the given property value is relative to 'line-height'.
     */
    public boolean isLineHeightRelative(int i) {
        return (masks[i] & LINE_HEIGHT_RELATIVE_MASK) != 0;
    }

    /**
     * Tells whether the given property value is relative to 'font-size'.
     */
    public boolean isFontSizeRelative(int i) {
        return (masks[i] & FONT_SIZE_RELATIVE_MASK) != 0;
    }

    /**
     * Tells whether the given property value is relative to the
     * width of the containing block.
     */
    public boolean isBlockWidthRelative(int i) {
        return (masks[i] & BLOCK_WIDTH_RELATIVE_MASK) != 0;
    }

    /**
     * Tells whether the given property value is relative to the
     * height of the containing block.
     */
    public boolean isBlockHeightRelative(int i) {
        return (masks[i] & BLOCK_HEIGHT_RELATIVE_MASK) != 0;
    }

    /**
     * Puts a property value, given the property index.
     * @param i The property index.
     * @param v The property value.
     */
    public void putValue(int i, Value v) {
        values[i] = v;
    }

    /**
     * Puts a property mask, given the property index.
     * @param i The property index.
     * @param m The property mask.
     */
    public void putMask(int i, short m) {
        masks[i] = m;
    }

    /**
     * Sets the priority of a property value.
     */
    public void putImportant(int i, boolean b) {
        masks[i] &= ~IMPORTANT_MASK;
        masks[i] |= (b) ? IMPORTANT_MASK : 0;
    }

    /**
     * Sets the origin of the given value.
     */
    public void putOrigin(int i, short val) {
        masks[i] &= ~ORIGIN_MASK;
        masks[i] |= (short)(val & ORIGIN_MASK);
    }

    /**
     * Sets the computed flag of a property value.
     */
    public void putComputed(int i, boolean b) {
        masks[i] &= ~COMPUTED_MASK;
        masks[i] |= (b) ? COMPUTED_MASK : 0;
    }

    /**
     * Sets the null-cascaded flag of a property value.
     */
    public void putNullCascaded(int i, boolean b) {
        masks[i] &= ~NULL_CASCADED_MASK;
        masks[i] |= (b) ? NULL_CASCADED_MASK : 0;
    }

    /**
     * Sets the color-relative flag of a property value.
     */
    public void putColorRelative(int i, boolean b) {
        masks[i] &= ~COLOR_RELATIVE_MASK;
        masks[i] |= (b) ? COLOR_RELATIVE_MASK : 0;
    }

    /**
     * Sets the parent-relative flag of a property value.
     */
    public void putParentRelative(int i, boolean b) {
        masks[i] &= ~PARENT_RELATIVE_MASK;
        masks[i] |= (b) ? PARENT_RELATIVE_MASK : 0;
    }

    /**
     * Sets the line-height-relative flag of a property value.
     */
    public void putLineHeightRelative(int i, boolean b) {
        masks[i] &= ~LINE_HEIGHT_RELATIVE_MASK;
        masks[i] |= (b) ? LINE_HEIGHT_RELATIVE_MASK : 0;
    }

    /**
     * Sets the font-size-relative flag of a property value.
     */
    public void putFontSizeRelative(int i, boolean b) {
        masks[i] &= ~FONT_SIZE_RELATIVE_MASK;
        masks[i] |= (b) ? FONT_SIZE_RELATIVE_MASK : 0;
    }

    /**
     * Sets the block-width-relative flag of a property value.
     */
    public void putBlockWidthRelative(int i, boolean b) {
        masks[i] &= ~BLOCK_WIDTH_RELATIVE_MASK;
        masks[i] |= (b) ? BLOCK_WIDTH_RELATIVE_MASK : 0;
    }

    /**
     * Sets the block-height-relative flag of a property value.
     */
    public void putBlockHeightRelative(int i, boolean b) {
        masks[i] &= ~BLOCK_HEIGHT_RELATIVE_MASK;
        masks[i] |= (b) ? BLOCK_HEIGHT_RELATIVE_MASK : 0;
    }

    /**
     * Returns a printable representation of this style map.
     */
    public String toString(CSSEngine eng) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            Value v = values[i];
            if (v != null) {
                sb.append(eng.getPropertyName(i));
                sb.append(": ");
                sb.append(v);
                if (isImportant(i)) {
                    sb.append(" !important");
                }
                sb.append(";\n");
            }
        }
        return sb.toString();
    }
}