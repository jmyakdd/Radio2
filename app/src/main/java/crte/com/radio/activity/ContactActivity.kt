package crte.com.radio.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.jmy.mycustomerviewlibrary.inf.RefreshLoadListener
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import crte.com.radio.R
import crte.com.radio.adapter.ContactAdapter
import crte.com.radio.entry.Contact
import crte.com.radio.entry.StatusMessageEvent
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_contact.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ContactActivity : BaseTitleActivity(), RefreshLoadListener {
    override fun upLoad() {
        Observable.create(object : ObservableOnSubscribe<String> {

            override fun subscribe(emitter: ObservableEmitter<String>) {
                Thread.sleep(2000)
                emitter.onNext("sss")
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {

                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: String) {
                        getData()
                        my_recyclerview.notifyDataSetChanged()
                    }


                    override fun onError(e: Throwable) {

                    }
                })
    }

    override fun downRefresh() {
        Observable.create(object : ObservableOnSubscribe<String> {

            override fun subscribe(emitter: ObservableEmitter<String>) {
                Thread.sleep(2000)
                emitter.onNext("sss")
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {

                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: String) {
                        data.clear()
                        if (count % 2 != 0)
                            getData()
                        count++
                        my_recyclerview.notifyDataSetChanged()
                        if (data.size == 0) {
                            my_recyclerview.stopRefresh(0, true)
                        } else {
                            my_recyclerview.stopRefresh(1, true)
                        }
                    }


                    override fun onError(e: Throwable) {

                    }
                })
    }

    var count = 0
    var data = mutableListOf<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        setBack()
        setTitle("通讯录")
        var adapter = ContactAdapter(this, data)

        my_recyclerview.setAdapter(adapter)
        my_recyclerview.setLayoutManager(LinearLayoutManager(this))
        my_recyclerview.setLoadListener(this)

        /*rv_contact.adapter = adapter
        rv_contact.layoutManager = LinearLayoutManager(this)*/
        adapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return true
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                Toast.makeText(this@ContactActivity, data.get(position).name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        my_recyclerview.startRefresh()
    }

    fun getData() {
        for (i in 0..20) {
            var d = Contact()
            d.id = i
            d.name = "name${i}"
            data.add(d)
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
}
