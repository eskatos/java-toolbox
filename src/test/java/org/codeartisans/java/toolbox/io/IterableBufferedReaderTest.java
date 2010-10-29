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
package org.codeartisans.java.toolbox.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * WARN this test let file descriptors open
 */
public class IterableBufferedReaderTest
{

    private File testFile;

    @Before
    public void before()
            throws IOException
    {
        testFile = File.createTempFile( "java-toolbox", this.getClass().getSimpleName() );
        Writer out = new BufferedWriter( new FileWriter( testFile ) );
        out.write( "one\n" );
        out.write( "two\n" );
        out.write( "three\n" );
        out.write( "four\n" );
        out.write( "five\n" );
        out.write( "six\n" );
        out.write( "seven\n" );
        out.write( "eight\n" );
        out.write( "nine\n" );
        out.write( "ten\n" );
        out.close();
    }

    @Test
    public void test()
            throws IOException
    {
        IterableBufferedReader underTest = new IterableBufferedReader( newBufferedReader(), 40 );
        int count = 0;
        System.out.println( "################ Test 40" );
        for ( String eachLine : underTest ) {
            System.out.println( eachLine );
            count++;
        }
        Assert.assertEquals( 10, count );

        underTest = new IterableBufferedReader( newBufferedReader() );
        count = 0;
        System.out.println( "################ Test without limit" );
        for ( String eachLine : underTest ) {
            System.out.println( eachLine );
            count++;
        }
        Assert.assertEquals( 10, count );

        underTest = new IterableBufferedReader( newBufferedReader(), 5 );
        count = 0;
        System.out.println( "################ Test 5" );
        for ( String eachLine : underTest ) {
            System.out.println( eachLine );
            count++;
        }
        Assert.assertEquals( 5, count );

    }

    private BufferedReader newBufferedReader()
            throws IOException
    {
        return new BufferedReader( new InputStreamReader( new DataInputStream( new FileInputStream( testFile ) ) ) );
    }

}
