package javafx.scene.layout;

import javafx.EasyInitialization;

/**
 * @author Nandem on 2017/3/13.
 */
public abstract class EasyAnchorPane extends AnchorPane implements EasyInitialization
{
    protected EasyAnchorPane()
    {
        loadUI();
        initEvent();
        initialize();
    }
}
