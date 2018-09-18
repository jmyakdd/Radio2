package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

public class AboutViewModel {
    private Context context;
    private ViewCallBack callBack;

    public final ObservableField<String> radioId = new ObservableField<>();
    public final ObservableField<String> modelNum = new ObservableField<>();
    public final ObservableField<String> radioSw = new ObservableField<>();
    public final ObservableField<String> androidSw = new ObservableField<>();

    public AboutViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        radioId.set("电台ID：");
        modelNum.set("账号ID：");
        radioSw.set("电台版本：");
        androidSw.set("软件版本：");
    }

    public interface ViewCallBack {

    }
}
