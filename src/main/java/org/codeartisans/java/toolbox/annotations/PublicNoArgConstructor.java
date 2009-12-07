package org.codeartisans.java.toolbox.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Paul Merlin <p.merlin@nosphere.org>
 */
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface PublicNoArgConstructor
{
}
