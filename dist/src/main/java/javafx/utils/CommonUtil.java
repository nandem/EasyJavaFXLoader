package javafx.utils;

import com.sun.istack.internal.Nullable;

/**
 * @author Nandem on 2017-06-11.
 */
public class CommonUtil
{
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }
}
