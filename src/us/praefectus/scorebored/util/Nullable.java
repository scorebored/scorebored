package us.praefectus.scorebored.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Indicates that a parameter value can be null.
 */
@Documented
@Target(ElementType.PARAMETER)
public @interface Nullable {
}
