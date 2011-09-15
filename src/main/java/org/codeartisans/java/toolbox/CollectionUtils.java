/*
 * Copyright (c) 2010, Paul Merlin. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.codeartisans.java.toolbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public final class CollectionUtils
{

    private CollectionUtils()
    {
    }

    public static boolean isEmpty( Iterable<?> iterable )
    {
        if ( iterable == null ) {
            return true;
        }
        return iterable.iterator().hasNext();
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
