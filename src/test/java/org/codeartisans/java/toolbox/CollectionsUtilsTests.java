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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Paul Merlin <paul@nosphere.org>
 */
public class CollectionsUtilsTests
{

    private static final Collection<String> LEFT = Arrays.asList( new String[]{ "foo", "bar", "pedobear" } );

    private static final Collection<String> RIGHT = Arrays.asList( new String[]{ "foo", "pedobear", "plop" } );

    private static final Collection<String> REMOVED_EXPECTED = Arrays.asList( new String[]{ "bar" } );

    private static final Collection<String> ADDED_EXPECTED = Arrays.asList( new String[]{ "plop" } );

    @Test
    public void testEquals()
    {
        assertTrue( CollectionUtils.collectionEquals( LEFT, LEFT ) );
        assertTrue( CollectionUtils.collectionEquals( RIGHT, RIGHT ) );

        assertFalse( CollectionUtils.collectionEquals( RIGHT, LEFT ) );
        assertFalse( CollectionUtils.collectionEquals( LEFT, RIGHT ) );

    }

    @Test
    public void testRemoved()
    {
        assertTrue( CollectionUtils.collectionEquals( Collections.emptyList(), CollectionUtils.removed( null, RIGHT ) ) );
        assertTrue( CollectionUtils.collectionEquals( Collections.emptyList(), CollectionUtils.removed( Collections.<String>emptyList(), RIGHT ) ) );

        assertTrue( CollectionUtils.collectionEquals( LEFT, CollectionUtils.removed( LEFT, null ) ) );
        assertTrue( CollectionUtils.collectionEquals( LEFT, CollectionUtils.removed( LEFT, Collections.<String>emptyList() ) ) );

        assertTrue( CollectionUtils.collectionEquals( REMOVED_EXPECTED, CollectionUtils.removed( LEFT, RIGHT ) ) );
    }

    @Test
    public void testAdded()
    {
        assertTrue( CollectionUtils.collectionEquals( Collections.emptyList(), CollectionUtils.added( LEFT, null ) ) );
        assertTrue( CollectionUtils.collectionEquals( Collections.emptyList(), CollectionUtils.added( LEFT, Collections.<String>emptyList() ) ) );

        assertTrue( CollectionUtils.collectionEquals( RIGHT, CollectionUtils.added( null, RIGHT ) ) );
        assertTrue( CollectionUtils.collectionEquals( RIGHT, CollectionUtils.added( Collections.<String>emptyList(), RIGHT ) ) );

        assertTrue( CollectionUtils.collectionEquals( ADDED_EXPECTED, CollectionUtils.added( LEFT, RIGHT ) ) );
    }

}
