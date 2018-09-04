package crte.com.radio

import android.os.Bundle
import crte.com.radio.activity.BaseActivity
import crte.com.radio.api.ApiServiceHelper
import crte.com.radio.api.BaseListResponseResult
import crte.com.radio.entry.Contact
import crte.com.radio.entry.NormalMessageEvent
import crte.com.radio.entry.StatusMessageEvent
import crte.com.radio.entry.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contact.setOnClickListener {
            //            jump(ContactActivity::class.java)
            ApiServiceHelper.getInstance().apiService.getUserList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<BaseListResponseResult<User>> {

                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(t: BaseListResponseResult<User>) {
                            log(t.toString())
                        }

                        override fun onError(e: Throwable) {

                        }

                    })
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveMessage(msgEvent: StatusMessageEvent) {
        when (msgEvent.code) {
            -1 -> {
                var contact: Contact = msgEvent.obj as Contact
                log(msgEvent.message!! + contact.name)
                showToast(msgEvent.message!! + contact.name)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveNormalMessage(normalMessageEvent: NormalMessageEvent) {
        when (normalMessageEvent.code) {
            -1 -> {
                showToast(normalMessageEvent.msg!!)
            }
        }
    }
}
