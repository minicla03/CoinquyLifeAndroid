package minicla03.coiquylife.authentication.Presentation.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import minicla03.coinquylife.authentication.R
import minicla03.coiquylife.authentication.Presentation.ViewModel.AuthViewModel

class LoginFragment : Fragment()
{
    private lateinit var authViewModel: AuthViewModel
    private lateinit var etEmail: TextView
    private lateinit var etPassword: TextView
    private lateinit var btnLogin: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_layout, container, false)
        etEmail = view.findViewById(R.id.editTextUsernameEmail)
        etPassword = view.findViewById(R.id.editTextPassword)
        btnLogin = view.findViewById(R.id.buttonLogin)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val factory = AuthViewModelFactory(requireActivity().application)
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        btnLogin.setOnClickListener()
        {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            authViewModel.login(email, password)
        }

        authViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result?.statusAuth)
            {
                AuthStatus.SUCCESS -> {
                    val intent = Intent(requireActivity(), minicla03.coinquylife.houseselection.Presentation.UI.CoinquyHouseSelectionActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    requireActivity().finish()
                }
                AuthStatus.WRONG_PASSWORD -> {
                    Toast.makeText(context, "Password errata!", Toast.LENGTH_SHORT).show()
                }
                AuthStatus.USER_NOT_FOUND -> {
                    Toast.makeText(context, "User not found!", Toast.LENGTH_SHORT).show()
                }
                AuthStatus.INVALID_EMAIL -> {
                    Toast.makeText(context, "Email non valida!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Login fallito!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
