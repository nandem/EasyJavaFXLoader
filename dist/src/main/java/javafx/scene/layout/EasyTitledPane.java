package javafx.scene.layout;

import javafx.scene.control.TitledPane;
import javafx.EasyInitialization;

/**
 * @author Nandem on 2017/3/13.
 */
public abstract class EasyTitledPane extends TitledPane implements EasyInitialization
{
    protected EasyTitledPane()
    {
        loadUI();
        initEvent();
        initialize();
    }
}
