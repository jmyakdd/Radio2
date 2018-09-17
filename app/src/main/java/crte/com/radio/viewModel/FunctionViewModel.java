package crte.com.radio.viewModel;

import android.content.Context;

public class FunctionViewModel {

    private Context context;
    private ViewCallBack callBack;

    public FunctionViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public interface ViewCallBack{

    }
}
