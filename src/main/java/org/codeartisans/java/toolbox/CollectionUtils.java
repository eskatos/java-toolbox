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
package org.codeartisans.java.toolbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author Paul Merlin
 */
public final class CollectionUtils
{

    private CollectionUtils()
    {
    }

    public static <T> T firstElementOrNull( final Iterable<T> iterable )
    {
        if ( iterable == null ) {
            return null;
        }
        final Iterator<T> iterator = iterable.iterator();
        if ( iterator.hasNext() ) {
            return iterator.next();
        } else {
            return null;
        }
    }

    /**
     * @param oldCollection A Collection
     * @param newCollection Another Collection
     * @return              True iff both collection are null or contains the same elements, false otherwise.
     */
    public static boolean collectionEquals( Collection<?> oldCollection, Collection<?> newCollection )
    {
        if ( oldCollection == null && newCollection == null ) {
            return true;
        }
        if ( oldCollection != null && newCollection != null && newCollection.size() == oldCollection.size() && oldCollection.containsAll( newCollection ) ) {
            return true;
        }
        return false;
    }

    /**
     * @param <T>   Type of collection elements.
     * @param left  Left collection of query. Treated as an empty collection if null.
     * @param right Right collection of query. Treated as an empty collection if null.
     * @return      A collection containing elements from the left collection not present in the right collection.
     */
    public static <T> Collection<T> removed( Collection<T> left, Collection<T> right )
    {
        if ( left == null || left.isEmpty() ) {
            return Collections.emptyList();
        }
        if ( right == null || right.isEmpty() ) {
            return new ArrayList<T>( left );
        }
        Collection<T> removed = new ArrayList<T>();
        for ( T eachLeft : left ) {
            if ( !right.contains( eachLeft ) ) {
                removed.add( eachLeft );
            }
        }
        return removed;
    }

    /**
     * @param <T>   Type of collection elements.
     * @param left  Left collection of query. Treated as an empty collection if null.
     * @param right Right collection of query. Treated as an empty collection if null.
     * @return      A collection containing elements from the right collection not present in the left collection.
     */
    public static <T> Collection<T> added( Collection<T> left, Collection<T> right )
    {
        if ( right == null || right.isEmpty() ) {
            return Collections.emptyList();
        }
        if ( left == null || left.isEmpty() ) {
            return new ArrayList<T>( right );
        }
        Collection<T> added = new ArrayList<T>();
        for ( T eachRight : right ) {
            if ( !left.contains( eachRight ) ) {
                added.add( eachRight );
            }
        }
        return added;
    }

}
