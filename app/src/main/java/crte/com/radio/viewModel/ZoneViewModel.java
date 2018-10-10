package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import crte.com.radio.dao.ZoneModel;
import crte.com.radio.entry.Zone;

public class ZoneViewModel {
    private Context context;
    private ViewCallBack callBack;
    public final ObservableField<List<Zone>> data = new ObservableField<>();

    public ZoneViewModel(Context context,ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        data.set(new ArrayList<Zone>());
    }

    public void initData() {
        data.get().clear();
        data.get().addAll(ZoneModel.select());
        callBack.loadDataComplete();
    }

    public interface ViewCallBack {
        void loadDataComplete();
    }
}
