package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import crte.com.radio.test.MyTestObservableOnSubscribe;
import crte.com.radio.test.MyTestObserver;
import crte.com.radio.dao.MessageModel;
import crte.com.radio.entry.Message;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageViewModel {
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<List<Message>> data = new ObservableField<>();

    public MessageViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.data.set(new ArrayList<Message>());
    }

    public void refreshData() {
        Observable observable = Observable.create(new MyTestObservableOnSubscribe());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyTestObserver() {
                    @Override
                    public void onNext(Object o) {
                        data.get().clear();
                        List<Message> list = MessageModel.select();
                        data.get().addAll(list);
                        callBack.completeRefresh();
                    }
                });
    }

    public void loadData() {
        Observable observable = Observable.create(new MyTestObservableOnSubscribe());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyTestObserver() {
                    @Override
                    public void onNext(Object o) {
                        List<Message> list = MessageModel.select();
                        data.get().addAll(list);
                        callBack.completeLoad();
                    }
                });
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
