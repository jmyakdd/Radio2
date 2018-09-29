package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import crte.com.radio.entry.NormalMessageEvent;

public class MainViewModel implements NormalMessageDataDealInf{
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<String>single = new ObservableField<>();
    public final ObservableField<String>contactName = new ObservableField<>();

    public MainViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        single.set("ch2");
        contactName.set("通话组123");
        single.get();
    }

    @Override
    public void dealMessage(NormalMessageEvent msg) {

    }

    public interface ViewCallBack{
        void showCallDialog();
    }
}
