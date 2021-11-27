package id.widianapw.androidutils.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.widianapw.android_utils.extensions.clickAndGoto
import id.widianapw.androidutils.R
import id.widianapw.androidutils.view.auth.LoginActivity
import id.widianapw.androidutils.view.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnHello?.clickAndGoto(this,LoginActivity::class.java)
    }
}