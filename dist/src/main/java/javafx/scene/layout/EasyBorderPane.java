package javafx.scene.layout;

import javafx.EasyInitialization;

/**
 * @author Nandem on 2017/3/13.
 */
public abstract class EasyBorderPane extends BorderPane implements EasyInitialization
{
    protected EasyBorderPane()
    {
        beforeInitialize();
    }
}
