package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

public class WorkViewModel {
    private Context context;
    public ObservableField<String> newWork = new ObservableField<>();
    public ObservableField<String> oldWork = new ObservableField<>();

    public WorkViewModel(Context context) {
        this.context = context;
        newWork.set("新的工单（0）");
        oldWork.set("结束工单（0）");
    }
}
