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
package org.codeartisans.java.toolbox;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils
{

    private static final String TEMPLATE_TOKEN_PATTERN_STR = "\\$\\{([^}]+)\\}";
    private static final Pattern TEMPLATE_TOKEN_PATTERN = Pattern.compile( TEMPLATE_TOKEN_PATTERN_STR );
    public static final String EMPTY = "";
    public static final char[] EMPTY_CHAR_ARRAY = EMPTY.toCharArray();

    private StringUtils()
    {
    }

    public static boolean isEmpty( String s )
    {
        return s == null || s.length() <= 0;
    }

    public static boolean isEmpty( char[] array )
    {
        return array == null || array.length <= 0;
    }

    public static String join( String[] strings )
    {
        return join( Arrays.asList( strings ) );
    }

    public static String join( String[] strings, String delimiter )
    {
        return join( Arrays.asList( strings ), delimiter );
    }

    public static String join( Iterable<? extends CharSequence> strings )
    {
        return join( strings, "" );
    }

    public static String join( Iterable<? extends CharSequence> strings, String delimiter )
    {
        int capacity = 0;
        int delimLength = delimiter.length();
        Iterator<? extends CharSequence> iter = strings.iterator();
        if ( iter.hasNext() ) {
            capacity += iter.next().length() + delimLength;
        }
        StringBuilder buffer = new StringBuilder( capacity );
        iter = strings.iterator();
        if ( iter.hasNext() ) {
            buffer.append( iter.next() );
            while ( iter.hasNext() ) {
                buffer.append( delimiter );
                buffer.append( iter.next() );
            }
        }
        return buffer.toString();
    }

    public static StringBuffer renderTemplate( StringBuffer template, Map<String, String> dict, boolean removeUnknown )
    {
        final Matcher matcher = TEMPLATE_TOKEN_PATTERN.matcher( template );
        final StringBuffer buffer = new StringBuffer();
        while ( matcher.find() ) {
            final String token = matcher.group( 1 );
            if ( token != null ) {
                final String replacement = dict.get( token );
                if ( replacement != null ) {
                    // Escape \ and $ because they are interpreted by the matcher object :
                    String quotedReplacement = Matcher.quoteReplacement( replacement );
                    matcher.appendReplacement( buffer, quotedReplacement );
                } else if ( removeUnknown ) {
                    matcher.appendReplacement( buffer, "" );
                }
            }
        }
        matcher.appendTail( buffer );
        return buffer;
    }

    public static StringBuffer renderTemplate( StringBuffer template, Map<String, String> dict )
    {
        return renderTemplate( template, dict, false );
    }

}
