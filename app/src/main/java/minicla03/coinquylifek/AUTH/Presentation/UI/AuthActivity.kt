package minicla03.coinquylifek.AUTH.Presentation.UI

import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import minicla03.coinquylifek.R

class AuthActivity : AppCompatActivity()
{
    private var showingLogin = true
    private lateinit var switchButton: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_layout)

        // modalità schermo intero
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide() // Nasconde l'ActionBar

        switchButton = findViewById(R.id.textViewRegister)

        if (savedInstanceState == null)
        {
            showFragmentWithText(LoginFragment(), "Non hai un account? Registrati", true)
        }

        switchButton.setOnClickListener {
            if (showingLogin) {
                showFragmentWithText(RegisterFragment(), "Hai già un account? Loggati", false)
            } else {
                showFragmentWithText(LoginFragment(), "Non hai un account? Registrati", true)
            }
        }
    }

    private fun showFragmentWithText(fragment: Fragment, buttonText: String, isLogin: Boolean)
    {
        showingLogin = isLogin

        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.auth_fragment_container, fragment, fragment::class.java.simpleName)
            .commit()

        switchButton.text = buttonText
    }
}