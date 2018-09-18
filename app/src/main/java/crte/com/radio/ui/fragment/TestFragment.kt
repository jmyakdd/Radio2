package crte.com.radio.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import crte.com.radio.R
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("test","onCreateView")
        var view = inflater.inflate(R.layout.fragment_test, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("test","onViewCreated")
        text.text = "one"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("test","onCreateView")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("test","onCreateView")
    }

    override fun onStart() {
        super.onStart()
        Log.e("test","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("test","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("test","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("test","onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("test","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("test","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("test","onDetach")
    }
}