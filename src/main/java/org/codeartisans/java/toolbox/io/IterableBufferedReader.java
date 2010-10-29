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
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class IterableBufferedReader
        implements Iterable<String>
{

    private final BufferedReader reader;
    private final Integer maxLines;

    public IterableBufferedReader( BufferedReader reader )
    {
        this( reader, -1 );
    }

    public IterableBufferedReader( BufferedReader reader, Integer maxLines )
    {
        this.reader = reader;
        this.maxLines = maxLines;
    }

    @Override
    public Iterator<String> iterator()
    {
        return new BufferedReaderIterator();
    }

    private class BufferedReaderIterator
            implements Iterator<String>
    {

        private String nextline;
        private Integer linesRead = 0;

        public BufferedReaderIterator()
        {
            try {
                if ( maxLines != 0 ) {
                    nextline = reader.readLine();
                }
            } catch ( IOException ex ) {
                throw new IllegalArgumentException( ex );
            }
        }

        @Override
        public boolean hasNext()
        {
            if ( maxLines > 0 && maxLines <= linesRead ) {
                return false;
            }
            return nextline != null;
        }

        @Override
        public String next()
        {
            try {

                if ( maxLines > 0 && maxLines <= linesRead ) {
                    throw new NoSuchElementException();
                }
                String result = nextline;
                if ( nextline != null ) {
                    nextline = reader.readLine();
                    if ( nextline == null ) {
                        reader.close();
                    }
                }
                linesRead++;
                return result;
            } catch ( IOException ex ) {
                throw new IllegalArgumentException( ex );
            }
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException( this.getClass().getSimpleName() + " is read only" );
        }

    }

}
