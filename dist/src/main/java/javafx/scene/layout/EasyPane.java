package javafx.scene.layout;

import javafx.EasyInitialization;

/**
 * @author Nandem on 2017/3/13.
 */
public abstract class EasyPane extends Pane implements EasyInitialization
{
    protected EasyPane()
    {
        loadUI();
        initEvent();
    }
}
