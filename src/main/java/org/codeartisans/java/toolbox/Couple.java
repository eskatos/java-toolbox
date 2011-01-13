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

public class Couple<L, R>
{

    private final L left;
    private final R right;

    public Couple( L left, R right )
    {
        this.left = left;
        this.right = right;
    }

    public final L left()
    {
        return left;
    }

    public final R right()
    {
        return right;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        @SuppressWarnings( "unchecked" )
        final Couple<L, R> other = ( Couple<L, R> ) obj;
        if ( this.left != other.left && ( this.left == null || !this.left.equals( other.left ) ) ) {
            return false;
        }
        if ( this.right != other.right && ( this.right == null || !this.right.equals( other.right ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + ( this.left != null ? this.left.hashCode() : 0 );
        hash = 79 * hash + ( this.right != null ? this.right.hashCode() : 0 );
        return hash;
    }

}
