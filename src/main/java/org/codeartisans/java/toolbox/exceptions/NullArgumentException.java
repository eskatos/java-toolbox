/*
 * Copyright (c) 2009 Paul Merlin <paul@nosphere.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.codeartisans.java.toolbox.exceptions;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * @author Paul Merlin <paul@nosphere.org>
 */
public final class NullArgumentException
        extends IllegalArgumentException
{

    private static final long serialVersionUID = 1L;

    private static final String WAS_NULL = " was null.";

    private static final String WAS_EMPTY = " was empty.";

    private static final String WAS_ZERO = " was zero.";

    private NullArgumentException( String message )
    {
        super( message );
    }

    public static void ensureNotNull( String name, Object value )
    {
        if ( value != null ) {
            return;
        }
        throw new NullArgumentException( name + WAS_NULL );
    }

    public static void ensureNotEmpty( String name, String value )
    {
        ensureNotNull( name, value );
        if ( value.length() == 0 ) {
            throw new NullArgumentException( name + WAS_EMPTY );
        }
    }

    public static void ensureNotEmpty( String name, CharSequence value )
    {
        ensureNotNull( name, value );
        if ( value.length() == 0 ) {
            throw new NullArgumentException( name + WAS_EMPTY );
        }
    }

    public static void ensureNotEmpty( String name, boolean trim, String value )
    {
        ensureNotNull( name, value );
        if ( value.length() == 0 || ( trim && value.trim().length() == 0 ) ) {

            throw new NullArgumentException( name + WAS_EMPTY );
        }
    }

    public static void ensureNotEmpty( String name, Object[] value )
    {
        ensureNotNull( name, value );
        if ( value.length == 0 ) {
            throw new NullArgumentException( name + WAS_EMPTY );
        }
    }

    public static void ensureNotEmpty( String name, Collection<?> value )
    {
        ensureNotNull( name, value );
        if ( value.isEmpty() ) {
            throw new NullArgumentException( name + WAS_EMPTY );
        }
    }

    public static void ensureNotEmpty( String name, Properties value )
    {
        ensureNotNull( name, value );
        if ( value.isEmpty() ) {
            throw new NullArgumentException( name + WAS_EMPTY );
        }
    }

    public static void ensureNotEmpty( String name, Map<?, ?> value )
            throws NullArgumentException
    {
        ensureNotNull( name, value );
        if ( value.isEmpty() ) {
            throw new NullArgumentException( name + WAS_EMPTY );
        }
    }

    /**
     * Ensures that the string array instance is not null and that it has entries that are not null or empty
     * either without trimming the string.
     */
    public static void ensureNotEmptyContent( String name, String[] value )
    {
        ensureNotEmptyContent( name, false, value );
    }

    /**
     * Ensures that the string array instance is not null and that it has entries that are not null or empty.
     */
    public static void ensureNotEmptyContent( String name, boolean trim, String[] value )
    {
        ensureNotEmpty( name, value );
        for ( int i = 0; i < value.length; i++ ) {
            ensureNotEmpty( value[i] + "[" + i + "]", value[i] );
            if ( trim ) {
                ensureNotEmpty( value[i] + "[" + i + "]", value[i].trim() );
            }
        }
    }

    public static void ensureNotZero( String name, Integer value )
    {
        ensureNotNull( name, value );
        if ( value == 0 ) {
            throw new NullArgumentException( name + WAS_ZERO );
        }
    }

}
