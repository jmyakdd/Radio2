package crte.com.radio.viewModel

import android.content.Context
import android.databinding.Observable
import android.databinding.ObservableField
import android.util.Log

class LoginViewModel(val context: Context, val viewCallBack: ViewCallBack) {
    val account: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()
    val passwordCopy: ObservableField<String> = ObservableField()

    init {
        password.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            /**
             * Called by an Observable whenever an observable property changes.
             * @param sender The Observable that is changing.
             * @param propertyId The BR identifier of the property that has changed. The getter
             * for this property should be annotated with [Bindable].
             */
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                var change = sender as ObservableField<String>
                Log.e("test",change.get())
                passwordCopy.set(change.get())
            }
        })
    }

    fun login() {
        Log.e("test", account.get() + password.get())
        if (account.get() == null || account.get().equals("")) {
            viewCallBack.showLoginError("账号不能为空")
            return
        }
        if (password.get() == null || password.get().equals("")) {
            viewCallBack.showLoginError("密码不能为空")
            return
        }
        viewCallBack.loginSuccess()
    }

    interface ViewCallBack {
        fun loginSuccess()

        fun showLoginError(msg: String)
    }
}