package uz.gita.mobilebankingcompose.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebanking.data.source.remote.dto.auth.request.SignUpRequest
import uz.gita.mobilebankingcompose.data.source.local.sharedPref.SharedPref
import uz.gita.mobilebankingcompose.data.source.remote.dto.auth.request.SignUpVerifyRequest
import uz.gita.mobilebankingcompose.data.source.remote.serevice.AuthApi
import uz.gita.mobilebankingcompose.domain.repository.AuthRepository
import uz.gita.mobilebankingcompose.util.ResultData
import uz.gita.mobilebankingcompose.util.mLog
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val sharedPref: SharedPref
) : AuthRepository {
    override fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>> = flow<ResultData<Unit>> {
        val response = authApi.signUp(signUpRequest)
        if (response.isSuccessful) {
            response.body()?.let {
                sharedPref.tempToken = it.token
                mLog("From AuthRepo inside signUp -- tempToken = ${it.token}")
                emit(ResultData.Success(Unit))
            }
        }
    }.catch { error ->
        error.message?.let {
            mLog("Failure ${error.cause}")
            mLog("message ${error.message}")
            mLog("Failure ${error.cause}")
        }

    }.flowOn(Dispatchers.IO)

    override fun signUpVerify(signUpRequestVerifyRequest: SignUpVerifyRequest): Flow<ResultData<Unit>> = flow<ResultData<Unit>> {
        val response = authApi.signUpVerify(signUpRequestVerifyRequest)

        if (response.isSuccessful) {
            response.body()?.let {
                mLog("From AuthRepo inside signUpVerify -- accessToken = ${it.accessToken}")
                mLog("From AuthRepo inside signUpVerify -- refreshToken = ${it.refreshToken}")

                sharedPref.accessToken = it.accessToken
                sharedPref.refreshToken = it.refreshToken
            }
        }
    }

}