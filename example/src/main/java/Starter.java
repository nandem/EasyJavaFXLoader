import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainView;

/**
 * @author Nandem on 2017/7/31.
 */
public class Starter extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MainView root = new MainView();
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("是不是要添加一个标题栏配置的功能？");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
