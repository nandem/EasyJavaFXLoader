package javafx.annotation;

import java.lang.annotation.*;

/**
 * @author Nandem on 2017/3/13.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MouseClicked
{
    String value() default "";
}
