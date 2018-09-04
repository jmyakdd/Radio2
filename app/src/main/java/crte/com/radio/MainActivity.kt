package crte.com.radio

import android.os.Bundle
import crte.com.radio.activity.BaseActivity
import crte.com.radio.activity.ContactActivity
import crte.com.radio.entry.Contact
import crte.com.radio.entry.NormalMessageEvent
import crte.com.radio.entry.StatusMessageEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contact.setOnClickListener {
            jump(ContactActivity::class.java)
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
