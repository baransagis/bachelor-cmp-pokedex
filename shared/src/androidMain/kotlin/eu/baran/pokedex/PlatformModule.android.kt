package eu.baran.pokedex

import android.content.Context
import androidx.room.Room
import eu.baran.pokedex.data.local.AppDatabase
import eu.baran.pokedex.data.remote.ApiConfig
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single {
        ApiConfig(
            baseUrl = "http://192.168.188.34:8080"
        )
    }
    single { provideDatabaseBuilder(get()) }
}

private fun provideDatabaseBuilder(context: Context) = Room.databaseBuilder<AppDatabase>(
    context = context,
    name = context.applicationContext.getDatabasePath("pokedex.db").absolutePath,
)