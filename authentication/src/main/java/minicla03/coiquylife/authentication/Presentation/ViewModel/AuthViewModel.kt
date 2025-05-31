package minicla03.coiquylife.authentication.Presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coinquyteam.authApplication.Utility.AuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Domain.Usecase.ILoginUserUseCase
import minicla03.coiquylife.authentication.Domain.Usecase.IRegisterUserUseCase
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: ILoginUserUseCase,
    private val registerUseCase: IRegisterUserUseCase,
) : ViewModel() {

    private val _loginStatus = MutableLiveData<AuthStatus>()
    val loginStatus: LiveData<AuthStatus> get() = _loginStatus

    private val _registerStatus = MutableLiveData<AuthStatus>()
    val registerStatus: LiveData<AuthStatus> get() = _registerStatus

    fun login(email: String?, password: String?) {
        if (isInvalidEmail(email)) {
            _loginStatus.postValue(AuthStatus.INVALID_CREDENTIALS)
            return
        }

        if (password.isNullOrBlank()) {
            _loginStatus.postValue(AuthStatus.INVALID_PASSWORD)
            return
        }

        viewModelScope.launch {
            val user = User(username = email ?: "", password = password)
            loginUseCase.login(user) { result: AuthStatus ->
                _loginStatus.postValue(result)
            }
        }
    }

    fun register(user: User) {
        if (isInvalidEmail(user.email)) {
            _registerStatus.postValue(AuthStatus.INVALID_EMAIL)
            return
        }

        viewModelScope.launch {
            registerUseCase.register(user) { result: AuthStatus ->
                _registerStatus.postValue(result)
            }
        }
    }

    private fun isInvalidEmail(email: String?): Boolean {
        if (email == null) return true
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return !Pattern.matches(emailRegex, email)
    }
}