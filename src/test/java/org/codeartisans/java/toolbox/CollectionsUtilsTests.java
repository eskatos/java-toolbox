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

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CollectionsUtilsTests
{

    private static final Collection<String> LEFT = Arrays.asList( new String[]{ "foo", "bar", "pedobear" } );
    private static final Collection<String> RIGHT = Arrays.asList( new String[]{ "foo", "pedobear", "plop" } );
    private static final Collection<String> REMOVED_EXPECTED = Arrays.asList( new String[]{ "bar" } );
    private static final Collection<String> ADDED_EXPECTED = Arrays.asList( new String[]{ "plop" } );

    @Test
    public void testEquals()
    {
        assertTrue( Collections.collectionEquals( LEFT, LEFT ) );
        assertTrue( Collections.collectionEquals( RIGHT, RIGHT ) );

        assertFalse( Collections.collectionEquals( RIGHT, LEFT ) );
        assertFalse( Collections.collectionEquals( LEFT, RIGHT ) );

    }

    @Test
    public void testRemoved()
    {
        assertTrue( Collections.collectionEquals( java.util.Collections.emptyList(), Collections.removed( null, RIGHT ) ) );
        assertTrue( Collections.collectionEquals( java.util.Collections.emptyList(), Collections.removed( java.util.Collections.<String>emptyList(), RIGHT ) ) );

        assertTrue( Collections.collectionEquals( LEFT, Collections.removed( LEFT, null ) ) );
        assertTrue( Collections.collectionEquals( LEFT, Collections.removed( LEFT, java.util.Collections.<String>emptyList() ) ) );

        assertTrue( Collections.collectionEquals( REMOVED_EXPECTED, Collections.removed( LEFT, RIGHT ) ) );
    }

    @Test
    public void testAdded()
    {
        assertTrue( Collections.collectionEquals( java.util.Collections.emptyList(), Collections.added( LEFT, null ) ) );
        assertTrue( Collections.collectionEquals( java.util.Collections.emptyList(), Collections.added( LEFT, java.util.Collections.<String>emptyList() ) ) );

        assertTrue( Collections.collectionEquals( RIGHT, Collections.added( null, RIGHT ) ) );
        assertTrue( Collections.collectionEquals( RIGHT, Collections.added( java.util.Collections.<String>emptyList(), RIGHT ) ) );

        assertTrue( Collections.collectionEquals( ADDED_EXPECTED, Collections.added( LEFT, RIGHT ) ) );
    }

}
