package org.codeartisans.java.toolbox;

import java.util.Iterator;

public final class CollectionUtils
{

    public static <T> T firstElementOrNull(final Iterable<T> iterable)
    {
        final Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

}
