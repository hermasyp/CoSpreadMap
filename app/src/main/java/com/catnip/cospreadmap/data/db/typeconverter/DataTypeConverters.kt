package com.catnip.cospreadmap.data.db.typeconverter

import androidx.room.TypeConverter
import com.catnip.cospreadmap.data.model.spread.global.GlobalDataWrapper
import com.catnip.cospreadmap.data.model.spread.local.LocalDataWrapper
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

class LocalWrapperTypeConverter {
    private val gson: Gson = Gson()
    private val type: Type = object : TypeToken<LocalDataWrapper>() {}.type

    @TypeConverter
    fun stringToObject(json: String): LocalDataWrapper {
        return try {
            gson.fromJson(json, type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            gson.fromJson(json, type)
        }
    }

    @TypeConverter
    fun objectToString(obj: LocalDataWrapper): String {
        return gson.toJson(obj, type)
    }
}
class GlobalWrapperTypeConverter {
    private val gson: Gson = Gson()
    private val type: Type = object : TypeToken<GlobalDataWrapper>() {}.type

    @TypeConverter
    fun stringToObject(json: String): GlobalDataWrapper {
        return try {
            gson.fromJson(json, type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            gson.fromJson(json, type)
        }
    }

    @TypeConverter
    fun objectToString(obj: GlobalDataWrapper): String {
        return gson.toJson(obj, type)
    }
}