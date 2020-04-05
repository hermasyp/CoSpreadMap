package com.catnip.cospreadmap.data.model.spread.global

import com.google.gson.annotations.SerializedName

data class GlobalSpread(

    @field:SerializedName("OBJECTID")
    val id: Int? = null,

    @field:SerializedName("Recovered")
    val recovered: Int? = null,

    @field:SerializedName("Country_Region")
    val countryRegion: String? = null,

    @field:SerializedName("Active")
    val active: Int? = null,

    @field:SerializedName("Last_Update")
    val lastUpdate: Long? = null,

    @field:SerializedName("Deaths")
    val deaths: Int? = null,

    @field:SerializedName("Confirmed")
    val confirmed: Int? = null,

    @field:SerializedName("Lat")
    val lat: Int? = null,

    @field:SerializedName("Long_")
    val long: Int? = null
)