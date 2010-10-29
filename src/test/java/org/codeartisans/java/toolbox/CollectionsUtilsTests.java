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
import java.util.Collections;
import static org.junit.Assert.*;
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
