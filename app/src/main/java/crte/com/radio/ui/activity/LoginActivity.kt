package crte.com.radio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import crte.com.radio.R
import crte.com.radio.databinding.ActivityLoginBinding
import crte.com.radio.viewModel.LoginViewModel

class LoginActivity : BaseActivity(), LoginViewModel.ViewCallBack {
    override fun loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
    }

    override fun showLoginError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = LoginViewModel(this, this)
        binding.viewModel = viewModel
    }
}