package org.codeartisans.java.toolbox.guice;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public final class GuiceHelper
{

    private GuiceHelper()
    {
    }

    public static void enableDebugOutput()
    {
        Logger guiceLogger = Logger.getLogger("com.google.inject");
        guiceLogger.addHandler(new ConsoleHandler()
        {

            {
                setLevel(Level.ALL);
                setFormatter(new java.util.logging.Formatter()
                {

                    @Override
                    public String format(LogRecord record)
                    {
                        return String.format("[Guice] %s%n", record.getMessage());
                    }

                });
            }

        });
        guiceLogger.setLevel(Level.ALL);
    }

}
