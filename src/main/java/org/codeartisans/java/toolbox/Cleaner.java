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
