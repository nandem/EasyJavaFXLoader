package javafx.annotation;

import java.lang.annotation.*;

/**
 * 面文件路径注解
 * 注意：
 * <ol>
 *     <li>此注解必须用在构造函数上</li>
 *     <li>此注解与{@link javafx.EasyInitialization}联合使用可以方便的初始化所有需要加载fxml文件作为界面的组件</li>
 * </ol>
 *
 * @author Nandem on 2017-3-13.
 * @see javafx.EasyInitialization
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Load
{
    String view() default "";
    String css() default "";
}
