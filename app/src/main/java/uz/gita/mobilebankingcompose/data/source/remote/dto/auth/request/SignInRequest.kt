package uz.gita.mobilebanking.data.source.remote.dto.auth.request

data class SignInRequest(
    val phone: String,
    val password: String
)
