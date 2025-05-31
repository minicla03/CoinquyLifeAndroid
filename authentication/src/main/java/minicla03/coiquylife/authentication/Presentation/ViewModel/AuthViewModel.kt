package minicla03.coiquylife.authentication.Presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Domain.Usecase.ILoginUserUseCase
import minicla03.coiquylife.authentication.Domain.Usecase.IRegisterUserUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: ILoginUserUseCase,
    private val registerUseCase: IRegisterUserUseCase,
) : ViewModel() {

    private val _loginStatus = MutableLiveData<AuthResult>()
    val loginStatus: LiveData<AuthResult> get() = _loginStatus

    private val _registerStatus = MutableLiveData<AuthResult>()
    val registerStatus: LiveData<AuthResult> get() = _registerStatus

    fun login(email: String, password: String)
    {
        viewModelScope.launch {
            loginUseCase.login(email, password) { result: AuthResult ->
                _loginStatus.postValue(result)
            }
        }
    }

    fun register(username: String, email: String, password: String, name: String, surname: String)
    {
        viewModelScope.launch {
            registerUseCase.register(username, email, password, name, surname) { result: AuthResult ->
                _registerStatus.postValue(result)
            }
        }
    }
}