package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

public class ReplyMessageViewModel {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();

    private Context context;
    private ViewCallBack callBack;

    public ReplyMessageViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        name.set("收信人：jmy");
        content.set("");
    }

    public void send(){

    }

    public interface ViewCallBack {

    }
}
