package uz.gita.mobilebanking.data.source.remote.dto.auth.request

data class SignInVerifyRequest(
    val token: String,
    val code: String
)
