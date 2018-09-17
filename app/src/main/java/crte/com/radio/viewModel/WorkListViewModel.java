package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import crte.com.radio.dao.WorkModel;
import crte.com.radio.entry.Work;

public class WorkListViewModel {
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<List<Work>> data = new ObservableField<>();
    private int page = 1;

    public WorkListViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.data.set(new ArrayList<Work>());
    }

    private void getData() {
        List<Work> list = WorkModel.select();
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
        Work work = new Work(1L, "加水", System.currentTimeMillis());
        WorkModel.insert(work);
    }

    public interface ViewCallBack {
        void completeRefresh();

        void completeLoad();
    }
}
