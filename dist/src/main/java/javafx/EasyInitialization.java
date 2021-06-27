package javafx;

import javafx.annotation.Load;
import javafx.annotation.Event;
import javafx.annotation.Style;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import static javafx.utils.CommonUtil.checkNotNull;

/**
 * FXML注解初始化类，通过继承本接口的实现类可以轻松地使用注解 {@link Load}初始化界面而不需要写冗长的初始化代码
 * <p>
 * 注意：
 * <ol>
 * <li>Load注解必须使用在构造函数上</li>
 * <li>所有使用fxml文件初始化的组件，都需要将fxml文件的根节点变为<fx:root></fx:root></li>
 * </ol>
 *
 * @author Nandem on 2017/3/13.
 * @see Load
 */
public interface EasyInitialization
{
    /**
     * 由于反射时的类型擦除，故无法判断父子类的从属关系，索性所有点击事件的类都属于同一个包
     * 都有同样的鼠标点击方法，故以此折中
     *
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

    default void initFields()
    {

        Class clazz = this.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for(Field f : fields)
        {
            f.setAccessible(true);

            initStyle(f);
            initEvent(clazz, f);
        }

    }

    default void initStyle(Field f)
    {
        try
        {
            Annotation styleAnnotation = checkNotNull(f.getAnnotation(Style.class));
            ((Node)f.get(this)).setCursor(((Style)styleAnnotation).cursor().getCursor());
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch(NullPointerException e)
        {
            System.out.println("朕知道了");
        }
    }

    default void initEvent(Class clazz, Field f)
    {
        try
        {
            Annotation actionAnnotation = checkNotNull(f.getAnnotation(Event.class));
            if(isFromSamePackage(f.getType().getTypeName(), Control.class.getTypeName()))
            {
                ((Control) f.get(this)).setOnMouseClicked(event -> {
                    String methodName = ((Event) actionAnnotation).mouseClicked();

                    Method[] methods = clazz.getDeclaredMethods();

                    invoke(this, methodName, methods);
                });

                ((Control) f.get(this)).setOnMousePressed(event -> {
                    String methodName = ((Event) actionAnnotation).mousePressed();

                    Method[] methods = clazz.getDeclaredMethods();

                    invoke(this, methodName, methods);
                });

                ((Control) f.get(this)).setOnMouseReleased(event -> {
                    String methodName = ((Event) actionAnnotation).mouseReleased();

                    Method[] methods = clazz.getDeclaredMethods();

                    invoke(this, methodName, methods);
                });

                ((Control) f.get(this)).setOnMouseEntered(event -> {
                    String methodName = ((Event) actionAnnotation).mouseEntered();

                    Method[] methods = clazz.getDeclaredMethods();

                    invoke(this, methodName, methods);
                });

                ((Control) f.get(this)).setOnMouseExited(event -> {
                    String methodName = ((Event) actionAnnotation).mouseExited();

                    Method[] methods = clazz.getDeclaredMethods();

                    invoke(this, methodName, methods);
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

    default void invoke(Object obj, String methodName, Method[] methods)
    {
        for(Method m : methods)
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
    }

    /**
     * 通过读取构造器上的注解值来加载界面
     */
    default void loadUI()
    {
        Class<? extends EasyInitialization> clazz = this.getClass();

        try
        {
            Load uiAnnotation = checkNotNull(clazz.getAnnotation(Load.class), "请指定界面文件路径");
            String uiPath = uiAnnotation.view();
            String cssPath = uiAnnotation.css();

            LoadUIUtil.load(uiPath, this);

            if(!cssPath.equals(""))
            {
                loadCss(cssPath);
            }
        }
        catch(Exception e)
        {
            //如果没有Load注解，什么都不做，他可能自己去加载
        }
    }

    default void loadCss(String path)
    {
        ((Pane) this).getStylesheets().add(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
    }

    default void beforeInitialize()
    {
        loadUI();
        initFields();
        initialize();
    }

    default void initialize()
    {

    }

    default void setWidth(int width)
    {
        ((Pane) this).setPrefWidth(width);
        ((Pane) this).setMinWidth(width);
        ((Pane) this).setMaxWidth(width);
    }

    default void setHeight(int height)
    {
        ((Pane) this).setPrefHeight(height);
        ((Pane) this).setMinHeight(height);
        ((Pane) this).setMaxHeight(height);
    }
}
