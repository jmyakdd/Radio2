package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import crte.com.radio.entry.Voice;

public class AudioViewModel {
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<List<Voice>> data = new ObservableField<>();

    public AudioViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.data.set(new ArrayList<Voice>());
    }

    private void getData() {

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

    }


    public interface ViewCallBack {
        void completeRefresh();

        void completeLoad();
    }
}
