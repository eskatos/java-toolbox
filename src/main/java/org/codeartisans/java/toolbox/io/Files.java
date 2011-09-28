/*
 * Licenced under the Netheos Licence, Version 1.0 (the "Licence"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at :
 *
 * http://www.netheos.net/licences/LICENCE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright (c) Netheos
 */
package org.codeartisans.java.toolbox.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

// TODO Handle symlinks, if it is costly, make it optionnal
public final class Files
{

    public static void touch( File... paths )
            throws IOException
    {
        IOException exception = null;
        for ( File eachPath : paths ) {
            if ( eachPath.isFile() ) {
                try {
                    IO.closeSilently( new FileOutputStream( eachPath ) );
                } catch ( IOException ex ) {
                    exception = ex;
                }
            } else {
                eachPath.setLastModified( System.currentTimeMillis() );
            }
        }
        if ( exception != null ) {
            throw exception;
        }
    }

    public static void deleteSilently( File... paths )
    {
        try {
            delete( paths );
        } catch ( IOException ignored ) {
            // Ignored
        }
    }

    public static void delete( File... paths )
            throws IOException
    {
        IOException exception = null;
        for ( File eachPath : paths ) {
            if ( eachPath.exists() ) {
                if ( eachPath.isDirectory() ) {
                    delete( eachPath.listFiles() );
                }
                if ( !eachPath.delete() ) {
                    exception = new IOException( "Unable to delete " + eachPath );
                }
            }
        }
        if ( exception != null ) {
            throw exception;
        }
    }

    public static void mkdir( File... paths )
            throws IOException
    {
        IOException exception = null;
        for ( File eachPath : paths ) {
            if ( !eachPath.mkdirs() ) {
                exception = new IOException( "Unable to mkdir " + eachPath );
            }
        }
        if ( exception != null ) {
            throw exception;
        }
    }

    public static void empty( File... paths )
            throws IOException
    {
        IOException exception = null;
        for ( File eachPath : paths ) {
            if ( !eachPath.exists() ) {
                throw new FileNotFoundException( "File or directory do not exists " + eachPath );
            }
            if ( eachPath.isFile() ) {
                delete( eachPath );
                touch( eachPath );
            } else {
                delete( eachPath.listFiles() );
            }
        }
        if ( exception != null ) {
            throw exception;
        }
    }

    public static void move( File path, File to )
            throws IOException
    {
        move( new File[]{ path }, to );
        if ( !path.exists() ) {
            throw new FileNotFoundException( "File not found " + path );
        }
        if ( !path.renameTo( to ) ) {
            throw new IOException( "Unable to move " + path + " to " + to );
        }
    }

    public static void move( File[] paths, File to )
            throws IOException
    {
        move( Arrays.asList( paths ), to );
    }

    public static void move( Collection<File> paths, File to )
            throws IOException
    {
        IOException exception = null;
        if ( paths.size() > 1 ) {
            if ( !to.exists() ) {
                throw new IllegalArgumentException( "Destination directory for several files or directories do not exists" );
            }
            if ( !to.isDirectory() ) {
                throw new IllegalArgumentException( "Cannot move several files or directories to an existing file" );
            }
        }
        for ( File eachPath : paths ) {
            File target = new File( to, eachPath.getName() );
            if ( !eachPath.renameTo( target ) ) {
                exception = new IOException( "Unable to move " + eachPath + " to " + target );
            }
        }
        if ( exception != null ) {
            throw exception;
        }
    }

    private Files()
    {
    }

}
