package com.catnip.cospreadmap.di

import androidx.room.Room
import com.catnip.cateringlist.utils.rx.AppScheduler
import com.catnip.cospreadmap.data.db.AppDatabase
import com.catnip.cospreadmap.data.db.datasource.GlobalSpreadRoomDataSource
import com.catnip.cospreadmap.data.db.datasource.LocalSpreadRoomDataSource
import com.catnip.cospreadmap.data.network.RetrofitApi
import com.catnip.cospreadmap.data.network.datasource.GlobalSpreadNetworkDataSource
import com.catnip.cospreadmap.data.network.datasource.LocalSpreadNetworkDataSource
import com.catnip.cospreadmap.data.repositories.GlobalSpreadRepository
import com.catnip.cospreadmap.data.repositories.LocalSpreadRepository
import com.catnip.cospreadmap.feature.main.globalspread.GlobalSpreadViewModel
import com.catnip.cospreadmap.feature.main.localspread.LocalSpreadViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.Executors

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val networkModule = module {
    single { RetrofitApi() }
    single { AppScheduler() }
    factory { CompositeDisposable() }
}
val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DBNAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    factory { Executors.newSingleThreadExecutor() }
    single { get<AppDatabase>().globalSpreadDao() }
    single { get<AppDatabase>().localSpreadDao() }
}

val viewModels = module {
    viewModel { LocalSpreadViewModel(get()) }
    viewModel { GlobalSpreadViewModel(get()) }
}

val dataSource = module {
    single { LocalSpreadNetworkDataSource(get()) }
    single { GlobalSpreadNetworkDataSource(get()) }
    single { LocalSpreadRoomDataSource(get(), get()) }
    single { GlobalSpreadRoomDataSource(get(), get()) }
}

val repositories = module {
    factory { LocalSpreadRepository(get(), get(), get(), get()) }
    factory { GlobalSpreadRepository(get(), get(), get(), get()) }
}
