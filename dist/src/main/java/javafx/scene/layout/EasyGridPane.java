package javafx.scene.layout;

import javafx.EasyInitialization;

/**
 * @author Nandem on 2017/3/13.
 */
public abstract class EasyGridPane extends GridPane implements EasyInitialization
{
    protected EasyGridPane()
    {
        loadUI();
        initEvent();
    }
}
