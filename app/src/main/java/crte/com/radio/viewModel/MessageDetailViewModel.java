package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import crte.com.radio.entry.Message;
import crte.com.radio.util.DateTimeUtil;

public class MessageDetailViewModel {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> time = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();

    private Context context;
    private ViewCallBack callBack;

    public MessageDetailViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        name.set("发信人：");
        time.set("时间：");
        content.set("");
    }

    public void setData() {
        Message message = new Message();
        message.setName("jmy");
        message.setContent("设法时代峰峻我看话费长江索道");
        message.setTime(System.currentTimeMillis());

        name.set("发信人：" + message.getName());
        time.set("时间：" + DateTimeUtil.getTimeOfStr(message.getTime()));
        content.set(message.getContent());
    }

    public interface ViewCallBack {

    }
}
