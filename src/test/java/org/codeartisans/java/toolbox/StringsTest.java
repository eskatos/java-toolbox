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

import static org.junit.Assert.*;
import org.junit.Test;

public class StringsTest
{

    private static final String SMALL = "foo";
    private static final String BIG = "bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar ";

    @Test
    public void testMax()
    {
        assertEquals( Strings.EMPTY, Strings.max( null, 1 ) );
        assertEquals( Strings.EMPTY, Strings.max( "", -1 ) );

        assertTrue( SMALL.length() == Strings.max( SMALL, 10 ).length() );
        assertTrue( 10 == Strings.max( BIG, 10 ).length() );
    }

    @Test
    public void testMaxWordedEtc()
    {
        assertEquals( Strings.EMPTY, Strings.maxWordedEtc( null, 1 ) );
        assertEquals( Strings.EMPTY, Strings.maxWordedEtc( "", -1 ) );

        assertEquals( SMALL, Strings.maxWordedEtc( SMALL, 10 ) );
        assertEquals( "bar bar bar" + Strings.ETC, Strings.maxWordedEtc( BIG, 12 ) );
    }

    @Test
    public void testTrim()
    {
        String singleLine = "   \tfoo   \t ";

        assertEquals( SMALL, Strings.trim( singleLine ) );
        assertEquals( "foo   \t ", Strings.trimLeft( singleLine ) );
        assertEquals( "   \tfoo", Strings.trimRight( singleLine ) );

        assertEquals( Strings.EMPTY, Strings.verticalTrimLines( Strings.EMPTY ) );
        assertEquals( SMALL, Strings.verticalTrimLines( singleLine ) );

        String multiLine = "  two\n   three\n        eight\n\n one\n";
        String expected = " two\n  three\n       eight\n\none\n";
        assertEquals( expected, Strings.verticalTrimLines( multiLine ) );
    }

}
