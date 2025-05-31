package minicla03.coiquylife.authentication.Presentation.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.coinquyteam.authApplication.Utility.AuthStatus
import dagger.hilt.android.AndroidEntryPoint
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Presentation.ViewModel.AuthViewModel
import minicla03.coiquylife.authentication.R


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_register_layout, container, false)
        initializeUI(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun initializeUI(view: View) {
        emailEditText = view.findViewById(R.id.etEmail)
        passwordEditText = view.findViewById(R.id.etPassword)
        nameEditText = view.findViewById(R.id.etNome)
        surnameEditText = view.findViewById(R.id.etCognome)
        usernameEditText = view.findViewById(R.id.etUsername)
        registerButton = view.findViewById(R.id.btnRegister)
    }

    private fun setupListeners() {
        registerButton.setOnClickListener {
            attemptRegistration()
        }
    }

    private fun attemptRegistration() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val name = nameEditText.text.toString().trim()
        val surname = surnameEditText.text.toString().trim()
        val username = usernameEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || username.isEmpty()) {
            Toast.makeText(context, "Tutti i campi sono obbligatori", Toast.LENGTH_SHORT).show()
            return
        }

        val user = User(
            username = username,
            password = password,
            surname = surname,
            email = email,
            usernameOrEmail = username
        )

        authViewModel.register(user)
    }

    private fun setupObservers() {
        authViewModel.registerStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                AuthStatus.SUCCESS -> {
                    Toast.makeText(context, "Registrazione completata", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), LoginFragment::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                AuthStatus.USER_ALREADY_EXISTS -> {
                    Toast.makeText(context, "Utente già registrato", Toast.LENGTH_SHORT).show()
                }
                AuthStatus.INVALID_EMAIL -> {
                    Toast.makeText(context, "Email non valida", Toast.LENGTH_SHORT).show()
                }
                is AuthStatus.ERROR -> {
                    Toast.makeText(context, "Errore: ${status.message}", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Errore sconosciuto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}