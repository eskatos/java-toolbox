/*
 * Copyright (c) 2009 Paul Merlin <paul@nosphere.org>
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
package org.codeartisans.java.toolbox.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * @author Paul Merlin <paul@nosphere.org>
 */
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
