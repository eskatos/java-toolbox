package org.codeartisans.java.toolbox.exceptions;

import java.util.Properties;

public final class NullArgumentException
        extends IllegalArgumentException
{

    private static final long serialVersionUID = 1L;
    private static final String WAS_NULL = " was null.";
    private static final String WAS_EMPTY = " was empty.";
    private static final String WAS_ZERO = " was zero.";

    private NullArgumentException(String message)
    {
        super(message);
    }

    public static void ensureNotNull(String name, Object value)
    {
        if (value != null) {
            return;
        }
        throw new NullArgumentException(name + WAS_NULL);
    }

    public static void ensureNotEmpty(String name, String value)
    {
        ensureNotNull(name, value);
        if (value.length() == 0) {
            throw new NullArgumentException(name + WAS_EMPTY);
        }
    }

    public static void ensureNotEmpty(String name, boolean trim, String value)
    {
        ensureNotNull(name, value);
        if (value.length() == 0 || (trim && value.trim().length() == 0)) {

            throw new NullArgumentException(name + WAS_EMPTY);
        }
    }

    public static void ensureNotEmpty(String name, Object[] value)
            throws NullArgumentException
    {
        ensureNotNull(name, value);
        if (value.length == 0) {
            throw new NullArgumentException(name + WAS_EMPTY);
        }
    }

    public static void ensureNotEmpty(String name, Properties value)
            throws NullArgumentException
    {
        ensureNotNull(name, value);
        if (value.isEmpty()) {
            throw new NullArgumentException(name + WAS_EMPTY);
        }
    }

    /**
     * Ensures that the string array instance is not null and that it has entries that are not null or empty
     * either without trimming the string.
     */
    public static void ensureNotEmptyContent(String name, String[] value)
            throws NullArgumentException
    {
        ensureNotEmptyContent(name, false, value);
    }

    /**
     * Ensures that the string array instance is not null and that it has entries that are not null or empty.
     */
    public static void ensureNotEmptyContent(String name, boolean trim, String[] value)
            throws NullArgumentException
    {
        ensureNotEmpty(name, value);
        for (int i = 0; i < value.length; i++) {
            ensureNotEmpty(value[i] + "[" + i + "]", value[i]);
            if (trim) {
                ensureNotEmpty(value[i] + "[" + i + "]", value[i].trim());
            }
        }
    }

    public static void ensureNotZero(String name, Integer value)
            throws NullArgumentException
    {
        ensureNotNull(name, value);
        if (value == 0) {
            throw new NullArgumentException(name + WAS_ZERO);
        }
    }

}
