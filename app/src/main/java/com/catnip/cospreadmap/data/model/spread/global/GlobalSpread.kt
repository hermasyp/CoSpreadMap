package com.catnip.cospreadmap.data.model.spread.global

import com.google.gson.annotations.SerializedName

data class GlobalSpread(

    @field:SerializedName("OBJECTID")
    val id: String? = null,

    @field:SerializedName("Recovered")
    val recovered: String? = null,

    @field:SerializedName("Country_Region")
    val countryRegion: String? = null,

    @field:SerializedName("Active")
    val active: String? = null,

    @field:SerializedName("Last_Update")
    val lastUpdate: String? = null,

    @field:SerializedName("Deaths")
    val deaths: String? = null,

    @field:SerializedName("Confirmed")
    val confirmed: String? = null,

    @field:SerializedName("Lat")
    val lat: String? = null,

    @field:SerializedName("Long_")
    val long: String? = null
)