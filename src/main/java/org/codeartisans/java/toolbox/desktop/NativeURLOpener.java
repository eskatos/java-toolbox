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
package org.codeartisans.java.toolbox.desktop;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javax.swing.JOptionPane;

public final class NativeURLOpener
{

    private NativeURLOpener()
    {
    }

    public static void open( final URI uri )
            throws IOException
    {
        if ( Desktop.isDesktopSupported() ) {
            Desktop.getDesktop().browse( uri );
        } else if ( System.getenv( "DESKTOP_SESSION" ).startsWith( "KDE" ) ) {
            new ProcessBuilder( "kfmclient", "openURL", uri.toASCIIString() ).start();
        } else {
            // TODO i18n
            JOptionPane.showMessageDialog( null, uri.toString(), "URL", JOptionPane.INFORMATION_MESSAGE );
        }
    }

}
