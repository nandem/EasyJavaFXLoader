package cloudmusic.view;

import javafx.annotation.LoadView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.EasyPane;

/**
 * @author Nandem on 2017/7/31.
 */
@LoadView(path = "/cloudmusic/layout/main_view.fxml")
public class MainView extends EasyPane
{
    @Override
    public void initialize()
    {
        /*/
        this.setWidth(1022);
        this.setHeight(670);
        //*/

        /*
         * 拖动开始
         */
        this.setOnMousePressed(event ->
        {
            System.out.println("按下");
        });
        /*
         * 拖动中
         */
        this.setOnMouseDragged(event ->
        {
//            System.out.println("控件坐标：" + event.getX() + " | 屏幕坐标：" + event.getScreenX());
        });
        /*
         * 拖动结束
         */
        this.setOnMouseReleased(event ->
        {
            System.out.println("结束");
        });
    }
}
