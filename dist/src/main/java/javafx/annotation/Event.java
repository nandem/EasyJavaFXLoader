package javafx.annotation;

import java.lang.annotation.*;

/**
 * @author Nandem on 2017/3/13.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Event
{
    String mouseEntered() default "";
    String mouseExited() default "";
    String mousePressed() default "";
    String mouseReleased() default "";
    String mouseClicked() default "";
}
