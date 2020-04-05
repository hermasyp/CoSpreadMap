package com.catnip.cospreadmap.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.catnip.cospreadmap.data.db.entity.GlobalSpreadEntity
import com.catnip.cospreadmap.data.db.entity.LocalSpreadEntity

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/



@Database(entities = [GlobalSpreadEntity::class,LocalSpreadEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
companion object{const val DBNAME = "CoSpreadMapDB"}
    abstract fun localSpreadDao():LocalSpreadEntity
    abstract fun globalSpreadDao():GlobalSpreadEntity
}