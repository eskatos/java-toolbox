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

import java.util.Map;
import java.util.Random;
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

    public static StringBuffer renderTemplate( final StringBuffer template,
                                               final Map<String, String> dict,
                                               final boolean removeUnknown )
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

    public static StringBuffer renderTemplate( final StringBuffer template,
                                               final Map<String, String> dict )
    {
        return renderTemplate( template, dict, false );
    }

    public static String random( int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random )
    {
        if ( count == 0 ) {
            return "";
        } else if ( count < 0 ) {
            throw new IllegalArgumentException( "Requested random string length " + count + " is less than 0." );
        }
        if ( ( start == 0 ) && ( end == 0 ) ) {
            end = 'z' + 1;
            start = ' ';
            if ( !letters && !numbers ) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }

        StringBuilder buffer = new StringBuilder();
        int gap = end - start;

        while ( count-- != 0 ) {
            char ch;
            if ( chars == null ) {
                ch = ( char ) ( random.nextInt( gap ) + start );
            } else {
                ch = chars[random.nextInt( gap ) + start];
            }
            if ( ( letters && numbers && Character.isLetterOrDigit( ch ) )
                    || ( letters && Character.isLetter( ch ) )
                    || ( numbers && Character.isDigit( ch ) )
                    || ( !letters && !numbers ) ) {
                buffer.append( ch );
            } else {
                count++;
            }
        }
        return buffer.toString();
    }

}
