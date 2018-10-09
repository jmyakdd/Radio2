package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jmy.mycustomerviewlibrary.inf.RefreshLoadListener
import crte.com.radio.R
import crte.com.radio.adapter.AudioAdapter
import crte.com.radio.databinding.ActivityAudioBinding
import crte.com.radio.viewModel.AudioViewModel

class AudioActivity : BaseTitleActivity(), RefreshLoadListener, AudioViewModel.ViewCallBack {
    override fun completeRefresh() {

    }

    override fun completeLoad() {
    }

    override fun upLoad() {

    }

    override fun downRefresh() {

    }

    lateinit var binding: ActivityAudioBinding
    lateinit var viewModel: AudioViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_audio)
        viewModel = AudioViewModel(this, this)

        setBack()
        setTitle("录音回放")

        var adapter = AudioAdapter(this, viewModel.data.get()!!)
        binding.myRecyclerview.setLoadListener(this)
        binding.myRecyclerview.setAdapter(adapter)
        binding.myRecyclerview.setLayoutManager(LinearLayoutManager(this))
    }
}