/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.util.awt.svg;

import java.util.Map;
import java.util.Hashtable;
import java.util.Set;
import java.util.HashSet;

import org.w3c.dom.Document;

/**
 * Abstract class with common utility methods used by subclasses
 * for specific convertion operations. It holds a reference to a
 * domFactory Document, which many implementations use, and provides
 * a convenience method, to offers a convertion of double values
 * to String that remove the trailing '.' character on integral
 * values.
 *
 * @author <a href="mailto:vincent.hardy@eng.sun.com">Vincent Hardy</a>
 * @version $Id$
 */

public abstract class AbstractSVGConverter implements SVGConverter{
    public static final String ERROR_DOM_FACTORY_NULL = "domFactory should not be null";

    public static final int DEFAULT_PRECISION = 10000;

    /**
     * Used by converters to create Elements and other DOM objects
     */
    protected Document domFactory;

    /**
     * Map of descriptions already processed by this converter. The
     * key type is left to the implementations
     */
    protected Map descMap = new Hashtable();

    /**
     * Set of definitions to interpret the values of the attributes
     * generated by this converter since its creation
     */
    protected Set defSet = new HashSet();

    /**
     * @param domFactory Can be used by the SVGConverter extentions
     *        to create Elements and other types of DOM objects.
     */
    public AbstractSVGConverter(Document domFactory){
        if(domFactory==null)
            throw new IllegalArgumentException(ERROR_DOM_FACTORY_NULL);

        this.domFactory = domFactory;
    }

    /**
     * Default constructor
     */
    public AbstractSVGConverter(){
    }

    /**
     * @return set of definitions referenced by the attribute
     *         values created by the implementation since its
     *         creation. The return value should never be null.
     *         If no definition is needed, an empty set should be
     *         returned.
     */
    public Set getDefinitionSet(){
        return defSet;
    }

    /**
     * @return the double value formated as an int if there
     *         is no fractional part. This avoids the extra
     *         ".0" that a standard convertion gives.
     */
    public static String doubleString(double value){
        return doubleString(value, DEFAULT_PRECISION);
    }

    public static String doubleString(double value, int precision){
        value = Math.round(value*precision)/(double)precision;

        if(((int)value) == value)
            return Integer.toString((int)value);
        else
            return Double.toString(value);
    }
}
