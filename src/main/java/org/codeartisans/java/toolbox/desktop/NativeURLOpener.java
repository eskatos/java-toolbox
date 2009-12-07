package org.codeartisans.java.toolbox.desktop;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public final class NativeURLOpener
{

    private NativeURLOpener()
    {
    }

    public static void open(final URI uri)
            throws IOException
    {
        if (Desktop.isDesktopSupported()) {
            // Windows, MacOSX, Gnome
            Desktop.getDesktop().browse(uri);
            //} else if (SystemUtils.IS_OS_UNIX && System.getenv("DESKTOP_SESSION").startsWith("KDE")) {
        } else if (System.getenv("DESKTOP_SESSION").startsWith("KDE")) {
            new ProcessBuilder("kfmclient", "openURL", uri.toASCIIString()).start();
        } else {
            // TODO: Fenetre avec le lien à cliquer ou copier/coller
        }
    }

}
