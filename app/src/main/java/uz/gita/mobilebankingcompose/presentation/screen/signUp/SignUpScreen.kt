package uz.gita.mobilebankingcompose.presentation.screen.signUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.mobilebankingcompose.presentation.util.CustomOutlinedTextField
import uz.gita.mobilebankingcompose.presentation.viewmodel.SignUpViewModelImpl
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme


class SignUpScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SignUpContract.SignUpViewModel = getViewModel<SignUpViewModelImpl>()
        val uiState = viewModel.uiState.collectAsState().value
        SignUpScreenContent(uiState, viewModel::onEventDispatcher)
    }

}


@Composable
fun SignUpScreenContent(
    uiState: SignUpContract.UiState,
    eventDispatcher: (SignUpContract.Intent) -> Unit
) {
    CustomOutlinedTextField(uiState.firstName, eventDispatcher = eventDispatcher, label = "")

}

@Preview
@Composable
fun PreSignUp() {
    MobileBankingComposeTheme {
        SignUpScreenContent(uiState = SignUpContract.UiState(), eventDispatcher = { })

    }
}