package crte.com.radio.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import crte.com.radio.R;

public class MenuSelectDialog extends Dialog{
    public MenuSelectDialog(@NonNull Context context) {
        super(context);
    }

    public MenuSelectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MenuSelectDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_menu_select);
    }
}
