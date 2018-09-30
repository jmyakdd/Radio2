package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import crte.com.radio.entry.NormalMessageEvent;

public class MainViewModel implements NormalMessageDataDealInf {
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<String> single = new ObservableField<>();
    public final ObservableField<String> contactName = new ObservableField<>();
    public final ObservableField<String> callStatus = new ObservableField<>();

    public MainViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        single.set("ch2");
        contactName.set("通话组123");
        single.get();
    }

    @Override
    public void dealMessage(NormalMessageEvent msg) {
        switch (msg.getCode()) {
            case NormalMessageEvent.CALL_START:
                callStatus.set(msg.getMessage());
                break;
            case NormalMessageEvent.CALL_END:
                callStatus.set(msg.getMessage());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        callStatus.set("");
                    }
                }).start();
                break;
        }
    }

    public interface ViewCallBack {
        /*void callStart(Contact contact);

        void callEnd();*/
    }
}
