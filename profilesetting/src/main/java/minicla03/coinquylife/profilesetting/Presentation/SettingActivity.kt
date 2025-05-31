package minicla03.coinquylife.ProfileSetting.UI

import android.R
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import java.util.Objects

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Objects.requireNonNull<ActionBar?>(getSupportActionBar()).hide()
        setContentView(R.layout.setting_layout)

        val toolbar = findViewById<MaterialToolbar?>(R.id.toolbarSettings)
        toolbar.setNavigationOnClickListener(View.OnClickListener { v: View? -> onBackPressed() })

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.settingFragmentContainer, SettingOptionFragment())
            .commit()
    }
}