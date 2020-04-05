package com.catnip.cospreadmap.data.model.spread.global

import com.catnip.cospreadmap.data.model.total.global.GlobalCountWrapper
import com.google.gson.annotations.SerializedName

data class GlobalSpreadResponse(
    @field:SerializedName("attributes")
    val globalSpread: GlobalSpread? = null
)

data class GlobalDataWrapper(
    val spreadData: List<GlobalSpreadResponse>? = null,
    val globalCountWrapper: GlobalCountWrapper? = null
)