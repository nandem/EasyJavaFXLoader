package javafx.scene.layout;

import javafx.EasyInitialization;

/**
 * @author Nandem on 2017/3/13.
 */
public abstract class EasyFlowPane extends FlowPane implements EasyInitialization
{
    protected EasyFlowPane()
    {
        loadUI();
        initEvent();
        initialize();
    }
}
