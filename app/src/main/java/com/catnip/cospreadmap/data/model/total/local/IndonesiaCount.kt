package com.catnip.cospreadmap.data.model.total.local

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

data class IndonesiaCount(
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("positif")
    val positive : String? = null,
    @SerializedName("sembuh")
    val cured : String? = null,
    @SerializedName("meninggal")
    val death : String? = null
)