package crte.com.radio.viewModel

import android.content.Context
import android.databinding.ObservableField
import crte.com.radio.dao.ContactDbModel
import crte.com.radio.entry.Contact
import crte.com.radio.entry.NormalMessageEvent
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ContactViewModel(val context: Context, val viewCallBack: ViewCallBack):NormalMessageDataDealInf {
    override fun dealMessage(msg: NormalMessageEvent?) {

    }

    val datas: ObservableField<MutableList<Contact>> = ObservableField()
    var page = 1

    init {
        datas.set(ArrayList<Contact>())
    }

    fun startRefresh() {
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
                        page = 1
                        datas.get()!!.clear()
                        getData()
                        page++
                        viewCallBack.completeRefresh()
                    }


                    override fun onError(e: Throwable) {

                    }
                })
    }

    fun startLoad() {
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
                        page++
                        viewCallBack.completeLoad()
                    }


                    override fun onError(e: Throwable) {

                    }
                })
    }

    fun getData() {
        var list = ContactDbModel.selectUserList()
        for (data in list) {
            var contact = Contact()
            contact.id = data.id
            contact.name = data.name
            datas.get()!!.add(contact)
        }
    }

    interface ViewCallBack {
        fun completeRefresh()

        fun completeLoad()
    }
}