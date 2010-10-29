package org.codeartisans.java.toolbox;

public final class Cleaner
{

    private Cleaner()
    {
    }

    /**
     * @param bytes Bytes to fill with zeroes.
     */
    public static void empty( byte[] bytes )
    {
        for ( int idx = 0; idx < bytes.length; idx++ ) {
            bytes[idx] = 0;
        }
    }

    /**
     * @param chars Chars to fill with spaces.
     */
    public static void empty( char[] chars )
    {
        for ( int idx = 0; idx < chars.length; idx++ ) {
            chars[idx] = ' ';
        }
    }

    /**
     * @param sb StringBuffer to fill with zeroes.
     */
    public static void empty( StringBuffer sb )
    {
        if ( sb != null ) {
            sb.delete( 0, sb.length() );
        }
    }

}
