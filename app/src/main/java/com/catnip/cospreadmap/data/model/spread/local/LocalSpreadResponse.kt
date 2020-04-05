package com.catnip.cospreadmap.data.model.spread.local

import com.catnip.cospreadmap.data.model.province.Province
import com.catnip.cospreadmap.data.model.total.local.IndonesiaCount
import com.google.gson.annotations.SerializedName

data class LocalSpreadResponse(
	@field:SerializedName("attributes")
	val localSpread: LocalSpread? = null
)

data class LocalSpreadWrapper(
	val localSpread: LocalSpread? = null,
	val province: Province
)

data class LocalDataWrapper(
	val spreadData : List<LocalSpreadWrapper>? = null,
	val spreadCount : IndonesiaCount? = null
)