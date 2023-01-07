package uz.gita.mobilebankingcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.source.remote.dto.auth.request.SignUpRequest
import uz.gita.mobilebankingcompose.data.source.remote.dto.auth.request.SignUpVerifyRequest
import uz.gita.mobilebankingcompose.util.ResultData


interface AuthRepository {
    fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>>
    fun signUpVerify(signUpRequestVerifyRequest: SignUpVerifyRequest): Flow<ResultData<Unit>>
}