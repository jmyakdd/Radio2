package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import crte.com.radio.dao.ContactDbModel;
import crte.com.radio.entry.Contact;
import crte.com.radio.message.NormalMessageEvent;

public class MainViewModel implements NormalMessageDataDealInf {
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<String> single = new ObservableField<>();
    public final ObservableField<String> contactName = new ObservableField<>();
    public final ObservableField<String> callStatus = new ObservableField<>();

    public MainViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;

        //初始化请求
        init();
        single.set("ch2");
        contactName.set("通话组123");
    }

    private void init() {

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
            case NormalMessageEvent.CHANGE_CHANNEL:
                int id = (int) msg.getData();
                Contact contact = ContactDbModel.selectUserById(id);
                single.set("ch1");
                contactName.set(contact.getSignalName());
                break;
        }
    }

    public interface ViewCallBack {
        /*void callStart(Contact contact);

        void callEnd();*/
    }
}
