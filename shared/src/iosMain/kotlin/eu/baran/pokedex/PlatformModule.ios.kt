package eu.baran.pokedex

import androidx.room.Room
import eu.baran.pokedex.data.local.AppDatabase
import eu.baran.pokedex.data.remote.ApiConfig
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual val platformModule: Module = module {
    single {
        ApiConfig(
            baseUrl = "http://192.168.188.34:8080"
        )
    }
    single { provideDatabaseBuilder() }
}


private fun provideDatabaseBuilder() = Room.databaseBuilder<AppDatabase>(
    name = documentDirectory() + "/pokedex.db"
)

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}