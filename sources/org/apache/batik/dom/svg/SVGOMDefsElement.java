/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.dom.svg;

import org.apache.batik.dom.AbstractDocument;
import org.w3c.dom.svg.SVGDefsElement;

/**
 * This class implements {@link org.w3c.dom.svg.SVGDefsElement}.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class SVGOMDefsElement
    extends    SVGGraphicsElement
    implements SVGDefsElement {
    /**
     * Creates a new SVGOMDefsElement object.
     */
    public SVGOMDefsElement() {
    }

    /**
     * Creates a new SVGOMDefsElement object.
     * @param prefix The namespace prefix.
     * @param owner The owner document.
     */
    public SVGOMDefsElement(String prefix, AbstractDocument owner) {
        super(prefix, owner);

    }

    /**
     * <b>DOM</b>: Implements {@link org.w3c.dom.Node#getLocalName()}.
     */
    public String getLocalName() {
        return TAG_DEFS;
    }
}
