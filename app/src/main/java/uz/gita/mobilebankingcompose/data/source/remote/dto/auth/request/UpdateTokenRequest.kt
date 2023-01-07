package uz.gita.mobilebanking.data.source.remote.dto.auth.request

import com.google.gson.annotations.SerializedName

/**
Created: Bekzod Yusupov
Project: Mobile Banking
Date: 2022/12/15
Time: 18:54
 */

data class UpdateTokenRequest(
    @SerializedName("refresh-token")
    val refreshToken: String
)
