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
package org.codeartisans.java.toolbox.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public final class FreePortFinder
{

    public static final String LOOPBACK = "127.0.0.1";
    public static final String ALL_INTERFACES = "0.0.0.0";

    private FreePortFinder()
    {
    }

    public static int findRandom()
            throws IOException
    {
        return findRandomOnIface( null );
    }

    public static int findWithPreference( final int prefered )
            throws IOException
    {
        return findOnIfaceWithPreference( null, prefered );
    }

    public static int findRandomOnIface( final InetAddress address )
            throws IOException
    {
        return findOnIfaceWithPreference( address, -1 );
    }

    public static int findOnIfaceWithPreference( final InetAddress address, final int prefered )
            throws IOException
    {
        ServerSocket server;
        if ( prefered > 0 ) {
            server = new ServerSocket( prefered, 1, address );
        } else {
            server = new ServerSocket( 0, 1, address );
        }
        int port = server.getLocalPort();
        server.close();
        return port;
    }

}
