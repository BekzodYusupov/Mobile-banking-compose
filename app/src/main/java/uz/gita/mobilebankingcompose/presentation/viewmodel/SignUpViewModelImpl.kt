package uz.gita.mobilebankingcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobilebanking.data.source.remote.dto.auth.request.SignUpRequest
import uz.gita.mobilebankingcompose.domain.repository.AuthRepository
import uz.gita.mobilebankingcompose.presentation.screen.signUp.SignUpContract
import uz.gita.mobilebankingcompose.util.ResultData
import uz.gita.mobilebankingcompose.util.mLog
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class SignUpViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignUpContract.SignUpViewModel, ViewModel() {
    override val uiState = MutableStateFlow(SignUpContract.UiState())


    override fun onEventDispatcher(intent: SignUpContract.Intent) {
        when (intent) {
            is SignUpContract.Intent.SignUp -> {
                viewModelScope.launch {

                    val signUpRequest = SignUpRequest(
                        uiState.value.phone,
                        uiState.value.password,
                        uiState.value.firstName,
                        uiState.value.lastName,
                        stringToLong(uiState.value.dateOfBirth),
                        uiState.value.gender
                    )
                    mLog("SignUpViewModelImpl - signupReques = $signUpRequest")

                    authRepository.signUp(signUpRequest).collectLatest {
                        when (it) {
                            is ResultData.Error -> {}
                            is ResultData.Message -> {}
                            is ResultData.Success -> {/*open verify*/ }
                        }
                    }
                }
            }
            is SignUpContract.Intent.FirstName -> reduce {
                it
            }
        }
    }

    private fun reduce(block: (oldState: SignUpContract.UiState) -> SignUpContract.UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }

    private fun stringToLong(date: String): String {
        val f = SimpleDateFormat("dd/MM/yyyy")
        val d: Date = f.parse(date)
        return d.time.toString()
    }
}