package minicla03.coiquylife.authentication.Presentation.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import minicla03.coinquylifek.APP.TokenManager

import minicla03.coiquylife.authentication.Data.RepositoryImpl.AuthRepository
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Data.Response.AuthStatus
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Domain.Usecase.ILoginUserUseCase
import minicla03.coiquylife.authentication.Domain.Usecase.IRegisterUserUseCase
import minicla03.coiquylife.authentication.Domain.Repository.IAuthRepository
import minicla03.coiquylife.authentication.Domain.Usecase.LoginUserUseCase
import minicla03.coiquylife.authentication.Domain.Usecase.RegisterUserUseCase

import java.util.regex.Pattern

class AuthViewModel(application: Application) : ViewModel()
{
    private val loginUseCase: ILoginUserUseCase
    private val registerUseCase: IRegisterUserUseCase

    private val _loginResult = MutableLiveData<AuthResult?>()
    val loginResult: LiveData<AuthResult?> get() = _loginResult

    private val _registerResult = MutableLiveData<AuthResult?>()
    val registerResult: LiveData<AuthResult?> get() = _registerResult

    init
    {
        val repo: IAuthRepository = AuthRepository()
        val tokenManager = TokenManager(application)
        loginUseCase = LoginUserUseCase(repo, tokenManager)
        registerUseCase = RegisterUserUseCase(repo)
    }

    fun login(email: String?, password: String?)
    {
        if (isInvalidEmail(email))
        {
            _loginResult.postValue(AuthResult(AuthStatus.INVALID_EMAIL, ""))
            return
        }

        if (password == null)
        {
            _loginResult.postValue(AuthResult(AuthStatus.INVALID_PASSWORD, ""))
            return
        }
        viewModelScope.launch {
            loginUseCase.login(email!!, password) { result -> _loginResult.postValue(result) }
        }
        
    }

    fun register(user: User)
    {
        if (isInvalidEmail(user.email))
        {
            _registerResult.postValue(AuthResult(AuthStatus.INVALID_EMAIL, ""))
            return
        }
        viewModelScope.launch {
            registerUseCase.register(user) { result -> _registerResult.postValue(result) }

        }
    }

    private fun isInvalidEmail(email: String?): Boolean
    {
        if (email == null) return true
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        val pattern = Pattern.compile(emailRegex)
        val matcher = pattern.matcher(email)
        return !matcher.matches()
    }
}
