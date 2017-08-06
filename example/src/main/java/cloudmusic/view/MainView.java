package cloudmusic.view;

import javafx.annotation.Load;
import javafx.fxml.FXML;
import javafx.scene.layout.EasyPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Nandem on 2017/7/31.
 */
@Load(view = "/cloudmusic/layout/main_view.fxml", css = "/cloudmusic/css/main_view.css")
public class MainView extends EasyPane
{
    private Stage stage;
    @FXML
    private Pane topPaneContainer;

    public MainView(Stage stage)
    {
        this.stage = stage;
    }

    @Override
    public void initialize()
    {
        switchUndecorated();
    }

    /*^_^*------无系统窗口时窗口拖动-----------------------------------------------------*^_^*/
    private double xOffset = 0;
    private double yOffset = 0;

    private void switchUndecorated()
    {
        /*
         * 拖动开始
         */
        this.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        /*
         * 拖动中
         */
        this.setOnMouseDragged(event -> {
            event.consume();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        /*
         * 拖动结束
         */
        this.setOnMouseReleased(event -> {
            System.out.println("结束");
            if(event.getScreenY() - yOffset < 0)
            {
                stage.setY(0);
            }
            /*/
            if(event.getScreenX() - xOffset < 0)
            {
                stage.setX(0);
            }
            //*/
        });
    }
}
