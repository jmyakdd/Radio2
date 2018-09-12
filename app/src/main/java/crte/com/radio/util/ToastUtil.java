package crte.com.radio.util;

import android.content.Context;
import android.widget.Toast;

import crte.com.radio.App;


/**
 * Created by CRTE-CD-13 on 2016/7/29.
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * Toast短时显示
     *
     * @param content
     */
    public static void showShort(String content) {
        if (toast == null) {
            Context m = App.instance.getApplicationContext();
            toast = Toast.makeText(m, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * Toast长时显示
     *
     * @param content
     */
    public static void showLong(String content) {
        if (toast == null) {
            toast = Toast.makeText(App.instance.getApplicationContext(), content, Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
