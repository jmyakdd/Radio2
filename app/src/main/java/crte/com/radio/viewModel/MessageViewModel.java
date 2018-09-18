package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import crte.com.radio.dao.MessageModel;
import crte.com.radio.entry.Message;

public class MessageViewModel {
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<List<Message>> data = new ObservableField<>();

    public MessageViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.data.set(new ArrayList<Message>());
    }

    private void getData() {
        List<Message> list = MessageModel.select();
        data.get().addAll(list);
    }

    public void refreshData() {
        data.get().clear();
        getData();
        callBack.completeRefresh();
    }

    public void loadData() {
        getData();
        callBack.completeLoad();
    }

    public void insert() {
        Message message = new Message();
        message.setContent("加水、吸污");
        message.setTime(System.currentTimeMillis());
        MessageModel.insert(message);
    }


    public interface ViewCallBack {
        void completeRefresh();

        void completeLoad();
    }
}
