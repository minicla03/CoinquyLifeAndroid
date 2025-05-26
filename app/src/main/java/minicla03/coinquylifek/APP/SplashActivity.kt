package minicla03.coinquylifek.APP

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import minicla03.coinquylifek.AUTH.Presentation.UI.AuthActivity
import minicla03.coinquylifek.R
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AlertDialog

class SplashActivity : AppCompatActivity()
{
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        handler = Handler(Looper.getMainLooper())
        checkConnectionAndProceed()
    }

    private fun checkConnectionAndProceed() {
        if (isNetworkAvailable(this))
        {
            handler.postDelayed({
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }, 2500)
        }
        else
        {
            showNoInternetDialog()
        }
    }

    private fun showNoInternetDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Connessione assente")
        builder.setMessage("Per continuare è necessaria una connessione a Internet.")
        builder.setCancelable(false)
        builder.setPositiveButton("Riprova") { _, _ ->
            checkConnectionAndProceed()
        }
        builder.show()
    }

    private fun isNetworkAvailable(context: Context): Boolean
    {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
