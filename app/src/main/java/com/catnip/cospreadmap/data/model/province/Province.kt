package com.catnip.cospreadmap.data.model.province

import com.google.gson.annotations.SerializedName

data class Province(

    @field:SerializedName("alt_name")
    val altName: String? = null,

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
)
