/*
 * Copyright 2013 Paul Merlin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codeartisans.java.toolbox.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.codeartisans.java.toolbox.exceptions.NullArgumentException;

public class TeeOutputStream
        extends OutputStream
{

    private final List<OutputStream> outputs;

    public TeeOutputStream( OutputStream... outputs )
    {
        this( Arrays.asList( outputs ) );
    }

    public TeeOutputStream( Collection<OutputStream> outputs )
    {
        this.outputs = new ArrayList<OutputStream>();
        for ( OutputStream output : outputs ) {
            NullArgumentException.ensureNotNull( "OutputStream", output );
            outputs.add( output );
        }
    }

    @Override
    public void write( int b )
            throws IOException
    {
        for ( OutputStream output : outputs ) {
            output.write( b );
        }
    }

    @Override
    public void write( byte[] b )
            throws IOException
    {
        for ( OutputStream output : outputs ) {
            output.write( b );
        }
    }

    @Override
    public void write( byte[] b, int off, int len )
            throws IOException
    {
        for ( OutputStream output : outputs ) {
            output.write( b, off, len );
        }
    }

    @Override
    public void flush()
            throws IOException
    {
        for ( OutputStream output : outputs ) {
            output.flush();
        }
    }

    @Override
    public void close()
            throws IOException
    {
        for ( OutputStream output : outputs ) {
            output.close();
        }
    }

}
