package minicla03.coinquylifek.APP

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import minicla03.coinquylifek.AUTH.Presentation.UI.AuthActivity
import minicla03.coinquylifek.R

class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        Handler().postDelayed({
            val prefs: SharedPreferences = getSharedPreferences("auth_prefs", MODE_PRIVATE)
            val isLoggedIn = prefs.getBoolean("is_logged_in", false)
            val isLoggedWithHouse = prefs.getBoolean("is_logged_with_house", false)

            /*
            if (isLoggedWithHouse) {
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            } else if (isLoggedIn) {
                startActivity(Intent(this@SplashActivity, CoinquyHouseSelectionActivity::class.java))
            } else {
            */
            startActivity(Intent(this, AuthActivity::class.java))
            // }

            finish()
        }, 2500) // ⏳ 2.5 secondi di ritardo (2500 ms)
    }
}
