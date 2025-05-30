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
import androidx.lifecycle.ViewModelProvider
import minicla03.coinquylifek.AUTH.Data.Response.AuthStatus
import minicla03.coinquylifek.AUTH.Domain.Model.User
import minicla03.coinquylifek.AUTH.Presentation.ViewModel.AuthViewModel
import minicla03.coinquylifek.AUTH.Presentation.ViewModel.AuthViewModelFactory
import minicla03.coinquylifek.R

class RegisterFragment : Fragment()
{
    private lateinit var authViewModel: AuthViewModel

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_layout, container, false)
        initializeUI(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val factory = AuthViewModelFactory(requireActivity().application)
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        setupObservers()
        setupListeners()
    }

    private fun initializeUI(view: View)
    {
        emailEditText = view.findViewById(R.id.etEmail)
        passwordEditText = view.findViewById(R.id.etPassword)
        nameEditText = view.findViewById(R.id.etNome)
        surnameEditText = view.findViewById(R.id.etCognome)
        usernameEditText = view.findViewById(R.id.etUsername)
        registerButton = view.findViewById(R.id.btnRegister)
    }

    private fun setupObservers()
    {
        authViewModel.registerResult.observe(viewLifecycleOwner) { result ->
            when (result?.statusAuth)
            {
                AuthStatus.SUCCESS -> {
                    val intent = Intent(context, minicla03.coiquylife.authentication.Presentation.UI.LoginFragment::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                AuthStatus.ALREADY_REGISTERED -> {
                    Toast.makeText(context, "User already exist", Toast.LENGTH_SHORT).show()
                }
                AuthStatus.INVALID_EMAIL -> {
                    Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show()
                }
                AuthStatus.ERROR -> {
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
                }
                else -> { /* no-op */ }
            }
        }
    }

    private fun setupListeners()
    {
        registerButton.setOnClickListener {
            attemptRegistration()
        }
    }

    private fun attemptRegistration()
    {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val name = nameEditText.text.toString().trim()
        val surname = surnameEditText.text.toString().trim()
        val username = usernameEditText.text.toString().trim()

        val user = User(username, name, password, surname, email)
        authViewModel.register(user)
    }
}
