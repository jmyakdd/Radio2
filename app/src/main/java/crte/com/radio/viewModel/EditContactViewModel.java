package crte.com.radio.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import crte.com.radio.dao.ContactDbModel;
import crte.com.radio.entry.Contact;
import crte.com.radio.util.ToastUtil;

public class EditContactViewModel {
    private Context context;
    private ViewCallBack callBack;

    public final ObservableField<String> id = new ObservableField();
    public final ObservableField<String> name = new ObservableField();

    public EditContactViewModel(Context context, ViewCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void save() {
        if (id.get() == null || id.get().equals("")) {
            ToastUtil.showShort("id不能为空");
            return;
        }
        if (name.get() == null || name.get().equals("")) {
            ToastUtil.showShort("昵称不能为空");
            return;
        }
        Contact user = new Contact(Long.parseLong(id.get()), name.get());
        long result = ContactDbModel.insert(user);
        if (result == 0) {
            callBack.saveFail();
        } else {
            callBack.saveSuccess();
        }
    }

    public void clear() {
        id.set("");
        name.set("");
    }

    public interface ViewCallBack {
        void saveSuccess();

        void saveFail();
    }
}
