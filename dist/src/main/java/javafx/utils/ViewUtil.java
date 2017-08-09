package javafx.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * @author Nandem on 2017/8/9.
 */
public class ViewUtil
{
    //TODO 这里没有做多块屏幕的适配，需要做
    private static Screen screen = Screen.getPrimary();
    private static Rectangle2D bounds = screen.getVisualBounds();

    public static double getScreenWidth()
    {
        return bounds.getWidth();
    }

    public static double getScreenHeight()
    {
        return bounds.getHeight();
    }
}
