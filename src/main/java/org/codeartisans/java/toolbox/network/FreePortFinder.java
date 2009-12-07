package org.codeartisans.java.toolbox.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public final class FreePortFinder
{

    public static final String LOOPBACK = "127.0.0.1";
    public static final String ALL_INTERFACES = "0.0.0.0";

    public static int findRandom()
            throws IOException
    {
        return findRandomOnIface(null);
    }

    public static int findWithPreference(final int prefered)
            throws IOException
    {
        return findOnIfaceWithPreference(null, prefered);
    }

    public static int findRandomOnIface(final InetAddress address)
            throws IOException
    {
        return findOnIfaceWithPreference(address, -1);
    }

    public static int findOnIfaceWithPreference(final InetAddress address, final int prefered)
            throws IOException
    {
        ServerSocket server;
        if (prefered > 0) {
            server = new ServerSocket(prefered, 1, address);
        } else {
            server = new ServerSocket(0, 1, address);
        }
        int port = server.getLocalPort();
        server.close();
        return port;
    }

}
