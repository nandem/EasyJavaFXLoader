package javafx;

import javafx.annotation.Load;
import javafx.annotation.MouseClicked;
import javafx.scene.control.Control;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static javafx.utils.CommonUtil.checkNotNull;

/**
 * FXML注解初始化类，通过继承本接口的实现类可以轻松地使用注解 {@link javafx.annotation.Load}初始化界面而不需要写冗长的初始化代码
 *
 * 注意：
 * <ol>
 *     <li>Load注解必须使用在构造函数上</li>
 *     <li>所有使用fxml文件初始化的组件，都需要将fxml文件的根节点变为<fx:root></fx:root></li>
 * </ol>
 *
 * @author Nandem on 2017/3/13.
 * @see javafx.annotation.Load
 */
public interface EasyInitialization
{
    /**
     * 由于反射时的类型擦除，故无法判断父子类的从属关系，索性所有点击事件的类都属于同一个包
     * 都有同样的鼠标点击方法，故以此折中
     * @return true：属于同一个包。false：不属于同一个包
     */
    default boolean isFromSamePackage(String one, String another)
    {
        String[] oneArray = one.toLowerCase().split(".");
        String[] anotherArray = another.toLowerCase().split(".");

        String oneTem = "", anotherTem = "";

        int limit = oneArray.length > anotherArray.length ? anotherArray.length : oneArray.length;

        for(int i = 0; i < limit - 1; i++)
        {
            one += oneArray[i];
            anotherTem += anotherArray[i];
        }
        return oneTem.equals(anotherTem);
    }

    default void initEvent()
    {
        Class clazz = this.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for(Field f : fields)
        {
            f.setAccessible(true);
            try
            {
                isFromSamePackage(f.getType().getTypeName(), Control.class.getTypeName());
                Annotation actionAnnotation = checkNotNull(f.getAnnotation(MouseClicked.class));
                if(isFromSamePackage(f.getType().getTypeName(), Control.class.getTypeName()))
                {

                    ((Control)f.get(this)).setOnMouseClicked(event ->
                    {
                        String methodName = ((MouseClicked)actionAnnotation).value();

                        for(Method m : clazz.getDeclaredMethods())
                        {
                            m.setAccessible(true);
                            if(m.getName().equals(methodName))
                            {
                                try
                                {
                                    m.invoke(this, null);
                                }
                                catch(IllegalAccessException e)
                                {
                                    e.printStackTrace();
                                }
                                catch(InvocationTargetException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
            catch(NullPointerException ne)
            {
                //这里什么都不用做，因为有的字段并不需要添加鼠标点击事件
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * 通过读取构造器上的注解值来加载界面
     */
    default void loadUI()
    {
        Class clazz = this.getClass();

        Constructor[] constructors = clazz.getConstructors();

        for(Constructor c : constructors)
        {
            try
            {
                Annotation uiAnnotation = checkNotNull(c.getAnnotation(Load.class));
                String uiPath = ((Load) uiAnnotation).value();
                LoadUIUtil.load(uiPath, this);
            }
            catch(Exception e)
            {
                //如果没有Load注解，什么都不做，他可能自己去加载
            }
        }
    }
}
