package id.widianapw.androidutils.view.auth

import android.os.Bundle
import android.widget.Toast
import id.widianapw.android_utils.extensions.add
import id.widianapw.android_utils.extensions.onClick
import id.widianapw.android_utils.extensions.validate
import id.widianapw.android_utils.extensions.value
import id.widianapw.android_utils.validator.ValidatorType
import id.widianapw.androidutils.R
import id.widianapw.androidutils.model.auth.AuthResponse
import id.widianapw.androidutils.presenter.auth.AuthDelegate
import id.widianapw.androidutils.presenter.auth.AuthPresenter
import id.widianapw.androidutils.utilities.doRequest
import id.widianapw.androidutils.view.common.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), AuthDelegate {
    private val presenter = AuthPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login?.onClick {
            if (validateForm()) {
                loginProcess()
            } else {
                Toast.makeText(this, "Fill all field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun loginSuccess(response: AuthResponse) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
//        Log.d("RESULT", response.data.access_token + "TOKENS")
//        til_email?.editText?.setText(response.data.access_token)
    }

    private fun loginProcess() {
        doRequest{
            presenter.login(til_email.value(), til_password.value())
        }.add(jobs)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeJobs()
    }

    private fun validateForm(): Boolean {
        val isValid = mutableListOf<Boolean>()
        til_email?.validate(ValidatorType.Email, ValidatorType.Required)?.let {
            isValid.add(
                it
            )
        }

        til_password?.validate(ValidatorType.Required, ValidatorType.MinimumLength(6))?.let {
            isValid.add(it)
        }

        return !isValid.contains(false)
    }
}