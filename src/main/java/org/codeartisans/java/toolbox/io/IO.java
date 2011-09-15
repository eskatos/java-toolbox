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

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class IO
{

    public static void closeSilently( Closeable closeable )
    {
        if ( closeable != null ) {
            try {
                closeable.close();
            } catch ( IOException ignored ) {
            }
        }
    }

    public static void closeSilently( Connection connection )
    {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException ignored ) {
            }
        }
    }

    public static void closeSilently( Statement statement )
    {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException ignored ) {
            }
        }
    }

    public static void closeSilently( ResultSet resultSet )
    {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException ignored ) {
            }
        }
    }

    private IO()
    {
    }

}
