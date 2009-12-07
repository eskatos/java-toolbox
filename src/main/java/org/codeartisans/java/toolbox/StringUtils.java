package org.codeartisans.java.toolbox;

public final class StringUtils
{

    private StringUtils()
    {
    }

    public static boolean isEmpty(String s)
    {
        return s == null || s.length() <= 0;
    }

    public static boolean isEmpty(char[] array)
    {
        return array == null || array.length <= 0;
    }

}
