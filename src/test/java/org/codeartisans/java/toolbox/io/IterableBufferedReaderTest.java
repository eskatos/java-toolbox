/*
 * Copyright (c) 2010 Paul Merlin <paul@nosphere.org>
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
package org.codeartisans.java.toolbox.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * WARN this test let file descriptors open
 * 
 * @author Paul Merlin <paul@nosphere.org>
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
