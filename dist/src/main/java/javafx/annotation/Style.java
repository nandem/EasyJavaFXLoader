package javafx.annotation;

import javafx.enums.Cursor;

import java.lang.annotation.*;

/**
 * @author Nandem on 2017/8/8.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Style
{
    Cursor cursor() default Cursor.DEFAULT;
}
