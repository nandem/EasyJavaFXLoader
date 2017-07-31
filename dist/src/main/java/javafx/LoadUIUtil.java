package javafx;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * 自定义模块 界面加载工具
 *
 * @author Nandem on 2016/10/26.
 */
public class LoadUIUtil
{
    /**
     * <p>加载界面</p>
     * <br>注意：如果界面加载失败，将退出整个程序！
     *
     * @param uiPath   界面路径
     * @param uiObject 界面对象
     */
    public static boolean load(String uiPath, EasyInitialization uiObject)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadUIUtil.class.getResource(uiPath));
        fxmlLoader.setRoot(uiObject);
        fxmlLoader.setController(uiObject);

        try
        {
            fxmlLoader.load();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        return true;
    }
}
