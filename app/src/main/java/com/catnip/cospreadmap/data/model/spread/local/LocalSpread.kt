package com.catnip.cospreadmap.data.model.spread.local

import com.catnip.cospreadmap.data.model.province.Province
import com.google.gson.annotations.SerializedName

data class LocalSpread(

    @field:SerializedName("FID")
    val id: String? = null,

    @field:SerializedName("Kode_Provi")
    val provinceID: String? = null,

    @field:SerializedName("Kasus_Meni")
    val death: String? = null,

    @field:SerializedName("Kasus_Posi")
    val positive: String? = null,

    @field:SerializedName("Provinsi")
    val provinceName: String? = null,

    @field:SerializedName("Kasus_Semb")
    val cured: String? = null

)