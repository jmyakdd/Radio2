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

class Test1Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("test1","onCreateView")
        var view = inflater.inflate(R.layout.fragment_test, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("test1","onViewCreated")
        text.text = "two"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("test1","onCreateView")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("test","onCreateView")
    }

    override fun onStart() {
        super.onStart()
        Log.e("test1","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("test1","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("test1","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("test1","onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("test1","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("test1","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("test1","onDetach")
    }
}