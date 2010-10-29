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
package org.codeartisans.java.toolbox.annotations;

//import java.lang.reflect.Modifier;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes( "org.codeartisans.java.toolbox.annotations.PublicNoArgConstructor" )
@SupportedSourceVersion( SourceVersion.RELEASE_6 )
public final class PublicNoArgConstructorProcessor
        extends AbstractProcessor
{

    @Override
    public boolean process( Set<? extends TypeElement> annotations, RoundEnvironment env )
    {
        for ( TypeElement type : annotations ) {
            processNoArgsConstructorClasses( env, type );
        }
        return true;
    }

    private void processNoArgsConstructorClasses(
            RoundEnvironment env, TypeElement type )
    {
        for ( Element element : env.getElementsAnnotatedWith( type ) ) {
            processClass( element );
        }
    }

    private void processClass( Element element )
    {
        if ( !doesClassContainNoArgsConstructor( element ) ) {
            processingEnv.getMessager().printMessage( Diagnostic.Kind.ERROR,
                                                      "Class " + element + " needs a Public No-Args Constructor" );
        }
    }

    private boolean doesClassContainNoArgsConstructor( Element el )
    {
        for ( Element subelement : el.getEnclosedElements() ) {
            if ( subelement.getKind() == ElementKind.CONSTRUCTOR
                    && subelement.getModifiers().contains( Modifier.PUBLIC ) ) {
                TypeMirror mirror = subelement.asType();
                if ( mirror.accept( NO_ARGS_VISITOR, null ) ) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final TypeVisitor<Boolean, Void> NO_ARGS_VISITOR = new SimpleTypeVisitor6<Boolean, Void>()
    {

        @Override
        public Boolean visitExecutable( ExecutableType t, Void v )
        {
            return t.getParameterTypes().isEmpty();
        }

    };
}
