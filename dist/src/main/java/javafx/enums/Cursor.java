package javafx.enums;

/**
 * @author Nandem on 2017/8/8.
 */
public enum Cursor
{
    DEFAULT,
    CROSSHAIR,
    TEXT,
    WAIT,
    MOVE,
    SW_RESIZE,
    SE_RESIZE,
    NW_RESIZE,
    NE_RESIZE,
    N_RESIZE,
    S_RESIZE,
    W_RESIZE,
    E_RESIZE,
    OPEN_HAND, CLOSED_HAND, HAND, H_RESIZE, V_RESIZE, DISAPPEAR, NONE;

    public javafx.scene.Cursor getCursor()
    {
        switch(this)
        {
            case DEFAULT:
                return javafx.scene.Cursor.DEFAULT;
            case CROSSHAIR:
                return javafx.scene.Cursor.CROSSHAIR;
            case TEXT:
                return javafx.scene.Cursor.TEXT;
            case WAIT:
                return javafx.scene.Cursor.WAIT;
            case MOVE:
                return javafx.scene.Cursor.MOVE;
            case SW_RESIZE:
                return javafx.scene.Cursor.SW_RESIZE;
            case SE_RESIZE:
                return javafx.scene.Cursor.SE_RESIZE;
            case NW_RESIZE:
                return javafx.scene.Cursor.NW_RESIZE;
            case NE_RESIZE:
                return javafx.scene.Cursor.NE_RESIZE;
            case N_RESIZE:
                return javafx.scene.Cursor.N_RESIZE;
            case S_RESIZE:
                return javafx.scene.Cursor.S_RESIZE;
            case W_RESIZE:
                return javafx.scene.Cursor.W_RESIZE;
            case E_RESIZE:
                return javafx.scene.Cursor.E_RESIZE;
            case OPEN_HAND:
                return javafx.scene.Cursor.OPEN_HAND;
            case CLOSED_HAND:
                return javafx.scene.Cursor.CLOSED_HAND;
            case HAND:
                return javafx.scene.Cursor.HAND;
            case H_RESIZE:
                return javafx.scene.Cursor.H_RESIZE;
            case V_RESIZE:
                return javafx.scene.Cursor.V_RESIZE;
            case DISAPPEAR:
                return javafx.scene.Cursor.DISAPPEAR;
            case NONE:
                return javafx.scene.Cursor.NONE;
        }

        return javafx.scene.Cursor.NONE;
    }
}
