package cloudmusic.view;

import javafx.annotation.Load;
import javafx.annotation.Event;
import javafx.annotation.Style;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.EasyPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.utils.ViewUtil;

/**
 * @author Nandem on 2017/7/31.
 */
@Load(view = "/cloudmusic/layout/main_view.fxml", css = "/cloudmusic/css/main_view.css")
public class MainView extends EasyPane
{
  private final Stage stage;

  @FXML
  private Pane topPaneContainer;
  @FXML
  private Pane topPane;

  @FXML
  @Style(cursor = javafx.enums.Cursor.HAND)
  @Event(mouseClicked = "min")
  private Button min;

  @FXML
  @Style(cursor = javafx.enums.Cursor.HAND)
  @Event(mouseClicked = "max")
  private Button max;

  @FXML
  private WebView webView;

  @FXML
  @Style(cursor = javafx.enums.Cursor.HAND)
  @Event(mouseClicked = "close", mouseEntered = "closeEntered", mouseExited = "closeExited")
  private Button close;

  public MainView(Stage stage)
  {
    this.stage = stage;
  }

  @Override
  public void initialize()
  {
    switchUndecorated();
    System.out.println(webView);

    WebEngine we = webView.getEngine();

    we.load("http://47.92.81.118:8081");
  }

  private void closeEntered()
  {
    System.out.println("鼠标进入");
  }

  private void closeExited()
  {
    System.out.println("鼠标移出");
  }

  private void min()
  {
    stage.setIconified(true);
  }

  private void max()
  {
    if (!stage.isMaximized())
    {
      stage.setMaximized(true);
      topPaneContainer.setPrefWidth(ViewUtil.getScreenWidth());
      topPane.setPrefWidth(ViewUtil.getScreenWidth());
    }
    else
    {
      int originWidth = 1022;
      stage.setMaximized(false);
      topPaneContainer.setPrefWidth(originWidth);
      topPane.setPrefWidth(originWidth);
    }
  }

  private void close()
  {
    stage.close();
  }

  /*^_^*------无系统窗口时窗口拖动-----------------------------------------------------*^_^*/
  private double xOffset = 0;
  private double yOffset = 0;

  private void switchUndecorated()
  {
    /*
     * 拖动开始
     */
    topPaneContainer.setOnMousePressed(event ->
    {
      event.consume();
      xOffset = event.getSceneX();
      yOffset = event.getSceneY();
    });
    /*
     * 拖动中
     */
    topPaneContainer.setOnMouseDragged(event ->
    {
      event.consume();
      if (stage.isMaximized())
      {
        return;
      }
      stage.setX(event.getScreenX() - xOffset);
      stage.setY(event.getScreenY() - yOffset);
    });
    /*
     * 拖动结束
     */
    topPaneContainer.setOnMouseReleased(event ->
    {
      System.out.println("结束");
      if (event.getScreenY() - yOffset < 0)
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