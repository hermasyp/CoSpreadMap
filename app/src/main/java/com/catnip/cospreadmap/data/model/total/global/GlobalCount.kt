package com.catnip.cospreadmap.data.model.total.global

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

data class Count(
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("value")
    val value : String? = null
)
data class GlobalCountWrapper(
    val positiveCount: Count,
    val curedCount: Count,
    val deathCount: Count
)

